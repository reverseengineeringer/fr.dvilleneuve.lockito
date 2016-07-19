package com.j256.ormlite.dao;

import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;
import java.util.Iterator;

public abstract interface CloseableIterator<T>
  extends Iterator<T>
{
  public abstract void close()
    throws SQLException;
  
  public abstract void closeQuietly();
  
  public abstract T current()
    throws SQLException;
  
  public abstract T first()
    throws SQLException;
  
  public abstract DatabaseResults getRawResults();
  
  public abstract T moveRelative(int paramInt)
    throws SQLException;
  
  public abstract void moveToNext();
  
  public abstract T nextThrow()
    throws SQLException;
  
  public abstract T previous()
    throws SQLException;
}

/* Location:
 * Qualified Name:     com.j256.ormlite.dao.CloseableIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */