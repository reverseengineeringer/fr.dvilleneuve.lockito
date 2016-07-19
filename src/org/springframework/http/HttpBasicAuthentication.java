package org.springframework.http;

import org.springframework.util.Base64Utils;

public class HttpBasicAuthentication
  extends HttpAuthentication
{
  private final String password;
  private final String username;
  
  public HttpBasicAuthentication(String paramString1, String paramString2)
  {
    username = paramString1;
    password = paramString2;
  }
  
  public String getHeaderValue()
  {
    return String.format("Basic %s", new Object[] { Base64Utils.encodeToString(String.format("%s:%s", new Object[] { username, password }).getBytes()) });
  }
  
  public String toString()
  {
    try
    {
      String str = String.format("Authorization: %s", new Object[] { getHeaderValue() });
      return str;
    }
    catch (RuntimeException localRuntimeException) {}
    return null;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.HttpBasicAuthentication
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */