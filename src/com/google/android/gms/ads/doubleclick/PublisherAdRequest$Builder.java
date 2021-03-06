package com.google.android.gms.ads.doubleclick;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzy;
import java.util.Date;
import java.util.List;

public final class PublisherAdRequest$Builder
{
  private final zzad.zza zzaid = new zzad.zza();
  
  public Builder addCategoryExclusion(String paramString)
  {
    zzaid.zzal(paramString);
    return this;
  }
  
  public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
  {
    zzaid.zzb(paramClass, paramBundle);
    return this;
  }
  
  public Builder addCustomTargeting(String paramString1, String paramString2)
  {
    zzaid.zzf(paramString1, paramString2);
    return this;
  }
  
  public Builder addCustomTargeting(String paramString, List<String> paramList)
  {
    if (paramList != null) {
      zzaid.zzf(paramString, zzy.zzhr(",").zza(paramList));
    }
    return this;
  }
  
  public Builder addKeyword(String paramString)
  {
    zzaid.zzaf(paramString);
    return this;
  }
  
  public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
  {
    zzaid.zza(paramNetworkExtras);
    return this;
  }
  
  public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
  {
    zzaid.zza(paramClass, paramBundle);
    return this;
  }
  
  public Builder addTestDevice(String paramString)
  {
    zzaid.zzag(paramString);
    return this;
  }
  
  public PublisherAdRequest build()
  {
    return new PublisherAdRequest(this, null);
  }
  
  public Builder setBirthday(Date paramDate)
  {
    zzaid.zza(paramDate);
    return this;
  }
  
  public Builder setContentUrl(String paramString)
  {
    zzab.zzb(paramString, "Content URL must be non-null.");
    zzab.zzh(paramString, "Content URL must be non-empty.");
    if (paramString.length() <= 512) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[] { Integer.valueOf(512), Integer.valueOf(paramString.length()) });
      zzaid.zzai(paramString);
      return this;
    }
  }
  
  public Builder setGender(int paramInt)
  {
    zzaid.zzt(paramInt);
    return this;
  }
  
  public Builder setIsDesignedForFamilies(boolean paramBoolean)
  {
    zzaid.zzo(paramBoolean);
    return this;
  }
  
  public Builder setLocation(Location paramLocation)
  {
    zzaid.zzb(paramLocation);
    return this;
  }
  
  @Deprecated
  public Builder setManualImpressionsEnabled(boolean paramBoolean)
  {
    zzaid.setManualImpressionsEnabled(paramBoolean);
    return this;
  }
  
  public Builder setPublisherProvidedId(String paramString)
  {
    zzaid.zzaj(paramString);
    return this;
  }
  
  public Builder setRequestAgent(String paramString)
  {
    zzaid.zzak(paramString);
    return this;
  }
  
  public Builder tagForChildDirectedTreatment(boolean paramBoolean)
  {
    zzaid.zzn(paramBoolean);
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */