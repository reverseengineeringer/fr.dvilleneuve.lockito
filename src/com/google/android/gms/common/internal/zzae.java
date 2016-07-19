package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzae
  implements Parcelable.Creator<SignInButtonConfig>
{
  static void zza(SignInButtonConfig paramSignInButtonConfig, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzcm(paramParcel);
    zzb.zzc(paramParcel, 1, mVersionCode);
    zzb.zzc(paramParcel, 2, paramSignInButtonConfig.zzatg());
    zzb.zzc(paramParcel, 3, paramSignInButtonConfig.zzath());
    zzb.zza(paramParcel, 4, paramSignInButtonConfig.zzati(), paramInt, false);
    zzb.zzaj(paramParcel, i);
  }
  
  public SignInButtonConfig zzci(Parcel paramParcel)
  {
    int k = 0;
    int m = zza.zzcl(paramParcel);
    Scope[] arrayOfScope = null;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = zza.zzck(paramParcel);
      switch (zza.zzgi(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        i = zza.zzg(paramParcel, n);
        break;
      case 2: 
        j = zza.zzg(paramParcel, n);
        break;
      case 3: 
        k = zza.zzg(paramParcel, n);
        break;
      case 4: 
        arrayOfScope = (Scope[])zza.zzb(paramParcel, n, Scope.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new zza.zza(37 + "Overread allowed size end=" + m, paramParcel);
    }
    return new SignInButtonConfig(i, j, k, arrayOfScope);
  }
  
  public SignInButtonConfig[] zzgg(int paramInt)
  {
    return new SignInButtonConfig[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.zzae
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */