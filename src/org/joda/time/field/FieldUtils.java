package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

public class FieldUtils
{
  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      return true;
    }
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return false;
    }
    return paramObject1.equals(paramObject2);
  }
  
  public static int getWrappedValue(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 >= paramInt3) {
      throw new IllegalArgumentException("MIN > MAX");
    }
    paramInt3 = paramInt3 - paramInt2 + 1;
    paramInt1 -= paramInt2;
    if (paramInt1 >= 0) {
      return paramInt1 % paramInt3 + paramInt2;
    }
    paramInt1 = -paramInt1 % paramInt3;
    if (paramInt1 == 0) {
      return paramInt2 + 0;
    }
    return paramInt3 - paramInt1 + paramInt2;
  }
  
  public static int getWrappedValue(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return getWrappedValue(paramInt1 + paramInt2, paramInt3, paramInt4);
  }
  
  public static int safeAdd(int paramInt1, int paramInt2)
  {
    int i = paramInt1 + paramInt2;
    if (((paramInt1 ^ i) < 0) && ((paramInt1 ^ paramInt2) >= 0)) {
      throw new ArithmeticException("The calculation caused an overflow: " + paramInt1 + " + " + paramInt2);
    }
    return i;
  }
  
  public static long safeAdd(long paramLong1, long paramLong2)
  {
    long l = paramLong1 + paramLong2;
    if (((paramLong1 ^ l) < 0L) && ((paramLong1 ^ paramLong2) >= 0L)) {
      throw new ArithmeticException("The calculation caused an overflow: " + paramLong1 + " + " + paramLong2);
    }
    return l;
  }
  
  public static long safeDivide(long paramLong1, long paramLong2)
  {
    if ((paramLong1 == Long.MIN_VALUE) && (paramLong2 == -1L)) {
      throw new ArithmeticException("Multiplication overflows a long: " + paramLong1 + " / " + paramLong2);
    }
    return paramLong1 / paramLong2;
  }
  
  public static int safeMultiply(int paramInt1, int paramInt2)
  {
    long l = paramInt1 * paramInt2;
    if ((l < -2147483648L) || (l > 2147483647L)) {
      throw new ArithmeticException("Multiplication overflows an int: " + paramInt1 + " * " + paramInt2);
    }
    return (int)l;
  }
  
  public static long safeMultiply(long paramLong, int paramInt)
  {
    long l = paramLong;
    switch (paramInt)
    {
    default: 
      l = paramLong * paramInt;
      if (l / paramInt != paramLong) {
        throw new ArithmeticException("Multiplication overflows a long: " + paramLong + " * " + paramInt);
      }
      break;
    case -1: 
      if (paramLong == Long.MIN_VALUE) {
        throw new ArithmeticException("Multiplication overflows a long: " + paramLong + " * " + paramInt);
      }
      l = -paramLong;
    case 1: 
      return l;
    case 0: 
      return 0L;
    }
    return l;
  }
  
  public static long safeMultiply(long paramLong1, long paramLong2)
  {
    if (paramLong2 == 1L) {
      return paramLong1;
    }
    if (paramLong1 == 1L) {
      return paramLong2;
    }
    if ((paramLong1 == 0L) || (paramLong2 == 0L)) {
      return 0L;
    }
    long l = paramLong1 * paramLong2;
    if ((l / paramLong2 != paramLong1) || ((paramLong1 == Long.MIN_VALUE) && (paramLong2 == -1L)) || ((paramLong2 == Long.MIN_VALUE) && (paramLong1 == -1L))) {
      throw new ArithmeticException("Multiplication overflows a long: " + paramLong1 + " * " + paramLong2);
    }
    return l;
  }
  
  public static int safeMultiplyToInt(long paramLong1, long paramLong2)
  {
    return safeToInt(safeMultiply(paramLong1, paramLong2));
  }
  
  public static int safeNegate(int paramInt)
  {
    if (paramInt == Integer.MIN_VALUE) {
      throw new ArithmeticException("Integer.MIN_VALUE cannot be negated");
    }
    return -paramInt;
  }
  
  public static long safeSubtract(long paramLong1, long paramLong2)
  {
    long l = paramLong1 - paramLong2;
    if (((paramLong1 ^ l) < 0L) && ((paramLong1 ^ paramLong2) < 0L)) {
      throw new ArithmeticException("The calculation caused an overflow: " + paramLong1 + " - " + paramLong2);
    }
    return l;
  }
  
  public static int safeToInt(long paramLong)
  {
    if ((-2147483648L <= paramLong) && (paramLong <= 2147483647L)) {
      return (int)paramLong;
    }
    throw new ArithmeticException("Value cannot fit in an int: " + paramLong);
  }
  
  public static void verifyValueBounds(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
      throw new IllegalFieldValueException(paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3));
    }
  }
  
  public static void verifyValueBounds(DateTimeField paramDateTimeField, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
      throw new IllegalFieldValueException(paramDateTimeField.getType(), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3));
    }
  }
  
  public static void verifyValueBounds(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
      throw new IllegalFieldValueException(paramDateTimeFieldType, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3));
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.FieldUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */