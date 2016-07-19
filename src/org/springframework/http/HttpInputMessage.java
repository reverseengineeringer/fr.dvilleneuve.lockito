package org.springframework.http;

import java.io.IOException;
import java.io.InputStream;

public abstract interface HttpInputMessage
  extends HttpMessage
{
  public abstract InputStream getBody()
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.HttpInputMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */