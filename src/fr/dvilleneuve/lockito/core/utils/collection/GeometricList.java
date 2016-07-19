package fr.dvilleneuve.lockito.core.utils.collection;

import android.support.annotation.NonNull;
import fr.dvilleneuve.lockito.core.model.Bound;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.utils.GeometryUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GeometricList<E extends Point>
  extends ArrayList<E>
{
  private final Bound bound = new Bound();
  
  public void add(int paramInt, E paramE)
  {
    super.add(paramInt, paramE);
    GeometryUtils.expandBound(bound, paramE);
  }
  
  public boolean add(E paramE)
  {
    if (super.add(paramE))
    {
      GeometryUtils.expandBound(bound, paramE);
      return true;
    }
    return false;
  }
  
  public boolean addAll(int paramInt, Collection<? extends E> paramCollection)
  {
    if (super.addAll(paramInt, paramCollection))
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Point localPoint = (Point)paramCollection.next();
        GeometryUtils.expandBound(bound, localPoint);
      }
      return true;
    }
    return false;
  }
  
  public boolean addAll(Collection<? extends E> paramCollection)
  {
    if (super.addAll(paramCollection))
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Point localPoint = (Point)paramCollection.next();
        GeometryUtils.expandBound(bound, localPoint);
      }
      return true;
    }
    return false;
  }
  
  public void clear()
  {
    super.clear();
    bound.clear();
  }
  
  @NonNull
  public Bound getBound()
  {
    return bound;
  }
  
  public E remove(int paramInt)
  {
    return (Point)super.remove(paramInt);
  }
  
  public boolean remove(Object paramObject)
  {
    return super.remove(paramObject);
  }
  
  public E set(int paramInt, E paramE)
  {
    Point localPoint = (Point)super.set(paramInt, paramE);
    GeometryUtils.expandBound(bound, paramE);
    return localPoint;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.collection.GeometricList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */