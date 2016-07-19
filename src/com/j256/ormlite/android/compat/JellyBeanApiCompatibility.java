package com.j256.ormlite.android.compat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CancellationSignal;

public class JellyBeanApiCompatibility
  extends BasicApiCompatibility
{
  public ApiCompatibility.CancellationHook createCancellationHook()
  {
    return new JellyBeanCancellationHook();
  }
  
  public Cursor rawQuery(SQLiteDatabase paramSQLiteDatabase, String paramString, String[] paramArrayOfString, ApiCompatibility.CancellationHook paramCancellationHook)
  {
    if (paramCancellationHook == null) {
      return paramSQLiteDatabase.rawQuery(paramString, paramArrayOfString);
    }
    return paramSQLiteDatabase.rawQuery(paramString, paramArrayOfString, cancellationSignal);
  }
  
  protected static class JellyBeanCancellationHook
    implements ApiCompatibility.CancellationHook
  {
    private final CancellationSignal cancellationSignal = new CancellationSignal();
    
    public void cancel()
    {
      cancellationSignal.cancel();
    }
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.android.compat.JellyBeanApiCompatibility
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */