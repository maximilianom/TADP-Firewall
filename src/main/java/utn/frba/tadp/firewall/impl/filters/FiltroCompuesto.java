package utn.frba.tadp.firewall.impl.filters;

import java.util.List;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class FiltroCompuesto implements Filter{
	private List<Filter> filtros;


	
	public FiltroCompuesto(List<Filter> filtros) {
		this.filtros = filtros;
	}
	
	public boolean accepts(Request request) {
		for(Filter filtro : this.filtros) {
			if (!filtro.accepts(request)) {
				return false;
			}
		}
		return true;
	}
}
