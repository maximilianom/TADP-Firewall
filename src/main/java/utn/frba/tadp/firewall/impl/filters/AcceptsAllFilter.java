package utn.frba.tadp.firewall.impl.filters;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class AcceptsAllFilter implements Filter {

	public boolean accepts(Request request) {
		return true;
	}

}
