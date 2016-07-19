package org.joda.time.tz;

import java.util.ArrayList;
import java.util.List;

class ZoneInfoCompiler$RuleSet
{
  private List<ZoneInfoCompiler.Rule> iRules = new ArrayList();
  
  ZoneInfoCompiler$RuleSet(ZoneInfoCompiler.Rule paramRule)
  {
    iRules.add(paramRule);
  }
  
  public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString)
  {
    int i = 0;
    while (i < iRules.size())
    {
      ((ZoneInfoCompiler.Rule)iRules.get(i)).addRecurring(paramDateTimeZoneBuilder, paramString);
      i += 1;
    }
  }
  
  void addRule(ZoneInfoCompiler.Rule paramRule)
  {
    if (!iName.equals(iRules.get(0)).iName)) {
      throw new IllegalArgumentException("Rule name mismatch");
    }
    iRules.add(paramRule);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.ZoneInfoCompiler.RuleSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */