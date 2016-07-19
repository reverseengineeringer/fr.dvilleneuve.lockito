package com.google.firebase.iid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class zze
{
  private static final Object zzamp = new Object();
  private final zzg baS;
  
  zze(zzg paramzzg)
  {
    baS = paramzzg;
  }
  
  @Nullable
  String zzcxc()
  {
    synchronized (zzamp)
    {
      Object localObject2 = baS.zzcxd().getString("topic_operaion_queue", null);
      if (localObject2 != null)
      {
        localObject2 = ((String)localObject2).split(",");
        if ((localObject2.length > 1) && (!TextUtils.isEmpty(localObject2[1])))
        {
          localObject2 = localObject2[1];
          return (String)localObject2;
        }
      }
      return null;
    }
  }
  
  void zzsf(String paramString)
  {
    synchronized (zzamp)
    {
      String str1 = baS.zzcxd().getString("topic_operaion_queue", "");
      String str2 = String.valueOf(",");
      paramString = String.valueOf(str1).length() + 0 + String.valueOf(str2).length() + String.valueOf(paramString).length() + str1 + str2 + paramString;
      baS.zzcxd().edit().putString("topic_operaion_queue", paramString).apply();
      return;
    }
  }
  
  boolean zzsj(String paramString)
  {
    for (;;)
    {
      String str1;
      synchronized (zzamp)
      {
        String str2 = baS.zzcxd().getString("topic_operaion_queue", "");
        str1 = String.valueOf(",");
        String str3 = String.valueOf(paramString);
        if (str3.length() != 0)
        {
          str1 = str1.concat(str3);
          if (!str2.startsWith(str1)) {
            break;
          }
          str1 = String.valueOf(",");
          paramString = String.valueOf(paramString);
          if (paramString.length() != 0)
          {
            paramString = str1.concat(paramString);
            paramString = str2.substring(paramString.length());
            baS.zzcxd().edit().putString("topic_operaion_queue", paramString).apply();
            return true;
          }
        }
        else
        {
          str1 = new String(str1);
        }
      }
      paramString = new String(str1);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.iid.zze
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */