package surrey.ramf.messaging.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
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
 * @author Surrey
 * 
 */
public class SEncodeFilter implements Filter {

	@Override
	public void destroy() {
		// filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		String headerString = ((HttpServletRequest) request).getHeader("s-enc");
		boolean isSEnc = (headerString != null && headerString.equals("true"));

		SurreyEncodeRequestWrapper wrapper = new SurreyEncodeRequestWrapper((HttpServletRequest) request, isSEnc);
		SurreyEncodeResponseWrapper responseWrapper = new SurreyEncodeResponseWrapper((HttpServletResponse) response,
				isSEnc);
		chain.doFilter(wrapper, responseWrapper);
	}

	public class SurreyEncodeResponseWrapper extends HttpServletResponseWrapper {

		private boolean isSEnc = false;
		private ByteArrayOutputStream out;

		public SurreyEncodeResponseWrapper(HttpServletResponse response) {
			super(response);
		}

		public SurreyEncodeResponseWrapper(HttpServletResponse response, boolean isSEnc) {
			super(response);
			this.isSEnc = isSEnc;
		}

		/**
		 * @see javax.servlet.ServletResponseWrapper#getOutputStream()
		 */
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (!isSEnc) {
				return super.getOutputStream();
			}
			out = new ByteArrayOutputStream();
			return new ServletOutputStream() {

				@Override
				public void write(int b) throws IOException {
					out.write(b);
				}
			};
		}

		/**
		 * @see javax.servlet.ServletResponseWrapper#getWriter()
		 */
		@Override
		public PrintWriter getWriter() throws IOException {
			if (!isSEnc) {
				return super.getWriter();
			}
			return new PrintWriter(out);
		}

		/**
		 * @see javax.servlet.ServletResponseWrapper#flushBuffer()
		 */
		@Override
		public void flushBuffer() throws IOException {
			if (isSEnc) {
				OutputStream output = super.getOutputStream();
				byte[] unenc = out.toByteArray();
				int count = 0;
				for (int i = 0; i < unenc.length; i++) {
					if (unenc[i] < 0 || unenc[i] == 33) {
						output.write((byte) 33);
						count++;
						if (unenc[i] == 33) {
							output.write((byte) 33);
							count++;
						} else if (unenc[i] == -33) {
							output.write((byte) 0);
							count++;
						} else {
							output.write(Math.abs(unenc[i]));
							count++;
						}
					} else {
						output.write(unenc[i]);
						count++;
					}
				}
				this.setContentLength(count);
			}
			super.flushBuffer();
		}

	}

	public class SurreyEncodeRequestWrapper extends HttpServletRequestWrapper {

		private boolean isSEnc = false;
		private ByteArrayOutputStream out;
		private ByteArrayInputStream inBytes;
		private byte[] bytes = null;

		public SurreyEncodeRequestWrapper(HttpServletRequest request) {
			super(request);
			isSEnc = false;
		}

		public SurreyEncodeRequestWrapper(HttpServletRequest request, boolean isSEnc) {
			super(request);
			this.isSEnc = isSEnc;
			if (isSEnc) {
				try {
					DataInputStream input = new DataInputStream(request.getInputStream());

					out = new ByteArrayOutputStream(request.getContentLength());
					int read = 0;
					while ((read = input.read()) > -1) {
						out.write(read);
					}
					String inStr = new String(out.toByteArray(), "UTF-8");
					bytes = new byte[inStr.length()];
					for (int i = 0; i < inStr.length(); i++) {
						bytes[i] = (byte) (inStr.charAt(i) > 127 ? -(inStr.charAt(i) - 127) : inStr.charAt(i));
					}
					inBytes = new ByteArrayInputStream(bytes);
					input.close();

				} catch (IOException e) {
					isSEnc = false; // let the server deal with bad requests
				}
			}

		}

		/**
		 * @see javax.servlet.ServletRequestWrapper#getInputStream()
		 */
		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (isSEnc) {
				return new ServletInputStream() {
					@Override
					public int read() throws IOException {
						return inBytes.read();
					}
				};
			}
			return super.getInputStream();
		}

		/**
		 * 
		 * @see javax.servlet.ServletRequestWrapper#getContentLength()
		 */
		@Override
		public int getContentLength() {
			if (!isSEnc) {
				return super.getContentLength();
			}
			return bytes.length;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
