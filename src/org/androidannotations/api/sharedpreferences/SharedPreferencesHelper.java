package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Set;

public abstract class SharedPreferencesHelper
{
  private final SharedPreferences sharedPreferences;
  
  public SharedPreferencesHelper(SharedPreferences paramSharedPreferences)
  {
    sharedPreferences = paramSharedPreferences;
  }
  
  protected BooleanPrefField booleanField(String paramString, boolean paramBoolean)
  {
    return new BooleanPrefField(sharedPreferences, paramString, paramBoolean);
  }
  
  public final void clear()
  {
    SharedPreferencesCompat.apply(sharedPreferences.edit().clear());
  }
  
  protected FloatPrefField floatField(String paramString, float paramFloat)
  {
    return new FloatPrefField(sharedPreferences, paramString, paramFloat);
  }
  
  public final SharedPreferences getSharedPreferences()
  {
    return sharedPreferences;
  }
  
  protected IntPrefField intField(String paramString, int paramInt)
  {
    return new IntPrefField(sharedPreferences, paramString, paramInt);
  }
  
  protected LongPrefField longField(String paramString, long paramLong)
  {
    return new LongPrefField(sharedPreferences, paramString, paramLong);
  }
  
  protected StringPrefField stringField(String paramString1, String paramString2)
  {
    return new StringPrefField(sharedPreferences, paramString1, paramString2);
  }
  
  protected StringSetPrefField stringSetField(String paramString, Set<String> paramSet)
  {
    return new StringSetPrefField(sharedPreferences, paramString, paramSet);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.SharedPreferencesHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */