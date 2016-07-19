package org.springframework.beans;

import java.beans.PropertyChangeEvent;
import org.springframework.core.ErrorCoded;

public abstract class PropertyAccessException
  extends BeansException
  implements ErrorCoded
{
  private static final long serialVersionUID = 1L;
  private transient PropertyChangeEvent propertyChangeEvent;
  
  public PropertyAccessException(PropertyChangeEvent paramPropertyChangeEvent, String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    propertyChangeEvent = paramPropertyChangeEvent;
  }
  
  public PropertyAccessException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public PropertyChangeEvent getPropertyChangeEvent()
  {
    return propertyChangeEvent;
  }
  
  public String getPropertyName()
  {
    if (propertyChangeEvent != null) {
      return propertyChangeEvent.getPropertyName();
    }
    return null;
  }
  
  public Object getValue()
  {
    if (propertyChangeEvent != null) {
      return propertyChangeEvent.getNewValue();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     org.springframework.beans.PropertyAccessException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */