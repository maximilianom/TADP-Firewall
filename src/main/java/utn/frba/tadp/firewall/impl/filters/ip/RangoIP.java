package utn.frba.tadp.firewall.impl.filters.ip;

public class RangoIP {
	private String ipInicio;
	private String ipFin;
	
	/**
	 * Construye un rango de IP con una IP de inicio y una de fin de rango.
	 * 
	 * @param ipInicio La IP donde arranca el rango.
	 * @param ipFin La IP donde finaliza el rango.
	 */
	public RangoIP(String ipInicio, String ipFin){
		this.ipInicio = ipInicio;
		this.ipFin = ipFin;
	}
	
	/**
	 * Construye un rango de IP que comienza y termina en la misma IP.
	 * 
	 * @param ip La IP única del rango.
	 */
	public RangoIP(String ip){
		this.ipInicio = ip;
		this.ipFin = ip;
	}
	
	/**
	 * Evalúa una IP para saber si está incluída dentro de éste rango.
	 * 
	 * @param unaIP La IP a evaluar si está dentro del rango.
	 * @return True si unaIP está en el rango especificado por este rango de IP, false de lo contrario.
	 */
	public boolean isBetween(String unaIP){
		Long minimum = ipToLong(this.ipInicio);
		Long maximum = ipToLong(this.ipFin);
		Long current = ipToLong(unaIP);
		
		return !(current <= maximum || current >= minimum);
	}
	
	private Long ipToLong(String ip) {
		String[] values = ip.split("\\.");
		Long numero = 0L;
		for (int i = 0; i < values.length; i++) {
			numero += Long.valueOf(values[i])
					* (int) Math.pow(10, (3 * (values.length - (i + 1))));
		}
		return numero;
	}
}
