package utn.frba.tadp.firewall.impl.model;

import utn.frba.tadp.firewall.api.ResponseListener;

public class Request {
	private Integer port;
	private String ipOrigen;
	private String ipDestino;
	private ResponseListener responseListener = null;

	public Request(Integer port, String ipOrigen, String ipDestino) {
		this.port = port;
		this.ipOrigen = ipOrigen;
		this.ipDestino = ipDestino;
	}
	
	public void enviarMensajeHaciaOrigen(String mensaje) {
		// dummy implementation
		System.out.println(mensaje);
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
	
	public void setResponseListener(ResponseListener l){
		this.responseListener = l;
	}
	
	public void requestBlocked(){
		if(this.hasResponseListener())
			this.responseListener.requestBlocked(this);
	}
	
	public void requestForwarded(Request forwardRequest){
		if(this.hasResponseListener())
			this.responseListener.requestForwarded(this, forwardRequest);
	}
	
	public boolean hasResponseListener(){
		return this.responseListener != null;
	}
}
