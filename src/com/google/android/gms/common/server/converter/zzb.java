package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import java.util.ArrayList;

public class zzb
  implements Parcelable.Creator<StringToIntConverter>
{
  static void zza(StringToIntConverter paramStringToIntConverter, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramStringToIntConverter.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 2, paramStringToIntConverter.zzato(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzaj(paramParcel, paramInt);
  }
  
  public StringToIntConverter zzcp(Parcel paramParcel)
  {
    int j = zza.zzcl(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
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
        localArrayList = zza.zzc(paramParcel, k, StringToIntConverter.Entry.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new StringToIntConverter(i, localArrayList);
  }
  
  public StringToIntConverter[] zzgm(int paramInt)
  {
    return new StringToIntConverter[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.converter.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */