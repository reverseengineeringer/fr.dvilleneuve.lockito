package com.google.android.gms.internal;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

final class zzanr$zzc
  implements Serializable, WildcardType
{
  private final Type beL;
  private final Type beM;
  
  public zzanr$zzc(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
  {
    if (paramArrayOfType2.length <= 1)
    {
      bool1 = true;
      zzanq.zzbn(bool1);
      if (paramArrayOfType1.length != 1) {
        break label87;
      }
      bool1 = true;
      label27:
      zzanq.zzbn(bool1);
      if (paramArrayOfType2.length != 1) {
        break label97;
      }
      zzanq.zzaa(paramArrayOfType2[0]);
      zzanr.zzj(paramArrayOfType2[0]);
      if (paramArrayOfType1[0] != Object.class) {
        break label92;
      }
    }
    label87:
    label92:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzanq.zzbn(bool1);
      beM = zzanr.zze(paramArrayOfType2[0]);
      beL = Object.class;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label27;
    }
    label97:
    zzanq.zzaa(paramArrayOfType1[0]);
    zzanr.zzj(paramArrayOfType1[0]);
    beM = null;
    beL = zzanr.zze(paramArrayOfType1[0]);
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof WildcardType)) && (zzanr.zza(this, (WildcardType)paramObject));
  }
  
  public Type[] getLowerBounds()
  {
    if (beM != null) {
      return new Type[] { beM };
    }
    return zzanr.beG;
  }
  
  public Type[] getUpperBounds()
  {
    return new Type[] { beL };
  }
  
  public int hashCode()
  {
    if (beM != null) {}
    for (int i = beM.hashCode() + 31;; i = 1) {
      return i ^ beL.hashCode() + 31;
    }
  }
  
  public String toString()
  {
    if (beM != null)
    {
      str = String.valueOf(zzanr.zzg(beM));
      if (str.length() != 0) {
        return "? super ".concat(str);
      }
      return new String("? super ");
    }
    if (beL == Object.class) {
      return "?";
    }
    String str = String.valueOf(zzanr.zzg(beL));
    if (str.length() != 0) {
      return "? extends ".concat(str);
    }
    return new String("? extends ");
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanr.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */