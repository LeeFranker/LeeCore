package http.apache;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

public class HttpApacheMultipartEntity extends MultipartEntity {
	private final IfaceHttpApacheTransferListener listener;

	public HttpApacheMultipartEntity(IfaceHttpApacheTransferListener listener) {
		super();
		this.listener = listener;
	}

	public HttpApacheMultipartEntity(HttpMultipartMode mode, IfaceHttpApacheTransferListener listener) {
		super(mode);
		this.listener = listener;
	}

	public HttpApacheMultipartEntity(HttpMultipartMode mode, String boundary, Charset charset, IfaceHttpApacheTransferListener listener) {
		super(mode, boundary, charset);
		this.listener = listener;
	}

	@Override
	public void writeTo(final OutputStream out) throws IOException {
		super.writeTo(new CountingOutputStream(out, listener));
	}

	/**
	 * 自定义的FilterOutputStream
	 */
	public static class CountingOutputStream extends FilterOutputStream {
		private final IfaceHttpApacheTransferListener listener;
		private long transferred;

		public CountingOutputStream(final OutputStream out, final IfaceHttpApacheTransferListener listener) {
			super(out);
			this.listener = listener;
			this.transferred = 0;
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			out.write(b, off, len);
			transferred += len;
			if (listener != null)
				listener.transferred(transferred);
		}

		@Override
		public void write(int b) throws IOException {
			out.write(b);
			transferred++;
			if (listener != null)
				listener.transferred(transferred);
		}
	}
}
