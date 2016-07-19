package org.springframework.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract interface ParameterNameDiscoverer
{
  public abstract String[] getParameterNames(Constructor<?> paramConstructor);
  
  public abstract String[] getParameterNames(Method paramMethod);
}

/* Location:
 * Qualified Name:     org.springframework.core.ParameterNameDiscoverer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */