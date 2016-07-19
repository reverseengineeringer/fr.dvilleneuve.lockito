package org.springframework.http;

import java.net.URI;

public abstract interface HttpRequest
  extends HttpMessage
{
  public abstract HttpMethod getMethod();
  
  public abstract URI getURI();
}

/* Location:
 * Qualified Name:     org.springframework.http.HttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */