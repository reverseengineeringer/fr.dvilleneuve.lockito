package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

class zzdc
{
  static void zza(SharedPreferences.Editor paramEditor)
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      paramEditor.apply();
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        commit();
      }
    }).start();
  }
  
  @SuppressLint({"CommitPrefEdits"})
  static void zzb(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = paramContext.getSharedPreferences(paramString1, 0).edit();
    paramContext.putString(paramString2, paramString3);
    zza(paramContext);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */