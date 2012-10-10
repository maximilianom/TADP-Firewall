package utn.frba.tadp.firewall.impl;

import java.util.List;

import utn.frba.tadp.firewall.impl.model.Regla;
import utn.frba.tadp.firewall.impl.model.Request;

public class Firewall {
	private List<Regla> reglas;
	
	public void evaluate(String ipOrigen, String ipDestino, Integer puerto) {
		Request request = new Request(puerto, ipOrigen, ipDestino);
		
		for(Regla regla : reglas) {
			regla.check(request);
		}
	}
}
