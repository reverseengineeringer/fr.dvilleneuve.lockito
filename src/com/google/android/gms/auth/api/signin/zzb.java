package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import java.util.ArrayList;

public class zzb
  implements Parcelable.Creator<GoogleSignInOptions>
{
  static void zza(GoogleSignInOptions paramGoogleSignInOptions, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, versionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 2, paramGoogleSignInOptions.zzafq(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramGoogleSignInOptions.getAccount(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramGoogleSignInOptions.zzafr());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 5, paramGoogleSignInOptions.zzafs());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 6, paramGoogleSignInOptions.zzaft());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 7, paramGoogleSignInOptions.zzafu(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 8, paramGoogleSignInOptions.zzafv(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzaj(paramParcel, i);
  }
  
  public GoogleSignInOptions zzat(Parcel paramParcel)
  {
    String str1 = null;
    boolean bool1 = false;
    int j = zza.zzcl(paramParcel);
    String str2 = null;
    boolean bool2 = false;
    boolean bool3 = false;
    Account localAccount = null;
    ArrayList localArrayList = null;
    int i = 0;
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
        localArrayList = zza.zzc(paramParcel, k, Scope.CREATOR);
        break;
      case 3: 
        localAccount = (Account)zza.zza(paramParcel, k, Account.CREATOR);
        break;
      case 4: 
        bool3 = zza.zzc(paramParcel, k);
        break;
      case 5: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 6: 
        bool1 = zza.zzc(paramParcel, k);
        break;
      case 7: 
        str2 = zza.zzq(paramParcel, k);
        break;
      case 8: 
        str1 = zza.zzq(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza(37 + "Overread allowed size end=" + j, paramParcel);
    }
    return new GoogleSignInOptions(i, localArrayList, localAccount, bool3, bool2, bool1, str2, str1);
  }
  
  public GoogleSignInOptions[] zzdb(int paramInt)
  {
    return new GoogleSignInOptions[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.auth.api.signin.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */