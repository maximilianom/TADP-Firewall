package utn.frba.tadp.firewall.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Builder<T, D extends T> {
	private Class<T> type;
	private Class<D> declaredType;
	private Map<String, Object> parameters;
	private Map<String, Class<?>> dependencies;

	public Builder() {
		this.parameters = new HashMap<String, Object>();
		this.dependencies = new HashMap<String, Class<?>>();
	}

	public void setToApplicationContext() {
		ApplicationContext.getInstance().setObject(this.declaredType,
				this.build());
	}
	
	@SuppressWarnings("unchecked")
	public D build(){
		try {
			T object = type.newInstance();
			for (String name : parameters.keySet()) {
				Field field = type.getDeclaredField(name);
				field.setAccessible(true);
				field.set(object, parameters.get(name));
			}

			for (String dependency : dependencies.keySet()) {
				Field field = type.getDeclaredField(dependency);
				field.setAccessible(true);
				field.set(
						object,
						ApplicationContext.getInstance().getObject(
								dependencies.get(dependency)));
			}
			return (D) object;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	public void setType(Class<T> aClass) {
		this.type = aClass;
	}

	public void setDeclaredType(Class<D> declaredType) {
		this.declaredType = declaredType;
	}

	public void setParameter(String name, Object object) {
		this.parameters.put(name, object);
	}

	public void setDependency(String name, Class<?> aClass) {
		this.dependencies.put(name, aClass);
	}
}
