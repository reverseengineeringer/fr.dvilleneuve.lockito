package org.springframework.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

final class UriComponents$1
  implements UriComponents.PathComponent
{
  public UriComponents.PathComponent encode(String paramString)
    throws UnsupportedEncodingException
  {
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    return this == paramObject;
  }
  
  public UriComponents.PathComponent expand(UriComponents.UriTemplateVariables paramUriTemplateVariables)
  {
    return this;
  }
  
  public String getPath()
  {
    return null;
  }
  
  public List<String> getPathSegments()
  {
    return Collections.emptyList();
  }
  
  public int hashCode()
  {
    return 42;
  }
  
  public void verify() {}
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */