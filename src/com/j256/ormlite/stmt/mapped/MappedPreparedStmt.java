package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.stmt.ArgumentHolder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedPreparedStmt<T, ID>
  extends BaseMappedQuery<T, ID>
  implements PreparedQuery<T>, PreparedDelete<T>, PreparedUpdate<T>
{
  private final ArgumentHolder[] argHolders;
  private final Long limit;
  private final StatementBuilder.StatementType type;
  
  public MappedPreparedStmt(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType1, FieldType[] paramArrayOfFieldType2, ArgumentHolder[] paramArrayOfArgumentHolder, Long paramLong, StatementBuilder.StatementType paramStatementType)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType1, paramArrayOfFieldType2);
    argHolders = paramArrayOfArgumentHolder;
    limit = paramLong;
    type = paramStatementType;
  }
  
  private CompiledStatement assignStatementArguments(CompiledStatement paramCompiledStatement)
    throws SQLException
  {
    for (;;)
    {
      int i;
      try
      {
        if (limit != null) {
          paramCompiledStatement.setMaxRows(limit.intValue());
        }
        localObject3 = null;
        localObject1 = localObject3;
        if (!logger.isLevelEnabled(Log.Level.TRACE)) {
          break label200;
        }
        localObject1 = localObject3;
        if (argHolders.length <= 0) {
          break label200;
        }
        localObject1 = new Object[argHolders.length];
      }
      finally
      {
        Object localObject3;
        Object localObject1;
        Object localObject4;
        if (0 != 0) {
          continue;
        }
        paramCompiledStatement.close();
      }
      if (i < argHolders.length)
      {
        localObject4 = argHolders[i].getSqlArgValue();
        localObject3 = argFieldTypes[i];
        if (localObject3 == null)
        {
          localObject3 = argHolders[i].getSqlType();
          paramCompiledStatement.setObject(i, localObject4, (SqlType)localObject3);
          if (localObject1 != null) {
            localObject1[i] = localObject4;
          }
        }
        else
        {
          localObject3 = ((FieldType)localObject3).getSqlType();
          continue;
        }
      }
      else
      {
        logger.debug("prepared statement '{}' with {} args", statement, Integer.valueOf(argHolders.length));
        if (localObject1 != null) {
          logger.trace("prepared statement arguments: {}", localObject1);
        }
        if (1 == 0) {
          paramCompiledStatement.close();
        }
        return paramCompiledStatement;
        label200:
        i = 0;
        continue;
      }
      i += 1;
    }
  }
  
  public CompiledStatement compile(DatabaseConnection paramDatabaseConnection, StatementBuilder.StatementType paramStatementType)
    throws SQLException
  {
    return compile(paramDatabaseConnection, paramStatementType, -1);
  }
  
  public CompiledStatement compile(DatabaseConnection paramDatabaseConnection, StatementBuilder.StatementType paramStatementType, int paramInt)
    throws SQLException
  {
    if (type != paramStatementType) {
      throw new SQLException("Could not compile this " + type + " statement since the caller is expecting a " + paramStatementType + " statement.  Check your QueryBuilder methods.");
    }
    return assignStatementArguments(paramDatabaseConnection.compileStatement(statement, paramStatementType, argFieldTypes, paramInt));
  }
  
  public String getStatement()
  {
    return statement;
  }
  
  public StatementBuilder.StatementType getType()
  {
    return type;
  }
  
  public void setArgumentHolderValue(int paramInt, Object paramObject)
    throws SQLException
  {
    if (paramInt < 0) {
      throw new SQLException("argument holder index " + paramInt + " must be >= 0");
    }
    if (argHolders.length <= paramInt) {
      throw new SQLException("argument holder index " + paramInt + " is not valid, only " + argHolders.length + " in statement (index starts at 0)");
    }
    argHolders[paramInt].setValue(paramObject);
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedPreparedStmt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */