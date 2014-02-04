package surrey.ramf.messaging.amf;
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
 * Created by Surrey Hughes of Reignite Pty Ltd <http://www.reignite.com.au>
 * @author Surrey
 *
 */
public class AMFMessageBody {

	public static final String RESULT_METHOD = "/onResult";
	public static final String STATUS_METHOD = "/onStatus";

	private String targetURI;
	private String responseURI;
	private Object data; //array of RemotingMessage

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the targetURI
	 */
	public String getTargetURI() {
		return targetURI;
	}

	/**
	 * @param targetURI
	 *            the targetURI to set
	 */
	public void setTargetURI(String targetURI) {
		this.targetURI = targetURI;
	}

	/**
	 * @return the reponseURI
	 */
	public String getResponseURI() {
		return responseURI;
	}

	/**
	 * @param reponseURI
	 *            the reponseURI to set
	 */
	public void setResponseURI(String responseURI) {
		this.responseURI = responseURI;
	}
}
