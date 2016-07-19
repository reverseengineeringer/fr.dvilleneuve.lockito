package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;

class RestTemplate$ResponseEntityResponseExtractor<T>
  implements ResponseExtractor<ResponseEntity<T>>
{
  private final HttpMessageConverterExtractor<T> delegate;
  
  public RestTemplate$ResponseEntityResponseExtractor(Class<T> paramClass)
  {
    Object localObject;
    if ((localObject != null) && (!Void.class.equals(localObject)))
    {
      delegate = new HttpMessageConverterExtractor((Class)localObject, paramClass.getMessageConverters());
      return;
    }
    delegate = null;
  }
  
  public ResponseEntity<T> extractData(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    if (delegate != null) {
      return new ResponseEntity(delegate.extractData(paramClientHttpResponse), paramClientHttpResponse.getHeaders(), paramClientHttpResponse.getStatusCode());
    }
    return new ResponseEntity(paramClientHttpResponse.getHeaders(), paramClientHttpResponse.getStatusCode());
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestTemplate.ResponseEntityResponseExtractor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */