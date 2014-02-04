/**
 * 
 */
package surrey.ramf.messaging.server;

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
 * A wrapper around a set of service calls identified by a name.
 * 
 * @author Surrey
 * 
 */
public interface Destination {

	/**
	 * @return the name
	 */
	String getName();

	/**
	 * @param name
	 *            the name to set.
	 */
	void setName(String name);

	/**
	 * Gets the service call from this destination with the given name and set
	 * of parameters.
	 * 
	 * @param operation
	 *            the operation name to distinguish the call.
	 * @param params
	 *            the parameters to be understood by the Service.
	 * @return a constructed and ready to invoke service.
	 */
	Service getService(String operation, Object[] params);

}
