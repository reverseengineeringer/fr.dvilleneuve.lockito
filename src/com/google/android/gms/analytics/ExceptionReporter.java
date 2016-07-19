package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.internal.zzae;
import java.util.ArrayList;

public class ExceptionReporter
  implements Thread.UncaughtExceptionHandler
{
  private final Context mContext;
  private final Thread.UncaughtExceptionHandler zzcsf;
  private final Tracker zzcsg;
  private ExceptionParser zzcsh;
  private GoogleAnalytics zzcsi;
  
  public ExceptionReporter(Tracker paramTracker, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler, Context paramContext)
  {
    if (paramTracker == null) {
      throw new NullPointerException("tracker cannot be null");
    }
    if (paramContext == null) {
      throw new NullPointerException("context cannot be null");
    }
    zzcsf = paramUncaughtExceptionHandler;
    zzcsg = paramTracker;
    zzcsh = new StandardExceptionParser(paramContext, new ArrayList());
    mContext = paramContext.getApplicationContext();
    if (paramUncaughtExceptionHandler == null)
    {
      paramTracker = "null";
      paramTracker = String.valueOf(paramTracker);
      if (paramTracker.length() == 0) {
        break label111;
      }
    }
    label111:
    for (paramTracker = "ExceptionReporter created, original handler is ".concat(paramTracker);; paramTracker = new String("ExceptionReporter created, original handler is "))
    {
      zzae.v(paramTracker);
      return;
      paramTracker = paramUncaughtExceptionHandler.getClass().getName();
      break;
    }
  }
  
  public ExceptionParser getExceptionParser()
  {
    return zzcsh;
  }
  
  public void setExceptionParser(ExceptionParser paramExceptionParser)
  {
    zzcsh = paramExceptionParser;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Object localObject = "UncaughtException";
    if (zzcsh != null)
    {
      if (paramThread != null)
      {
        localObject = paramThread.getName();
        localObject = zzcsh.getDescription((String)localObject, paramThrowable);
      }
    }
    else
    {
      str = String.valueOf(localObject);
      if (str.length() == 0) {
        break label126;
      }
    }
    label126:
    for (String str = "Reporting uncaught exception: ".concat(str);; str = new String("Reporting uncaught exception: "))
    {
      zzae.v(str);
      zzcsg.send(new HitBuilders.ExceptionBuilder().setDescription((String)localObject).setFatal(true).build());
      localObject = zzvx();
      ((GoogleAnalytics)localObject).dispatchLocalHits();
      ((GoogleAnalytics)localObject).zzwc();
      if (zzcsf != null)
      {
        zzae.v("Passing exception to the original handler");
        zzcsf.uncaughtException(paramThread, paramThrowable);
      }
      return;
      localObject = null;
      break;
    }
  }
  
  GoogleAnalytics zzvx()
  {
    if (zzcsi == null) {
      zzcsi = GoogleAnalytics.getInstance(mContext);
    }
    return zzcsi;
  }
  
  Thread.UncaughtExceptionHandler zzvy()
  {
    return zzcsf;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.ExceptionReporter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */