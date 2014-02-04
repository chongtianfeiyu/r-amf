package surrey.ramf.messaging;

import java.util.HashMap;
import java.util.Map;

import surrey.ramf.messaging.proxy.MessagingProxy;
import surrey.ramf.messaging.proxy.RuntimeMessagingProxy;
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
 * 
 * A registry for messaging proxies, either runtime as created by the serializer
 * / deserializer or custom as provided.
 * 
 * @author Surrey
 * 
 */
public class MessagingProxyRegistry {

	private Map<String, MessagingProxy<?>> proxyMap = new HashMap<String, MessagingProxy<?>>();
	private TypeEditor typeEditor;

	/**
	 * Gets or creates a MessagingProxy for the given type. The registry is
	 * first searched based on a canonical name match then a simple one. If no
	 * match is found a new RuntimeMessagingProxy is created and added to the
	 * proxy under the canonical name.
	 * 
	 * @param type
	 *            the class to proxy.
	 * @return a MessagingProxy.
	 */
	@SuppressWarnings("unchecked")
	public <T> MessagingProxy<T> getProxy(T instance) {
		MessagingProxy<T> proxy = null;
		Class<T> type = (Class<T>) instance.getClass();
		if (proxyMap.containsKey(type.getCanonicalName())) {
			proxy = (MessagingProxy<T>) proxyMap.get(type.getCanonicalName());
		} else if (proxyMap.containsKey(type.getSimpleName())) {
			proxy = (MessagingProxy<T>) proxyMap.get(type.getSimpleName());
		}
		if (proxy == null) {
			proxy = new RuntimeMessagingProxy<T>(type);
			if (typeEditor != null) {
				proxy.setTypeEditor(typeEditor);
			}
			proxyMap.put(type.getCanonicalName(), proxy);
		}
		proxy = proxy.copy();
		proxy.setInstance(instance);
		return proxy;
	}

	@SuppressWarnings("unchecked")
	public <T> MessagingProxy<T> getProxy(Class<?> clazz)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Object instance = clazz.newInstance();
		return getProxy((T) instance);
	}

	public void setRegistry(Map<String, MessagingProxy<?>> registry) {
		if (registry != null) {
			for (String key : registry.keySet()) {
				proxyMap.put(key, registry.get(key));
			}
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
