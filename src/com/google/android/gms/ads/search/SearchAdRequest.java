package com.google.android.gms.ads.search;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzad.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class SearchAdRequest
{
  public static final int BORDER_TYPE_DASHED = 1;
  public static final int BORDER_TYPE_DOTTED = 2;
  public static final int BORDER_TYPE_NONE = 0;
  public static final int BORDER_TYPE_SOLID = 3;
  public static final int CALL_BUTTON_COLOR_DARK = 2;
  public static final int CALL_BUTTON_COLOR_LIGHT = 0;
  public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
  public static final String DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  private final int mBackgroundColor;
  private final zzad zzaic;
  private final String zzanp;
  private final int zzcre;
  private final int zzcrf;
  private final int zzcrg;
  private final int zzcrh;
  private final int zzcri;
  private final int zzcrj;
  private final int zzcrk;
  private final String zzcrl;
  private final int zzcrm;
  private final String zzcrn;
  private final int zzcro;
  private final int zzcrp;
  
  private SearchAdRequest(Builder paramBuilder)
  {
    zzcre = Builder.zza(paramBuilder);
    mBackgroundColor = Builder.zzb(paramBuilder);
    zzcrf = Builder.zzc(paramBuilder);
    zzcrg = Builder.zzd(paramBuilder);
    zzcrh = Builder.zze(paramBuilder);
    zzcri = Builder.zzf(paramBuilder);
    zzcrj = Builder.zzg(paramBuilder);
    zzcrk = Builder.zzh(paramBuilder);
    zzcrl = Builder.zzi(paramBuilder);
    zzcrm = Builder.zzj(paramBuilder);
    zzcrn = Builder.zzk(paramBuilder);
    zzcro = Builder.zzl(paramBuilder);
    zzcrp = Builder.zzm(paramBuilder);
    zzanp = Builder.zzn(paramBuilder);
    zzaic = new zzad(Builder.zzo(paramBuilder), this);
  }
  
  public int getAnchorTextColor()
  {
    return zzcre;
  }
  
  public int getBackgroundColor()
  {
    return mBackgroundColor;
  }
  
  public int getBackgroundGradientBottom()
  {
    return zzcrf;
  }
  
  public int getBackgroundGradientTop()
  {
    return zzcrg;
  }
  
  public int getBorderColor()
  {
    return zzcrh;
  }
  
  public int getBorderThickness()
  {
    return zzcri;
  }
  
  public int getBorderType()
  {
    return zzcrj;
  }
  
  public int getCallButtonColor()
  {
    return zzcrk;
  }
  
  public String getCustomChannels()
  {
    return zzcrl;
  }
  
  public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> paramClass)
  {
    return zzaic.getCustomEventExtrasBundle(paramClass);
  }
  
  public int getDescriptionTextColor()
  {
    return zzcrm;
  }
  
  public String getFontFace()
  {
    return zzcrn;
  }
  
  public int getHeaderTextColor()
  {
    return zzcro;
  }
  
  public int getHeaderTextSize()
  {
    return zzcrp;
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
  
  public String getQuery()
  {
    return zzanp;
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return zzaic.isTestDevice(paramContext);
  }
  
  zzad zzdd()
  {
    return zzaic;
  }
  
  public static final class Builder
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.search.SearchAdRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */