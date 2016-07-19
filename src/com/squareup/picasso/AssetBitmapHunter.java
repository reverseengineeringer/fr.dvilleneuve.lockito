package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;

class AssetBitmapHunter
  extends BitmapHunter
{
  private AssetManager assetManager;
  
  public AssetBitmapHunter(Context paramContext, Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction)
  {
    super(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    assetManager = paramContext.getAssets();
  }
  
  Bitmap decode(Request paramRequest)
    throws IOException
  {
    return decodeAsset(uri.toString().substring(ASSET_PREFIX_LENGTH));
  }
  
  /* Error */
  Bitmap decodeAsset(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 55	com/squareup/picasso/AssetBitmapHunter:data	Lcom/squareup/picasso/Request;
    //   6: invokevirtual 59	com/squareup/picasso/Request:hasSize	()Z
    //   9: ifeq +64 -> 73
    //   12: new 61	android/graphics/BitmapFactory$Options
    //   15: dup
    //   16: invokespecial 64	android/graphics/BitmapFactory$Options:<init>	()V
    //   19: astore_3
    //   20: aload_3
    //   21: iconst_1
    //   22: putfield 68	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   25: aconst_null
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 19	com/squareup/picasso/AssetBitmapHunter:assetManager	Landroid/content/res/AssetManager;
    //   31: aload_1
    //   32: invokevirtual 74	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   35: astore 4
    //   37: aload 4
    //   39: astore_2
    //   40: aload 4
    //   42: aconst_null
    //   43: aload_3
    //   44: invokestatic 80	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   47: pop
    //   48: aload 4
    //   50: invokestatic 86	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   53: aload_0
    //   54: getfield 55	com/squareup/picasso/AssetBitmapHunter:data	Lcom/squareup/picasso/Request;
    //   57: getfield 89	com/squareup/picasso/Request:targetWidth	I
    //   60: aload_0
    //   61: getfield 55	com/squareup/picasso/AssetBitmapHunter:data	Lcom/squareup/picasso/Request;
    //   64: getfield 92	com/squareup/picasso/Request:targetHeight	I
    //   67: aload_3
    //   68: invokestatic 96	com/squareup/picasso/AssetBitmapHunter:calculateInSampleSize	(IILandroid/graphics/BitmapFactory$Options;)V
    //   71: aload_3
    //   72: astore_2
    //   73: aload_0
    //   74: getfield 19	com/squareup/picasso/AssetBitmapHunter:assetManager	Landroid/content/res/AssetManager;
    //   77: aload_1
    //   78: invokevirtual 74	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   81: astore_1
    //   82: aload_1
    //   83: aconst_null
    //   84: aload_2
    //   85: invokestatic 80	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   88: astore_2
    //   89: aload_1
    //   90: invokestatic 86	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   93: aload_2
    //   94: areturn
    //   95: astore_1
    //   96: aload_2
    //   97: invokestatic 86	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   100: aload_1
    //   101: athrow
    //   102: astore_2
    //   103: aload_1
    //   104: invokestatic 86	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   107: aload_2
    //   108: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	this	AssetBitmapHunter
    //   0	109	1	paramString	String
    //   1	96	2	localObject1	Object
    //   102	6	2	localObject2	Object
    //   19	53	3	localOptions	android.graphics.BitmapFactory.Options
    //   35	14	4	localInputStream	java.io.InputStream
    // Exception table:
    //   from	to	target	type
    //   27	37	95	finally
    //   40	48	95	finally
    //   82	89	102	finally
  }
  
  Picasso.LoadedFrom getLoadedFrom()
  {
    return Picasso.LoadedFrom.DISK;
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.AssetBitmapHunter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */