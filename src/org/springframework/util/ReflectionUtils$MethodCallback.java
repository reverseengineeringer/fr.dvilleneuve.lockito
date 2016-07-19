package org.springframework.util;

import java.lang.reflect.Method;

public abstract interface ReflectionUtils$MethodCallback
{
  public abstract void doWith(Method paramMethod)
    throws IllegalArgumentException, IllegalAccessException;
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils.MethodCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */