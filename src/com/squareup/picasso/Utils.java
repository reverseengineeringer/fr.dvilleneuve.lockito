package com.squareup.picasso;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.System;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadFactory;

final class Utils
{
  static final int DEFAULT_CONNECT_TIMEOUT = 15000;
  static final int DEFAULT_READ_TIMEOUT = 20000;
  private static final int KEY_PADDING = 50;
  private static final int MAX_DISK_CACHE_SIZE = 52428800;
  private static final int MAX_MEM_CACHE_SIZE = 31457280;
  private static final int MIN_DISK_CACHE_SIZE = 5242880;
  private static final String PICASSO_CACHE = "picasso-cache";
  static final String THREAD_IDLE_NAME = "Picasso-Idle";
  static final String THREAD_PREFIX = "Picasso-";
  
  static long calculateDiskCacheSize(File paramFile)
  {
    long l1 = 5242880L;
    try
    {
      paramFile = new StatFs(paramFile.getAbsolutePath());
      long l2 = paramFile.getBlockCount() * paramFile.getBlockSize() / 50L;
      l1 = l2;
    }
    catch (IllegalArgumentException paramFile)
    {
      for (;;) {}
    }
    return Math.max(Math.min(l1, 52428800L), 5242880L);
  }
  
  static int calculateMemoryCacheSize(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    if ((getApplicationInfoflags & 0x100000) != 0) {}
    for (int i = 1;; i = 0)
    {
      int k = localActivityManager.getMemoryClass();
      int j = k;
      if (i != 0)
      {
        j = k;
        if (Build.VERSION.SDK_INT >= 11) {
          j = ActivityManagerHoneycomb.getLargeMemoryClass(localActivityManager);
        }
      }
      return Math.min(1048576 * j / 7, 31457280);
    }
  }
  
  static void checkNotMain()
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
      throw new IllegalStateException("Method call should not happen from the main thread.");
    }
  }
  
  static void closeQuietly(InputStream paramInputStream)
  {
    if (paramInputStream == null) {
      return;
    }
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream) {}
  }
  
  static File createDefaultCacheDir(Context paramContext)
  {
    paramContext = new File(paramContext.getApplicationContext().getCacheDir(), "picasso-cache");
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  static Downloader createDefaultDownloader(Context paramContext)
  {
    try
    {
      Class.forName("com.squareup.okhttp.OkHttpClient");
      Downloader localDownloader = OkHttpLoaderCreator.create(paramContext);
      return localDownloader;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return new UrlConnectionDownloader(paramContext);
  }
  
  static String createKey(Request paramRequest)
  {
    StringBuilder localStringBuilder;
    if (uri != null)
    {
      String str = uri.toString();
      localStringBuilder = new StringBuilder(str.length() + 50);
      localStringBuilder.append(str);
      localStringBuilder.append('\n');
      if (rotationDegrees != 0.0F)
      {
        localStringBuilder.append("rotation:").append(rotationDegrees);
        if (hasRotationPivot) {
          localStringBuilder.append('@').append(rotationPivotX).append('x').append(rotationPivotY);
        }
        localStringBuilder.append('\n');
      }
      if (targetWidth != 0)
      {
        localStringBuilder.append("resize:").append(targetWidth).append('x').append(targetHeight);
        localStringBuilder.append('\n');
      }
      if (!centerCrop) {
        break label247;
      }
      localStringBuilder.append("centerCrop\n");
    }
    for (;;)
    {
      if (transformations == null) {
        break label265;
      }
      int i = 0;
      int j = transformations.size();
      while (i < j)
      {
        localStringBuilder.append(((Transformation)transformations.get(i)).key());
        localStringBuilder.append('\n');
        i += 1;
      }
      localStringBuilder = new StringBuilder(50);
      localStringBuilder.append(resourceId);
      break;
      label247:
      if (centerInside) {
        localStringBuilder.append("centerInside\n");
      }
    }
    label265:
    return localStringBuilder.toString();
  }
  
  static int getBitmapBytes(Bitmap paramBitmap)
  {
    if (Build.VERSION.SDK_INT >= 12) {}
    for (int i = BitmapHoneycombMR1.getByteCount(paramBitmap); i < 0; i = paramBitmap.getRowBytes() * paramBitmap.getHeight()) {
      throw new IllegalStateException("Negative size: " + paramBitmap);
    }
    return i;
  }
  
  static boolean hasPermission(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  static boolean isAirplaneModeOn(Context paramContext)
  {
    boolean bool = false;
    if (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0) {
      bool = true;
    }
    return bool;
  }
  
  static boolean parseResponseSourceHeader(String paramString)
  {
    boolean bool = true;
    if (paramString == null) {}
    do
    {
      return false;
      paramString = paramString.split(" ", 2);
      if ("CACHE".equals(paramString[0])) {
        return true;
      }
    } while (paramString.length == 1);
    try
    {
      if ("CONDITIONAL_CACHE".equals(paramString[0]))
      {
        int i = Integer.parseInt(paramString[1]);
        if (i != 304) {}
      }
      for (;;)
      {
        return bool;
        bool = false;
      }
      return false;
    }
    catch (NumberFormatException paramString) {}
  }
  
  @TargetApi(11)
  private static class ActivityManagerHoneycomb
  {
    static int getLargeMemoryClass(ActivityManager paramActivityManager)
    {
      return paramActivityManager.getLargeMemoryClass();
    }
  }
  
  @TargetApi(12)
  private static class BitmapHoneycombMR1
  {
    static int getByteCount(Bitmap paramBitmap)
    {
      return paramBitmap.getByteCount();
    }
  }
  
  private static class OkHttpLoaderCreator
  {
    static Downloader create(Context paramContext)
    {
      return new OkHttpDownloader(paramContext);
    }
  }
  
  private static class PicassoThread
    extends Thread
  {
    public PicassoThread(Runnable paramRunnable)
    {
      super();
    }
    
    public void run()
    {
      Process.setThreadPriority(10);
      super.run();
    }
  }
  
  static class PicassoThreadFactory
    implements ThreadFactory
  {
    public Thread newThread(Runnable paramRunnable)
    {
      return new Utils.PicassoThread(paramRunnable);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Utils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */