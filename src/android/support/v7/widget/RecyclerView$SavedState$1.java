package android.support.v7.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class RecyclerView$SavedState$1
  implements ParcelableCompatCreatorCallbacks<RecyclerView.SavedState>
{
  public RecyclerView.SavedState createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    return new RecyclerView.SavedState(paramParcel, paramClassLoader);
  }
  
  public RecyclerView.SavedState[] newArray(int paramInt)
  {
    return new RecyclerView.SavedState[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.RecyclerView.SavedState.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */