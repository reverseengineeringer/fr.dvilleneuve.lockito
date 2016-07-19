package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class LongPrefField
  extends AbstractPrefField
{
  private final long defaultValue;
  
  LongPrefField(SharedPreferences paramSharedPreferences, String paramString, long paramLong)
  {
    super(paramSharedPreferences, paramString);
    defaultValue = paramLong;
  }
  
  public long get()
  {
    return getOr(defaultValue);
  }
  
  public long getOr(long paramLong)
  {
    try
    {
      long l = sharedPreferences.getLong(key, paramLong);
      return l;
    }
    catch (ClassCastException localClassCastException)
    {
      try
      {
        paramLong = Long.parseLong(sharedPreferences.getString(key, "" + paramLong));
        return paramLong;
      }
      catch (Exception localException)
      {
        throw localClassCastException;
      }
    }
  }
  
  public void put(long paramLong)
  {
    apply(edit().putLong(key, paramLong));
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.LongPrefField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */