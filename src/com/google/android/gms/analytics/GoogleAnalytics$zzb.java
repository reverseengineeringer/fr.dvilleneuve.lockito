package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

@TargetApi(14)
class GoogleAnalytics$zzb
  implements Application.ActivityLifecycleCallbacks
{
  GoogleAnalytics$zzb(GoogleAnalytics paramGoogleAnalytics) {}
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityDestroyed(Activity paramActivity) {}
  
  public void onActivityPaused(Activity paramActivity) {}
  
  public void onActivityResumed(Activity paramActivity) {}
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity)
  {
    zzcsp.zzm(paramActivity);
  }
  
  public void onActivityStopped(Activity paramActivity)
  {
    zzcsp.zzn(paramActivity);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.GoogleAnalytics.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */