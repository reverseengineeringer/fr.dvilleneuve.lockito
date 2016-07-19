package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericArrayStringConverterFactory$IntArrayStringConverter$1
{
  NumericArrayStringConverterFactory$IntArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public int[] convertFromString(Class<? extends int[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericArrayStringConverterFactory.IntArrayStringConverter.access$300();
      return paramClass;
    }
    String[] arrayOfString = NumericArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new int[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      paramString[i] = Integer.parseInt(arrayOfString[i]);
      i += 1;
    }
  }
  
  public String convertToString(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfInt.length * 6);
    localStringBuilder.append(paramArrayOfInt[0]);
    int i = 1;
    while (i < paramArrayOfInt.length)
    {
      localStringBuilder.append(',').append(paramArrayOfInt[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.IntArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */