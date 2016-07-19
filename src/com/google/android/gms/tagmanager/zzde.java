package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

class zzde
  implements zzac
{
  private final zzb ayB;
  private final zza ayC;
  private final Context mContext;
  private final String zzbjj;
  
  zzde(Context paramContext, zza paramzza)
  {
    this(new zzb()
    {
      public HttpURLConnection zzd(URL paramAnonymousURL)
        throws IOException
      {
        return (HttpURLConnection)paramAnonymousURL.openConnection();
      }
    }, paramContext, paramzza);
  }
  
  zzde(zzb paramzzb, Context paramContext, zza paramzza)
  {
    ayB = paramzzb;
    mContext = paramContext.getApplicationContext();
    ayC = paramzza;
    zzbjj = zza("GoogleTagManager", "4.00", Build.VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
  }
  
  static String zzc(Locale paramLocale)
  {
    if (paramLocale == null) {}
    while ((paramLocale.getLanguage() == null) || (paramLocale.getLanguage().length() == 0)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLocale.getLanguage().toLowerCase());
    if ((paramLocale.getCountry() != null) && (paramLocale.getCountry().length() != 0)) {
      localStringBuilder.append("-").append(paramLocale.getCountry().toLowerCase());
    }
    return localStringBuilder.toString();
  }
  
  String zza(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6 });
  }
  
  public void zzai(List<zzar> paramList)
  {
    int n = Math.min(paramList.size(), 40);
    j = 1;
    int m = 0;
    zzar localzzar;
    Object localObject1;
    if (m < n)
    {
      localzzar = (zzar)paramList.get(m);
      localObject1 = zzd(localzzar);
      if (localObject1 == null)
      {
        zzbn.zzcy("No destination: discarding hit.");
        ayC.zzb(localzzar);
        i = j;
      }
    }
    for (;;)
    {
      m += 1;
      j = i;
      break;
      int k = j;
      try
      {
        localHttpURLConnection = ayB.zzd((URL)localObject1);
        i = j;
        if (j == 0) {}
      }
      catch (IOException localIOException2)
      {
        for (;;)
        {
          HttpURLConnection localHttpURLConnection;
          label186:
          label226:
          String str;
          i = k;
        }
      }
      try
      {
        zzbs.zzeb(mContext);
        i = 0;
        j = i;
        localHttpURLConnection.setRequestProperty("User-Agent", zzbjj);
        j = i;
        k = localHttpURLConnection.getResponseCode();
        j = i;
        localObject1 = localHttpURLConnection.getInputStream();
        if (k != 200) {}
        try
        {
          zzbn.zzcy(25 + "Bad response: " + k);
          ayC.zzc(localzzar);
          if (localObject1 != null)
          {
            k = i;
            ((InputStream)localObject1).close();
          }
          k = i;
          localHttpURLConnection.disconnect();
          continue;
        }
        finally {}
        ayC.zza(localzzar);
        break label186;
      }
      finally
      {
        Object localObject2 = null;
        i = j;
        break label226;
      }
    }
    if (localObject1 != null) {}
    try
    {
      ((InputStream)localObject1).close();
      localHttpURLConnection.disconnect();
      throw ((Throwable)localObject3);
    }
    catch (IOException localIOException1) {}
    str = String.valueOf(localIOException1.getClass().getSimpleName());
    if (str.length() != 0) {}
    for (str = "Exception sending hit: ".concat(str);; str = new String("Exception sending hit: "))
    {
      zzbn.zzcy(str);
      zzbn.zzcy(localIOException1.getMessage());
      ayC.zzc(localzzar);
      break;
    }
  }
  
  public boolean zzcbc()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)mContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
    {
      zzbn.v("...no network connectivity");
      return false;
    }
    return true;
  }
  
  URL zzd(zzar paramzzar)
  {
    paramzzar = paramzzar.zzcbp();
    try
    {
      paramzzar = new URL(paramzzar);
      return paramzzar;
    }
    catch (MalformedURLException paramzzar)
    {
      zzbn.e("Error trying to parse the GTM url.");
    }
    return null;
  }
  
  public static abstract interface zza
  {
    public abstract void zza(zzar paramzzar);
    
    public abstract void zzb(zzar paramzzar);
    
    public abstract void zzc(zzar paramzzar);
  }
  
  static abstract interface zzb
  {
    public abstract HttpURLConnection zzd(URL paramURL)
      throws IOException;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzde
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */