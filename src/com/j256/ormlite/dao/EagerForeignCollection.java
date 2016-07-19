package com.j256.ormlite.dao;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.DatabaseResults;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EagerForeignCollection<T, ID>
  extends BaseForeignCollection<T, ID>
  implements ForeignCollection<T>, CloseableWrappedIterable<T>, Serializable
{
  private static final long serialVersionUID = -2523335606983317721L;
  private List<T> results;
  
  public EagerForeignCollection(Dao<T, ID> paramDao, Object paramObject1, Object paramObject2, FieldType paramFieldType, String paramString, boolean paramBoolean)
    throws SQLException
  {
    super(paramDao, paramObject1, paramObject2, paramFieldType, paramString, paramBoolean);
    if (paramObject2 == null)
    {
      results = new ArrayList();
      return;
    }
    results = paramDao.query(getPreparedQuery());
  }
  
  public boolean add(T paramT)
  {
    if (results.add(paramT)) {
      return super.add(paramT);
    }
    return false;
  }
  
  public boolean addAll(Collection<? extends T> paramCollection)
  {
    if (results.addAll(paramCollection)) {
      return super.addAll(paramCollection);
    }
    return false;
  }
  
  public void close() {}
  
  public void closeLastIterator() {}
  
  public CloseableIterator<T> closeableIterator()
  {
    return iteratorThrow(-1);
  }
  
  public CloseableIterator<T> closeableIterator(int paramInt)
  {
    return iteratorThrow(-1);
  }
  
  public boolean contains(Object paramObject)
  {
    return results.contains(paramObject);
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    return results.containsAll(paramCollection);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof EagerForeignCollection)) {
      return false;
    }
    paramObject = (EagerForeignCollection)paramObject;
    return results.equals(results);
  }
  
  public CloseableWrappedIterable<T> getWrappedIterable()
  {
    return this;
  }
  
  public CloseableWrappedIterable<T> getWrappedIterable(int paramInt)
  {
    return this;
  }
  
  public int hashCode()
  {
    return results.hashCode();
  }
  
  public boolean isEager()
  {
    return true;
  }
  
  public boolean isEmpty()
  {
    return results.isEmpty();
  }
  
  public CloseableIterator<T> iterator()
  {
    return iteratorThrow(-1);
  }
  
  public CloseableIterator<T> iterator(int paramInt)
  {
    return iteratorThrow(paramInt);
  }
  
  public CloseableIterator<T> iteratorThrow()
  {
    return iteratorThrow(-1);
  }
  
  public CloseableIterator<T> iteratorThrow(int paramInt)
  {
    new CloseableIterator()
    {
      private int offset = -1;
      
      public void close() {}
      
      public void closeQuietly() {}
      
      public T current()
      {
        if (offset < 0) {
          offset = 0;
        }
        if (offset >= results.size()) {
          return null;
        }
        return (T)results.get(offset);
      }
      
      public T first()
      {
        offset = 0;
        if (offset >= results.size()) {
          return null;
        }
        return (T)results.get(0);
      }
      
      public DatabaseResults getRawResults()
      {
        return null;
      }
      
      public boolean hasNext()
      {
        return offset + 1 < results.size();
      }
      
      public T moveRelative(int paramAnonymousInt)
      {
        offset += paramAnonymousInt;
        if ((offset < 0) || (offset >= results.size())) {
          return null;
        }
        return (T)results.get(offset);
      }
      
      public void moveToNext()
      {
        offset += 1;
      }
      
      public T next()
      {
        offset += 1;
        return (T)results.get(offset);
      }
      
      public T nextThrow()
      {
        offset += 1;
        if (offset >= results.size()) {
          return null;
        }
        return (T)results.get(offset);
      }
      
      public T previous()
      {
        offset -= 1;
        if ((offset < 0) || (offset >= results.size())) {
          return null;
        }
        return (T)results.get(offset);
      }
      
      public void remove()
      {
        if (offset < 0) {
          throw new IllegalStateException("next() must be called before remove()");
        }
        if (offset >= results.size()) {
          throw new IllegalStateException("current results position (" + offset + ") is out of bounds");
        }
        Object localObject = results.remove(offset);
        offset -= 1;
        if (dao != null) {}
        try
        {
          dao.delete(localObject);
          return;
        }
        catch (SQLException localSQLException)
        {
          throw new RuntimeException(localSQLException);
        }
      }
    };
  }
  
  public int refreshAll()
    throws SQLException
  {
    int i = 0;
    Iterator localIterator = results.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      i += dao.refresh(localObject);
    }
    return i;
  }
  
  public int refreshCollection()
    throws SQLException
  {
    results = dao.query(getPreparedQuery());
    return results.size();
  }
  
  public boolean remove(Object paramObject)
  {
    boolean bool = true;
    if ((!results.remove(paramObject)) || (dao == null)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        int i = dao.delete(paramObject);
        if (i == 1) {
          continue;
        }
        return false;
      }
      catch (SQLException paramObject)
      {
        throw new IllegalStateException("Could not delete data element from dao", (Throwable)paramObject);
      }
    }
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    boolean bool = false;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (remove(paramCollection.next())) {
        bool = true;
      }
    }
    return bool;
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    return super.retainAll(paramCollection);
  }
  
  public int size()
  {
    return results.size();
  }
  
  public Object[] toArray()
  {
    return results.toArray();
  }
  
  public <E> E[] toArray(E[] paramArrayOfE)
  {
    return results.toArray(paramArrayOfE);
  }
  
  public int updateAll()
    throws SQLException
  {
    int i = 0;
    Iterator localIterator = results.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      i += dao.update(localObject);
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.dao.EagerForeignCollection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */