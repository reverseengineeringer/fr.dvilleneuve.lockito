package org.springframework.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class ReflectionUtils$2
  implements ReflectionUtils.MethodCallback
{
  ReflectionUtils$2(List paramList) {}
  
  public void doWith(Method paramMethod)
  {
    Object localObject2 = null;
    Iterator localIterator = val$methods.iterator();
    Method localMethod;
    do
    {
      localObject1 = localObject2;
      if (!localIterator.hasNext()) {
        break;
      }
      localMethod = (Method)localIterator.next();
    } while ((!paramMethod.getName().equals(localMethod.getName())) || (!Arrays.equals(paramMethod.getParameterTypes(), localMethod.getParameterTypes())));
    Object localObject1 = localObject2;
    if (localMethod.getReturnType() != paramMethod.getReturnType())
    {
      localObject1 = localObject2;
      if (localMethod.getReturnType().isAssignableFrom(paramMethod.getReturnType())) {
        localObject1 = localMethod;
      }
    }
    if (localObject1 != null) {
      val$methods.remove(localObject1);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */