package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzd
  implements Parcelable.Creator<FieldMappingDictionary.Entry>
{
  static void zza(FieldMappingDictionary.Entry paramEntry, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzcm(paramParcel);
    zzb.zzc(paramParcel, 1, versionCode);
    zzb.zza(paramParcel, 2, className, false);
    zzb.zzc(paramParcel, 3, zG, false);
    zzb.zzaj(paramParcel, paramInt);
  }
  
  public FieldMappingDictionary.Entry zzcu(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int j = zza.zzcl(paramParcel);
    int i = 0;
    String str = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzck(paramParcel);
      switch (zza.zzgi(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str = zza.zzq(paramParcel, k);
        break;
      case 3: 
        localArrayList = zza.zzc(paramParcel, k, FieldMappingDictionary.FieldMapPair.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new FieldMappingDictionary.Entry(i, str, localArrayList);
  }
  
  public FieldMappingDictionary.Entry[] zzgr(int paramInt)
  {
    return new FieldMappingDictionary.Entry[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.response.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */