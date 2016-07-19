package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

class PeriodFormatterBuilder$SimpleAffix
  implements PeriodFormatterBuilder.PeriodFieldAffix
{
  private final String iText;
  
  PeriodFormatterBuilder$SimpleAffix(String paramString)
  {
    iText = paramString;
  }
  
  public int calculatePrintedLength(int paramInt)
  {
    return iText.length();
  }
  
  public int parse(String paramString, int paramInt)
  {
    String str = iText;
    int i = str.length();
    if (paramString.regionMatches(true, paramInt, str, 0, i)) {
      return paramInt + i;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
  
  public void printTo(Writer paramWriter, int paramInt)
    throws IOException
  {
    paramWriter.write(iText);
  }
  
  public void printTo(StringBuffer paramStringBuffer, int paramInt)
  {
    paramStringBuffer.append(iText);
  }
  
  public int scan(String paramString, int paramInt)
  {
    String str = iText;
    int j = str.length();
    int k = paramString.length();
    int i = paramInt;
    for (;;)
    {
      if (i < k) {
        if (paramString.regionMatches(true, i, str, 0, j)) {
          return i;
        }
      }
      switch (paramString.charAt(i))
      {
      case '/': 
      default: 
        return paramInt ^ 0xFFFFFFFF;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.SimpleAffix
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */