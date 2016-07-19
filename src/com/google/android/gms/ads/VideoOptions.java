package com.google.android.gms.ads;

import com.google.android.gms.internal.zzir;

@zzir
public final class VideoOptions
{
  private final boolean zzaio;
  
  private VideoOptions(Builder paramBuilder)
  {
    zzaio = Builder.zza(paramBuilder);
  }
  
  public boolean getStartMuted()
  {
    return zzaio;
  }
  
  public static final class Builder
  {
    private boolean zzaio = false;
    
    public VideoOptions build()
    {
      return new VideoOptions(this, null);
    }
    
    public Builder setStartMuted(boolean paramBoolean)
    {
      zzaio = paramBoolean;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.VideoOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */