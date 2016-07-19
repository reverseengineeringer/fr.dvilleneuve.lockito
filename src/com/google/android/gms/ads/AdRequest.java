package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzad.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzab;
import java.util.Date;
import java.util.Set;

public final class AdRequest
{
  public static final String DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  public static final int GENDER_FEMALE = 2;
  public static final int GENDER_MALE = 1;
  public static final int GENDER_UNKNOWN = 0;
  public static final int MAX_CONTENT_URL_LENGTH = 512;
  private final zzad zzaic;
  
  private AdRequest(Builder paramBuilder)
  {
    zzaic = new zzad(Builder.zza(paramBuilder));
  }
  
  public Date getBirthday()
  {
    return zzaic.getBirthday();
  }
  
  public String getContentUrl()
  {
    return zzaic.getContentUrl();
  }
  
  public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> paramClass)
  {
    return zzaic.getCustomEventExtrasBundle(paramClass);
  }
  
  public int getGender()
  {
    return zzaic.getGender();
  }
  
  public Set<String> getKeywords()
  {
    return zzaic.getKeywords();
  }
  
  public Location getLocation()
  {
    return zzaic.getLocation();
  }
  
  @Deprecated
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return zzaic.getNetworkExtras(paramClass);
  }
  
  public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> paramClass)
  {
    return zzaic.getNetworkExtrasBundle(paramClass);
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return zzaic.isTestDevice(paramContext);
  }
  
  public zzad zzdd()
  {
    return zzaic;
  }
  
  public static final class Builder
  {
    private final zzad.zza zzaid = new zzad.zza();
    
    public Builder()
    {
      zzaid.zzag(AdRequest.DEVICE_ID_EMULATOR);
    }
    
    public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
    {
      zzaid.zzb(paramClass, paramBundle);
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
      if ((paramClass.equals(AdMobAdapter.class)) && (paramBundle.getBoolean("_emulatorLiveAds"))) {
        zzaid.zzah(AdRequest.DEVICE_ID_EMULATOR);
      }
      return this;
    }
    
    public Builder addTestDevice(String paramString)
    {
      zzaid.zzag(paramString);
      return this;
    }
    
    public AdRequest build()
    {
      return new AdRequest(this, null);
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.AdRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */