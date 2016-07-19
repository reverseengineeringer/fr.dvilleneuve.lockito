package fr.dvilleneuve.lockito.core.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;

public class BitmapUtils
{
  @NonNull
  private static Rect maxInnerRect(@NonNull Rect paramRect)
  {
    int i = Math.min(paramRect.width(), paramRect.height());
    int j = (paramRect.width() - i) / 2;
    int k = (paramRect.height() - i) / 2;
    return new Rect(j, k, j + i, k + i);
  }
  
  /* Error */
  public static void writeThumbnails(@NonNull java.io.File paramFile, @NonNull android.graphics.Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 42	java/io/File:getParentFile	()Ljava/io/File;
    //   4: astore_3
    //   5: aload_3
    //   6: invokevirtual 46	java/io/File:exists	()Z
    //   9: ifne +8 -> 17
    //   12: aload_3
    //   13: invokevirtual 49	java/io/File:mkdirs	()Z
    //   16: pop
    //   17: new 14	android/graphics/Rect
    //   20: dup
    //   21: iconst_0
    //   22: iconst_0
    //   23: aload_1
    //   24: invokevirtual 54	android/graphics/Bitmap:getWidth	()I
    //   27: aload_1
    //   28: invokevirtual 57	android/graphics/Bitmap:getHeight	()I
    //   31: invokespecial 30	android/graphics/Rect:<init>	(IIII)V
    //   34: invokestatic 59	fr/dvilleneuve/lockito/core/utils/BitmapUtils:maxInnerRect	(Landroid/graphics/Rect;)Landroid/graphics/Rect;
    //   37: astore_3
    //   38: sipush 128
    //   41: aload_3
    //   42: invokevirtual 18	android/graphics/Rect:width	()I
    //   45: invokestatic 27	java/lang/Math:min	(II)I
    //   48: istore_2
    //   49: new 14	android/graphics/Rect
    //   52: dup
    //   53: iconst_0
    //   54: iconst_0
    //   55: iload_2
    //   56: iload_2
    //   57: invokespecial 30	android/graphics/Rect:<init>	(IIII)V
    //   60: astore 5
    //   62: iload_2
    //   63: iload_2
    //   64: getstatic 65	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
    //   67: invokestatic 69	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   70: astore 4
    //   72: new 71	android/graphics/Canvas
    //   75: dup
    //   76: aload 4
    //   78: invokespecial 74	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   81: aload_1
    //   82: aload_3
    //   83: aload 5
    //   85: new 76	android/graphics/Paint
    //   88: dup
    //   89: invokespecial 77	android/graphics/Paint:<init>	()V
    //   92: invokevirtual 81	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V
    //   95: aload_1
    //   96: invokevirtual 84	android/graphics/Bitmap:recycle	()V
    //   99: aconst_null
    //   100: astore_1
    //   101: aconst_null
    //   102: astore_3
    //   103: new 86	java/io/FileOutputStream
    //   106: dup
    //   107: aload_0
    //   108: invokespecial 89	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   111: astore_0
    //   112: aload 4
    //   114: getstatic 95	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   117: bipush 100
    //   119: aload_0
    //   120: invokevirtual 99	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   123: pop
    //   124: aload_0
    //   125: invokestatic 105	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/OutputStream;)V
    //   128: aload 4
    //   130: invokevirtual 84	android/graphics/Bitmap:recycle	()V
    //   133: return
    //   134: astore_0
    //   135: aload_1
    //   136: invokevirtual 84	android/graphics/Bitmap:recycle	()V
    //   139: aload_0
    //   140: athrow
    //   141: astore_1
    //   142: aload_3
    //   143: astore_0
    //   144: aload_1
    //   145: astore_3
    //   146: aload_0
    //   147: astore_1
    //   148: ldc 107
    //   150: aload_3
    //   151: iconst_0
    //   152: anewarray 4	java/lang/Object
    //   155: invokestatic 113	fr/dvilleneuve/lockito/core/logger/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;[Ljava/lang/Object;)V
    //   158: aload_0
    //   159: invokestatic 105	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/OutputStream;)V
    //   162: aload 4
    //   164: invokevirtual 84	android/graphics/Bitmap:recycle	()V
    //   167: return
    //   168: astore_0
    //   169: aload_1
    //   170: invokestatic 105	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/OutputStream;)V
    //   173: aload 4
    //   175: invokevirtual 84	android/graphics/Bitmap:recycle	()V
    //   178: aload_0
    //   179: athrow
    //   180: astore_3
    //   181: aload_0
    //   182: astore_1
    //   183: aload_3
    //   184: astore_0
    //   185: goto -16 -> 169
    //   188: astore_3
    //   189: goto -43 -> 146
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	paramFile	java.io.File
    //   0	192	1	paramBitmap	android.graphics.Bitmap
    //   48	16	2	i	int
    //   4	147	3	localObject1	Object
    //   180	4	3	localObject2	Object
    //   188	1	3	localFileNotFoundException	java.io.FileNotFoundException
    //   70	104	4	localBitmap	android.graphics.Bitmap
    //   60	24	5	localRect	Rect
    // Exception table:
    //   from	to	target	type
    //   17	95	134	finally
    //   103	112	141	java/io/FileNotFoundException
    //   103	112	168	finally
    //   148	158	168	finally
    //   112	124	180	finally
    //   112	124	188	java/io/FileNotFoundException
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.BitmapUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */