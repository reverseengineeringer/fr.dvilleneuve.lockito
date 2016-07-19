package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;

 enum NumericArrayStringConverterFactory$LongArrayStringConverter
  implements StringConverter<long[]>
{
  INSTANCE;
  
  private static final long[] EMPTY = new long[0];
  
  private NumericArrayStringConverterFactory$LongArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.LongArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */