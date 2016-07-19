package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.internal.zzab;

public abstract class ResolvingResultCallbacks<R extends Result>
  extends ResultCallbacks<R>
{
  private final Activity mActivity;
  private final int sd;
  
  protected ResolvingResultCallbacks(@NonNull Activity paramActivity, int paramInt)
  {
    mActivity = ((Activity)zzab.zzb(paramActivity, "Activity must not be null"));
    sd = paramInt;
  }
  
  public final void onFailure(@NonNull Status paramStatus)
  {
    if (paramStatus.hasResolution()) {
      try
      {
        paramStatus.startResolutionForResult(mActivity, sd);
        return;
      }
      catch (IntentSender.SendIntentException paramStatus)
      {
        Log.e("ResolvingResultCallback", "Failed to start resolution", paramStatus);
        onUnresolvableFailure(new Status(8));
        return;
      }
    }
    onUnresolvableFailure(paramStatus);
  }
  
  public abstract void onSuccess(@NonNull R paramR);
  
  public abstract void onUnresolvableFailure(@NonNull Status paramStatus);
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.ResolvingResultCallbacks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */