package org.joda.convert.factory;

import org.joda.convert.StringConverter;

 enum BooleanArrayStringConverterFactory$BooleanArrayStringConverter
  implements StringConverter<boolean[]>
{
  INSTANCE;
  
  private static final boolean[] EMPTY = new boolean[0];
  
  private BooleanArrayStringConverterFactory$BooleanArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.BooleanArrayStringConverterFactory.BooleanArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */