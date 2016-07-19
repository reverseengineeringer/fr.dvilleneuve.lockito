package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

class SearchView$SavedState
  extends AbsSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
  {
    public SearchView.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
    {
      return new SearchView.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
    }
    
    public SearchView.SavedState[] newArray(int paramAnonymousInt)
    {
      return new SearchView.SavedState[paramAnonymousInt];
    }
  });
  boolean isIconified;
  
  public SearchView$SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    super(paramParcel, paramClassLoader);
    isIconified = ((Boolean)paramParcel.readValue(null)).booleanValue();
  }
  
  SearchView$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public String toString()
  {
    return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + isIconified + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeValue(Boolean.valueOf(isIconified));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.SearchView.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */