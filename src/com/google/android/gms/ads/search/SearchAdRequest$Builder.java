package com.google.android.gms.ads.search;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class SearchAdRequest$Builder
{
  private int mBackgroundColor;
  private final zzad.zza zzaid = new zzad.zza();
  private String zzanp;
  private int zzcre;
  private int zzcrf;
  private int zzcrg;
  private int zzcrh;
  private int zzcri;
  private int zzcrj = 0;
  private int zzcrk;
  private String zzcrl;
  private int zzcrm;
  private String zzcrn;
  private int zzcro;
  private int zzcrp;
  
  public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
  {
    zzaid.zzb(paramClass, paramBundle);
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
  
  public SearchAdRequest build()
  {
    return new SearchAdRequest(this, null);
  }
  
  public Builder setAnchorTextColor(int paramInt)
  {
    zzcre = paramInt;
    return this;
  }
  
  public Builder setBackgroundColor(int paramInt)
  {
    mBackgroundColor = paramInt;
    zzcrf = Color.argb(0, 0, 0, 0);
    zzcrg = Color.argb(0, 0, 0, 0);
    return this;
  }
  
  public Builder setBackgroundGradient(int paramInt1, int paramInt2)
  {
    mBackgroundColor = Color.argb(0, 0, 0, 0);
    zzcrf = paramInt2;
    zzcrg = paramInt1;
    return this;
  }
  
  public Builder setBorderColor(int paramInt)
  {
    zzcrh = paramInt;
    return this;
  }
  
  public Builder setBorderThickness(int paramInt)
  {
    zzcri = paramInt;
    return this;
  }
  
  public Builder setBorderType(int paramInt)
  {
    zzcrj = paramInt;
    return this;
  }
  
  public Builder setCallButtonColor(int paramInt)
  {
    zzcrk = paramInt;
    return this;
  }
  
  public Builder setCustomChannels(String paramString)
  {
    zzcrl = paramString;
    return this;
  }
  
  public Builder setDescriptionTextColor(int paramInt)
  {
    zzcrm = paramInt;
    return this;
  }
  
  public Builder setFontFace(String paramString)
  {
    zzcrn = paramString;
    return this;
  }
  
  public Builder setHeaderTextColor(int paramInt)
  {
    zzcro = paramInt;
    return this;
  }
  
  public Builder setHeaderTextSize(int paramInt)
  {
    zzcrp = paramInt;
    return this;
  }
  
  public Builder setLocation(Location paramLocation)
  {
    zzaid.zzb(paramLocation);
    return this;
  }
  
  public Builder setQuery(String paramString)
  {
    zzanp = paramString;
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
 * Qualified Name:     com.google.android.gms.ads.search.SearchAdRequest.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */