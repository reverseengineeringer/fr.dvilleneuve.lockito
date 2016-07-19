package io.fabric.sdk.android.services.network;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

class HttpRequest$9
  extends HttpRequest.CloseOperation<HttpRequest>
{
  HttpRequest$9(HttpRequest paramHttpRequest, Closeable paramCloseable, boolean paramBoolean, Reader paramReader, Writer paramWriter)
  {
    super(paramCloseable, paramBoolean);
  }
  
  public HttpRequest run()
    throws IOException
  {
    char[] arrayOfChar = new char[HttpRequest.access$100(this$0)];
    for (;;)
    {
      int i = val$input.read(arrayOfChar);
      if (i == -1) {
        break;
      }
      val$output.write(arrayOfChar, 0, i);
    }
    return this$0;
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */