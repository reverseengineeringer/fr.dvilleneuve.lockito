package com.facebook.stetho.common;

public final class StringUtil
{
  public static String removePrefix(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1.startsWith(paramString2)) {
      str = paramString1.substring(paramString2.length());
    }
    return str;
  }
  
  public static String removePrefix(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 != paramString3) {
      return paramString3;
    }
    return removePrefix(paramString1, paramString2);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.StringUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */