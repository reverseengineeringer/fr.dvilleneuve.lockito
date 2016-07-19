package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzbn;
import com.google.android.gms.tagmanager.zzdl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzadz
{
  private static zza zza(zzah.zzb paramzzb, zzah.zzf paramzzf, zzai.zza[] paramArrayOfzza, int paramInt)
    throws zzadz.zzg
  {
    zzb localzzb = zza.zzcgu();
    paramzzb = zzup;
    int i = paramzzb.length;
    paramInt = 0;
    if (paramInt < i)
    {
      int j = paramzzb[paramInt];
      Object localObject = (zzah.zze)zza(zzvf, Integer.valueOf(j).intValue(), "properties");
      String str = (String)zza(zzvd, key, "keys");
      localObject = (zzai.zza)zza(paramArrayOfzza, value, "values");
      if (zzag.zzrp.toString().equals(str)) {
        localzzb.zzq((zzai.zza)localObject);
      }
      for (;;)
      {
        paramInt += 1;
        break;
        localzzb.zzb(str, (zzai.zza)localObject);
      }
    }
    return localzzb.zzcgv();
  }
  
  private static zze zza(zzah.zzg paramzzg, List<zza> paramList1, List<zza> paramList2, List<zza> paramList3, zzah.zzf paramzzf)
  {
    zzf localzzf = zze.zzcgz();
    int[] arrayOfInt = zzvt;
    int j = arrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      localzzf.zzd((zza)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
      i += 1;
    }
    arrayOfInt = zzvu;
    j = arrayOfInt.length;
    i = 0;
    while (i < j)
    {
      localzzf.zze((zza)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
      i += 1;
    }
    paramList3 = zzvv;
    j = paramList3.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzf((zza)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
      i += 1;
    }
    paramList3 = zzvx;
    j = paramList3.length;
    i = 0;
    int k;
    while (i < j)
    {
      k = paramList3[i];
      localzzf.zzqd(zzve[Integer.valueOf(k).intValue()].zzwt);
      i += 1;
    }
    paramList3 = zzvw;
    j = paramList3.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzg((zza)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
      i += 1;
    }
    paramList1 = zzvy;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      k = paramList1[i];
      localzzf.zzqe(zzve[Integer.valueOf(k).intValue()].zzwt);
      i += 1;
    }
    paramList1 = zzvz;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzh((zza)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
      i += 1;
    }
    paramList1 = zzwb;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      k = paramList1[i];
      localzzf.zzqf(zzve[Integer.valueOf(k).intValue()].zzwt);
      i += 1;
    }
    paramList1 = zzwa;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      localzzf.zzi((zza)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
      i += 1;
    }
    paramzzg = zzwc;
    j = paramzzg.length;
    i = 0;
    while (i < j)
    {
      k = paramzzg[i];
      localzzf.zzqg(zzve[Integer.valueOf(k).intValue()].zzwt);
      i += 1;
    }
    return localzzf.zzchg();
  }
  
  private static zzai.zza zza(int paramInt, zzah.zzf paramzzf, zzai.zza[] paramArrayOfzza, Set<Integer> paramSet)
    throws zzadz.zzg
  {
    int k = 0;
    int m = 0;
    int j = 0;
    if (paramSet.contains(Integer.valueOf(paramInt)))
    {
      localObject = String.valueOf(paramSet);
      zzpn(String.valueOf(localObject).length() + 90 + "Value cycle detected.  Current value reference: " + paramInt + ".  Previous value references: " + (String)localObject + ".");
    }
    zzai.zza localzza1 = (zzai.zza)zza(zzve, paramInt, "values");
    if (paramArrayOfzza[paramInt] != null) {
      return paramArrayOfzza[paramInt];
    }
    Object localObject = null;
    paramSet.add(Integer.valueOf(paramInt));
    switch (type)
    {
    }
    for (;;)
    {
      if (localObject == null)
      {
        paramzzf = String.valueOf(localzza1);
        zzpn(String.valueOf(paramzzf).length() + 15 + "Invalid value: " + paramzzf);
      }
      paramArrayOfzza[paramInt] = localObject;
      paramSet.remove(Integer.valueOf(paramInt));
      return (zzai.zza)localObject;
      localObject = zzp(localzza1);
      zzai.zza localzza2 = zzo(localzza1);
      zzwu = new zzai.zza[zzwf.length];
      int[] arrayOfInt = zzwf;
      k = arrayOfInt.length;
      int i = 0;
      for (;;)
      {
        localObject = localzza2;
        if (j >= k) {
          break;
        }
        m = arrayOfInt[j];
        zzwu[i] = zza(m, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      localzza2 = zzo(localzza1);
      localObject = zzp(localzza1);
      if (zzwg.length != zzwh.length)
      {
        i = zzwg.length;
        j = zzwh.length;
        zzpn(58 + "Uneven map keys (" + i + ") and map values (" + j + ")");
      }
      zzwv = new zzai.zza[zzwg.length];
      zzww = new zzai.zza[zzwg.length];
      arrayOfInt = zzwg;
      m = arrayOfInt.length;
      j = 0;
      i = 0;
      while (j < m)
      {
        int n = arrayOfInt[j];
        zzwv[i] = zza(n, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      arrayOfInt = zzwh;
      m = arrayOfInt.length;
      i = 0;
      j = k;
      for (;;)
      {
        localObject = localzza2;
        if (j >= m) {
          break;
        }
        k = arrayOfInt[j];
        zzww[i] = zza(k, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      localObject = zzo(localzza1);
      zzwx = zzdl.zzg(zza(zzpzzwk, paramzzf, paramArrayOfzza, paramSet));
      continue;
      localzza2 = zzo(localzza1);
      localObject = zzp(localzza1);
      zzxb = new zzai.zza[zzwj.length];
      arrayOfInt = zzwj;
      k = arrayOfInt.length;
      i = 0;
      j = m;
      for (;;)
      {
        localObject = localzza2;
        if (j >= k) {
          break;
        }
        m = arrayOfInt[j];
        zzxb[i] = zza(m, paramzzf, paramArrayOfzza, paramSet);
        j += 1;
        i += 1;
      }
      localObject = localzza1;
    }
  }
  
  private static <T> T zza(T[] paramArrayOfT, int paramInt, String paramString)
    throws zzadz.zzg
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfT.length)) {
      zzpn(String.valueOf(paramString).length() + 45 + "Index out of bounds detected: " + paramInt + " in " + paramString);
    }
    return paramArrayOfT[paramInt];
  }
  
  public static zzc zzb(zzah.zzf paramzzf)
    throws zzadz.zzg
  {
    int j = 0;
    Object localObject = new zzai.zza[zzve.length];
    int i = 0;
    while (i < zzve.length)
    {
      zza(i, paramzzf, (zzai.zza[])localObject, new HashSet(0));
      i += 1;
    }
    zzd localzzd = zzc.zzcgw();
    ArrayList localArrayList1 = new ArrayList();
    i = 0;
    while (i < zzvh.length)
    {
      localArrayList1.add(zza(zzvh[i], paramzzf, (zzai.zza[])localObject, i));
      i += 1;
    }
    ArrayList localArrayList2 = new ArrayList();
    i = 0;
    while (i < zzvi.length)
    {
      localArrayList2.add(zza(zzvi[i], paramzzf, (zzai.zza[])localObject, i));
      i += 1;
    }
    ArrayList localArrayList3 = new ArrayList();
    i = 0;
    while (i < zzvg.length)
    {
      zza localzza = zza(zzvg[i], paramzzf, (zzai.zza[])localObject, i);
      localzzd.zzc(localzza);
      localArrayList3.add(localzza);
      i += 1;
    }
    localObject = zzvj;
    int k = localObject.length;
    i = j;
    while (i < k)
    {
      localzzd.zzb(zza(localObject[i], localArrayList1, localArrayList3, localArrayList2, paramzzf));
      i += 1;
    }
    localzzd.zzqc(version);
    localzzd.zzyl(zzvr);
    return localzzd.zzcgy();
  }
  
  public static void zzc(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  public static zzai.zza zzo(zzai.zza paramzza)
  {
    zzai.zza localzza = new zzai.zza();
    type = type;
    zzxc = ((int[])zzxc.clone());
    if (zzxd) {
      zzxd = zzxd;
    }
    return localzza;
  }
  
  private static zzah.zzh zzp(zzai.zza paramzza)
    throws zzadz.zzg
  {
    if ((zzah.zzh)paramzza.zza(zzah.zzh.zzwd) == null)
    {
      String str = String.valueOf(paramzza);
      zzpn(String.valueOf(str).length() + 54 + "Expected a ServingValue and didn't get one. Value is: " + str);
    }
    return (zzah.zzh)paramzza.zza(zzah.zzh.zzwd);
  }
  
  private static void zzpn(String paramString)
    throws zzadz.zzg
  {
    zzbn.e(paramString);
    throw new zzg(paramString);
  }
  
  public static class zza
  {
    private final Map<String, zzai.zza> aCH;
    private final zzai.zza aya;
    
    private zza(Map<String, zzai.zza> paramMap, zzai.zza paramzza)
    {
      aCH = paramMap;
      aya = paramzza;
    }
    
    public static zzadz.zzb zzcgu()
    {
      return new zzadz.zzb(null);
    }
    
    public String toString()
    {
      String str1 = String.valueOf(zzcft());
      String str2 = String.valueOf(aya);
      return String.valueOf(str1).length() + 32 + String.valueOf(str2).length() + "Properties: " + str1 + " pushAfterEvaluate: " + str2;
    }
    
    public void zza(String paramString, zzai.zza paramzza)
    {
      aCH.put(paramString, paramzza);
    }
    
    public zzai.zza zzccr()
    {
      return aya;
    }
    
    public Map<String, zzai.zza> zzcft()
    {
      return Collections.unmodifiableMap(aCH);
    }
  }
  
  public static class zzb
  {
    private final Map<String, zzai.zza> aCH = new HashMap();
    private zzai.zza aya;
    
    public zzb zzb(String paramString, zzai.zza paramzza)
    {
      aCH.put(paramString, paramzza);
      return this;
    }
    
    public zzadz.zza zzcgv()
    {
      return new zzadz.zza(aCH, aya, null);
    }
    
    public zzb zzq(zzai.zza paramzza)
    {
      aya = paramzza;
      return this;
    }
  }
  
  public static class zzc
  {
    private final List<zzadz.zze> aCE;
    private final Map<String, List<zzadz.zza>> aCF;
    private final int aCG;
    private final String nZ;
    
    private zzc(List<zzadz.zze> paramList, Map<String, List<zzadz.zza>> paramMap, String paramString, int paramInt)
    {
      aCE = Collections.unmodifiableList(paramList);
      aCF = Collections.unmodifiableMap(paramMap);
      nZ = paramString;
      aCG = paramInt;
    }
    
    public static zzadz.zzd zzcgw()
    {
      return new zzadz.zzd(null);
    }
    
    public String getVersion()
    {
      return nZ;
    }
    
    public String toString()
    {
      String str1 = String.valueOf(zzcfr());
      String str2 = String.valueOf(aCF);
      return String.valueOf(str1).length() + 17 + String.valueOf(str2).length() + "Rules: " + str1 + "  Macros: " + str2;
    }
    
    public List<zzadz.zze> zzcfr()
    {
      return aCE;
    }
    
    public Map<String, List<zzadz.zza>> zzcgx()
    {
      return aCF;
    }
  }
  
  public static class zzd
  {
    private final List<zzadz.zze> aCE = new ArrayList();
    private final Map<String, List<zzadz.zza>> aCF = new HashMap();
    private int aCG = 0;
    private String nZ = "";
    
    public zzd zzb(zzadz.zze paramzze)
    {
      aCE.add(paramzze);
      return this;
    }
    
    public zzd zzc(zzadz.zza paramzza)
    {
      String str = zzdl.zzg((zzai.zza)paramzza.zzcft().get(zzag.zzpi.toString()));
      List localList = (List)aCF.get(str);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        aCF.put(str, localObject);
      }
      ((List)localObject).add(paramzza);
      return this;
    }
    
    public zzadz.zzc zzcgy()
    {
      return new zzadz.zzc(aCE, aCF, nZ, aCG, null);
    }
    
    public zzd zzqc(String paramString)
    {
      nZ = paramString;
      return this;
    }
    
    public zzd zzyl(int paramInt)
    {
      aCG = paramInt;
      return this;
    }
  }
  
  public static class zze
  {
    private final List<zzadz.zza> aCJ;
    private final List<zzadz.zza> aCK;
    private final List<zzadz.zza> aCL;
    private final List<zzadz.zza> aCM;
    private final List<zzadz.zza> aDs;
    private final List<zzadz.zza> aDt;
    private final List<String> aDu;
    private final List<String> aDv;
    private final List<String> aDw;
    private final List<String> aDx;
    
    private zze(List<zzadz.zza> paramList1, List<zzadz.zza> paramList2, List<zzadz.zza> paramList3, List<zzadz.zza> paramList4, List<zzadz.zza> paramList5, List<zzadz.zza> paramList6, List<String> paramList7, List<String> paramList8, List<String> paramList9, List<String> paramList10)
    {
      aCJ = Collections.unmodifiableList(paramList1);
      aCK = Collections.unmodifiableList(paramList2);
      aCL = Collections.unmodifiableList(paramList3);
      aCM = Collections.unmodifiableList(paramList4);
      aDs = Collections.unmodifiableList(paramList5);
      aDt = Collections.unmodifiableList(paramList6);
      aDu = Collections.unmodifiableList(paramList7);
      aDv = Collections.unmodifiableList(paramList8);
      aDw = Collections.unmodifiableList(paramList9);
      aDx = Collections.unmodifiableList(paramList10);
    }
    
    public static zzadz.zzf zzcgz()
    {
      return new zzadz.zzf(null);
    }
    
    public String toString()
    {
      String str1 = String.valueOf(zzcfv());
      String str2 = String.valueOf(zzcfw());
      String str3 = String.valueOf(zzcfx());
      String str4 = String.valueOf(zzcfy());
      String str5 = String.valueOf(zzcha());
      String str6 = String.valueOf(zzchf());
      return String.valueOf(str1).length() + 102 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length() + "Positive predicates: " + str1 + "  Negative predicates: " + str2 + "  Add tags: " + str3 + "  Remove tags: " + str4 + "  Add macros: " + str5 + "  Remove macros: " + str6;
    }
    
    public List<zzadz.zza> zzcfv()
    {
      return aCJ;
    }
    
    public List<zzadz.zza> zzcfw()
    {
      return aCK;
    }
    
    public List<zzadz.zza> zzcfx()
    {
      return aCL;
    }
    
    public List<zzadz.zza> zzcfy()
    {
      return aCM;
    }
    
    public List<zzadz.zza> zzcha()
    {
      return aDs;
    }
    
    public List<String> zzchb()
    {
      return aDu;
    }
    
    public List<String> zzchc()
    {
      return aDv;
    }
    
    public List<String> zzchd()
    {
      return aDw;
    }
    
    public List<String> zzche()
    {
      return aDx;
    }
    
    public List<zzadz.zza> zzchf()
    {
      return aDt;
    }
  }
  
  public static class zzf
  {
    private final List<zzadz.zza> aCJ = new ArrayList();
    private final List<zzadz.zza> aCK = new ArrayList();
    private final List<zzadz.zza> aCL = new ArrayList();
    private final List<zzadz.zza> aCM = new ArrayList();
    private final List<zzadz.zza> aDs = new ArrayList();
    private final List<zzadz.zza> aDt = new ArrayList();
    private final List<String> aDu = new ArrayList();
    private final List<String> aDv = new ArrayList();
    private final List<String> aDw = new ArrayList();
    private final List<String> aDx = new ArrayList();
    
    public zzadz.zze zzchg()
    {
      return new zzadz.zze(aCJ, aCK, aCL, aCM, aDs, aDt, aDu, aDv, aDw, aDx, null);
    }
    
    public zzf zzd(zzadz.zza paramzza)
    {
      aCJ.add(paramzza);
      return this;
    }
    
    public zzf zze(zzadz.zza paramzza)
    {
      aCK.add(paramzza);
      return this;
    }
    
    public zzf zzf(zzadz.zza paramzza)
    {
      aCL.add(paramzza);
      return this;
    }
    
    public zzf zzg(zzadz.zza paramzza)
    {
      aCM.add(paramzza);
      return this;
    }
    
    public zzf zzh(zzadz.zza paramzza)
    {
      aDs.add(paramzza);
      return this;
    }
    
    public zzf zzi(zzadz.zza paramzza)
    {
      aDt.add(paramzza);
      return this;
    }
    
    public zzf zzqd(String paramString)
    {
      aDw.add(paramString);
      return this;
    }
    
    public zzf zzqe(String paramString)
    {
      aDx.add(paramString);
      return this;
    }
    
    public zzf zzqf(String paramString)
    {
      aDu.add(paramString);
      return this;
    }
    
    public zzf zzqg(String paramString)
    {
      aDv.add(paramString);
      return this;
    }
  }
  
  public static class zzg
    extends Exception
  {
    public zzg(String paramString)
    {
      super();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzadz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */