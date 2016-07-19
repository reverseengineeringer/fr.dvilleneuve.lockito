package org.springframework.util;

import java.lang.reflect.Field;

final class ReflectionUtils$3
  implements ReflectionUtils.FieldCallback
{
  ReflectionUtils$3(Object paramObject1, Object paramObject2) {}
  
  public void doWith(Field paramField)
    throws IllegalArgumentException, IllegalAccessException
  {
    ReflectionUtils.makeAccessible(paramField);
    Object localObject = paramField.get(val$src);
    paramField.set(val$dest, localObject);
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */