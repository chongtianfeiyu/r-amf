package surrey.ramf.messaging.server;

import java.io.InputStream;
import java.io.OutputStream;

import surrey.ramf.codec.CodecLocator;
import surrey.ramf.codec.MessageDeserializer;
import surrey.ramf.codec.MessageSerializer;
import surrey.ramf.messaging.amf.AMFMessage;

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
 * Configured through bean getters and setters to allow maximum flexibility.
 * 
 * @author Surrey
 * 
 */
public class RAMFServer {

	private EndpointLocator endpointLocator;
	private CodecLocator codecLocator;
	private Integer errorVersion = 99; // default to AMFR

	/**
	 * Gets the appropriate end point. An endpoint is a collection of services
	 * that are available within a given context. The one server may service
	 * multiple applications and each application may have numerous endpoints.
	 * Each endpoint context may in turn be subdivided to discriminate.
	 * 
	 * For example in a web context the application context would be the web
	 * app. In this case there would usually only be one application, but the
	 * same RAMFServer may be accessible to multiple web apps. eg:
	 * applicationContext = /myWebApp endpointContext = /RAMF endpointInfo =
	 * /amf
	 * 
	 * @param applicationContext
	 *            the application to search. The one server may provide
	 *            endpoints for multiple applications.
	 * @param endpointContext
	 *            the context within the application to search.
	 * @param endpointInfo
	 *            Any additional qualifiers to the endpoint context.
	 * @return an Endpoint or null if none found.
	 */
	public Endpoint getEndpoint(String applicationContext, String endpointContext, String endpointInfo) {
		return endpointLocator.getEndpoint(this, applicationContext, endpointContext, endpointInfo);
	}

	/**
	 * @return the endpointLocator
	 */
	public EndpointLocator getEndpointLocator() {
		return endpointLocator;
	}

	/**
	 * @param endpointLocator
	 *            the endpointLocator to set
	 */
	public void setEndpointLocator(EndpointLocator endpointLocator) {
		this.endpointLocator = endpointLocator;
	}

	/**
	 * Delegates to a CodecLocator.
	 * 
	 * @param in
	 *            The input stream that will be deserialized. The locator may
	 *            use this to determine what sort of deserializer is needed.
	 * 
	 * @return a MessageDeserializer.
	 */
	public MessageDeserializer locateDeserializer(InputStream in) {
		return codecLocator.locateDeserializer(in);
	}

	/**
	 * @return the codecLocator
	 */
	public CodecLocator getCodecLocator() {
		return codecLocator;
	}

	/**
	 * @param codecLocator
	 *            the codecLocator to set
	 */
	public void setCodecLocator(CodecLocator codecLocator) {
		this.codecLocator = codecLocator;
	}

	public MessageSerializer locateSerializer(OutputStream out, AMFMessage outMessage) {
		return codecLocator.locateSerializer(out, outMessage);
	}

	/**
	 * @return the default version for error messages
	 */
	public Integer getErrorVersion() { 
		return errorVersion;
	}

	/**
	 * @param errorVersion the errorVersion to set
	 */
	public void setErrorVersion(Integer errorVersion) {
		this.errorVersion = errorVersion;
	}

}
