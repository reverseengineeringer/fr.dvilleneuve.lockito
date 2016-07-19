package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database.AddDatabaseEvent;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class DatabasePeerManager
  extends ChromePeerManager
{
  private static final String[] UNINTERESTING_FILENAME_SUFFIXES = { "-journal", "-shm", "-uid", "-wal" };
  private final Context mContext;
  private final DatabaseFilesProvider mDatabaseFilesProvider;
  private final PeerRegistrationListener mPeerRegistrationListener = new PeerRegistrationListener()
  {
    public void onPeerRegistered(JsonRpcPeer paramAnonymousJsonRpcPeer)
    {
      DatabasePeerManager.this.bootstrapNewPeer(paramAnonymousJsonRpcPeer);
    }
    
    public void onPeerUnregistered(JsonRpcPeer paramAnonymousJsonRpcPeer) {}
  };
  
  @Deprecated
  public DatabasePeerManager(Context paramContext)
  {
    this(paramContext, new DefaultDatabaseFilesProvider(paramContext));
  }
  
  public DatabasePeerManager(Context paramContext, DatabaseFilesProvider paramDatabaseFilesProvider)
  {
    mContext = paramContext;
    mDatabaseFilesProvider = paramDatabaseFilesProvider;
    setListener(mPeerRegistrationListener);
  }
  
  private void bootstrapNewPeer(JsonRpcPeer paramJsonRpcPeer)
  {
    Object localObject1 = mDatabaseFilesProvider.getDatabaseFiles();
    Collections.sort((List)localObject1);
    localObject1 = tidyDatabaseList((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (File)((Iterator)localObject1).next();
      Database.DatabaseObject localDatabaseObject = new Database.DatabaseObject();
      id = ((File)localObject2).getPath();
      name = ((File)localObject2).getName();
      domain = mContext.getPackageName();
      version = "N/A";
      localObject2 = new Database.AddDatabaseEvent();
      database = localDatabaseObject;
      paramJsonRpcPeer.invokeMethod("Database.addDatabase", localObject2, null);
    }
  }
  
  private <T> T executeInsert(SQLiteDatabase paramSQLiteDatabase, String paramString, ExecuteResultHandler<T> paramExecuteResultHandler)
  {
    return (T)paramExecuteResultHandler.handleInsert(paramSQLiteDatabase.compileStatement(paramString).executeInsert());
  }
  
  private <T> T executeRawQuery(SQLiteDatabase paramSQLiteDatabase, String paramString, ExecuteResultHandler<T> paramExecuteResultHandler)
  {
    paramSQLiteDatabase.execSQL(paramString);
    return (T)paramExecuteResultHandler.handleRawQuery();
  }
  
  private <T> T executeSelect(SQLiteDatabase paramSQLiteDatabase, String paramString, ExecuteResultHandler<T> paramExecuteResultHandler)
  {
    paramSQLiteDatabase = paramSQLiteDatabase.rawQuery(paramString, null);
    try
    {
      paramString = paramExecuteResultHandler.handleSelect(paramSQLiteDatabase);
      return paramString;
    }
    finally
    {
      paramSQLiteDatabase.close();
    }
  }
  
  @TargetApi(11)
  private <T> T executeUpdateDelete(SQLiteDatabase paramSQLiteDatabase, String paramString, ExecuteResultHandler<T> paramExecuteResultHandler)
  {
    return (T)paramExecuteResultHandler.handleUpdateDelete(paramSQLiteDatabase.compileStatement(paramString).executeUpdateDelete());
  }
  
  private static String getFirstWord(String paramString)
  {
    String str = paramString.trim();
    int i = str.indexOf(' ');
    paramString = str;
    if (i >= 0) {
      paramString = str.substring(0, i);
    }
    return paramString;
  }
  
  private SQLiteDatabase openDatabase(String paramString)
    throws SQLiteException
  {
    Util.throwIfNull(paramString);
    return SQLiteDatabase.openDatabase(mContext.getDatabasePath(paramString).getAbsolutePath(), null, 0);
  }
  
  private static String removeSuffix(String paramString, String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      String str = paramString;
      if (i < j)
      {
        str = paramArrayOfString[i];
        if (paramString.endsWith(str)) {
          str = paramString.substring(0, paramString.length() - str.length());
        }
      }
      else
      {
        return str;
      }
      i += 1;
    }
  }
  
  static List<File> tidyDatabaseList(List<File> paramList)
  {
    HashSet localHashSet = new HashSet(paramList);
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      File localFile = (File)paramList.next();
      String str1 = localFile.getPath();
      String str2 = removeSuffix(str1, UNINTERESTING_FILENAME_SUFFIXES);
      if ((str2.equals(str1)) || (!localHashSet.contains(new File(str2)))) {
        localArrayList.add(localFile);
      }
    }
    return localArrayList;
  }
  
  public <T> T executeSQL(String paramString1, String paramString2, ExecuteResultHandler<T> paramExecuteResultHandler)
    throws SQLiteException
  {
    Util.throwIfNull(paramString2);
    Util.throwIfNull(paramExecuteResultHandler);
    paramString1 = openDatabase(paramString1);
    for (;;)
    {
      int i;
      try
      {
        str = getFirstWord(paramString2).toUpperCase();
        i = -1;
        switch (str.hashCode())
        {
        case -1785516855: 
          paramString2 = executeRawQuery(paramString1, paramString2, paramExecuteResultHandler);
          return paramString2;
        }
      }
      finally
      {
        String str;
        paramString1.close();
      }
      if (str.equals("UPDATE"))
      {
        i = 0;
        break label257;
        if (str.equals("DELETE"))
        {
          i = 1;
          break label257;
          if (str.equals("INSERT"))
          {
            i = 2;
            break label257;
            if (str.equals("SELECT"))
            {
              i = 3;
              break label257;
              if (str.equals("PRAGMA"))
              {
                i = 4;
                break label257;
                if (str.equals("EXPLAIN"))
                {
                  i = 5;
                  break label257;
                  paramString2 = executeUpdateDelete(paramString1, paramString2, paramExecuteResultHandler);
                  paramString1.close();
                  return paramString2;
                  paramString2 = executeInsert(paramString1, paramString2, paramExecuteResultHandler);
                  paramString1.close();
                  return paramString2;
                  paramString2 = executeSelect(paramString1, paramString2, paramExecuteResultHandler);
                  paramString1.close();
                  return paramString2;
                }
              }
            }
          }
        }
      }
      label257:
      switch (i)
      {
      }
    }
  }
  
  public List<String> getDatabaseTableNames(String paramString)
    throws SQLiteException
  {
    paramString = openDatabase(paramString);
    try
    {
      Cursor localCursor = paramString.rawQuery("SELECT name FROM sqlite_master WHERE type=?", new String[] { "table" });
      try
      {
        ArrayList localArrayList = new ArrayList();
        while (localCursor.moveToNext())
        {
          localArrayList.add(localCursor.getString(0));
          continue;
          localObject = finally;
        }
      }
      finally
      {
        localCursor.close();
      }
      ((Cursor)localObject).close();
    }
    finally
    {
      paramString.close();
    }
    paramString.close();
    return localList;
  }
  
  public static abstract interface ExecuteResultHandler<T>
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
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.database.DatabasePeerManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */