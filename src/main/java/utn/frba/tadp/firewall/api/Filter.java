package utn.frba.tadp.firewall.api;

import utn.frba.tadp.firewall.impl.model.Request;

public interface Filter {
	public boolean accepts(Request request);
}
