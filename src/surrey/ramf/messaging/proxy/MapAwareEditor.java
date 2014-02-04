package surrey.ramf.messaging.proxy;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
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

public class MapAwareEditor implements TypeEditor {

	@Override
	public Object convert(Object value, Class<?> requiredType) {
		if (value != null && !Map.class.isAssignableFrom(value.getClass()) && Map.class.isAssignableFrom(requiredType)) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			if (value.getClass().isArray()) {
				int length = Array.getLength(value);
				for (int i = 0; i < length; i++) {
					map.put(i, Array.get(value, i));
				}
			}
			value = map;
		}
		return value;
	}

}
