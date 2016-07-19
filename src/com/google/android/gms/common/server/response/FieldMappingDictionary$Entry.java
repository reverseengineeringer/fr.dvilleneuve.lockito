package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FieldMappingDictionary$Entry
  extends AbstractSafeParcelable
{
  public static final zzd CREATOR = new zzd();
  final String className;
  final int versionCode;
  final ArrayList<FieldMappingDictionary.FieldMapPair> zG;
  
  FieldMappingDictionary$Entry(int paramInt, String paramString, ArrayList<FieldMappingDictionary.FieldMapPair> paramArrayList)
  {
    versionCode = paramInt;
    className = paramString;
    zG = paramArrayList;
  }
  
  FieldMappingDictionary$Entry(String paramString, Map<String, FastJsonResponse.Field<?, ?>> paramMap)
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

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.response.FieldMappingDictionary.Entry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */