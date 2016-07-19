package com.j256.ormlite.android.compat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract interface ApiCompatibility
{
  public abstract CancellationHook createCancellationHook();
  
  public abstract Cursor rawQuery(SQLiteDatabase paramSQLiteDatabase, String paramString, String[] paramArrayOfString, CancellationHook paramCancellationHook);
  
  public static abstract interface CancellationHook
  {
    public abstract void cancel();
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.android.compat.ApiCompatibility
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */