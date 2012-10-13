package utn.frba.tadp.firewall.impl.filters.ip;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class FiltroRangoIp implements Filter {
	private RangoIP rangoOrigen;
	private RangoIP rangoDestino;
	

	public FiltroRangoIp(String ipOrigenMinimo, String ipOrigenMaximo, String ipDestinoMinimo,
			String ipDestinoMaximo) {
		this.rangoOrigen = new RangoIP(ipOrigenMinimo, ipOrigenMaximo);
		this.rangoDestino = new RangoIP(ipDestinoMinimo, ipDestinoMaximo);
	}
	
	public FiltroRangoIp(RangoIP rangoOrigen, RangoIP rangoDestino){
		this.rangoOrigen = rangoOrigen;
		this.rangoDestino = rangoDestino;
	}

	public boolean accepts(Request request) {
		return !rangoOrigen.isBetween(request.getIpOrigen()) && ! rangoDestino.isBetween(request.getIpDestino());
	}

}
