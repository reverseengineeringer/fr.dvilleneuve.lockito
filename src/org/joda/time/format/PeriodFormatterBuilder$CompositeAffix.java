package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

class PeriodFormatterBuilder$CompositeAffix
  implements PeriodFormatterBuilder.PeriodFieldAffix
{
  private final PeriodFormatterBuilder.PeriodFieldAffix iLeft;
  private final PeriodFormatterBuilder.PeriodFieldAffix iRight;
  
  PeriodFormatterBuilder$CompositeAffix(PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix1, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix2)
  {
    iLeft = paramPeriodFieldAffix1;
    iRight = paramPeriodFieldAffix2;
  }
  
  public int calculatePrintedLength(int paramInt)
  {
    return iLeft.calculatePrintedLength(paramInt) + iRight.calculatePrintedLength(paramInt);
  }
  
  public int parse(String paramString, int paramInt)
  {
    int i = iLeft.parse(paramString, paramInt);
    paramInt = i;
    if (i >= 0) {
      paramInt = iRight.parse(paramString, i);
    }
    return paramInt;
  }
  
  public void printTo(Writer paramWriter, int paramInt)
    throws IOException
  {
    iLeft.printTo(paramWriter, paramInt);
    iRight.printTo(paramWriter, paramInt);
  }
  
  public void printTo(StringBuffer paramStringBuffer, int paramInt)
  {
    iLeft.printTo(paramStringBuffer, paramInt);
    iRight.printTo(paramStringBuffer, paramInt);
  }
  
  public int scan(String paramString, int paramInt)
  {
    int i = iLeft.scan(paramString, paramInt);
    if (i >= 0) {
      return iRight.scan(paramString, i);
    }
    return paramInt ^ 0xFFFFFFFF;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.CompositeAffix
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */