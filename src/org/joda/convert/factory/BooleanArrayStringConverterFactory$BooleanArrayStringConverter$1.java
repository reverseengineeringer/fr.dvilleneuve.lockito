package org.joda.convert.factory;

 enum BooleanArrayStringConverterFactory$BooleanArrayStringConverter$1
{
  BooleanArrayStringConverterFactory$BooleanArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public boolean[] convertFromString(Class<? extends boolean[]> paramClass, String paramString)
  {
    if (paramString.length() == 0) {
      paramClass = BooleanArrayStringConverterFactory.BooleanArrayStringConverter.access$100();
    }
    boolean[] arrayOfBoolean;
    int i;
    do
    {
      return paramClass;
      arrayOfBoolean = new boolean[paramString.length()];
      i = 0;
      paramClass = arrayOfBoolean;
    } while (i >= arrayOfBoolean.length);
    int j = paramString.charAt(i);
    if (j == 84) {
      arrayOfBoolean[i] = true;
    }
    for (;;)
    {
      i += 1;
      break;
      if (j != 70) {
        break label74;
      }
      arrayOfBoolean[i] = false;
    }
    label74:
    throw new IllegalArgumentException("Invalid boolean[] string, must consist only of 'T' and 'F'");
  }
  
  public String convertToString(boolean[] paramArrayOfBoolean)
  {
    if (paramArrayOfBoolean.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfBoolean.length);
    int i = 0;
    if (i < paramArrayOfBoolean.length)
    {
      if (paramArrayOfBoolean[i] != 0) {}
      for (char c = 'T';; c = 'F')
      {
        localStringBuilder.append(c);
        i += 1;
        break;
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.BooleanArrayStringConverterFactory.BooleanArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */