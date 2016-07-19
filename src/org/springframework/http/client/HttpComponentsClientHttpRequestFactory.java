package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

public class HttpComponentsClientHttpRequestFactory
  implements ClientHttpRequestFactory, DisposableBean
{
  private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;
  private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
  private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 60000;
  private HttpClient httpClient;
  
  public HttpComponentsClientHttpRequestFactory()
  {
    Object localObject = new SchemeRegistry();
    ((SchemeRegistry)localObject).register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    ((SchemeRegistry)localObject).register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    localObject = new ThreadSafeClientConnManager(localBasicHttpParams, (SchemeRegistry)localObject);
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 100);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(5));
    httpClient = new DefaultHttpClient((ClientConnectionManager)localObject, null);
    setReadTimeout(60000);
  }
  
  public HttpComponentsClientHttpRequestFactory(HttpClient paramHttpClient)
  {
    Assert.notNull(paramHttpClient, "HttpClient must not be null");
    httpClient = paramHttpClient;
  }
  
  protected HttpContext createHttpContext(HttpMethod paramHttpMethod, URI paramURI)
  {
    return null;
  }
  
  protected HttpUriRequest createHttpRequest(HttpMethod paramHttpMethod, URI paramURI)
  {
    switch (paramHttpMethod)
    {
    default: 
      throw new IllegalArgumentException("Invalid HTTP method: " + paramHttpMethod);
    case ???: 
      return new HttpGet(paramURI);
    case ???: 
      return new HttpDelete(paramURI);
    case ???: 
      return new HttpHead(paramURI);
    case ???: 
      return new HttpOptions(paramURI);
    case ???: 
      return new HttpPost(paramURI);
    case ???: 
      return new HttpPut(paramURI);
    }
    return new HttpTrace(paramURI);
  }
  
  public ClientHttpRequest createRequest(URI paramURI, HttpMethod paramHttpMethod)
    throws IOException
  {
    HttpUriRequest localHttpUriRequest = createHttpRequest(paramHttpMethod, paramURI);
    postProcessHttpRequest(localHttpUriRequest);
    return new HttpComponentsClientHttpRequest(getHttpClient(), localHttpUriRequest, createHttpContext(paramHttpMethod, paramURI));
  }
  
  public void destroy()
  {
    getHttpClient().getConnectionManager().shutdown();
  }
  
  public HttpClient getHttpClient()
  {
    return httpClient;
  }
  
  protected void postProcessHttpRequest(HttpUriRequest paramHttpUriRequest)
  {
    HttpProtocolParams.setUseExpectContinue(paramHttpUriRequest.getParams(), false);
  }
  
  public void setConnectTimeout(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Assert.isTrue(bool, "Timeout must be a non-negative value");
      getHttpClient().getParams().setIntParameter("http.connection.timeout", paramInt);
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
      getHttpClient().getParams().setIntParameter("http.socket.timeout", paramInt);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.HttpComponentsClientHttpRequestFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */