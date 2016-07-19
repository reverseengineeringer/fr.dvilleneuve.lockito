package com.squareup.picasso;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StatsSnapshot
{
  private static final String TAG = "Picasso";
  public final long averageOriginalBitmapSize;
  public final long averageTransformedBitmapSize;
  public final long cacheHits;
  public final long cacheMisses;
  public final int maxSize;
  public final int originalBitmapCount;
  public final int size;
  public final long timeStamp;
  public final long totalOriginalBitmapSize;
  public final long totalTransformedBitmapSize;
  public final int transformedBitmapCount;
  
  public StatsSnapshot(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, int paramInt3, int paramInt4, long paramLong7)
  {
    maxSize = paramInt1;
    size = paramInt2;
    cacheHits = paramLong1;
    cacheMisses = paramLong2;
    totalOriginalBitmapSize = paramLong3;
    totalTransformedBitmapSize = paramLong4;
    averageOriginalBitmapSize = paramLong5;
    averageTransformedBitmapSize = paramLong6;
    originalBitmapCount = paramInt3;
    transformedBitmapCount = paramInt4;
    timeStamp = paramLong7;
  }
  
  public void dump()
  {
    StringWriter localStringWriter = new StringWriter();
    dump(new PrintWriter(localStringWriter));
    Log.i("Picasso", localStringWriter.toString());
  }
  
  public void dump(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println("===============BEGIN PICASSO STATS ===============");
    paramPrintWriter.println("Memory Cache Stats");
    paramPrintWriter.print("  Max Cache Size: ");
    paramPrintWriter.println(maxSize);
    paramPrintWriter.print("  Cache Size: ");
    paramPrintWriter.println(size);
    paramPrintWriter.print("  Cache % Full: ");
    paramPrintWriter.println((int)Math.ceil(size / maxSize * 100.0F));
    paramPrintWriter.print("  Cache Hits: ");
    paramPrintWriter.println(cacheHits);
    paramPrintWriter.print("  Cache Misses: ");
    paramPrintWriter.println(cacheMisses);
    paramPrintWriter.println("Bitmap Stats");
    paramPrintWriter.print("  Total Bitmaps Decoded: ");
    paramPrintWriter.println(originalBitmapCount);
    paramPrintWriter.print("  Total Bitmap Size: ");
    paramPrintWriter.println(totalOriginalBitmapSize);
    paramPrintWriter.print("  Total Transformed Bitmaps: ");
    paramPrintWriter.println(transformedBitmapCount);
    paramPrintWriter.print("  Total Transformed Bitmap Size: ");
    paramPrintWriter.println(totalTransformedBitmapSize);
    paramPrintWriter.print("  Average Bitmap Size: ");
    paramPrintWriter.println(averageOriginalBitmapSize);
    paramPrintWriter.print("  Average Transformed Bitmap Size: ");
    paramPrintWriter.println(averageTransformedBitmapSize);
    paramPrintWriter.println("===============END PICASSO STATS ===============");
    paramPrintWriter.flush();
  }
  
  public String toString()
  {
    return "StatsSnapshot{maxSize=" + maxSize + ", size=" + size + ", cacheHits=" + cacheHits + ", cacheMisses=" + cacheMisses + ", totalOriginalBitmapSize=" + totalOriginalBitmapSize + ", totalTransformedBitmapSize=" + totalTransformedBitmapSize + ", averageOriginalBitmapSize=" + averageOriginalBitmapSize + ", averageTransformedBitmapSize=" + averageTransformedBitmapSize + ", originalBitmapCount=" + originalBitmapCount + ", transformedBitmapCount=" + transformedBitmapCount + ", timeStamp=" + timeStamp + '}';
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.StatsSnapshot
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */