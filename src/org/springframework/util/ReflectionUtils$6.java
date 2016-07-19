package org.springframework.util;

import java.lang.reflect.Method;

final class ReflectionUtils$6
  implements ReflectionUtils.MethodFilter
{
  public boolean matches(Method paramMethod)
  {
    return (!paramMethod.isBridge()) && (paramMethod.getDeclaringClass() != Object.class);
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */