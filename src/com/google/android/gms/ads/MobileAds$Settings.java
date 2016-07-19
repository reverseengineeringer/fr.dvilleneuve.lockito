package com.google.android.gms.ads;

import com.google.android.gms.ads.internal.client.zzah;

public final class MobileAds$Settings
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

/* Location:
 * Qualified Name:     com.google.android.gms.ads.MobileAds.Settings
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */