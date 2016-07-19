package com.google.android.gms.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzs;

public class zzqn
{
  private final Object uZ;
  
  public zzqn(Activity paramActivity)
  {
    zzab.zzb(paramActivity, "Activity must not be null");
    if ((zzs.zzavj()) || ((paramActivity instanceof FragmentActivity))) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "This Activity is not supported before platform version 11 (3.0 Honeycomb). Please use FragmentActivity instead.");
      uZ = paramActivity;
      return;
    }
  }
  
  public boolean zzaqm()
  {
    return uZ instanceof FragmentActivity;
  }
  
  public Activity zzaqn()
  {
    return (Activity)uZ;
  }
  
  public FragmentActivity zzaqo()
  {
    return (FragmentActivity)uZ;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */