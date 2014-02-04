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
 * A standard TypeEditor that converts Doubles to other number types and
 * converts NaN to null.
 * 
 * @author Surrey
 * 
 */
public class NumberAwareEditor implements TypeEditor {

	@Override
	public Object convert(Object value, Class<?> requiredType) {
		if (value == null || value.equals(Double.NaN)) { // shortcut
			return null;
		}
		Object result = value;
		if (value instanceof Number) {
			if (requiredType.isPrimitive()) {
				if (int.class.isAssignableFrom(requiredType)) {
					result = ((Number) value).intValue();
				} else if (long.class.isAssignableFrom(requiredType)) {
					result = ((Number) value).longValue();
				} else if (double.class.isAssignableFrom(requiredType)) {
					result = ((Number) value).doubleValue();
				} else if (short.class.isAssignableFrom(requiredType)) {
					result = ((Number) value).shortValue();
				} else if (char.class.isAssignableFrom(requiredType)) {
					result = ((Character) value).charValue();
				} else if (float.class.isAssignableFrom(requiredType)) {
					result = ((Float) value).floatValue();
				}
			} else if (Number.class.isAssignableFrom(requiredType)) {
				if (Integer.class.isAssignableFrom(requiredType)) {
					result = new Integer(((Number) value).intValue());
				} else if (Long.class.isAssignableFrom(requiredType)) {
					result = new Long(((Number) value).longValue());
				} else if (Double.class.isAssignableFrom(requiredType)) {
					result = new Double(((Number) value).doubleValue());
				} else if (Short.class.isAssignableFrom(requiredType)) {
					result = new Short(((Number) value).shortValue());
				} else if (Float.class.isAssignableFrom(requiredType)) {
					result = new Float(((Number) value).floatValue());
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		NumberAwareEditor e = new NumberAwareEditor();
		System.out.println(e.convert(new Double(4), long.class));
	}
}
