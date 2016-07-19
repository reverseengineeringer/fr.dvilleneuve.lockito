package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import fr.dvilleneuve.lockito.core.ItinerariesOrder;
import fr.dvilleneuve.lockito.core.RestClient;
import fr.dvilleneuve.lockito.core.dto.GItinerary;
import fr.dvilleneuve.lockito.core.dto.GItineraryException;
import fr.dvilleneuve.lockito.core.dto.converter.GItineraryConverter;
import fr.dvilleneuve.lockito.core.helper.DatabaseHelper;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.ItineraryMode;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.FileUtils;
import fr.dvilleneuve.lockito.core.utils.GeometryUtils;
import fr.dvilleneuve.lockito.core.utils.IOUtils;
import fr.dvilleneuve.lockito.core.utils.LocationsConverter;
import fr.dvilleneuve.lockito.core.utils.SimulationUtils;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.rest.RestService;

@EBean
public class ItineraryManager
{
  @RootContext
  Context context;
  @Bean
  GItineraryConverter gItineraryConverter;
  @OrmLiteDao(helper=DatabaseHelper.class, model=Itinerary.class)
  Dao<Itinerary, Long> itineraryDao;
  @OrmLiteDao(helper=DatabaseHelper.class, model=ItineraryInfo.class)
  Dao<ItineraryInfo, Long> itineraryInfoDao;
  @OrmLiteDao(helper=DatabaseHelper.class, model=Leg.class)
  Dao<Leg, Long> legDao;
  @RestService
  RestClient restClient;
  
  public boolean deleteItinery(ItineraryInfo paramItineraryInfo)
  {
    try
    {
      long l1 = paramItineraryInfo.getId();
      long l2 = paramItineraryInfo.getItinerary().getId();
      Logger.info("Deleting itinerary %d", new Object[] { Long.valueOf(l1) });
      if (itineraryInfoDao.deleteById(Long.valueOf(l1)) == 1)
      {
        itineraryDao.deleteById(Long.valueOf(l2));
        Object localObject = legDao.deleteBuilder();
        ((DeleteBuilder)localObject).where().eq("itinerary_id", Long.valueOf(l2));
        ((DeleteBuilder)localObject).delete();
        localObject = IOUtils.getSnapshotFile(context, paramItineraryInfo);
        try
        {
          FileUtils.deleteFile((File)localObject);
          return true;
        }
        catch (IOException localIOException)
        {
          Logger.warn("Couldn't delete itinerary snapshot", new Object[0]);
          return true;
        }
      }
      return false;
    }
    catch (SQLException localSQLException)
    {
      Logger.error("Can't delete itinerary %d", localSQLException, new Object[] { Long.valueOf(paramItineraryInfo.getId()) });
    }
  }
  
  public ItineraryInfo duplicateItinerary(ItineraryInfo paramItineraryInfo)
  {
    try
    {
      Object localObject = paramItineraryInfo.getItinerary();
      itineraryDao.refresh(localObject);
      ((Itinerary)localObject).postLoad();
      localObject = paramItineraryInfo.clone();
      itineraryDao.assignEmptyForeignCollection(((ItineraryInfo)localObject).getItinerary(), "legs");
      ((ItineraryInfo)localObject).setName(context.getString(2131165281, new Object[] { paramItineraryInfo.getName() }));
      ((ItineraryInfo)localObject).setPlayCounter(0L);
      save((ItineraryInfo)localObject);
      paramItineraryInfo = IOUtils.getSnapshotFile(context, paramItineraryInfo);
      File localFile = IOUtils.getSnapshotFile(context, (ItineraryInfo)localObject);
      try
      {
        FileUtils.copyFile(paramItineraryInfo, localFile);
        return (ItineraryInfo)localObject;
      }
      catch (IOException paramItineraryInfo)
      {
        Logger.warn("Couldn't copy itinerary snapshot", new Object[0]);
        return (ItineraryInfo)localObject;
      }
      return null;
    }
    catch (SQLException paramItineraryInfo)
    {
      Logger.error("Can't create a new itinerary", paramItineraryInfo, new Object[0]);
    }
  }
  
