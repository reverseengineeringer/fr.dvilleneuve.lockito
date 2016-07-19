package org.joda.time.tz;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.DateTimeUtils;

public class DefaultNameProvider
  implements NameProvider
{
  private HashMap<Locale, Map<String, Map<String, Object>>> iByLocaleCache = createCache();
  
  private HashMap createCache()
  {
    return new HashMap(7);
  }
  
  private String[] getNameSet(Locale paramLocale, String paramString1, String paramString2)
  {
    if ((paramLocale == null) || (paramString1 == null) || (paramString2 == null))
    {
      paramLocale = null;
      return paramLocale;
    }
    for (;;)
    {
      int i;
      try
      {
        Object localObject2 = (Map)iByLocaleCache.get(paramLocale);
        Object localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject2 = iByLocaleCache;
          localObject1 = createCache();
          ((HashMap)localObject2).put(paramLocale, localObject1);
        }
        Object localObject3 = (Map)((Map)localObject1).get(paramString1);
        localObject2 = localObject3;
        if (localObject3 == null)
        {
          localObject3 = createCache();
          ((Map)localObject1).put(paramString1, localObject3);
          String[][] arrayOfString = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
          localObject2 = null;
          int j = arrayOfString.length;
          i = 0;
          localObject1 = localObject2;
          if (i < j)
          {
            localObject1 = arrayOfString[i];
            if ((localObject1 == null) || (localObject1.length != 5) || (!paramString1.equals(localObject1[0]))) {
              break label388;
            }
          }
          arrayOfString = DateTimeUtils.getDateFormatSymbols(paramLocale).getZoneStrings();
          localObject2 = null;
          j = arrayOfString.length;
          i = 0;
          paramLocale = (Locale)localObject2;
          if (i < j)
          {
            paramLocale = arrayOfString[i];
            if ((paramLocale == null) || (paramLocale.length != 5) || (!paramString1.equals(paramLocale[0]))) {
              break label397;
            }
          }
          localObject2 = localObject3;
          if (localObject1 != null)
          {
            localObject2 = localObject3;
            if (paramLocale != null)
            {
              ((Map)localObject3).put(localObject1[2], new String[] { paramLocale[2], paramLocale[1] });
              if (!localObject1[2].equals(localObject1[4])) {
                continue;
              }
              ((Map)localObject3).put(localObject1[4] + "-Summer", new String[] { paramLocale[4], paramLocale[3] });
              localObject2 = localObject3;
            }
          }
        }
        paramLocale = (String[])((Map)localObject2).get(paramString2);
        break;
        ((Map)localObject3).put(localObject1[4], new String[] { paramLocale[4], paramLocale[3] });
        localObject2 = localObject3;
        continue;
        i += 1;
      }
      finally {}
      label388:
      continue;
      label397:
      i += 1;
    }
  }
  
  public String getName(Locale paramLocale, String paramString1, String paramString2)
  {
    paramLocale = getNameSet(paramLocale, paramString1, paramString2);
    if (paramLocale == null) {
      return null;
    }
    return paramLocale[1];
  }
  
  public String getShortName(Locale paramLocale, String paramString1, String paramString2)
  {
    paramLocale = getNameSet(paramLocale, paramString1, paramString2);
    if (paramLocale == null) {
      return null;
    }
    return paramLocale[0];
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DefaultNameProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */