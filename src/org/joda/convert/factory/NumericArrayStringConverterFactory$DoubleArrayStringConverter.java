package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;

 enum NumericArrayStringConverterFactory$DoubleArrayStringConverter
  implements StringConverter<double[]>
{
  INSTANCE;
  
  private static final double[] EMPTY = new double[0];
  
  private NumericArrayStringConverterFactory$DoubleArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.DoubleArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */