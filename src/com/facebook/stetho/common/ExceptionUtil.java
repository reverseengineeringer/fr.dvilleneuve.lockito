package com.facebook.stetho.common;

public class ExceptionUtil
{
  public static RuntimeException propagate(Throwable paramThrowable)
  {
    propagateIfInstanceOf(paramThrowable, Error.class);
    propagateIfInstanceOf(paramThrowable, RuntimeException.class);
    throw new RuntimeException(paramThrowable);
  }
  
  public static <T extends Throwable> void propagateIfInstanceOf(Throwable paramThrowable, Class<T> paramClass)
    throws Throwable
  {
    if (paramClass.isInstance(paramThrowable)) {
      throw paramThrowable;
    }
  }
  
  public static <T extends Throwable> void sneakyThrow(Throwable paramThrowable)
    throws Throwable
  {
    throw paramThrowable;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.ExceptionUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */