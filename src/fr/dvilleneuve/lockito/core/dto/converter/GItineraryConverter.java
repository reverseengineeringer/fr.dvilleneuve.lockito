package fr.dvilleneuve.lockito.core.dto.converter;

import fr.dvilleneuve.lockito.core.dto.GItinerary;
import fr.dvilleneuve.lockito.core.dto.GLeg;
import fr.dvilleneuve.lockito.core.dto.GLocation;
import fr.dvilleneuve.lockito.core.dto.GMeasurement;
import fr.dvilleneuve.lockito.core.dto.GPolyline;
import fr.dvilleneuve.lockito.core.dto.GRoute;
import fr.dvilleneuve.lockito.core.dto.GStep;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.androidannotations.annotations.EBean;

@EBean
public class GItineraryConverter
{
  private Deque<Point> decodePolyline(String paramString)
  {
    LinkedList localLinkedList = new LinkedList();
    int i = 0;
    int i2 = paramString.length();
    int k = 0;
    int j = 0;
    int m;
    int n;
    if (i < i2)
    {
      m = 0;
      n = 0;
    }
    for (int i1 = i;; i1 = i)
    {
      i = i1 + 1;
      i1 = paramString.charAt(i1) - '?';
      n |= (i1 & 0x1F) << m;
      m += 5;
      if (i1 < 32)
      {
        if ((n & 0x1) != 0)
        {
          m = n >> 1 ^ 0xFFFFFFFF;
          label94:
          i1 = k + m;
          k = 0;
          m = 0;
        }
        for (n = i;; n = i)
        {
          i = n + 1;
          n = paramString.charAt(n) - '?';
          m |= (n & 0x1F) << k;
          k += 5;
          if (n < 32)
          {
            if ((m & 0x1) != 0) {}
            for (k = m >> 1 ^ 0xFFFFFFFF;; k = m >> 1)
            {
              j += k;
              localLinkedList.add(new Point(i1 / 100000.0D, j / 100000.0D));
              k = i1;
              break;
              m = n >> 1;
              break label94;
            }
            return localLinkedList;
          }
        }
      }
    }
  }
  
  private Point toPoint(GLocation paramGLocation, String paramString)
  {
    return new Point(paramString, lat, lng, 0.0F, 0.0F);
  }
  
  public ItineraryInfo mergeToEntity(ItineraryInfo paramItineraryInfo, List<Point> paramList, GItinerary paramGItinerary)
  {
    long l2 = 0L;
    long l1 = 0L;
    if (routes.size() == 0) {
      return paramItineraryInfo;
    }
    Itinerary localItinerary = paramItineraryInfo.getItinerary();
    localItinerary.clear();
    ArrayList localArrayList = new ArrayList();
    paramGItinerary = (GRoute)routes.get(0);
    int i = 0;
    int k = legs.size();
    while (i < k)
    {
      GLeg localGLeg = (GLeg)legs.get(i);
      Leg localLeg = new Leg();
      localLeg.setStartWaypoint(toPoint(start_location, start_address));
      localLeg.setEndWaypoint(toPoint(end_location, end_address));
      localLeg.setDistance(distance.value);
      l2 += distance.value;
      l1 += duration.value;
      int j = 0;
      int m = steps.size();
      while (j < m)
      {
        localLeg.addAll(decodePolyline(steps.get(j)).polyline.points));
        j += 1;
      }
      localArrayList.add(localLeg);
      i += 1;
    }
    localItinerary.getLegs().addAll(localArrayList);
    localItinerary.setWaypoints(paramList);
    paramItineraryInfo.setDistance(l2);
    paramItineraryInfo.setDuration(l1);
    return paramItineraryInfo;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.dto.converter.GItineraryConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */