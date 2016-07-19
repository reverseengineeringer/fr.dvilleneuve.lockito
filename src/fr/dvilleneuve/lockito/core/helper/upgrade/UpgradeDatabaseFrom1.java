package fr.dvilleneuve.lockito.core.helper.upgrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.model.ormlite.ItineraryConverter;
import fr.dvilleneuve.lockito.core.model.ormlite.PointListConverter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpgradeDatabaseFrom1
  extends UpgradeDatabase
{
  public UpgradeDatabaseFrom1(Context paramContext)
  {
    super(paramContext, 1);
  }
  
  private List<Point> convertPoints1To2(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split("\\|");
    int j = paramString.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        String[] arrayOfString = paramString[i].split(",");
        if (arrayOfString.length >= 2) {}
        try
        {
          localArrayList.add(new Point(Double.parseDouble(arrayOfString[0]), Double.parseDouble(arrayOfString[1])));
          i += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Logger.warn("Can't parse double: %s", new Object[] { localException.getMessage() });
          }
        }
      }
    }
    return localArrayList;
  }
  
  public void doUpgrade(SQLiteDatabase paramSQLiteDatabase)
    throws SQLException
  {
    Cursor localCursor = paramSQLiteDatabase.query("itinerary", new String[] { "id", "waypoints", "legs" }, null, null, null, null, null);
    while (localCursor.moveToNext())
    {
      int i = localCursor.getInt(0);
      Object localObject1 = localCursor.getString(1);
      Object localObject2 = localCursor.getString(2);
      localObject1 = convertPoints1To2((String)localObject1);
      Object localObject3 = convertPoints1To2((String)localObject2);
      localObject2 = new ArrayList();
      Leg localLeg = new Leg();
      localLeg.addAll((Collection)localObject3);
      ((List)localObject2).add(localLeg);
      localObject1 = (String)PointListConverter.getSingleton().javaToSqlArg(null, localObject1);
      localObject2 = (String)ItineraryConverter.getSingleton().javaToSqlArg(null, localObject2);
      localObject3 = new ContentValues();
      ((ContentValues)localObject3).put("waypoints", (String)localObject1);
      ((ContentValues)localObject3).put("legs", (String)localObject2);
      paramSQLiteDatabase.update("itinerary", (ContentValues)localObject3, "id = ?", new String[] { Integer.valueOf(i).toString() });
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.upgrade.UpgradeDatabaseFrom1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */