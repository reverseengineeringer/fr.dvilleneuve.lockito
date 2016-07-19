package com.google.android.gms.common.images;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzrh;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object wf = new Object();
  private static HashSet<Uri> wg = new HashSet();
  private static ImageManager wh;
  private static ImageManager wi;
  private final Context mContext;
  private final Handler mHandler;
  private final ExecutorService wj;
  private final zzb wk;
  private final zzrh wl;
  private final Map<zza, ImageReceiver> wm;
  private final Map<Uri, ImageReceiver> wn;
  private final Map<Uri, Long> wo;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    mContext = paramContext.getApplicationContext();
    mHandler = new Handler(Looper.getMainLooper());
    wj = Executors.newFixedThreadPool(4);
    if (paramBoolean)
    {
      wk = new zzb(mContext);
      if (zzs.zzavm()) {
        zzarj();
      }
    }
    for (;;)
    {
      wl = new zzrh();
      wm = new HashMap();
      wn = new HashMap();
      wo = new HashMap();
      return;
      wk = null;
    }
  }
  
  public static ImageManager create(Context paramContext)
  {
    return zzg(paramContext, false);
  }
  
  private Bitmap zza(zza.zza paramzza)
  {
    if (wk == null) {
      return null;
    }
    return (Bitmap)wk.get(paramzza);
  }
  
  @TargetApi(14)
  private void zzarj()
  {
    mContext.registerComponentCallbacks(new zze(wk));
  }
  
  public static ImageManager zzg(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (wi == null) {
        wi = new ImageManager(paramContext, true);
      }
      return wi;
    }
    if (wh == null) {
      wh = new ImageManager(paramContext, false);
    }
    return wh;
  }
  
  public void loadImage(ImageView paramImageView, int paramInt)
  {
    zza(new zza.zzb(paramImageView, paramInt));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri)
  {
    zza(new zza.zzb(paramImageView, paramUri));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    paramImageView = new zza.zzb(paramImageView, paramUri);
    paramImageView.zzfu(paramInt);
    zza(paramImageView);
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    zza(new zza.zzc(paramOnImageLoadedListener, paramUri));
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    paramOnImageLoadedListener = new zza.zzc(paramOnImageLoadedListener, paramUri);
    paramOnImageLoadedListener.zzfu(paramInt);
    zza(paramOnImageLoadedListener);
  }
  
  public void zza(zza paramzza)
  {
    zzb.zzhj("ImageManager.loadImage() must be called in the main thread");
    new zzd(paramzza).run();
  }
  
  @KeepName
  private final class ImageReceiver
    extends ResultReceiver
  {
    private final Uri mUri;
    private final ArrayList<zza> wp;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      mUri = paramUri;
      wp = new ArrayList();
    }
    
    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.zzf(ImageManager.this).execute(new ImageManager.zzc(ImageManager.this, mUri, paramBundle));
    }
    
    public void zzarl()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.zzb(ImageManager.this).sendBroadcast(localIntent);
    }
    
    public void zzb(zza paramzza)
    {
      zzb.zzhj("ImageReceiver.addImageRequest() must be called in the main thread");
      wp.add(paramzza);
    }
    
    public void zzc(zza paramzza)
    {
      zzb.zzhj("ImageReceiver.removeImageRequest() must be called in the main thread");
      wp.remove(paramzza);
    }
  }
  
  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
  
  @TargetApi(11)
  private static final class zza
  {
    static int zza(ActivityManager paramActivityManager)
    {
      return paramActivityManager.getLargeMemoryClass();
    }
  }
  
  private static final class zzb
    extends LruCache<zza.zza, Bitmap>
  {
    public zzb(Context paramContext)
    {
      super();
    }
    
    @TargetApi(11)
    private static int zzcc(Context paramContext)
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      if ((getApplicationInfoflags & 0x100000) != 0)
      {
        i = 1;
        if ((i == 0) || (!zzs.zzavj())) {
          break label55;
        }
      }
      label55:
      for (int i = ImageManager.zza.zza(localActivityManager);; i = localActivityManager.getMemoryClass())
      {
        return (int)(i * 1048576 * 0.33F);
        i = 0;
        break;
      }
    }
    
    protected int zza(zza.zza paramzza, Bitmap paramBitmap)
    {
      return paramBitmap.getHeight() * paramBitmap.getRowBytes();
    }
    
    protected void zza(boolean paramBoolean, zza.zza paramzza, Bitmap paramBitmap1, Bitmap paramBitmap2)
    {
      super.entryRemoved(paramBoolean, paramzza, paramBitmap1, paramBitmap2);
    }
  }
  
  private final class zzc
    implements Runnable
  {
    private final Uri mUri;
    private final ParcelFileDescriptor wr;
    
    public zzc(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
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
            ImageManager.zzg(ImageManager.this).post(new ImageManager.zzf(ImageManager.this, mUri, localBitmap, bool1, localCountDownLatch));
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
  
  private final class zzd
    implements Runnable
  {
    private final zza ws;
    
    public zzd(zza paramzza)
    {
      ws = paramzza;
    }
    
    public void run()
    {
      zzb.zzhj("LoadImageRunnable must be executed on the main thread");
      Object localObject1 = (ImageManager.ImageReceiver)ImageManager.zza(ImageManager.this).get(ws);
      if (localObject1 != null)
      {
        ImageManager.zza(ImageManager.this).remove(ws);
        ((ImageManager.ImageReceiver)localObject1).zzc(ws);
      }
      zza.zza localzza = ws.wu;
      if (uri == null)
      {
        ws.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
        return;
      }
      localObject1 = ImageManager.zza(ImageManager.this, localzza);
      if (localObject1 != null)
      {
        ws.zza(ImageManager.zzb(ImageManager.this), (Bitmap)localObject1, true);
        return;
      }
      localObject1 = (Long)ImageManager.zzd(ImageManager.this).get(uri);
      if (localObject1 != null)
      {
        if (SystemClock.elapsedRealtime() - ((Long)localObject1).longValue() < 3600000L)
        {
          ws.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
          return;
        }
        ImageManager.zzd(ImageManager.this).remove(uri);
      }
      ws.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this));
      ??? = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).get(uri);
      localObject1 = ???;
      if (??? == null)
      {
        localObject1 = new ImageManager.ImageReceiver(ImageManager.this, uri);
        ImageManager.zze(ImageManager.this).put(uri, localObject1);
      }
      ((ImageManager.ImageReceiver)localObject1).zzb(ws);
      if (!(ws instanceof zza.zzc)) {
        ImageManager.zza(ImageManager.this).put(ws, localObject1);
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
  
  @TargetApi(14)
  private static final class zze
    implements ComponentCallbacks2
  {
    private final ImageManager.zzb wk;
    
    public zze(ImageManager.zzb paramzzb)
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
  
  private final class zzf
    implements Runnable
  {
    private final Bitmap mBitmap;
    private final Uri mUri;
    private boolean wt;
    private final CountDownLatch zzalc;
    
    public zzf(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
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
          localzza.zza(ImageManager.zzb(ImageManager.this), mBitmap, false);
        }
        for (;;)
        {
          if (!(localzza instanceof zza.zzc)) {
            ImageManager.zza(ImageManager.this).remove(localzza);
          }
          i += 1;
          break;
          ImageManager.zzd(ImageManager.this).put(mUri, Long.valueOf(SystemClock.elapsedRealtime()));
          localzza.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), false);
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
      while (ImageManager.zzh(ImageManager.this) != null) {
        if (wt)
        {
          ImageManager.zzh(ImageManager.this).evictAll();
          System.gc();
          wt = false;
          ImageManager.zzg(ImageManager.this).post(this);
          return;
          bool = false;
        }
        else if (bool)
        {
          ImageManager.zzh(ImageManager.this).put(new zza.zza(mUri), mBitmap);
        }
      }
      ??? = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).remove(mUri);
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.ImageManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */