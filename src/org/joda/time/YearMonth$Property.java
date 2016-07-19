package org.joda.time;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.field.AbstractPartialFieldProperty;

public class YearMonth$Property
  extends AbstractPartialFieldProperty
  implements Serializable
{
  private static final long serialVersionUID = 5727734012190224363L;
  private final YearMonth iBase;
  private final int iFieldIndex;
  
  YearMonth$Property(YearMonth paramYearMonth, int paramInt)
  {
    iBase = paramYearMonth;
    iFieldIndex = paramInt;
  }
  
  public YearMonth addToCopy(int paramInt)
  {
    int[] arrayOfInt = iBase.getValues();
    arrayOfInt = getField().add(iBase, iFieldIndex, arrayOfInt, paramInt);
    return new YearMonth(iBase, arrayOfInt);
  }
  
  public YearMonth addWrapFieldToCopy(int paramInt)
  {
    int[] arrayOfInt = iBase.getValues();
    arrayOfInt = getField().addWrapField(iBase, iFieldIndex, arrayOfInt, paramInt);
    return new YearMonth(iBase, arrayOfInt);
  }
  
  public int get()
  {
    return iBase.getValue(iFieldIndex);
  }
  
  public DateTimeField getField()
  {
    return iBase.getField(iFieldIndex);
  }
  
  protected ReadablePartial getReadablePartial()
  {
    return iBase;
  }
  
  public YearMonth getYearMonth()
  {
    return iBase;
  }
  
  public YearMonth setCopy(int paramInt)
  {
    int[] arrayOfInt = iBase.getValues();
    arrayOfInt = getField().set(iBase, iFieldIndex, arrayOfInt, paramInt);
    return new YearMonth(iBase, arrayOfInt);
  }
  
  public YearMonth setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public YearMonth setCopy(String paramString, Locale paramLocale)
  {
    int[] arrayOfInt = iBase.getValues();
    paramString = getField().set(iBase, iFieldIndex, arrayOfInt, paramString, paramLocale);
    return new YearMonth(iBase, paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.YearMonth.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */