package utn.frba.tadp.firewall.utils;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
	private Map<Class<?>, Object> clases;
	
	private static ApplicationContext instance = null;
	
	private ApplicationContext() {
		this.clases = new HashMap<Class<?>, Object>();
	}
	
	public static ApplicationContext getInstance() {
		if(instance == null) {
			instance = new ApplicationContext();
		}
		
		return instance;
	}
	
	public <T> T getObject(Class<T> aClass) {
		return (T) this.clases.get(aClass);
	}
	
	void setObject(Class<?> aClass, Object object) {
		this.clases.put(aClass, object);
	}
}
