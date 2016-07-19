package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;

final class ArrayBuilders$1
{
  ArrayBuilders$1(Class paramClass, int paramInt, Object paramObject) {}
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramObject == null);
        bool1 = bool2;
      } while (paramObject.getClass() != val$defaultValueType);
      bool1 = bool2;
    } while (Array.getLength(paramObject) != val$length);
    int i = 0;
    if (i < val$length)
    {
      Object localObject1 = Array.get(val$defaultValue, i);
      Object localObject2 = Array.get(paramObject, i);
      if (localObject1 == localObject2) {}
      while ((localObject1 == null) || (localObject1.equals(localObject2)))
      {
        i += 1;
        break;
      }
      return false;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ArrayBuilders.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */