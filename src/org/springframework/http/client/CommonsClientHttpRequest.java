package org.springframework.http.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Deprecated
final class CommonsClientHttpRequest
  extends AbstractBufferingClientHttpRequest
{
  private final HttpClient httpClient;
  private final HttpMethodBase httpMethod;
  
  CommonsClientHttpRequest(HttpClient paramHttpClient, HttpMethodBase paramHttpMethodBase)
  {
    httpClient = paramHttpClient;
    httpMethod = paramHttpMethodBase;
  }
  
  public ClientHttpResponse executeInternal(HttpHeaders paramHttpHeaders, byte[] paramArrayOfByte)
    throws IOException
  {
    paramHttpHeaders = paramHttpHeaders.entrySet().iterator();
    while (paramHttpHeaders.hasNext())
    {
      Object localObject = (Map.Entry)paramHttpHeaders.next();
      String str1 = (String)((Map.Entry)localObject).getKey();
      localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str2 = (String)((Iterator)localObject).next();
        httpMethod.addRequestHeader(str1, str2);
      }
    }
    if ((httpMethod instanceof EntityEnclosingMethod)) {
      ((EntityEnclosingMethod)httpMethod).setRequestEntity(new ByteArrayRequestEntity(paramArrayOfByte));
    }
    httpClient.executeMethod(httpMethod);
    return new CommonsClientHttpResponse(httpMethod);
  }
  
  public HttpMethod getMethod()
  {
    return HttpMethod.valueOf(httpMethod.getName());
  }
  
  public java.net.URI getURI()
  {
    try
    {
      java.net.URI localURI = java.net.URI.create(httpMethod.getURI().getEscapedURI());
      return localURI;
    }
    catch (URIException localURIException)
    {
      throw new IllegalStateException("Could not get HttpMethod URI: " + localURIException.getMessage(), localURIException);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.client.CommonsClientHttpRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */