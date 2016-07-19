package com.crashlytics.android;

import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityCallable;

class Crashlytics$1
  extends PriorityCallable<Void>
{
  Crashlytics$1(Crashlytics paramCrashlytics) {}
  
  public Void call()
    throws Exception
  {
    return this$0.doInBackground();
  }
  
  public Priority getPriority()
  {
    return Priority.IMMEDIATE;
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.Crashlytics.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */