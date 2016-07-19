package com.j256.ormlite.support;

import java.sql.SQLException;

public abstract interface DatabaseConnectionProxyFactory
{
  public abstract DatabaseConnection createProxy(DatabaseConnection paramDatabaseConnection)
    throws SQLException;
}

/* Location:
 * Qualified Name:     com.j256.ormlite.support.DatabaseConnectionProxyFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */