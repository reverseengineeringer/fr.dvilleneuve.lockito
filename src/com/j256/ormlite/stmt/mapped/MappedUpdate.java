package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedUpdate<T, ID>
  extends BaseMappedStatement<T, ID>
{
  private final FieldType versionFieldType;
  private final int versionFieldTypeIndex;
  
  private MappedUpdate(TableInfo<T, ID> paramTableInfo, String paramString, FieldType[] paramArrayOfFieldType, FieldType paramFieldType, int paramInt)
  {
    super(paramTableInfo, paramString, paramArrayOfFieldType);
    versionFieldType = paramFieldType;
    versionFieldTypeIndex = paramInt;
  }
  
  public static <T, ID> MappedUpdate<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo)
    throws SQLException
  {
    FieldType localFieldType = paramTableInfo.getIdField();
    if (localFieldType == null) {
      throw new SQLException("Cannot update " + paramTableInfo.getDataClass() + " because it doesn't have an id field");
    }
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendTableName(paramDatabaseType, localStringBuilder, "UPDATE ", paramTableInfo.getTableName());
    int i1 = 1;
    int j = 0;
    Object localObject1 = null;
    int i = -1;
    FieldType[] arrayOfFieldType = paramTableInfo.getFieldTypes();
    int i2 = arrayOfFieldType.length;
    int k = 0;
    int n;
    while (k < i2)
    {
      localObject3 = arrayOfFieldType[k];
      localObject2 = localObject1;
      n = i;
      m = j;
      if (isFieldUpdatable((FieldType)localObject3, localFieldType))
      {
        if (((FieldType)localObject3).isVersion())
        {
          localObject1 = localObject3;
          i = j;
        }
        m = j + 1;
        n = i;
        localObject2 = localObject1;
      }
      k += 1;
      localObject1 = localObject2;
      i = n;
      j = m;
    }
    k = j + 1;
    j = k;
    if (localObject1 != null) {
      j = k + 1;
    }
    Object localObject2 = new FieldType[j];
    Object localObject3 = paramTableInfo.getFieldTypes();
    i2 = localObject3.length;
    int m = 0;
    j = 0;
    k = i1;
    while (m < i2)
    {
      arrayOfFieldType = localObject3[m];
      if (!isFieldUpdatable(arrayOfFieldType, localFieldType))
      {
        m += 1;
      }
      else
      {
        if (k != 0)
        {
          localStringBuilder.append("SET ");
          k = 0;
        }
        for (;;)
        {
          appendFieldColumnName(paramDatabaseType, localStringBuilder, arrayOfFieldType, null);
          n = j + 1;
          localObject2[j] = arrayOfFieldType;
          localStringBuilder.append("= ?");
          j = n;
          break;
          localStringBuilder.append(", ");
        }
      }
    }
    localStringBuilder.append(' ');
    appendWhereFieldEq(paramDatabaseType, localFieldType, localStringBuilder, null);
    k = j + 1;
    localObject2[j] = localFieldType;
    if (localObject1 != null)
    {
      localStringBuilder.append(" AND ");
      appendFieldColumnName(paramDatabaseType, localStringBuilder, (FieldType)localObject1, null);
      localStringBuilder.append("= ?");
      localObject2[k] = localObject1;
    }
    return new MappedUpdate(paramTableInfo, localStringBuilder.toString(), (FieldType[])localObject2, (FieldType)localObject1, i);
  }
  
  private static boolean isFieldUpdatable(FieldType paramFieldType1, FieldType paramFieldType2)
  {
    return (paramFieldType1 != paramFieldType2) && (!paramFieldType1.isForeignCollection()) && (!paramFieldType1.isReadOnly());
  }
  
  public int update(DatabaseConnection paramDatabaseConnection, T paramT, ObjectCache paramObjectCache)
    throws SQLException
  {
    for (;;)
    {
      int j;
      int i;
      try
      {
        if (argFieldTypes.length <= 1) {
          return 0;
        }
        Object[] arrayOfObject = getFieldObjects(paramT);
        Object localObject1 = null;
        if (versionFieldType != null)
        {
          localObject1 = versionFieldType.extractJavaFieldValue(paramT);
          localObject1 = versionFieldType.moveToNextValue(localObject1);
          arrayOfObject[versionFieldTypeIndex] = versionFieldType.convertJavaFieldToSqlArgValue(localObject1);
        }
        j = paramDatabaseConnection.update(statement, arrayOfObject, argFieldTypes);
        if (j > 0)
        {
          if (localObject1 != null) {
            versionFieldType.assignField(paramT, localObject1, false, null);
          }
          if (paramObjectCache != null)
          {
            paramDatabaseConnection = idField.extractJavaFieldValue(paramT);
            paramDatabaseConnection = paramObjectCache.get(clazz, paramDatabaseConnection);
            if ((paramDatabaseConnection != null) && (paramDatabaseConnection != paramT))
            {
              localObject1 = tableInfo.getFieldTypes();
              int k = localObject1.length;
              i = 0;
              if (i < k)
              {
                Object localObject2 = localObject1[i];
                if (localObject2 == idField) {
                  break label278;
                }
                ((FieldType)localObject2).assignField(paramDatabaseConnection, ((FieldType)localObject2).extractJavaFieldValue(paramT), false, paramObjectCache);
                break label278;
              }
            }
          }
        }
        logger.debug("update data with statement '{}' and {} args, changed {} rows", statement, Integer.valueOf(arrayOfObject.length), Integer.valueOf(j));
        if (arrayOfObject.length > 0)
        {
          logger.trace("update arguments: {}", arrayOfObject);
          return j;
        }
      }
      catch (SQLException paramDatabaseConnection)
      {
        throw SqlExceptionUtil.create("Unable to run update stmt on object " + paramT + ": " + statement, paramDatabaseConnection);
      }
      return j;
      label278:
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedUpdate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */