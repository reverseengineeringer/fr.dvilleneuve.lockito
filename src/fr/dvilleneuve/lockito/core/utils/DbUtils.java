package fr.dvilleneuve.lockito.core.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import fr.dvilleneuve.lockito.core.logger.Logger;
import java.io.IOException;
import java.util.regex.Pattern;

public class DbUtils
{
  private static final Pattern SQL_EMPTY_LINE_PATTERN = Pattern.compile("^\\s*(--)?.*$");
  
  public static int executeSqlScript(Context paramContext, SQLiteDatabase paramSQLiteDatabase, String paramString)
    throws IOException
  {
    return executeSqlScript(paramContext, paramSQLiteDatabase, paramString, true);
  }
  
  public static int executeSqlScript(Context paramContext, SQLiteDatabase paramSQLiteDatabase, String paramString, boolean paramBoolean)
    throws IOException
  {
    try
    {
      paramContext = FileUtils.readAsset(paramContext, paramString);
      paramContext = new String(paramContext, "UTF-8").split(";(\\s)*[\n\r]");
      if (paramBoolean) {
        return executeSqlStatementsInTx(paramSQLiteDatabase, paramContext);
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = new byte[0];
      }
    }
    return executeSqlStatements(paramSQLiteDatabase, paramContext);
  }
  
  public static int executeSqlStatements(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString)
  {
    int j = 0;
    int m = paramArrayOfString.length;
    int i = 0;
    while (i < m)
    {
      String str = paramArrayOfString[i].trim();
      int k = j;
      if (!str.isEmpty()) {}
      try
      {
        paramSQLiteDatabase.execSQL(str);
        k = j + 1;
        i += 1;
        j = k;
      }
      catch (SQLiteException paramSQLiteDatabase)
      {
        Logger.error("Failed to exec SQL script at line %d: %s", new Object[] { Integer.valueOf(j), str });
        throw paramSQLiteDatabase;
      }
    }
    return j;
  }
  
  public static int executeSqlStatementsInTx(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString)
  {
    paramSQLiteDatabase.beginTransaction();
    try
    {
      int i = executeSqlStatements(paramSQLiteDatabase, paramArrayOfString);
      paramSQLiteDatabase.setTransactionSuccessful();
      return i;
    }
    finally
    {
      paramSQLiteDatabase.endTransaction();
    }
  }
  
  public static void logTableDumpData(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    paramSQLiteDatabase = paramSQLiteDatabase.query(paramString, null, null, null, null, null, null);
    try
    {
      Logger.info("dump %s = %s", new Object[] { paramString, DatabaseUtils.dumpCursorToString(paramSQLiteDatabase) });
      return;
    }
    catch (Exception paramString)
    {
      Logger.error("Can't dump data", paramString, new Object[0]);
      return;
    }
    finally
    {
      paramSQLiteDatabase.close();
    }
  }
  
  public static void logTablesDumpSchema(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE type = ?", new String[] { "table" });
    try
    {
      Logger.info(DatabaseUtils.dumpCursorToString(paramSQLiteDatabase), new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      Logger.error("Can't dump schema", localException, new Object[0]);
      return;
    }
    finally
    {
      paramSQLiteDatabase.close();
    }
  }
  
  public static void vacuum(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("VACUUM");
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.DbUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */