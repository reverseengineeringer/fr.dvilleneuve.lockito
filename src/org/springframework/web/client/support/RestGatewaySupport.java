package org.springframework.web.client.support;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

public class RestGatewaySupport
{
  private RestTemplate restTemplate;
  
  public RestGatewaySupport()
  {
    restTemplate = new RestTemplate();
  }
  
  public RestGatewaySupport(ClientHttpRequestFactory paramClientHttpRequestFactory)
  {
    Assert.notNull(paramClientHttpRequestFactory, "'requestFactory' must not be null");
    restTemplate = new RestTemplate(paramClientHttpRequestFactory);
  }
  
  public RestTemplate getRestTemplate()
  {
    return restTemplate;
  }
  
  public void setRestTemplate(RestTemplate paramRestTemplate)
  {
    Assert.notNull(paramRestTemplate, "'restTemplate' must not be null");
    restTemplate = paramRestTemplate;
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.support.RestGatewaySupport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */