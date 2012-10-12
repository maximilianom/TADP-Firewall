package utn.frba.tadp.firewall.impl.filters.ip;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class FiltroIpIndividual implements Filter {
	private String ipOrigen;
	private String ipDestino;

	public FiltroIpIndividual(String ipOrigen, String ipDestino) {
		this.ipOrigen = ipOrigen;
		this.ipDestino = ipDestino;
	}

	public boolean accepts(Request request) {
		return !(request.getIpDestino().equals(this.ipDestino) && request
				.getIpOrigen().equals(this.ipOrigen));
	}

}