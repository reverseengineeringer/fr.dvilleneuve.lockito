package de.greenrobot.event.util;

public class ThrowableFailureEvent
  implements HasExecutionScope
{
  private Object executionContext;
  protected final boolean suppressErrorUi;
  protected final Throwable throwable;
  
  public ThrowableFailureEvent(Throwable paramThrowable)
  {
    throwable = paramThrowable;
    suppressErrorUi = false;
  }
  
  public ThrowableFailureEvent(Throwable paramThrowable, boolean paramBoolean)
  {
    throwable = paramThrowable;
    suppressErrorUi = paramBoolean;
  }
  
  public Object getExecutionScope()
  {
    return executionContext;
  }
  
  public Throwable getThrowable()
  {
    return throwable;
  }
  
  public boolean isSuppressErrorUi()
  {
    return suppressErrorUi;
  }
  
  public void setExecutionScope(Object paramObject)
  {
    executionContext = paramObject;
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.ThrowableFailureEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */