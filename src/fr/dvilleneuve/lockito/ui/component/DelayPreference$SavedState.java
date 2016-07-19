package fr.dvilleneuve.lockito.ui.component;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.preference.Preference.BaseSavedState;

class DelayPreference$SavedState
  extends Preference.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
  {
    public DelayPreference.SavedState createFromParcel(Parcel paramAnonymousParcel)
    {
      return new DelayPreference.SavedState(paramAnonymousParcel);
    }
    
    public DelayPreference.SavedState[] newArray(int paramAnonymousInt)
    {
      return new DelayPreference.SavedState[paramAnonymousInt];
    }
  };
  int value;
  
  public DelayPreference$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    value = paramParcel.readInt();
  }
  
  public DelayPreference$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(value);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.DelayPreference.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */