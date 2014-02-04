package surrey.ramf.codec.amf;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;

import surrey.ramf.messaging.MessagingAliasRegistry;
import surrey.ramf.messaging.MessagingProxyRegistry;
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

public interface SerializeWorker extends ObjectOutput {

	/**
	 * @param message
	 *            the message to check.
	 * @return true if this worker will work with this message.
	 */
	boolean worksWith(AMFMessage message);

	/**
	 * @return the output stream used by this worker
	 */
	OutputStream getOutputStream();

	/**
	 * @param out
	 *            the OutputStream to set.
	 */
	void setOutputStream(OutputStream out);

	/**
	 * Resets the object reference count.
	 */
	void reset();

	/**
	 * @param aliasRegistry
	 *            the MessagingAliasRegistry to include for this worker.
	 */
	void setAliasRegistry(MessagingAliasRegistry aliasRegistry);

	/**
	 * @param proxyRegistry
	 *            the MessagingProxyRegistry for this worker.
	 */
	void setProxyRegistry(MessagingProxyRegistry proxyRegistry);

	/**
	 * AMF 0/3 write unknonwn length always. A complete waste of 4 bytes.
	 * disregard in AMFR.
	 * 
	 * @param unknownContentLength
	 * @throws IOException
	 */
	void writeMessageLength(int unknownContentLength) throws IOException;

}
