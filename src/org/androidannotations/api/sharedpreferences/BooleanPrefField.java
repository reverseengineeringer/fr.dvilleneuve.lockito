package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class BooleanPrefField
  extends AbstractPrefField
{
  private final boolean defaultValue;
  
  BooleanPrefField(SharedPreferences paramSharedPreferences, String paramString, boolean paramBoolean)
  {
    super(paramSharedPreferences, paramString);
    defaultValue = paramBoolean;
  }
  
  public boolean get()
  {
    return getOr(defaultValue);
  }
  
  public boolean getOr(boolean paramBoolean)
  {
    return sharedPreferences.getBoolean(key, paramBoolean);
  }
  
  public void put(boolean paramBoolean)
  {
    apply(edit().putBoolean(key, paramBoolean));
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.BooleanPrefField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */