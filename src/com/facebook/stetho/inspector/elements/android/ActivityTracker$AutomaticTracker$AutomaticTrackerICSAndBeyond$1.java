package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond$1
  implements Application.ActivityLifecycleCallbacks
{
  ActivityTracker$AutomaticTracker$AutomaticTrackerICSAndBeyond$1(ActivityTracker.AutomaticTracker.AutomaticTrackerICSAndBeyond paramAutomaticTrackerICSAndBeyond) {}
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    ActivityTracker.AutomaticTracker.AutomaticTrackerICSAndBeyond.access$100(this$0).add(paramActivity);
  }
  
  public void onActivityDestroyed(Activity paramActivity)
  {
    ActivityTracker.AutomaticTracker.AutomaticTrackerICSAndBeyond.access$100(this$0).remove(paramActivity);
  }
  
  public void onActivityPaused(Activity paramActivity) {}
  
  public void onActivityResumed(Activity paramActivity) {}
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity) {}
  
  public void onActivityStopped(Activity paramActivity) {}
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ActivityTracker.AutomaticTracker.AutomaticTrackerICSAndBeyond.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */