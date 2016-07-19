package net.danlew.android.joda;

import android.util.Log;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResUtils
{
  private static final String TZDATA_PREFIX = "__tzdata_";
  private static Map<Class<?>, Map<String, Integer>> sIdentifierCache = new ConcurrentHashMap();
  
  private static String convertPathToResource(String paramString)
  {
    paramString = new File(paramString);
    ArrayList localArrayList = new ArrayList();
    File localFile;
    do
    {
      localArrayList.add(paramString.getName());
      localFile = paramString.getParentFile();
      paramString = localFile;
    } while (localFile != null);
    paramString = new StringBuffer();
    int i = localArrayList.size() - 1;
    while (i >= 0)
    {
      if (paramString.length() > 0) {
        paramString.append("_");
      }
      paramString.append((String)localArrayList.get(i));
      i -= 1;
    }
    return paramString.toString().replace('-', '_').replace("+", "plus").toLowerCase(Locale.US);
  }
  
  public static int getIdentifier(Class<?> paramClass, String paramString)
  {
    Object localObject;
    int i;
    if (!sIdentifierCache.containsKey(paramClass))
    {
      localObject = new ConcurrentHashMap();
      sIdentifierCache.put(paramClass, localObject);
      if (!((Map)localObject).containsKey(paramString)) {
        break label78;
      }
      i = ((Integer)((Map)localObject).get(paramString)).intValue();
    }
    for (;;)
    {
      return i;
      localObject = (Map)sIdentifierCache.get(paramClass);
      break;
      try
      {
        label78:
        int j = paramClass.getField(paramString).getInt(null);
        i = j;
        if (j != 0)
        {
          ((Map)localObject).put(paramString, Integer.valueOf(j));
          return j;
        }
      }
      catch (Exception localException)
      {
        Log.e("JodaTimeAndroid", "Failed to retrieve identifier: type=" + paramClass + " name=" + paramString, localException);
      }
    }
    return 0;
  }
  
  public static String getTzResource(String paramString)
  {
    return "__tzdata_" + convertPathToResource(paramString);
  }
  
  public static String getZoneInfoMapResource()
  {
    return "__tzdata_" + convertPathToResource("ZoneInfoMap");
  }
}

/* Location:
 * Qualified Name:     net.danlew.android.joda.ResUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */