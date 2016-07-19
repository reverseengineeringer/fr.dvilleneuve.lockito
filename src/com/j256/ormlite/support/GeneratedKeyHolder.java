package com.j256.ormlite.support;

import java.sql.SQLException;

public abstract interface GeneratedKeyHolder
{
  public abstract void addKey(Number paramNumber)
    throws SQLException;
}

/* Location:
 * Qualified Name:     com.j256.ormlite.support.GeneratedKeyHolder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */