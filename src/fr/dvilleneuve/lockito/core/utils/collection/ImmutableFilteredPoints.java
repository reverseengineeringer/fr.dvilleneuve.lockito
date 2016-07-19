package fr.dvilleneuve.lockito.core.utils.collection;

import android.support.annotation.NonNull;
import fr.dvilleneuve.lockito.core.model.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ImmutableFilteredPoints
  implements List<Point>
{
  private final List<Point> filteredList = new ArrayList();
  
  public ImmutableFilteredPoints(List<Point> paramList, double paramDouble)
  {
    this(paramList, paramDouble, true);
  }
  
  public ImmutableFilteredPoints(List<Point> paramList, double paramDouble, boolean paramBoolean)
  {
    if (paramList.size() > 1)
    {
      paramDouble = Math.pow(paramDouble, 2.0D);
      int i = 0;
      int m = paramList.size();
      for (;;)
      {
        Point localPoint1;
        int j;
        if (i < m)
        {
          localPoint1 = (Point)paramList.get(i);
          filteredList.add(localPoint1);
          j = i + 1;
        }
        int k;
        for (;;)
        {
          k = i;
          if (j < m)
          {
            Point localPoint2 = (Point)paramList.get(j);
            if (Math.pow(localPoint2.getLatitude() - localPoint1.getLatitude(), 2.0D) + Math.pow(localPoint2.getLongitude() - localPoint1.getLongitude(), 2.0D) >= paramDouble) {
              k = j;
            }
          }
          else
          {
            if (j != m) {
              break;
            }
            if ((paramBoolean) && (k != j - 1)) {
              filteredList.add(paramList.get(j - 1));
            }
            return;
          }
          j += 1;
        }
        i = k - 1 + 1;
      }
    }
    filteredList.addAll(paramList);
  }
  
  public void add(int paramInt, Point paramPoint)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(Point paramPoint)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int paramInt, @NonNull Collection<? extends Point> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(@NonNull Collection<? extends Point> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean contains(Object paramObject)
  {
    return filteredList.contains(paramObject);
  }
  
  public boolean containsAll(@NonNull Collection<?> paramCollection)
  {
    return filteredList.containsAll(paramCollection);
  }
  
  public Point get(int paramInt)
  {
    return (Point)filteredList.get(paramInt);
  }
  
  public int indexOf(Object paramObject)
  {
    return filteredList.indexOf(paramObject);
  }
  
  public boolean isEmpty()
  {
    return filteredList.isEmpty();
  }
  
  @NonNull
  public Iterator<Point> iterator()
  {
    return filteredList.iterator();
  }
  
  public int lastIndexOf(Object paramObject)
  {
    return filteredList.lastIndexOf(paramObject);
  }
  
  public ListIterator<Point> listIterator()
  {
    return filteredList.listIterator();
  }
  
  @NonNull
  public ListIterator<Point> listIterator(int paramInt)
  {
    return filteredList.listIterator();
  }
  
  public Point remove(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean removeAll(@NonNull Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean retainAll(@NonNull Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public Point set(int paramInt, Point paramPoint)
  {
    throw new UnsupportedOperationException();
  }
  
  public int size()
  {
    return filteredList.size();
  }
  
  @NonNull
  public List<Point> subList(int paramInt1, int paramInt2)
  {
    return filteredList.subList(paramInt1, paramInt2);
  }
  
  @NonNull
  public Object[] toArray()
  {
    return filteredList.toArray();
  }
  
  @NonNull
  public <T> T[] toArray(@NonNull T[] paramArrayOfT)
  {
    return filteredList.toArray(paramArrayOfT);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.collection.ImmutableFilteredPoints
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */