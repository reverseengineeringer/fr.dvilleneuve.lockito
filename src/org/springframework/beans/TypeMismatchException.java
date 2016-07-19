package org.springframework.beans;

import java.beans.PropertyChangeEvent;

public class TypeMismatchException
  extends PropertyAccessException
{
  public static final String ERROR_CODE = "typeMismatch";
  private static final long serialVersionUID = 1L;
  private Class<?> requiredType;
  private transient Object value;
  
  public TypeMismatchException(PropertyChangeEvent paramPropertyChangeEvent, Class<?> paramClass)
  {
    this(paramPropertyChangeEvent, paramClass, null);
  }
  
  public TypeMismatchException(PropertyChangeEvent paramPropertyChangeEvent, Class<?> paramClass, Throwable paramThrowable) {}
  
  public TypeMismatchException(Object paramObject, Class<?> paramClass)
  {
    this(paramObject, paramClass, null);
  }
  
  public TypeMismatchException(Object paramObject, Class<?> paramClass, Throwable paramThrowable) {}
  
  public String getErrorCode()
  {
    return "typeMismatch";
  }
  
  public Class<?> getRequiredType()
  {
    return requiredType;
  }
  
  public Object getValue()
  {
    return value;
  }
}

/* Location:
 * Qualified Name:     org.springframework.beans.TypeMismatchException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */