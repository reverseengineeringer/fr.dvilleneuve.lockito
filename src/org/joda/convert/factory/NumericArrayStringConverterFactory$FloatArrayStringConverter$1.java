package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericArrayStringConverterFactory$FloatArrayStringConverter$1
{
  NumericArrayStringConverterFactory$FloatArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public float[] convertFromString(Class<? extends float[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericArrayStringConverterFactory.FloatArrayStringConverter.access$900();
      return paramClass;
    }
    String[] arrayOfString = NumericArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new float[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      paramString[i] = Float.parseFloat(arrayOfString[i]);
      i += 1;
    }
  }
  
  public String convertToString(float[] paramArrayOfFloat)
  {
    if (paramArrayOfFloat.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfFloat.length * 8);
    localStringBuilder.append(paramArrayOfFloat[0]);
    int i = 1;
    while (i < paramArrayOfFloat.length)
    {
      localStringBuilder.append(',').append(paramArrayOfFloat[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.FloatArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */