package fr.dvilleneuve.lockito.core.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils
{
  /* Error */
  public static void copyFile(@Nullable File paramFile1, @Nullable File paramFile2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +4 -> 9
    //   8: return
    //   9: aload_0
    //   10: invokevirtual 20	java/io/File:exists	()Z
    //   13: ifne +35 -> 48
    //   16: new 22	java/io/FileNotFoundException
    //   19: dup
    //   20: new 24	java/lang/StringBuilder
    //   23: dup
    //   24: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   27: ldc 27
    //   29: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: aload_0
    //   33: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   36: ldc 36
    //   38: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: invokespecial 43	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   47: athrow
    //   48: aload_1
    //   49: invokevirtual 47	java/io/File:getParentFile	()Ljava/io/File;
    //   52: ifnull +21 -> 73
    //   55: aload_1
    //   56: invokevirtual 47	java/io/File:getParentFile	()Ljava/io/File;
    //   59: invokevirtual 20	java/io/File:exists	()Z
    //   62: ifne +11 -> 73
    //   65: aload_1
    //   66: invokevirtual 47	java/io/File:getParentFile	()Ljava/io/File;
    //   69: invokevirtual 50	java/io/File:mkdirs	()Z
    //   72: pop
    //   73: aload_1
    //   74: invokevirtual 20	java/io/File:exists	()Z
    //   77: ifeq +42 -> 119
    //   80: aload_1
    //   81: invokevirtual 53	java/io/File:canWrite	()Z
    //   84: ifne +35 -> 119
    //   87: new 13	java/io/IOException
    //   90: dup
    //   91: new 24	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   98: ldc 55
    //   100: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload_1
    //   104: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   107: ldc 57
    //   109: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   115: invokespecial 58	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   118: athrow
    //   119: aload_0
    //   120: invokevirtual 61	java/io/File:getCanonicalPath	()Ljava/lang/String;
    //   123: aload_1
    //   124: invokevirtual 61	java/io/File:getCanonicalPath	()Ljava/lang/String;
    //   127: invokevirtual 67	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   130: ifeq +35 -> 165
    //   133: new 13	java/io/IOException
    //   136: dup
    //   137: new 24	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   144: ldc 69
    //   146: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload_0
    //   150: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   153: ldc 71
    //   155: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: invokespecial 58	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   164: athrow
    //   165: new 73	java/io/FileInputStream
    //   168: dup
    //   169: aload_0
    //   170: invokespecial 76	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   173: astore_2
    //   174: new 78	java/io/FileOutputStream
    //   177: dup
    //   178: aload_1
    //   179: invokespecial 79	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   182: astore_3
    //   183: aload_2
    //   184: aload_3
    //   185: invokestatic 85	fr/dvilleneuve/lockito/core/utils/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   188: pop
    //   189: aload_3
    //   190: invokestatic 89	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/OutputStream;)V
    //   193: aload_2
    //   194: invokestatic 92	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/InputStream;)V
    //   197: aload_0
    //   198: invokevirtual 96	java/io/File:length	()J
    //   201: aload_1
    //   202: invokevirtual 96	java/io/File:length	()J
    //   205: lcmp
    //   206: ifeq -198 -> 8
    //   209: new 13	java/io/IOException
    //   212: dup
    //   213: new 24	java/lang/StringBuilder
    //   216: dup
    //   217: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   220: ldc 98
    //   222: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload_0
    //   226: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   229: ldc 100
    //   231: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: aload_1
    //   235: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   238: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   241: invokespecial 58	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   244: athrow
    //   245: astore_0
    //   246: aload_3
    //   247: invokestatic 89	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/OutputStream;)V
    //   250: aload_0
    //   251: athrow
    //   252: astore_0
    //   253: aload_2
    //   254: invokestatic 92	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/InputStream;)V
    //   257: aload_0
    //   258: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	paramFile1	File
    //   0	259	1	paramFile2	File
    //   173	81	2	localFileInputStream	java.io.FileInputStream
    //   182	65	3	localFileOutputStream	java.io.FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   183	189	245	finally
    //   174	183	252	finally
    //   189	193	252	finally
    //   246	252	252	finally
  }
  
  public static void deleteFile(@Nullable File paramFile)
    throws IOException
  {
    if (paramFile == null) {}
    do
    {
      do
      {
        return;
      } while (!paramFile.isFile());
      if (!paramFile.exists()) {
        throw new FileNotFoundException("File does not exist: " + paramFile);
      }
    } while (paramFile.delete());
    throw new IOException("Unable to delete file: " + paramFile);
  }
  
  public static byte[] readAsset(@NonNull Context paramContext, @NonNull String paramString)
    throws IOException
  {
    Object localObject = paramContext.getResources().getAssets();
    paramContext = null;
    try
    {
      paramString = ((AssetManager)localObject).open(paramString);
      paramContext = paramString;
      localObject = IOUtils.toBytes(paramString);
      return (byte[])localObject;
    }
    finally
    {
      IOUtils.safelyClose(paramContext);
    }
  }
  
  /* Error */
  public static void writeFile(@NonNull File paramFile, @NonNull byte[] paramArrayOfByte)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 47	java/io/File:getParentFile	()Ljava/io/File;
    //   4: invokevirtual 50	java/io/File:mkdirs	()Z
    //   7: pop
    //   8: aconst_null
    //   9: astore_2
    //   10: new 78	java/io/FileOutputStream
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 79	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   18: astore_0
    //   19: aload_0
    //   20: aload_1
    //   21: invokevirtual 144	java/io/FileOutputStream:write	([B)V
    //   24: aload_0
    //   25: invokestatic 89	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/OutputStream;)V
    //   28: return
    //   29: astore_1
    //   30: aload_2
    //   31: astore_0
    //   32: aload_0
    //   33: invokestatic 89	fr/dvilleneuve/lockito/core/utils/IOUtils:safelyClose	(Ljava/io/OutputStream;)V
    //   36: aload_1
    //   37: athrow
    //   38: astore_1
    //   39: goto -7 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	paramFile	File
    //   0	42	1	paramArrayOfByte	byte[]
    //   9	22	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	19	29	finally
    //   19	24	38	finally
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.FileUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */