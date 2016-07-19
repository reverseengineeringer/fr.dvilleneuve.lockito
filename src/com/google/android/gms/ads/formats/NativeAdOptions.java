package com.google.android.gms.ads.formats;

import com.google.android.gms.internal.zzir;
import java.lang.annotation.Annotation;

@zzir
public final class NativeAdOptions
{
  public static final int ADCHOICES_BOTTOM_LEFT = 3;
  public static final int ADCHOICES_BOTTOM_RIGHT = 2;
  public static final int ADCHOICES_TOP_LEFT = 0;
  public static final int ADCHOICES_TOP_RIGHT = 1;
  public static final int ORIENTATION_ANY = 0;
  public static final int ORIENTATION_LANDSCAPE = 2;
  public static final int ORIENTATION_PORTRAIT = 1;
  private final boolean zzaiv;
  private final int zzaiw;
  private final boolean zzaix;
  private final int zzaiy;
  
  private NativeAdOptions(Builder paramBuilder)
  {
    zzaiv = Builder.zza(paramBuilder);
    zzaiw = Builder.zzb(paramBuilder);
    zzaix = Builder.zzc(paramBuilder);
    zzaiy = Builder.zzd(paramBuilder);
  }
  
  public int getAdChoicesPlacement()
  {
    return zzaiy;
  }
  
  public int getImageOrientation()
  {
    return zzaiw;
  }
  
  public boolean shouldRequestMultipleImages()
  {
    return zzaix;
  }
  
  public boolean shouldReturnUrlsForImageAssets()
  {
    return zzaiv;
  }
  
  public static @interface AdChoicesPlacement {}
  
  public static final class Builder
  {
    private boolean zzaiv = false;
    private int zzaiw = 0;
    private boolean zzaix = false;
    private int zzaiy = 1;
    
    public NativeAdOptions build()
    {
      return new NativeAdOptions(this, null);
    }
    
    public Builder setAdChoicesPlacement(@NativeAdOptions.AdChoicesPlacement int paramInt)
    {
      zzaiy = paramInt;
      return this;
    }
    
    public Builder setImageOrientation(int paramInt)
    {
      zzaiw = paramInt;
      return this;
    }
    
    public Builder setRequestMultipleImages(boolean paramBoolean)
    {
      zzaix = paramBoolean;
      return this;
    }
    
    public Builder setReturnUrlsForImageAssets(boolean paramBoolean)
    {
      zzaiv = paramBoolean;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.formats.NativeAdOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */