package com.j256.ormlite.support;

import com.j256.ormlite.logger.Logger;
import java.sql.SQLException;

public abstract class BaseConnectionSource
  implements ConnectionSource
{
  private ThreadLocal<NestedConnection> specialConnection = new ThreadLocal();
  
  protected boolean clearSpecial(DatabaseConnection paramDatabaseConnection, Logger paramLogger)
  {
    NestedConnection localNestedConnection = (NestedConnection)specialConnection.get();
    if (paramDatabaseConnection == null) {
      return false;
    }
    if (localNestedConnection == null)
    {
      paramLogger.error("no connection has been saved when clear() called");
      return false;
    }
    if (connection == paramDatabaseConnection)
    {
      if (localNestedConnection.decrementAndGet() == 0) {
        specialConnection.set(null);
      }
      return true;
    }
    paramLogger.error("connection saved {} is not the one being cleared {}", connection, paramDatabaseConnection);
    return false;
  }
  
  protected DatabaseConnection getSavedConnection()
  {
    NestedConnection localNestedConnection = (NestedConnection)specialConnection.get();
    if (localNestedConnection == null) {
      return null;
    }
    return connection;
  }
  
  public DatabaseConnection getSpecialConnection()
  {
    NestedConnection localNestedConnection = (NestedConnection)specialConnection.get();
    if (localNestedConnection == null) {
      return null;
    }
    return connection;
  }
  
  protected boolean isSavedConnection(DatabaseConnection paramDatabaseConnection)
  {
    NestedConnection localNestedConnection = (NestedConnection)specialConnection.get();
    if (localNestedConnection == null) {}
    while (connection != paramDatabaseConnection) {
      return false;
    }
    return true;
  }
  
  protected boolean saveSpecial(DatabaseConnection paramDatabaseConnection)
    throws SQLException
  {
    NestedConnection localNestedConnection = (NestedConnection)specialConnection.get();
    if (localNestedConnection == null)
    {
      specialConnection.set(new NestedConnection(paramDatabaseConnection));
      return true;
    }
    if (connection != paramDatabaseConnection) {
      throw new SQLException("trying to save connection " + paramDatabaseConnection + " but already have saved connection " + connection);
    }
    localNestedConnection.increment();
    return false;
  }
  
  private static class NestedConnection
  {
    public final DatabaseConnection connection;
    private int nestedC;
    
    public NestedConnection(DatabaseConnection paramDatabaseConnection)
    {
      connection = paramDatabaseConnection;
      nestedC = 1;
    }
    
    public int decrementAndGet()
    {
      nestedC -= 1;
      return nestedC;
    }
    
    public void increment()
    {
      nestedC += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.support.BaseConnectionSource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */