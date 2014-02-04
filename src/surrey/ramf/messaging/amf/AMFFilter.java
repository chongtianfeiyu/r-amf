package surrey.ramf.messaging.amf;

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
 * 
 * A filter that forms a part of a chain through the AMFEndpoint lifecycle.
 * 
 * @author Surrey
 * 
 */
public interface AMFFilter {

	/**
	 * Does work on the given visitor (AMFServiceContext) This method should
	 * invoke the next filter in the chain
	 * 
	 * @param context
	 *            the visitor with the input and output stream.
	 */
	void invoke(AMFServiceContext context);

	/**
	 * @param next
	 *            the next filter in the chain
	 */
	void setNext(AMFFilter next);

	/**
	 * @return the next filter in the chain or null.
	 */
	AMFFilter getNext();

}
