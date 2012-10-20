package utn.frba.tadp.firewall;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utn.frba.tadp.firewall.api.Action;
import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.Firewall;
import utn.frba.tadp.firewall.impl.actions.InformarBloqueoAction;
import utn.frba.tadp.firewall.impl.filters.ip.FiltroIpIndividual;
import utn.frba.tadp.firewall.impl.model.Regla;
import utn.frba.tadp.firewall.impl.model.Request;
import utn.frba.tadp.firewall.mock.ResponseListenerMock;

public class TestDelFirewall {
	private Firewall firewall;
	private ResponseListenerMock listenerMock;
	
	@Before
	public void setUp() {
		this.firewall = new Firewall();
		this.listenerMock = new ResponseListenerMock();
		this.firewall.setDefaultResponseListener(this.listenerMock);
	}
	
	@Test
	public void testFirewallBloquea() {
		Filter filter = new FiltroIpIndividual("127.0.0.1", "127.0.0.1");
		
		List<Action> acciones = new ArrayList<Action>();
		acciones.add(new InformarBloqueoAction());
		
		Regla regla = new Regla(new ArrayList<Action>(), acciones, filter);
		firewall.addRule(regla);
		Request r = new Request(80, "127.0.0.1", "127.0.0.1");
		
		firewall.evaluate(r);
		assertTrue(listenerMock.wasBlocked(r));
	}

}
