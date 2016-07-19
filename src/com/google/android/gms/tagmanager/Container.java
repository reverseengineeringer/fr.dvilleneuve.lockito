package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzadz;
import com.google.android.gms.internal.zzadz.zzc;
import com.google.android.gms.internal.zzadz.zzg;
import com.google.android.gms.internal.zzah.zzf;
import com.google.android.gms.internal.zzah.zzi;
import com.google.android.gms.internal.zzah.zzj;
import com.google.android.gms.internal.zzai.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container
{
  private final String auZ;
  private final DataLayer ava;
  private zzcw avb;
  private Map<String, FunctionCallMacroCallback> avc = new HashMap();
  private Map<String, FunctionCallTagCallback> avd = new HashMap();
  private volatile long ave;
  private volatile String avf = "";
  private final Context mContext;
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, zzadz.zzc paramzzc)
  {
    mContext = paramContext;
    ava = paramDataLayer;
    auZ = paramString;
    ave = paramLong;
    zza(paramzzc);
  }
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, zzah.zzj paramzzj)
  {
    mContext = paramContext;
    ava = paramDataLayer;
    auZ = paramString;
    ave = paramLong;
    zza(zzwq);
    if (zzwp != null) {
      zza(zzwp);
    }
  }
  
  private void zza(zzadz.zzc paramzzc)
  {
    avf = paramzzc.getVersion();
    zzai localzzai = zznr(avf);
    zza(new zzcw(mContext, paramzzc, ava, new zza(null), new zzb(null), localzzai));
    if (getBoolean("_gtm.loadEventEnabled")) {
      ava.pushEvent("gtm.load", DataLayer.mapOf(new Object[] { "gtm.id", auZ }));
    }
  }
  
  private void zza(zzah.zzf paramzzf)
  {
    if (paramzzf == null) {
      throw new NullPointerException();
    }
    try
    {
      zzadz.zzc localzzc = zzadz.zzb(paramzzf);
      zza(localzzc);
      return;
    }
    catch (zzadz.zzg localzzg)
    {
      paramzzf = String.valueOf(paramzzf);
      String str = String.valueOf(localzzg.toString());
      zzbn.e(String.valueOf(paramzzf).length() + 46 + String.valueOf(str).length() + "Not loading resource: " + paramzzf + " because it is invalid: " + str);
    }
  }
  
  private void zza(zzcw paramzzcw)
  {
    try
    {
      avb = paramzzcw;
      return;
    }
    finally
    {
      paramzzcw = finally;
      throw paramzzcw;
    }
  }
  
  private void zza(zzah.zzi[] paramArrayOfzzi)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfzzi.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(paramArrayOfzzi[i]);
      i += 1;
    }
    zzcai().zzaj(localArrayList);
  }
  
  private zzcw zzcai()
  {
    try
    {
      zzcw localzzcw = avb;
      return localzzcw;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean getBoolean(String paramString)
  {
    zzcw localzzcw = zzcai();
    if (localzzcw == null)
    {
      zzbn.e("getBoolean called for closed container.");
      return zzdl.zzcdn().booleanValue();
    }
    try
    {
      boolean bool = zzdl.zzk((zzai.zza)localzzcw.zzom(paramString).getObject()).booleanValue();
      return bool;
    }
    catch (Exception paramString)
    {
      paramString = String.valueOf(paramString.getMessage());
      zzbn.e(String.valueOf(paramString).length() + 66 + "Calling getBoolean() threw an exception: " + paramString + " Returning default value.");
    }
    return zzdl.zzcdn().booleanValue();
  }
  
  public String getContainerId()
  {
    return auZ;
  }
  
  public double getDouble(String paramString)
  {
    zzcw localzzcw = zzcai();
    if (localzzcw == null)
    {
      zzbn.e("getDouble called for closed container.");
      return zzdl.zzcdm().doubleValue();
    }
    try
    {
      double d = zzdl.zzj((zzai.zza)localzzcw.zzom(paramString).getObject()).doubleValue();
      return d;
    }
    catch (Exception paramString)
    {
      paramString = String.valueOf(paramString.getMessage());
      zzbn.e(String.valueOf(paramString).length() + 65 + "Calling getDouble() threw an exception: " + paramString + " Returning default value.");
    }
    return zzdl.zzcdm().doubleValue();
  }
  
  public long getLastRefreshTime()
  {
    return ave;
  }
  
  public long getLong(String paramString)
  {
    zzcw localzzcw = zzcai();
    if (localzzcw == null)
    {
      zzbn.e("getLong called for closed container.");
      return zzdl.zzcdl().longValue();
    }
    try
    {
      long l = zzdl.zzi((zzai.zza)localzzcw.zzom(paramString).getObject()).longValue();
      return l;
    }
    catch (Exception paramString)
    {
      paramString = String.valueOf(paramString.getMessage());
      zzbn.e(String.valueOf(paramString).length() + 63 + "Calling getLong() threw an exception: " + paramString + " Returning default value.");
    }
    return zzdl.zzcdl().longValue();
  }
  
  public String getString(String paramString)
  {
    zzcw localzzcw = zzcai();
    if (localzzcw == null)
    {
      zzbn.e("getString called for closed container.");
      return zzdl.zzcdp();
    }
    try
    {
      paramString = zzdl.zzg((zzai.zza)localzzcw.zzom(paramString).getObject());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString = String.valueOf(paramString.getMessage());
      zzbn.e(String.valueOf(paramString).length() + 65 + "Calling getString() threw an exception: " + paramString + " Returning default value.");
    }
    return zzdl.zzcdp();
  }
  
  public boolean isDefault()
  {
    return getLastRefreshTime() == 0L;
  }
  
  public void registerFunctionCallMacroCallback(String paramString, FunctionCallMacroCallback paramFunctionCallMacroCallback)
  {
    if (paramFunctionCallMacroCallback == null) {
      throw new NullPointerException("Macro handler must be non-null");
    }
    synchronized (avc)
    {
      avc.put(paramString, paramFunctionCallMacroCallback);
      return;
    }
  }
  
  public void registerFunctionCallTagCallback(String paramString, FunctionCallTagCallback paramFunctionCallTagCallback)
  {
    if (paramFunctionCallTagCallback == null) {
      throw new NullPointerException("Tag callback must be non-null");
    }
    synchronized (avd)
    {
      avd.put(paramString, paramFunctionCallTagCallback);
      return;
    }
  }
  
  void release()
  {
    avb = null;
  }
  
  public void unregisterFunctionCallMacroCallback(String paramString)
  {
    synchronized (avc)
    {
      avc.remove(paramString);
      return;
    }
  }
  
  public void unregisterFunctionCallTagCallback(String paramString)
  {
    synchronized (avd)
    {
      avd.remove(paramString);
      return;
    }
  }
  
  public String zzcah()
  {
    return avf;
  }
  
  FunctionCallMacroCallback zzno(String paramString)
  {
    synchronized (avc)
    {
      paramString = (FunctionCallMacroCallback)avc.get(paramString);
      return paramString;
    }
  }
  
  public FunctionCallTagCallback zznp(String paramString)
  {
    synchronized (avd)
    {
      paramString = (FunctionCallTagCallback)avd.get(paramString);
      return paramString;
    }
  }
  
  public void zznq(String paramString)
  {
    zzcai().zznq(paramString);
  }
  
  zzai zznr(String paramString)
  {
    if (zzci.zzcce().zzccf().equals(zzci.zza.axp)) {}
    return new zzbv();
  }
  
  public static abstract interface FunctionCallMacroCallback
  {
    public abstract Object getValue(String paramString, Map<String, Object> paramMap);
  }
  
  public static abstract interface FunctionCallTagCallback
  {
    public abstract void execute(String paramString, Map<String, Object> paramMap);
  }
  
  private class zza
    implements zzt.zza
  {
    private zza() {}
    
    public Object zzd(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallMacroCallback localFunctionCallMacroCallback = zzno(paramString);
      if (localFunctionCallMacroCallback == null) {
        return null;
      }
      return localFunctionCallMacroCallback.getValue(paramString, paramMap);
    }
  }
  
  private class zzb
    implements zzt.zza
  {
    private zzb() {}
    
    public Object zzd(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallTagCallback localFunctionCallTagCallback = zznp(paramString);
      if (localFunctionCallTagCallback != null) {
        localFunctionCallTagCallback.execute(paramString, paramMap);
      }
      return zzdl.zzcdp();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.Container
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */