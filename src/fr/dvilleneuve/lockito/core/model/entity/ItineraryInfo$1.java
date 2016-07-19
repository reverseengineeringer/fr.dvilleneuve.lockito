package fr.dvilleneuve.lockito.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ItineraryInfo$1
  implements Parcelable.Creator<ItineraryInfo>
{
  public ItineraryInfo createFromParcel(Parcel paramParcel)
  {
    return new ItineraryInfo(paramParcel);
  }
  
  public ItineraryInfo[] newArray(int paramInt)
  {
    return new ItineraryInfo[paramInt];
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */