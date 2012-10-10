package utn.frba.tadp.firewall.impl.model;

import utn.frba.tadp.firewall.impl.Firewall;

public class Request {
	private Integer port;
	private String ipOrigen;
	private String ipDestino;
	private Firewall firewall;
	
	public Request(Integer port, String ipOrigen, String ipDestino) {
		this.port = port;
		this.ipOrigen = ipOrigen;
		this.ipDestino = ipDestino;
	}
	
	public void enviarMensajeHaciaOrigen(String mensaje) {
		// dummy implementation
		System.out.println(mensaje);
	}

	public Firewall getFirewall() {
		return firewall;
	}

	public void setFirewall(Firewall firewall) {
		this.firewall = firewall;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getIpOrigen() {
		return ipOrigen;
	}

	public void setIpOrigen(String ipOrigen) {
		this.ipOrigen = ipOrigen;
	}

	public String getIpDestino() {
		return ipDestino;
	}

	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
	}
	

}
