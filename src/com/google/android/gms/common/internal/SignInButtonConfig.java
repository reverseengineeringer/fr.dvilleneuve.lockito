package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class SignInButtonConfig
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<SignInButtonConfig> CREATOR = new zzae();
  final int mVersionCode;
  private final Scope[] ro;
  private final int yY;
  private final int yZ;
  
  SignInButtonConfig(int paramInt1, int paramInt2, int paramInt3, Scope[] paramArrayOfScope)
  {
    mVersionCode = paramInt1;
    yY = paramInt2;
    yZ = paramInt3;
    ro = paramArrayOfScope;
  }
  
  public SignInButtonConfig(int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
  {
    this(1, paramInt1, paramInt2, paramArrayOfScope);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzae.zza(this, paramParcel, paramInt);
  }
  
  public int zzatg()
  {
    return yY;
  }
  
  public int zzath()
  {
    return yZ;
  }
  
  public Scope[] zzati()
  {
    return ro;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.SignInButtonConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */