package surrey.ramf.codec.amf;

import java.io.IOException;
import java.io.OutputStream;

import surrey.ramf.codec.MessageSerializer;
import surrey.ramf.exception.MessageIncompatibleException;
import surrey.ramf.exception.MessagingException;
import surrey.ramf.logging.LogWriter;
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
public class AMFMessageSerializer implements MessageSerializer, AMFTypes {

	private SerializeWorker worker;

	public AMFMessageSerializer(SerializeWorker worker) {
		this.worker = worker;
	}

	@Override
	public void writeMessage(AMFMessage message) throws MessagingException, IOException {
		if (worker.worksWith(message)) {
			try {
				worker.writeShort(message.getVersion());
				worker.writeShort(message.getHeaders().size());
				for (AMFMessageHeader header : message.getHeaders()) {
					writeHeader(header);
				}
				worker.writeShort(message.getBodies().size());
				for (AMFMessageBody body : message.getBodies()) {
					writeBody(body);
				}
			} catch (IOException e) {
				LogWriter.error(getClass(), "Failed to serialize message: " + message, e);
				throw e;
			}
		} else {
			throw new MessageIncompatibleException("Worker: " + worker.getClass().getCanonicalName()
					+ " can't work with: " + message);
		}
	}

	protected void writeBody(AMFMessageBody body) throws IOException {
		if (body.getTargetURI() == null) {
			worker.writeUTF(NULL_STRING);
		} else {
			worker.writeUTF(body.getTargetURI());
		}

		if (body.getResponseURI() == null) {
			worker.writeUTF(NULL_STRING);
		} else {
			worker.writeUTF(body.getResponseURI());
		}

		worker.writeMessageLength(UNKNOWN_CONTENT_LENGTH);
		worker.reset();

		writeObject(body.getData());
	}

	protected void writeObject(Object data) throws IOException {
		worker.writeObject(data);
	}

	protected void writeHeader(AMFMessageHeader header) throws IOException {
		worker.writeUTF(header.getName());
		worker.writeBoolean(header.getMustUnderstand());
		worker.writeMessageLength(UNKNOWN_CONTENT_LENGTH);
		worker.reset();
		writeObject(header.getData());
	}

	public OutputStream getOutputStream() {
		return worker.getOutputStream();
	}
}
