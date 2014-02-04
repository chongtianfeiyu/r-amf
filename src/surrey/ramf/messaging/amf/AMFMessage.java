package surrey.ramf.messaging.amf;

import java.util.ArrayList;
import java.util.List;
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
 * 
 * An AMF message
 * 
 * @author Surrey
 * 
 */
public class AMFMessage {

	private int version;
	private List<AMFMessageHeader> headers;
	private List<AMFMessageBody> bodies;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<AMFMessageHeader> getHeaders() {
		if (headers == null) {
			headers = new ArrayList<AMFMessageHeader>();
		}
		return headers;
	}

	public void setHeaders(List<AMFMessageHeader> headers) {
		this.headers = headers;
	}

	public List<AMFMessageBody> getBodies() {
		if (bodies == null){
			bodies = new ArrayList<AMFMessageBody>();
		}
		return bodies;
	}

	public void setBodies(List<AMFMessageBody> bodies) {
		this.bodies = bodies;
	}

}
