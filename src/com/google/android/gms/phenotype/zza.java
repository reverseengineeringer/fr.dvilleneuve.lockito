package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<Configuration>
{
  static void zza(Configuration paramConfiguration, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzcm(paramParcel);
    zzb.zzc(paramParcel, 1, mVersionCode);
    zzb.zzc(paramParcel, 2, arY);
    zzb.zza(paramParcel, 3, arZ, paramInt, false);
    zzb.zza(paramParcel, 4, asa, false);
    zzb.zzaj(paramParcel, i);
  }
  
  public Configuration zzpu(Parcel paramParcel)
  {
    String[] arrayOfString = null;
    int j = 0;
    int m = com.google.android.gms.common.internal.safeparcel.zza.zzcl(paramParcel);
    Flag[] arrayOfFlag = null;
    int i = 0;
    if (paramParcel.dataPosition() < m)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzck(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzgi(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        k = j;
        j = i;
        i = k;
      }
      for (;;)
      {
        k = j;
        j = i;
        i = k;
        break;
        k = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        i = j;
        j = k;
        continue;
        k = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        j = i;
        i = k;
        continue;
        arrayOfFlag = (Flag[])com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k, Flag.CREATOR);
        k = i;
        i = j;
        j = k;
        continue;
        arrayOfString = com.google.android.gms.common.internal.safeparcel.zza.zzac(paramParcel, k);
        k = i;
        i = j;
        j = k;
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new Configuration(i, j, arrayOfFlag, arrayOfString);
  }
  
  public Configuration[] zzwx(int paramInt)
  {
    return new Configuration[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.phenotype.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */