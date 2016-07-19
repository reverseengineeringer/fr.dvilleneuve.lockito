package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer
{
  public static final String EVENT_KEY = "event";
  public static final Object OBJECT_NOT_PRESENT = new Object();
  static final String[] avE = "gtm.lifetime".toString().split("\\.");
  private static final Pattern avF = Pattern.compile("(\\d+)\\s*([smhd]?)");
  private final ConcurrentHashMap<zzb, Integer> avG;
  private final Map<String, Object> avH;
  private final ReentrantLock avI;
  private final LinkedList<Map<String, Object>> avJ;
  private final zzc avK;
  private final CountDownLatch avL;
  
  DataLayer()
  {
    this(new zzc()
    {
      public void zza(DataLayer.zzc.zza paramAnonymouszza)
      {
        paramAnonymouszza.zzaf(new ArrayList());
      }
      
      public void zza(List<DataLayer.zza> paramAnonymousList, long paramAnonymousLong) {}
      
      public void zzny(String paramAnonymousString) {}
    });
  }
  
  DataLayer(zzc paramzzc)
  {
    avK = paramzzc;
    avG = new ConcurrentHashMap();
    avH = new HashMap();
    avI = new ReentrantLock();
    avJ = new LinkedList();
    avL = new CountDownLatch(1);
    zzcat();
  }
  
  public static List<Object> listOf(Object... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramVarArgs.length)
    {
      localArrayList.add(paramVarArgs[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public static Map<String, Object> mapOf(Object... paramVarArgs)
  {
    if (paramVarArgs.length % 2 != 0) {
      throw new IllegalArgumentException("expected even number of key-value pairs");
    }
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramVarArgs.length)
    {
      if (!(paramVarArgs[i] instanceof String))
      {
        paramVarArgs = String.valueOf(paramVarArgs[i]);
        throw new IllegalArgumentException(String.valueOf(paramVarArgs).length() + 21 + "key is not a string: " + paramVarArgs);
      }
      localHashMap.put((String)paramVarArgs[i], paramVarArgs[(i + 1)]);
      i += 2;
    }
    return localHashMap;
  }
  
  private void zza(Map<String, Object> paramMap, String paramString, Collection<zza> paramCollection)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (paramString.length() == 0) {}
      for (paramMap = "";; paramMap = ".")
      {
        String str = (String)localEntry.getKey();
        paramMap = String.valueOf(paramString).length() + 0 + String.valueOf(paramMap).length() + String.valueOf(str).length() + paramString + paramMap + str;
        if (!(localEntry.getValue() instanceof Map)) {
          break label145;
        }
        zza((Map)localEntry.getValue(), paramMap, paramCollection);
        break;
      }
      label145:
      if (!paramMap.equals("gtm.lifetime")) {
        paramCollection.add(new zza(paramMap, localEntry.getValue()));
      }
    }
  }
  
  private void zzay(Map<String, Object> paramMap)
  {
    avI.lock();
    try
    {
      avJ.offer(paramMap);
      if (avI.getHoldCount() == 1) {
        zzcau();
      }
      zzaz(paramMap);
      return;
    }
    finally
    {
      avI.unlock();
    }
  }
  
  private void zzaz(Map<String, Object> paramMap)
  {
    Long localLong = zzba(paramMap);
    if (localLong == null) {
      return;
    }
    paramMap = zzbc(paramMap);
    paramMap.remove("gtm.lifetime");
    avK.zza(paramMap, localLong.longValue());
  }
  
  private Long zzba(Map<String, Object> paramMap)
  {
    paramMap = zzbb(paramMap);
    if (paramMap == null) {
      return null;
    }
    return zznx(paramMap.toString());
  }
  
  private Object zzbb(Map<String, Object> paramMap)
  {
    String[] arrayOfString = avE;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      Object localObject = paramMap;
      if (i < j)
      {
        localObject = arrayOfString[i];
        if (!(paramMap instanceof Map)) {
          localObject = null;
        }
      }
      else
      {
        return localObject;
      }
      paramMap = ((Map)paramMap).get(localObject);
      i += 1;
    }
  }
  
  private List<zza> zzbc(Map<String, Object> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    zza(paramMap, "", localArrayList);
    return localArrayList;
  }
  
  private void zzbd(Map<String, Object> paramMap)
  {
    synchronized (avH)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        zzd(zzo(str, paramMap.get(str)), avH);
      }
    }
    zzbe(paramMap);
  }
  
  private void zzbe(Map<String, Object> paramMap)
  {
    Iterator localIterator = avG.keySet().iterator();
    while (localIterator.hasNext()) {
      ((zzb)localIterator.next()).zzaw(paramMap);
    }
  }
  
  private void zzcat()
  {
    avK.zza(new DataLayer.zzc.zza()
    {
      public void zzaf(List<DataLayer.zza> paramAnonymousList)
      {
        paramAnonymousList = paramAnonymousList.iterator();
        while (paramAnonymousList.hasNext())
        {
          DataLayer.zza localzza = (DataLayer.zza)paramAnonymousList.next();
          DataLayer.zza(DataLayer.this, zzo(zzaxn, zzcnr));
        }
        DataLayer.zza(DataLayer.this).countDown();
      }
    });
  }
  
  private void zzcau()
  {
    int i = 0;
    for (;;)
    {
      Map localMap = (Map)avJ.poll();
      if (localMap != null)
      {
        zzbd(localMap);
        i += 1;
        if (i > 500)
        {
          avJ.clear();
          throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
        }
      }
      else
      {
        return;
      }
    }
  }
  
  static Long zznx(String paramString)
  {
    Matcher localMatcher = avF.matcher(paramString);
    if (!localMatcher.matches())
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {}
      for (paramString = "unknown _lifetime: ".concat(paramString);; paramString = new String("unknown _lifetime: "))
      {
        zzbn.zzcx(paramString);
        return null;
      }
    }
    long l;
    try
    {
      l = Long.parseLong(localMatcher.group(1));
      if (l <= 0L)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0)
        {
          paramString = "non-positive _lifetime: ".concat(paramString);
          zzbn.zzcx(paramString);
          return null;
        }
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        str = String.valueOf(paramString);
        if (str.length() != 0) {}
        for (str = "illegal number in _lifetime value: ".concat(str);; str = new String("illegal number in _lifetime value: "))
        {
          zzbn.zzcy(str);
          l = 0L;
          break;
        }
        paramString = new String("non-positive _lifetime: ");
      }
      String str = localMatcher.group(2);
      if (str.length() == 0) {
        return Long.valueOf(l);
      }
      switch (str.charAt(0))
      {
      default: 
        paramString = String.valueOf(paramString);
        if (paramString.length() == 0) {}
        break;
      }
    }
    for (paramString = "unknown units in _lifetime: ".concat(paramString);; paramString = new String("unknown units in _lifetime: "))
    {
      zzbn.zzcy(paramString);
      return null;
      return Long.valueOf(l * 1000L);
      return Long.valueOf(l * 1000L * 60L);
      return Long.valueOf(l * 1000L * 60L * 60L);
      return Long.valueOf(l * 1000L * 60L * 60L * 24L);
    }
  }
  
  public Object get(String paramString)
  {
    for (;;)
    {
      int i;
      synchronized (avH)
      {
        Map localMap1 = avH;
        String[] arrayOfString = paramString.split("\\.");
        int j = arrayOfString.length;
        paramString = localMap1;
        i = 0;
        if (i < j)
        {
          localMap1 = arrayOfString[i];
          if (!(paramString instanceof Map)) {
            return null;
          }
          paramString = ((Map)paramString).get(localMap1);
          if (paramString == null) {
            return null;
          }
        }
        else
        {
          return paramString;
        }
      }
      i += 1;
    }
  }
  
  public void push(String paramString, Object paramObject)
  {
    push(zzo(paramString, paramObject));
  }
  
  public void push(Map<String, Object> paramMap)
  {
    try
    {
      avL.await();
      zzay(paramMap);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        zzbn.zzcy("DataLayer.push: unexpected InterruptedException");
      }
    }
  }
  
  public void pushEvent(String paramString, Map<String, Object> paramMap)
  {
    paramMap = new HashMap(paramMap);
    paramMap.put("event", paramString);
    push(paramMap);
  }
  
  public String toString()
  {
    synchronized (avH)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = avH.entrySet().iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localStringBuilder.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[] { localEntry.getKey(), localEntry.getValue() }));
      }
    }
    String str = ((StringBuilder)localObject).toString();
    return str;
  }
  
  void zza(zzb paramzzb)
  {
    avG.put(paramzzb, Integer.valueOf(0));
  }
  
  void zzb(List<Object> paramList1, List<Object> paramList2)
  {
    while (paramList2.size() < paramList1.size()) {
      paramList2.add(null);
    }
    int i = 0;
    if (i < paramList1.size())
    {
      Object localObject = paramList1.get(i);
      if ((localObject instanceof List))
      {
        if (!(paramList2.get(i) instanceof List)) {
          paramList2.set(i, new ArrayList());
        }
        zzb((List)localObject, (List)paramList2.get(i));
      }
      for (;;)
      {
        i += 1;
        break;
        if ((localObject instanceof Map))
        {
          if (!(paramList2.get(i) instanceof Map)) {
            paramList2.set(i, new HashMap());
          }
          zzd((Map)localObject, (Map)paramList2.get(i));
        }
        else if (localObject != OBJECT_NOT_PRESENT)
        {
          paramList2.set(i, localObject);
        }
      }
    }
  }
  
  void zzd(Map<String, Object> paramMap1, Map<String, Object> paramMap2)
  {
    Iterator localIterator = paramMap1.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramMap1.get(str);
      if ((localObject instanceof List))
      {
        if (!(paramMap2.get(str) instanceof List)) {
          paramMap2.put(str, new ArrayList());
        }
        zzb((List)localObject, (List)paramMap2.get(str));
      }
      else if ((localObject instanceof Map))
      {
        if (!(paramMap2.get(str) instanceof Map)) {
          paramMap2.put(str, new HashMap());
        }
        zzd((Map)localObject, (Map)paramMap2.get(str));
      }
      else
      {
        paramMap2.put(str, localObject);
      }
    }
  }
  
  void zznw(String paramString)
  {
    push(paramString, null);
    avK.zzny(paramString);
  }
  
  Map<String, Object> zzo(String paramString, Object paramObject)
  {
    HashMap localHashMap1 = new HashMap();
    String[] arrayOfString = paramString.toString().split("\\.");
    int i = 0;
    HashMap localHashMap2;
    for (paramString = localHashMap1; i < arrayOfString.length - 1; paramString = localHashMap2)
    {
      localHashMap2 = new HashMap();
      paramString.put(arrayOfString[i], localHashMap2);
      i += 1;
    }
    paramString.put(arrayOfString[(arrayOfString.length - 1)], paramObject);
    return localHashMap1;
  }
  
  static final class zza
  {
    public final String zzaxn;
    public final Object zzcnr;
    
    zza(String paramString, Object paramObject)
    {
      zzaxn = paramString;
      zzcnr = paramObject;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zza)) {}
      do
      {
        return false;
        paramObject = (zza)paramObject;
      } while ((!zzaxn.equals(zzaxn)) || (!zzcnr.equals(zzcnr)));
      return true;
    }
    
    public int hashCode()
    {
      return Arrays.hashCode(new Integer[] { Integer.valueOf(zzaxn.hashCode()), Integer.valueOf(zzcnr.hashCode()) });
    }
    
    public String toString()
    {
      String str1 = zzaxn;
      String str2 = String.valueOf(zzcnr.toString());
      return String.valueOf(str1).length() + 13 + String.valueOf(str2).length() + "Key: " + str1 + " value: " + str2;
    }
  }
  
  static abstract interface zzb
  {
    public abstract void zzaw(Map<String, Object> paramMap);
  }
  
  static abstract interface zzc
  {
    public abstract void zza(zza paramzza);
    
    public abstract void zza(List<DataLayer.zza> paramList, long paramLong);
    
    public abstract void zzny(String paramString);
    
    public static abstract interface zza
    {
      public abstract void zzaf(List<DataLayer.zza> paramList);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.DataLayer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */