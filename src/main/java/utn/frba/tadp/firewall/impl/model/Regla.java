package utn.frba.tadp.firewall.impl.model;

import java.util.List;

import utn.frba.tadp.firewall.api.Action;
import utn.frba.tadp.firewall.api.Filter;
import utn.frba.tadp.firewall.impl.model.exceptions.BloqueaRequestException;

public class Regla {
	private List<Action> publicActions;
	private List<Action> blockActions;
	private Filter filter;

	public Regla(List<Action> publicActions, List<Action> blockActions, Filter filter) {
		this.publicActions = publicActions;
		this.blockActions = blockActions;
		this.filter = filter;
	}
	
	public void check(Request request) {
		if(!filter.accepts(request)) {
			for(Action action : blockActions) {
				action.makeAction(request);
			}
			throw new BloqueaRequestException("El filtro configurado no permite al request avanzar");
		}
		
		for(Action action : publicActions) {
			action.makeAction(request);
		}
	}

	public List<Action> getPublicActions() {
		return publicActions;
	}

	public void addPublicAction(Action action) {
		this.publicActions.add(action);
	}

	public void setPublicActions(List<Action> publicActions) {
		this.publicActions = publicActions;
	}

	public List<Action> getBlockActions() {
		return blockActions;
	}

	public void addBlockAction(Action action) {
		this.blockActions.add(action);
	}

	public void setBlockActions(List<Action> blockActions) {
		this.blockActions = blockActions;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}
