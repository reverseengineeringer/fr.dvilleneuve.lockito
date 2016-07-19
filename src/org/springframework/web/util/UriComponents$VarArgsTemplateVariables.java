package org.springframework.web.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class UriComponents$VarArgsTemplateVariables
  implements UriComponents.UriTemplateVariables
{
  private final Iterator<Object> valueIterator;
  
  public UriComponents$VarArgsTemplateVariables(Object... paramVarArgs)
  {
    valueIterator = Arrays.asList(paramVarArgs).iterator();
  }
  
  public Object getValue(String paramString)
  {
    if (!valueIterator.hasNext()) {
      throw new IllegalArgumentException("Not enough variable values available to expand '" + paramString + "'");
    }
    return valueIterator.next();
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.VarArgsTemplateVariables
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */