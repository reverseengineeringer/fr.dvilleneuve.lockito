package fr.dvilleneuve.lockito.ui.component;

import android.text.InputFilter;
import android.text.Spanned;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FixedEditTextDecimal$DecimalDigitsInputFilter
  implements InputFilter
{
  Pattern mPattern;
  
  public FixedEditTextDecimal$DecimalDigitsInputFilter(FixedEditTextDecimal paramFixedEditTextDecimal, int paramInt1, int paramInt2)
  {
    paramFixedEditTextDecimal = new DecimalFormatSymbols(Locale.getDefault());
    paramFixedEditTextDecimal = "\\" + paramFixedEditTextDecimal.getDecimalSeparator();
    mPattern = Pattern.compile("[0-9]{0," + (paramInt1 - 1) + "}+((" + paramFixedEditTextDecimal + "[0-9]{0," + (paramInt2 - 1) + "})?)||(" + paramFixedEditTextDecimal + ")?");
  }
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    if (!mPattern.matcher(paramSpanned).matches()) {
      return "";
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.FixedEditTextDecimal.DecimalDigitsInputFilter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */