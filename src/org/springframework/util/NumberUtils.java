package org.springframework.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public abstract class NumberUtils
{
  public static <T extends Number> T convertNumberToTargetClass(Number paramNumber, Class<T> paramClass)
    throws IllegalArgumentException
  {
    Assert.notNull(paramNumber, "Number must not be null");
    Assert.notNull(paramClass, "Target class must not be null");
    if (paramClass.isInstance(paramNumber)) {
      return paramNumber;
    }
    long l;
    if (paramClass.equals(Byte.class))
    {
      l = paramNumber.longValue();
      if ((l < -128L) || (l > 127L)) {
        raiseOverflowException(paramNumber, paramClass);
      }
      return new Byte(paramNumber.byteValue());
    }
    if (paramClass.equals(Short.class))
    {
      l = paramNumber.longValue();
      if ((l < -32768L) || (l > 32767L)) {
        raiseOverflowException(paramNumber, paramClass);
      }
      return new Short(paramNumber.shortValue());
    }
    if (paramClass.equals(Integer.class))
    {
      l = paramNumber.longValue();
      if ((l < -2147483648L) || (l > 2147483647L)) {
        raiseOverflowException(paramNumber, paramClass);
      }
      return new Integer(paramNumber.intValue());
    }
    if (paramClass.equals(Long.class)) {
      return new Long(paramNumber.longValue());
    }
    if (paramClass.equals(BigInteger.class))
    {
      if ((paramNumber instanceof BigDecimal)) {
        return ((BigDecimal)paramNumber).toBigInteger();
      }
      return BigInteger.valueOf(paramNumber.longValue());
    }
    if (paramClass.equals(Float.class)) {
      return new Float(paramNumber.floatValue());
    }
    if (paramClass.equals(Double.class)) {
      return new Double(paramNumber.doubleValue());
    }
    if (paramClass.equals(BigDecimal.class)) {
      return new BigDecimal(paramNumber.toString());
    }
    throw new IllegalArgumentException("Could not convert number [" + paramNumber + "] of type [" + paramNumber.getClass().getName() + "] to unknown target class [" + paramClass.getName() + "]");
  }
  
  private static BigInteger decodeBigInteger(String paramString)
  {
    int n = 10;
    int k = 0;
    int m = 0;
    if (paramString.startsWith("-"))
    {
      m = 1;
      k = 0 + 1;
    }
    int i;
    int j;
    if ((paramString.startsWith("0x", k)) || (paramString.startsWith("0X", k)))
    {
      i = k + 2;
      j = 16;
    }
    for (;;)
    {
      BigInteger localBigInteger = new BigInteger(paramString.substring(i), j);
      paramString = localBigInteger;
      if (m != 0) {
        paramString = localBigInteger.negate();
      }
      return paramString;
      if (paramString.startsWith("#", k))
      {
        i = k + 1;
        j = 16;
      }
      else
      {
        i = k;
        j = n;
        if (paramString.startsWith("0", k))
        {
          i = k;
          j = n;
          if (paramString.length() > k + 1)
          {
            i = k + 1;
            j = 8;
          }
        }
      }
    }
  }
  
  private static boolean isHexNumber(String paramString)
  {
    boolean bool = false;
    if (paramString.startsWith("-")) {}
    for (int i = 1;; i = 0)
    {
      if ((paramString.startsWith("0x", i)) || (paramString.startsWith("0X", i)) || (paramString.startsWith("#", i))) {
        bool = true;
      }
      return bool;
    }
  }
  
  public static <T extends Number> T parseNumber(String paramString, Class<T> paramClass)
  {
    Assert.notNull(paramString, "Text must not be null");
    Assert.notNull(paramClass, "Target class must not be null");
    String str = StringUtils.trimAllWhitespace(paramString);
    if (paramClass.equals(Byte.class))
    {
      if (isHexNumber(str)) {
        return Byte.decode(str);
      }
      return Byte.valueOf(str);
    }
    if (paramClass.equals(Short.class))
    {
      if (isHexNumber(str)) {
        return Short.decode(str);
      }
      return Short.valueOf(str);
    }
    if (paramClass.equals(Integer.class))
    {
      if (isHexNumber(str)) {
        return Integer.decode(str);
      }
      return Integer.valueOf(str);
    }
    if (paramClass.equals(Long.class))
    {
      if (isHexNumber(str)) {
        return Long.decode(str);
      }
      return Long.valueOf(str);
    }
    if (paramClass.equals(BigInteger.class))
    {
      if (isHexNumber(str)) {
        return decodeBigInteger(str);
      }
      return new BigInteger(str);
    }
    if (paramClass.equals(Float.class)) {
      return Float.valueOf(str);
    }
    if (paramClass.equals(Double.class)) {
      return Double.valueOf(str);
    }
    if ((paramClass.equals(BigDecimal.class)) || (paramClass.equals(Number.class))) {
      return new BigDecimal(str);
    }
    throw new IllegalArgumentException("Cannot convert String [" + paramString + "] to target class [" + paramClass.getName() + "]");
  }
  
  public static <T extends Number> T parseNumber(String paramString, Class<T> paramClass, NumberFormat paramNumberFormat)
  {
    if (paramNumberFormat != null)
    {
      Assert.notNull(paramString, "Text must not be null");
      Assert.notNull(paramClass, "Target class must not be null");
      Object localObject = null;
      int j = 0;
      int i = j;
      if ((paramNumberFormat instanceof DecimalFormat))
      {
        DecimalFormat localDecimalFormat = (DecimalFormat)paramNumberFormat;
        localObject = localDecimalFormat;
        i = j;
        if (BigDecimal.class.equals(paramClass))
        {
          localObject = localDecimalFormat;
          i = j;
          if (!localDecimalFormat.isParseBigDecimal())
          {
            localDecimalFormat.setParseBigDecimal(true);
            i = 1;
            localObject = localDecimalFormat;
          }
        }
      }
      try
      {
        paramString = convertNumberToTargetClass(paramNumberFormat.parse(StringUtils.trimAllWhitespace(paramString)), paramClass);
        return paramString;
      }
      catch (ParseException paramString)
      {
        throw new IllegalArgumentException("Could not parse number: " + paramString.getMessage());
      }
      finally
      {
        if (i != 0) {
          ((DecimalFormat)localObject).setParseBigDecimal(false);
        }
      }
    }
    return parseNumber(paramString, paramClass);
  }
  
  private static void raiseOverflowException(Number paramNumber, Class<?> paramClass)
  {
    throw new IllegalArgumentException("Could not convert number [" + paramNumber + "] of type [" + paramNumber.getClass().getName() + "] to target class [" + paramClass.getName() + "]: overflow");
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.NumberUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */