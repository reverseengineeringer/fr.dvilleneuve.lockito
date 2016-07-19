package com.j256.ormlite.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.j256.ormlite.android.compat.ApiCompatibility;
import com.j256.ormlite.android.compat.ApiCompatibility.CancellationHook;
import com.j256.ormlite.android.compat.ApiCompatibilityUtils;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseResults;
import java.util.ArrayList;
import java.util.List;

public class AndroidCompiledStatement
  implements CompiledStatement
{
  private static final String[] NO_STRING_ARGS = new String[0];
  private static final ApiCompatibility apiCompatibility = ApiCompatibilityUtils.getCompatibility();
  private static Logger logger = LoggerFactory.getLogger(AndroidCompiledStatement.class);
  private List<Object> args;
  private final boolean cancelQueriesEnabled;
  private ApiCompatibility.CancellationHook cancellationHook;
  private Cursor cursor;
  private final SQLiteDatabase db;
  private Integer max;
  private final String sql;
  private final StatementBuilder.StatementType type;
  
  public AndroidCompiledStatement(String paramString, SQLiteDatabase paramSQLiteDatabase, StatementBuilder.StatementType paramStatementType, boolean paramBoolean)
  {
    sql = paramString;
    db = paramSQLiteDatabase;
    type = paramStatementType;
    cancelQueriesEnabled = paramBoolean;
  }
  
  static int execSql(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, Object[] paramArrayOfObject)
    throws java.sql.SQLException
  {
    for (;;)
    {
      SQLiteDatabase localSQLiteDatabase;
      int j;
      int i;
      try
      {
        paramSQLiteDatabase.execSQL(paramString2, paramArrayOfObject);
        localSQLiteDatabase = null;
        paramArrayOfObject = null;
      }
      catch (android.database.SQLException paramSQLiteDatabase)
      {
        long l;
        throw SqlExceptionUtil.create("Problems executing " + paramString1 + " Android statement: " + paramString2, paramSQLiteDatabase);
      }
      label136:
      try
      {
        paramSQLiteDatabase = paramSQLiteDatabase.compileStatement("SELECT CHANGES()");
        paramArrayOfObject = paramSQLiteDatabase;
        localSQLiteDatabase = paramSQLiteDatabase;
        l = paramSQLiteDatabase.simpleQueryForLong();
        j = (int)l;
        i = j;
        if (paramSQLiteDatabase != null)
        {
          paramSQLiteDatabase.close();
          i = j;
        }
      }
      catch (android.database.SQLException paramSQLiteDatabase)
      {
        j = 1;
        i = j;
        if (paramArrayOfObject == null) {
          continue;
        }
        paramArrayOfObject.close();
        i = j;
      }
      finally
      {
        if (localSQLiteDatabase == null) {
          break label136;
        }
        localSQLiteDatabase.close();
      }
    }
    logger.trace("executing statement {} changed {} rows: {}", paramString1, Integer.valueOf(i), paramString2);
    return i;
  }
  
  private Object[] getArgArray()
  {
    if (args == null) {
      return NO_STRING_ARGS;
    }
    return args.toArray(new Object[args.size()]);
  }
  
  private String[] getStringArray()
  {
    if (args == null) {
      return NO_STRING_ARGS;
    }
    return (String[])args.toArray(new String[args.size()]);
  }
  
  private void isInPrep()
    throws java.sql.SQLException
  {
    if (cursor != null) {
      throw new java.sql.SQLException("Query already run. Cannot add argument values.");
    }
  }
  
  public void cancel()
  {
    if (cancellationHook != null) {
      cancellationHook.cancel();
    }
  }
  
  public void close()
    throws java.sql.SQLException
  {
    if (cursor != null) {}
    try
    {
      cursor.close();
      cancellationHook = null;
      return;
    }
    catch (android.database.SQLException localSQLException)
    {
      throw SqlExceptionUtil.create("Problems closing Android cursor", localSQLException);
    }
  }
  
  public void closeQuietly()
  {
    try
    {
      close();
      return;
    }
    catch (java.sql.SQLException localSQLException) {}
  }
  
  public int getColumnCount()
    throws java.sql.SQLException
  {
    return getCursor().getColumnCount();
  }
  
  public String getColumnName(int paramInt)
    throws java.sql.SQLException
  {
    return getCursor().getColumnName(paramInt);
  }
  
  /* Error */
  public Cursor getCursor()
    throws java.sql.SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 149	com/j256/ormlite/android/AndroidCompiledStatement:cursor	Landroid/database/Cursor;
    //   4: ifnonnull +95 -> 99
    //   7: aconst_null
    //   8: astore_1
    //   9: aload_1
    //   10: astore_2
    //   11: aload_0
    //   12: getfield 181	com/j256/ormlite/android/AndroidCompiledStatement:max	Ljava/lang/Integer;
    //   15: ifnonnull +89 -> 104
    //   18: aload_1
    //   19: astore_2
    //   20: aload_0
    //   21: getfield 60	com/j256/ormlite/android/AndroidCompiledStatement:sql	Ljava/lang/String;
    //   24: astore_1
    //   25: aload_1
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 66	com/j256/ormlite/android/AndroidCompiledStatement:cancelQueriesEnabled	Z
    //   31: ifeq +17 -> 48
    //   34: aload_1
    //   35: astore_2
    //   36: aload_0
    //   37: getstatic 53	com/j256/ormlite/android/AndroidCompiledStatement:apiCompatibility	Lcom/j256/ormlite/android/compat/ApiCompatibility;
    //   40: invokeinterface 187 1 0
    //   45: putfield 157	com/j256/ormlite/android/AndroidCompiledStatement:cancellationHook	Lcom/j256/ormlite/android/compat/ApiCompatibility$CancellationHook;
    //   48: aload_1
    //   49: astore_2
    //   50: aload_0
    //   51: getstatic 53	com/j256/ormlite/android/AndroidCompiledStatement:apiCompatibility	Lcom/j256/ormlite/android/compat/ApiCompatibility;
    //   54: aload_0
    //   55: getfield 62	com/j256/ormlite/android/AndroidCompiledStatement:db	Landroid/database/sqlite/SQLiteDatabase;
    //   58: aload_1
    //   59: aload_0
    //   60: invokespecial 189	com/j256/ormlite/android/AndroidCompiledStatement:getStringArray	()[Ljava/lang/String;
    //   63: aload_0
    //   64: getfield 157	com/j256/ormlite/android/AndroidCompiledStatement:cancellationHook	Lcom/j256/ormlite/android/compat/ApiCompatibility$CancellationHook;
    //   67: invokeinterface 193 5 0
    //   72: putfield 149	com/j256/ormlite/android/AndroidCompiledStatement:cursor	Landroid/database/Cursor;
    //   75: aload_1
    //   76: astore_2
    //   77: aload_0
    //   78: getfield 149	com/j256/ormlite/android/AndroidCompiledStatement:cursor	Landroid/database/Cursor;
    //   81: invokeinterface 197 1 0
    //   86: pop
    //   87: aload_1
    //   88: astore_2
    //   89: getstatic 41	com/j256/ormlite/android/AndroidCompiledStatement:logger	Lcom/j256/ormlite/logger/Logger;
    //   92: ldc -57
    //   94: aload_0
    //   95: aload_1
    //   96: invokevirtual 202	com/j256/ormlite/logger/Logger:trace	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   99: aload_0
    //   100: getfield 149	com/j256/ormlite/android/AndroidCompiledStatement:cursor	Landroid/database/Cursor;
    //   103: areturn
    //   104: aload_1
    //   105: astore_2
    //   106: new 109	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   113: aload_0
    //   114: getfield 60	com/j256/ormlite/android/AndroidCompiledStatement:sql	Ljava/lang/String;
    //   117: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: ldc -52
    //   122: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_0
    //   126: getfield 181	com/j256/ormlite/android/AndroidCompiledStatement:max	Ljava/lang/Integer;
    //   129: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: astore_1
    //   136: goto -111 -> 25
    //   139: astore_1
    //   140: new 109	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   147: ldc -47
    //   149: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload_2
    //   153: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: aload_1
    //   160: invokestatic 128	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   163: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	this	AndroidCompiledStatement
    //   8	128	1	str1	String
    //   139	21	1	localSQLException	android.database.SQLException
    //   10	143	2	str2	String
    // Exception table:
    //   from	to	target	type
    //   11	18	139	android/database/SQLException
    //   20	25	139	android/database/SQLException
    //   27	34	139	android/database/SQLException
    //   36	48	139	android/database/SQLException
    //   50	75	139	android/database/SQLException
    //   77	87	139	android/database/SQLException
    //   89	99	139	android/database/SQLException
    //   106	136	139	android/database/SQLException
  }
  
  public int runExecute()
    throws java.sql.SQLException
  {
    if (!type.isOkForExecute()) {
      throw new IllegalArgumentException("Cannot call execute on a " + type + " statement");
    }
    return execSql(db, "runExecute", sql, getArgArray());
  }
  
  public DatabaseResults runQuery(ObjectCache paramObjectCache)
    throws java.sql.SQLException
  {
    if (!type.isOkForQuery()) {
      throw new IllegalArgumentException("Cannot call query on a " + type + " statement");
    }
    return new AndroidDatabaseResults(getCursor(), paramObjectCache);
  }
  
  public int runUpdate()
    throws java.sql.SQLException
  {
    if (!type.isOkForUpdate()) {
      throw new IllegalArgumentException("Cannot call update on a " + type + " statement");
    }
    if (max == null) {}
    for (String str = sql;; str = sql + " " + max) {
      return execSql(db, "runUpdate", str, getArgArray());
    }
  }
  
  public void setMaxRows(int paramInt)
    throws java.sql.SQLException
  {
    isInPrep();
    max = Integer.valueOf(paramInt);
  }
  
  public void setObject(int paramInt, Object paramObject, SqlType paramSqlType)
    throws java.sql.SQLException
  {
    isInPrep();
    if (args == null) {
      args = new ArrayList();
    }
    if (paramObject == null)
    {
      args.add(paramInt, null);
      return;
    }
    switch (paramSqlType)
    {
    default: 
      throw new java.sql.SQLException("Unknown sql argument type: " + paramSqlType);
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
      args.add(paramInt, paramObject.toString());
      return;
    case ???: 
    case ???: 
      args.add(paramInt, paramObject);
      return;
    }
    throw new java.sql.SQLException("Invalid Android type: " + paramSqlType);
  }
  
  public void setQueryTimeout(long paramLong) {}
  
  public String toString()
  {
    return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.android.AndroidCompiledStatement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */