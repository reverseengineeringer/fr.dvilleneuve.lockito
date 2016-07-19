package android.support.v4.view;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class AbsSavedState$2
  implements ParcelableCompatCreatorCallbacks<AbsSavedState>
{
  public AbsSavedState createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    if (paramParcel.readParcelable(paramClassLoader) != null) {
      throw new IllegalStateException("superState must be null");
    }
    return AbsSavedState.EMPTY_STATE;
  }
  
  public AbsSavedState[] newArray(int paramInt)
  {
    return new AbsSavedState[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.AbsSavedState.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */