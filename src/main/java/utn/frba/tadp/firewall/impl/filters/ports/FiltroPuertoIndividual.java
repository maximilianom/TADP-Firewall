package utn.frba.tadp.firewall.impl.filters.ports;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class FiltroPuertoIndividual implements Filter {
	private Integer puerto;
	
	public FiltroPuertoIndividual(Integer puerto) {
		this.puerto = puerto;
	}

	public boolean accepts(Request request) {
		return !(request.getPort() == this.puerto);
	}
}
