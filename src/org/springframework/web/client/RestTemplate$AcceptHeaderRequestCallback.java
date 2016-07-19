package org.springframework.web.client;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;

class RestTemplate$AcceptHeaderRequestCallback
  implements RequestCallback
{
  private final Class<?> responseType;
  
  private RestTemplate$AcceptHeaderRequestCallback(Class<?> paramClass)
  {
    Class localClass;
    responseType = localClass;
  }
  
  public void doWithRequest(ClientHttpRequest paramClientHttpRequest)
    throws IOException
  {
    if (responseType != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = this$0.getMessageConverters().iterator();
      while (localIterator1.hasNext())
      {
        Object localObject = (HttpMessageConverter)localIterator1.next();
        if (((HttpMessageConverter)localObject).canRead(responseType, null))
        {
          Iterator localIterator2 = ((HttpMessageConverter)localObject).getSupportedMediaTypes().iterator();
          while (localIterator2.hasNext())
          {
            MediaType localMediaType = (MediaType)localIterator2.next();
            localObject = localMediaType;
            if (localMediaType.getCharSet() != null) {
              localObject = new MediaType(localMediaType.getType(), localMediaType.getSubtype());
            }
            localArrayList.add(localObject);
          }
        }
      }
      if (!localArrayList.isEmpty())
      {
        MediaType.sortBySpecificity(localArrayList);
        if (Log.isLoggable("RestTemplate", 3)) {
          Log.d("RestTemplate", "Setting request Accept header to " + localArrayList);
        }
        paramClientHttpRequest.getHeaders().setAccept(localArrayList);
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestTemplate.AcceptHeaderRequestCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */