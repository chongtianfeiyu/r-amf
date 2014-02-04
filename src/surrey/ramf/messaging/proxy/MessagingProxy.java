package surrey.ramf.messaging.proxy;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

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
 * Either handcrafted and added to the registry or auto generated at runtime.
 * Used to serialize / deserialize objects.
 * 
 * @author Surrey
 * 
 */
public interface MessagingProxy<T> {

	/**
	 * @return a copy of this proxy so this proxy can be used like a prototype.
	 */
	MessagingProxy<T> copy();

	/**
	 * @param instance
	 *            the instance to proxy.
	 */
	void setInstance(T instance);

	/**
	 * @return true if this proxy proxies a class that is externalizable.
	 */
	boolean isExternalizable();

	/**
	 * @return a List of the names of the properties proxied
	 */
	List<String> getProperties();

	/**
	 * @param legacy
	 *            true if this proxy is to produce class names suitable for
	 *            consumption by flex compatible clients.
	 * @return the name of the class being proxied.
	 */
	String getProxyClassName(boolean legacy);

	/**
	 * To allow the proxy full control over serialisation.
	 * 
	 * @param worker
	 * @throws IOException
	 */
	void writeExternal(ObjectOutput worker) throws IOException;

	/**
	 * To allow the proxy full control over serialisation.
	 * 
	 * @param worker
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void readExternal(ObjectInput worker) throws IOException,
			ClassNotFoundException;

	/**
	 * Gets the value for the given property name.
	 * 
	 * @param propName
	 *            the property to get the value for.
	 * @return the value or null.
	 */
	Object getValue(String propName);

	/**
	 * Sets the given value on the property.
	 * 
	 * @param propertyName
	 *            the property to set.
	 * @param value
	 *            the value to set.
	 */
	void setValue(String propertyName, Object value);

	/**
	 * Returns the instance or creates a new instance if needed.
	 * 
	 * @return
	 */
	T getInstance();

	/**
	 * When deserializing, put the properties in order of reading into the
	 * properties list.
	 * 
	 * @param readString
	 */
	void addReadProperty(String readString);

	/**
	 * @return the list of properties in read order.
	 */
	List<String> getReadProperties();

	/**
	 * @param editor
	 *            The editor for the Proxy to use when reading values before
	 *            setting them.
	 */
	void setTypeEditor(TypeEditor editor);
}
