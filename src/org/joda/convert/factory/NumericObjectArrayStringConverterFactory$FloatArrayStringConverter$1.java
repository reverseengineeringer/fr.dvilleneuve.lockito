package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericObjectArrayStringConverterFactory$FloatArrayStringConverter$1
{
  NumericObjectArrayStringConverterFactory$FloatArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Float[] convertFromString(Class<? extends Float[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericObjectArrayStringConverterFactory.FloatArrayStringConverter.access$900();
      return paramClass;
    }
    String[] arrayOfString = NumericObjectArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new Float[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      if (!arrayOfString[i].equals("-")) {
        paramString[i] = Float.valueOf(Float.parseFloat(arrayOfString[i]));
      }
      i += 1;
    }
  }
  
  public String convertToString(Float[] paramArrayOfFloat)
  {
    if (paramArrayOfFloat.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder1 = new StringBuilder(paramArrayOfFloat.length * 8);
    int i;
    label41:
    StringBuilder localStringBuilder2;
    if (paramArrayOfFloat[0] != null)
    {
      localObject = paramArrayOfFloat[0];
      localStringBuilder1.append(localObject);
      i = 1;
      if (i >= paramArrayOfFloat.length) {
        break label92;
      }
      localStringBuilder2 = localStringBuilder1.append(',');
      if (paramArrayOfFloat[i] == null) {
        break label86;
      }
    }
    label86:
    for (Object localObject = paramArrayOfFloat[i];; localObject = "-")
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
 * Qualified Name:     org.joda.convert.factory.NumericObjectArrayStringConverterFactory.FloatArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */