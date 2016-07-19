package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

class PeriodFormatterBuilder$PluralAffix
  implements PeriodFormatterBuilder.PeriodFieldAffix
{
  private final String iPluralText;
  private final String iSingularText;
  
  PeriodFormatterBuilder$PluralAffix(String paramString1, String paramString2)
  {
    iSingularText = paramString1;
    iPluralText = paramString2;
  }
  
  public int calculatePrintedLength(int paramInt)
  {
    if (paramInt == 1) {}
    for (String str = iSingularText;; str = iPluralText) {
      return str.length();
    }
  }
  
  public int parse(String paramString, int paramInt)
  {
    String str1 = iPluralText;
    String str3 = iSingularText;
    String str4 = str1;
    String str2 = str3;
    if (str1.length() < str3.length())
    {
      str2 = str1;
      str4 = str3;
    }
    if (paramString.regionMatches(true, paramInt, str4, 0, str4.length())) {
      return str4.length() + paramInt;
    }
    if (paramString.regionMatches(true, paramInt, str2, 0, str2.length())) {
      return str2.length() + paramInt;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
  
  public void printTo(Writer paramWriter, int paramInt)
    throws IOException
  {
    if (paramInt == 1) {}
    for (String str = iSingularText;; str = iPluralText)
    {
      paramWriter.write(str);
      return;
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, int paramInt)
  {
    if (paramInt == 1) {}
    for (String str = iSingularText;; str = iPluralText)
    {
      paramStringBuffer.append(str);
      return;
    }
  }
  
  public int scan(String paramString, int paramInt)
  {
    String str1 = iPluralText;
    String str3 = iSingularText;
    String str4 = str1;
    String str2 = str3;
    if (str1.length() < str3.length())
    {
      str2 = str1;
      str4 = str3;
    }
    int j = str4.length();
    int k = str2.length();
    int m = paramString.length();
    int i = paramInt;
    while (i < m)
    {
      if (paramString.regionMatches(true, i, str4, 0, j)) {}
      while (paramString.regionMatches(true, i, str2, 0, k)) {
        return i;
      }
      i += 1;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.PluralAffix
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */