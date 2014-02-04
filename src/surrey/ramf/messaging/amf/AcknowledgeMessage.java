package surrey.ramf.messaging.amf;

import java.util.Map;

import surrey.ramf.messaging.Aliased;
import surrey.ramf.messaging.amfr.RemoteResponseMessage;

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

 * The response from an AMFEndpoint routed remote call.
 * 
 * @author Surrey
 * 
 */
public class AcknowledgeMessage extends RemoteResponseMessage implements Aliased {

	private static final long serialVersionUID = -3060421139350506857L;

	protected long timestamp;
	protected Map<Object, Object> headers;
	protected String correlationId;
	protected String messageId;
	protected long timeToLive;
	protected Object clientId;
	protected String destination;

	@Override
	public String getAlias() {
		return "flex.messaging.messages.AcknowledgeMessage";
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the headers
	 */
	public Map<Object, Object> getHeaders() {
		return headers;
	}

	/**
	 * @param headers
	 *            the headers to set
	 */
	public void setHeaders(Map<Object, Object> headers) {
		this.headers = headers;
	}

	/**
	 * @return the correlationId
	 */
	public String getCorrelationId() {
		return correlationId;
	}

	/**
	 * @param correlationId the correlationId to set
	 */
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the timeToLive
	 */
	public long getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the clientId
	 */
	public Object getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Object clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

}
