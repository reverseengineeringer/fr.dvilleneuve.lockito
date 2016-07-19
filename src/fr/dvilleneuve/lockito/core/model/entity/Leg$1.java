package fr.dvilleneuve.lockito.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class Leg$1
  implements Parcelable.Creator<Leg>
{
  public Leg createFromParcel(Parcel paramParcel)
  {
    return new Leg(paramParcel);
  }
  
  public Leg[] newArray(int paramInt)
  {
    return new Leg[paramInt];
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.Leg.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */