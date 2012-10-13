package utn.frba.tadp.firewall;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utn.frba.tadp.firewall.api.Action;
import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.Firewall;
import utn.frba.tadp.firewall.impl.filters.ip.FiltroIpIndividual;
import utn.frba.tadp.firewall.impl.model.Regla;
import utn.frba.tadp.firewall.impl.model.exceptions.BloqueaRequestException;
import utn.frba.tadp.firewall.mock.InformarBloqueoMock;

public class TestDelFirewall {
	private Firewall firewall;
	private InformarBloqueoMock actionMock;
	
	@Before
	public void setUp() {
		this.firewall = new Firewall();
		this.actionMock = new InformarBloqueoMock();
	}
	
	@Test(expected = BloqueaRequestException.class)
	public void testFirewallBloquea() {
		Filter filter = new FiltroIpIndividual("127.0.0.1", "127.0.0.1");
		List<Action> lista = new ArrayList<Action>();
		lista.add(actionMock);
		Regla regla = new Regla(new ArrayList<Action>(), lista, filter);
		firewall.addRule(regla);
		
		try {
			firewall.evaluate("127.0.0.1", "127.0.0.1", 80);
		} catch(BloqueaRequestException e) {
			assertTrue(actionMock.wasCalled());
			throw e;
		}
		
	}

}
