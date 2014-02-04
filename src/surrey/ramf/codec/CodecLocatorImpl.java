/**
 * 
 */
package surrey.ramf.codec;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import surrey.ramf.codec.amf.AMFMessageDeserializer;
import surrey.ramf.codec.amf.AMFMessageSerializer;
import surrey.ramf.codec.amf.DeserializeWorker;
import surrey.ramf.codec.amf.SerializeWorker;
import surrey.ramf.logging.LogWriter;
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
public class CodecLocatorImpl implements CodecLocator {

	private Map<Integer, Class<SerializeWorker>> serializeWorkers;
	private Map<Integer, Class<DeserializeWorker>> deserializeWorkers;
	private MessagingProxyRegistry proxyRegistry;
	private MessagingAliasRegistry aliasRegistry;

	/**
	 * @see surrey.ramf.codec.CodecLocator#locateDeserializer(java.io.InputStream)
	 */
	@Override
	public MessageDeserializer locateDeserializer(InputStream in) {
		MessageDeserializer deserializer = null;
		try {
			DataInputStream di = new DataInputStream(new BufferedInputStream(in));
			di.mark(3);
			int version = di.readUnsignedShort();
			di.reset();
			Class<DeserializeWorker> workerClass = deserializeWorkers.get(version);
			DeserializeWorker worker = workerClass.newInstance();
			worker.setInputStream(di);
			worker.setAliasRegistry(aliasRegistry);
			worker.setProxyRegistry(proxyRegistry);

			deserializer = new AMFMessageDeserializer(worker);
		} catch (Exception e) {
			LogWriter.error(getClass(), "Failed to create deserializer: " + e, e);
		}

		return deserializer;
	}

	/**
	 * @see surrey.ramf.codec.CodecLocator#locateSerializer(java.io.OutputStream,
	 *      surrey.ramf.messaging.amf.AMFMessage)
	 */
	@Override
	public MessageSerializer locateSerializer(OutputStream out, AMFMessage outMessage) {
		MessageSerializer serializer = null;
		try {
			Class<SerializeWorker> workerClass = serializeWorkers.get(outMessage.getVersion());
			SerializeWorker worker = workerClass.newInstance();
			worker.setOutputStream(out);
			worker.setAliasRegistry(aliasRegistry);
			worker.setProxyRegistry(proxyRegistry);
			serializer = new AMFMessageSerializer(worker);
		} catch (Exception e) {
			LogWriter.error(getClass(), "Failed to create serializer: " + e, e);
		}

		return serializer;
	}

	/**
	 * @return the serializeWorkers
	 */
	public Map<Integer, Class<SerializeWorker>> getSerializeWorkers() {
		return serializeWorkers;
	}

	/**
	 * @param serializeWorkers
	 *            the serializeWorkers to set
	 */
	public void setSerializeWorkers(Map<Integer, Class<SerializeWorker>> serializeWorkers) {
		this.serializeWorkers = serializeWorkers;
	}

	/**
	 * @return the deserializeWorkers
	 */
	public Map<Integer, Class<DeserializeWorker>> getDeserializeWorkers() {
		return deserializeWorkers;
	}

	/**
	 * @param deserializeWorkers
	 *            the deserializeWorkers to set
	 */
	public void setDeserializeWorkers(Map<Integer, Class<DeserializeWorker>> deserializeWorkers) {
		this.deserializeWorkers = deserializeWorkers;
	}

	/**
	 * @return the proxyRegistry
	 */
	public MessagingProxyRegistry getProxyRegistry() {
		return proxyRegistry;
	}

	/**
	 * @param proxyRegistry
	 *            the proxyRegistry to set
	 */
	public void setProxyRegistry(MessagingProxyRegistry proxyRegistry) {
		this.proxyRegistry = proxyRegistry;
	}

	/**
	 * @return the aliasRegistry
	 */
	public MessagingAliasRegistry getAliasRegistry() {
		return aliasRegistry;
	}

	/**
	 * @param aliasRegistry
	 *            the aliasRegistry to set
	 */
	public void setAliasRegistry(MessagingAliasRegistry aliasRegistry) {
		this.aliasRegistry = aliasRegistry;
	}

}
