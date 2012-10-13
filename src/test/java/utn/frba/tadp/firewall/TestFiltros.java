package utn.frba.tadp.firewall;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.filters.ip.FiltroIpIndividual;
import utn.frba.tadp.firewall.impl.filters.ip.FiltroRangoIp;
import utn.frba.tadp.firewall.impl.filters.ports.FiltroConjuntoDePuertos;
import utn.frba.tadp.firewall.impl.filters.ports.FiltroPuertoIndividual;
import utn.frba.tadp.firewall.impl.model.RangoIP;
import utn.frba.tadp.firewall.impl.model.Request;

public class TestFiltros {
	
	private String ipOrigen = "127.0.0.1";
	private String ipDestino = "127.0.0.1";
	private Integer puerto = 80;
	
	private Request miRequest;
	
	@Before
	public void setUp() {
		miRequest = new Request(puerto, ipOrigen, ipDestino);
	}
	
	@Test
	public void testFiltroIpIndividual() {
		Filter filter = new FiltroIpIndividual(this.ipOrigen, this.ipDestino);
		assertFalse(filter.accepts(this.miRequest));
	}
	
	@Test
	public void testFiltroRangoIp() {
		RangoIP rangoOrigen = new RangoIP("126.256.256.256", "127.0.0.2");
		RangoIP rangoDestino = new RangoIP("126.256.256.256", "127.0.0.2");
		
		Filter filter = new FiltroRangoIp(rangoOrigen, rangoDestino);
		assertFalse(filter.accepts(miRequest));
	}
	
	@Test
	public void testFiltroPuertoIndividual() {
		Filter filter = new FiltroPuertoIndividual(puerto);
		assertFalse(filter.accepts(miRequest));
	}
	
	@Test
	public void testFiltroConjuntoDePuertos() {
		List<Integer> puertos = Arrays.asList(79, 80, 81);
		Filter filter = new FiltroConjuntoDePuertos(puertos);
		assertFalse(filter.accepts(miRequest));
	}
}