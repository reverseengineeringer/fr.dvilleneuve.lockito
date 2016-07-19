package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper
  implements Parcelable
{
  public static final Parcelable.Creator<BinderWrapper> CREATOR = new Parcelable.Creator()
  {
    public BinderWrapper zzce(Parcel paramAnonymousParcel)
    {
      return new BinderWrapper(paramAnonymousParcel, null);
    }
    
    public BinderWrapper[] zzfz(int paramAnonymousInt)
    {
      return new BinderWrapper[paramAnonymousInt];
    }
  };
  private IBinder xA = null;
  
  public BinderWrapper() {}
  
  public BinderWrapper(IBinder paramIBinder)
  {
    xA = paramIBinder;
  }
  
  private BinderWrapper(Parcel paramParcel)
  {
    xA = paramParcel.readStrongBinder();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeStrongBinder(xA);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.BinderWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */