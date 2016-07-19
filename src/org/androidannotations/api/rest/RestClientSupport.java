package org.androidannotations.api.rest;

import org.springframework.web.client.RestTemplate;

public abstract interface RestClientSupport
{
  public abstract RestTemplate getRestTemplate();
  
  public abstract void setRestTemplate(RestTemplate paramRestTemplate);
}

/* Location:
 * Qualified Name:     org.androidannotations.api.rest.RestClientSupport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */