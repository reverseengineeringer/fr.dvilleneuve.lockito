package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.OptionsMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.TraceMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

@Deprecated
public class CommonsClientHttpRequestFactory
  implements ClientHttpRequestFactory, DisposableBean
{
  private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 60000;
  private HttpClient httpClient;
  
  public CommonsClientHttpRequestFactory()
  {
    httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
    setReadTimeout(60000);
  }
  
  public CommonsClientHttpRequestFactory(HttpClient paramHttpClient)
  {
    Assert.notNull(paramHttpClient, "httpClient must not be null");
    httpClient = paramHttpClient;
  }
  
  protected HttpMethodBase createCommonsHttpMethod(HttpMethod paramHttpMethod, String paramString)
  {
    switch (paramHttpMethod)
    {
    default: 
      throw new IllegalArgumentException("Invalid HTTP method: " + paramHttpMethod);
    case ???: 
      return new GetMethod(paramString);
    case ???: 
      return new DeleteMethod(paramString);
    case ???: 
      return new HeadMethod(paramString);
    case ???: 
      return new OptionsMethod(paramString);
    case ???: 
      return new PostMethod(paramString);
    case ???: 
      return new PutMethod(paramString);
    }
    return new TraceMethod(paramString);
  }
  
  public ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod)
    throws IOException
  {
    paramURI = createCommonsHttpMethod(paramHttpMethod, paramURI.toString());
    postProcessCommonsHttpMethod(paramURI);
    return new CommonsClientHttpRequest(getHttpClient(), paramURI);
  }
  
  public void destroy()
  {
    HttpConnectionManager localHttpConnectionManager = getHttpClient().getHttpConnectionManager();
    if ((localHttpConnectionManager instanceof MultiThreadedHttpConnectionManager)) {
      ((MultiThreadedHttpConnectionManager)localHttpConnectionManager).shutdown();
    }
  }
  
  public HttpClient getHttpClient()
  {
    return httpClient;
  }
  
  protected void postProcessCommonsHttpMethod(HttpMethodBase paramHttpMethodBase) {}
  
  public void setConnectTimeout(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Assert.isTrue(bool, "Timeout must be a non-negative value");
      httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(paramInt);
      return;
    }
  }
  
  public void setHttpClient(HttpClient paramHttpClient)
  {
    httpClient = paramHttpClient;
  }
  
  public void setReadTimeout(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Assert.isTrue(bool, "Timeout must be a non-negative value");
      getHttpClient().getHttpConnectionManager().getParams().setSoTimeout(paramInt);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.CommonsClientHttpRequestFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */