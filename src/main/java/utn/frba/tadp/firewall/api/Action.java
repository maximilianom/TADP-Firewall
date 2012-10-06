package utn.frba.tadp.firewall.api;

import utn.frba.tadp.firewall.impl.model.Request;

public interface Action {
	public void makeAction(Request request);
}
