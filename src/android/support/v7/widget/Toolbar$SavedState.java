package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

public class Toolbar$SavedState
  extends AbsSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
  {
    public Toolbar.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
    {
      return new Toolbar.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
    }
    
    public Toolbar.SavedState[] newArray(int paramAnonymousInt)
    {
      return new Toolbar.SavedState[paramAnonymousInt];
    }
  });
  int expandedMenuItemId;
  boolean isOverflowOpen;
  
  public Toolbar$SavedState(Parcel paramParcel)
  {
    this(paramParcel, null);
  }
  
  public Toolbar$SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    super(paramParcel, paramClassLoader);
    expandedMenuItemId = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      isOverflowOpen = bool;
      return;
    }
  }
  
  public Toolbar$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(expandedMenuItemId);
    if (isOverflowOpen) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.Toolbar.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */