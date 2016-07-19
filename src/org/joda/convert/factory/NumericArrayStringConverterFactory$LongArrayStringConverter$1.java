package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericArrayStringConverterFactory$LongArrayStringConverter$1
{
  NumericArrayStringConverterFactory$LongArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public long[] convertFromString(Class<? extends long[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericArrayStringConverterFactory.LongArrayStringConverter.access$100();
      return paramClass;
    }
    String[] arrayOfString = NumericArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new long[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      paramString[i] = Long.parseLong(arrayOfString[i]);
      i += 1;
    }
  }
  
  public String convertToString(long[] paramArrayOfLong)
  {
    if (paramArrayOfLong.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfLong.length * 8);
    localStringBuilder.append(paramArrayOfLong[0]);
    int i = 1;
    while (i < paramArrayOfLong.length)
    {
      localStringBuilder.append(',').append(paramArrayOfLong[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.LongArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */