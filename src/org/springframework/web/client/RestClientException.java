package org.springframework.web.client;

import org.springframework.core.NestedRuntimeException;

public class RestClientException
  extends NestedRuntimeException
{
  private static final long serialVersionUID = 1L;
  
  public RestClientException(String paramString)
  {
    super(paramString);
  }
  
  public RestClientException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestClientException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */