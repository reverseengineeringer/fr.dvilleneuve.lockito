package io.fabric.sdk.android.services.network;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

class HttpRequest$7
  extends HttpRequest.CloseOperation<HttpRequest>
{
  HttpRequest$7(HttpRequest paramHttpRequest, Closeable paramCloseable, boolean paramBoolean, BufferedReader paramBufferedReader, Writer paramWriter)
  {
    super(paramCloseable, paramBoolean);
  }
  
  public HttpRequest run()
    throws IOException
  {
    return this$0.copy(val$reader, val$writer);
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */