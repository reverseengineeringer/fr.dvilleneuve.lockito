package com.google.android.gms.analytics.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class zzah$zza
{
  private int ai;
  private ByteArrayOutputStream aj = new ByteArrayOutputStream();
  
  public zzah$zza(zzah paramzzah) {}
  
  public byte[] getPayload()
  {
    return aj.toByteArray();
  }
  
  public int zzadm()
  {
    return ai;
  }
  
  public boolean zzj(zzab paramzzab)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramzzab);
    if (ai + 1 > ak.zzyy().zzabo()) {
      return false;
    }
    Object localObject = ak.zza(paramzzab, false);
    if (localObject == null)
    {
      ak.zzyx().zza(paramzzab, "Error formatting hit");
      return true;
    }
    localObject = ((String)localObject).getBytes();
    int j = localObject.length;
    if (j > ak.zzyy().zzabg())
    {
      ak.zzyx().zza(paramzzab, "Hit size exceeds the maximum size limit");
      return true;
    }
    int i = j;
    if (aj.size() > 0) {
      i = j + 1;
    }
    if (i + aj.size() > ak.zzyy().zzabi()) {
      return false;
    }
    try
    {
      if (aj.size() > 0) {
        aj.write(zzah.zzadl());
      }
      aj.write((byte[])localObject);
      ai += 1;
      return true;
    }
    catch (IOException paramzzab)
    {
      ak.zze("Failed to write payload when batching hits", paramzzab);
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzah.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */