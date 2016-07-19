package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericArrayStringConverterFactory$DoubleArrayStringConverter$1
{
  NumericArrayStringConverterFactory$DoubleArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public double[] convertFromString(Class<? extends double[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericArrayStringConverterFactory.DoubleArrayStringConverter.access$700();
      return paramClass;
    }
    String[] arrayOfString = NumericArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new double[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      paramString[i] = Double.parseDouble(arrayOfString[i]);
      i += 1;
    }
  }
  
  public String convertToString(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfDouble.length * 8);
    localStringBuilder.append(paramArrayOfDouble[0]);
    int i = 1;
    while (i < paramArrayOfDouble.length)
    {
      localStringBuilder.append(',').append(paramArrayOfDouble[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.DoubleArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */