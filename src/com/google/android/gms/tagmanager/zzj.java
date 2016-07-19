package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzj
  extends zzdj
{
  private static final String ID = zzaf.zziv.toString();
  private static final String URL = zzag.zztw.toString();
  private static final String auS = zzag.zzkx.toString();
  private static final String auT = zzag.zztv.toString();
  static final String auU;
  private static final Set<String> auV = new HashSet();
  private final zza auW;
  private final Context mContext;
  
  static
  {
    String str = ID;
    auU = String.valueOf(str).length() + 17 + "gtm_" + str + "_unrepeatable";
  }
  
  public zzj(Context paramContext)
  {
    this(paramContext, new zza()
    {
      public zzas zzcad()
      {
        return zzz.zzds(zzj.this);
      }
    });
  }
  
  zzj(Context paramContext, zza paramzza)
  {
    super(ID, new String[] { URL });
    auW = paramzza;
    mContext = paramContext;
  }
  
  private boolean zznk(String paramString)
  {
    boolean bool1 = true;
    for (;;)
    {
      try
      {
        boolean bool2 = zznm(paramString);
        if (bool2) {
          return bool1;
        }
        if (zznl(paramString)) {
          auV.add(paramString);
        } else {
          bool1 = false;
        }
      }
      finally {}
    }
  }
  
  public void zzax(Map<String, zzai.zza> paramMap)
  {
    if (paramMap.get(auT) != null) {}
    for (String str = zzdl.zzg((zzai.zza)paramMap.get(auT)); (str != null) && (zznk(str)); str = null) {
      return;
    }
    Uri.Builder localBuilder = Uri.parse(zzdl.zzg((zzai.zza)paramMap.get(URL))).buildUpon();
    paramMap = (zzai.zza)paramMap.get(auS);
    if (paramMap != null)
    {
      paramMap = zzdl.zzl(paramMap);
      if (!(paramMap instanceof List))
      {
        paramMap = String.valueOf(localBuilder.build().toString());
        if (paramMap.length() != 0) {}
        for (paramMap = "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(paramMap);; paramMap = new String("ArbitraryPixel: additional params not a list: not sending partial hit: "))
        {
          zzbn.e(paramMap);
          return;
        }
      }
      paramMap = ((List)paramMap).iterator();
      while (paramMap.hasNext())
      {
        Object localObject = paramMap.next();
        if (!(localObject instanceof Map))
        {
          paramMap = String.valueOf(localBuilder.build().toString());
          if (paramMap.length() != 0) {}
          for (paramMap = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(paramMap);; paramMap = new String("ArbitraryPixel: additional params contains non-map: not sending partial hit: "))
          {
            zzbn.e(paramMap);
            return;
          }
        }
        localObject = ((Map)localObject).entrySet().iterator();
        while (((Iterator)localObject).hasNext())
        {
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
          localBuilder.appendQueryParameter(localEntry.getKey().toString(), localEntry.getValue().toString());
        }
      }
    }
    paramMap = localBuilder.build().toString();
    auW.zzcad().zzob(paramMap);
    paramMap = String.valueOf(paramMap);
    if (paramMap.length() != 0) {}
    for (paramMap = "ArbitraryPixel: url = ".concat(paramMap);; paramMap = new String("ArbitraryPixel: url = "))
    {
      zzbn.v(paramMap);
      if (str == null) {
        break;
      }
      try
      {
        auV.add(str);
        zzdc.zzb(mContext, auU, str, "true");
        return;
      }
      finally {}
    }
  }
  
  boolean zznl(String paramString)
  {
    return mContext.getSharedPreferences(auU, 0).contains(paramString);
  }
  
  boolean zznm(String paramString)
  {
    return auV.contains(paramString);
  }
  
  public static abstract interface zza
  {
    public abstract zzas zzcad();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */