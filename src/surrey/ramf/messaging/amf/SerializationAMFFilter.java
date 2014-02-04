/**
 * 
 */
package surrey.ramf.messaging.amf;

import surrey.ramf.codec.MessageDeserializer;
import surrey.ramf.codec.MessageSerializer;
import surrey.ramf.logging.LogWriter;
import surrey.ramf.messaging.server.AMFServiceContext;

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
 * Deserializes the request and serializes the response.
 * 
 * @author Surrey
 * 
 */
public class SerializationAMFFilter extends BaseAMFFilter {

	/**
	 * 
	 * @see surrey.ramf.messaging.amf.AMFFilter#invoke(surrey.ramf.messaging.server.AMFServiceContext)
	 */
	@Override
	public void invoke(AMFServiceContext context) {
		MessageDeserializer deserializer = context.locateDeserializer();
		context.setResponseMessage(new AMFMessage());
		AMFMessage message = null;
		try {
			message = (AMFMessage) deserializer.readMessage();
			context.getResponseMessage().setVersion(message.getVersion());
		} catch (Exception e) {
			LogWriter.error(getClass(), "Failed to deserialize message: " + e, e);
			context.getResponseMessage().setVersion(context.getErrorVersion());
			AMFMessageBody errorBody = new AMFMessageBody();
			ErrorMessage errorMessage = new ErrorMessage("Failed to deserialize message: " + e, "", "", null);
			errorBody.setData(errorMessage);
			errorBody.setResponseURI(AMFMessageBody.STATUS_METHOD);
			errorBody.setTargetURI(errorBody.getResponseURI());
			context.setResponseBody(errorBody);
		}

		context.setRequestMessage(message);
		if (message != null) {
			nextFilter(context);
		}

		AMFMessage response = context.getResponseMessage();
		MessageSerializer serializer = context.locateSerializer();
		try {
			serializer.writeMessage(response);
		} catch (Exception e) {
			LogWriter.error(getClass(), "Failed to serialize response: " + e, e);

			AMFMessageBody errorBody = new AMFMessageBody();
			ErrorMessage errorMessage = new ErrorMessage("Failed to serialize message: " + e, "", "", null);
			errorBody.setData(errorMessage);
			context.setResponseBody(errorBody);
			response = new AMFMessage();
			response.getBodies().add(errorBody);
			errorBody.setResponseURI(AMFMessageBody.STATUS_METHOD);
			errorBody.setTargetURI(errorBody.getResponseURI());
			try {
				serializer.writeMessage(response);
			} catch (Exception e1) {
				LogWriter.error(getClass(), "Could not serialize error message: " + e1, e1);
				throw new RuntimeException("Could not serialize error message", e1);
			}
		}
	}

}
