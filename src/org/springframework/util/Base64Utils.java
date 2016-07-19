package org.springframework.util;

import android.os.Build.VERSION;
import java.io.IOException;

public class Base64Utils
{
  private static final Boolean froyoOrNewer;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 8) {}
    for (boolean bool = true;; bool = false)
    {
      froyoOrNewer = Boolean.valueOf(bool);
      return;
    }
  }
  
  public static byte[] decode(String paramString)
  {
    if (froyoOrNewer.booleanValue()) {
      return android.util.Base64.decode(paramString, 2);
    }
    try
    {
      paramString = org.springframework.util.support.Base64.decode(paramString);
      return paramString;
    }
    catch (IOException paramString)
    {
      throw new IllegalArgumentException(paramString.getLocalizedMessage());
    }
  }
  
  public static byte[] decode(byte[] paramArrayOfByte)
  {
    if (froyoOrNewer.booleanValue()) {
      return android.util.Base64.decode(paramArrayOfByte, 2);
    }
    try
    {
      paramArrayOfByte = org.springframework.util.support.Base64.decode(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new IllegalArgumentException(paramArrayOfByte.getLocalizedMessage());
    }
  }
  
  public static byte[] encode(byte[] paramArrayOfByte)
  {
    if (froyoOrNewer.booleanValue()) {
      return android.util.Base64.encode(paramArrayOfByte, 2);
    }
    return org.springframework.util.support.Base64.encodeBytesToBytes(paramArrayOfByte);
  }
  
  public static String encodeToString(byte[] paramArrayOfByte)
  {
    if (froyoOrNewer.booleanValue()) {
      return android.util.Base64.encodeToString(paramArrayOfByte, 2);
    }
    return org.springframework.util.support.Base64.encodeBytes(paramArrayOfByte);
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.Base64Utils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */