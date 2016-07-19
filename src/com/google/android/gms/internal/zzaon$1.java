package com.google.android.gms.internal;

import java.io.IOException;

final class zzaon$1
  extends zzank<Class>
{
  public void zza(zzaor paramzzaor, Class paramClass)
    throws IOException
  {
    if (paramClass == null)
    {
      paramzzaor.r();
      return;
    }
    paramzzaor = String.valueOf(paramClass.getName());
    throw new UnsupportedOperationException(String.valueOf(paramzzaor).length() + 76 + "Attempted to serialize java.lang.Class: " + paramzzaor + ". Forgot to register a type adapter?");
  }
  
  public Class zzo(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */