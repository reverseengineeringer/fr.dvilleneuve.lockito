package com.google.android.gms.internal;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

final class zzanr$zza
  implements Serializable, GenericArrayType
{
  private final Type beH;
  
  public zzanr$zza(Type paramType)
  {
    beH = zzanr.zze(paramType);
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof GenericArrayType)) && (zzanr.zza(this, (GenericArrayType)paramObject));
  }
  
  public Type getGenericComponentType()
  {
    return beH;
  }
  
  public int hashCode()
  {
    return beH.hashCode();
  }
  
  public String toString()
  {
    return String.valueOf(zzanr.zzg(beH)).concat("[]");
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanr.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */