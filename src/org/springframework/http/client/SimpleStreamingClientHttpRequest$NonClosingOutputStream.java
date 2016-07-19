package org.springframework.http.client;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class SimpleStreamingClientHttpRequest$NonClosingOutputStream
  extends FilterOutputStream
{
  private SimpleStreamingClientHttpRequest$NonClosingOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
  }
  
  public void close()
    throws IOException
  {}
}

/* Location:
 * Qualified Name:     org.springframework.http.client.SimpleStreamingClientHttpRequest.NonClosingOutputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */