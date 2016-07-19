package org.springframework.web.client;

import java.io.IOException;

public class ResourceAccessException
  extends RestClientException
{
  private static final long serialVersionUID = 1L;
  
  public ResourceAccessException(String paramString)
  {
    super(paramString);
  }
  
  public ResourceAccessException(String paramString, IOException paramIOException)
  {
    super(paramString, paramIOException);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.ResourceAccessException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */