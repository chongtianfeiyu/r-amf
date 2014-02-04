package surrey.ramf.messaging.server;

import java.io.InputStream;
import java.io.OutputStream;

import surrey.ramf.codec.MessageDeserializer;
import surrey.ramf.codec.MessageSerializer;
import surrey.ramf.messaging.amf.AMFMessage;
import surrey.ramf.messaging.amf.AMFMessageBody;

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
 * A visitor that keeps track of a service request process through an
 * AMFEndpoint and filterchain.
 * 
 * @author Surrey
 * 
 */
public class AMFServiceContext {

	private InputStream in;
	private OutputStream originalOut;
	private RAMFServer server;
	private AMFMessage inMessage;
	private AMFMessage outMessage;
	private int bodyInProcess = 0;
	private Endpoint endpoint;

	public AMFServiceContext(Endpoint endpoint, RAMFServer server,
			InputStream in, OutputStream out) {
		this.server = server;
		this.endpoint = endpoint;
		this.in = in;
		this.originalOut = out;
	}

	/**
	 * @return a MessageDeserializer
	 */
	public MessageDeserializer locateDeserializer() {
		MessageDeserializer deserializer = server.locateDeserializer(in);
		return deserializer;
	}

	/**
	 * @param message
	 *            the inbound request.
	 */
	public void setRequestMessage(AMFMessage message) {
		inMessage = message;
	}

	public AMFMessage getResponseMessage() {
		return outMessage;
	}

	/**
	 * @return a MessageSerializer.
	 */
	public MessageSerializer locateSerializer() {
		return server.locateSerializer(originalOut, outMessage);
	}

	/**
	 * @return the request message
	 */
	public AMFMessage getRequestMessage() {
		return inMessage;
	}

	/**
	 * Sets the AMFMessageBody currently being processed by the server
	 * 
	 * @param body
	 */
	public void setBodyInProcess(int index) {
		this.bodyInProcess = index;
	}

	/**
	 * @return the message body currently being processed.
	 */
	public AMFMessageBody getRequestBody() {
		return inMessage.getBodies().get(bodyInProcess);
	}

	public void setResponseMessage(AMFMessage responseMessage) {
		this.outMessage = responseMessage;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	/**
	 * @param responseBody
	 *            the body to set in the out message.
	 */
	public void setResponseBody(AMFMessageBody responseBody) {
		if (outMessage == null) {
			outMessage = new AMFMessage();
			outMessage.setVersion(inMessage.getVersion());
		}
		outMessage.getBodies().add(bodyInProcess, responseBody);
	}

	/**
	 * @return the version number for error messages
	 */
	public int getErrorVersion() {
		return server.getErrorVersion();
	}

}
