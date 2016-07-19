package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class IntPrefField
  extends AbstractPrefField
{
  private final int defaultValue;
  
  IntPrefField(SharedPreferences paramSharedPreferences, String paramString, int paramInt)
  {
    super(paramSharedPreferences, paramString);
    defaultValue = paramInt;
  }
  
  public int get()
  {
    return getOr(defaultValue);
  }
  
  public int getOr(int paramInt)
  {
    try
    {
      int i = sharedPreferences.getInt(key, paramInt);
      return i;
    }
    catch (ClassCastException localClassCastException)
    {
      try
      {
        paramInt = Integer.parseInt(sharedPreferences.getString(key, "" + paramInt));
        return paramInt;
      }
      catch (Exception localException)
      {
        throw localClassCastException;
      }
    }
  }
  
  public void put(int paramInt)
  {
    apply(edit().putInt(key, paramInt));
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.IntPrefField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */