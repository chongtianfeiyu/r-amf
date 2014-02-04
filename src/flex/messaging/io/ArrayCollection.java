package flex.messaging.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;

import surrey.ramf.codec.amf.AMF3DeserializeWorker;
import surrey.ramf.codec.amf.AMF3SerializeWorker;
import surrey.ramf.logging.LogWriter;

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
 * Used to map to client mx.collections.ArrayCollection to java.util.Lists in
 * Java.
 */
public class ArrayCollection extends ArrayList<Object> implements Externalizable {

	public ArrayCollection() {
		super();
	}

	public ArrayCollection(Collection<Object> c) {
		super(c);
	}

	public ArrayCollection(int initialCapacity) {
		super(initialCapacity);
	}

	public Object[] getSource() {
		return toArray();
	}

	public void setSource(Object[] s) {
		if (s != null) {
			clear();

			for (int i = 0; i < s.length; i++) {
				add(s[i]);
			}
		} else {
			clear();
		}
	}

	public void setSource(Collection<Object> s) {
		addAll(s);
	}

	@SuppressWarnings("unchecked")
	public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {

		Object s = ((AMF3DeserializeWorker)input).readAMF3Object();
		if (s instanceof Collection) {
			s = ((Collection<Object>) s).toArray();
		}
		Object[] source = (Object[]) s;
		setSource(source);
	}

	public void writeExternal(ObjectOutput output) throws IOException {
		if (output instanceof AMF3SerializeWorker) {
			try {
				((AMF3SerializeWorker) output).writeAMF3Object(getSource());
			} catch (ClassNotFoundException e) {
				LogWriter.error(getClass(), "Could not write external ArrayCollection: " + e, e);
				throw new IOException("Class not found", e);
			}
		} else {
			output.writeObject(getSource());
		}	}
}
