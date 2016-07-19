package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<StringToIntConverter.Entry>
{
  static void zza(StringToIntConverter.Entry paramEntry, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzcm(paramParcel);
    zzb.zzc(paramParcel, 1, versionCode);
    zzb.zza(paramParcel, 2, zr, false);
    zzb.zzc(paramParcel, 3, zs);
    zzb.zzaj(paramParcel, paramInt);
  }
  
  public StringToIntConverter.Entry zzcq(Parcel paramParcel)
  {
    int j = 0;
    int k = zza.zzcl(paramParcel);
    String str = null;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzck(paramParcel);
      switch (zza.zzgi(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        i = zza.zzg(paramParcel, m);
        break;
      case 2: 
        str = zza.zzq(paramParcel, m);
        break;
      case 3: 
        j = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new StringToIntConverter.Entry(i, str, j);
  }
  
  public StringToIntConverter.Entry[] zzgn(int paramInt)
  {
    return new StringToIntConverter.Entry[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.converter.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */