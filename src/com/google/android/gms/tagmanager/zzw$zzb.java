package com.google.android.gms.tagmanager;

import java.util.Arrays;

class zzw$zzb
{
  final byte[] avX;
  final String zzaxn;
  
  zzw$zzb(String paramString, byte[] paramArrayOfByte)
  {
    zzaxn = paramString;
    avX = paramArrayOfByte;
  }
  
  public String toString()
  {
    String str = zzaxn;
    int i = Arrays.hashCode(avX);
    return String.valueOf(str).length() + 54 + "KeyAndSerialized: key = " + str + " serialized hash = " + i;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzw.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */