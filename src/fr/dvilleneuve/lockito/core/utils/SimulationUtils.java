package fr.dvilleneuve.lockito.core.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import java.util.Iterator;
import java.util.List;

public class SimulationUtils
{
  public static float getAccuracyBase(@NonNull ItineraryInfo paramItineraryInfo, @Nullable Leg paramLeg, @Nullable Point paramPoint)
  {
    if ((paramPoint != null) && (paramPoint.getAccuracyBase() > 0.0F)) {
      return paramPoint.getAccuracyBase();
    }
    if ((paramLeg != null) && (paramLeg.getAccuracyBase() > 0.0F)) {
      return paramLeg.getAccuracyBase();
    }
    return paramItineraryInfo.getAccuracyBase();
  }
  
  public static float getAccuracyDelta(@NonNull ItineraryInfo paramItineraryInfo, @Nullable Leg paramLeg, @Nullable Point paramPoint)
  {
    if ((paramPoint != null) && (paramPoint.getAccuracyDelta() > 0.0F)) {
      return paramPoint.getAccuracyDelta();
    }
    if ((paramLeg != null) && (paramLeg.getAccuracyDelta() > 0.0F)) {
      return paramLeg.getAccuracyDelta();
    }
    return paramItineraryInfo.getAccuracyDelta();
  }
  
  public static float getAltitude(@NonNull ItineraryInfo paramItineraryInfo, @Nullable Leg paramLeg, @Nullable Point paramPoint)
  {
    if ((paramPoint != null) && (paramPoint.getAltitude() > 0.0F)) {
      return paramPoint.getAltitude();
    }
    if ((paramLeg != null) && (paramLeg.getAltitude() > 0.0F)) {
      return paramLeg.getAltitude();
    }
    return paramItineraryInfo.getAltitude();
  }
  
  public static long getDuration(@NonNull ItineraryInfo paramItineraryInfo)
  {
    Object localObject = paramItineraryInfo.getItinerary().getLegs();
    long l1 = 0L;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Leg localLeg = (Leg)((Iterator)localObject).next();
      long l2 = localLeg.getDistance();
      double d = getSpeed(paramItineraryInfo, localLeg, null);
      if ((l2 > 0L) && (d > 0.0D)) {
        l1 += (l2 / d);
      }
    }
    return l1;
  }
  
  public static float getSpeed(@NonNull ItineraryInfo paramItineraryInfo, @Nullable Leg paramLeg, @Nullable Point paramPoint)
  {
    if ((paramPoint != null) && (paramPoint.getSpeed() > 0.0F)) {
      return paramPoint.getSpeed();
    }
    if ((paramLeg != null) && (paramLeg.getSpeed() > 0.0F)) {
      return paramLeg.getSpeed();
    }
    return paramItineraryInfo.getSpeed();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.SimulationUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */