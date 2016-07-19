package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

public class DrawerLayout$SavedState
  extends AbsSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
  {
    public DrawerLayout.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
    {
      return new DrawerLayout.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
    }
    
    public DrawerLayout.SavedState[] newArray(int paramAnonymousInt)
    {
      return new DrawerLayout.SavedState[paramAnonymousInt];
    }
  });
  int lockModeEnd;
  int lockModeLeft;
  int lockModeRight;
  int lockModeStart;
  int openDrawerGravity = 0;
  
  public DrawerLayout$SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    super(paramParcel, paramClassLoader);
    openDrawerGravity = paramParcel.readInt();
    lockModeLeft = paramParcel.readInt();
    lockModeRight = paramParcel.readInt();
    lockModeStart = paramParcel.readInt();
    lockModeEnd = paramParcel.readInt();
  }
  
  public DrawerLayout$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(openDrawerGravity);
    paramParcel.writeInt(lockModeLeft);
    paramParcel.writeInt(lockModeRight);
    paramParcel.writeInt(lockModeStart);
    paramParcel.writeInt(lockModeEnd);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.DrawerLayout.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */