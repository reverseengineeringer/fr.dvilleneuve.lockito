package com.google.android.gms.common.images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.common.internal.zzb;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

final class ImageManager$zzc
  implements Runnable
{
  private final Uri mUri;
  private final ParcelFileDescriptor wr;
  
  public ImageManager$zzc(ImageManager paramImageManager, Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
  {
    mUri = paramUri;
    wr = paramParcelFileDescriptor;
  }
  
  public void run()
  {
    zzb.zzhk("LoadBitmapFromDiskRunnable can't be executed in the main thread");
    boolean bool1 = false;
    boolean bool2 = false;
    Bitmap localBitmap = null;
    CountDownLatch localCountDownLatch = null;
    if (wr != null) {}
    try
    {
      localBitmap = BitmapFactory.decodeFileDescriptor(wr.getFileDescriptor());
      bool1 = bool2;
      String str2;
      Object localObject;
      String str1;
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      try
      {
        for (;;)
        {
          wr.close();
          localCountDownLatch = new CountDownLatch(1);
          ImageManager.zzg(wq).post(new ImageManager.zzf(wq, mUri, localBitmap, bool1, localCountDownLatch));
          try
          {
            localCountDownLatch.await();
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            str1 = String.valueOf(mUri);
            Log.w("ImageManager", String.valueOf(str1).length() + 32 + "Latch interrupted while posting " + str1);
          }
          localOutOfMemoryError = localOutOfMemoryError;
          str2 = String.valueOf(mUri);
          Log.e("ImageManager", String.valueOf(str2).length() + 34 + "OOM while loading bitmap for uri: " + str2, localOutOfMemoryError);
          bool1 = true;
          localObject = localCountDownLatch;
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Log.e("ImageManager", "closed failed", localIOException);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.ImageManager.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */