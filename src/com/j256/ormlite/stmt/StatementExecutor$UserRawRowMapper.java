package com.j256.ormlite.stmt;

import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.DatabaseResults;
import java.sql.SQLException;

class StatementExecutor$UserRawRowMapper<UO>
  implements GenericRowMapper<UO>
{
  private String[] columnNames;
  private final RawRowMapper<UO> mapper;
  private final GenericRowMapper<String[]> stringRowMapper;
  
  public StatementExecutor$UserRawRowMapper(RawRowMapper<UO> paramRawRowMapper, GenericRowMapper<String[]> paramGenericRowMapper)
  {
    mapper = paramRawRowMapper;
    stringRowMapper = paramGenericRowMapper;
  }
  
  private String[] getColumnNames(DatabaseResults paramDatabaseResults)
    throws SQLException
  {
    if (columnNames != null) {
      return columnNames;
    }
    columnNames = paramDatabaseResults.getColumnNames();
    return columnNames;
  }
  
  public UO mapRow(DatabaseResults paramDatabaseResults)
    throws SQLException
  {
    String[] arrayOfString = (String[])stringRowMapper.mapRow(paramDatabaseResults);
    return (UO)mapper.mapRow(getColumnNames(paramDatabaseResults), arrayOfString);
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.stmt.StatementExecutor.UserRawRowMapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */