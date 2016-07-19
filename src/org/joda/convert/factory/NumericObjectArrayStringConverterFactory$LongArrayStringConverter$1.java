package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericObjectArrayStringConverterFactory$LongArrayStringConverter$1
{
  NumericObjectArrayStringConverterFactory$LongArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Long[] convertFromString(Class<? extends Long[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericObjectArrayStringConverterFactory.LongArrayStringConverter.access$100();
      return paramClass;
    }
    String[] arrayOfString = NumericObjectArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new Long[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      if (!arrayOfString[i].equals("-")) {
        paramString[i] = Long.valueOf(Long.parseLong(arrayOfString[i]));
      }
      i += 1;
    }
  }
  
  public String convertToString(Long[] paramArrayOfLong)
  {
    if (paramArrayOfLong.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder1 = new StringBuilder(paramArrayOfLong.length * 8);
    int i;
    label41:
    StringBuilder localStringBuilder2;
    if (paramArrayOfLong[0] != null)
    {
      localObject = paramArrayOfLong[0];
      localStringBuilder1.append(localObject);
      i = 1;
      if (i >= paramArrayOfLong.length) {
        break label92;
      }
      localStringBuilder2 = localStringBuilder1.append(',');
      if (paramArrayOfLong[i] == null) {
        break label86;
      }
    }
    label86:
    for (Object localObject = paramArrayOfLong[i];; localObject = "-")
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
 * Qualified Name:     org.joda.convert.factory.NumericObjectArrayStringConverterFactory.LongArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */