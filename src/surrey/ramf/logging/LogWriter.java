package surrey.ramf.logging;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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

public class LogWriter {

	private static Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();

	public static void info(Class<?> loggerName, String message) {
		Logger logger = getLogger(loggerName);
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	public static void debug(Class<?> loggerName, String message) {
		Logger logger = getLogger(loggerName);
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	public static void debug(Class<?> loggerName, String message,
			Throwable thrown) {
		Logger logger = getLogger(loggerName);
		if (logger.isDebugEnabled()) {
			logger.debug(message, thrown);
		}
	}

	public static void debug(Class<?> loggerName, Throwable thrown) {
		Logger logger = getLogger(loggerName);
		if (logger.isDebugEnabled()) {
			logger.debug(thrown.getMessage(), thrown);
		}
	}

	public static void error(Class<?> loggerName, String message) {
		Logger logger = getLogger(loggerName);
		if (logger.isEnabledFor(Level.ERROR)) {
			logger.error(message);
		}
	}

	public static void error(Class<?> loggerName, String message,
			Throwable thrown) {
		Logger logger = getLogger(loggerName);
		if (logger.isEnabledFor(Level.ERROR)) {
			logger.error(message, thrown);
		}
	}

	public static void error(Class<?> loggerName, Throwable thrown) {
		Logger logger = getLogger(loggerName);
		if (logger.isEnabledFor(Level.ERROR)) {
			logger.error(thrown.getMessage(), thrown);
		}
	}

	private static Logger getLogger(Class<?> loggerName) {
		Logger logger = loggers.get(loggerName);
		if (logger == null) {
			logger = Logger.getLogger(loggerName);
			loggers.put(loggerName, logger);
		}
		return logger;
	}

	/**
	 * @param class1
	 * @param req
	 */
	public static void info(Class<?> loggerName, HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		sb.append("Request For: ");
		sb.append(req.getRequestURI());
		sb.append("\n");
		sb.append("Request Headers:\n");
		Enumeration<?> names = req.getHeaderNames();
		Enumeration<?> values = null;
		Object name = null;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			values = req.getHeaders(name.toString());
			while (values.hasMoreElements()) {
				sb.append(name);
				sb.append(" = ");
				sb.append(values.nextElement().toString());
				sb.append("\n");
			}
		}
		sb.append("\n");
		sb.append("Request Attributes:");
		names = req.getAttributeNames();
		while (names.hasMoreElements()) {
			name = names.nextElement();
			sb.append(name);
			sb.append(" = ");
			sb.append(req.getAttribute(name.toString()).toString());
			sb.append("\n");
		}
		sb.append("\n");
		sb.append("Request Params:\n");
		names = req.getParameterNames();
		while (names.hasMoreElements()) {
			name = names.nextElement();
			sb.append(name);
			sb.append(" = ");
			sb.append(req.getParameter(name.toString()).toString());
			sb.append("\n");
		}
		info(loggerName, sb.toString());
	}

}
