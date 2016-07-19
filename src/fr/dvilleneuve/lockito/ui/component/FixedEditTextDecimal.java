package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import fr.dvilleneuve.lockito.core.logger.Logger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FixedEditTextDecimal
  extends EnhancedEditText
{
  private static final DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance(Locale.getDefault());
  private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("0.#");
  
  public FixedEditTextDecimal(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public FixedEditTextDecimal(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public FixedEditTextDecimal(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init()
  {
    setInputType(8192);
    setKeyListener(DigitsKeyListener.getInstance("0123456789" + DECIMAL_FORMAT_SYMBOLS.getDecimalSeparator()));
    setFilters(new InputFilter[] { new DecimalDigitsInputFilter(4, 1) });
  }
  
  public float getValue(float paramFloat)
  {
    if (getText().length() == 0) {
      return paramFloat;
    }
    return parseValue(getText().toString(), paramFloat);
  }
  
  protected float parseValue(String paramString, float paramFloat)
  {
    try
    {
      float f = NUMBER_FORMAT.parse(paramString).floatValue();
      return f;
    }
    catch (ParseException localParseException)
    {
      Logger.warn("Can't parse integer value from '%s'", new Object[] { paramString });
    }
    return paramFloat;
  }
  
  class DecimalDigitsInputFilter
    implements InputFilter
  {
    Pattern mPattern;
    
    public DecimalDigitsInputFilter(int paramInt1, int paramInt2)
    {
      this$1 = new DecimalFormatSymbols(Locale.getDefault());
      this$1 = "\\" + getDecimalSeparator();
      mPattern = Pattern.compile("[0-9]{0," + (paramInt1 - 1) + "}+((" + FixedEditTextDecimal.this + "[0-9]{0," + (paramInt2 - 1) + "})?)||(" + FixedEditTextDecimal.this + ")?");
    }
    
    public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
    {
      if (!mPattern.matcher(paramSpanned).matches()) {
        return "";
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.FixedEditTextDecimal
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */