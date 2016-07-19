package com.j256.ormlite.dao;

import java.sql.SQLException;

public abstract interface RawRowMapper<T>
{
  public abstract T mapRow(String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SQLException;
}

/* Location:
 * Qualified Name:     com.j256.ormlite.dao.RawRowMapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */