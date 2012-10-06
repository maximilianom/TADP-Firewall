package utn.frba.tadp.firewall.impl.filters.ip;

import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.Request;

public class FiltroRangoIp implements Filter {
	private String ipOrigen;
	private String ipDestinoMinimo;
	private String ipDestinoMaximo;

	public FiltroRangoIp(String ipOrigen, String ipDestinoMinimo,
			String ipDestinoMaximo) {
		this.ipOrigen = ipOrigen;
		this.ipDestinoMaximo = ipDestinoMaximo;
		this.ipDestinoMinimo = ipDestinoMinimo;
	}

	public boolean accepts(Request request) {
		return (isBetweenValidRange(request.getIpDestino())
				&& !request.getIpOrigen().equals(this.ipOrigen));
	}
	
	private boolean isBetweenValidRange(String ipDestino) {
		Long minimum = ipToLong(this.ipDestinoMinimo);
		Long maximum = ipToLong(this.ipDestinoMaximo);
		Long current = ipToLong(ipDestino);
		
		return !(current <= maximum || current >= minimum);
	}
	
	private Long ipToLong(String ip) {
		String[] values = ip.split("\\.");
		Long numero = 0L;
		for (int i = 0; i < values.length; i++) {
			System.out.println(values[i]);
			numero += Long.valueOf(values[i])
					* (int) Math.pow(10, (3 * (values.length - (i + 1))));
		}
		return numero;
	}

}
