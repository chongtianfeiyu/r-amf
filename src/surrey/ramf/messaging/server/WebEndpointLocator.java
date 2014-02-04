/**
 * 
 */
package surrey.ramf.messaging.server;

import java.util.Map;
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
 * A basic EndpointLocator that assumes it is operating in a web container and
 * the given context strings are the servlet context, path and info.
 * 
 * @author Surrey
 * 
 */
public class WebEndpointLocator implements EndpointLocator {

	private Map<String, Endpoint> endpointMap;

	/**
	 * @see surrey.ramf.messaging.server.EndpointLocator#getEndpoint(surrey.ramf.messaging.server.RAMFServer,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Endpoint getEndpoint(RAMFServer server, String applicationContext, String endpointContext,
			String endpointInfo) {
		String key = endpointContext + (endpointInfo != null ? endpointInfo : "");
		Endpoint endpoint = endpointMap.get(key);

		return endpoint;
	}

	/**
	 * @return the endpointMap
	 */
	public Map<String, Endpoint> getEndpointMap() {
		return endpointMap;
	}

	/**
	 * @param endpointMap
	 *            the endpointMap to set
	 */
	public void setEndpointMap(Map<String, Endpoint> endpointMap) {
		this.endpointMap = endpointMap;
	}

}
