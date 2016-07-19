package io.fabric.sdk.android.services.network;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

class HttpRequest$5
  extends HttpRequest.CloseOperation<HttpRequest>
{
  HttpRequest$5(HttpRequest paramHttpRequest, Closeable paramCloseable, boolean paramBoolean, OutputStream paramOutputStream)
  {
    super(paramCloseable, paramBoolean);
  }
  
  protected HttpRequest run()
    throws HttpRequest.HttpRequestException, IOException
  {
    return this$0.receive(val$output);
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */