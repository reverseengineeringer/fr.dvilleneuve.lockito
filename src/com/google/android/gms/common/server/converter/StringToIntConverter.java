package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class StringToIntConverter
  extends AbstractSafeParcelable
  implements FastJsonResponse.zza<String, Integer>
{
  public static final zzb CREATOR = new zzb();
  private final int mVersionCode;
  private final HashMap<String, Integer> zo;
  private final SparseArray<String> zp;
  private final ArrayList<Entry> zq;
  
  public StringToIntConverter()
  {
    mVersionCode = 1;
    zo = new HashMap();
    zp = new SparseArray();
    zq = null;
  }
  
  StringToIntConverter(int paramInt, ArrayList<Entry> paramArrayList)
  {
    mVersionCode = paramInt;
    zo = new HashMap();
    zp = new SparseArray();
    zq = null;
    zzh(paramArrayList);
  }
  
  private void zzh(ArrayList<Entry> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Entry localEntry = (Entry)paramArrayList.next();
      zzi(zr, zs);
    }
  }
  
  int getVersionCode()
  {
    return mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb localzzb = CREATOR;
    zzb.zza(this, paramParcel, paramInt);
  }
  
  ArrayList<Entry> zzato()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = zo.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new Entry(str, ((Integer)zo.get(str)).intValue()));
    }
    return localArrayList;
  }
  
  public int zzatp()
  {
    return 7;
  }
  
  public int zzatq()
  {
    return 0;
  }
  
  public String zzd(Integer paramInteger)
  {
    String str = (String)zp.get(paramInteger.intValue());
    paramInteger = str;
    if (str == null)
    {
      paramInteger = str;
      if (zo.containsKey("gms_unknown")) {
        paramInteger = "gms_unknown";
      }
    }
    return paramInteger;
  }
  
  public StringToIntConverter zzi(String paramString, int paramInt)
  {
    zo.put(paramString, Integer.valueOf(paramInt));
    zp.put(paramInt, paramString);
    return this;
  }
  
  public static final class Entry
    extends AbstractSafeParcelable
  {
    public static final zzc CREATOR = new zzc();
    final int versionCode;
    final String zr;
    final int zs;
    
    Entry(int paramInt1, String paramString, int paramInt2)
    {
      versionCode = paramInt1;
      zr = paramString;
      zs = paramInt2;
    }
    
    Entry(String paramString, int paramInt)
    {
      versionCode = 1;
      zr = paramString;
      zs = paramInt;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzc localzzc = CREATOR;
      zzc.zza(this, paramParcel, paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.converter.StringToIntConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */