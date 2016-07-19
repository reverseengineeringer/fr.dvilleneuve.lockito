package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;

public final class InstallReferrerReceiver
  extends CampaignTrackingReceiver
{
  protected void zzi(Context paramContext, String paramString)
  {
    zzbe.zzog(paramString);
    zzbe.zzw(paramContext, paramString);
  }
  
  protected Class<? extends CampaignTrackingService> zzvv()
  {
    return InstallReferrerService.class;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.InstallReferrerReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */