package org.springframework.util;

import java.lang.reflect.Method;
import java.util.List;

final class ReflectionUtils$1
  implements ReflectionUtils.MethodCallback
{
  ReflectionUtils$1(List paramList) {}
  
  public void doWith(Method paramMethod)
  {
    val$methods.add(paramMethod);
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */