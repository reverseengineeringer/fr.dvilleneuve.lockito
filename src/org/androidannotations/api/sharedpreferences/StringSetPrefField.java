package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Set;

public final class StringSetPrefField
  extends AbstractPrefField
{
  private final Set<String> defaultValue;
  
  StringSetPrefField(SharedPreferences paramSharedPreferences, String paramString, Set<String> paramSet)
  {
    super(paramSharedPreferences, paramString);
    defaultValue = paramSet;
  }
  
  public Set<String> get()
  {
    return getOr(defaultValue);
  }
  
  public Set<String> getOr(Set<String> paramSet)
  {
    return SharedPreferencesCompat.getStringSet(sharedPreferences, key, paramSet);
  }
  
  public void put(Set<String> paramSet)
  {
    SharedPreferences.Editor localEditor = sharedPreferences.edit();
    SharedPreferencesCompat.putStringSet(localEditor, key, paramSet);
    apply(localEditor);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.StringSetPrefField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */