/**
 * 
 */
package surrey.ramf.messaging.proxy;

import java.util.Collection;
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

/**
 * Delegates conversion to type specific editors.
 * 
 * @author Surrey
 * 
 */
public class DelegatingTypeEditor implements TypeEditor {

	private TypeEditor numberEditor = new NumberAwareEditor();
	private TypeEditor collectionEditor = new CollectionAwareEditor();
	private TypeEditor mapEditor = new MapAwareEditor();

	/**
	 * @see surrey.ramf.messaging.proxy.TypeEditor#convert(java.lang.Object,
	 *      java.lang.Class)
	 */
	@Override
	public Object convert(Object value, Class<?> requiredType) {
		if (value != null) {
			if (value instanceof Number) {
				return numberEditor.convert(value, requiredType);
			}
			if (Collection.class.isAssignableFrom(requiredType)) {
				return collectionEditor.convert(value, requiredType);
			}
			if (Map.class.isAssignableFrom(requiredType)){
				return mapEditor.convert(value, requiredType);
			}
		}
		return value;
	}

}
