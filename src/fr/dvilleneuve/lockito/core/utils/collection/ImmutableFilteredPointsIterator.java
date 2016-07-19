package fr.dvilleneuve.lockito.core.utils.collection;

import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.utils.GeometryUtils;
import java.util.Iterator;
import java.util.List;

public class ImmutableFilteredPointsIterator
  implements Iterator<Point>
{
  private int index;
  private double lastDistance;
  private final List<Point> list;
  private final double minDistance;
  private Point p1;
  private Point p2;
  private final int size;
  
  public ImmutableFilteredPointsIterator(List<Point> paramList, double paramDouble)
  {
    list = paramList;
    size = paramList.size();
    minDistance = paramDouble;
    index = 0;
    lastDistance = -1.0D;
    if (size > 0) {
      p1 = ((Point)paramList.get(0));
    }
    if (size > 1) {
      p2 = ((Point)paramList.get(size - 1));
    }
  }
  
  public boolean hasNext()
  {
    return index < size;
  }
  
  public Point next()
  {
    if (index == 0)
    {
      index += 1;
      return p1;
    }
    if (index == size - 1)
    {
      index += 1;
      return p2;
    }
    while (index < size)
    {
      Point localPoint = (Point)list.get(index);
      if (GeometryUtils.distance(p1, localPoint) >= minDistance)
      {
        double d = GeometryUtils.distanceFromSegment(p1, p2, localPoint);
        if (d > lastDistance) {
          lastDistance = d;
        }
      }
      else
      {
        index += 1;
        continue;
      }
      lastDistance = -1.0D;
      p1 = ((Point)list.get(index - 1));
      return p1;
    }
    return p2;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.collection.ImmutableFilteredPointsIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */