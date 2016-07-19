package fr.dvilleneuve.lockito.core.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import fr.dvilleneuve.lockito.core.model.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LocationsConverter
{
  @NonNull
  public static List<LatLng> toLatLngs(@Nullable List<Point> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localArrayList.add(((Point)paramList.next()).toLatLng());
      }
    }
    return localArrayList;
  }
  
  @Nullable
  public static Point toLocation(@Nullable String paramString)
  {
    if (paramString == null) {}
    do
    {
      return null;
      paramString = paramString.split(",");
    } while (paramString.length != 2);
    return new Point(Double.parseDouble(paramString[0]), Double.parseDouble(paramString[1]));
  }
  
  @Nullable
  public static List<Point> toLocations(@Nullable String paramString)
  {
    if (paramString == null)
    {
      paramString = Collections.emptyList();
      return paramString;
    }
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = paramString.split("\\|");
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      paramString = localArrayList;
      if (i >= j) {
        break;
      }
      paramString = toLocation(arrayOfString[i]);
      if (paramString != null) {
        localArrayList.add(paramString);
      }
      i += 1;
    }
  }
  
  @NonNull
  public static List<Point> toLocations(@Nullable List<Marker> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localArrayList.add(new Point(((Marker)paramList.next()).getPosition()));
      }
    }
    return localArrayList;
  }
  
  @Nullable
  public static String toString(@Nullable Point paramPoint)
  {
    if (paramPoint == null) {
      return null;
    }
    return paramPoint.getLatitude() + "," + paramPoint.getLongitude();
  }
  
  @Nullable
  public static String toString(@Nullable List<Point> paramList)
  {
    if (CollectionUtils.isEmpty(paramList)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramList.size();
    if (i < j)
    {
      Point localPoint = (Point)paramList.get(i);
      if (localPoint == null) {}
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(toString(localPoint));
        if (i < j - 1) {
          localStringBuilder.append("|");
        }
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.LocationsConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */