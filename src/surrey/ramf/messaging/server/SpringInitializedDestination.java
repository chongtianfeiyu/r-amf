package surrey.ramf.messaging.server;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import surrey.ramf.messaging.proxy.TypeEditor;

/*
Copyright (c) 2014 Surrey Hughes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/**
 * @author Surrey
 *
 */
public class SpringInitializedDestination implements Destination, InitializingBean {

	private String name;
	private Object target;
	private Map<String, List<Method>> methodMap = new HashMap<String, List<Method>>();
	private TypeEditor typeEditor;
	private boolean initialized = false;

	@Override
	public String getName() {
		return name;
	}

	public static void main(String[] args) throws Exception {
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Service getService(String operation, Object[] params) {
		List<Method> methods = methodMap.get(operation);
		int paramLength = params == null ? 0 : params.length;
		Service service = null;
		if (methods != null) {
			for (Method method : methods) {
				if (method.getParameterTypes().length == paramLength) {
					Class<?>[] methodTypes = method.getParameterTypes();
					int matches = 0;
					Object[] newParams = new Object[paramLength];
					for (int i = 0; i < paramLength; i++) {
						Object param = params[i];
						Class<?> requiredClass = methodTypes[i];
						if (param == null || requiredClass.isAssignableFrom(param.getClass())) {
							matches++;
							newParams[i] = param;
						} else {
							param = convertParam(param, requiredClass);
							if (param == null || requiredClass.isAssignableFrom(param.getClass())) {
								matches++;
								newParams[i] = param;
							}
						}
					}
					if (matches == paramLength) {
						service = new RAMFService(target, method, newParams);
						break;
					}
				}
			}
		}
		return service;
	}

	private Object convertParam(Object param, Class<?> requiredType) {
		if (typeEditor != null) {
			param = typeEditor.convert(param, requiredType);
		}
		return param;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (target != null) {
			// introspect and cache methods
			BeanInfo info = Introspector.getBeanInfo(target.getClass(), Object.class);
			MethodDescriptor[] meths = info.getMethodDescriptors();
			for (MethodDescriptor meth : meths) {
				Method method = meth.getMethod();
				List<Method> methods = methodMap.get(method.getName());
				if (methods == null) {
					methods = new ArrayList<Method>();
					methodMap.put(method.getName(), methods);
				}
				methods.add(method);
			}
		}
		initialized = true;
	}

	/**
	 * @return the target
	 */
	public Object getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 * @throws Exception
	 */
	public void setTarget(Object target) throws Exception {
		this.target = target;
		if (initialized) { // target being set outside of spring context.
			initialized = false;
			afterPropertiesSet();
		}
	}

	/**
	 * @return the typeEditor
	 */
	public TypeEditor getTypeEditor() {
		return typeEditor;
	}

	/**
	 * @param typeEditor
	 *            the typeEditor to set
	 */
	public void setTypeEditor(TypeEditor typeEditor) {
		this.typeEditor = typeEditor;
	}

}
