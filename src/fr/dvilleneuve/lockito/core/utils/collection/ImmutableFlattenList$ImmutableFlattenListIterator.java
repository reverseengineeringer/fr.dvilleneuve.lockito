package fr.dvilleneuve.lockito.core.utils.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class ImmutableFlattenList$ImmutableFlattenListIterator
  implements Iterator<FlattenItem<E>>
{
  private final Iterator<T> iterator;
  private Iterator<E> listIterator;
  private int subListIndex = 0;
  
  ImmutableFlattenList$ImmutableFlattenListIterator(ImmutableFlattenList paramImmutableFlattenList)
  {
    iterator = ImmutableFlattenList.access$000(paramImmutableFlattenList).iterator();
    if (iterator.hasNext()) {
      listIterator = paramImmutableFlattenList.getSubList(iterator.next()).iterator();
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
    listIterator = this$0.getSubList(iterator.next()).iterator();
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
      listIterator = this$0.getSubList(iterator.next()).iterator();
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

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.collection.ImmutableFlattenList.ImmutableFlattenListIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */