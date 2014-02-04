package surrey.ramf.messaging.proxy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class CollectionAwareEditor implements TypeEditor {

	@Override
	public Object convert(Object value, Class<?> requiredType) {
		if (value != null) {
			if (value.getClass().isArray()) {
				if (Collection.class.isAssignableFrom(requiredType)) {
					Collection<Object> col = null;
					if (List.class.isAssignableFrom(requiredType)) {
						col = new ArrayList<Object>();
					} else if (Set.class.isAssignableFrom(requiredType)) {
						col = new HashSet<Object>();
					}
					if (col != null) {
						for (int i = 0; i < Array.getLength(value); i++) {
							col.add(Array.get(value, i));
						}
					}
					value = col;
				}
			}
		}
		return value;
	}

}
