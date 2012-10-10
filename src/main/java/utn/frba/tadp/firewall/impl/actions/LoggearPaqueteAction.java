package utn.frba.tadp.firewall.impl.actions;

import utn.frba.tadp.firewall.api.Action;
import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class LoggearPaqueteAction implements Action {
	
	private Filter filtro;
	
	public LoggearPaqueteAction(Filter filtro ) {
		this.filtro = filtro;
	}

	public void makeAction(Request request) {
		if(filtro.accepts(request)) {
			System.out.println("Imprimiendo informacion del paquete");
			System.out.println("Ip origen: " + request.getIpOrigen());
			System.out.println("Ip destino: " + request.getIpDestino());
			System.out.println("Puerto: " + request.getPort());
		}
	}

}
