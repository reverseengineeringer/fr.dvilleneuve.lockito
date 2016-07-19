package org.springframework.web.util;

import java.util.Map;

class UriComponents$MapTemplateVariables
  implements UriComponents.UriTemplateVariables
{
  private final Map<String, ?> uriVariables;
  
  public UriComponents$MapTemplateVariables(Map<String, ?> paramMap)
  {
    uriVariables = paramMap;
  }
  
  public Object getValue(String paramString)
  {
    if (!uriVariables.containsKey(paramString)) {
      throw new IllegalArgumentException("Map has no value for '" + paramString + "'");
    }
    return uriVariables.get(paramString);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.MapTemplateVariables
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */