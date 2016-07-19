package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfigLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public abstract class OrmLiteSqliteOpenHelper
  extends SQLiteOpenHelper
{
  protected static Logger logger = LoggerFactory.getLogger(OrmLiteSqliteOpenHelper.class);
  protected boolean cancelQueriesEnabled;
  protected AndroidConnectionSource connectionSource = new AndroidConnectionSource(this);
  private volatile boolean isOpen = true;
  
  public OrmLiteSqliteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
    logger.trace("{}: constructed connectionSource {}", this, connectionSource);
  }
  
  public OrmLiteSqliteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt1, int paramInt2)
  {
    this(paramContext, paramString, paramCursorFactory, paramInt1, openFileId(paramContext, paramInt2));
  }
  
  public OrmLiteSqliteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, File paramFile)
  {
    this(paramContext, paramString, paramCursorFactory, paramInt, openFile(paramFile));
  }
  
  public OrmLiteSqliteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, InputStream paramInputStream)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
    if (paramInputStream == null) {
      return;
    }
    try
    {
      DaoManager.addCachedDatabaseConfigs(DatabaseTableConfigLoader.loadDatabaseConfigFromReader(new BufferedReader(new InputStreamReader(paramInputStream), 4096)));
      try
      {
        paramInputStream.close();
        return;
      }
      catch (IOException paramContext)
      {
        return;
      }
      try
      {
        paramInputStream.close();
        throw paramContext;
      }
      catch (IOException paramString)
      {
        for (;;) {}
      }
    }
    catch (SQLException paramContext)
    {
      paramContext = paramContext;
      throw new IllegalStateException("Could not load object config file", paramContext);
    }
    finally {}
  }
  
  private static InputStream openFile(File paramFile)
  {
    if (paramFile == null) {
      return null;
    }
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      return localFileInputStream;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      throw new IllegalArgumentException("Could not open config file " + paramFile, localFileNotFoundException);
    }
  }
  
  private static InputStream openFileId(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources().openRawResource(paramInt);
    if (paramContext == null) {
      throw new IllegalStateException("Could not find object config file with id " + paramInt);
    }
    return paramContext;
  }
  
  public void close()
  {
    super.close();
    connectionSource.close();
    isOpen = false;
  }
  
  public ConnectionSource getConnectionSource()
  {
    if (!isOpen) {
      logger.warn(new IllegalStateException(), "Getting connectionSource was called after closed");
    }
    return connectionSource;
  }
  
  public <D extends Dao<T, ?>, T> D getDao(Class<T> paramClass)
    throws SQLException
  {
    return DaoManager.createDao(getConnectionSource(), paramClass);
  }
  
  public <D extends RuntimeExceptionDao<T, ?>, T> D getRuntimeExceptionDao(Class<T> paramClass)
  {
    try
    {
      RuntimeExceptionDao localRuntimeExceptionDao = new RuntimeExceptionDao(getDao(paramClass));
      return localRuntimeExceptionDao;
    }
    catch (SQLException localSQLException)
    {
      throw new RuntimeException("Could not create RuntimeExcepitionDao for class " + paramClass, localSQLException);
    }
  }
  
  public boolean isOpen()
  {
    return isOpen;
  }
  
  /* Error */
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 156	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:getConnectionSource	()Lcom/j256/ormlite/support/ConnectionSource;
    //   4: astore 5
    //   6: aload 5
    //   8: invokeinterface 187 1 0
    //   13: astore 4
    //   15: iconst_0
    //   16: istore_2
    //   17: aload 4
    //   19: astore_3
    //   20: aload 4
    //   22: ifnonnull +28 -> 50
    //   25: new 189	com/j256/ormlite/android/AndroidDatabaseConnection
    //   28: dup
    //   29: aload_1
    //   30: iconst_1
    //   31: aload_0
    //   32: getfield 191	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:cancelQueriesEnabled	Z
    //   35: invokespecial 194	com/j256/ormlite/android/AndroidDatabaseConnection:<init>	(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    //   38: astore_3
    //   39: aload 5
    //   41: aload_3
    //   42: invokeinterface 198 2 0
    //   47: pop
    //   48: iconst_1
    //   49: istore_2
    //   50: aload_0
    //   51: aload_1
    //   52: aload 5
    //   54: invokevirtual 201	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:onCreate	(Landroid/database/sqlite/SQLiteDatabase;Lcom/j256/ormlite/support/ConnectionSource;)V
    //   57: iload_2
    //   58: ifeq +11 -> 69
    //   61: aload 5
    //   63: aload_3
    //   64: invokeinterface 205 2 0
    //   69: return
    //   70: astore_1
    //   71: new 89	java/lang/IllegalStateException
    //   74: dup
    //   75: ldc -49
    //   77: aload_1
    //   78: invokespecial 94	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   81: athrow
    //   82: astore_1
    //   83: iload_2
    //   84: ifeq +11 -> 95
    //   87: aload 5
    //   89: aload_3
    //   90: invokeinterface 205 2 0
    //   95: aload_1
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	OrmLiteSqliteOpenHelper
    //   0	97	1	paramSQLiteDatabase	SQLiteDatabase
    //   16	68	2	i	int
    //   19	71	3	localObject	Object
    //   13	8	4	localDatabaseConnection	com.j256.ormlite.support.DatabaseConnection
    //   4	84	5	localConnectionSource	ConnectionSource
    // Exception table:
    //   from	to	target	type
    //   39	48	70	java/sql/SQLException
    //   50	57	82	finally
  }
  
  public abstract void onCreate(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource);
  
  /* Error */
  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 156	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:getConnectionSource	()Lcom/j256/ormlite/support/ConnectionSource;
    //   4: astore 7
    //   6: aload 7
    //   8: invokeinterface 187 1 0
    //   13: astore 6
    //   15: iconst_0
    //   16: istore 4
    //   18: aload 6
    //   20: astore 5
    //   22: aload 6
    //   24: ifnonnull +31 -> 55
    //   27: new 189	com/j256/ormlite/android/AndroidDatabaseConnection
    //   30: dup
    //   31: aload_1
    //   32: iconst_1
    //   33: aload_0
    //   34: getfield 191	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:cancelQueriesEnabled	Z
    //   37: invokespecial 194	com/j256/ormlite/android/AndroidDatabaseConnection:<init>	(Landroid/database/sqlite/SQLiteDatabase;ZZ)V
    //   40: astore 5
    //   42: aload 7
    //   44: aload 5
    //   46: invokeinterface 198 2 0
    //   51: pop
    //   52: iconst_1
    //   53: istore 4
    //   55: aload_0
    //   56: aload_1
    //   57: aload 7
    //   59: iload_2
    //   60: iload_3
    //   61: invokevirtual 212	com/j256/ormlite/android/apptools/OrmLiteSqliteOpenHelper:onUpgrade	(Landroid/database/sqlite/SQLiteDatabase;Lcom/j256/ormlite/support/ConnectionSource;II)V
    //   64: iload 4
    //   66: ifeq +12 -> 78
    //   69: aload 7
    //   71: aload 5
    //   73: invokeinterface 205 2 0
    //   78: return
    //   79: astore_1
    //   80: new 89	java/lang/IllegalStateException
    //   83: dup
    //   84: ldc -49
    //   86: aload_1
    //   87: invokespecial 94	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   90: athrow
    //   91: astore_1
    //   92: iload 4
    //   94: ifeq +12 -> 106
    //   97: aload 7
    //   99: aload 5
    //   101: invokeinterface 205 2 0
    //   106: aload_1
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	OrmLiteSqliteOpenHelper
    //   0	108	1	paramSQLiteDatabase	SQLiteDatabase
    //   0	108	2	paramInt1	int
    //   0	108	3	paramInt2	int
    //   16	77	4	i	int
    //   20	80	5	localObject	Object
    //   13	10	6	localDatabaseConnection	com.j256.ormlite.support.DatabaseConnection
    //   4	94	7	localConnectionSource	ConnectionSource
    // Exception table:
    //   from	to	target	type
    //   42	52	79	java/sql/SQLException
    //   55	64	91	finally
  }
  
  public abstract void onUpgrade(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource, int paramInt1, int paramInt2);
  
  public String toString()
  {
    return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */