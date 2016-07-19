package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

class SlidingPaneLayout$SavedState
  extends AbsSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
  {
    public SlidingPaneLayout.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
    {
      return new SlidingPaneLayout.SavedState(paramAnonymousParcel, paramAnonymousClassLoader, null);
    }
    
    public SlidingPaneLayout.SavedState[] newArray(int paramAnonymousInt)
    {
      return new SlidingPaneLayout.SavedState[paramAnonymousInt];
    }
  });
  boolean isOpen;
  
  private SlidingPaneLayout$SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    super(paramParcel, paramClassLoader);
    if (paramParcel.readInt() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      isOpen = bool;
      return;
    }
  }
  
  SlidingPaneLayout$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    if (isOpen) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SlidingPaneLayout.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */