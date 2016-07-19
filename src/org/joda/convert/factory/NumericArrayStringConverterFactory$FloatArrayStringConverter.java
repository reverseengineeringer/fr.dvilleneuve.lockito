package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;

 enum NumericArrayStringConverterFactory$FloatArrayStringConverter
  implements StringConverter<float[]>
{
  INSTANCE;
  
  private static final float[] EMPTY = new float[0];
  
  private NumericArrayStringConverterFactory$FloatArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.FloatArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */