package fr.dvilleneuve.lockito.core.helper.upgrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.dvilleneuve.lockito.core.UnitSystem;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.ormlite.PointConverter;
import fr.dvilleneuve.lockito.core.model.ormlite.PointListConverter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class UpgradeDatabaseFrom3
  extends UpgradeDatabase
{
  private final float speedMultiplier;
  
  public UpgradeDatabaseFrom3(Context paramContext)
  {
    super(paramContext, 3);
    speedMultiplier = PreferenceManager_.getInstance_(paramContext).getUnitSystem().getSpeedMultiplier();
  }
  
  private String updatePointSpeed(String paramString)
    throws IOException
  {
    if (paramString == null) {}
    do
    {
      return null;
      paramString = POINT_CONVERTER.parsePosition(paramString);
    } while (paramString == null);
    paramString.setSpeed(paramString.getSpeed() / speedMultiplier);
    return POINT_CONVERTER.toString(paramString);
  }
  
  private String updatePointsSpeed(String paramString)
    throws IOException
  {
    if (paramString == null) {}
    do
    {
      return null;
      paramString = POINT_LIST_CONVERTER.parsePositions(paramString);
    } while ((paramString == null) || (paramString.isEmpty()));
    Iterator localIterator = paramString.iterator();
    while (localIterator.hasNext())
    {
      Point localPoint = (Point)localIterator.next();
      localPoint.setSpeed(localPoint.getSpeed() / speedMultiplier);
    }
    return POINT_LIST_CONVERTER.toString(paramString);
  }
  
  public void doUpgrade(SQLiteDatabase paramSQLiteDatabase)
    throws SQLException, IOException
  {
    paramSQLiteDatabase.execSQL("UPDATE `itineraryinfo` SET speed = speed / " + speedMultiplier);
    Cursor localCursor = paramSQLiteDatabase.query("itinerary", new String[] { "id", "waypoints" }, null, null, null, null, null);
    int i;
    String str1;
    Object localObject;
    while (localCursor.moveToNext())
    {
      i = localCursor.getInt(0);
      str1 = localCursor.getString(1);
      localObject = new ContentValues();
      ((ContentValues)localObject).put("waypoints", updatePointsSpeed(str1));
      paramSQLiteDatabase.update("itinerary", (ContentValues)localObject, "id = ?", new String[] { Integer.valueOf(i).toString() });
    }
    localCursor = paramSQLiteDatabase.query("leg", new String[] { "id", "startWaypoint", "endWaypoint", "points" }, null, null, null, null, null);
    while (localCursor.moveToNext())
    {
      i = localCursor.getInt(0);
      str1 = localCursor.getString(1);
      localObject = localCursor.getString(2);
      String str2 = localCursor.getString(3);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("startWaypoint", updatePointSpeed(str1));
      localContentValues.put("endWaypoint", updatePointSpeed((String)localObject));
      localContentValues.put("points", updatePointsSpeed(str2));
      paramSQLiteDatabase.update("leg", localContentValues, "id = ?", new String[] { Integer.valueOf(i).toString() });
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.upgrade.UpgradeDatabaseFrom3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */