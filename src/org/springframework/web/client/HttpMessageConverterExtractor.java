package org.springframework.web.client;

import android.util.Log;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;

public class HttpMessageConverterExtractor<T>
  implements ResponseExtractor<T>
{
  private static final String TAG = "RestTemplate";
  private final List<HttpMessageConverter<?>> messageConverters;
  private final Class<T> responseType;
  
  public HttpMessageConverterExtractor(Class<T> paramClass, List<HttpMessageConverter<?>> paramList)
  {
    Assert.notNull(paramClass, "'responseType' must not be null");
    Assert.notEmpty(paramList, "'messageConverters' must not be empty");
    responseType = paramClass;
    messageConverters = paramList;
  }
  
  public T extractData(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    if (!hasMessageBody(paramClientHttpResponse)) {
      return null;
    }
    Object localObject2 = paramClientHttpResponse.getHeaders().getContentType();
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      if (Log.isLoggable("RestTemplate", 3)) {
        Log.d("RestTemplate", "No Content-Type header found, defaulting to application/octet-stream");
      }
      localObject1 = MediaType.APPLICATION_OCTET_STREAM;
    }
    localObject2 = messageConverters.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      HttpMessageConverter localHttpMessageConverter = (HttpMessageConverter)((Iterator)localObject2).next();
      if (localHttpMessageConverter.canRead(responseType, (MediaType)localObject1))
      {
        if (Log.isLoggable("RestTemplate", 3)) {
          Log.d("RestTemplate", "Reading [" + responseType.getName() + "] as \"" + localObject1 + "\" using [" + localHttpMessageConverter + "]");
        }
        return (T)localHttpMessageConverter.read(responseType, paramClientHttpResponse);
      }
    }
    throw new RestClientException("Could not extract response: no suitable HttpMessageConverter found for response type [" + responseType.getName() + "] and content type [" + localObject1 + "]");
  }
  
  protected boolean hasMessageBody(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    HttpStatus localHttpStatus = paramClientHttpResponse.getStatusCode();
    if ((localHttpStatus == HttpStatus.NO_CONTENT) || (localHttpStatus == HttpStatus.NOT_MODIFIED)) {}
    while (paramClientHttpResponse.getHeaders().getContentLength() == 0L) {
      return false;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.HttpMessageConverterExtractor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */