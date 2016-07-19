package org.springframework.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.util.StringUtils;

final class UriComponents$FullPathComponent
  implements UriComponents.PathComponent
{
  private final String path;
  
  UriComponents$FullPathComponent(String paramString)
  {
    path = paramString;
  }
  
  public UriComponents.PathComponent encode(String paramString)
    throws UnsupportedEncodingException
  {
    return new FullPathComponent(UriComponents.encodeUriComponent(getPath(), paramString, UriComponents.Type.PATH));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof FullPathComponent))
    {
      paramObject = (FullPathComponent)paramObject;
      return getPath().equals(((FullPathComponent)paramObject).getPath());
    }
    return false;
  }
  
  public UriComponents.PathComponent expand(UriComponents.UriTemplateVariables paramUriTemplateVariables)
  {
    return new FullPathComponent(UriComponents.access$200(getPath(), paramUriTemplateVariables));
  }
  
  public String getPath()
  {
    return path;
  }
  
  public List<String> getPathSegments()
  {
    String str = new String(new char[] { '/' });
    return Collections.unmodifiableList(Arrays.asList(StringUtils.tokenizeToStringArray(path, str)));
  }
  
  public int hashCode()
  {
    return getPath().hashCode();
  }
  
  public void verify()
  {
    UriComponents.access$100(path, UriComponents.Type.PATH);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.FullPathComponent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */