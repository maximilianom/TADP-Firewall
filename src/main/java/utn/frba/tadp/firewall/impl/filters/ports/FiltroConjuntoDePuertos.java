package utn.frba.tadp.firewall.impl.filters.ports;

import java.util.List;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class FiltroConjuntoDePuertos implements Filter {
	private List<Integer> puertos;
	
	public FiltroConjuntoDePuertos(List<Integer> puertos) {
		this.puertos = puertos;
	}

	public boolean accepts(Request request) {
		return !(puertos.contains(request.getPort()));
	}
	
	

}