  public boolean incrementPlayCounter(ItineraryInfo paramItineraryInfo)
  {
    Long localLong = Long.valueOf(paramItineraryInfo.getId());
    paramItineraryInfo.incPlayCounter();
    if (localLong.longValue() == 0L) {}
    for (;;)
    {
      return true;
      try
      {
        paramItineraryInfo = itineraryInfoDao.updateBuilder();
        paramItineraryInfo.where().idEq(localLong);
        paramItineraryInfo.updateColumnExpression("playCounter", "playCounter + 1");
        int i = paramItineraryInfo.update();
        if (i != 1) {
          return false;
        }
      }
      catch (SQLException paramItineraryInfo)
      {
        Logger.error("Can't update itinerary's name for %d", paramItineraryInfo, new Object[] { localLong });
      }
    }
    return false;
  }
  
  public List<ItineraryInfo> loadAllItineraries(ItinerariesOrder paramItinerariesOrder)
  {
    Logger.info("Loading all itineraries order by %s...", new Object[] { paramItinerariesOrder });
    try
    {
      paramItinerariesOrder = itineraryInfoDao.queryBuilder().orderBy(paramItinerariesOrder.getColumnName(), paramItinerariesOrder.isAscending()).query();
      Logger.debug("Loaded %d itineraries", new Object[] { Integer.valueOf(paramItinerariesOrder.size()) });
      return paramItinerariesOrder;
    }
    catch (SQLException paramItinerariesOrder)
    {
      Logger.error("Can't find all itineraries", paramItinerariesOrder, new Object[0]);
    }
    return Collections.emptyList();
  }
  
  public ItineraryInfo loadItinerary(Long paramLong)
  {
    Logger.info("Loading itinerary %d", new Object[] { paramLong });
    try
    {
      ItineraryInfo localItineraryInfo = (ItineraryInfo)itineraryInfoDao.queryForId(paramLong);
      if (localItineraryInfo != null)
      {
        Itinerary localItinerary = localItineraryInfo.getItinerary();
        itineraryDao.refresh(localItinerary);
        localItinerary.postLoad();
      }
      return localItineraryInfo;
    }
    catch (SQLException localSQLException)
    {
      Logger.error("Can't find itinerary %d", localSQLException, new Object[] { paramLong });
    }
    return null;
  }
  
  public ItineraryInfo loadOrCreateItinerary(Long paramLong, String paramString)
  {
    Object localObject = null;
    if (paramLong != null) {
      paramLong = loadItinerary(paramLong);
    }
    for (;;)
    {
      return paramLong;
      paramLong = (Long)localObject;
      if (paramString != null)
      {
        paramLong = new ItineraryInfo(paramString);
        try
        {
          paramString = paramLong.getItinerary();
          itineraryDao.assignEmptyForeignCollection(paramString, "legs");
        }
        catch (SQLException paramLong)
        {
          Logger.error("Can't prepare legs list in the new itinerary", paramLong, new Object[0]);
        }
      }
    }
    return null;
  }
  
  public boolean renameItinerary(ItineraryInfo paramItineraryInfo, String paramString)
  {
    paramItineraryInfo.setName(paramString);
    return renameItinerary(Long.valueOf(paramItineraryInfo.getId()), paramString);
  }
  
  public boolean renameItinerary(Long paramLong, String paramString)
  {
    if (paramLong == null) {}
    for (;;)
    {
      return true;
      try
      {
        UpdateBuilder localUpdateBuilder = itineraryInfoDao.updateBuilder();
        localUpdateBuilder.where().idEq(paramLong);
        localUpdateBuilder.updateColumnValue("name", paramString);
        int i = localUpdateBuilder.update();
        if (i != 1) {
          return false;
        }
      }
      catch (SQLException paramString)
      {
        Logger.error("Can't update itinerary's name for %d", paramString, new Object[] { paramLong });
      }
    }
    return false;
  }
  
  public ItineraryInfo retrieveItinerary(ItineraryInfo paramItineraryInfo, List<Point> paramList, ItineraryMode paramItineraryMode)
    throws Exception
  {
    Logger.debug("Retrieving itinerary for %s with waypoints %s and mode %s", new Object[] { paramItineraryInfo, paramList, paramItineraryMode });
    paramItineraryInfo.setItineraryMode(paramItineraryMode);
    switch (paramItineraryMode)
    {
    default: 
      return retrieveLinearItinerary(paramItineraryInfo, paramList, paramItineraryMode);
    }
    return retrieveItineraryFromGoogle(paramItineraryInfo, paramList, paramItineraryMode);
  }
  
  public ItineraryInfo retrieveItineraryFromGoogle(ItineraryInfo paramItineraryInfo, List<Point> paramList, ItineraryMode paramItineraryMode)
    throws Exception
  {
    Object localObject1 = paramItineraryInfo.getItinerary();
    int i = paramList.size();
    if (i < 2)
    {
      paramItineraryInfo.setDistance(-1L);
      paramItineraryInfo.setDuration(-1L);
      ((Itinerary)localObject1).setWaypoints(paramList);
      paramItineraryMode = new Leg();
      if (i == 1) {
        paramItineraryMode.add((Point)paramList.get(0));
      }
      paramList = ((Itinerary)localObject1).getLegs();
      paramList.clear();
      paramList.add(paramItineraryMode);
      return paramItineraryInfo;
    }
    Object localObject2 = (Point)paramList.get(0);
    localObject1 = (Point)paramList.get(i - 1);
    Logger.info("Retrieve itineraryInfo from %s to %s", new Object[] { localObject2, localObject1 });
    localObject2 = LocationsConverter.toString((Point)localObject2);
    String str = LocationsConverter.toString((Point)localObject1);
    if (paramList.size() > 2) {}
    for (localObject1 = LocationsConverter.toString(paramList.subList(1, i - 1));; localObject1 = "")
    {
      paramItineraryMode = restClient.retrieveItinerary((String)localObject2, str, (String)localObject1, paramItineraryMode.getGoogleModeValue());
      if ("OK".equals(status)) {
        break;
      }
      throw new GItineraryException(status);
    }
    return gItineraryConverter.mergeToEntity(paramItineraryInfo, paramList, paramItineraryMode);
  }
  
  public ItineraryInfo retrieveLinearItinerary(ItineraryInfo paramItineraryInfo, List<Point> paramList, ItineraryMode paramItineraryMode)
    throws Exception
  {
    paramItineraryMode = paramItineraryInfo.getItinerary();
    paramItineraryMode.setWaypoints(paramList);
    paramItineraryMode = paramItineraryMode.getLegs();
    paramItineraryMode.clear();
    long l2 = 0L;
    long l1 = 0L;
    int j = paramList.size();
    Object localObject;
    long l4;
    long l3;
    if (j < 2)
    {
      localObject = new Leg();
      if (j == 1) {
        ((Leg)localObject).add((Point)paramList.get(0));
      }
      paramItineraryMode.add(localObject);
      l4 = -1L;
      l3 = -1L;
      paramItineraryInfo.setDistance(l4);
      paramItineraryInfo.setDuration(l3);
      return paramItineraryInfo;
    }
    int i = 0;
    for (;;)
    {
      l4 = l2;
      l3 = l1;
      if (i >= j - 1) {
        break;
      }
      localObject = (Point)paramList.get(i);
      Point localPoint = (Point)paramList.get(i + 1);
      l3 = GeometryUtils.distance((Point)localObject, localPoint);
      Leg localLeg = new Leg();
      localLeg.setDistance(l3);
      localLeg.add((Point)localObject);
      localLeg.add(localPoint);
      paramItineraryMode.add(localLeg);
      l2 += l3;
      l1 = ((float)l1 + (float)l2 * localLeg.getSpeed());
      i += 1;
    }
  }
  
  public Dao.CreateOrUpdateStatus save(ItineraryInfo paramItineraryInfo)
  {
    try
    {
      Logger.info("Saving itineraryInfo %s with id %d", new Object[] { paramItineraryInfo.getName(), Long.valueOf(paramItineraryInfo.getId()) });
      Itinerary localItinerary = paramItineraryInfo.getItinerary();
      localItinerary.preSave();
      itineraryDao.createOrUpdate(localItinerary);
      Iterator localIterator = localItinerary.getLegs().iterator();
      while (localIterator.hasNext())
      {
        Leg localLeg = (Leg)localIterator.next();
        localLeg.setItinerary(localItinerary);
        legDao.createOrUpdate(localLeg);
      }
      paramItineraryInfo.setDuration(SimulationUtils.getDuration(paramItineraryInfo));
    }
    catch (SQLException localSQLException)
    {
      Logger.error("Can't save itineraryInfo %d", localSQLException, new Object[] { Long.valueOf(paramItineraryInfo.getId()) });
      return new Dao.CreateOrUpdateStatus(false, false, 0);
    }
    paramItineraryInfo.setUpdatedDate(new Date());
    paramItineraryInfo.clearNewChangesState();
    Dao.CreateOrUpdateStatus localCreateOrUpdateStatus = itineraryInfoDao.createOrUpdate(paramItineraryInfo);
    return localCreateOrUpdateStatus;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.ItineraryManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */