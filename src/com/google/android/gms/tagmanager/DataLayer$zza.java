package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class DataLayer$zza
{
  public final String zzaxn;
  public final Object zzcnr;
  
  DataLayer$zza(String paramString, Object paramObject)
  {
    zzaxn = paramString;
    zzcnr = paramObject;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zza)) {}
    do
    {
      return false;
      paramObject = (zza)paramObject;
    } while ((!zzaxn.equals(zzaxn)) || (!zzcnr.equals(zzcnr)));
    return true;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Integer[] { Integer.valueOf(zzaxn.hashCode()), Integer.valueOf(zzcnr.hashCode()) });
  }
  
  public String toString()
  {
    String str1 = zzaxn;
    String str2 = String.valueOf(zzcnr.toString());
    return String.valueOf(str1).length() + 13 + String.valueOf(str2).length() + "Key: " + str1 + " value: " + str2;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.DataLayer.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */