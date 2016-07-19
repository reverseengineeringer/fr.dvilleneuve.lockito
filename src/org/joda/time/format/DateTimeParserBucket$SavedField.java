package org.joda.time.format;

import java.util.Locale;
import org.joda.time.DateTimeField;

class DateTimeParserBucket$SavedField
  implements Comparable<SavedField>
{
  final DateTimeField iField;
  final Locale iLocale;
  final String iText;
  final int iValue;
  
  DateTimeParserBucket$SavedField(DateTimeField paramDateTimeField, int paramInt)
  {
    iField = paramDateTimeField;
    iValue = paramInt;
    iText = null;
    iLocale = null;
  }
  
  DateTimeParserBucket$SavedField(DateTimeField paramDateTimeField, String paramString, Locale paramLocale)
  {
    iField = paramDateTimeField;
    iValue = 0;
    iText = paramString;
    iLocale = paramLocale;
  }
  
  public int compareTo(SavedField paramSavedField)
  {
    paramSavedField = iField;
    int i = DateTimeParserBucket.compareReverse(iField.getRangeDurationField(), paramSavedField.getRangeDurationField());
    if (i != 0) {
      return i;
    }
    return DateTimeParserBucket.compareReverse(iField.getDurationField(), paramSavedField.getDurationField());
  }
  
  long set(long paramLong, boolean paramBoolean)
  {
    if (iText == null) {}
    for (paramLong = iField.set(paramLong, iValue);; paramLong = iField.set(paramLong, iText, iLocale))
    {
      long l = paramLong;
      if (paramBoolean) {
        l = iField.roundFloor(paramLong);
      }
      return l;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeParserBucket.SavedField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */