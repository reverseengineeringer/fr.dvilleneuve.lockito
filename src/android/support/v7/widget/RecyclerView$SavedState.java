package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

public class RecyclerView$SavedState
  extends AbsSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
  {
    public RecyclerView.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
    {
      return new RecyclerView.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
    }
    
    public RecyclerView.SavedState[] newArray(int paramAnonymousInt)
    {
      return new RecyclerView.SavedState[paramAnonymousInt];
    }
  });
  Parcelable mLayoutState;
  
  RecyclerView$SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    super(paramParcel, paramClassLoader);
    if (paramClassLoader != null) {}
    for (;;)
    {
      mLayoutState = paramParcel.readParcelable(paramClassLoader);
      return;
      paramClassLoader = RecyclerView.LayoutManager.class.getClassLoader();
    }
  }
  
  RecyclerView$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  private void copyFrom(SavedState paramSavedState)
  {
    mLayoutState = mLayoutState;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(mLayoutState, 0);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.RecyclerView.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */