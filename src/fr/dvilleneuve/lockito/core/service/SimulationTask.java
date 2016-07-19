package fr.dvilleneuve.lockito.core.service;

import android.annotation.TargetApi;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.CollectionUtils;
import fr.dvilleneuve.lockito.core.utils.GeometryUtils;
import fr.dvilleneuve.lockito.core.utils.SimulationUtils;
import fr.dvilleneuve.lockito.core.utils.collection.FlattenItem;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class SimulationTask
  extends TimerTask
{
  private static final Random RANDOM = new Random();
  private FlattenItem<Point> currentLocation;
  private boolean finished = false;
  private int index = 0;
  private boolean isPaused = false;
  private final ItineraryInfo itineraryInfo;
  private final FlattenItem<Point> lastPoint;
  private long lastRunningDate;
  @Nullable
  private OnLocationMockListener listener;
  private final List<FlattenItem<Point>> locations;
  private final String provider;
  private boolean started = false;
  
  public SimulationTask(@NonNull String paramString, @NonNull ItineraryInfo paramItineraryInfo, @NonNull OnLocationMockListener paramOnLocationMockListener)
  {
    itineraryInfo = paramItineraryInfo;
    provider = paramString;
    listener = paramOnLocationMockListener;
    locations = paramItineraryInfo.getItinerary().getAllPoints();
    currentLocation = ((FlattenItem)CollectionUtils.elementAt(locations, 0));
    lastRunningDate = System.currentTimeMillis();
    lastPoint = ((FlattenItem)CollectionUtils.lastElement(locations));
    Logger.info("Prepare simulation with total distance = %d", new Object[] { Long.valueOf(paramItineraryInfo.getDistance()) });
  }
  
  private Location buildLocation(@NonNull Point paramPoint, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Location localLocation = new Location(provider);
    localLocation.setTime(System.currentTimeMillis());
    localLocation.setLatitude(paramPoint.getLatitude());
    localLocation.setLongitude(paramPoint.getLongitude());
    localLocation.setAltitude(paramFloat1);
    localLocation.setSpeed(paramFloat2);
    localLocation.setAccuracy(nextAccuracy(paramFloat3, paramFloat4));
    enhanceLocation(localLocation);
    return localLocation;
  }
  
  @TargetApi(17)
  private void enhanceLocation(@NonNull Location paramLocation)
  {
    if (shouldSetElapsedRealtimeNanos()) {
      paramLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
    }
  }
  
  private float getAccuracyBase(@NonNull FlattenItem<Point> paramFlattenItem)
  {
    Leg localLeg = itineraryInfo.getItinerary().getLeg(paramFlattenItem.getSubListIndex());
    return SimulationUtils.getAccuracyBase(itineraryInfo, localLeg, (Point)paramFlattenItem.getItem());
  }
  
  private float getAccuracyDelta(@NonNull FlattenItem<Point> paramFlattenItem)
  {
    Leg localLeg = itineraryInfo.getItinerary().getLeg(paramFlattenItem.getSubListIndex());
    return SimulationUtils.getAccuracyDelta(itineraryInfo, localLeg, (Point)paramFlattenItem.getItem());
  }
  
  private float getAltitude(@NonNull FlattenItem<Point> paramFlattenItem)
  {
    Leg localLeg = itineraryInfo.getItinerary().getLeg(paramFlattenItem.getSubListIndex());
    return SimulationUtils.getAltitude(itineraryInfo, localLeg, (Point)paramFlattenItem.getItem());
  }
  
  private float getSpeed(@NonNull FlattenItem<Point> paramFlattenItem)
  {
    Leg localLeg = itineraryInfo.getItinerary().getLeg(paramFlattenItem.getSubListIndex());
    return SimulationUtils.getSpeed(itineraryInfo, localLeg, (Point)paramFlattenItem.getItem());
  }
  
  private float nextAccuracy(float paramFloat1, float paramFloat2)
  {
    return Math.abs(paramFloat1 + (RANDOM.nextFloat() * paramFloat2 * 2.0F - paramFloat2));
  }
  
  protected float bearing(@NonNull Point paramPoint1, @NonNull Point paramPoint2)
  {
    return GeometryUtils.bearing(paramPoint1, paramPoint2);
  }
  
  protected float distance(@NonNull Point paramPoint1, @NonNull Point paramPoint2)
  {
    return GeometryUtils.distance(paramPoint1, paramPoint2);
  }
  
  protected float getSecondsBetweenTicks()
  {
    return (float)(System.currentTimeMillis() - lastRunningDate) / 1000.0F;
  }
  
  public void mockLocation(@NonNull FlattenItem<Point> paramFlattenItem, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    Object localObject = buildLocation((Point)paramFlattenItem.getItem(), paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    if (paramFlattenItem.equals(currentLocation)) {}
    for (localObject = new MockedLocation((Location)localObject, 0L, paramFloat6);; localObject = new MockedLocation((Location)localObject, Math.round(paramFloat5), paramFloat6))
    {
      if (listener != null) {
        listener.onLocationMocked((MockedLocation)localObject);
      }
      if ((locations.size() > 1) && (paramFlattenItem.equals(lastPoint)) && (!finished))
      {
        finished = true;
        if (listener != null) {
          listener.onLocationFinished((MockedLocation)localObject);
        }
      }
      currentLocation = paramFlattenItem;
      return;
      ((Location)localObject).setBearing(bearing((Point)currentLocation.getItem(), (Point)paramFlattenItem.getItem()));
    }
  }
  
  @NonNull
  protected FlattenItem<Point> nextLocation(@NonNull FlattenItem<Point> paramFlattenItem, float paramFloat)
  {
    if (index >= locations.size() - 2) {
      return (FlattenItem)locations.get(locations.size() - 1);
    }
    Object localObject = (FlattenItem)locations.get(index);
    FlattenItem localFlattenItem = (FlattenItem)locations.get(index + 1);
    Point localPoint = (Point)localFlattenItem.getItem();
    localObject = (Point)((FlattenItem)localObject).getItem();
    float f2 = distance((Point)localObject, localPoint);
    float f3 = distance((Point)localObject, (Point)paramFlattenItem.getItem());
    float f4 = f3 + paramFloat;
    float f1 = f4 / f2;
    if (f2 > f4)
    {
      paramFloat = (float)((localPoint.getLatitude() - ((Point)localObject).getLatitude()) * f1);
      f1 = (float)((localPoint.getLongitude() - ((Point)localObject).getLongitude()) * f1);
      return new FlattenItem(new Point(((Point)localObject).getLatitude() + paramFloat, ((Point)localObject).getLongitude() + f1), paramFlattenItem.getSubListIndex());
    }
    if (index < locations.size() - 2)
    {
      index += 1;
      return nextLocation(localFlattenItem, paramFloat - (f2 - f3));
    }
    return localFlattenItem;
  }
  
  @TargetApi(17)
  public void run()
  {
    float f1;
    float f2;
    float f3;
    float f4;
    float f5;
    if (locations.size() > 0)
    {
      f1 = getSecondsBetweenTicks();
      f2 = getAltitude(currentLocation);
      f3 = getSpeed(currentLocation);
      f4 = getAccuracyBase(currentLocation);
      f5 = getAccuracyDelta(currentLocation);
      if ((!started) || (isPaused)) {
        break label169;
      }
      float f6 = f3 * f1;
      FlattenItem localFlattenItem = nextLocation(currentLocation, f6);
      Logger.debug("secondsBetween: %f, speed: %f, distance: %f, realDistance: %f", new Object[] { Float.valueOf(f1), Float.valueOf(f3), Float.valueOf(f6), Float.valueOf(distance((Point)currentLocation.getItem(), (Point)localFlattenItem.getItem())) });
      mockLocation(localFlattenItem, f2, f3, f4, f5, f6, f1);
    }
    for (;;)
    {
      lastRunningDate = System.currentTimeMillis();
      return;
      label169:
      started = true;
      mockLocation(currentLocation, f2, f3, f4, f5, 0.0F, f1);
    }
  }
  
  public void setListener(@Nullable OnLocationMockListener paramOnLocationMockListener)
  {
    listener = paramOnLocationMockListener;
  }
  
  public void setPaused(boolean paramBoolean)
  {
    isPaused = paramBoolean;
  }
  
  protected boolean shouldSetElapsedRealtimeNanos()
  {
    return Build.VERSION.SDK_INT >= 17;
  }
  
  public static abstract interface OnLocationMockListener
  {
    public abstract void onLocationFinished(MockedLocation paramMockedLocation);
    
    public abstract void onLocationMocked(MockedLocation paramMockedLocation);
    
    public abstract void onLocationStarted(MockedLocation paramMockedLocation);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */