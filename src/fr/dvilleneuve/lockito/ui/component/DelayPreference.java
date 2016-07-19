package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.preference.DialogPreference;
import android.preference.Preference.BaseSavedState;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class DelayPreference
  extends DialogPreference
{
  private static final int DEFAULT_VALUE = 1;
  private int currentValue = 1;
  
  public DelayPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public DelayPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setDialogLayoutResource(2130903076);
  }
  
  public CharSequence getSummary()
  {
    CharSequence localCharSequence = super.getSummary();
    if (localCharSequence == null) {
      return null;
    }
    return String.format(localCharSequence.toString(), new Object[] { Integer.valueOf(currentValue) });
  }
  
  protected void onBindDialogView(View paramView)
  {
    paramView = (NumberPicker)paramView.findViewById(2131624060);
    paramView.setMinValue(1);
    paramView.setMaxValue(3600);
    paramView.setValue(currentValue);
    paramView.setFocusable(true);
    paramView.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
    {
      public void onValueChange(NumberPicker paramAnonymousNumberPicker, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        DelayPreference.access$002(DelayPreference.this, paramAnonymousInt2);
      }
    });
  }
  
  protected void onDialogClosed(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      persistInt(currentValue);
      notifyChanged();
    }
  }
  
  protected Object onGetDefaultValue(TypedArray paramTypedArray, int paramInt)
  {
    return Integer.valueOf(paramTypedArray.getInteger(paramInt, 1));
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable == null) || (!paramParcelable.getClass().equals(SavedState.class)))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    currentValue = value;
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    value = currentValue;
    return localSavedState;
  }
  
  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    super.onSetInitialValue(paramBoolean, paramObject);
    if (paramBoolean)
    {
      currentValue = getPersistedInt(currentValue);
      return;
    }
    currentValue = ((Integer)paramObject).intValue();
    persistInt(currentValue);
  }
  
  private static class SavedState
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
    
    public SavedState(Parcel paramParcel)
    {
      super();
      value = paramParcel.readInt();
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(value);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.DelayPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */