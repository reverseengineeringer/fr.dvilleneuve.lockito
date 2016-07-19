package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.internal.client.zzag;
import com.google.android.gms.ads.internal.client.zzah;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class MobileAds
{
  public static RewardedVideoAd getRewardedVideoAdInstance(Context paramContext)
  {
    return zzag.zzjo().getRewardedVideoAdInstance(paramContext);
  }
  
  @Deprecated
  public static void initialize(Context paramContext)
  {
    initialize(paramContext, null, null);
  }
  
  @RequiresPermission("android.permission.INTERNET")
  public static void initialize(Context paramContext, String paramString)
  {
    initialize(paramContext, paramString, null);
  }
  
  @Deprecated
  @RequiresPermission("android.permission.INTERNET")
  public static void initialize(Context paramContext, String paramString, Settings paramSettings)
  {
    zzag localzzag = zzag.zzjo();
    if (paramSettings == null) {}
    for (paramSettings = null;; paramSettings = paramSettings.zzdf())
    {
      localzzag.zza(paramContext, paramString, paramSettings);
      return;
    }
  }
  
  public static void setAppMuted(boolean paramBoolean)
  {
    zzag.zzjo().setAppMuted(paramBoolean);
  }
  
  public static void setAppVolume(float paramFloat)
  {
    zzag.zzjo().setAppVolume(paramFloat);
  }
  
  public static final class Settings
  {
    private final zzah zzaik = new zzah();
    
    @Deprecated
    public String getTrackingId()
    {
      return zzaik.getTrackingId();
    }
    
    @Deprecated
    public boolean isGoogleAnalyticsEnabled()
    {
      return zzaik.isGoogleAnalyticsEnabled();
    }
    
    @Deprecated
    public Settings setGoogleAnalyticsEnabled(boolean paramBoolean)
    {
      zzaik.zzp(paramBoolean);
      return this;
    }
    
    @Deprecated
    public Settings setTrackingId(String paramString)
    {
      zzaik.zzao(paramString);
      return this;
    }
    
    zzah zzdf()
    {
      return zzaik;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.MobileAds
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */