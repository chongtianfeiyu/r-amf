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

 * The base abstract implementation of the AMFFilter. All AMFFilters should
 * probably extend this one.
 * 
 * @author Surrey
 * 
 */
public abstract class BaseAMFFilter implements AMFFilter {

	private AMFFilter next;

	protected void nextFilter(AMFServiceContext context) {
		if (next != null) {
			next.invoke(context);
		}
	}

	@Override
	public void setNext(AMFFilter next) {
		this.next = next;
	}

	@Override
	public AMFFilter getNext() {
		return next;
	}
}
