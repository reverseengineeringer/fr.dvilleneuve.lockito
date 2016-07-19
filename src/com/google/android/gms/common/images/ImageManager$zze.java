package com.google.android.gms.common.images;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

@TargetApi(14)
final class ImageManager$zze
  implements ComponentCallbacks2
{
  private final ImageManager.zzb wk;
  
  public ImageManager$zze(ImageManager.zzb paramzzb)
  {
    wk = paramzzb;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public void onLowMemory()
  {
    wk.evictAll();
  }
  
  public void onTrimMemory(int paramInt)
  {
    if (paramInt >= 60) {
      wk.evictAll();
    }
    while (paramInt < 20) {
      return;
    }
    wk.trimToSize(wk.size() / 2);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.ImageManager.zze
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */