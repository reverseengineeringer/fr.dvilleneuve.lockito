package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

public class FixedEditTextPreference
  extends EditTextPreference
{
  public FixedEditTextPreference(Context paramContext)
  {
    super(paramContext);
  }
  
  public FixedEditTextPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public FixedEditTextPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public CharSequence getSummary()
  {
    String str = getText();
    CharSequence localCharSequence = super.getSummary();
    if ((localCharSequence == null) || (str == null)) {
      return localCharSequence;
    }
    return String.format(localCharSequence.toString(), new Object[] { str });
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.FixedEditTextPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */