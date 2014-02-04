package surrey.ramf.messaging.amf;

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
 * An object describing an error that occured during RAMF handling of a message.
 * This is not an error in the service code.
 * 
 * @author Surrey
 * 
 */
public class ErrorMessage implements MessageBodyData {

	private static final long serialVersionUID = 6180346423554155977L;

	private String message;
	private String cause;

	/**
	 * @param cause
	 *            a string describing the error.
	 * @param destination
	 *            the message destination when the error occured.
	 * @param operation
	 *            the operation attempted.
	 * @param params
	 *            the params passed.
	 */
	public ErrorMessage(String cause, String destination, String operation, Object[] params) {
		this.cause = cause;
		String paramString = createParamString(params);
		message = "Failed during call to: " + destination + "." + operation + paramString;
	}

	private String createParamString(Object[] params) {
		if (params == null) {
			return "(null)";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Object object : params) {
			if (object == null) {
				sb.append("null");
			} else {
				sb.append(object.getClass().getSimpleName());
			}
			sb.append(",");
		}
		if (sb.indexOf(",") > 0) {
			sb.delete(sb.length() - 1, sb.length());
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		return cause + "\n" + message;
	}
}
