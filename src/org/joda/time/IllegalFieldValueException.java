package org.joda.time;

public class IllegalFieldValueException
  extends IllegalArgumentException
{
  private static final long serialVersionUID = 6305711765985447737L;
  private final DateTimeFieldType iDateTimeFieldType;
  private final DurationFieldType iDurationFieldType;
  private final String iFieldName;
  private final Number iLowerBound;
  private String iMessage;
  private final Number iNumberValue;
  private final String iStringValue;
  private final Number iUpperBound;
  
  public IllegalFieldValueException(String paramString, Number paramNumber1, Number paramNumber2, Number paramNumber3)
  {
    super(createMessage(paramString, paramNumber1, paramNumber2, paramNumber3, null));
    iDateTimeFieldType = null;
    iDurationFieldType = null;
    iFieldName = paramString;
    iNumberValue = paramNumber1;
    iStringValue = null;
    iLowerBound = paramNumber2;
    iUpperBound = paramNumber3;
    iMessage = super.getMessage();
  }
  
  public IllegalFieldValueException(String paramString1, String paramString2)
  {
    super(createMessage(paramString1, paramString2));
    iDateTimeFieldType = null;
    iDurationFieldType = null;
    iFieldName = paramString1;
    iStringValue = paramString2;
    iNumberValue = null;
    iLowerBound = null;
    iUpperBound = null;
    iMessage = super.getMessage();
  }
  
  public IllegalFieldValueException(DateTimeFieldType paramDateTimeFieldType, Number paramNumber1, Number paramNumber2, Number paramNumber3)
  {
    super(createMessage(paramDateTimeFieldType.getName(), paramNumber1, paramNumber2, paramNumber3, null));
    iDateTimeFieldType = paramDateTimeFieldType;
    iDurationFieldType = null;
    iFieldName = paramDateTimeFieldType.getName();
    iNumberValue = paramNumber1;
    iStringValue = null;
    iLowerBound = paramNumber2;
    iUpperBound = paramNumber3;
    iMessage = super.getMessage();
  }
  
  public IllegalFieldValueException(DateTimeFieldType paramDateTimeFieldType, Number paramNumber, String paramString)
  {
    super(createMessage(paramDateTimeFieldType.getName(), paramNumber, null, null, paramString));
    iDateTimeFieldType = paramDateTimeFieldType;
    iDurationFieldType = null;
    iFieldName = paramDateTimeFieldType.getName();
    iNumberValue = paramNumber;
    iStringValue = null;
    iLowerBound = null;
    iUpperBound = null;
    iMessage = super.getMessage();
  }
  
  public IllegalFieldValueException(DateTimeFieldType paramDateTimeFieldType, String paramString)
  {
    super(createMessage(paramDateTimeFieldType.getName(), paramString));
    iDateTimeFieldType = paramDateTimeFieldType;
    iDurationFieldType = null;
    iFieldName = paramDateTimeFieldType.getName();
    iStringValue = paramString;
    iNumberValue = null;
    iLowerBound = null;
    iUpperBound = null;
    iMessage = super.getMessage();
  }
  
  public IllegalFieldValueException(DurationFieldType paramDurationFieldType, Number paramNumber1, Number paramNumber2, Number paramNumber3)
  {
    super(createMessage(paramDurationFieldType.getName(), paramNumber1, paramNumber2, paramNumber3, null));
    iDateTimeFieldType = null;
    iDurationFieldType = paramDurationFieldType;
    iFieldName = paramDurationFieldType.getName();
    iNumberValue = paramNumber1;
    iStringValue = null;
    iLowerBound = paramNumber2;
    iUpperBound = paramNumber3;
    iMessage = super.getMessage();
  }
  
  public IllegalFieldValueException(DurationFieldType paramDurationFieldType, String paramString)
  {
    super(createMessage(paramDurationFieldType.getName(), paramString));
    iDateTimeFieldType = null;
    iDurationFieldType = paramDurationFieldType;
    iFieldName = paramDurationFieldType.getName();
    iStringValue = paramString;
    iNumberValue = null;
    iLowerBound = null;
    iUpperBound = null;
    iMessage = super.getMessage();
  }
  
  private static String createMessage(String paramString1, Number paramNumber1, Number paramNumber2, Number paramNumber3, String paramString2)
  {
    paramString1 = new StringBuilder().append("Value ").append(paramNumber1).append(" for ").append(paramString1).append(' ');
    if (paramNumber2 == null) {
      if (paramNumber3 == null) {
        paramString1.append("is not supported");
      }
    }
    for (;;)
    {
      if (paramString2 != null) {
        paramString1.append(": ").append(paramString2);
      }
      return paramString1.toString();
      paramString1.append("must not be larger than ").append(paramNumber3);
      continue;
      if (paramNumber3 == null) {
        paramString1.append("must not be smaller than ").append(paramNumber2);
      } else {
        paramString1.append("must be in the range [").append(paramNumber2).append(',').append(paramNumber3).append(']');
      }
    }
  }
  
  private static String createMessage(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer().append("Value ");
    if (paramString2 == null) {
      localStringBuffer.append("null");
    }
    for (;;)
    {
      localStringBuffer.append(" for ").append(paramString1).append(' ').append("is not supported");
      return localStringBuffer.toString();
      localStringBuffer.append('"');
      localStringBuffer.append(paramString2);
      localStringBuffer.append('"');
    }
  }
  
  public DateTimeFieldType getDateTimeFieldType()
  {
    return iDateTimeFieldType;
  }
  
  public DurationFieldType getDurationFieldType()
  {
    return iDurationFieldType;
  }
  
  public String getFieldName()
  {
    return iFieldName;
  }
  
  public Number getIllegalNumberValue()
  {
    return iNumberValue;
  }
  
  public String getIllegalStringValue()
  {
    return iStringValue;
  }
  
  public String getIllegalValueAsString()
  {
    String str2 = iStringValue;
    String str1 = str2;
    if (str2 == null) {
      str1 = String.valueOf(iNumberValue);
    }
    return str1;
  }
  
  public Number getLowerBound()
  {
    return iLowerBound;
  }
  
  public String getMessage()
  {
    return iMessage;
  }
  
  public Number getUpperBound()
  {
    return iUpperBound;
  }
  
  public void prependMessage(String paramString)
  {
    if (iMessage == null) {
      iMessage = paramString;
    }
    while (paramString == null) {
      return;
    }
    iMessage = (paramString + ": " + iMessage);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.IllegalFieldValueException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */