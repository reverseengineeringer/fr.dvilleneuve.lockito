package com.google.android.gms.common.server;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class FavaDiagnosticsEntity
  extends AbstractSafeParcelable
{
  public static final zza CREATOR = new zza();
  final int mVersionCode;
  public final String zl;
  public final int zm;
  
  public FavaDiagnosticsEntity(int paramInt1, String paramString, int paramInt2)
  {
    mVersionCode = paramInt1;
    zl = paramString;
    zm = paramInt2;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.FavaDiagnosticsEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */