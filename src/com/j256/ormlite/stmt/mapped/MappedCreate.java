package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.GeneratedKeyHolder;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedCreate<T, ID>
  extends BaseMappedStatement<T, ID>
{
  private String dataClassName;
  private final String queryNextSequenceStmt;
  private int versionFieldTypeIndex;
  
  private MappedCreate(TableInfo<T, ID> paramTableInfo, String paramString1, FieldType[] paramArrayOfFieldType, String paramString2, int paramInt)
  {
    super(paramTableInfo, paramString1, paramArrayOfFieldType);
    dataClassName = paramTableInfo.getDataClass().getSimpleName();
    queryNextSequenceStmt = paramString2;
    versionFieldTypeIndex = paramInt;
  }
  
  private void assignIdValue(T paramT, Number paramNumber, String paramString, ObjectCache paramObjectCache)
    throws SQLException
  {
    idField.assignIdValue(paramT, paramNumber, paramObjectCache);
    if (logger.isLevelEnabled(Log.Level.DEBUG)) {
      logger.debug("assigned id '{}' from {} to '{}' in {} object", new Object[] { paramNumber, paramString, idField.getFieldName(), dataClassName });
    }
  }
  
  private void assignSequenceId(DatabaseConnection paramDatabaseConnection, T paramT, ObjectCache paramObjectCache)
    throws SQLException
  {
    long l = paramDatabaseConnection.queryForLong(queryNextSequenceStmt);
    logger.debug("queried for sequence {} using stmt: {}", Long.valueOf(l), queryNextSequenceStmt);
    if (l == 0L) {
      throw new SQLException("Should not have returned 0 for stmt: " + queryNextSequenceStmt);
    }
    assignIdValue(paramT, Long.valueOf(l), "sequence", paramObjectCache);
  }
  
  public static <T, ID> MappedCreate<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    appendTableName(paramDatabaseType, localStringBuilder, "INSERT INTO ", paramTableInfo.getTableName());
    int j = 0;
    int i = -1;
    FieldType[] arrayOfFieldType = paramTableInfo.getFieldTypes();
    int i1 = arrayOfFieldType.length;
    int k = 0;
    Object localObject;
    int n;
    int m;
    while (k < i1)
    {
      localObject = arrayOfFieldType[k];
      n = i;
      m = j;
      if (isFieldCreatable(paramDatabaseType, (FieldType)localObject))
      {
        if (((FieldType)localObject).isVersion()) {
          i = j;
        }
        m = j + 1;
        n = i;
      }
      k += 1;
      i = n;
      j = m;
    }
    arrayOfFieldType = new FieldType[j];
    if (j == 0) {
      paramDatabaseType.appendInsertNoColumns(localStringBuilder);
    }
    for (;;)
    {
      paramDatabaseType = buildQueryNextSequence(paramDatabaseType, paramTableInfo.getIdField());
      return new MappedCreate(paramTableInfo, localStringBuilder.toString(), arrayOfFieldType, paramDatabaseType, i);
      k = 1;
      localStringBuilder.append('(');
      localObject = paramTableInfo.getFieldTypes();
      i1 = localObject.length;
      m = 0;
      j = 0;
      while (m < i1)
      {
        FieldType localFieldType = localObject[m];
        if (!isFieldCreatable(paramDatabaseType, localFieldType))
        {
          m += 1;
        }
        else
        {
          if (k != 0) {
            k = 0;
          }
          for (;;)
          {
            appendFieldColumnName(paramDatabaseType, localStringBuilder, localFieldType, null);
            n = j + 1;
            arrayOfFieldType[j] = localFieldType;
            j = n;
            break;
            localStringBuilder.append(",");
          }
        }
      }
      localStringBuilder.append(") VALUES (");
      j = 1;
      localObject = paramTableInfo.getFieldTypes();
      m = localObject.length;
      k = 0;
      while (k < m) {
        if (!isFieldCreatable(paramDatabaseType, localObject[k]))
        {
          k += 1;
        }
        else
        {
          if (j != 0) {
            j = 0;
          }
          for (;;)
          {
            localStringBuilder.append("?");
            break;
            localStringBuilder.append(",");
          }
        }
      }
      localStringBuilder.append(")");
    }
  }
  
  private static String buildQueryNextSequence(DatabaseType paramDatabaseType, FieldType paramFieldType)
  {
    if (paramFieldType == null) {}
    do
    {
      return null;
      paramFieldType = paramFieldType.getGeneratedIdSequence();
    } while (paramFieldType == null);
    StringBuilder localStringBuilder = new StringBuilder(64);
    paramDatabaseType.appendSelectNextValFromSequence(localStringBuilder, paramFieldType);
    return localStringBuilder.toString();
  }
  
  private boolean foreignCollectionsAreAssigned(FieldType[] paramArrayOfFieldType, Object paramObject)
    throws SQLException
  {
    int j = paramArrayOfFieldType.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfFieldType[i].extractJavaFieldValue(paramObject) == null) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private static boolean isFieldCreatable(DatabaseType paramDatabaseType, FieldType paramFieldType)
  {
    if (paramFieldType.isForeignCollection()) {}
    do
    {
      do
      {
        return false;
      } while (paramFieldType.isReadOnly());
      if ((paramDatabaseType.isIdSequenceNeeded()) && (paramDatabaseType.isSelectSequenceBeforeInsert())) {
        return true;
      }
    } while ((paramFieldType.isGeneratedId()) && (!paramFieldType.isSelfGeneratedId()) && (!paramFieldType.isAllowGeneratedIdInsert()));
    return true;
  }
  
  public int insert(DatabaseType paramDatabaseType, DatabaseConnection paramDatabaseConnection, T paramT, ObjectCache paramObjectCache)
    throws SQLException
  {
    FieldType localFieldType = null;
    Object localObject1 = localFieldType;
    int i;
    if (idField != null)
    {
      if ((!idField.isAllowGeneratedIdInsert()) || (idField.isObjectsFieldValueDefault(paramT))) {
        break label148;
      }
      i = 0;
    }
    label89:
    label114:
    label148:
    Object localObject2;
    for (;;)
    {
      if ((idField.isSelfGeneratedId()) && (idField.isGeneratedId()))
      {
        localObject1 = localFieldType;
        if (i != 0)
        {
          idField.assignField(paramT, idField.generateId(), false, paramObjectCache);
          localObject1 = localFieldType;
        }
      }
      try
      {
        if (tableInfo.isForeignAutoCreate())
        {
          paramDatabaseType = tableInfo.getFieldTypes();
          int j = paramDatabaseType.length;
          i = 0;
          if (i < j)
          {
            localFieldType = paramDatabaseType[i];
            boolean bool = localFieldType.isForeignAutoCreate();
            if (!bool) {}
            for (;;)
            {
              i += 1;
              break label114;
              i = 1;
              break;
              if ((idField.isGeneratedIdSequence()) && (paramDatabaseType.isSelectSequenceBeforeInsert()))
              {
                localObject1 = localFieldType;
                if (i == 0) {
                  break label89;
                }
                assignSequenceId(paramDatabaseConnection, paramT, paramObjectCache);
                localObject1 = localFieldType;
                break label89;
              }
              localObject1 = localFieldType;
              if (!idField.isGeneratedId()) {
                break label89;
              }
              localObject1 = localFieldType;
              if (i == 0) {
                break label89;
              }
              localObject1 = new KeyHolder(null);
              break label89;
              localObject2 = localFieldType.extractRawJavaFieldValue(paramT);
              if ((localObject2 != null) && (localFieldType.getForeignIdField().isObjectsFieldValueDefault(localObject2))) {
                localFieldType.createWithForeignDao(localObject2);
              }
            }
          }
        }
        localObject2 = getFieldObjects(paramT);
      }
      catch (SQLException paramDatabaseType)
      {
        throw SqlExceptionUtil.create("Unable to run insert stmt on object " + paramT + ": " + statement, paramDatabaseType);
      }
    }
    localFieldType = null;
    paramDatabaseType = localFieldType;
    if (versionFieldTypeIndex >= 0)
    {
      paramDatabaseType = localFieldType;
      if (localObject2[versionFieldTypeIndex] == null)
      {
        localFieldType = argFieldTypes[versionFieldTypeIndex];
        paramDatabaseType = localFieldType.moveToNextValue(null);
        localObject2[versionFieldTypeIndex] = localFieldType.convertJavaFieldToSqlArgValue(paramDatabaseType);
      }
    }
    try
    {
      i = paramDatabaseConnection.insert(statement, (Object[])localObject2, argFieldTypes, (GeneratedKeyHolder)localObject1);
      logger.debug("insert data with statement '{}' and {} args, changed {} rows", statement, Integer.valueOf(localObject2.length), Integer.valueOf(i));
      if (localObject2.length > 0) {
        logger.trace("insert arguments: {}", localObject2);
      }
      if (i <= 0) {
        break label598;
      }
      if (paramDatabaseType != null) {
        argFieldTypes[versionFieldTypeIndex].assignField(paramT, paramDatabaseType, false, null);
      }
      if (localObject1 == null) {
        break label556;
      }
      paramDatabaseType = ((KeyHolder)localObject1).getKey();
      if (paramDatabaseType == null) {
        throw new SQLException("generated-id key was not set by the update call");
      }
    }
    catch (SQLException paramDatabaseType)
    {
      logger.debug("insert data with statement '{}' and {} args, threw exception: {}", statement, Integer.valueOf(localObject2.length), paramDatabaseType);
      if (localObject2.length > 0) {
        logger.trace("insert arguments: {}", localObject2);
      }
      throw paramDatabaseType;
    }
    if (paramDatabaseType.longValue() == 0L) {
      throw new SQLException("generated-id key must not be 0 value");
    }
    assignIdValue(paramT, paramDatabaseType, "keyholder", paramObjectCache);
    label556:
    if ((paramObjectCache != null) && (foreignCollectionsAreAssigned(tableInfo.getForeignCollections(), paramT)))
    {
      paramDatabaseType = idField.extractJavaFieldValue(paramT);
      paramObjectCache.put(clazz, paramDatabaseType, paramT);
    }
    label598:
    return i;
  }
  
  private static class KeyHolder
    implements GeneratedKeyHolder
  {
    Number key;
    
    public void addKey(Number paramNumber)
      throws SQLException
    {
      if (key == null)
      {
        key = paramNumber;
        return;
      }
      throw new SQLException("generated key has already been set to " + key + ", now set to " + paramNumber);
    }
    
    public Number getKey()
    {
      return key;
    }
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedCreate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */