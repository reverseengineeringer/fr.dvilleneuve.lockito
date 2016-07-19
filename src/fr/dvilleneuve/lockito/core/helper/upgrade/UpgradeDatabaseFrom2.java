package fr.dvilleneuve.lockito.core.helper.upgrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.model.ormlite.PointConverter;
import fr.dvilleneuve.lockito.core.model.ormlite.PointGeometricListConverter;
import fr.dvilleneuve.lockito.core.model.ormlite.PointListConverter;
import fr.dvilleneuve.lockito.core.utils.collection.GeometricList;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UpgradeDatabaseFrom2
  extends UpgradeDatabase
{
  public UpgradeDatabaseFrom2(Context paramContext)
  {
    super(paramContext, 2);
  }
  
  private List<Leg> convertLegs2To3(List<Point> paramList, String paramString)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    paramString = (List)OBJECT_MAPPER.readValue(paramString, new TypeReference() {});
    int i = 0;
    int j = paramList.size();
    paramString = paramString.iterator();
    while (paramString.hasNext())
    {
      List localList = (List)paramString.next();
      Leg localLeg = new Leg();
      localLeg.setPoints(convertPoints2To3(localList));
      localLeg.setItinerary(null);
      if (i < j - 1)
      {
        localLeg.setStartWaypoint((Point)paramList.get(i));
        localLeg.setEndWaypoint((Point)paramList.get(i + 1));
      }
      localArrayList.add(localLeg);
      i += 1;
    }
    return localArrayList;
  }
  
  private GeometricList<Point> convertPoints2To3(List<Map<String, Object>> paramList)
    throws IOException
  {
    GeometricList localGeometricList = new GeometricList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Map localMap = (Map)paramList.next();
      try
      {
        localGeometricList.add(new Point(Double.parseDouble(localMap.get("latitude").toString()), Double.parseDouble(localMap.get("longitude").toString())));
      }
      catch (Exception localException)
      {
        Logger.warn("Can't parse double: %s", new Object[] { localException.getMessage() });
      }
    }
    return localGeometricList;
  }
  
  private List<Point> convertPoints2To3(String paramString)
    throws IOException
  {
    convertPoints2To3((List)OBJECT_MAPPER.readValue(paramString, new TypeReference() {}));
  }
  
  public void doUpgrade(SQLiteDatabase paramSQLiteDatabase)
    throws SQLException, IOException
  {
    Cursor localCursor = paramSQLiteDatabase.query("itinerary_old", new String[] { "id", "waypoints", "legs" }, null, null, null, null, null);
    while (localCursor.moveToNext())
    {
      Integer localInteger = Integer.valueOf(localCursor.getInt(0));
      Object localObject2 = localCursor.getString(1);
      Object localObject1 = localCursor.getString(2);
      localObject2 = convertPoints2To3((String)localObject2);
      localObject1 = convertLegs2To3((List)localObject2, (String)localObject1);
      localObject2 = (String)PointListConverter.getSingleton().javaToSqlArg(null, localObject2);
      Object localObject3 = new ContentValues();
      ((ContentValues)localObject3).put("waypoints", (String)localObject2);
      paramSQLiteDatabase.update("itinerary", (ContentValues)localObject3, "id = ?", new String[] { localInteger.toString() });
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject4 = (Leg)((Iterator)localObject1).next();
        localObject2 = (String)PointGeometricListConverter.getSingleton().javaToSqlArg(null, ((Leg)localObject4).getPoints());
        localObject3 = (String)PointConverter.getSingleton().javaToSqlArg(null, ((Leg)localObject4).getStartWaypoint());
        localObject4 = (String)PointConverter.getSingleton().javaToSqlArg(null, ((Leg)localObject4).getEndWaypoint());
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("points", (String)localObject2);
        localContentValues.put("startWaypoint", (String)localObject3);
        localContentValues.put("endWaypoint", (String)localObject4);
        localContentValues.put("itinerary_id", localInteger);
        localContentValues.put("accuracyBase", Integer.valueOf(0));
        localContentValues.put("accuracyDelta", Integer.valueOf(0));
        localContentValues.put("distance", Integer.valueOf(0));
        localContentValues.put("speed", Integer.valueOf(0));
        paramSQLiteDatabase.insert("leg", null, localContentValues);
      }
    }
    paramSQLiteDatabase.execSQL("DROP TABLE `itinerary_old`");
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.upgrade.UpgradeDatabaseFrom2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */