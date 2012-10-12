package utn.frba.tadp.firewall.impl;

import java.util.ArrayList;
import java.util.List;

import utn.frba.tadp.firewall.impl.model.Regla;
import utn.frba.tadp.firewall.impl.model.Request;

public class Firewall {
	private List<Regla> reglas;
	
	public Firewall(){
		this.reglas = new ArrayList<Regla>();
	}
	
	public void evaluate(String ipOrigen, String ipDestino, Integer puerto) {
		Request request = new Request(puerto, ipOrigen, ipDestino);
		
		for(Regla regla : reglas) {
			regla.check(request);
		}
	}
	
	public void addRule(Regla rule){
		this.reglas.add(rule);
	}
	
	public List<Regla> getRules(){
		return this.reglas;
	}
}
