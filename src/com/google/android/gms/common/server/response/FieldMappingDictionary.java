package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FieldMappingDictionary
  extends AbstractSafeParcelable
{
  public static final zzc CREATOR = new zzc();
  private final int mVersionCode;
  private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zD;
  private final ArrayList<Entry> zE;
  private final String zF;
  
  FieldMappingDictionary(int paramInt, ArrayList<Entry> paramArrayList, String paramString)
  {
    mVersionCode = paramInt;
    zE = null;
    zD = zzi(paramArrayList);
    zF = ((String)zzab.zzaa(paramString));
    zzaud();
  }
  
  private static HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzi(ArrayList<Entry> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      Entry localEntry = (Entry)paramArrayList.get(i);
      localHashMap.put(className, localEntry.zzaug());
      i += 1;
    }
    return localHashMap;
  }
  
  int getVersionCode()
  {
    return mVersionCode;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = zD.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localStringBuilder.append((String)localObject).append(":\n");
      localObject = (Map)zD.get(localObject);
      Iterator localIterator2 = ((Map)localObject).keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        localStringBuilder.append("  ").append(str).append(": ");
        localStringBuilder.append(((Map)localObject).get(str));
      }
    }
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc localzzc = CREATOR;
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public void zzaud()
  {
    Iterator localIterator1 = zD.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localObject = (Map)zD.get(localObject);
      Iterator localIterator2 = ((Map)localObject).keySet().iterator();
      while (localIterator2.hasNext()) {
        ((FastJsonResponse.Field)((Map)localObject).get((String)localIterator2.next())).zza(this);
      }
    }
  }
  
  ArrayList<Entry> zzaue()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = zD.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new Entry(str, (Map)zD.get(str)));
    }
    return localArrayList;
  }
  
  public String zzauf()
  {
    return zF;
  }
  
  public Map<String, FastJsonResponse.Field<?, ?>> zzhx(String paramString)
  {
    return (Map)zD.get(paramString);
  }
  
  public static class Entry
    extends AbstractSafeParcelable
  {
    public static final zzd CREATOR = new zzd();
    final String className;
    final int versionCode;
    final ArrayList<FieldMappingDictionary.FieldMapPair> zG;
    
    Entry(int paramInt, String paramString, ArrayList<FieldMappingDictionary.FieldMapPair> paramArrayList)
    {
      versionCode = paramInt;
      className = paramString;
      zG = paramArrayList;
    }
    
    Entry(String paramString, Map<String, FastJsonResponse.Field<?, ?>> paramMap)
    {
      versionCode = 1;
      className = paramString;
      zG = zzat(paramMap);
    }
    
    private static ArrayList<FieldMappingDictionary.FieldMapPair> zzat(Map<String, FastJsonResponse.Field<?, ?>> paramMap)
    {
      if (paramMap == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localArrayList.add(new FieldMappingDictionary.FieldMapPair(str, (FastJsonResponse.Field)paramMap.get(str)));
      }
      return localArrayList;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzd localzzd = CREATOR;
      zzd.zza(this, paramParcel, paramInt);
    }
    
    HashMap<String, FastJsonResponse.Field<?, ?>> zzaug()
    {
      HashMap localHashMap = new HashMap();
      int j = zG.size();
      int i = 0;
      while (i < j)
      {
        FieldMappingDictionary.FieldMapPair localFieldMapPair = (FieldMappingDictionary.FieldMapPair)zG.get(i);
        localHashMap.put(zzcb, zH);
        i += 1;
      }
      return localHashMap;
    }
  }
  
  public static class FieldMapPair
    extends AbstractSafeParcelable
  {
    public static final zzb CREATOR = new zzb();
    final int versionCode;
    final FastJsonResponse.Field<?, ?> zH;
    final String zzcb;
    
    FieldMapPair(int paramInt, String paramString, FastJsonResponse.Field<?, ?> paramField)
    {
      versionCode = paramInt;
      zzcb = paramString;
      zH = paramField;
    }
    
    FieldMapPair(String paramString, FastJsonResponse.Field<?, ?> paramField)
    {
      versionCode = 1;
      zzcb = paramString;
      zH = paramField;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzb localzzb = CREATOR;
      zzb.zza(this, paramParcel, paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.response.FieldMappingDictionary
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */