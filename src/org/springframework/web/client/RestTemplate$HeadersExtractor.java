package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;

class RestTemplate$HeadersExtractor
  implements ResponseExtractor<HttpHeaders>
{
  public HttpHeaders extractData(ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    return paramClientHttpResponse.getHeaders();
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestTemplate.HeadersExtractor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */