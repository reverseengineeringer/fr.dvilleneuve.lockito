package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericObjectArrayStringConverterFactory$DoubleArrayStringConverter$1
{
  NumericObjectArrayStringConverterFactory$DoubleArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Double[] convertFromString(Class<? extends Double[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericObjectArrayStringConverterFactory.DoubleArrayStringConverter.access$700();
      return paramClass;
    }
    String[] arrayOfString = NumericObjectArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new Double[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      if (!arrayOfString[i].equals("-")) {
        paramString[i] = Double.valueOf(Double.parseDouble(arrayOfString[i]));
      }
      i += 1;
    }
  }
  
  public String convertToString(Double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder1 = new StringBuilder(paramArrayOfDouble.length * 8);
    int i;
    label41:
    StringBuilder localStringBuilder2;
    if (paramArrayOfDouble[0] != null)
    {
      localObject = paramArrayOfDouble[0];
      localStringBuilder1.append(localObject);
      i = 1;
      if (i >= paramArrayOfDouble.length) {
        break label92;
      }
      localStringBuilder2 = localStringBuilder1.append(',');
      if (paramArrayOfDouble[i] == null) {
        break label86;
      }
    }
    label86:
    for (Object localObject = paramArrayOfDouble[i];; localObject = "-")
    {
      localStringBuilder2.append(localObject);
      i += 1;
      break label41;
      localObject = "-";
      break;
    }
    label92:
    return localStringBuilder1.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericObjectArrayStringConverterFactory.DoubleArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */