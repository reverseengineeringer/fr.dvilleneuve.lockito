package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzadz;
import com.google.android.gms.internal.zzadz.zza;
import com.google.android.gms.internal.zzadz.zzc;
import com.google.android.gms.internal.zzadz.zze;
import com.google.android.gms.internal.zzah.zzi;
import com.google.android.gms.internal.zzai.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzcw
{
  private static final zzcd<zzai.zza> axI = new zzcd(zzdl.zzcdq(), true);
  private final DataLayer ava;
  private final zzadz.zzc axJ;
  private final zzai axK;
  private final Map<String, zzal> axL;
  private final Map<String, zzal> axM;
  private final Map<String, zzal> axN;
  private final zzl<zzadz.zza, zzcd<zzai.zza>> axO;
  private final zzl<String, zzb> axP;
  private final Set<zzadz.zze> axQ;
  private final Map<String, zzc> axR;
  private volatile String axS;
  private int axT;
  
  public zzcw(Context paramContext, zzadz.zzc paramzzc, DataLayer paramDataLayer, zzt.zza paramzza1, zzt.zza paramzza2, zzai paramzzai)
  {
    if (paramzzc == null) {
      throw new NullPointerException("resource cannot be null");
    }
    axJ = paramzzc;
    axQ = new HashSet(paramzzc.zzcfr());
    ava = paramDataLayer;
    axK = paramzzai;
    paramzzc = new zzm.zza()
    {
      public int zza(zzadz.zza paramAnonymouszza, zzcd<zzai.zza> paramAnonymouszzcd)
      {
        return ((zzai.zza)paramAnonymouszzcd.getObject()).an();
      }
    };
    axO = new zzm().zza(1048576, paramzzc);
    paramzzc = new zzm.zza()
    {
      public int zza(String paramAnonymousString, zzcw.zzb paramAnonymouszzb)
      {
        return paramAnonymousString.length() + paramAnonymouszzb.getSize();
      }
    };
    axP = new zzm().zza(1048576, paramzzc);
    axL = new HashMap();
    zzb(new zzj(paramContext));
    zzb(new zzt(paramzza2));
    zzb(new zzx(paramDataLayer));
    zzb(new zzdm(paramContext, paramDataLayer));
    axM = new HashMap();
    zzc(new zzr());
    zzc(new zzaf());
    zzc(new zzag());
    zzc(new zzan());
    zzc(new zzao());
    zzc(new zzbj());
    zzc(new zzbk());
    zzc(new zzcm());
    zzc(new zzdf());
    axN = new HashMap();
    zza(new zzb(paramContext));
    zza(new zzc(paramContext));
    zza(new zze(paramContext));
    zza(new zzf(paramContext));
    zza(new zzg(paramContext));
    zza(new zzh(paramContext));
    zza(new zzi(paramContext));
    zza(new zzn());
    zza(new zzq(axJ.getVersion()));
    zza(new zzt(paramzza1));
    zza(new zzv(paramDataLayer));
    zza(new zzaa(paramContext));
    zza(new zzab());
    zza(new zzae());
    zza(new zzaj(this));
    zza(new zzap());
    zza(new zzaq());
    zza(new zzbd(paramContext));
    zza(new zzbf());
    zza(new zzbi());
    zza(new zzbp());
    zza(new zzbr(paramContext));
    zza(new zzce());
    zza(new zzcg());
    zza(new zzcj());
    zza(new zzcl());
    zza(new zzcn(paramContext));
    zza(new zzcx());
    zza(new zzcy());
    zza(new zzdh());
    zza(new zzdn());
    axR = new HashMap();
    paramDataLayer = axQ.iterator();
    while (paramDataLayer.hasNext())
    {
      paramzza1 = (zzadz.zze)paramDataLayer.next();
      if (paramzzai.zzcbk())
      {
        zza(paramzza1.zzcha(), paramzza1.zzchb(), "add macro");
        zza(paramzza1.zzchf(), paramzza1.zzchc(), "remove macro");
        zza(paramzza1.zzcfx(), paramzza1.zzchd(), "add tag");
        zza(paramzza1.zzcfy(), paramzza1.zzche(), "remove tag");
      }
      int i = 0;
      while (i < paramzza1.zzcha().size())
      {
        paramzza2 = (zzadz.zza)paramzza1.zzcha().get(i);
        paramzzc = "Unknown";
        paramContext = paramzzc;
        if (paramzzai.zzcbk())
        {
          paramContext = paramzzc;
          if (i < paramzza1.zzchb().size()) {
            paramContext = (String)paramzza1.zzchb().get(i);
          }
        }
        paramzzc = zzi(axR, zza(paramzza2));
        paramzzc.zza(paramzza1);
        paramzzc.zza(paramzza1, paramzza2);
        paramzzc.zza(paramzza1, paramContext);
        i += 1;
      }
      i = 0;
      while (i < paramzza1.zzchf().size())
      {
        paramzza2 = (zzadz.zza)paramzza1.zzchf().get(i);
        paramzzc = "Unknown";
        paramContext = paramzzc;
        if (paramzzai.zzcbk())
        {
          paramContext = paramzzc;
          if (i < paramzza1.zzchc().size()) {
            paramContext = (String)paramzza1.zzchc().get(i);
          }
        }
        paramzzc = zzi(axR, zza(paramzza2));
        paramzzc.zza(paramzza1);
        paramzzc.zzb(paramzza1, paramzza2);
        paramzzc.zzb(paramzza1, paramContext);
        i += 1;
      }
    }
    paramContext = axJ.zzcgx().entrySet().iterator();
    while (paramContext.hasNext())
    {
      paramzzc = (Map.Entry)paramContext.next();
      paramDataLayer = ((List)paramzzc.getValue()).iterator();
      while (paramDataLayer.hasNext())
      {
        paramzza1 = (zzadz.zza)paramDataLayer.next();
        if (!zzdl.zzk((zzai.zza)paramzza1.zzcft().get(com.google.android.gms.internal.zzag.zzqn.toString())).booleanValue()) {
          zzi(axR, (String)paramzzc.getKey()).zzb(paramzza1);
        }
      }
    }
  }
  
  private zzcd<zzai.zza> zza(zzai.zza paramzza, Set<String> paramSet, zzdo paramzzdo)
  {
    if (!zzxd) {
      return new zzcd(paramzza, true);
    }
    zzcd localzzcd1;
    switch (type)
    {
    case 5: 
    case 6: 
    default: 
      i = type;
      zzbn.e(25 + "Unknown type: " + i);
      return axI;
    case 2: 
      localzza = zzadz.zzo(paramzza);
      zzwu = new zzai.zza[zzwu.length];
      i = 0;
      while (i < zzwu.length)
      {
        localzzcd1 = zza(zzwu[i], paramSet, paramzzdo.zzxz(i));
        if (localzzcd1 == axI) {
          return axI;
        }
        zzwu[i] = ((zzai.zza)localzzcd1.getObject());
        i += 1;
      }
      return new zzcd(localzza, false);
    case 3: 
      localzza = zzadz.zzo(paramzza);
      if (zzwv.length != zzww.length)
      {
        paramzza = String.valueOf(paramzza.toString());
        if (paramzza.length() != 0) {}
        for (paramzza = "Invalid serving value: ".concat(paramzza);; paramzza = new String("Invalid serving value: "))
        {
          zzbn.e(paramzza);
          return axI;
        }
      }
      zzwv = new zzai.zza[zzwv.length];
      zzww = new zzai.zza[zzwv.length];
      i = 0;
      while (i < zzwv.length)
      {
        localzzcd1 = zza(zzwv[i], paramSet, paramzzdo.zzya(i));
        zzcd localzzcd2 = zza(zzww[i], paramSet, paramzzdo.zzyb(i));
        if ((localzzcd1 == axI) || (localzzcd2 == axI)) {
          return axI;
        }
        zzwv[i] = ((zzai.zza)localzzcd1.getObject());
        zzww[i] = ((zzai.zza)localzzcd2.getObject());
        i += 1;
      }
      return new zzcd(localzza, false);
    case 4: 
      if (paramSet.contains(zzwx))
      {
        paramzza = String.valueOf(zzwx);
        paramSet = String.valueOf(paramSet.toString());
        zzbn.e(String.valueOf(paramzza).length() + 79 + String.valueOf(paramSet).length() + "Macro cycle detected.  Current macro reference: " + paramzza + ".  Previous macro references: " + paramSet + ".");
        return axI;
      }
      paramSet.add(zzwx);
      paramzzdo = zzdp.zza(zza(zzwx, paramSet, paramzzdo.zzcby()), zzxc);
      paramSet.remove(zzwx);
      return paramzzdo;
    }
    zzai.zza localzza = zzadz.zzo(paramzza);
    zzxb = new zzai.zza[zzxb.length];
    int i = 0;
    while (i < zzxb.length)
    {
      localzzcd1 = zza(zzxb[i], paramSet, paramzzdo.zzyc(i));
      if (localzzcd1 == axI) {
        return axI;
      }
      zzxb[i] = ((zzai.zza)localzzcd1.getObject());
      i += 1;
    }
    return new zzcd(localzza, false);
  }
  
  private zzcd<zzai.zza> zza(String paramString, Set<String> paramSet, zzbq paramzzbq)
  {
    axT += 1;
    Object localObject = (zzb)axP.get(paramString);
    if ((localObject != null) && (!axK.zzcbk()))
    {
      zza(((zzb)localObject).zzccr(), paramSet);
      axT -= 1;
      return ((zzb)localObject).zzccq();
    }
    localObject = (zzc)axR.get(paramString);
    if (localObject == null)
    {
      paramSet = String.valueOf(zzccp());
      zzbn.e(String.valueOf(paramSet).length() + 15 + String.valueOf(paramString).length() + paramSet + "Invalid macro: " + paramString);
      axT -= 1;
      return axI;
    }
    zzcd localzzcd = zza(paramString, ((zzc)localObject).zzccs(), ((zzc)localObject).zzcct(), ((zzc)localObject).zzccu(), ((zzc)localObject).zzccw(), ((zzc)localObject).zzccv(), paramSet, paramzzbq.zzcaw());
    if (((Set)localzzcd.getObject()).isEmpty()) {}
    for (localObject = ((zzc)localObject).zzccx(); localObject == null; localObject = (zzadz.zza)((Set)localzzcd.getObject()).iterator().next())
    {
      axT -= 1;
      return axI;
      if (((Set)localzzcd.getObject()).size() > 1)
      {
        localObject = String.valueOf(zzccp());
        zzbn.zzcy(String.valueOf(localObject).length() + 37 + String.valueOf(paramString).length() + (String)localObject + "Multiple macros active for macroName " + paramString);
      }
    }
    paramzzbq = zza(axN, (zzadz.zza)localObject, paramSet, paramzzbq.zzcbq());
    boolean bool;
    if ((localzzcd.zzcbz()) && (paramzzbq.zzcbz()))
    {
      bool = true;
      if (paramzzbq != axI) {
        break label441;
      }
    }
    label441:
    for (paramzzbq = axI;; paramzzbq = new zzcd((zzai.zza)paramzzbq.getObject(), bool))
    {
      localObject = ((zzadz.zza)localObject).zzccr();
      if (paramzzbq.zzcbz()) {
        axP.zzi(paramString, new zzb(paramzzbq, (zzai.zza)localObject));
      }
      zza((zzai.zza)localObject, paramSet);
      axT -= 1;
      return paramzzbq;
      bool = false;
      break;
    }
  }
  
  private zzcd<zzai.zza> zza(Map<String, zzal> paramMap, zzadz.zza paramzza, Set<String> paramSet, zzco paramzzco)
  {
    boolean bool = true;
    Object localObject1 = (zzai.zza)paramzza.zzcft().get(com.google.android.gms.internal.zzag.zzow.toString());
    if (localObject1 == null)
    {
      zzbn.e("No function id in properties");
      paramMap = axI;
    }
    zzal localzzal;
    do
    {
      return paramMap;
      localObject1 = zzwy;
      localzzal = (zzal)paramMap.get(localObject1);
      if (localzzal == null)
      {
        zzbn.e(String.valueOf(localObject1).concat(" has no backing implementation."));
        return axI;
      }
      paramMap = (zzcd)axO.get(paramzza);
    } while ((paramMap != null) && (!axK.zzcbk()));
    paramMap = new HashMap();
    Iterator localIterator = paramzza.zzcft().entrySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject2 = paramzzco.zzoi((String)localEntry.getKey());
      localObject2 = zza((zzai.zza)localEntry.getValue(), paramSet, ((zzcq)localObject2).zze((zzai.zza)localEntry.getValue()));
      if (localObject2 == axI) {
        return axI;
      }
      if (((zzcd)localObject2).zzcbz()) {
        paramzza.zza((String)localEntry.getKey(), (zzai.zza)((zzcd)localObject2).getObject());
      }
      for (;;)
      {
        paramMap.put((String)localEntry.getKey(), (zzai.zza)((zzcd)localObject2).getObject());
        break;
        i = 0;
      }
    }
    if (!localzzal.zzf(paramMap.keySet()))
    {
      paramzza = String.valueOf(localzzal.zzcbm());
      paramMap = String.valueOf(paramMap.keySet());
      zzbn.e(String.valueOf(localObject1).length() + 43 + String.valueOf(paramzza).length() + String.valueOf(paramMap).length() + "Incorrect keys for function " + (String)localObject1 + " required " + paramzza + " had " + paramMap);
      return axI;
    }
    if ((i != 0) && (localzzal.zzcac())) {}
    for (;;)
    {
      paramMap = new zzcd(localzzal.zzav(paramMap), bool);
      if (bool) {
        axO.zzi(paramzza, paramMap);
      }
      paramzzco.zzd((zzai.zza)paramMap.getObject());
      return paramMap;
      bool = false;
    }
  }
  
  private zzcd<Set<zzadz.zza>> zza(Set<zzadz.zze> paramSet, Set<String> paramSet1, zza paramzza, zzcv paramzzcv)
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    paramSet = paramSet.iterator();
    boolean bool = true;
    if (paramSet.hasNext())
    {
      zzadz.zze localzze = (zzadz.zze)paramSet.next();
      zzcr localzzcr = paramzzcv.zzcbx();
      zzcd localzzcd = zza(localzze, paramSet1, localzzcr);
      if (((Boolean)localzzcd.getObject()).booleanValue()) {
        paramzza.zza(localzze, localHashSet1, localHashSet2, localzzcr);
      }
      if ((bool) && (localzzcd.zzcbz())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    localHashSet1.removeAll(localHashSet2);
    paramzzcv.zzg(localHashSet1);
    return new zzcd(localHashSet1, bool);
  }
  
  private static String zza(zzadz.zza paramzza)
  {
    return zzdl.zzg((zzai.zza)paramzza.zzcft().get(com.google.android.gms.internal.zzag.zzpi.toString()));
  }
  
  private void zza(zzai.zza paramzza, Set<String> paramSet)
  {
    if (paramzza == null) {}
    for (;;)
    {
      return;
      paramzza = zza(paramzza, paramSet, new zzcb());
      if (paramzza != axI)
      {
        paramzza = zzdl.zzl((zzai.zza)paramzza.getObject());
        if ((paramzza instanceof Map))
        {
          paramzza = (Map)paramzza;
          ava.push(paramzza);
          return;
        }
        if (!(paramzza instanceof List)) {
          break;
        }
        paramzza = ((List)paramzza).iterator();
        while (paramzza.hasNext())
        {
          paramSet = paramzza.next();
          if ((paramSet instanceof Map))
          {
            paramSet = (Map)paramSet;
            ava.push(paramSet);
          }
          else
          {
            zzbn.zzcy("pushAfterEvaluate: value not a Map");
          }
        }
      }
    }
    zzbn.zzcy("pushAfterEvaluate: value not a Map or List");
  }
  
  private static void zza(List<zzadz.zza> paramList, List<String> paramList1, String paramString)
  {
    if (paramList.size() != paramList1.size()) {
      zzbn.zzcx(String.valueOf(paramString).length() + 102 + "Invalid resource: imbalance of rule names of functions for " + paramString + " operation. Using default rule name instead");
    }
  }
  
  private static void zza(Map<String, zzal> paramMap, zzal paramzzal)
  {
    if (paramMap.containsKey(paramzzal.zzcbl()))
    {
      paramMap = String.valueOf(paramzzal.zzcbl());
      if (paramMap.length() != 0) {}
      for (paramMap = "Duplicate function type name: ".concat(paramMap);; paramMap = new String("Duplicate function type name: ")) {
        throw new IllegalArgumentException(paramMap);
      }
    }
    paramMap.put(paramzzal.zzcbl(), paramzzal);
  }
  
  private String zzccp()
  {
    if (axT <= 1) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toString(axT));
    int i = 2;
    while (i < axT)
    {
      localStringBuilder.append(' ');
      i += 1;
    }
    localStringBuilder.append(": ");
    return localStringBuilder.toString();
  }
  
  private static zzc zzi(Map<String, zzc> paramMap, String paramString)
  {
    zzc localzzc2 = (zzc)paramMap.get(paramString);
    zzc localzzc1 = localzzc2;
    if (localzzc2 == null)
    {
      localzzc1 = new zzc();
      paramMap.put(paramString, localzzc1);
    }
    return localzzc1;
  }
  
  zzcd<Boolean> zza(zzadz.zza paramzza, Set<String> paramSet, zzco paramzzco)
  {
    paramzza = zza(axM, paramzza, paramSet, paramzzco);
    paramSet = zzdl.zzk((zzai.zza)paramzza.getObject());
    paramzzco.zzd(zzdl.zzar(paramSet));
    return new zzcd(paramSet, paramzza.zzcbz());
  }
  
  zzcd<Boolean> zza(zzadz.zze paramzze, Set<String> paramSet, zzcr paramzzcr)
  {
    Object localObject = paramzze.zzcfw().iterator();
    boolean bool = true;
    if (((Iterator)localObject).hasNext())
    {
      zzcd localzzcd = zza((zzadz.zza)((Iterator)localObject).next(), paramSet, paramzzcr.zzcbr());
      if (((Boolean)localzzcd.getObject()).booleanValue())
      {
        paramzzcr.zzf(zzdl.zzar(Boolean.valueOf(false)));
        return new zzcd(Boolean.valueOf(false), localzzcd.zzcbz());
      }
      if ((bool) && (localzzcd.zzcbz())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    paramzze = paramzze.zzcfv().iterator();
    while (paramzze.hasNext())
    {
      localObject = zza((zzadz.zza)paramzze.next(), paramSet, paramzzcr.zzcbs());
      if (!((Boolean)((zzcd)localObject).getObject()).booleanValue())
      {
        paramzzcr.zzf(zzdl.zzar(Boolean.valueOf(false)));
        return new zzcd(Boolean.valueOf(false), ((zzcd)localObject).zzcbz());
      }
      if ((bool) && (((zzcd)localObject).zzcbz())) {
        bool = true;
      } else {
        bool = false;
      }
    }
    paramzzcr.zzf(zzdl.zzar(Boolean.valueOf(true)));
    return new zzcd(Boolean.valueOf(true), bool);
  }
  
  zzcd<Set<zzadz.zza>> zza(String paramString, Set<zzadz.zze> paramSet, final Map<zzadz.zze, List<zzadz.zza>> paramMap1, final Map<zzadz.zze, List<String>> paramMap2, final Map<zzadz.zze, List<zzadz.zza>> paramMap3, final Map<zzadz.zze, List<String>> paramMap4, Set<String> paramSet1, zzcv paramzzcv)
  {
    zza(paramSet, paramSet1, new zza()
    {
      public void zza(zzadz.zze paramAnonymouszze, Set<zzadz.zza> paramAnonymousSet1, Set<zzadz.zza> paramAnonymousSet2, zzcr paramAnonymouszzcr)
      {
        List localList1 = (List)paramMap1.get(paramAnonymouszze);
        List localList2 = (List)paramMap2.get(paramAnonymouszze);
        if (localList1 != null)
        {
          paramAnonymousSet1.addAll(localList1);
          paramAnonymouszzcr.zzcbt().zzc(localList1, localList2);
        }
        paramAnonymousSet1 = (List)paramMap3.get(paramAnonymouszze);
        paramAnonymouszze = (List)paramMap4.get(paramAnonymouszze);
        if (paramAnonymousSet1 != null)
        {
          paramAnonymousSet2.addAll(paramAnonymousSet1);
          paramAnonymouszzcr.zzcbu().zzc(paramAnonymousSet1, paramAnonymouszze);
        }
      }
    }, paramzzcv);
  }
  
  zzcd<Set<zzadz.zza>> zza(Set<zzadz.zze> paramSet, zzcv paramzzcv)
  {
    zza(paramSet, new HashSet(), new zza()
    {
      public void zza(zzadz.zze paramAnonymouszze, Set<zzadz.zza> paramAnonymousSet1, Set<zzadz.zza> paramAnonymousSet2, zzcr paramAnonymouszzcr)
      {
        paramAnonymousSet1.addAll(paramAnonymouszze.zzcfx());
        paramAnonymousSet2.addAll(paramAnonymouszze.zzcfy());
        paramAnonymouszzcr.zzcbv().zzc(paramAnonymouszze.zzcfx(), paramAnonymouszze.zzchd());
        paramAnonymouszzcr.zzcbw().zzc(paramAnonymouszze.zzcfy(), paramAnonymouszze.zzche());
      }
    }, paramzzcv);
  }
  
  void zza(zzal paramzzal)
  {
    zza(axN, paramzzal);
  }
  
  public void zzaj(List<zzah.zzi> paramList)
  {
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (!paramList.hasNext()) {
          break;
        }
        Object localObject = (zzah.zzi)paramList.next();
        if ((name == null) || (!name.startsWith("gaExperiment:")))
        {
          localObject = String.valueOf(localObject);
          zzbn.v(String.valueOf(localObject).length() + 22 + "Ignored supplemental: " + (String)localObject);
        }
        else
        {
          zzak.zza(ava, (zzah.zzi)localObject);
        }
      }
      finally {}
    }
  }
  
  void zzb(zzal paramzzal)
  {
    zza(axL, paramzzal);
  }
  
  void zzc(zzal paramzzal)
  {
    zza(axM, paramzzal);
  }
  
  String zzcco()
  {
    try
    {
      String str = axS;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zznq(String paramString)
  {
    try
    {
      zzon(paramString);
      paramString = axK.zzod(paramString);
      zzu localzzu = paramString.zzcbi();
      Iterator localIterator = ((Set)zza(axQ, localzzu.zzcaw()).getObject()).iterator();
      while (localIterator.hasNext())
      {
        zzadz.zza localzza = (zzadz.zza)localIterator.next();
        zza(axL, localzza, new HashSet(), localzzu.zzcav());
      }
      paramString.zzcbj();
    }
    finally {}
    zzon(null);
  }
  
  public zzcd<zzai.zza> zzom(String paramString)
  {
    axT = 0;
    zzah localzzah = axK.zzoc(paramString);
    paramString = zza(paramString, new HashSet(), localzzah.zzcbh());
    localzzah.zzcbj();
    return paramString;
  }
  
  void zzon(String paramString)
  {
    try
    {
      axS = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  static abstract interface zza
  {
    public abstract void zza(zzadz.zze paramzze, Set<zzadz.zza> paramSet1, Set<zzadz.zza> paramSet2, zzcr paramzzcr);
  }
  
  private static class zzb
  {
    private zzcd<zzai.zza> axZ;
    private zzai.zza aya;
    
    public zzb(zzcd<zzai.zza> paramzzcd, zzai.zza paramzza)
    {
      axZ = paramzzcd;
      aya = paramzza;
    }
    
    public int getSize()
    {
      int j = ((zzai.zza)axZ.getObject()).an();
      if (aya == null) {}
      for (int i = 0;; i = aya.an()) {
        return i + j;
      }
    }
    
    public zzcd<zzai.zza> zzccq()
    {
      return axZ;
    }
    
    public zzai.zza zzccr()
    {
      return aya;
    }
  }
  
  private static class zzc
  {
    private final Set<zzadz.zze> axQ = new HashSet();
    private final Map<zzadz.zze, List<zzadz.zza>> ayb = new HashMap();
    private final Map<zzadz.zze, List<zzadz.zza>> ayc = new HashMap();
    private final Map<zzadz.zze, List<String>> ayd = new HashMap();
    private final Map<zzadz.zze, List<String>> aye = new HashMap();
    private zzadz.zza ayf;
    
    public void zza(zzadz.zze paramzze)
    {
      axQ.add(paramzze);
    }
    
    public void zza(zzadz.zze paramzze, zzadz.zza paramzza)
    {
      List localList = (List)ayb.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        ayb.put(paramzze, localObject);
      }
      ((List)localObject).add(paramzza);
    }
    
    public void zza(zzadz.zze paramzze, String paramString)
    {
      List localList = (List)ayd.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        ayd.put(paramzze, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void zzb(zzadz.zza paramzza)
    {
      ayf = paramzza;
    }
    
    public void zzb(zzadz.zze paramzze, zzadz.zza paramzza)
    {
      List localList = (List)ayc.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        ayc.put(paramzze, localObject);
      }
      ((List)localObject).add(paramzza);
    }
    
    public void zzb(zzadz.zze paramzze, String paramString)
    {
      List localList = (List)aye.get(paramzze);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        aye.put(paramzze, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public Set<zzadz.zze> zzccs()
    {
      return axQ;
    }
    
    public Map<zzadz.zze, List<zzadz.zza>> zzcct()
    {
      return ayb;
    }
    
    public Map<zzadz.zze, List<String>> zzccu()
    {
      return ayd;
    }
    
    public Map<zzadz.zze, List<String>> zzccv()
    {
      return aye;
    }
    
    public Map<zzadz.zze, List<zzadz.zza>> zzccw()
    {
      return ayc;
    }
    
    public zzadz.zza zzccx()
    {
      return ayf;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */