package utn.frba.tadp.firewall.mock;

import utn.frba.tadp.firewall.api.Action;
import utn.frba.tadp.firewall.impl.model.Request;

public class InformarBloqueoMock implements Action {
	private boolean called = false;
	
	public void makeAction(Request request) {
		this.called = true;		
	}

	public boolean wasCalled(){
		return this.called;
	}
}
