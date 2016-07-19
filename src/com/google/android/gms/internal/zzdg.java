package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zzir
public class zzdg
{
  @Nullable
  public static zzdi zza(@Nullable zzdk paramzzdk, long paramLong)
  {
    if (paramzzdk == null) {
      return null;
    }
    return paramzzdk.zzc(paramLong);
  }
  
  public static boolean zza(@Nullable zzdk paramzzdk, @Nullable zzdi paramzzdi, long paramLong, String... paramVarArgs)
  {
    if ((paramzzdk == null) || (paramzzdi == null)) {
      return false;
    }
    return paramzzdk.zza(paramzzdi, paramLong, paramVarArgs);
  }
  
  public static boolean zza(@Nullable zzdk paramzzdk, @Nullable zzdi paramzzdi, String... paramVarArgs)
  {
    if ((paramzzdk == null) || (paramzzdi == null)) {
      return false;
    }
    return paramzzdk.zza(paramzzdi, paramVarArgs);
  }
  
  @Nullable
  public static zzdi zzb(@Nullable zzdk paramzzdk)
  {
    if (paramzzdk == null) {
      return null;
    }
    return paramzzdk.zzkg();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzdg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */