package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

abstract interface PeriodFormatterBuilder$PeriodFieldAffix
{
  public abstract int calculatePrintedLength(int paramInt);
  
  public abstract int parse(String paramString, int paramInt);
  
  public abstract void printTo(Writer paramWriter, int paramInt)
    throws IOException;
  
  public abstract void printTo(StringBuffer paramStringBuffer, int paramInt);
  
  public abstract int scan(String paramString, int paramInt);
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */