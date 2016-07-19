package com.squareup.picasso;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

class Stats
{
  private static final int BITMAP_DECODE_FINISHED = 3;
  private static final int BITMAP_TRANSFORMED_FINISHED = 4;
  private static final int CACHE_HIT = 1;
  private static final int CACHE_MISS = 2;
  private static final int REQUESTED_COMPLETED = 0;
  private static final String STATS_THREAD_NAME = "Picasso-Stats";
  long averageOriginalBitmapSize;
  long averageTransformedBitmapSize;
  final Cache cache;
  long cacheHits;
  long cacheMisses;
  final Handler handler;
  int originalBitmapCount;
  final HandlerThread statsThread;
  long totalOriginalBitmapSize;
  long totalTransformedBitmapSize;
  int transformedBitmapCount;
  
  Stats(Cache paramCache)
  {
    cache = paramCache;
    statsThread = new HandlerThread("Picasso-Stats", 10);
    statsThread.start();
    handler = new StatsHandler(statsThread.getLooper());
  }
  
  private static long getAverage(int paramInt, long paramLong)
  {
    return paramLong / paramInt;
  }
  
  private void processBitmap(Bitmap paramBitmap, int paramInt)
  {
    int i = Utils.getBitmapBytes(paramBitmap);
    handler.sendMessage(handler.obtainMessage(paramInt, i, 0));
  }
  
  StatsSnapshot createSnapshot()
  {
    try
    {
      StatsSnapshot localStatsSnapshot = new StatsSnapshot(cache.maxSize(), cache.size(), cacheHits, cacheMisses, totalOriginalBitmapSize, totalTransformedBitmapSize, averageOriginalBitmapSize, averageTransformedBitmapSize, originalBitmapCount, transformedBitmapCount, System.currentTimeMillis());
      return localStatsSnapshot;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  void dispatchBitmapDecoded(Bitmap paramBitmap)
  {
    processBitmap(paramBitmap, 3);
  }
  
  void dispatchBitmapTransformed(Bitmap paramBitmap)
  {
    processBitmap(paramBitmap, 4);
  }
  
  void dispatchCacheHit()
  {
    handler.sendEmptyMessage(1);
  }
  
  void dispatchCacheMiss()
  {
    handler.sendEmptyMessage(2);
  }
  
  void performBitmapDecoded(long paramLong)
  {
    originalBitmapCount += 1;
    totalOriginalBitmapSize += paramLong;
    averageOriginalBitmapSize = getAverage(originalBitmapCount, totalOriginalBitmapSize);
  }
  
  void performBitmapTransformed(long paramLong)
  {
    transformedBitmapCount += 1;
    totalTransformedBitmapSize += paramLong;
    averageTransformedBitmapSize = getAverage(originalBitmapCount, totalTransformedBitmapSize);
  }
  
  void performCacheHit()
  {
    cacheHits += 1L;
  }
  
  void performCacheMiss()
  {
    cacheMisses += 1L;
  }
  
  void shutdown()
  {
    statsThread.quit();
  }
  
  private class StatsHandler
    extends Handler
  {
    public StatsHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(final Message paramMessage)
    {
      for (;;)
      {
        synchronized (Stats.this)
        {
          switch (what)
          {
          case 0: 
            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
              public void run()
              {
                throw new AssertionError("Unhandled stats message." + paramMessagewhat);
              }
            });
            return;
          case 1: 
            performCacheHit();
          }
        }
        performCacheMiss();
        continue;
        performBitmapDecoded(arg1);
        continue;
        performBitmapTransformed(arg1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Stats
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */