package com.google.android.gms.common.images;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.gms.common.internal.zzb;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class ImageManager$zzf
  implements Runnable
{
  private final Bitmap mBitmap;
  private final Uri mUri;
  private boolean wt;
  private final CountDownLatch zzalc;
  
  public ImageManager$zzf(ImageManager paramImageManager, Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
  {
    mUri = paramUri;
    mBitmap = paramBitmap;
    wt = paramBoolean;
    zzalc = paramCountDownLatch;
  }
  
  private void zza(ImageManager.ImageReceiver paramImageReceiver, boolean paramBoolean)
  {
    paramImageReceiver = ImageManager.ImageReceiver.zza(paramImageReceiver);
    int j = paramImageReceiver.size();
    int i = 0;
    if (i < j)
    {
      zza localzza = (zza)paramImageReceiver.get(i);
      if (paramBoolean) {
        localzza.zza(ImageManager.zzb(wq), mBitmap, false);
      }
      for (;;)
      {
        if (!(localzza instanceof zza.zzc)) {
          ImageManager.zza(wq).remove(localzza);
        }
        i += 1;
        break;
        ImageManager.zzd(wq).put(mUri, Long.valueOf(SystemClock.elapsedRealtime()));
        localzza.zza(ImageManager.zzb(wq), ImageManager.zzc(wq), false);
      }
    }
  }
  
  public void run()
  {
    zzb.zzhj("OnBitmapLoadedRunnable must be executed in the main thread");
    boolean bool;
    if (mBitmap != null) {
      bool = true;
    }
    while (ImageManager.zzh(wq) != null) {
      if (wt)
      {
        ImageManager.zzh(wq).evictAll();
        System.gc();
        wt = false;
        ImageManager.zzg(wq).post(this);
        return;
        bool = false;
      }
      else if (bool)
      {
        ImageManager.zzh(wq).put(new zza.zza(mUri), mBitmap);
      }
    }
    ??? = (ImageManager.ImageReceiver)ImageManager.zze(wq).remove(mUri);
    if (??? != null) {
      zza((ImageManager.ImageReceiver)???, bool);
    }
    zzalc.countDown();
    synchronized (ImageManager.zzamh())
    {
      ImageManager.zzark().remove(mUri);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.ImageManager.zzf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */