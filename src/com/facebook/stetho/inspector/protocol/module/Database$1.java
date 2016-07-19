package com.facebook.stetho.inspector.protocol.module;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.database.DatabasePeerManager.ExecuteResultHandler;
import java.util.Arrays;

class Database$1
  implements DatabasePeerManager.ExecuteResultHandler<Database.ExecuteSQLResponse>
{
  Database$1(Database paramDatabase) {}
  
  public Database.ExecuteSQLResponse handleInsert(long paramLong)
    throws SQLiteException
  {
    Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
    columnNames = Arrays.asList(new String[] { "ID of last inserted row" });
    values = Arrays.asList(new Object[] { Long.valueOf(paramLong) });
    return localExecuteSQLResponse;
  }
  
  public Database.ExecuteSQLResponse handleRawQuery()
    throws SQLiteException
  {
    Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
    columnNames = Arrays.asList(new String[] { "success" });
    values = Arrays.asList(new Object[] { "true" });
    return localExecuteSQLResponse;
  }
  
  public Database.ExecuteSQLResponse handleSelect(Cursor paramCursor)
    throws SQLiteException
  {
    Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
    columnNames = Arrays.asList(paramCursor.getColumnNames());
    values = Database.access$200(this$0, paramCursor, 250);
    return localExecuteSQLResponse;
  }
  
  public Database.ExecuteSQLResponse handleUpdateDelete(int paramInt)
    throws SQLiteException
  {
    Database.ExecuteSQLResponse localExecuteSQLResponse = new Database.ExecuteSQLResponse(null);
    columnNames = Arrays.asList(new String[] { "Modified rows" });
    values = Arrays.asList(new Object[] { Integer.valueOf(paramInt) });
    return localExecuteSQLResponse;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Database.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */