/**
 * 
 */
package surrey.ramf.messaging.amf;

import java.lang.reflect.Array;

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
 * Each inbound message could have multiple message payloads (RemotingMessages).
 * This filter breaks each message out and passes it on for processing.
 * 
 * @author Surrey
 * 
 */
public class BatchAMFFilter extends BaseAMFFilter {

	/**
	 * @see surrey.ramf.messaging.amf.AMFFilter#invoke(surrey.ramf.messaging.server.AMFServiceContext)
	 */
	@Override
	public void invoke(AMFServiceContext context) {
		AMFMessage inMessage = context.getRequestMessage();
		if (inMessage.getBodies() != null) {
			int bodyIndex = 0;
			for (AMFMessageBody body : inMessage.getBodies()) {
				Object data = body.getData();
				// AMFMessageBody data for RPC is array of RemotingMessage when
				// coming from Flash / Flex or compliant client
				if (data.getClass().isArray()) {
					data = Array.get(data, 0);
					body.setData(data);
				}
				context.setBodyInProcess(bodyIndex);
				nextFilter(context);
				bodyIndex++;
			}
		}
	}

}
