package org.joda.time.tz;

import java.util.Map;
import java.util.StringTokenizer;

class ZoneInfoCompiler$Zone
{
  public final String iFormat;
  public final String iName;
  private Zone iNext;
  public final int iOffsetMillis;
  public final String iRules;
  public final ZoneInfoCompiler.DateTimeOfYear iUntilDateTimeOfYear;
  public final int iUntilYear;
  
  private ZoneInfoCompiler$Zone(String paramString, StringTokenizer paramStringTokenizer)
  {
    iName = paramString.intern();
    iOffsetMillis = ZoneInfoCompiler.parseTime(paramStringTokenizer.nextToken());
    iRules = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
    iFormat = paramStringTokenizer.nextToken().intern();
    int i = Integer.MAX_VALUE;
    ZoneInfoCompiler.DateTimeOfYear localDateTimeOfYear = ZoneInfoCompiler.getStartOfYear();
    paramString = localDateTimeOfYear;
    if (paramStringTokenizer.hasMoreTokens())
    {
      int j = Integer.parseInt(paramStringTokenizer.nextToken());
      paramString = localDateTimeOfYear;
      i = j;
      if (paramStringTokenizer.hasMoreTokens())
      {
        paramString = new ZoneInfoCompiler.DateTimeOfYear(paramStringTokenizer);
        i = j;
      }
    }
    iUntilYear = i;
    iUntilDateTimeOfYear = paramString;
  }
  
  ZoneInfoCompiler$Zone(StringTokenizer paramStringTokenizer)
  {
    this(paramStringTokenizer.nextToken(), paramStringTokenizer);
  }
  
  private static void addToBuilder(Zone paramZone, DateTimeZoneBuilder paramDateTimeZoneBuilder, Map<String, ZoneInfoCompiler.RuleSet> paramMap)
  {
    for (;;)
    {
      if (paramZone != null)
      {
        paramDateTimeZoneBuilder.setStandardOffset(iOffsetMillis);
        if (iRules != null) {
          break label40;
        }
        paramDateTimeZoneBuilder.setFixedSavings(iFormat, 0);
      }
      while (iUntilYear == Integer.MAX_VALUE)
      {
        return;
        try
        {
          label40:
          int i = ZoneInfoCompiler.parseTime(iRules);
          paramDateTimeZoneBuilder.setFixedSavings(iFormat, i);
        }
        catch (Exception localException)
        {
          ZoneInfoCompiler.RuleSet localRuleSet = (ZoneInfoCompiler.RuleSet)paramMap.get(iRules);
          if (localRuleSet == null) {
            throw new IllegalArgumentException("Rules not found: " + iRules);
          }
          localRuleSet.addRecurring(paramDateTimeZoneBuilder, iFormat);
        }
      }
      iUntilDateTimeOfYear.addCutover(paramDateTimeZoneBuilder, iUntilYear);
      paramZone = iNext;
    }
  }
  
  public void addToBuilder(DateTimeZoneBuilder paramDateTimeZoneBuilder, Map<String, ZoneInfoCompiler.RuleSet> paramMap)
  {
    addToBuilder(this, paramDateTimeZoneBuilder, paramMap);
  }
  
  void chain(StringTokenizer paramStringTokenizer)
  {
    if (iNext != null)
    {
      iNext.chain(paramStringTokenizer);
      return;
    }
    iNext = new Zone(iName, paramStringTokenizer);
  }
  
  public String toString()
  {
    String str = "[Zone]\nName: " + iName + "\n" + "OffsetMillis: " + iOffsetMillis + "\n" + "Rules: " + iRules + "\n" + "Format: " + iFormat + "\n" + "UntilYear: " + iUntilYear + "\n" + iUntilDateTimeOfYear;
    if (iNext == null) {
      return str;
    }
    return str + "...\n" + iNext.toString();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.ZoneInfoCompiler.Zone
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */