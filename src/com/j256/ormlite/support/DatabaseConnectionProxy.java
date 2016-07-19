package com.j256.ormlite.support;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import java.sql.SQLException;
import java.sql.Savepoint;

public class DatabaseConnectionProxy
  implements DatabaseConnection
{
  private final DatabaseConnection proxy;
  
  public DatabaseConnectionProxy(DatabaseConnection paramDatabaseConnection)
  {
    proxy = paramDatabaseConnection;
  }
  
  public void close()
    throws SQLException
  {
    if (proxy != null) {
      proxy.close();
    }
  }
  
  public void closeQuietly()
  {
    if (proxy != null) {
      proxy.closeQuietly();
    }
  }
  
  public void commit(Savepoint paramSavepoint)
    throws SQLException
  {
    if (proxy != null) {
      proxy.commit(paramSavepoint);
    }
  }
  
  public CompiledStatement compileStatement(String paramString, StatementBuilder.StatementType paramStatementType, FieldType[] paramArrayOfFieldType, int paramInt)
    throws SQLException
  {
    if (proxy == null) {
      return null;
    }
    return proxy.compileStatement(paramString, paramStatementType, paramArrayOfFieldType, paramInt);
  }
  
  public int delete(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType)
    throws SQLException
  {
    if (proxy == null) {
      return 0;
    }
    return proxy.delete(paramString, paramArrayOfObject, paramArrayOfFieldType);
  }
  
  public int executeStatement(String paramString, int paramInt)
    throws SQLException
  {
    if (proxy == null) {
      return 0;
    }
    return proxy.executeStatement(paramString, paramInt);
  }
  
  public int insert(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType, GeneratedKeyHolder paramGeneratedKeyHolder)
    throws SQLException
  {
    if (proxy == null) {
      return 0;
    }
    return proxy.insert(paramString, paramArrayOfObject, paramArrayOfFieldType, paramGeneratedKeyHolder);
  }
  
  public boolean isAutoCommit()
    throws SQLException
  {
    if (proxy == null) {
      return false;
    }
    return proxy.isAutoCommit();
  }
  
  public boolean isAutoCommitSupported()
    throws SQLException
  {
    if (proxy == null) {
      return false;
    }
    return proxy.isAutoCommitSupported();
  }
  
  public boolean isClosed()
    throws SQLException
  {
    if (proxy == null) {
      return true;
    }
    return proxy.isClosed();
  }
  
  public boolean isTableExists(String paramString)
    throws SQLException
  {
    if (proxy == null) {
      return false;
    }
    return proxy.isTableExists(paramString);
  }
  
  public long queryForLong(String paramString)
    throws SQLException
  {
    if (proxy == null) {
      return 0L;
    }
    return proxy.queryForLong(paramString);
  }
  
  public long queryForLong(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType)
    throws SQLException
  {
    if (proxy == null) {
      return 0L;
    }
    return proxy.queryForLong(paramString, paramArrayOfObject, paramArrayOfFieldType);
  }
  
  public <T> Object queryForOne(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType, GenericRowMapper<T> paramGenericRowMapper, ObjectCache paramObjectCache)
    throws SQLException
  {
    if (proxy == null) {
      return null;
    }
    return proxy.queryForOne(paramString, paramArrayOfObject, paramArrayOfFieldType, paramGenericRowMapper, paramObjectCache);
  }
  
  public void rollback(Savepoint paramSavepoint)
    throws SQLException
  {
    if (proxy != null) {
      proxy.rollback(paramSavepoint);
    }
  }
  
  public void setAutoCommit(boolean paramBoolean)
    throws SQLException
  {
    if (proxy != null) {
      proxy.setAutoCommit(paramBoolean);
    }
  }
  
  public Savepoint setSavePoint(String paramString)
    throws SQLException
  {
    if (proxy == null) {
      return null;
    }
    return proxy.setSavePoint(paramString);
  }
  
  public int update(String paramString, Object[] paramArrayOfObject, FieldType[] paramArrayOfFieldType)
    throws SQLException
  {
    if (proxy == null) {
      return 0;
    }
    return proxy.update(paramString, paramArrayOfObject, paramArrayOfFieldType);
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.support.DatabaseConnectionProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */