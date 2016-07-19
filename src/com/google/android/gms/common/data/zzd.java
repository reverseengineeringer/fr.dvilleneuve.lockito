package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable>
  extends AbstractDataBuffer<T>
{
  private static final String[] vM = { "data" };
  private final Parcelable.Creator<T> vN;
  
  public zzd(DataHolder paramDataHolder, Parcelable.Creator<T> paramCreator)
  {
    super(paramDataHolder);
    vN = paramCreator;
  }
  
  public static <T extends SafeParcelable> void zza(DataHolder.zza paramzza, T paramT)
  {
    Parcel localParcel = Parcel.obtain();
    paramT.writeToParcel(localParcel, 0);
    paramT = new ContentValues();
    paramT.put("data", localParcel.marshall());
    paramzza.zza(paramT);
    localParcel.recycle();
  }
  
  public static DataHolder.zza zzarc()
  {
    return DataHolder.zzb(vM);
  }
  
  public T zzfn(int paramInt)
  {
    Object localObject = tk.zzg("data", paramInt, tk.zzfo(paramInt));
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall((byte[])localObject, 0, localObject.length);
    localParcel.setDataPosition(0);
    localObject = (SafeParcelable)vN.createFromParcel(localParcel);
    localParcel.recycle();
    return (T)localObject;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.data.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */