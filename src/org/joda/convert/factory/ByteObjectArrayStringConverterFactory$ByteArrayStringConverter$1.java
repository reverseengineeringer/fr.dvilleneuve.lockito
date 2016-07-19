package org.joda.convert.factory;

 enum ByteObjectArrayStringConverterFactory$ByteArrayStringConverter$1
{
  ByteObjectArrayStringConverterFactory$ByteArrayStringConverter$1()
  {
    super(paramString, paramInt, null);
  }
  
  public Byte[] convertFromString(Class<? extends Byte[]> paramClass, String paramString)
  {
    if (paramString.length() == 0) {
      paramClass = ByteObjectArrayStringConverterFactory.ByteArrayStringConverter.access$100();
    }
    Byte[] arrayOfByte;
    int i;
    do
    {
      return paramClass;
      if (paramString.length() % 2 == 1) {
        throw new IllegalArgumentException("Invalid Byte[] string");
      }
      arrayOfByte = new Byte[paramString.length() / 2];
      i = 0;
      paramClass = arrayOfByte;
    } while (i >= arrayOfByte.length);
    paramClass = paramString.substring(i * 2, i * 2 + 2);
    if (paramClass.equals("--")) {
      arrayOfByte[i] = null;
    }
    for (;;)
    {
      i += 1;
      break;
      arrayOfByte[i] = Byte.valueOf((byte)Integer.parseInt(paramClass, 16));
    }
  }
  
  public String convertToString(Byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length);
    int i = 0;
    if (i < paramArrayOfByte.length)
    {
      if (paramArrayOfByte[i] == null) {
        localStringBuilder.append('-').append('-');
      }
      for (;;)
      {
        i += 1;
        break;
        int j = paramArrayOfByte[i].byteValue();
        localStringBuilder.append("0123456789ABCDEF".charAt((j & 0xF0) >>> 4)).append("0123456789ABCDEF".charAt(j & 0xF));
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.ByteObjectArrayStringConverterFactory.ByteArrayStringConverter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */