package org.joda.time;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.field.AbstractPartialFieldProperty;

@Deprecated
public class YearMonthDay$Property
  extends AbstractPartialFieldProperty
  implements Serializable
{
  private static final long serialVersionUID = 5727734012190224363L;
  private final int iFieldIndex;
  private final YearMonthDay iYearMonthDay;
  
  YearMonthDay$Property(YearMonthDay paramYearMonthDay, int paramInt)
  {
    iYearMonthDay = paramYearMonthDay;
    iFieldIndex = paramInt;
  }
  
  public YearMonthDay addToCopy(int paramInt)
  {
    int[] arrayOfInt = iYearMonthDay.getValues();
    arrayOfInt = getField().add(iYearMonthDay, iFieldIndex, arrayOfInt, paramInt);
    return new YearMonthDay(iYearMonthDay, arrayOfInt);
  }
  
  public YearMonthDay addWrapFieldToCopy(int paramInt)
  {
    int[] arrayOfInt = iYearMonthDay.getValues();
    arrayOfInt = getField().addWrapField(iYearMonthDay, iFieldIndex, arrayOfInt, paramInt);
    return new YearMonthDay(iYearMonthDay, arrayOfInt);
  }
  
  public int get()
  {
    return iYearMonthDay.getValue(iFieldIndex);
  }
  
  public DateTimeField getField()
  {
    return iYearMonthDay.getField(iFieldIndex);
  }
  
  protected ReadablePartial getReadablePartial()
  {
    return iYearMonthDay;
  }
  
  public YearMonthDay getYearMonthDay()
  {
    return iYearMonthDay;
  }
  
  public YearMonthDay setCopy(int paramInt)
  {
    int[] arrayOfInt = iYearMonthDay.getValues();
    arrayOfInt = getField().set(iYearMonthDay, iFieldIndex, arrayOfInt, paramInt);
    return new YearMonthDay(iYearMonthDay, arrayOfInt);
  }
  
  public YearMonthDay setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public YearMonthDay setCopy(String paramString, Locale paramLocale)
  {
    int[] arrayOfInt = iYearMonthDay.getValues();
    paramString = getField().set(iYearMonthDay, iFieldIndex, arrayOfInt, paramString, paramLocale);
    return new YearMonthDay(iYearMonthDay, paramString);
  }
  
  public YearMonthDay withMaximumValue()
  {
    return setCopy(getMaximumValue());
  }
  
  public YearMonthDay withMinimumValue()
  {
    return setCopy(getMinimumValue());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.YearMonthDay.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */