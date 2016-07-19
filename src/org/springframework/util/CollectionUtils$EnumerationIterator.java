package org.springframework.util;

import java.util.Enumeration;
import java.util.Iterator;

class CollectionUtils$EnumerationIterator<E>
  implements Iterator<E>
{
  private Enumeration<E> enumeration;
  
  public CollectionUtils$EnumerationIterator(Enumeration<E> paramEnumeration)
  {
    enumeration = paramEnumeration;
  }
  
  public boolean hasNext()
  {
    return enumeration.hasMoreElements();
  }
  
  public E next()
  {
    return (E)enumeration.nextElement();
  }
  
  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Not supported");
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.CollectionUtils.EnumerationIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */