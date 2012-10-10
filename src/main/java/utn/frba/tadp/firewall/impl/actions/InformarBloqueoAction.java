package utn.frba.tadp.firewall.impl.actions;

import utn.frba.tadp.firewall.api.Action;
import utn.frba.tadp.firewall.impl.model.Request;

public class InformarBloqueoAction implements Action {

	public void makeAction(Request request) {
		request.enviarMensajeHaciaOrigen("No se pudo enviar mensaje por firewall");
	}

}
