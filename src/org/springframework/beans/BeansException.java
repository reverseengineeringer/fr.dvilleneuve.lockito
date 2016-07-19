package org.springframework.beans;

import org.springframework.core.NestedRuntimeException;
import org.springframework.util.ObjectUtils;

public abstract class BeansException
  extends NestedRuntimeException
{
  private static final long serialVersionUID = 1L;
  
  public BeansException(String paramString)
  {
    super(paramString);
  }
  
  public BeansException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof BeansException)) {
        return false;
      }
      paramObject = (BeansException)paramObject;
    } while ((getMessage().equals(((BeansException)paramObject).getMessage())) && (ObjectUtils.nullSafeEquals(getCause(), ((BeansException)paramObject).getCause())));
    return false;
  }
  
  public int hashCode()
  {
    return getMessage().hashCode();
  }
}

/* Location:
 * Qualified Name:     org.springframework.beans.BeansException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */