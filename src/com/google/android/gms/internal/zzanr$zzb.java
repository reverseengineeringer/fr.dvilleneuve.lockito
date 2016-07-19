package com.google.android.gms.internal;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

final class zzanr$zzb
  implements Serializable, ParameterizedType
{
  private final Type beI;
  private final Type beJ;
  private final Type[] beK;
  
  public zzanr$zzb(Type paramType1, Type paramType2, Type... paramVarArgs)
  {
    int i;
    boolean bool;
    if ((paramType2 instanceof Class))
    {
      Class localClass = (Class)paramType2;
      if ((Modifier.isStatic(localClass.getModifiers())) || (localClass.getEnclosingClass() == null))
      {
        i = 1;
        if ((paramType1 == null) && (i == 0)) {
          break label156;
        }
        bool = true;
        label54:
        zzanq.zzbn(bool);
      }
    }
    else
    {
      if (paramType1 != null) {
        break label162;
      }
    }
    label156:
    label162:
    for (paramType1 = null;; paramType1 = zzanr.zze(paramType1))
    {
      beI = paramType1;
      beJ = zzanr.zze(paramType2);
      beK = ((Type[])paramVarArgs.clone());
      i = j;
      while (i < beK.length)
      {
        zzanq.zzaa(beK[i]);
        zzanr.zzj(beK[i]);
        beK[i] = zzanr.zze(beK[i]);
        i += 1;
      }
      i = 0;
      break;
      bool = false;
      break label54;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof ParameterizedType)) && (zzanr.zza(this, (ParameterizedType)paramObject));
  }
  
  public Type[] getActualTypeArguments()
  {
    return (Type[])beK.clone();
  }
  
  public Type getOwnerType()
  {
    return beI;
  }
  
  public Type getRawType()
  {
    return beJ;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(beK) ^ beJ.hashCode() ^ zzanr.zzcn(beI);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder((beK.length + 1) * 30);
    localStringBuilder.append(zzanr.zzg(beJ));
    if (beK.length == 0) {
      return localStringBuilder.toString();
    }
    localStringBuilder.append("<").append(zzanr.zzg(beK[0]));
    int i = 1;
    while (i < beK.length)
    {
      localStringBuilder.append(", ").append(zzanr.zzg(beK[i]));
      i += 1;
    }
    return ">";
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanr.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */