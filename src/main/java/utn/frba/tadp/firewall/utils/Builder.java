package utn.frba.tadp.firewall.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Builder {
	private Class<?> type;
	private Class<?> declaredType;
	private Map<String, Object> parameters;
	private Map<String, Class<?>> dependencies;

	public Builder() {
		this.parameters = new HashMap<String, Object>();
		this.dependencies = new HashMap<String, Class<?>>();
	}

	public void setToApplicationContext() {
		try {
			Object object = type.newInstance();
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

			ApplicationContext.getInstance().setObject(this.declaredType,
					object);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	public void setType(Class<?> aClass) {
		this.type = aClass;
	}

	public void setDeclaredType(Class<?> declaredType) {
		this.declaredType = declaredType;
	}

	public void setParameter(String name, Object object) {
		this.parameters.put(name, object);
	}

	public void setDependency(String name, Class<?> aClass) {
		this.dependencies.put(name, aClass);
	}
}
