package org.joda.time.tz;

import java.util.StringTokenizer;

class ZoneInfoCompiler$Rule
{
  public final ZoneInfoCompiler.DateTimeOfYear iDateTimeOfYear;
  public final int iFromYear;
  public final String iLetterS;
  public final String iName;
  public final int iSaveMillis;
  public final int iToYear;
  public final String iType;
  
  ZoneInfoCompiler$Rule(StringTokenizer paramStringTokenizer)
  {
    iName = paramStringTokenizer.nextToken().intern();
    iFromYear = ZoneInfoCompiler.parseYear(paramStringTokenizer.nextToken(), 0);
    iToYear = ZoneInfoCompiler.parseYear(paramStringTokenizer.nextToken(), iFromYear);
    if (iToYear < iFromYear) {
      throw new IllegalArgumentException();
    }
    iType = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
    iDateTimeOfYear = new ZoneInfoCompiler.DateTimeOfYear(paramStringTokenizer);
    iSaveMillis = ZoneInfoCompiler.parseTime(paramStringTokenizer.nextToken());
    iLetterS = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
  }
  
  private String formatName(String paramString)
  {
    int i = paramString.indexOf('/');
    if (i > 0) {
      if (iSaveMillis == 0) {
        str = paramString.substring(0, i).intern();
      }
    }
    do
    {
      return str;
      return paramString.substring(i + 1).intern();
      i = paramString.indexOf("%s");
      str = paramString;
    } while (i < 0);
    String str = paramString.substring(0, i);
    paramString = paramString.substring(i + 2);
    if (iLetterS == null) {}
    for (paramString = str.concat(paramString);; paramString = str + iLetterS + paramString) {
      return paramString.intern();
    }
  }
  
  public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString)
  {
    paramString = formatName(paramString);
    iDateTimeOfYear.addRecurring(paramDateTimeZoneBuilder, paramString, iSaveMillis, iFromYear, iToYear);
  }
  
  public String toString()
  {
    return "[Rule]\nName: " + iName + "\n" + "FromYear: " + iFromYear + "\n" + "ToYear: " + iToYear + "\n" + "Type: " + iType + "\n" + iDateTimeOfYear + "SaveMillis: " + iSaveMillis + "\n" + "LetterS: " + iLetterS + "\n";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.ZoneInfoCompiler.Rule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */