package io.fabric.sdk.android.services.network;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.CharBuffer;

class HttpRequest$6
  extends HttpRequest.CloseOperation<HttpRequest>
{
  HttpRequest$6(HttpRequest paramHttpRequest, Closeable paramCloseable, boolean paramBoolean, BufferedReader paramBufferedReader, Appendable paramAppendable)
  {
    super(paramCloseable, paramBoolean);
  }
  
  public HttpRequest run()
    throws IOException
  {
    CharBuffer localCharBuffer = CharBuffer.allocate(HttpRequest.access$100(this$0));
    for (;;)
    {
      int i = val$reader.read(localCharBuffer);
      if (i == -1) {
        break;
      }
      localCharBuffer.rewind();
      val$appendable.append(localCharBuffer, 0, i);
      localCharBuffer.rewind();
    }
    return this$0;
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */