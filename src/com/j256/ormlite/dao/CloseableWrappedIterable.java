package com.j256.ormlite.dao;

import java.sql.SQLException;

public abstract interface CloseableWrappedIterable<T>
  extends CloseableIterable<T>
{
  public abstract void close()
    throws SQLException;
}

/* Location:
 * Qualified Name:     com.j256.ormlite.dao.CloseableWrappedIterable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */