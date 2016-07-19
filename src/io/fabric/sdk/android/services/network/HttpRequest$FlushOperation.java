package io.fabric.sdk.android.services.network;

import java.io.Flushable;
import java.io.IOException;

public abstract class HttpRequest$FlushOperation<V>
  extends HttpRequest.Operation<V>
{
  private final Flushable flushable;
  
  protected HttpRequest$FlushOperation(Flushable paramFlushable)
  {
    flushable = paramFlushable;
  }
  
  protected void done()
    throws IOException
  {
    flushable.flush();
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.FlushOperation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */