package utn.frba.tadp.firewall.mock;

import utn.frba.tadp.firewall.api.RequestListener;
import utn.frba.tadp.firewall.impl.model.Request;

public class RequestListenerMock implements RequestListener {
	private Request blockedRequest = null;
	private Request forwardedRequest = null;
	
	public void requestBlocked(Request r) {
		this.blockedRequest = r;
		System.out.println("Request bloqueado\nOrigen: " + r.getIpOrigen()+
							"\nDestino: "+ r.getIpDestino() + "\nPuerto: " + r.getPort());
	}

	public void requestForwarded(Request original, Request forwarded) {
		this.forwardedRequest = original;
	}
	
	public boolean wasBlocked(Request aRequest){
		return this.blockedRequest == aRequest;
	}
	
	public boolean wasForwarded(Request aRequest){
		return this.forwardedRequest == aRequest;
	}
}
