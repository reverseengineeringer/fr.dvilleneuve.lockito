package org.springframework.util;

import java.lang.reflect.Field;

public abstract interface ReflectionUtils$FieldCallback
{
  public abstract void doWith(Field paramField)
    throws IllegalArgumentException, IllegalAccessException;
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils.FieldCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */