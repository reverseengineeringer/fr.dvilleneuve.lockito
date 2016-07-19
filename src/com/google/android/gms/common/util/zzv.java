package com.google.android.gms.common.util;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzab;
import java.util.Set;

public final class zzv
{
  public static String[] zzb(Scope[] paramArrayOfScope)
  {
    zzab.zzb(paramArrayOfScope, "scopes can't be null.");
    String[] arrayOfString = new String[paramArrayOfScope.length];
    int i = 0;
    while (i < paramArrayOfScope.length)
    {
      arrayOfString[i] = paramArrayOfScope[i].zzaoh();
      i += 1;
    }
    return arrayOfString;
  }
  
  public static String[] zzd(Set<Scope> paramSet)
  {
    zzab.zzb(paramSet, "scopes can't be null.");
    return zzb((Scope[])paramSet.toArray(new Scope[paramSet.size()]));
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.util.zzv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */