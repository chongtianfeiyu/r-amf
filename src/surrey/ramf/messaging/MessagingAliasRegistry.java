package surrey.ramf.messaging;

import java.util.HashMap;
import java.util.Map;

import surrey.ramf.messaging.amf.AcknowledgeMessage;
import surrey.ramf.messaging.amf.RemotingMessage;

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
 * A Registry for class alias names. This is referenced when reading messages
 * from a client so we can map client classes to server classes.
 * 
 * @author Surrey
 * 
 */
public class MessagingAliasRegistry {

	protected Map<String, Class<?>> aliasRegistry = new HashMap<String, Class<?>>();

	public MessagingAliasRegistry() {
		aliasRegistry.put("flex.messaging.messages.RemotingMessage",
				RemotingMessage.class);
		aliasRegistry.put("DSK", AcknowledgeMessage.class);
	}

	public Class<?> getAlias(String toAlias) throws ClassNotFoundException {
		if (aliasRegistry.containsKey(toAlias)) {
			return aliasRegistry.get(toAlias);
		}
		Class<?> fromAlias = Class.forName(toAlias);
		aliasRegistry.put(toAlias, fromAlias);
		return fromAlias;
	}

	public void setRegistry(Map<String, Class<?>> registry) {
		if (registry != null) {
			for (String key : registry.keySet()) {
				aliasRegistry.put(key, registry.get(key));
			}
		}
	}
}
