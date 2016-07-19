package org.springframework.http;

import java.io.IOException;
import java.io.OutputStream;

public abstract interface HttpOutputMessage
  extends HttpMessage
{
  public abstract OutputStream getBody()
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.HttpOutputMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */