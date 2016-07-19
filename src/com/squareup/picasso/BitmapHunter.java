package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

abstract class BitmapHunter
  implements Runnable
{
  private static final String ANDROID_ASSET = "android_asset";
  protected static final int ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
  private static final Object DECODE_LOCK = new Object();
  final List<Action> actions;
  final Cache cache;
  final Request data;
  final Dispatcher dispatcher;
  Exception exception;
  int exifRotation;
  Future<?> future;
  final String key;
  Picasso.LoadedFrom loadedFrom;
  final Picasso picasso;
  Bitmap result;
  final boolean skipMemoryCache;
  final Stats stats;
  
  BitmapHunter(Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction)
  {
    picasso = paramPicasso;
    dispatcher = paramDispatcher;
    cache = paramCache;
    stats = paramStats;
    key = paramAction.getKey();
    data = paramAction.getData();
    skipMemoryCache = skipCache;
    actions = new ArrayList(4);
    attach(paramAction);
  }
  
  static Bitmap applyCustomTransformations(List<Transformation> paramList, Bitmap paramBitmap)
  {
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      Transformation localTransformation = (Transformation)paramList.get(i);
      Bitmap localBitmap = localTransformation.transform(paramBitmap);
      if (localBitmap == null)
      {
        paramBitmap = new StringBuilder().append("Transformation ").append(localTransformation.key()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
        paramList = paramList.iterator();
        while (paramList.hasNext()) {
          paramBitmap.append(((Transformation)paramList.next()).key()).append('\n');
        }
        throw new NullPointerException(paramBitmap.toString());
      }
      if ((localBitmap == paramBitmap) && (paramBitmap.isRecycled())) {
        throw new IllegalStateException("Transformation " + localTransformation.key() + " returned input Bitmap but recycled it.");
      }
      if ((localBitmap != paramBitmap) && (!paramBitmap.isRecycled())) {
        throw new IllegalStateException("Transformation " + localTransformation.key() + " mutated input Bitmap but failed to recycle the original.");
      }
      paramBitmap = localBitmap;
      i += 1;
    }
    return paramBitmap;
  }
  
  static void calculateInSampleSize(int paramInt1, int paramInt2, BitmapFactory.Options paramOptions)
  {
    int k = outHeight;
    int j = outWidth;
    int i = 1;
    if ((k > paramInt2) || (j > paramInt1))
    {
      i = Math.round(k / paramInt2);
      paramInt1 = Math.round(j / paramInt1);
      if (i >= paramInt1) {
        break label62;
      }
    }
    for (;;)
    {
      inSampleSize = i;
      inJustDecodeBounds = false;
      return;
      label62:
      i = paramInt1;
    }
  }
  
  static BitmapHunter forRequest(Context paramContext, Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction, Downloader paramDownloader)
  {
    if (getDataresourceId != 0) {
      return new ResourceBitmapHunter(paramContext, paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    }
    Uri localUri = getDatauri;
    String str = localUri.getScheme();
    if ("content".equals(str))
    {
      if ((ContactsContract.Contacts.CONTENT_URI.getHost().equals(localUri.getHost())) && (!localUri.getPathSegments().contains("photo"))) {
        return new ContactsPhotoBitmapHunter(paramContext, paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
      }
      return new ContentProviderBitmapHunter(paramContext, paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    }
    if ("file".equals(str))
    {
      if ("android_asset".equals(localUri.getPathSegments().get(0))) {
        return new AssetBitmapHunter(paramContext, paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
      }
      return new FileBitmapHunter(paramContext, paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    }
    if ("android.resource".equals(str)) {
      return new ResourceBitmapHunter(paramContext, paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    }
    return new NetworkBitmapHunter(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction, paramDownloader);
  }
  
  static Bitmap transformResult(Request paramRequest, Bitmap paramBitmap, int paramInt)
  {
    int i4 = paramBitmap.getWidth();
    int i5 = paramBitmap.getHeight();
    int i6 = 0;
    int i2 = 0;
    int i7 = 0;
    int i3 = 0;
    int j = i4;
    int i = i5;
    Object localObject = new Matrix();
    int i1 = i6;
    int k = i7;
    int m = j;
    int n = i;
    int i8;
    int i9;
    float f1;
    float f2;
    float f3;
    if (paramRequest.needsMatrixTransform())
    {
      i8 = targetWidth;
      i9 = targetHeight;
      f1 = rotationDegrees;
      if (f1 != 0.0F)
      {
        if (!hasRotationPivot) {
          break label239;
        }
        ((Matrix)localObject).setRotate(f1, rotationPivotX, rotationPivotY);
      }
      if (!centerCrop) {
        break label283;
      }
      f2 = i8 / i4;
      f3 = i9 / i5;
      if (f2 <= f3) {
        break label248;
      }
      f1 = f2;
      i = (int)Math.ceil(i5 * (f3 / f2));
      k = (i5 - i) / 2;
      m = j;
      j = i2;
      label177:
      ((Matrix)localObject).preScale(f1, f1);
      n = i;
      i1 = j;
    }
    for (;;)
    {
      if (paramInt != 0) {
        ((Matrix)localObject).preRotate(paramInt);
      }
      localObject = Bitmap.createBitmap(paramBitmap, i1, k, m, n, (Matrix)localObject, true);
      paramRequest = paramBitmap;
      if (localObject != paramBitmap)
      {
        paramBitmap.recycle();
        paramRequest = (Request)localObject;
      }
      return paramRequest;
      label239:
      ((Matrix)localObject).setRotate(f1);
      break;
      label248:
      f1 = f3;
      m = (int)Math.ceil(i4 * (f2 / f3));
      j = (i4 - m) / 2;
      k = i3;
      break label177;
      label283:
      if (centerInside)
      {
        f1 = i8 / i4;
        f2 = i9 / i5;
        if (f1 < f2) {}
        for (;;)
        {
          ((Matrix)localObject).preScale(f1, f1);
          i1 = i6;
          k = i7;
          m = j;
          n = i;
          break;
          f1 = f2;
        }
      }
      i1 = i6;
      k = i7;
      m = j;
      n = i;
      if (i8 != 0)
      {
        i1 = i6;
        k = i7;
        m = j;
        n = i;
        if (i9 != 0) {
          if (i8 == i4)
          {
            i1 = i6;
            k = i7;
            m = j;
            n = i;
            if (i9 == i5) {}
          }
          else
          {
            ((Matrix)localObject).preScale(i8 / i4, i9 / i5);
            i1 = i6;
            k = i7;
            m = j;
            n = i;
          }
        }
      }
    }
  }
  
  void attach(Action paramAction)
  {
    actions.add(paramAction);
  }
  
  boolean cancel()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (actions.isEmpty())
    {
      bool1 = bool2;
      if (future != null)
      {
        bool1 = bool2;
        if (future.cancel(false)) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  abstract Bitmap decode(Request paramRequest)
    throws IOException;
  
  void detach(Action paramAction)
  {
    actions.remove(paramAction);
  }
  
  List<Action> getActions()
  {
    return actions;
  }
  
  Request getData()
  {
    return data;
  }
  
  Exception getException()
  {
    return exception;
  }
  
  String getKey()
  {
    return key;
  }
  
  Picasso.LoadedFrom getLoadedFrom()
  {
    return loadedFrom;
  }
  
  Bitmap getResult()
  {
    return result;
  }
  
  Bitmap hunt()
    throws IOException
  {
    if (!skipMemoryCache)
    {
      localObject1 = cache.get(key);
      if (localObject1 != null)
      {
        stats.dispatchCacheHit();
        loadedFrom = Picasso.LoadedFrom.MEMORY;
        return (Bitmap)localObject1;
      }
    }
    Object localObject3 = decode(data);
    Object localObject1 = localObject3;
    if (localObject3 != null)
    {
      stats.dispatchBitmapDecoded((Bitmap)localObject3);
      localObject1 = localObject3;
      if (!data.needsTransformation()) {}
    }
    synchronized (DECODE_LOCK)
    {
      if (!data.needsMatrixTransform())
      {
        localObject1 = localObject3;
        if (exifRotation == 0) {}
      }
      else
      {
        localObject1 = transformResult(data, (Bitmap)localObject3, exifRotation);
      }
      localObject3 = localObject1;
      if (data.hasCustomTransformations()) {
        localObject3 = applyCustomTransformations(data.transformations, (Bitmap)localObject1);
      }
      stats.dispatchBitmapTransformed((Bitmap)localObject3);
      localObject1 = localObject3;
      return (Bitmap)localObject1;
    }
  }
  
  boolean isCancelled()
  {
    return (future != null) && (future.isCancelled());
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: invokestatic 410	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   3: new 116	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   10: ldc_w 412
    //   13: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: aload_0
    //   17: getfield 81	com/squareup/picasso/BitmapHunter:data	Lcom/squareup/picasso/Request;
    //   20: invokevirtual 415	com/squareup/picasso/Request:getName	()Ljava/lang/String;
    //   23: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokevirtual 418	java/lang/Thread:setName	(Ljava/lang/String;)V
    //   32: aload_0
    //   33: aload_0
    //   34: invokevirtual 420	com/squareup/picasso/BitmapHunter:hunt	()Landroid/graphics/Bitmap;
    //   37: putfield 359	com/squareup/picasso/BitmapHunter:result	Landroid/graphics/Bitmap;
    //   40: aload_0
    //   41: getfield 359	com/squareup/picasso/BitmapHunter:result	Landroid/graphics/Bitmap;
    //   44: ifnonnull +21 -> 65
    //   47: aload_0
    //   48: getfield 63	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   51: aload_0
    //   52: invokevirtual 426	com/squareup/picasso/Dispatcher:dispatchFailed	(Lcom/squareup/picasso/BitmapHunter;)V
    //   55: invokestatic 410	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   58: ldc_w 428
    //   61: invokevirtual 418	java/lang/Thread:setName	(Ljava/lang/String;)V
    //   64: return
    //   65: aload_0
    //   66: getfield 63	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   69: aload_0
    //   70: invokevirtual 431	com/squareup/picasso/Dispatcher:dispatchComplete	(Lcom/squareup/picasso/BitmapHunter;)V
    //   73: goto -18 -> 55
    //   76: astore_1
    //   77: aload_0
    //   78: aload_1
    //   79: putfield 351	com/squareup/picasso/BitmapHunter:exception	Ljava/lang/Exception;
    //   82: aload_0
    //   83: getfield 63	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   86: aload_0
    //   87: invokevirtual 434	com/squareup/picasso/Dispatcher:dispatchRetry	(Lcom/squareup/picasso/BitmapHunter;)V
    //   90: invokestatic 410	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   93: ldc_w 428
    //   96: invokevirtual 418	java/lang/Thread:setName	(Ljava/lang/String;)V
    //   99: return
    //   100: astore_1
    //   101: invokestatic 410	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   104: ldc_w 428
    //   107: invokevirtual 418	java/lang/Thread:setName	(Ljava/lang/String;)V
    //   110: aload_1
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	this	BitmapHunter
    //   76	3	1	localIOException	IOException
    //   100	11	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	55	76	java/io/IOException
    //   65	73	76	java/io/IOException
    //   0	55	100	finally
    //   65	73	100	finally
    //   77	90	100	finally
  }
  
  protected void setExifRotation(int paramInt)
  {
    exifRotation = paramInt;
  }
  
  boolean shouldRetry(boolean paramBoolean, NetworkInfo paramNetworkInfo)
  {
    return false;
  }
  
  boolean shouldSkipMemoryCache()
  {
    return skipMemoryCache;
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.BitmapHunter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */