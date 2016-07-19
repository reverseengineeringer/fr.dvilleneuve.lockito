package io.fabric.sdk.android.services.network;

import java.io.UnsupportedEncodingException;

public class HttpRequest$Base64
{
  private static final byte EQUALS_SIGN = 61;
  private static final String PREFERRED_ENCODING = "US-ASCII";
  private static final byte[] _STANDARD_ALPHABET = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  
  public static String encode(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("US-ASCII");
      paramString = arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        paramString = paramString.getBytes();
      }
    }
    return encodeBytes(paramString);
  }
  
  private static byte[] encode3to4(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
  {
    int k = 0;
    byte[] arrayOfByte = _STANDARD_ALPHABET;
    int i;
    if (paramInt2 > 0)
    {
      i = paramArrayOfByte1[paramInt1] << 24 >>> 8;
      label23:
      if (paramInt2 <= 1) {
        break label104;
      }
    }
    label104:
    for (int j = paramArrayOfByte1[(paramInt1 + 1)] << 24 >>> 16;; j = 0)
    {
      if (paramInt2 > 2) {
        k = paramArrayOfByte1[(paramInt1 + 2)] << 24 >>> 24;
      }
      paramInt1 = j | i | k;
      switch (paramInt2)
      {
      default: 
        return paramArrayOfByte2;
        i = 0;
        break label23;
      }
    }
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = arrayOfByte[(paramInt1 & 0x3F)];
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = 61;
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
  }
  
  public static String encodeBytes(byte[] paramArrayOfByte)
  {
    return encodeBytes(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static String encodeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte = encodeBytesToBytes(paramArrayOfByte, paramInt1, paramInt2);
    try
    {
      String str = new String(paramArrayOfByte, "US-ASCII");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return new String(paramArrayOfByte);
  }
  
  public static byte[] encodeBytesToBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("Cannot serialize a null array.");
    }
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Cannot have negative offset: " + paramInt1);
    }
    if (paramInt2 < 0) {
      throw new IllegalArgumentException("Cannot have length offset: " + paramInt2);
    }
    if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
      throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramArrayOfByte.length) }));
    }
    int j = paramInt2 / 3;
    if (paramInt2 % 3 > 0) {}
    byte[] arrayOfByte;
    for (int i = 4;; i = 0)
    {
      arrayOfByte = new byte[j * 4 + i];
      j = 0;
      i = 0;
      while (j < paramInt2 - 2)
      {
        encode3to4(paramArrayOfByte, j + paramInt1, 3, arrayOfByte, i);
        j += 3;
        i += 4;
      }
    }
    int k = i;
    if (j < paramInt2)
    {
      encode3to4(paramArrayOfByte, j + paramInt1, paramInt2 - j, arrayOfByte, i);
      k = i + 4;
    }
    if (k <= arrayOfByte.length - 1)
    {
      paramArrayOfByte = new byte[k];
      System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, k);
      return paramArrayOfByte;
    }
    return arrayOfByte;
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.Base64
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */