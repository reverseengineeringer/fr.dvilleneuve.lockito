package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class FieldMappingDictionary$FieldMapPair
  extends AbstractSafeParcelable
{
  public static final zzb CREATOR = new zzb();
  final int versionCode;
  final FastJsonResponse.Field<?, ?> zH;
  final String zzcb;
  
  FieldMappingDictionary$FieldMapPair(int paramInt, String paramString, FastJsonResponse.Field<?, ?> paramField)
  {
    versionCode = paramInt;
    zzcb = paramString;
    zH = paramField;
  }
  
  FieldMappingDictionary$FieldMapPair(String paramString, FastJsonResponse.Field<?, ?> paramField)
  {
    versionCode = 1;
    zzcb = paramString;
    zH = paramField;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb localzzb = CREATOR;
    zzb.zza(this, paramParcel, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */