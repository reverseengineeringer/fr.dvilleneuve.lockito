package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<UserProfileChangeRequest>
{
  static void zza(UserProfileChangeRequest paramUserProfileChangeRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzcm(paramParcel);
    zzb.zzc(paramParcel, 1, mVersionCode);
    zzb.zza(paramParcel, 2, paramUserProfileChangeRequest.getDisplayName(), false);
    zzb.zza(paramParcel, 3, paramUserProfileChangeRequest.zzckv(), false);
    zzb.zza(paramParcel, 4, paramUserProfileChangeRequest.zzckw());
    zzb.zza(paramParcel, 5, paramUserProfileChangeRequest.zzckx());
    zzb.zzaj(paramParcel, paramInt);
  }
  
  public UserProfileChangeRequest[] zzacw(int paramInt)
  {
    return new UserProfileChangeRequest[paramInt];
  }
  
  public UserProfileChangeRequest zzup(Parcel paramParcel)
  {
    String str1 = null;
    boolean bool1 = false;
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzcl(paramParcel);
    boolean bool2 = false;
    String str2 = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzck(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzgi(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        break;
      case 1: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        break;
      case 2: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(paramParcel, k);
        break;
      case 3: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzq(paramParcel, k);
        break;
      case 4: 
        bool2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, k);
        break;
      case 5: 
        bool1 = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new UserProfileChangeRequest(i, str2, str1, bool2, bool1);
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.auth.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */