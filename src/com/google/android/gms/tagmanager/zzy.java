package com.google.android.gms.tagmanager;

import android.util.Log;

public class zzy
  implements zzbo
{
  private int zzczi = 5;
  
  public void e(String paramString)
  {
    if (zzczi <= 6) {
      Log.e("GoogleTagManager", paramString);
    }
  }
  
  public void setLogLevel(int paramInt)
  {
    zzczi = paramInt;
  }
  
  public void v(String paramString)
  {
    if (zzczi <= 2) {
      Log.v("GoogleTagManager", paramString);
    }
  }
  
  public void zzb(String paramString, Throwable paramThrowable)
  {
    if (zzczi <= 6) {
      Log.e("GoogleTagManager", paramString, paramThrowable);
    }
  }
  
  public void zzcw(String paramString)
  {
    if (zzczi <= 3) {
      Log.d("GoogleTagManager", paramString);
    }
  }
  
  public void zzcx(String paramString)
  {
    if (zzczi <= 4) {
      Log.i("GoogleTagManager", paramString);
    }
  }
  
  public void zzcy(String paramString)
  {
    if (zzczi <= 5) {
      Log.w("GoogleTagManager", paramString);
    }
  }
  
  public void zzd(String paramString, Throwable paramThrowable)
  {
    if (zzczi <= 5) {
      Log.w("GoogleTagManager", paramString, paramThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */