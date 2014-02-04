package surrey.ramf.codec.amf;

import java.io.IOException;

import surrey.ramf.codec.MessageDeserializer;
import surrey.ramf.exception.MessageIncompatibleException;
import surrey.ramf.messaging.amf.AMFMessage;
import surrey.ramf.messaging.amf.AMFMessageBody;
import surrey.ramf.messaging.amf.AMFMessageHeader;
import surrey.ramf.messaging.amf.AMFTypes;

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
public class AMFMessageDeserializer implements MessageDeserializer, AMFTypes {

	private DeserializeWorker worker;

	public AMFMessageDeserializer(DeserializeWorker worker) {
		this.worker = worker;
	}

	@Override
	public Object readMessage() throws ClassNotFoundException, IOException, MessageIncompatibleException {
		// Read packet header
		AMFMessage message = new AMFMessage();
		int version = worker.readUnsignedShort();
		message.setVersion(version);

		if (worker.worksWith(message)) {
			// Read headers
			int headerCount = worker.readUnsignedShort();
			for (int i = 0; i < headerCount; i++) {
				message.getHeaders().add(readHeader());
			}

			// Read bodies
			int bodyCount = worker.readUnsignedShort();
			for (int i = 0; i < bodyCount; i++) {
				message.getBodies().add(readBody());
			}
		} else {
			throw new MessageIncompatibleException("Worker: " + worker.getClass().getCanonicalName()
					+ " can't work with: " + message);
		}
		return message;
	}

	public AMFMessageHeader readHeader() throws ClassNotFoundException, IOException {
		AMFMessageHeader header = new AMFMessageHeader();
		String name = worker.readUTF();
		header.setName(name);
		boolean mustUnderstand = worker.readBoolean();
		header.setMustUnderstand(mustUnderstand);

		worker.readMessageLength();
		worker.reset();
		Object data = readObject();

		header.setData(data);
		return header;
	}

	public AMFMessageBody readBody() throws ClassNotFoundException, IOException {
		AMFMessageBody body = new AMFMessageBody();
		String targetURI = worker.readUTF();
		body.setTargetURI(targetURI);
		String responseURI = worker.readUTF();
		body.setResponseURI(responseURI);

		worker.readMessageLength();

		worker.reset();
		Object data = readObject();

		body.setData(data); //in amf3 data is RemotingMessage[1]
		return body;
	}

	public Object readObject() throws ClassNotFoundException, IOException {
		return worker.readObject();
	}
}
