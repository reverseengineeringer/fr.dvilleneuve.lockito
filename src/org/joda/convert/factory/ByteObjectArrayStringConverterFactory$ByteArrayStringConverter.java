package org.joda.convert.factory;

import org.joda.convert.StringConverter;

 enum ByteObjectArrayStringConverterFactory$ByteArrayStringConverter
  implements StringConverter<Byte[]>
{
  INSTANCE;
  
  private static final Byte[] EMPTY = new Byte[0];
  private static final String HEX = "0123456789ABCDEF";
  
  private ByteObjectArrayStringConverterFactory$ByteArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.ByteObjectArrayStringConverterFactory.ByteArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */