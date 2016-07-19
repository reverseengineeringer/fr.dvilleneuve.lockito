package io.fabric.sdk.android.services.network;

import java.io.Flushable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

class HttpRequest$10
  extends HttpRequest.FlushOperation<HttpRequest>
{
  HttpRequest$10(HttpRequest paramHttpRequest, Flushable paramFlushable, Reader paramReader, Writer paramWriter)
  {
    super(paramFlushable);
  }
  
  protected HttpRequest run()
    throws IOException
  {
    return this$0.copy(val$input, val$writer);
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */