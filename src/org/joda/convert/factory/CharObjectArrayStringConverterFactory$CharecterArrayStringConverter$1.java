package org.joda.convert.factory;

import java.util.Arrays;

 enum CharObjectArrayStringConverterFactory$CharecterArrayStringConverter$1
{
  CharObjectArrayStringConverterFactory$CharecterArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Character[] convertFromString(Class<? extends Character[]> paramClass, String paramString)
  {
    if (paramString.length() == 0) {
      return CharObjectArrayStringConverterFactory.CharecterArrayStringConverter.access$100();
    }
    paramClass = new Character[paramString.length()];
    int i = 0;
    int k = paramString.indexOf('\\');
    if (k >= 0)
    {
      j = 0;
      while (j < k)
      {
        paramClass[i] = Character.valueOf(paramString.charAt(j));
        j += 1;
        i += 1;
      }
      if (paramString.charAt(k + 1) == '\\')
      {
        j = i + 1;
        paramClass[i] = Character.valueOf('\\');
      }
      for (i = j;; i = j)
      {
        paramString = paramString.substring(k + 2);
        break;
        if (paramString.charAt(k + 1) != '-') {
          break label138;
        }
        j = i + 1;
        paramClass[i] = null;
      }
      label138:
      throw new IllegalArgumentException("Invalid Character[] string, incorrect escape");
    }
    int j = 0;
    while (j < paramString.length())
    {
      paramClass[i] = Character.valueOf(paramString.charAt(j));
      j += 1;
      i += 1;
    }
    return (Character[])Arrays.copyOf(paramClass, i);
  }
  
  public String convertToString(Character[] paramArrayOfCharacter)
  {
    if (paramArrayOfCharacter.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfCharacter.length * 8);
    int i = 0;
    if (i < paramArrayOfCharacter.length)
    {
      if (paramArrayOfCharacter[i] == null) {
        localStringBuilder.append("\\-");
      }
      for (;;)
      {
        i += 1;
        break;
        char c = paramArrayOfCharacter[i].charValue();
        if (c == '\\') {
          localStringBuilder.append("\\\\");
        } else {
          localStringBuilder.append(c);
        }
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.CharObjectArrayStringConverterFactory.CharecterArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */