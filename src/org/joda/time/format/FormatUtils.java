package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

public class FormatUtils
{
  private static final double LOG_10 = Math.log(10.0D);
  
  public static void appendPaddedInteger(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < 0)
    {
      paramStringBuffer.append('-');
      i = paramInt2;
      if (paramInt1 != Integer.MIN_VALUE) {
        i = -paramInt1;
      }
    }
    else
    {
      if (i >= 10) {
        break label88;
      }
      while (paramInt2 > 1)
      {
        paramStringBuffer.append('0');
        paramInt2 -= 1;
      }
    }
    while (i > 10)
    {
      paramStringBuffer.append('0');
      i -= 1;
    }
    paramStringBuffer.append("2147483648");
    return;
    paramStringBuffer.append((char)(i + 48));
    return;
    label88:
    if (i < 100)
    {
      while (paramInt2 > 2)
      {
        paramStringBuffer.append('0');
        paramInt2 -= 1;
      }
      paramInt1 = (i + 1) * 13421772 >> 27;
      paramStringBuffer.append((char)(paramInt1 + 48));
      paramStringBuffer.append((char)(i - (paramInt1 << 3) - (paramInt1 << 1) + 48));
      return;
    }
    if (i < 1000) {
      paramInt1 = 3;
    }
    while (paramInt2 > paramInt1)
    {
      paramStringBuffer.append('0');
      paramInt2 -= 1;
      continue;
      if (i < 10000) {
        paramInt1 = 4;
      } else {
        paramInt1 = (int)(Math.log(i) / LOG_10) + 1;
      }
    }
    paramStringBuffer.append(Integer.toString(i));
  }
  
  public static void appendPaddedInteger(StringBuffer paramStringBuffer, long paramLong, int paramInt)
  {
    int i = (int)paramLong;
    if (i == paramLong)
    {
      appendPaddedInteger(paramStringBuffer, i, paramInt);
      return;
    }
    if (paramInt <= 19)
    {
      paramStringBuffer.append(Long.toString(paramLong));
      return;
    }
    long l = paramLong;
    if (paramLong < 0L)
    {
      paramStringBuffer.append('-');
      i = paramInt;
      if (paramLong != Long.MIN_VALUE) {
        l = -paramLong;
      }
    }
    else
    {
      i = (int)(Math.log(l) / LOG_10);
      while (paramInt > i + 1)
      {
        paramStringBuffer.append('0');
        paramInt -= 1;
      }
    }
    while (i > 19)
    {
      paramStringBuffer.append('0');
      i -= 1;
    }
    paramStringBuffer.append("9223372036854775808");
    return;
    paramStringBuffer.append(Long.toString(l));
  }
  
  public static void appendUnpaddedInteger(StringBuffer paramStringBuffer, int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0)
    {
      paramStringBuffer.append('-');
      if (paramInt != Integer.MIN_VALUE) {
        i = -paramInt;
      }
    }
    else
    {
      if (i >= 10) {
        break label47;
      }
      paramStringBuffer.append((char)(i + 48));
      return;
    }
    paramStringBuffer.append("2147483648");
    return;
    label47:
    if (i < 100)
    {
      paramInt = (i + 1) * 13421772 >> 27;
      paramStringBuffer.append((char)(paramInt + 48));
      paramStringBuffer.append((char)(i - (paramInt << 3) - (paramInt << 1) + 48));
      return;
    }
    paramStringBuffer.append(Integer.toString(i));
  }
  
  public static void appendUnpaddedInteger(StringBuffer paramStringBuffer, long paramLong)
  {
    int i = (int)paramLong;
    if (i == paramLong)
    {
      appendUnpaddedInteger(paramStringBuffer, i);
      return;
    }
    paramStringBuffer.append(Long.toString(paramLong));
  }
  
  public static int calculateDigitCount(long paramLong)
  {
    if (paramLong < 0L)
    {
      if (paramLong != Long.MIN_VALUE) {
        return calculateDigitCount(-paramLong) + 1;
      }
      return 20;
    }
    if (paramLong < 10L) {
      return 1;
    }
    if (paramLong < 100L) {
      return 2;
    }
    if (paramLong < 1000L) {
      return 3;
    }
    if (paramLong < 10000L) {
      return 4;
    }
    return (int)(Math.log(paramLong) / LOG_10) + 1;
  }
  
  static String createErrorMessage(String paramString, int paramInt)
  {
    int i = paramInt + 32;
    if (paramString.length() <= i + 3) {}
    for (String str = paramString; paramInt <= 0; str = paramString.substring(0, i).concat("...")) {
      return "Invalid format: \"" + str + '"';
    }
    if (paramInt >= paramString.length()) {
      return "Invalid format: \"" + str + "\" is too short";
    }
    return "Invalid format: \"" + str + "\" is malformed at \"" + str.substring(paramInt) + '"';
  }
  
  static int parseTwoDigits(String paramString, int paramInt)
  {
    int i = paramString.charAt(paramInt) - '0';
    return (i << 3) + (i << 1) + paramString.charAt(paramInt + 1) - 48;
  }
  
  public static void writePaddedInteger(Writer paramWriter, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt1;
    if (paramInt1 < 0)
    {
      paramWriter.write(45);
      i = paramInt2;
      if (paramInt1 != Integer.MIN_VALUE) {
        i = -paramInt1;
      }
    }
    else
    {
      if (i >= 10) {
        break label82;
      }
      while (paramInt2 > 1)
      {
        paramWriter.write(48);
        paramInt2 -= 1;
      }
    }
    while (i > 10)
    {
      paramWriter.write(48);
      i -= 1;
    }
    paramWriter.write("2147483648");
    return;
    paramWriter.write(i + 48);
    return;
    label82:
    if (i < 100)
    {
      while (paramInt2 > 2)
      {
        paramWriter.write(48);
        paramInt2 -= 1;
      }
      paramInt1 = (i + 1) * 13421772 >> 27;
      paramWriter.write(paramInt1 + 48);
      paramWriter.write(i - (paramInt1 << 3) - (paramInt1 << 1) + 48);
      return;
    }
    if (i < 1000) {
      paramInt1 = 3;
    }
    while (paramInt2 > paramInt1)
    {
      paramWriter.write(48);
      paramInt2 -= 1;
      continue;
      if (i < 10000) {
        paramInt1 = 4;
      } else {
        paramInt1 = (int)(Math.log(i) / LOG_10) + 1;
      }
    }
    paramWriter.write(Integer.toString(i));
  }
  
  public static void writePaddedInteger(Writer paramWriter, long paramLong, int paramInt)
    throws IOException
  {
    int i = (int)paramLong;
    if (i == paramLong)
    {
      writePaddedInteger(paramWriter, i, paramInt);
      return;
    }
    if (paramInt <= 19)
    {
      paramWriter.write(Long.toString(paramLong));
      return;
    }
    long l = paramLong;
    if (paramLong < 0L)
    {
      paramWriter.write(45);
      i = paramInt;
      if (paramLong != Long.MIN_VALUE) {
        l = -paramLong;
      }
    }
    else
    {
      i = (int)(Math.log(l) / LOG_10);
      while (paramInt > i + 1)
      {
        paramWriter.write(48);
        paramInt -= 1;
      }
    }
    while (i > 19)
    {
      paramWriter.write(48);
      i -= 1;
    }
    paramWriter.write("9223372036854775808");
    return;
    paramWriter.write(Long.toString(l));
  }
  
  public static void writeUnpaddedInteger(Writer paramWriter, int paramInt)
    throws IOException
  {
    int i = paramInt;
    if (paramInt < 0)
    {
      paramWriter.write(45);
      if (paramInt != Integer.MIN_VALUE) {
        i = -paramInt;
      }
    }
    else
    {
      if (i >= 10) {
        break label43;
      }
      paramWriter.write(i + 48);
      return;
    }
    paramWriter.write("2147483648");
    return;
    label43:
    if (i < 100)
    {
      paramInt = (i + 1) * 13421772 >> 27;
      paramWriter.write(paramInt + 48);
      paramWriter.write(i - (paramInt << 3) - (paramInt << 1) + 48);
      return;
    }
    paramWriter.write(Integer.toString(i));
  }
  
  public static void writeUnpaddedInteger(Writer paramWriter, long paramLong)
    throws IOException
  {
    int i = (int)paramLong;
    if (i == paramLong)
    {
      writeUnpaddedInteger(paramWriter, i);
      return;
    }
    paramWriter.write(Long.toString(paramLong));
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.FormatUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */