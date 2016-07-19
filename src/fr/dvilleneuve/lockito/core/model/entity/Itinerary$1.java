package fr.dvilleneuve.lockito.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class Itinerary$1
  implements Parcelable.Creator<Itinerary>
{
  public Itinerary createFromParcel(Parcel paramParcel)
  {
    return new Itinerary(paramParcel);
  }
  
  public Itinerary[] newArray(int paramInt)
  {
    return new Itinerary[paramInt];
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.Itinerary.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */