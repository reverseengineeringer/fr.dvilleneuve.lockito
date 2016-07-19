package com.google.android.gms.common.util;

import android.util.Base64;

public final class zzc
{
  public static String zzp(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(paramArrayOfByte, 0);
  }
  
  public static String zzq(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(paramArrayOfByte, 10);
  }
  
  public static String zzr(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(paramArrayOfByte, 11);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.util.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */