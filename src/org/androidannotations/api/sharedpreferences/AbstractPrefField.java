package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class AbstractPrefField
{
  protected final String key;
  protected final SharedPreferences sharedPreferences;
  
  public AbstractPrefField(SharedPreferences paramSharedPreferences, String paramString)
  {
    sharedPreferences = paramSharedPreferences;
    key = paramString;
  }
  
  protected final void apply(SharedPreferences.Editor paramEditor)
  {
    SharedPreferencesCompat.apply(paramEditor);
  }
  
  protected SharedPreferences.Editor edit()
  {
    return sharedPreferences.edit();
  }
  
  public final boolean exists()
  {
    return sharedPreferences.contains(key);
  }
  
  public String key()
  {
    return key;
  }
  
  public final void remove()
  {
    apply(edit().remove(key));
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.AbstractPrefField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */