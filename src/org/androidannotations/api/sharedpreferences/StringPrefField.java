package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class StringPrefField
  extends AbstractPrefField
{
  private final String defaultValue;
  
  StringPrefField(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    super(paramSharedPreferences, paramString1);
    defaultValue = paramString2;
  }
  
  public String get()
  {
    return getOr(defaultValue);
  }
  
  public String getOr(String paramString)
  {
    return sharedPreferences.getString(key, paramString);
  }
  
  public void put(String paramString)
  {
    apply(edit().putString(key, paramString));
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.StringPrefField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */