package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.zza;

public class ConverterWrapper
  extends AbstractSafeParcelable
{
  public static final zza CREATOR = new zza();
  private final int mVersionCode;
  private final StringToIntConverter zn;
  
  ConverterWrapper(int paramInt, StringToIntConverter paramStringToIntConverter)
  {
    mVersionCode = paramInt;
    zn = paramStringToIntConverter;
  }
  
  private ConverterWrapper(StringToIntConverter paramStringToIntConverter)
  {
    mVersionCode = 1;
    zn = paramStringToIntConverter;
  }
  
  public static ConverterWrapper zza(FastJsonResponse.zza<?, ?> paramzza)
  {
    if ((paramzza instanceof StringToIntConverter)) {
      return new ConverterWrapper((StringToIntConverter)paramzza);
    }
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }
  
  int getVersionCode()
  {
    return mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza localzza = CREATOR;
    zza.zza(this, paramParcel, paramInt);
  }
  
  StringToIntConverter zzatm()
  {
    return zn;
  }
  
  public FastJsonResponse.zza<?, ?> zzatn()
  {
    if (zn != null) {
      return zn;
    }
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.converter.ConverterWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */