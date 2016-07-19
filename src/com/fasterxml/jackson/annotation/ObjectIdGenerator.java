package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public abstract class ObjectIdGenerator<T>
  implements Serializable
{
  public abstract boolean canUseFor(ObjectIdGenerator<?> paramObjectIdGenerator);
  
  public abstract ObjectIdGenerator<T> forScope(Class<?> paramClass);
  
  public abstract T generateId(Object paramObject);
  
  public abstract Class<?> getScope();
  
  public abstract IdKey key(Object paramObject);
  
  public abstract ObjectIdGenerator<T> newForSerialization(Object paramObject);
  
  public static final class IdKey
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private final int hashCode;
    private final Object key;
    private final Class<?> scope;
    private final Class<?> type;
    
    public IdKey(Class<?> paramClass1, Class<?> paramClass2, Object paramObject)
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
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.annotation.ObjectIdGenerator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */