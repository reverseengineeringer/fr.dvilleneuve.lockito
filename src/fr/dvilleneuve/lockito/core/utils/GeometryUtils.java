package fr.dvilleneuve.lockito.core.utils;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.Bound;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.collection.GeometricList;
import fr.dvilleneuve.lockito.core.utils.collection.ImmutableFilteredPointsIterator;
import java.util.Collection;
import java.util.Iterator;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class GeometryUtils
{
  @RootContext
  Context context;
  private float touchCircleRadius;
  
  public static float bearing(@NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint1, @NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint2)
  {
    if (paramPoint1.equals(paramPoint2)) {
      return 0.0F;
    }
    float[] arrayOfFloat = new float[3];
    Location.distanceBetween(paramPoint1.getLatitude(), paramPoint1.getLongitude(), paramPoint2.getLatitude(), paramPoint2.getLongitude(), arrayOfFloat);
    return arrayOfFloat[2];
  }
  
  public static float distance(@NonNull Location paramLocation1, @NonNull Location paramLocation2)
  {
    if (paramLocation1.equals(paramLocation2)) {
      return 0.0F;
    }
    float[] arrayOfFloat = new float[1];
    Location.distanceBetween(paramLocation1.getLatitude(), paramLocation1.getLongitude(), paramLocation2.getLatitude(), paramLocation2.getLongitude(), arrayOfFloat);
    return arrayOfFloat[0];
  }
  
  public static float distance(@NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint1, @NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint2)
  {
    if (paramPoint1.equals(paramPoint2)) {
      return 0.0F;
    }
    float[] arrayOfFloat = new float[1];
    Location.distanceBetween(paramPoint1.getLatitude(), paramPoint1.getLongitude(), paramPoint2.getLatitude(), paramPoint2.getLongitude(), arrayOfFloat);
    return arrayOfFloat[0];
  }
  
  public static double distanceFromSegment(@NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint1, @NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint2, @NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint3)
  {
    double d1 = paramPoint2.getLatitude() - paramPoint1.getLatitude();
    double d2 = paramPoint2.getLongitude() - paramPoint1.getLongitude();
    double d3 = Math.sqrt(d1 * d1 + d2 * d2);
    return Math.abs((paramPoint3.getLatitude() - paramPoint1.getLatitude()) * d2 - (paramPoint3.getLongitude() - paramPoint1.getLongitude()) * d1) / d3;
  }
  
  public static void expandBound(@NonNull Bound paramBound1, @Nullable Bound paramBound2)
  {
    if (paramBound2 != null)
    {
      if (minLatitude < minLatitude) {
        minLatitude = minLatitude;
      }
      if (maxLatitude > maxLatitude) {
        maxLatitude = maxLatitude;
      }
      if (minLongitude < minLongitude) {
        minLongitude = minLongitude;
      }
      if (maxLongitude > maxLongitude) {
        maxLongitude = maxLongitude;
      }
    }
  }
  
  public static void expandBound(@NonNull Bound paramBound, @Nullable fr.dvilleneuve.lockito.core.model.Point paramPoint)
  {
    if (paramPoint != null)
    {
      if (paramPoint.getLatitude() < minLatitude) {
        minLatitude = paramPoint.getLatitude();
      }
      if (paramPoint.getLatitude() > maxLatitude) {
        maxLatitude = paramPoint.getLatitude();
      }
      if (paramPoint.getLongitude() < minLongitude) {
        minLongitude = paramPoint.getLongitude();
      }
      if (paramPoint.getLongitude() > maxLongitude) {
        maxLongitude = paramPoint.getLongitude();
      }
    }
  }
  
  private float sq(float paramFloat)
  {
    return (float)Math.pow(paramFloat, 2.0D);
  }
  
  protected float dpiToPixels(int paramInt)
  {
    DisplayMetrics localDisplayMetrics = context.getResources().getDisplayMetrics();
    return TypedValue.applyDimension(1, paramInt, localDisplayMetrics);
  }
  
  @NonNull
  protected LatLng fromScreenPoint(@NonNull Projection paramProjection, @NonNull android.graphics.Point paramPoint)
  {
    return paramProjection.fromScreenLocation(paramPoint);
  }
  
  protected float getTouchCircleRadius()
  {
    return touchCircleRadius;
  }
  
  public int indexOfIntersectLeg(@NonNull Projection paramProjection, @NonNull Itinerary paramItinerary, @NonNull LatLng paramLatLng)
  {
    android.graphics.Point localPoint = toScreenPoint(paramProjection, paramLatLng);
    Object localObject = fromScreenPoint(paramProjection, new android.graphics.Point(0, 0));
    double d = Math.max(fromScreenPointandroid.graphics.PointgetTouchCircleRadius0longitude - longitude, 0.02D);
    Logger.debug("Filter with distance %f", new Object[] { Double.valueOf(d) });
    int i = -1;
    Iterator localIterator = paramItinerary.getLegs().iterator();
    while (localIterator.hasNext())
    {
      paramItinerary = (Leg)localIterator.next();
      int j = i + 1;
      i = j;
      if (paramItinerary.getBound().contains(new fr.dvilleneuve.lockito.core.model.Point(paramLatLng)))
      {
        paramItinerary = paramItinerary.getPoints();
        ImmutableFilteredPointsIterator localImmutableFilteredPointsIterator = new ImmutableFilteredPointsIterator(paramItinerary, d);
        Logger.debug("Search intersect in leg %d with %d original points", new Object[] { Integer.valueOf(j), Integer.valueOf(paramItinerary.size()) });
        i = j;
        if (localImmutableFilteredPointsIterator.hasNext()) {
          for (paramItinerary = toScreenPoint(paramProjection, localImmutableFilteredPointsIterator.next());; paramItinerary = (Itinerary)localObject)
          {
            i = j;
            if (!localImmutableFilteredPointsIterator.hasNext()) {
              break;
            }
            localObject = toScreenPoint(paramProjection, localImmutableFilteredPointsIterator.next());
            if (isIntersect(paramItinerary, (android.graphics.Point)localObject, localPoint, getTouchCircleRadius())) {
              return j;
            }
          }
        }
      }
    }
    return -1;
  }
  
  @AfterInject
  void init()
  {
    touchCircleRadius = dpiToPixels(20);
  }
  
  protected boolean isIntersect(@NonNull android.graphics.Point paramPoint1, @NonNull android.graphics.Point paramPoint2, float paramFloat)
  {
    return sq(x - x) + sq(y - y) <= sq(paramFloat);
  }
  
  protected boolean isIntersect(@NonNull android.graphics.Point paramPoint1, @NonNull android.graphics.Point paramPoint2, @NonNull android.graphics.Point paramPoint3, float paramFloat)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramPoint1.equals(paramPoint2)) {
      bool1 = isIntersect(paramPoint1, paramPoint3, paramFloat);
    }
    float f1;
    float f5;
    do
    {
      float f2;
      float f3;
      float f4;
      float f6;
      do
      {
        do
        {
          return bool1;
          f1 = x - x;
          f2 = y - y;
          f3 = x - x;
          f4 = y - y;
          f6 = sq(paramFloat);
          paramFloat = sq(f1);
          f5 = sq(f2);
          if (sq(f4 * f1 - f3 * f2) > (paramFloat + f5) * f6) {
            break;
          }
          bool1 = bool2;
        } while (sq(f3) + sq(f4) <= f6);
        bool1 = bool2;
      } while (sq(f1 - f3) + sq(f2 - f4) <= f6);
      f1 = f3 * f1 + f4 * f2;
      if (f1 < 0.0F) {
        break;
      }
      bool1 = bool2;
    } while (f1 <= paramFloat + f5);
    return false;
  }
  
  @NonNull
  protected android.graphics.Point toScreenPoint(@NonNull Projection paramProjection, @NonNull LatLng paramLatLng)
  {
    return paramProjection.toScreenLocation(paramLatLng);
  }
  
  @NonNull
  protected android.graphics.Point toScreenPoint(@NonNull Projection paramProjection, @NonNull fr.dvilleneuve.lockito.core.model.Point paramPoint)
  {
    return toScreenPoint(paramProjection, paramPoint.toLatLng());
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.GeometryUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */