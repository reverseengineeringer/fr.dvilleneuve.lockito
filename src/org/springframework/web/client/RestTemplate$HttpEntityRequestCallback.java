package org.springframework.web.client;

import android.util.Log;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;

class RestTemplate$HttpEntityRequestCallback
  extends RestTemplate.AcceptHeaderRequestCallback
{
  private final HttpEntity<Object> requestEntity;
  
  private RestTemplate$HttpEntityRequestCallback(RestTemplate paramRestTemplate, Object paramObject)
  {
    this(paramRestTemplate, paramObject, null);
  }
  
  private RestTemplate$HttpEntityRequestCallback(Object paramObject, Class<?> paramClass)
  {
    super((RestTemplate)paramObject, localClass, null);
    if ((paramClass instanceof HttpEntity))
    {
      requestEntity = ((HttpEntity)paramClass);
      return;
    }
    if (paramClass != null)
    {
      requestEntity = new HttpEntity(paramClass);
      return;
    }
    requestEntity = HttpEntity.EMPTY;
  }
  
  public void doWithRequest(ClientHttpRequest paramClientHttpRequest)
    throws IOException
  {
    super.doWithRequest(paramClientHttpRequest);
    if (!requestEntity.hasBody())
    {
      paramClientHttpRequest = paramClientHttpRequest.getHeaders();
      localObject = requestEntity.getHeaders();
      if (!((HttpHeaders)localObject).isEmpty()) {
        paramClientHttpRequest.putAll((Map)localObject);
      }
      if (paramClientHttpRequest.getContentLength() == -1L) {
        paramClientHttpRequest.setContentLength(0L);
      }
      return;
    }
    Object localObject = requestEntity.getBody();
    Class localClass = localObject.getClass();
    HttpHeaders localHttpHeaders = requestEntity.getHeaders();
    MediaType localMediaType = localHttpHeaders.getContentType();
    Iterator localIterator = this$0.getMessageConverters().iterator();
    while (localIterator.hasNext())
    {
      HttpMessageConverter localHttpMessageConverter = (HttpMessageConverter)localIterator.next();
      if (localHttpMessageConverter.canWrite(localClass, localMediaType))
      {
        if (!localHttpHeaders.isEmpty()) {
          paramClientHttpRequest.getHeaders().putAll(localHttpHeaders);
        }
        if (Log.isLoggable("RestTemplate", 3))
        {
          if (localMediaType == null) {
            break label229;
          }
          Log.d("RestTemplate", "Writing [" + localObject + "] as \"" + localMediaType + "\" using [" + localHttpMessageConverter + "]");
        }
        for (;;)
        {
          localHttpMessageConverter.write(localObject, localMediaType, paramClientHttpRequest);
          return;
          label229:
          Log.d("RestTemplate", "Writing [" + localObject + "] using [" + localHttpMessageConverter + "]");
        }
      }
    }
    localObject = "Could not write request: no suitable HttpMessageConverter found for request type [" + localClass.getName() + "]";
    paramClientHttpRequest = (ClientHttpRequest)localObject;
    if (localMediaType != null) {
      paramClientHttpRequest = (String)localObject + " and content type [" + localMediaType + "]";
    }
    throw new RestClientException(paramClientHttpRequest);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestTemplate.HttpEntityRequestCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */