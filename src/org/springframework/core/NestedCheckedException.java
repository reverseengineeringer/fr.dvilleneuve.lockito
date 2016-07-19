package org.springframework.core;

public abstract class NestedCheckedException
  extends Exception
{
  private static final long serialVersionUID = 7100714597678207546L;
  
  static
  {
    NestedExceptionUtils.class.getName();
  }
  
  public NestedCheckedException(String paramString)
  {
    super(paramString);
  }
  
  public NestedCheckedException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public boolean contains(Class<?> paramClass)
  {
    if (paramClass == null) {}
    Throwable localThrowable2;
    do
    {
      return false;
      if (paramClass.isInstance(this)) {
        return true;
      }
      localThrowable2 = getCause();
    } while (localThrowable2 == this);
    Throwable localThrowable1 = localThrowable2;
    if ((localThrowable2 instanceof NestedCheckedException)) {
      return ((NestedCheckedException)localThrowable2).contains(paramClass);
    }
    do
    {
      localThrowable1 = localThrowable1.getCause();
      if (localThrowable1 == null) {
        break;
      }
      if (paramClass.isInstance(localThrowable1)) {
        return true;
      }
    } while (localThrowable1.getCause() != localThrowable1);
    return false;
  }
  
  public String getMessage()
  {
    return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
  }
  
  public Throwable getMostSpecificCause()
  {
    Throwable localThrowable = getRootCause();
    if (localThrowable != null) {
      return localThrowable;
    }
    return this;
  }
  
  public Throwable getRootCause()
  {
    Object localObject = null;
    for (Throwable localThrowable = getCause(); (localThrowable != null) && (localThrowable != localObject); localThrowable = localThrowable.getCause()) {
      localObject = localThrowable;
    }
    return (Throwable)localObject;
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.NestedCheckedException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */