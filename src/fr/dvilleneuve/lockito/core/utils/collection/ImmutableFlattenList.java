package fr.dvilleneuve.lockito.core.utils.collection;

import android.support.annotation.NonNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class ImmutableFlattenList<T, E>
  implements List<FlattenItem<E>>
{
  private final boolean isEmpty;
  private final Collection<T> lists;
  private final int size;
  
  public ImmutableFlattenList(Collection<T> paramCollection)
  {
    lists = paramCollection;
    int i = 0;
    boolean bool = true;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      List localList = getSubList(paramCollection.next());
      int j = i + localList.size();
      i = j;
      if (!localList.isEmpty())
      {
        bool = false;
        i = j;
      }
    }
    size = i;
    isEmpty = bool;
  }
  
  public void add(int paramInt, FlattenItem<E> paramFlattenItem)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean add(FlattenItem<E> paramFlattenItem)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(int paramInt, @NonNull Collection<? extends FlattenItem<E>> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(@NonNull Collection<? extends FlattenItem<E>> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean contains(Object paramObject)
  {
    Iterator localIterator = lists.iterator();
    while (localIterator.hasNext()) {
      if (getSubList(localIterator.next()).contains(paramObject)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsAll(@NonNull Collection<?> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (!contains(paramCollection.next())) {
        return false;
      }
    }
    return true;
  }
  
  public FlattenItem<E> get(int paramInt)
  {
    if (paramInt >= size) {
      throw new IndexOutOfBoundsException("Invalid index " + paramInt + ", size is " + size);
    }
    Iterator localIterator = lists.iterator();
    List localList1 = null;
    int j = 0;
    int i = paramInt;
    paramInt = j;
    List localList2;
    for (;;)
    {
      localList2 = localList1;
      if (!localIterator.hasNext()) {
        break;
      }
      localList1 = getSubList(localIterator.next());
      j = localList1.size();
      localList2 = localList1;
      if (i < j) {
        break;
      }
      i -= j;
      paramInt += 1;
    }
    if ((i < 0) || (localList2 == null)) {
      return null;
    }
    return new FlattenItem(localList2.get(i), paramInt);
  }
  
  public abstract List<E> getSubList(T paramT);
  
  public int indexOf(Object paramObject)
  {
    return 0;
  }
  
  public boolean isEmpty()
  {
    return isEmpty;
  }
  
  @NonNull
  public Iterator<FlattenItem<E>> iterator()
  {
    return new ImmutableFlattenListIterator();
  }
  
  public int lastIndexOf(Object paramObject)
  {
    return 0;
  }
  
  public ListIterator<FlattenItem<E>> listIterator()
  {
    throw new UnsupportedOperationException();
  }
  
  @NonNull
  public ListIterator<FlattenItem<E>> listIterator(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public FlattenItem<E> remove(int paramInt)
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
  
  public FlattenItem<E> set(int paramInt, FlattenItem<E> paramFlattenItem)
  {
    throw new UnsupportedOperationException();
  }
  
  public int size()
  {
    return size;
  }
  
  @NonNull
  public List<FlattenItem<E>> subList(int paramInt1, int paramInt2)
  {
    throw new UnsupportedOperationException();
  }
  
  @NonNull
  public Object[] toArray()
  {
    throw new UnsupportedOperationException();
  }
  
  @NonNull
  public <T> T[] toArray(@NonNull T[] paramArrayOfT)
  {
    throw new UnsupportedOperationException();
  }
  
  private class ImmutableFlattenListIterator
    implements Iterator<FlattenItem<E>>
  {
    private final Iterator<T> iterator = lists.iterator();
    private Iterator<E> listIterator;
    private int subListIndex = 0;
    
    ImmutableFlattenListIterator()
    {
      if (iterator.hasNext()) {
        listIterator = getSubList(iterator.next()).iterator();
      }
    }
    
    public boolean hasNext()
    {
      if (listIterator == null) {}
      do
      {
        return false;
        if (listIterator.hasNext()) {
          return true;
        }
      } while (!iterator.hasNext());
      listIterator = getSubList(iterator.next()).iterator();
      subListIndex += 1;
      return hasNext();
    }
    
    public FlattenItem<E> next()
    {
      if (listIterator == null) {
        throw new NoSuchElementException();
      }
      if (listIterator.hasNext()) {
        return new FlattenItem(listIterator.next(), subListIndex);
      }
      if (iterator.hasNext())
      {
        listIterator = getSubList(iterator.next()).iterator();
        subListIndex += 1;
        return next();
      }
      throw new NoSuchElementException();
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.collection.ImmutableFlattenList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */