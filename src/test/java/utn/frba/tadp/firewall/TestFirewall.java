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
import utn.frba.tadp.firewall.impl.filters.ip.FiltroIpIndividual;
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
	public void testBloqueo() {
		Filter filtroIP = new FiltroIpIndividual("127.0.0.1", "127.0.0.1");
		List<Action> blockActions = new ArrayList<Action>();
		
		InformarBloqueoMock informarBloqueoMock = new InformarBloqueoMock();
		blockActions.add(informarBloqueoMock);
		
		Regla bloquearIp = new Regla(new ArrayList<Action>(), blockActions, filtroIP);
		this.firewall.addRule(bloquearIp);
		
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
			this.firewall.evaluate("127.0.0.1", "127.0.0.2", new Integer(5));
		}catch (BloqueaRequestException ex){
			// Finalmente, debería dejarnos enviar a otra ip de destino distinta,
			// si lanza una excepción, falla el filtro.
			Assert.fail();
		}
	}

}
