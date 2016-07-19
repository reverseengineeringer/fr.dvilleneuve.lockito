package org.joda.convert.factory;

import java.util.regex.Pattern;

 enum NumericObjectArrayStringConverterFactory$ShortArrayStringConverter$1
{
  NumericObjectArrayStringConverterFactory$ShortArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Short[] convertFromString(Class<? extends Short[]> paramClass, String paramString)
  {
    if (paramString.length() == 0)
    {
      paramClass = NumericObjectArrayStringConverterFactory.ShortArrayStringConverter.access$500();
      return paramClass;
    }
    String[] arrayOfString = NumericObjectArrayStringConverterFactory.DELIMITER.split(paramString);
    paramString = new Short[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramClass = paramString;
      if (i >= arrayOfString.length) {
        break;
      }
      if (!arrayOfString[i].equals("-")) {
        paramString[i] = Short.valueOf(Short.parseShort(arrayOfString[i]));
      }
      i += 1;
    }
  }
  
  public String convertToString(Short[] paramArrayOfShort)
  {
    if (paramArrayOfShort.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder1 = new StringBuilder(paramArrayOfShort.length * 3);
    int i;
    label40:
    StringBuilder localStringBuilder2;
    if (paramArrayOfShort[0] != null)
    {
      localObject = paramArrayOfShort[0];
      localStringBuilder1.append(localObject);
      i = 1;
      if (i >= paramArrayOfShort.length) {
        break label91;
      }
      localStringBuilder2 = localStringBuilder1.append(',');
      if (paramArrayOfShort[i] == null) {
        break label85;
      }
    }
    label85:
    for (Object localObject = paramArrayOfShort[i];; localObject = "-")
    {
      localStringBuilder2.append(localObject);
      i += 1;
      break label40;
      localObject = "-";
      break;
    }
    label91:
    return localStringBuilder1.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericObjectArrayStringConverterFactory.ShortArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */