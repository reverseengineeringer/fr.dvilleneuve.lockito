package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericObjectArrayStringConverterFactory$IntArrayStringConverter$1
{
  NumericObjectArrayStringConverterFactory$IntArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Integer[] convertFromString(Class<? extends Integer[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericObjectArrayStringConverterFactory.IntArrayStringConverter.access$300();
      return paramClass;
    }
    String[] arrayOfString = NumericObjectArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new Integer[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      if (!arrayOfString[i].equals("-")) {
        paramString[i] = Integer.valueOf(Integer.parseInt(arrayOfString[i]));
      }
      i += 1;
    }
  }
  
  public String convertToString(Integer[] paramArrayOfInteger)
  {
    if (paramArrayOfInteger.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder1 = new StringBuilder(paramArrayOfInteger.length * 6);
    int i;
    label41:
    StringBuilder localStringBuilder2;
    if (paramArrayOfInteger[0] != null)
    {
      localObject = paramArrayOfInteger[0];
      localStringBuilder1.append(localObject);
      i = 1;
      if (i >= paramArrayOfInteger.length) {
        break label92;
      }
      localStringBuilder2 = localStringBuilder1.append(',');
      if (paramArrayOfInteger[i] == null) {
        break label86;
      }
    }
    label86:
    for (Object localObject = paramArrayOfInteger[i];; localObject = "-")
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
 * Qualified Name:     org.joda.convert.factory.NumericObjectArrayStringConverterFactory.IntArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */