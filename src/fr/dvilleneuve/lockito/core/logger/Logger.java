package fr.dvilleneuve.lockito.core.logger;

import android.util.Log;
import com.crashlytics.android.Crashlytics;
import java.util.WeakHashMap;

public class Logger
{
  private static final WeakHashMap<String, String> SHORTEN_CLASS_NAMES = new WeakHashMap();
  private static final String TAG = "Lockito";
  
  public static void debug(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(improveLog(paramString), paramVarArgs);
    if (Log.isLoggable("Lockito", 3)) {
      Log.d("Lockito", paramString);
    }
    Crashlytics.log(String.format("%s: %s", new Object[] { "DEBUG", paramString }));
  }
  
  public static void error(String paramString, Throwable paramThrowable, Object... paramVarArgs)
  {
    paramString = String.format(improveLog(paramString), paramVarArgs);
    if (Log.isLoggable("Lockito", 6)) {
      Log.e("Lockito", paramString, paramThrowable);
    }
    Crashlytics.log(String.format("%s: %s", new Object[] { "ERROR", paramString }));
    if (paramThrowable != null) {
      Crashlytics.logException(paramThrowable);
    }
  }
  
  public static void error(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(improveLog(paramString), paramVarArgs);
    if (Log.isLoggable("Lockito", 6)) {
      Log.e("Lockito", paramString);
    }
    Crashlytics.log(String.format("%s: %s", new Object[] { "ERROR", paramString }));
  }
  
  protected static String improveLog(String paramString)
  {
    String str = retrieveCaller();
    if (str == null) {
      return paramString;
    }
    return String.format("%s - %s", new Object[] { str, paramString });
  }
  
  public static void info(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(improveLog(paramString), paramVarArgs);
    if (Log.isLoggable("Lockito", 4)) {
      Log.i("Lockito", paramString);
    }
    Crashlytics.log(String.format("%s: %s", new Object[] { "INFO", paramString }));
  }
  
  protected static String retrieveCaller()
  {
    int i = 0;
    for (;;)
    {
      int k;
      try
      {
        Object localObject1 = Thread.currentThread().getStackTrace();
        j = 0;
        int m = localObject1.length;
        if (i < m)
        {
          Object localObject2 = localObject1[i];
          if (Logger.class.getCanonicalName().equals(((StackTraceElement)localObject2).getClassName()))
          {
            k = 1;
            break label94;
          }
          k = j;
          if (j == 0) {
            break label94;
          }
          localObject1 = String.format("%s:%d", new Object[] { shortenCanonicalName(((StackTraceElement)localObject2).getClassName()), Integer.valueOf(((StackTraceElement)localObject2).getLineNumber()) });
          return (String)localObject1;
        }
      }
      catch (Exception localException) {}
      return null;
      label94:
      i += 1;
      int j = k;
    }
  }
  
  protected static String shortenCanonicalName(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    synchronized (SHORTEN_CLASS_NAMES)
    {
      String str = (String)SHORTEN_CLASS_NAMES.get(paramString);
      Object localObject = str;
      if (str == null)
      {
        localObject = new StringBuilder();
        int j = 0;
        int i = -1;
        int m;
        int k;
        do
        {
          ((StringBuilder)localObject).append(paramString.charAt(i + 1));
          m = paramString.indexOf('.', i + 1);
          k = j;
          if (m > -1)
          {
            ((StringBuilder)localObject).append('.');
            k = m + 1;
          }
          j = k;
          i = m;
        } while (m > -1);
        if (k < paramString.length() - 1) {
          ((StringBuilder)localObject).append(paramString.substring(k + 1));
        }
        localObject = ((StringBuilder)localObject).toString();
        SHORTEN_CLASS_NAMES.put(paramString, localObject);
      }
      return (String)localObject;
    }
  }
  
  public static void warn(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(improveLog(paramString), paramVarArgs);
    if (Log.isLoggable("Lockito", 5)) {
      Log.w("Lockito", paramString);
    }
    Crashlytics.log(String.format("%s: %s", new Object[] { "WARN", paramString }));
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.logger.Logger
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */