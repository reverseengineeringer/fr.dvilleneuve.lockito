package com.facebook.stetho.inspector.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

public abstract interface DatabasePeerManager$ExecuteResultHandler<T>
{
  public abstract T handleInsert(long paramLong)
    throws SQLiteException;
  
  public abstract T handleRawQuery()
    throws SQLiteException;
  
  public abstract T handleSelect(Cursor paramCursor)
    throws SQLiteException;
  
  public abstract T handleUpdateDelete(int paramInt)
    throws SQLiteException;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.database.DatabasePeerManager.ExecuteResultHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */