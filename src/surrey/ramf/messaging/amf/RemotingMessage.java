package surrey.ramf.messaging.amf;

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
 * A remote method invocation request. Usually the data in an AMFMessageBody
 * 
 * clientId, destination, messageId, timestamp, timeToLive, headers
 * 
 * @author Surrey
 * 
 */
public class RemotingMessage extends surrey.ramf.messaging.amfr.RemotingMessage implements Aliased {

	private static final long serialVersionUID = -4242347429758789638L;

	private String source;
	private String remoteUsername;
	private String remotePassword;
	protected Object clientId;
	protected String messageId;
	protected long timestamp;
	protected long timeToLive;

	@Override
	public RemoteResponseMessage createResponse() {
		return new AcknowledgeMessage();
	}

	/**
	 * Dummy for amf3 compatibility.
	 * 
	 * @return
	 */
	public Object getBody() {
		return getParameters();
	}

	/**
	 * Dummy for amf3 compatibility. Body == parameters for some reason
	 * 
	 * @param bodyValue
	 */
	public void setBody(Object bodyValue) {
		// do nothing
	}

	public String getSource() {
		return source;
	}

	public void setSource(String s) {
		source = s;
	}

	/**
	 * @return the remoteUsername
	 */
	public String getRemoteUsername() {
		return remoteUsername;
	}

	/**
	 * @param remoteUsername
	 *            the remoteUsername to set
	 */
	public void setRemoteUsername(String remoteUsername) {
		this.remoteUsername = remoteUsername;
	}

	/**
	 * @return the remotePassword
	 */
	public String getRemotePassword() {
		return remotePassword;
	}

	/**
	 * @param remotePassword
	 *            the remotePassword to set
	 */
	public void setRemotePassword(String remotePassword) {
		this.remotePassword = remotePassword;
	}

	/**
	 * @return the clientId
	 */
	public Object getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
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
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
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
	 * @return the timeToLive
	 */
	public long getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive
	 *            the timeToLive to set
	 */
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	@Override
	public String getAlias() {
		return "flex.messaging.messages.RemotingMessage";
	}
}
