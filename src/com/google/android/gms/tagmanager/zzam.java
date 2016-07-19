package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import java.io.File;

class zzam
{
  public static int version()
  {
    try
    {
      int i = Integer.parseInt(Build.VERSION.SDK);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      str = String.valueOf(Build.VERSION.SDK);
      if (str.length() == 0) {}
    }
    for (String str = "Invalid version number: ".concat(str);; str = new String("Invalid version number: "))
    {
      zzbn.e(str);
      return 0;
    }
  }
  
  @TargetApi(9)
  static boolean zzet(String paramString)
  {
    if (version() < 9) {
      return false;
    }
    paramString = new File(paramString);
    paramString.setReadable(false, false);
    paramString.setWritable(false, false);
    paramString.setReadable(true, true);
    paramString.setWritable(true, true);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzam
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */