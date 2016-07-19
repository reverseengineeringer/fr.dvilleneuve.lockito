package org.joda.time.format;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PeriodFormat
{
  private static final String BUNDLE_NAME = "org.joda.time.format.messages";
  private static final ConcurrentMap<Locale, PeriodFormatter> FORMATTERS = new ConcurrentHashMap();
  
  public static PeriodFormatter getDefault()
  {
    return wordBased(Locale.ENGLISH);
  }
  
  public static PeriodFormatter wordBased()
  {
    return wordBased(Locale.getDefault());
  }
  
  public static PeriodFormatter wordBased(Locale paramLocale)
  {
    Object localObject2 = (PeriodFormatter)FORMATTERS.get(paramLocale);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = ResourceBundle.getBundle("org.joda.time.format.messages", paramLocale);
      localObject2 = new String[4];
      localObject2[0] = ((ResourceBundle)localObject1).getString("PeriodFormat.space");
      localObject2[1] = ((ResourceBundle)localObject1).getString("PeriodFormat.comma");
      localObject2[2] = ((ResourceBundle)localObject1).getString("PeriodFormat.commandand");
      localObject2[3] = ((ResourceBundle)localObject1).getString("PeriodFormat.commaspaceand");
      localObject1 = new PeriodFormatterBuilder().appendYears().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.year"), ((ResourceBundle)localObject1).getString("PeriodFormat.years")).appendSeparator(((ResourceBundle)localObject1).getString("PeriodFormat.commaspace"), ((ResourceBundle)localObject1).getString("PeriodFormat.spaceandspace"), (String[])localObject2).appendMonths().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.month"), ((ResourceBundle)localObject1).getString("PeriodFormat.months")).appendSeparator(((ResourceBundle)localObject1).getString("PeriodFormat.commaspace"), ((ResourceBundle)localObject1).getString("PeriodFormat.spaceandspace"), (String[])localObject2).appendWeeks().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.week"), ((ResourceBundle)localObject1).getString("PeriodFormat.weeks")).appendSeparator(((ResourceBundle)localObject1).getString("PeriodFormat.commaspace"), ((ResourceBundle)localObject1).getString("PeriodFormat.spaceandspace"), (String[])localObject2).appendDays().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.day"), ((ResourceBundle)localObject1).getString("PeriodFormat.days")).appendSeparator(((ResourceBundle)localObject1).getString("PeriodFormat.commaspace"), ((ResourceBundle)localObject1).getString("PeriodFormat.spaceandspace"), (String[])localObject2).appendHours().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.hour"), ((ResourceBundle)localObject1).getString("PeriodFormat.hours")).appendSeparator(((ResourceBundle)localObject1).getString("PeriodFormat.commaspace"), ((ResourceBundle)localObject1).getString("PeriodFormat.spaceandspace"), (String[])localObject2).appendMinutes().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.minute"), ((ResourceBundle)localObject1).getString("PeriodFormat.minutes")).appendSeparator(((ResourceBundle)localObject1).getString("PeriodFormat.commaspace"), ((ResourceBundle)localObject1).getString("PeriodFormat.spaceandspace"), (String[])localObject2).appendSeconds().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.second"), ((ResourceBundle)localObject1).getString("PeriodFormat.seconds")).appendSeparator(((ResourceBundle)localObject1).getString("PeriodFormat.commaspace"), ((ResourceBundle)localObject1).getString("PeriodFormat.spaceandspace"), (String[])localObject2).appendMillis().appendSuffix(((ResourceBundle)localObject1).getString("PeriodFormat.millisecond"), ((ResourceBundle)localObject1).getString("PeriodFormat.milliseconds")).toFormatter();
      FORMATTERS.putIfAbsent(paramLocale, localObject1);
    }
    return (PeriodFormatter)localObject1;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */