package org.joda.convert.factory;

 enum BooleanObjectArrayStringConverterFactory$BooleanArrayStringConverter$1
{
  BooleanObjectArrayStringConverterFactory$BooleanArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Boolean[] convertFromString(Class<? extends Boolean[]> paramClass, String paramString)
  {
    if (paramString.length() == 0) {
      paramClass = BooleanObjectArrayStringConverterFactory.BooleanArrayStringConverter.access$100();
    }
    Boolean[] arrayOfBoolean;
    int i;
    do
    {
      return paramClass;
      arrayOfBoolean = new Boolean[paramString.length()];
      i = 0;
      paramClass = arrayOfBoolean;
    } while (i >= arrayOfBoolean.length);
    int j = paramString.charAt(i);
    if (j == 84) {
      arrayOfBoolean[i] = Boolean.TRUE;
    }
    for (;;)
    {
      i += 1;
      break;
      if (j == 70)
      {
        arrayOfBoolean[i] = Boolean.FALSE;
      }
      else
      {
        if (j != 45) {
          break label94;
        }
        arrayOfBoolean[i] = null;
      }
    }
    label94:
    throw new IllegalArgumentException("Invalid Boolean[] string, must consist only of 'T', 'F' and '-'");
  }
  
  public String convertToString(Boolean[] paramArrayOfBoolean)
  {
    if (paramArrayOfBoolean.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfBoolean.length);
    int i = 0;
    if (i < paramArrayOfBoolean.length)
    {
      char c;
      if (paramArrayOfBoolean[i] == null) {
        c = '-';
      }
      for (;;)
      {
        localStringBuilder.append(c);
        i += 1;
        break;
        if (paramArrayOfBoolean[i].booleanValue()) {
          c = 'T';
        } else {
          c = 'F';
        }
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.BooleanObjectArrayStringConverterFactory.BooleanArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */