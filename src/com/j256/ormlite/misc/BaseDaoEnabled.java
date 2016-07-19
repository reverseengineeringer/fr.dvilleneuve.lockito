package com.j256.ormlite.misc;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

public abstract class BaseDaoEnabled<T, ID>
{
  protected transient Dao<T, ID> dao;
  
  private void checkForDao()
    throws SQLException
  {
    if (dao == null) {
      throw new SQLException("Dao has not been set on " + getClass() + " object: " + this);
    }
  }
  
  public int create()
    throws SQLException
  {
    checkForDao();
    return dao.create(this);
  }
  
  public int delete()
    throws SQLException
  {
    checkForDao();
    return dao.delete(this);
  }
  
  public ID extractId()
    throws SQLException
  {
    checkForDao();
    return (ID)dao.extractId(this);
  }
  
  public Dao<T, ID> getDao()
  {
    return dao;
  }
  
  public String objectToString()
  {
    try
    {
      checkForDao();
      return dao.objectToString(this);
    }
    catch (SQLException localSQLException)
    {
      throw new IllegalArgumentException(localSQLException);
    }
  }
  
  public boolean objectsEqual(T paramT)
    throws SQLException
  {
    checkForDao();
    return dao.objectsEqual(this, paramT);
  }
  
  public int refresh()
    throws SQLException
  {
    checkForDao();
    return dao.refresh(this);
  }
  
  public void setDao(Dao<T, ID> paramDao)
  {
    dao = paramDao;
  }
  
  public int update()
    throws SQLException
  {
    checkForDao();
    return dao.update(this);
  }
  
  public int updateId(ID paramID)
    throws SQLException
  {
    checkForDao();
    return dao.updateId(this, paramID);
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.misc.BaseDaoEnabled
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */