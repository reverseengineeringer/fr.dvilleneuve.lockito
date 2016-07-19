package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

public class SelectIterator<T, ID>
  implements CloseableIterator<T>
{
  private static final Logger logger = LoggerFactory.getLogger(SelectIterator.class);
  private boolean alreadyMoved;
  private final Dao<T, ID> classDao;
  private boolean closed;
  private final CompiledStatement compiledStmt;
  private final DatabaseConnection connection;
  private final ConnectionSource connectionSource;
  private final Class<?> dataClass;
  private boolean first = true;
  private T last;
  private final DatabaseResults results;
  private int rowC;
  private final GenericRowMapper<T> rowMapper;
  private final String statement;
  
  public SelectIterator(Class<?> paramClass, Dao<T, ID> paramDao, GenericRowMapper<T> paramGenericRowMapper, ConnectionSource paramConnectionSource, DatabaseConnection paramDatabaseConnection, CompiledStatement paramCompiledStatement, String paramString, ObjectCache paramObjectCache)
    throws SQLException
  {
    dataClass = paramClass;
    classDao = paramDao;
    rowMapper = paramGenericRowMapper;
    connectionSource = paramConnectionSource;
    connection = paramDatabaseConnection;
    compiledStmt = paramCompiledStatement;
    results = paramCompiledStatement.runQuery(paramObjectCache);
    statement = paramString;
    if (paramString != null) {
      logger.debug("starting iterator @{} for '{}'", Integer.valueOf(hashCode()), paramString);
    }
  }
  
  private T getCurrent()
    throws SQLException
  {
    last = rowMapper.mapRow(results);
    alreadyMoved = false;
    rowC += 1;
    return (T)last;
  }
  
  public void close()
    throws SQLException
  {
    if (!closed)
    {
      compiledStmt.close();
      closed = true;
      last = null;
      if (statement != null) {
        logger.debug("closed iterator @{} after {} rows", Integer.valueOf(hashCode()), Integer.valueOf(rowC));
      }
      connectionSource.releaseConnection(connection);
    }
  }
  
  public void closeQuietly()
  {
    try
    {
      close();
      return;
    }
    catch (SQLException localSQLException) {}
  }
  
  public T current()
    throws SQLException
  {
    if (closed) {
      return null;
    }
    if (first) {
      return (T)first();
    }
    return (T)getCurrent();
  }
  
  public T first()
    throws SQLException
  {
    if (closed) {}
    do
    {
      return null;
      first = false;
    } while (!results.first());
    return (T)getCurrent();
  }
  
  public DatabaseResults getRawResults()
  {
    return results;
  }
  
  public boolean hasNext()
  {
    try
    {
      boolean bool = hasNextThrow();
      return bool;
    }
    catch (SQLException localSQLException)
    {
      last = null;
      closeQuietly();
      throw new IllegalStateException("Errors getting more results of " + dataClass, localSQLException);
    }
  }
  
  public boolean hasNextThrow()
    throws SQLException
  {
    if (closed) {
      return false;
    }
    if (alreadyMoved) {
      return true;
    }
    if (first) {
      first = false;
    }
    for (boolean bool = results.first();; bool = results.next())
    {
      if (!bool) {
        close();
      }
      alreadyMoved = true;
      return bool;
    }
  }
  
  public T moveRelative(int paramInt)
    throws SQLException
  {
    if (closed) {}
    do
    {
      return null;
      first = false;
    } while (!results.moveRelative(paramInt));
    return (T)getCurrent();
  }
  
  public void moveToNext()
  {
    last = null;
    first = false;
    alreadyMoved = false;
  }
  
  public T next()
  {
    Object localObject1 = null;
    try
    {
      Object localObject2 = nextThrow();
      if (localObject2 != null) {
        return (T)localObject2;
      }
    }
    catch (SQLException localSQLException)
    {
      last = null;
      closeQuietly();
      throw new IllegalStateException("Could not get next result for " + dataClass, localSQLException);
    }
  }
  
  public T nextThrow()
    throws SQLException
  {
    if (closed) {
      return null;
    }
    if (!alreadyMoved)
    {
      if (first) {
        first = false;
      }
      for (boolean bool = results.first(); !bool; bool = results.next())
      {
        first = false;
        return null;
      }
    }
    first = false;
    return (T)getCurrent();
  }
  
  public T previous()
    throws SQLException
  {
    if (closed) {}
    do
    {
      return null;
      first = false;
    } while (!results.previous());
    return (T)getCurrent();
  }
  
  public void remove()
  {
    try
    {
      removeThrow();
      return;
    }
    catch (SQLException localSQLException)
    {
      closeQuietly();
      throw new IllegalStateException("Could not delete " + dataClass + " object " + last, localSQLException);
    }
  }
  
  public void removeThrow()
    throws SQLException
  {
    if (last == null) {
      throw new IllegalStateException("No last " + dataClass + " object to remove. Must be called after a call to next.");
    }
    if (classDao == null) {
      throw new IllegalStateException("Cannot remove " + dataClass + " object because classDao not initialized");
    }
    try
    {
      classDao.delete(last);
      return;
    }
    finally
    {
      last = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.stmt.SelectIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */