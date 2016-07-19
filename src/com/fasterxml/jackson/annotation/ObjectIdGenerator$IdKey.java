package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public final class ObjectIdGenerator$IdKey
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final int hashCode;
  private final Object key;
  private final Class<?> scope;
  private final Class<?> type;
  
  public ObjectIdGenerator$IdKey(Class<?> paramClass1, Class<?> paramClass2, Object paramObject)
  {
    type = paramClass1;
    scope = paramClass2;
    key = paramObject;
    int j = paramObject.hashCode() + paramClass1.getName().hashCode();
    int i = j;
    if (paramClass2 != null) {
      i = j ^ paramClass2.getName().hashCode();
    }
    hashCode = i;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (paramObject.getClass() != getClass()) {
        return false;
      }
      paramObject = (IdKey)paramObject;
    } while ((key.equals(key)) && (type == type) && (scope == scope));
    return false;
  }
  
  public int hashCode()
  {
    return hashCode;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */