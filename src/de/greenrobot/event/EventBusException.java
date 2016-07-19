package de.greenrobot.event;

public class EventBusException
  extends RuntimeException
{
  private static final long serialVersionUID = -2912559384646531479L;
  
  public EventBusException(String paramString)
  {
    super(paramString);
  }
  
  public EventBusException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public EventBusException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.EventBusException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */