package fr.dvilleneuve.lockito.core.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import fr.dvilleneuve.lockito.core.helper.upgrade.UpgradeDatabaseFrom1;
import fr.dvilleneuve.lockito.core.helper.upgrade.UpgradeDatabaseFrom2;
import fr.dvilleneuve.lockito.core.helper.upgrade.UpgradeDatabaseFrom3;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.DbUtils;
import fr.dvilleneuve.lockito.core.utils.FileUtils;
import java.io.File;
import java.io.IOException;

public class DatabaseHelper
  extends OrmLiteSqliteOpenHelper
{
  private static final String DATABASE_NAME = "Lockito.sqlite";
  private static final int DATABASE_VERSION = 6;
  private final Context context;
  
  public DatabaseHelper(Context paramContext)
  {
    super(paramContext, "Lockito.sqlite", null, 6);
    context = paramContext;
    Logger.info("Instanciating DatabaseHelper", new Object[0]);
  }
  
  private void copyDatabase()
  {
    File localFile1 = new File(context.getDatabasePath("Lockito.sqlite").getPath());
    File localFile2 = new File(Environment.getExternalStorageDirectory(), "Lockito.sqlite");
    try
    {
      FileUtils.copyFile(localFile1, localFile2);
      Logger.info("Database copied from %s to %s", new Object[] { localFile1, localFile2 });
      return;
    }
    catch (IOException localIOException)
    {
      Logger.error("Can't copy database", localIOException, new Object[0]);
    }
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource)
  {
    Logger.info("Create database structure", new Object[0]);
    try
    {
      TableUtils.createTable(paramConnectionSource, ItineraryInfo.class);
      TableUtils.createTable(paramConnectionSource, Itinerary.class);
      TableUtils.createTable(paramConnectionSource, Leg.class);
      return;
    }
    catch (android.database.SQLException paramSQLiteDatabase)
    {
      Logger.error("Can't create database", paramSQLiteDatabase, new Object[0]);
      throw new RuntimeException(paramSQLiteDatabase);
    }
    catch (java.sql.SQLException paramSQLiteDatabase)
    {
      Logger.error("Can't create database", paramSQLiteDatabase, new Object[0]);
    }
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource, int paramInt1, int paramInt2)
  {
    Logger.info("Update database from %d to %d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
    for (;;)
    {
      int i;
      try
      {
        paramSQLiteDatabase.beginTransaction();
        i = paramInt1;
        if (i >= paramInt2) {
          break label254;
        }
        Logger.info("Step update database from %d to %d", new Object[] { Integer.valueOf(i), Integer.valueOf(i + 1) });
        DbUtils.executeSqlScript(context, paramSQLiteDatabase, "updateSql-" + i + "-" + (i + 1) + ".sql", false);
        switch (i)
        {
        case 1: 
          new UpgradeDatabaseFrom1(context).doUpgrade(paramSQLiteDatabase);
        }
      }
      catch (Exception paramConnectionSource)
      {
        Logger.error("Can't update database from %d to %d", paramConnectionSource, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            Toast.makeText(context, "Something went wrong in database upgrade process. A report has been sent.", 1).show();
          }
        });
        return;
        new UpgradeDatabaseFrom2(context).doUpgrade(paramSQLiteDatabase);
      }
      finally
      {
        paramSQLiteDatabase.endTransaction();
      }
      new UpgradeDatabaseFrom3(context).doUpgrade(paramSQLiteDatabase);
      break label287;
      label254:
      paramSQLiteDatabase.setTransactionSuccessful();
      Logger.info("Upgrade from %d to %d success", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
      paramSQLiteDatabase.endTransaction();
      return;
      label287:
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.DatabaseHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */