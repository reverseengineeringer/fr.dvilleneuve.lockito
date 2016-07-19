package org.joda.time;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.field.AbstractPartialFieldProperty;

public class MonthDay$Property
  extends AbstractPartialFieldProperty
  implements Serializable
{
  private static final long serialVersionUID = 5727734012190224363L;
  private final MonthDay iBase;
  private final int iFieldIndex;
  
  MonthDay$Property(MonthDay paramMonthDay, int paramInt)
  {
    iBase = paramMonthDay;
    iFieldIndex = paramInt;
  }
  
  public MonthDay addToCopy(int paramInt)
  {
    int[] arrayOfInt = iBase.getValues();
    arrayOfInt = getField().add(iBase, iFieldIndex, arrayOfInt, paramInt);
    return new MonthDay(iBase, arrayOfInt);
  }
  
  public MonthDay addWrapFieldToCopy(int paramInt)
  {
    int[] arrayOfInt = iBase.getValues();
    arrayOfInt = getField().addWrapField(iBase, iFieldIndex, arrayOfInt, paramInt);
    return new MonthDay(iBase, arrayOfInt);
  }
  
  public int get()
  {
    return iBase.getValue(iFieldIndex);
  }
  
  public DateTimeField getField()
  {
    return iBase.getField(iFieldIndex);
  }
  
  public MonthDay getMonthDay()
  {
    return iBase;
  }
  
  protected ReadablePartial getReadablePartial()
  {
    return iBase;
  }
  
  public MonthDay setCopy(int paramInt)
  {
    int[] arrayOfInt = iBase.getValues();
    arrayOfInt = getField().set(iBase, iFieldIndex, arrayOfInt, paramInt);
    return new MonthDay(iBase, arrayOfInt);
  }
  
  public MonthDay setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public MonthDay setCopy(String paramString, Locale paramLocale)
  {
    int[] arrayOfInt = iBase.getValues();
    paramString = getField().set(iBase, iFieldIndex, arrayOfInt, paramString, paramLocale);
    return new MonthDay(iBase, paramString);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.MonthDay.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */