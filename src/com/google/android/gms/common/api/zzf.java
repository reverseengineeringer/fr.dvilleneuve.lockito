package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzf
  implements Parcelable.Creator<Status>
{
  static void zza(Status paramStatus, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzcm(paramParcel);
    zzb.zzc(paramParcel, 1, paramStatus.getStatusCode());
    zzb.zza(paramParcel, 2, paramStatus.getStatusMessage(), false);
    zzb.zza(paramParcel, 3, paramStatus.zzaoi(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramStatus.getVersionCode());
    zzb.zzaj(paramParcel, i);
  }
  
  public Status zzbz(Parcel paramParcel)
  {
    PendingIntent localPendingIntent = null;
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
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        str = zza.zzq(paramParcel, m);
        break;
      case 3: 
        localPendingIntent = (PendingIntent)zza.zza(paramParcel, m, PendingIntent.CREATOR);
        break;
      case 1000: 
        i = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza(37 + "Overread allowed size end=" + k, paramParcel);
    }
    return new Status(i, j, str, localPendingIntent);
  }
  
  public Status[] zzfe(int paramInt)
  {
    return new Status[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.zzf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */