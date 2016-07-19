package org.joda.convert.factory;

import org.joda.convert.StringConverter;

 enum BooleanObjectArrayStringConverterFactory$BooleanArrayStringConverter
  implements StringConverter<Boolean[]>
{
  INSTANCE;
  
  private static final Boolean[] EMPTY = new Boolean[0];
  
  private BooleanObjectArrayStringConverterFactory$BooleanArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.BooleanObjectArrayStringConverterFactory.BooleanArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */