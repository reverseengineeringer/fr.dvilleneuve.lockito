package com.google.android.gms.analytics.internal;

public abstract class zzd
  extends zzc
{
  private boolean zzcwt;
  
  protected zzd(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public void initialize()
  {
    zzwv();
    zzcwt = true;
  }
  
  public boolean isInitialized()
  {
    return zzcwt;
  }
  
  protected abstract void zzwv();
  
  protected void zzzg()
  {
    if (!isInitialized()) {
      throw new IllegalStateException("Not initialized");
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */