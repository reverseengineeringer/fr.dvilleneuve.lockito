package com.google.android.gms.common.data;

import android.content.ContentValues;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class DataHolder$zza
{
  private final String[] vO;
  private final ArrayList<HashMap<String, Object>> vX;
  private final String vY;
  private final HashMap<Object, Integer> vZ;
  private boolean wa;
  private String wb;
  
  private DataHolder$zza(String[] paramArrayOfString, String paramString)
  {
    vO = ((String[])zzab.zzaa(paramArrayOfString));
    vX = new ArrayList();
    vY = paramString;
    vZ = new HashMap();
    wa = false;
    wb = null;
  }
  
  private int zzb(HashMap<String, Object> paramHashMap)
  {
    if (vY == null) {
      return -1;
    }
    paramHashMap = paramHashMap.get(vY);
    if (paramHashMap == null) {
      return -1;
    }
    Integer localInteger = (Integer)vZ.get(paramHashMap);
    if (localInteger == null)
    {
      vZ.put(paramHashMap, Integer.valueOf(vX.size()));
      return -1;
    }
    return localInteger.intValue();
  }
  
  public zza zza(ContentValues paramContentValues)
  {
    zzb.zzw(paramContentValues);
    HashMap localHashMap = new HashMap(paramContentValues.size());
    paramContentValues = paramContentValues.valueSet().iterator();
    while (paramContentValues.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramContentValues.next();
      localHashMap.put((String)localEntry.getKey(), localEntry.getValue());
    }
    return zza(localHashMap);
  }
  
  public zza zza(HashMap<String, Object> paramHashMap)
  {
    zzb.zzw(paramHashMap);
    int i = zzb(paramHashMap);
    if (i == -1) {
      vX.add(paramHashMap);
    }
    for (;;)
    {
      wa = false;
      return this;
      vX.remove(i);
      vX.add(i, paramHashMap);
    }
  }
  
  public DataHolder zzfq(int paramInt)
  {
    return new DataHolder(this, paramInt, null, null);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.data.DataHolder.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */