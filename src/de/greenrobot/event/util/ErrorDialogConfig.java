package de.greenrobot.event.util;

import android.content.res.Resources;
import android.util.Log;
import de.greenrobot.event.EventBus;

public class ErrorDialogConfig
{
  int defaultDialogIconId;
  final int defaultErrorMsgId;
  Class<?> defaultEventTypeOnDialogClosed;
  final int defaultTitleId;
  EventBus eventBus;
  boolean logExceptions = true;
  final ExceptionToResourceMapping mapping;
  final Resources resources;
  String tagForLoggingExceptions;
  
  public ErrorDialogConfig(Resources paramResources, int paramInt1, int paramInt2)
  {
    resources = paramResources;
    defaultTitleId = paramInt1;
    defaultErrorMsgId = paramInt2;
    mapping = new ExceptionToResourceMapping();
  }
  
  public ErrorDialogConfig addMapping(Class<? extends Throwable> paramClass, int paramInt)
  {
    mapping.addMapping(paramClass, paramInt);
    return this;
  }
  
  public void disableExceptionLogging()
  {
    logExceptions = false;
  }
  
  EventBus getEventBus()
  {
    if (eventBus != null) {
      return eventBus;
    }
    return EventBus.getDefault();
  }
  
  public int getMessageIdForThrowable(Throwable paramThrowable)
  {
    Integer localInteger = mapping.mapThrowable(paramThrowable);
    if (localInteger != null) {
      return localInteger.intValue();
    }
    Log.d(EventBus.TAG, "No specific message ressource ID found for " + paramThrowable);
    return defaultErrorMsgId;
  }
  
  public void setDefaultDialogIconId(int paramInt)
  {
    defaultDialogIconId = paramInt;
  }
  
  public void setDefaultEventTypeOnDialogClosed(Class<?> paramClass)
  {
    defaultEventTypeOnDialogClosed = paramClass;
  }
  
  public void setEventBus(EventBus paramEventBus)
  {
    eventBus = paramEventBus;
  }
  
  public void setTagForLoggingExceptions(String paramString)
  {
    tagForLoggingExceptions = paramString;
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.ErrorDialogConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */