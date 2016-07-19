package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.IOException;

class ContentStreamBitmapHunter
  extends BitmapHunter
{
  final Context context;
  
  ContentStreamBitmapHunter(Context paramContext, Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction)
  {
    super(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    context = paramContext;
  }
  
  Bitmap decode(Request paramRequest)
    throws IOException
  {
    return decodeContentStream(paramRequest);
  }
  
  /* Error */
  protected Bitmap decodeContentStream(Request paramRequest)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 13	com/squareup/picasso/ContentStreamBitmapHunter:context	Landroid/content/Context;
    //   4: invokevirtual 28	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 5
    //   9: aconst_null
    //   10: astore_2
    //   11: aload_1
    //   12: invokevirtual 34	com/squareup/picasso/Request:hasSize	()Z
    //   15: ifeq +59 -> 74
    //   18: new 36	android/graphics/BitmapFactory$Options
    //   21: dup
    //   22: invokespecial 39	android/graphics/BitmapFactory$Options:<init>	()V
    //   25: astore_3
    //   26: aload_3
    //   27: iconst_1
    //   28: putfield 43	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   31: aconst_null
    //   32: astore_2
    //   33: aload 5
    //   35: aload_1
    //   36: getfield 47	com/squareup/picasso/Request:uri	Landroid/net/Uri;
    //   39: invokevirtual 53	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   42: astore 4
    //   44: aload 4
    //   46: astore_2
    //   47: aload 4
    //   49: aconst_null
    //   50: aload_3
    //   51: invokestatic 59	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   54: pop
    //   55: aload 4
    //   57: invokestatic 65	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   60: aload_1
    //   61: getfield 69	com/squareup/picasso/Request:targetWidth	I
    //   64: aload_1
    //   65: getfield 72	com/squareup/picasso/Request:targetHeight	I
    //   68: aload_3
    //   69: invokestatic 76	com/squareup/picasso/ContentStreamBitmapHunter:calculateInSampleSize	(IILandroid/graphics/BitmapFactory$Options;)V
    //   72: aload_3
    //   73: astore_2
    //   74: aload 5
    //   76: aload_1
    //   77: getfield 47	com/squareup/picasso/Request:uri	Landroid/net/Uri;
    //   80: invokevirtual 53	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   83: astore_1
    //   84: aload_1
    //   85: aconst_null
    //   86: aload_2
    //   87: invokestatic 59	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   90: astore_2
    //   91: aload_1
    //   92: invokestatic 65	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   95: aload_2
    //   96: areturn
    //   97: astore_1
    //   98: aload_2
    //   99: invokestatic 65	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   102: aload_1
    //   103: athrow
    //   104: astore_2
    //   105: aload_1
    //   106: invokestatic 65	com/squareup/picasso/Utils:closeQuietly	(Ljava/io/InputStream;)V
    //   109: aload_2
    //   110: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	111	0	this	ContentStreamBitmapHunter
    //   0	111	1	paramRequest	Request
    //   10	89	2	localObject1	Object
    //   104	6	2	localObject2	Object
    //   25	48	3	localOptions	android.graphics.BitmapFactory.Options
    //   42	14	4	localInputStream	java.io.InputStream
    //   7	68	5	localContentResolver	android.content.ContentResolver
    // Exception table:
    //   from	to	target	type
    //   33	44	97	finally
    //   47	55	97	finally
    //   84	91	104	finally
  }
  
  Picasso.LoadedFrom getLoadedFrom()
  {
    return Picasso.LoadedFrom.DISK;
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.ContentStreamBitmapHunter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */