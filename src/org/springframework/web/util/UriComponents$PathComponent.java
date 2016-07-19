package org.springframework.web.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

abstract interface UriComponents$PathComponent
{
  public abstract PathComponent encode(String paramString)
    throws UnsupportedEncodingException;
  
  public abstract PathComponent expand(UriComponents.UriTemplateVariables paramUriTemplateVariables);
  
  public abstract String getPath();
  
  public abstract List<String> getPathSegments();
  
  public abstract void verify();
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.PathComponent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */