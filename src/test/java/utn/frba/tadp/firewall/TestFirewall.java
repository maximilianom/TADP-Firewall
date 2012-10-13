package utn.frba.tadp.firewall;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utn.frba.tadp.firewall.*;
import utn.frba.tadp.firewall.api.*;
import utn.frba.tadp.firewall.impl.*;
import utn.frba.tadp.firewall.impl.actions.InformarBloqueoAction;
import utn.frba.tadp.firewall.impl.actions.LoggearPaqueteAction;
import utn.frba.tadp.firewall.impl.filters.AcceptsAllFilter;
import utn.frba.tadp.firewall.impl.filters.FiltroCompuesto;
import utn.frba.tadp.firewall.impl.filters.ip.FiltroIpIndividual;
import utn.frba.tadp.firewall.impl.filters.ip.FiltroRangoIp;
import utn.frba.tadp.firewall.impl.filters.ports.FiltroConjuntoDePuertos;
import utn.frba.tadp.firewall.impl.model.Regla;
import utn.frba.tadp.firewall.impl.model.exceptions.BloqueaRequestException;
import utn.frba.tadp.firewall.mock.InformarBloqueoMock;

public class TestFirewall {
	private Firewall firewall;
	
	@Before
	public void initialize(){
		this.firewall = new Firewall();		
	}
	
	@Test
	public void testBloqueoIP() {
		this.testBloqueo(new FiltroIpIndividual("127.0.0.1", "127.0.0.1"));
	}

	@Test
	public void testBloqueoPuertos(){
		ArrayList<Integer> puertosBloqueados = new ArrayList<Integer>();
		puertosBloqueados.add(new Integer(5));
		puertosBloqueados.add(new Integer(6));
		
		this.testBloqueo(new FiltroConjuntoDePuertos(puertosBloqueados));
	}
	
	@Test
	public void testFiltroCompuesto(){
		FiltroCompuesto filtroCompuesto;
		
		ArrayList<Integer> puertosBloqueados = new ArrayList<Integer>();
		puertosBloqueados.add(15);
		puertosBloqueados.add(99);
		
		ArrayList<Filter> filtros = new ArrayList<Filter>();
		filtros.add(new FiltroConjuntoDePuertos(puertosBloqueados));
		filtros.add(new AcceptsAllFilter());
		filtros.add(new FiltroRangoIp("127.0.0.1", "127.0.0.1", "127.0.0.0", "0.0.0.0"));
		
		filtroCompuesto = new FiltroCompuesto(filtros);
		
		// Como ninguna de las reglas bloquea el envío del paquete que utiliza
		// el método "testBloqueo", el método debería arrojar un AssertionError,
		// motivo por el cual el test es exitoso si se arroja dicho error y se lo
		// captura.
		try{
			this.testBloqueo(filtroCompuesto);
			Assert.fail();
		}catch (java.lang.AssertionError e){
			return;
		}
	}
	
	
	/**
	 * Método que dado un filtro corrobora que al evaluar el pedido:
	 * Origen: 127.0.0.1, Destino: 127.0.0.1 y Puerto: 5
	 * se bloquee el envío de paquetes.
	 * Además verifica que el mismo pedido con puerto 80 pase.
	 * 
	 * @param filtro El filtro a probar que bloquea el envío.
	 */
	private void testBloqueo(Filter filtro){
		List<Action> blockActions = new ArrayList<Action>();
		
		InformarBloqueoMock informarBloqueoMock = new InformarBloqueoMock();
		blockActions.add(informarBloqueoMock);
		
		Regla bloquear = new Regla(new ArrayList<Action>(), blockActions, filtro);
		this.firewall.addRule(bloquear);
		
		boolean exceptionRaised = false;
		try{
			this.firewall.evaluate("127.0.0.1", "127.0.0.1", new Integer(5));
		}catch (BloqueaRequestException ex){
			exceptionRaised = true;
		}finally{
			// Si bloquea, debería lanzar una excepción informando que se bloquea.
			assertTrue(exceptionRaised);
		}
		// Antes de lanzar la excepción, debería ejecutar las acciones pre-bloqueo.
		assertTrue(informarBloqueoMock.wasCalled());
		
		try{
			this.firewall.evaluate("127.0.0.1", "127.0.0.2", new Integer(80));
		}catch (BloqueaRequestException ex){
			// Si éste pedido no pasa, falló el test.
			Assert.fail();
		}
	}
}
