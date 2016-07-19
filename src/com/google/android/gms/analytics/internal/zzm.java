package com.google.android.gms.analytics.internal;

public enum zzm
{
  private zzm() {}
  
  public static zzm zzer(String paramString)
  {
    if ("BATCH_BY_SESSION".equalsIgnoreCase(paramString)) {
      return zzcys;
    }
    if ("BATCH_BY_TIME".equalsIgnoreCase(paramString)) {
      return zzcyt;
    }
    if ("BATCH_BY_BRUTE_FORCE".equalsIgnoreCase(paramString)) {
      return zzcyu;
    }
    if ("BATCH_BY_COUNT".equalsIgnoreCase(paramString)) {
      return zzcyv;
    }
    if ("BATCH_BY_SIZE".equalsIgnoreCase(paramString)) {
      return zzcyw;
    }
    return zzcyr;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */