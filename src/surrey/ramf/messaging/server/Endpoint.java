package surrey.ramf.messaging.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import surrey.ramf.exception.MessagingException;
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
 * An endpoint is a collection of services to expose to RAMF / AMF clients.
 * 
 * @author Surrey
 * 
 */
public interface Endpoint {

	/**
	 * Reads the messages from the input stream then writes to the output
	 * stream. This method operates synchronously. That is it blocks until the
	 * message is serviced and a response sent.
	 * 
	 * @param in
	 *            the input stream to read the message from.
	 * @param out
	 *            the output stream to write the answer (service response or
	 *            error message) to.
	 */
	void service(InputStream in, OutputStream out);

	/**
	 * Takes the given data and passes it to an appropriate service. In the case
	 * of AMF it takes a RemotingMessage and returns an AcknowlegeMessage
	 * 
	 * @param data
	 *            the data to route to the service.
	 * @return the service response.
	 * @throws MessagingException 
	 */
	MessageBodyData routeMessageToService(MessageBodyData data) throws MessagingException;

	/**
	 * @return the destination map
	 */
	Map<String, Destination> getDestinationMap();

	/**
	 * @param destinationMap
	 *            the mapping of destination names to Destination
	 */
	void setDestinationMap(Map<String, Destination> destinationMap);
}
