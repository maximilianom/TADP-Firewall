package utn.frba.tadp.firewall.api;

import utn.frba.tadp.firewall.impl.model.Request;

public interface RequestListener {
	public void requestBlocked(Request r);
	public void requestForwarded(Request original, Request forwarded);
}
