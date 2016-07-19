package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzaed;
import com.google.android.gms.internal.zzah.zzj;

class zzcs
  implements Runnable
{
  private final String auZ;
  private volatile String avw;
  private volatile zzs axA;
  private volatile String axB;
  private final zzaed axx;
  private final String axy;
  private zzbm<zzah.zzj> axz;
  private final Context mContext;
  
  zzcs(Context paramContext, String paramString, zzaed paramzzaed, zzs paramzzs)
  {
    mContext = paramContext;
    axx = paramzzaed;
    auZ = paramString;
    axA = paramzzs;
    paramContext = String.valueOf("/r?id=");
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {}
    for (paramContext = paramContext.concat(paramString);; paramContext = new String(paramContext))
    {
      axy = paramContext;
      avw = axy;
      axB = null;
      return;
    }
  }
  
  public zzcs(Context paramContext, String paramString, zzs paramzzs)
  {
    this(paramContext, paramString, new zzaed(), paramzzs);
  }
  
  private boolean zzcch()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      zzbn.v("...no network connectivity");
      return false;
    }
    return true;
  }
  
  /* Error */
  private void zzcci()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 100	com/google/android/gms/tagmanager/zzcs:zzcch	()Z
    //   4: ifne +16 -> 20
    //   7: aload_0
    //   8: getfield 102	com/google/android/gms/tagmanager/zzcs:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   11: getstatic 108	com/google/android/gms/tagmanager/zzbm$zza:awN	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   14: invokeinterface 114 2 0
    //   19: return
    //   20: ldc 116
    //   22: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: invokevirtual 120	com/google/android/gms/tagmanager/zzcs:zzccj	()Ljava/lang/String;
    //   29: astore_3
    //   30: aload_0
    //   31: getfield 29	com/google/android/gms/tagmanager/zzcs:axx	Lcom/google/android/gms/internal/zzaed;
    //   34: invokevirtual 124	com/google/android/gms/internal/zzaed:zzchh	()Lcom/google/android/gms/internal/zzaec;
    //   37: astore_2
    //   38: aload_2
    //   39: aload_3
    //   40: invokeinterface 130 2 0
    //   45: astore_1
    //   46: new 132	java/io/ByteArrayOutputStream
    //   49: dup
    //   50: invokespecial 133	java/io/ByteArrayOutputStream:<init>	()V
    //   53: astore 4
    //   55: aload_1
    //   56: aload 4
    //   58: invokestatic 139	com/google/android/gms/internal/zzadz:zzc	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   61: aload 4
    //   63: invokevirtual 143	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   66: invokestatic 149	com/google/android/gms/internal/zzah$zzj:zzf	([B)Lcom/google/android/gms/internal/zzah$zzj;
    //   69: astore 4
    //   71: aload 4
    //   73: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   76: astore_1
    //   77: new 151	java/lang/StringBuilder
    //   80: dup
    //   81: aload_1
    //   82: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   85: invokevirtual 45	java/lang/String:length	()I
    //   88: bipush 43
    //   90: iadd
    //   91: invokespecial 154	java/lang/StringBuilder:<init>	(I)V
    //   94: ldc -100
    //   96: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_1
    //   100: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   109: aload 4
    //   111: getfield 167	com/google/android/gms/internal/zzah$zzj:zzwq	Lcom/google/android/gms/internal/zzah$zzf;
    //   114: ifnonnull +38 -> 152
    //   117: aload 4
    //   119: getfield 171	com/google/android/gms/internal/zzah$zzj:zzwp	[Lcom/google/android/gms/internal/zzah$zzi;
    //   122: arraylength
    //   123: ifne +29 -> 152
    //   126: aload_0
    //   127: getfield 31	com/google/android/gms/tagmanager/zzcs:auZ	Ljava/lang/String;
    //   130: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   133: astore_1
    //   134: aload_1
    //   135: invokevirtual 45	java/lang/String:length	()I
    //   138: ifeq +197 -> 335
    //   141: ldc -83
    //   143: aload_1
    //   144: invokevirtual 49	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   147: astore_1
    //   148: aload_1
    //   149: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   152: aload_0
    //   153: getfield 102	com/google/android/gms/tagmanager/zzcs:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   156: aload 4
    //   158: invokeinterface 177 2 0
    //   163: aload_2
    //   164: invokeinterface 180 1 0
    //   169: ldc -74
    //   171: invokestatic 93	com/google/android/gms/tagmanager/zzbn:v	(Ljava/lang/String;)V
    //   174: return
    //   175: astore_1
    //   176: aload_0
    //   177: getfield 31	com/google/android/gms/tagmanager/zzcs:auZ	Ljava/lang/String;
    //   180: astore_1
    //   181: new 151	java/lang/StringBuilder
    //   184: dup
    //   185: aload_3
    //   186: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   189: invokevirtual 45	java/lang/String:length	()I
    //   192: bipush 79
    //   194: iadd
    //   195: aload_1
    //   196: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   199: invokevirtual 45	java/lang/String:length	()I
    //   202: iadd
    //   203: invokespecial 154	java/lang/StringBuilder:<init>	(I)V
    //   206: ldc -72
    //   208: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: aload_3
    //   212: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: ldc -70
    //   217: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_1
    //   221: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: ldc -68
    //   226: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   232: invokestatic 191	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   235: aload_0
    //   236: getfield 102	com/google/android/gms/tagmanager/zzcs:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   239: getstatic 194	com/google/android/gms/tagmanager/zzbm$zza:awP	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   242: invokeinterface 114 2 0
    //   247: aload_2
    //   248: invokeinterface 180 1 0
    //   253: return
    //   254: astore_1
    //   255: aload_1
    //   256: invokevirtual 197	java/io/IOException:getMessage	()Ljava/lang/String;
    //   259: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   262: astore 4
    //   264: new 151	java/lang/StringBuilder
    //   267: dup
    //   268: aload_3
    //   269: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   272: invokevirtual 45	java/lang/String:length	()I
    //   275: bipush 40
    //   277: iadd
    //   278: aload 4
    //   280: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   283: invokevirtual 45	java/lang/String:length	()I
    //   286: iadd
    //   287: invokespecial 154	java/lang/StringBuilder:<init>	(I)V
    //   290: ldc -57
    //   292: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: aload_3
    //   296: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: ldc -55
    //   301: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: aload 4
    //   306: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   312: aload_1
    //   313: invokestatic 205	com/google/android/gms/tagmanager/zzbn:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   316: aload_0
    //   317: getfield 102	com/google/android/gms/tagmanager/zzcs:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   320: getstatic 208	com/google/android/gms/tagmanager/zzbm$zza:awO	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   323: invokeinterface 114 2 0
    //   328: aload_2
    //   329: invokeinterface 180 1 0
    //   334: return
    //   335: new 37	java/lang/String
    //   338: dup
    //   339: ldc -83
    //   341: invokespecial 58	java/lang/String:<init>	(Ljava/lang/String;)V
    //   344: astore_1
    //   345: goto -197 -> 148
    //   348: astore_1
    //   349: aload_1
    //   350: invokevirtual 197	java/io/IOException:getMessage	()Ljava/lang/String;
    //   353: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   356: astore 4
    //   358: new 151	java/lang/StringBuilder
    //   361: dup
    //   362: aload_3
    //   363: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   366: invokevirtual 45	java/lang/String:length	()I
    //   369: bipush 51
    //   371: iadd
    //   372: aload 4
    //   374: invokestatic 41	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   377: invokevirtual 45	java/lang/String:length	()I
    //   380: iadd
    //   381: invokespecial 154	java/lang/StringBuilder:<init>	(I)V
    //   384: ldc -46
    //   386: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: aload_3
    //   390: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   393: ldc -55
    //   395: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   398: aload 4
    //   400: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: aload_1
    //   407: invokestatic 205	com/google/android/gms/tagmanager/zzbn:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   410: aload_0
    //   411: getfield 102	com/google/android/gms/tagmanager/zzcs:axz	Lcom/google/android/gms/tagmanager/zzbm;
    //   414: getstatic 194	com/google/android/gms/tagmanager/zzbm$zza:awP	Lcom/google/android/gms/tagmanager/zzbm$zza;
    //   417: invokeinterface 114 2 0
    //   422: aload_2
    //   423: invokeinterface 180 1 0
    //   428: return
    //   429: astore_1
    //   430: aload_2
    //   431: invokeinterface 180 1 0
    //   436: aload_1
    //   437: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	438	0	this	zzcs
    //   45	104	1	localObject1	Object
    //   175	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   180	41	1	str1	String
    //   254	59	1	localIOException1	java.io.IOException
    //   344	1	1	str2	String
    //   348	59	1	localIOException2	java.io.IOException
    //   429	8	1	localObject2	Object
    //   37	394	2	localzzaec	com.google.android.gms.internal.zzaec
    //   29	361	3	str3	String
    //   53	346	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   38	46	175	java/io/FileNotFoundException
    //   38	46	254	java/io/IOException
    //   46	148	348	java/io/IOException
    //   148	152	348	java/io/IOException
    //   152	163	348	java/io/IOException
    //   335	345	348	java/io/IOException
    //   38	46	429	finally
    //   46	148	429	finally
    //   148	152	429	finally
    //   152	163	429	finally
    //   176	247	429	finally
    //   255	328	429	finally
    //   335	345	429	finally
    //   349	422	429	finally
  }
  
  public void run()
  {
    if (axz == null) {
      throw new IllegalStateException("callback must be set before execute");
    }
    axz.zzcaq();
    zzcci();
  }
  
  void zza(zzbm<zzah.zzj> paramzzbm)
  {
    axz = paramzzbm;
  }
  
  String zzccj()
  {
    Object localObject1 = String.valueOf(axA.zzcas());
    Object localObject2 = avw;
    String str = String.valueOf("&v=a65833898");
    localObject2 = String.valueOf(localObject1).length() + 0 + String.valueOf(localObject2).length() + String.valueOf(str).length() + (String)localObject1 + (String)localObject2 + str;
    localObject1 = localObject2;
    if (axB != null)
    {
      localObject1 = localObject2;
      if (!axB.trim().equals(""))
      {
        localObject1 = String.valueOf(localObject2);
        localObject2 = String.valueOf("&pv=");
        str = axB;
        localObject1 = String.valueOf(localObject1).length() + 0 + String.valueOf(localObject2).length() + String.valueOf(str).length() + (String)localObject1 + (String)localObject2 + str;
      }
    }
    localObject2 = localObject1;
    if (zzci.zzcce().zzccf().equals(zzci.zza.axp))
    {
      localObject1 = String.valueOf(localObject1);
      localObject2 = String.valueOf("&gtm_debug=x");
      if (((String)localObject2).length() != 0) {
        localObject2 = ((String)localObject1).concat((String)localObject2);
      }
    }
    else
    {
      return (String)localObject2;
    }
    return new String((String)localObject1);
  }
  
  void zznv(String paramString)
  {
    if (paramString == null)
    {
      avw = axy;
      return;
    }
    String str = String.valueOf(paramString);
    if (str.length() != 0) {}
    for (str = "Setting CTFE URL path: ".concat(str);; str = new String("Setting CTFE URL path: "))
    {
      zzbn.zzcw(str);
      avw = paramString;
      return;
    }
  }
  
  void zzok(String paramString)
  {
    String str = String.valueOf(paramString);
    if (str.length() != 0) {}
    for (str = "Setting previous container version: ".concat(str);; str = new String("Setting previous container version: "))
    {
      zzbn.zzcw(str);
      axB = paramString;
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */