package surrey.ramf.messaging.proxy;

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
 * A TypeEditor is used by a MessagingProxy to manipulate individual field types
 * into the type required by the proxy. For example Javascript and Actionscript
 * only send doubles and not longs and they can't send null values for numbers.
 * 
 * @author Surrey
 * 
 */
public interface TypeEditor {

	/**
	 * Attempts to convert the given value into the provided type.
	 * 
	 * @param value
	 *            the value to convert
	 * @param requiredType
	 *            the type to attempt to convert it to.
	 * @return the original value or the converted one if possible.
	 */
	Object convert(Object value, Class<?> requiredType);

}
