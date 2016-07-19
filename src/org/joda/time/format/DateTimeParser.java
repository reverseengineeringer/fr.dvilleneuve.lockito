package org.joda.time.format;

public abstract interface DateTimeParser
{
  public abstract int estimateParsedLength();
  
  public abstract int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt);
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */