package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build.VERSION;
import android.os.Bundle;
import javax.annotation.Nullable;

abstract class ActivityTracker$AutomaticTracker
{
  @Nullable
  public static AutomaticTracker newInstanceIfPossible(Application paramApplication, ActivityTracker paramActivityTracker)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      return new AutomaticTrackerICSAndBeyond(paramApplication, paramActivityTracker);
    }
    return null;
  }
  
  public abstract void register();
  
  public abstract void unregister();
  
  @TargetApi(14)
  private static class AutomaticTrackerICSAndBeyond
    extends ActivityTracker.AutomaticTracker
  {
    private final Application mApplication;
    private final Application.ActivityLifecycleCallbacks mLifecycleCallbacks = new Application.ActivityLifecycleCallbacks()
    {
      public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
      {
        mTracker.add(paramAnonymousActivity);
      }
      
      public void onActivityDestroyed(Activity paramAnonymousActivity)
      {
        mTracker.remove(paramAnonymousActivity);
      }
      
      public void onActivityPaused(Activity paramAnonymousActivity) {}
      
      public void onActivityResumed(Activity paramAnonymousActivity) {}
      
      public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
      
      public void onActivityStarted(Activity paramAnonymousActivity) {}
      
      public void onActivityStopped(Activity paramAnonymousActivity) {}
    };
    private final ActivityTracker mTracker;
    
    public AutomaticTrackerICSAndBeyond(Application paramApplication, ActivityTracker paramActivityTracker)
    {
      super();
      mApplication = paramApplication;
      mTracker = paramActivityTracker;
    }
    
    public void register()
    {
      mApplication.registerActivityLifecycleCallbacks(mLifecycleCallbacks);
    }
    
    public void unregister()
    {
      mApplication.unregisterActivityLifecycleCallbacks(mLifecycleCallbacks);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ActivityTracker.AutomaticTracker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */