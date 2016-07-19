package org.androidannotations.api.rest;

import org.springframework.http.HttpAuthentication;

public abstract interface RestClientHeaders
{
  public abstract String getCookie(String paramString);
  
  public abstract String getHeader(String paramString);
  
  public abstract void setAuthentication(HttpAuthentication paramHttpAuthentication);
  
  public abstract void setBearerAuth(String paramString);
  
  public abstract void setCookie(String paramString1, String paramString2);
  
  public abstract void setHeader(String paramString1, String paramString2);
  
  public abstract void setHttpBasicAuth(String paramString1, String paramString2);
}

/* Location:
 * Qualified Name:     org.androidannotations.api.rest.RestClientHeaders
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */