package fr.dvilleneuve.lockito.core.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class Point$1
  implements Parcelable.Creator<Point>
{
  public Point createFromParcel(Parcel paramParcel)
  {
    return new Point(paramParcel);
  }
  
  public Point[] newArray(int paramInt)
  {
    return new Point[paramInt];
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.Point.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */