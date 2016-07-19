package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzadx.zza;
import com.google.android.gms.internal.zzadz;
import com.google.android.gms.internal.zzadz.zzc;
import com.google.android.gms.internal.zzadz.zzg;
import com.google.android.gms.internal.zzah.zzf;
import com.google.android.gms.internal.zzapb;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class zzcu
  implements zzp.zzf
{
  private final String auZ;
  private final ExecutorService axF;
  private zzbm<zzadx.zza> axz;
  private final Context mContext;
  
  zzcu(Context paramContext, String paramString)
  {
    mContext = paramContext;
    auZ = paramString;
    axF = Executors.newSingleThreadExecutor();
  }
  
  private zzadz.zzc zza(ByteArrayOutputStream paramByteArrayOutputStream)
  {
    try
    {
      paramByteArrayOutputStream = zzbg.zzoh(paramByteArrayOutputStream.toString("UTF-8"));
      return paramByteArrayOutputStream;
    }
    catch (UnsupportedEncodingException paramByteArrayOutputStream)
    {
      zzbn.zzcw("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
      return null;
    }
    catch (JSONException paramByteArrayOutputStream)
    {
      zzbn.zzcy("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
    }
    return null;
  }
  
  private zzadz.zzc zzal(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = zzadz.zzb(zzah.zzf.zze(paramArrayOfByte));
      if (paramArrayOfByte != null) {
        zzbn.v("The container was successfully loaded from the resource (using binary file)");
      }
      return paramArrayOfByte;
    }
    catch (zzapb paramArrayOfByte)
    {
      zzbn.e("The resource file is corrupted. The container cannot be extracted from the binary file");
      return null;
    }
    catch (zzadz.zzg paramArrayOfByte)
    {
      zzbn.zzcy("The resource file is invalid. The container from the binary file is invalid");
    }
    return null;
  }
  
  private void zzd(zzadx.zza paramzza)
    throws IllegalArgumentException
  {
    if ((zzwq == null) && (aDq == null)) {
      throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
    }
  }
  
  public void release()
  {
    try
    {
      axF.shutdown();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zza(zzbm<zzadx.zza> paramzzbm)
  {
    axz = paramzzbm;
  }
  
  public void zzb(final zzadx.zza paramzza)
  {
    axF.execute(new Runnable()
    {
      public void run()
      {
        zzc(paramzza);
      }
    });
  }
  
  /* Error */
  boolean zzc(zzadx.zza paramzza)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 147	com/google/android/gms/tagmanager/zzcu:zzccn	()Ljava/io/File;
    //   4: astore_3
    //   5: new 149	java/io/FileOutputStream
    //   8: dup
    //   9: aload_3
    //   10: invokespecial 152	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   13: astore_2
    //   14: aload_2
    //   15: aload_1
    //   16: invokestatic 158	com/google/android/gms/internal/zzapc:zzf	(Lcom/google/android/gms/internal/zzapc;)[B
    //   19: invokevirtual 162	java/io/FileOutputStream:write	([B)V
    //   22: aload_2
    //   23: invokevirtual 165	java/io/FileOutputStream:close	()V
    //   26: iconst_1
    //   27: ireturn
    //   28: astore_1
    //   29: ldc -89
    //   31: invokestatic 98	com/google/android/gms/tagmanager/zzbn:e	(Ljava/lang/String;)V
    //   34: iconst_0
    //   35: ireturn
    //   36: astore_1
    //   37: ldc -87
    //   39: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   42: goto -16 -> 26
    //   45: astore_1
    //   46: ldc -85
    //   48: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   51: aload_3
    //   52: invokevirtual 177	java/io/File:delete	()Z
    //   55: pop
    //   56: aload_2
    //   57: invokevirtual 165	java/io/FileOutputStream:close	()V
    //   60: iconst_0
    //   61: ireturn
    //   62: astore_1
    //   63: ldc -87
    //   65: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   68: iconst_0
    //   69: ireturn
    //   70: astore_1
    //   71: aload_2
    //   72: invokevirtual 165	java/io/FileOutputStream:close	()V
    //   75: aload_1
    //   76: athrow
    //   77: astore_2
    //   78: ldc -87
    //   80: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   83: goto -8 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	this	zzcu
    //   0	86	1	paramzza	zzadx.zza
    //   13	59	2	localFileOutputStream	java.io.FileOutputStream
    //   77	1	2	localIOException	java.io.IOException
    //   4	48	3	localFile	File
    // Exception table:
    //   from	to	target	type
    //   5	14	28	java/io/FileNotFoundException
    //   22	26	36	java/io/IOException
    //   14	22	45	java/io/IOException
    //   56	60	62	java/io/IOException
    //   14	22	70	finally
    //   46	56	70	finally
    //   71	75	77	java/io/IOException
  }
  
  public void zzcar()
  {
    axF.execute(new Runnable()
    {
      public void run()
      {
        zzccm();
      }
    });
  }
  
  /* Error */
  void zzccm()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 128	com/google/android/gms/tagmanager/zzcu:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   4: ifnonnull +13 -> 17
    //   7: new 184	java/lang/IllegalStateException
    //   10: dup
    //   11: ldc -70
    //   13: invokespecial 187	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 128	com/google/android/gms/tagmanager/zzcu:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   21: invokeinterface 192 1 0
    //   26: ldc -62
    //   28: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   31: invokestatic 200	com/google/android/gms/tagmanager/zzci:zzcce	()Lcom/google/android/gms/tagmanager/zzci;
    //   34: invokevirtual 204	com/google/android/gms/tagmanager/zzci:zzccf	()Lcom/google/android/gms/tagmanager/zzci$zza;
    //   37: getstatic 210	com/google/android/gms/tagmanager/zzci$zza:axo	Lcom/google/android/gms/tagmanager/zzci$zza;
    //   40: if_acmpeq +15 -> 55
    //   43: invokestatic 200	com/google/android/gms/tagmanager/zzci:zzcce	()Lcom/google/android/gms/tagmanager/zzci;
    //   46: invokevirtual 204	com/google/android/gms/tagmanager/zzci:zzccf	()Lcom/google/android/gms/tagmanager/zzci$zza;
    //   49: getstatic 213	com/google/android/gms/tagmanager/zzci$zza:axp	Lcom/google/android/gms/tagmanager/zzci$zza;
    //   52: if_acmpne +32 -> 84
    //   55: aload_0
    //   56: getfield 28	com/google/android/gms/tagmanager/zzcu:auZ	Ljava/lang/String;
    //   59: invokestatic 200	com/google/android/gms/tagmanager/zzci:zzcce	()Lcom/google/android/gms/tagmanager/zzci;
    //   62: invokevirtual 217	com/google/android/gms/tagmanager/zzci:getContainerId	()Ljava/lang/String;
    //   65: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifeq +16 -> 84
    //   71: aload_0
    //   72: getfield 128	com/google/android/gms/tagmanager/zzcu:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   75: getstatic 229	com/google/android/gms/tagmanager/zzbm$zza:awN	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   78: invokeinterface 232 2 0
    //   83: return
    //   84: new 234	java/io/FileInputStream
    //   87: dup
    //   88: aload_0
    //   89: invokevirtual 147	com/google/android/gms/tagmanager/zzcu:zzccn	()Ljava/io/File;
    //   92: invokespecial 235	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   95: astore_1
    //   96: new 47	java/io/ByteArrayOutputStream
    //   99: dup
    //   100: invokespecial 236	java/io/ByteArrayOutputStream:<init>	()V
    //   103: astore_2
    //   104: aload_1
    //   105: aload_2
    //   106: invokestatic 239	com/google/android/gms/internal/zzadz:zzc	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   109: aload_2
    //   110: invokevirtual 243	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   113: invokestatic 247	com/google/android/gms/internal/zzadx$zza:zzap	([B)Lcom/google/android/gms/internal/zzadx$zza;
    //   116: astore_2
    //   117: aload_0
    //   118: aload_2
    //   119: invokespecial 249	com/google/android/gms/tagmanager/zzcu:zzd	(Lcom/google/android/gms/internal/zzadx$zza;)V
    //   122: aload_0
    //   123: getfield 128	com/google/android/gms/tagmanager/zzcu:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   126: aload_2
    //   127: invokeinterface 253 2 0
    //   132: aload_1
    //   133: invokevirtual 254	java/io/FileInputStream:close	()V
    //   136: ldc_w 256
    //   139: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   142: return
    //   143: astore_1
    //   144: ldc_w 258
    //   147: invokestatic 65	com/google/android/gms/tagmanager/zzbn:zzcw	(Ljava/lang/String;)V
    //   150: aload_0
    //   151: getfield 128	com/google/android/gms/tagmanager/zzcu:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   154: getstatic 229	com/google/android/gms/tagmanager/zzbm$zza:awN	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   157: invokeinterface 232 2 0
    //   162: return
    //   163: astore_1
    //   164: ldc_w 260
    //   167: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   170: goto -34 -> 136
    //   173: astore_2
    //   174: aload_0
    //   175: getfield 128	com/google/android/gms/tagmanager/zzcu:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   178: getstatic 263	com/google/android/gms/tagmanager/zzbm$zza:awO	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   181: invokeinterface 232 2 0
    //   186: ldc_w 265
    //   189: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   192: aload_1
    //   193: invokevirtual 254	java/io/FileInputStream:close	()V
    //   196: goto -60 -> 136
    //   199: astore_1
    //   200: ldc_w 260
    //   203: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   206: goto -70 -> 136
    //   209: astore_2
    //   210: aload_0
    //   211: getfield 128	com/google/android/gms/tagmanager/zzcu:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   214: getstatic 263	com/google/android/gms/tagmanager/zzbm$zza:awO	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   217: invokeinterface 232 2 0
    //   222: ldc_w 267
    //   225: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   228: aload_1
    //   229: invokevirtual 254	java/io/FileInputStream:close	()V
    //   232: goto -96 -> 136
    //   235: astore_1
    //   236: ldc_w 260
    //   239: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   242: goto -106 -> 136
    //   245: astore_2
    //   246: aload_1
    //   247: invokevirtual 254	java/io/FileInputStream:close	()V
    //   250: aload_2
    //   251: athrow
    //   252: astore_1
    //   253: ldc_w 260
    //   256: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   259: goto -9 -> 250
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	262	0	this	zzcu
    //   95	38	1	localFileInputStream	java.io.FileInputStream
    //   143	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   163	30	1	localIOException1	java.io.IOException
    //   199	30	1	localIOException2	java.io.IOException
    //   235	12	1	localIOException3	java.io.IOException
    //   252	1	1	localIOException4	java.io.IOException
    //   103	24	2	localObject1	Object
    //   173	1	2	localIOException5	java.io.IOException
    //   209	1	2	localIllegalArgumentException	IllegalArgumentException
    //   245	6	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   84	96	143	java/io/FileNotFoundException
    //   132	136	163	java/io/IOException
    //   96	132	173	java/io/IOException
    //   192	196	199	java/io/IOException
    //   96	132	209	java/lang/IllegalArgumentException
    //   228	232	235	java/io/IOException
    //   96	132	245	finally
    //   174	192	245	finally
    //   210	228	245	finally
    //   246	250	252	java/io/IOException
  }
  
  File zzccn()
  {
    String str1 = String.valueOf("resource_");
    String str2 = String.valueOf(auZ);
    if (str2.length() != 0) {}
    for (str1 = str1.concat(str2);; str1 = new String(str1)) {
      return new File(mContext.getDir("google_tagmanager", 0), str1);
    }
  }
  
  /* Error */
  public zzadz.zzc zzxw(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	com/google/android/gms/tagmanager/zzcu:mContext	Landroid/content/Context;
    //   4: invokevirtual 300	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: iload_1
    //   8: invokevirtual 306	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   11: astore_2
    //   12: aload_0
    //   13: getfield 26	com/google/android/gms/tagmanager/zzcu:mContext	Landroid/content/Context;
    //   16: invokevirtual 300	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   19: iload_1
    //   20: invokevirtual 310	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   23: invokestatic 273	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   26: astore_3
    //   27: new 312	java/lang/StringBuilder
    //   30: dup
    //   31: aload_3
    //   32: invokestatic 273	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   35: invokevirtual 277	java/lang/String:length	()I
    //   38: bipush 66
    //   40: iadd
    //   41: invokespecial 315	java/lang/StringBuilder:<init>	(I)V
    //   44: ldc_w 317
    //   47: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: iload_1
    //   51: invokevirtual 324	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   54: ldc_w 326
    //   57: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: aload_3
    //   61: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: ldc_w 328
    //   67: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 330	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   76: new 47	java/io/ByteArrayOutputStream
    //   79: dup
    //   80: invokespecial 236	java/io/ByteArrayOutputStream:<init>	()V
    //   83: astore_3
    //   84: aload_2
    //   85: aload_3
    //   86: invokestatic 239	com/google/android/gms/internal/zzadz:zzc	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   89: aload_0
    //   90: aload_3
    //   91: invokespecial 332	com/google/android/gms/tagmanager/zzcu:zza	(Ljava/io/ByteArrayOutputStream;)Lcom/google/android/gms/internal/zzadz$zzc;
    //   94: astore_2
    //   95: aload_2
    //   96: ifnull +39 -> 135
    //   99: ldc_w 334
    //   102: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   105: aload_2
    //   106: areturn
    //   107: astore_2
    //   108: new 312	java/lang/StringBuilder
    //   111: dup
    //   112: bipush 98
    //   114: invokespecial 315	java/lang/StringBuilder:<init>	(I)V
    //   117: ldc_w 336
    //   120: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: iload_1
    //   124: invokevirtual 324	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   127: invokevirtual 330	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   133: aconst_null
    //   134: areturn
    //   135: aload_0
    //   136: aload_3
    //   137: invokevirtual 243	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   140: invokespecial 338	com/google/android/gms/tagmanager/zzcu:zzal	([B)Lcom/google/android/gms/internal/zzadz$zzc;
    //   143: astore_2
    //   144: aload_2
    //   145: areturn
    //   146: astore_2
    //   147: aload_0
    //   148: getfield 26	com/google/android/gms/tagmanager/zzcu:mContext	Landroid/content/Context;
    //   151: invokevirtual 300	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   154: iload_1
    //   155: invokevirtual 310	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   158: invokestatic 273	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   161: astore_2
    //   162: new 312	java/lang/StringBuilder
    //   165: dup
    //   166: aload_2
    //   167: invokestatic 273	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   170: invokevirtual 277	java/lang/String:length	()I
    //   173: bipush 67
    //   175: iadd
    //   176: invokespecial 315	java/lang/StringBuilder:<init>	(I)V
    //   179: ldc_w 340
    //   182: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: iload_1
    //   186: invokevirtual 324	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   189: ldc_w 326
    //   192: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload_2
    //   196: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: ldc_w 328
    //   202: invokevirtual 321	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: invokevirtual 330	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: invokestatic 70	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   211: aconst_null
    //   212: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	213	0	this	zzcu
    //   0	213	1	paramInt	int
    //   11	95	2	localObject1	Object
    //   107	1	2	localNotFoundException	android.content.res.Resources.NotFoundException
    //   143	2	2	localzzc	zzadz.zzc
    //   146	1	2	localIOException	java.io.IOException
    //   161	35	2	str	String
    //   26	111	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	12	107	android/content/res/Resources$NotFoundException
    //   76	95	146	java/io/IOException
    //   99	105	146	java/io/IOException
    //   135	144	146	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */