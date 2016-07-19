package com.google.android.gms.common.images;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.google.android.gms.common.internal.zzb;
import java.util.HashSet;
import java.util.Map;

final class ImageManager$zzd
  implements Runnable
{
  private final zza ws;
  
  public ImageManager$zzd(ImageManager paramImageManager, zza paramzza)
  {
    ws = paramzza;
  }
  
  public void run()
  {
    zzb.zzhj("LoadImageRunnable must be executed on the main thread");
    Object localObject1 = (ImageManager.ImageReceiver)ImageManager.zza(wq).get(ws);
    if (localObject1 != null)
    {
      ImageManager.zza(wq).remove(ws);
      ((ImageManager.ImageReceiver)localObject1).zzc(ws);
    }
    zza.zza localzza = ws.wu;
    if (uri == null)
    {
      ws.zza(ImageManager.zzb(wq), ImageManager.zzc(wq), true);
      return;
    }
    localObject1 = ImageManager.zza(wq, localzza);
    if (localObject1 != null)
    {
      ws.zza(ImageManager.zzb(wq), (Bitmap)localObject1, true);
      return;
    }
    localObject1 = (Long)ImageManager.zzd(wq).get(uri);
    if (localObject1 != null)
    {
      if (SystemClock.elapsedRealtime() - ((Long)localObject1).longValue() < 3600000L)
      {
        ws.zza(ImageManager.zzb(wq), ImageManager.zzc(wq), true);
        return;
      }
      ImageManager.zzd(wq).remove(uri);
    }
    ws.zza(ImageManager.zzb(wq), ImageManager.zzc(wq));
    ??? = (ImageManager.ImageReceiver)ImageManager.zze(wq).get(uri);
    localObject1 = ???;
    if (??? == null)
    {
      localObject1 = new ImageManager.ImageReceiver(wq, uri);
      ImageManager.zze(wq).put(uri, localObject1);
    }
    ((ImageManager.ImageReceiver)localObject1).zzb(ws);
    if (!(ws instanceof zza.zzc)) {
      ImageManager.zza(wq).put(ws, localObject1);
    }
    synchronized (ImageManager.zzamh())
    {
      if (!ImageManager.zzark().contains(uri))
      {
        ImageManager.zzark().add(uri);
        ((ImageManager.ImageReceiver)localObject1).zzarl();
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.ImageManager.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */