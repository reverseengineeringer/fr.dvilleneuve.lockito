package org.springframework.web.client;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UriUtils;

class RestTemplate$HttpUrlTemplate
  extends UriTemplate
{
  private static final long serialVersionUID = 1L;
  
  public RestTemplate$HttpUrlTemplate(String paramString)
  {
    super(paramString);
  }
  
  protected URI encodeUri(String paramString)
  {
    try
    {
      URI localURI = new URI(UriUtils.encodeHttpUrl(paramString, "UTF-8"));
      return localURI;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new IllegalStateException(paramString);
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new IllegalArgumentException("Could not create HTTP URL from [" + paramString + "]: " + localURISyntaxException, localURISyntaxException);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestTemplate.HttpUrlTemplate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */