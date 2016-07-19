package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Command
  implements Parcelable
{
  @Deprecated
  public static final Parcelable.Creator<Command> CREATOR = new Parcelable.Creator()
  {
    @Deprecated
    public Command[] zzbw(int paramAnonymousInt)
    {
      return new Command[paramAnonymousInt];
    }
    
    @Deprecated
    public Command zzt(Parcel paramAnonymousParcel)
    {
      return new Command(paramAnonymousParcel);
    }
  };
  private String mValue;
  private String zzbgk;
  private String zzcza;
  
  @Deprecated
  public Command() {}
  
  @Deprecated
  Command(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }
  
  @Deprecated
  private void readFromParcel(Parcel paramParcel)
  {
    zzbgk = paramParcel.readString();
    zzcza = paramParcel.readString();
    mValue = paramParcel.readString();
  }
  
  @Deprecated
  public int describeContents()
  {
    return 0;
  }
  
  public String getId()
  {
    return zzbgk;
  }
  
  public String getValue()
  {
    return mValue;
  }
  
  @Deprecated
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(zzbgk);
    paramParcel.writeString(zzcza);
    paramParcel.writeString(mValue);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.Command
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */