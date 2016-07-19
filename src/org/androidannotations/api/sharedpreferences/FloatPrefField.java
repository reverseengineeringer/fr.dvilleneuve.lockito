package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class FloatPrefField
  extends AbstractPrefField
{
  private final float defaultValue;
  
  FloatPrefField(SharedPreferences paramSharedPreferences, String paramString, float paramFloat)
  {
    super(paramSharedPreferences, paramString);
    defaultValue = paramFloat;
  }
  
  public float get()
  {
    return getOr(defaultValue);
  }
  
  public float getOr(float paramFloat)
  {
    try
    {
      float f = sharedPreferences.getFloat(key, paramFloat);
      return f;
    }
    catch (ClassCastException localClassCastException)
    {
      try
      {
        paramFloat = Float.parseFloat(sharedPreferences.getString(key, "" + paramFloat));
        return paramFloat;
      }
      catch (Exception localException)
      {
        throw localClassCastException;
      }
    }
  }
  
  public void put(float paramFloat)
  {
    apply(edit().putFloat(key, paramFloat));
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.FloatPrefField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */