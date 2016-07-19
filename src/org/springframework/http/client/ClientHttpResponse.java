package org.springframework.http.client;

import java.io.IOException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;

public abstract interface ClientHttpResponse
  extends HttpInputMessage
{
  public abstract void close();
  
  public abstract int getRawStatusCode()
    throws IOException;
  
  public abstract HttpStatus getStatusCode()
    throws IOException;
  
  public abstract String getStatusText()
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.client.ClientHttpResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */