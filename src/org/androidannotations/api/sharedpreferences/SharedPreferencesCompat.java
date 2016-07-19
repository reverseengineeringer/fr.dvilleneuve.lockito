package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public abstract class SharedPreferencesCompat
{
  private static final Method sApplyMethod = findMethod(SharedPreferences.Editor.class, "apply", new Class[0]);
  private static final Method sGetStringSetMethod = findMethod(SharedPreferences.class, "getStringSet", new Class[] { String.class, Set.class });
  private static final Method sPutStringSetMethod = findMethod(SharedPreferences.Editor.class, "putStringSet", new Class[] { String.class, Set.class });
  
  public static void apply(SharedPreferences.Editor paramEditor)
  {
    try
    {
      invoke(sApplyMethod, paramEditor, new Object[0]);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      paramEditor.commit();
    }
  }
  
  private static Method findMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, paramVarArgs);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass) {}
    return null;
  }
  
  public static Set<String> getStringSet(SharedPreferences paramSharedPreferences, String paramString, Set<String> paramSet)
  {
    try
    {
      paramSet = (Set)invoke(sGetStringSetMethod, paramSharedPreferences, new Object[] { paramString, paramSet });
      return paramSet;
    }
    catch (NoSuchMethodException paramSet) {}
    return SetXmlSerializer.deserialize(paramSharedPreferences.getString(paramString, null));
  }
  
  public static <T> T invoke(Method paramMethod, Object paramObject, Object... paramVarArgs)
    throws NoSuchMethodException
  {
    if (paramMethod == null) {
      throw new NoSuchMethodException();
    }
    try
    {
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      return (T)paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      throw new NoSuchMethodException(paramMethod.getName());
    }
    catch (IllegalArgumentException paramObject)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramObject)
    {
      for (;;) {}
    }
  }
  
  public static void putStringSet(SharedPreferences.Editor paramEditor, String paramString, Set<String> paramSet)
  {
    try
    {
      invoke(sPutStringSetMethod, paramEditor, new Object[] { paramString, paramSet });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      paramEditor.putString(paramString, SetXmlSerializer.serialize(paramSet));
    }
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.SharedPreferencesCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */