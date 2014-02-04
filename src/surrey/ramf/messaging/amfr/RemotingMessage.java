package surrey.ramf.messaging.amfr;

import java.util.Map;

import surrey.ramf.messaging.MessageBodyData;

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
 * A remoting message used by AMFR (AMF version 99) It drops legacy support.
 * 
 * @author Surrey
 * 
 */
public class RemotingMessage implements MessageBodyData {

	private static final long serialVersionUID = 4025292893851888403L;

	private String operation;
	private Object[] parameters;
	protected String destination;
	private Map<String, Object> headers;

	public RemoteResponseMessage createResponse() {
		return new RemoteResponseMessage();
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the parameters
	 */
	public Object[] getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the headers
	 */
	public Map<String, Object> getHeaders() {
		return headers;
	}

	/**
	 * @param headers
	 *            the headers to set
	 */
	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}
}
