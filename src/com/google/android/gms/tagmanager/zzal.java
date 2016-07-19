package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzal
{
  private final Set<String> awn;
  private final String awo;
  
  public zzal(String paramString, String... paramVarArgs)
  {
    awo = paramString;
    awn = new HashSet(paramVarArgs.length);
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramString = paramVarArgs[i];
      awn.add(paramString);
      i += 1;
    }
  }
  
  public abstract zzai.zza zzav(Map<String, zzai.zza> paramMap);
  
  public abstract boolean zzcac();
  
  public String zzcbl()
  {
    return awo;
  }
  
  public Set<String> zzcbm()
  {
    return awn;
  }
  
  boolean zzf(Set<String> paramSet)
  {
    return paramSet.containsAll(awn);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzal
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */