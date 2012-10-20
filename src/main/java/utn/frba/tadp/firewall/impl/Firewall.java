package utn.frba.tadp.firewall.impl;

import java.util.ArrayList;
import java.util.List;

import utn.frba.tadp.firewall.api.ResponseListener;
import utn.frba.tadp.firewall.impl.model.Regla;
import utn.frba.tadp.firewall.impl.model.Request;

public class Firewall {
	private List<Regla> reglas;
	private ResponseListener defaultListener = null;
	
	public Firewall(){
		this.reglas = new ArrayList<Regla>();
	}
	
	public void evaluate(Request request) {
		if(! request.hasResponseListener())request.setResponseListener(this.defaultListener);
		
		for(Regla regla : reglas) {
			regla.check(request);
		}
	}
	
	public void evaluate(String ipOrigen, String ipDestino, Integer puerto) {
		this.evaluate(new Request(puerto, ipOrigen, ipDestino));
	}
	
	public void addRule(Regla rule){
		this.reglas.add(rule);
	}
	
	public List<Regla> getRules(){
		return this.reglas;
	}
	
	public void setDefaultResponseListener(ResponseListener l){
		this.defaultListener = l;
	}

}
