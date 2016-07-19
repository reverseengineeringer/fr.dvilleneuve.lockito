package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericArrayStringConverterFactory$ShortArrayStringConverter$1
{
  NumericArrayStringConverterFactory$ShortArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public short[] convertFromString(Class<? extends short[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericArrayStringConverterFactory.ShortArrayStringConverter.access$500();
      return paramClass;
    }
    String[] arrayOfString = NumericArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new short[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      paramString[i] = Short.parseShort(arrayOfString[i]);
      i += 1;
    }
  }
  
  public String convertToString(short[] paramArrayOfShort)
  {
    if (paramArrayOfShort.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfShort.length * 3);
    localStringBuilder.append(paramArrayOfShort[0]);
    int i = 1;
    while (i < paramArrayOfShort.length)
    {
      localStringBuilder.append(',').append(paramArrayOfShort[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.ShortArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */