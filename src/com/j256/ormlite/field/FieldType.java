package com.j256.ormlite.field;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.LazyForeignCollection;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.types.VoidType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.mapped.MappedQueryForId;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableInfo;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public class FieldType
{
  private static boolean DEFAULT_VALUE_BOOLEAN = false;
  private static byte DEFAULT_VALUE_BYTE = 0;
  private static char DEFAULT_VALUE_CHAR = '\000';
  private static double DEFAULT_VALUE_DOUBLE = 0.0D;
  private static float DEFAULT_VALUE_FLOAT = 0.0F;
  private static int DEFAULT_VALUE_INT = 0;
  private static long DEFAULT_VALUE_LONG = 0L;
  private static short DEFAULT_VALUE_SHORT = 0;
  public static final String FOREIGN_ID_FIELD_SUFFIX = "_id";
  private static final ThreadLocal<LevelCounters> threadLevelCounters = new ThreadLocal()
  {
    protected FieldType.LevelCounters initialValue()
    {
      return new FieldType.LevelCounters(null);
    }
  };
  private final String columnName;
  private final ConnectionSource connectionSource;
  private DataPersister dataPersister;
  private Object dataTypeConfigObj;
  private Object defaultValue;
  private final Field field;
  private final DatabaseFieldConfig fieldConfig;
  private FieldConverter fieldConverter;
  private final Method fieldGetMethod;
  private final Method fieldSetMethod;
  private BaseDaoImpl<?, ?> foreignDao;
  private FieldType foreignFieldType;
  private FieldType foreignIdField;
  private TableInfo<?, ?> foreignTableInfo;
  private final String generatedIdSequence;
  private final boolean isGeneratedId;
  private final boolean isId;
  private MappedQueryForId<Object, Object> mappedQueryForId;
  private final Class<?> parentClass;
  private final String tableName;
  
  public FieldType(ConnectionSource paramConnectionSource, String paramString, Field paramField, DatabaseFieldConfig paramDatabaseFieldConfig, Class<?> paramClass)
    throws SQLException
  {
    connectionSource = paramConnectionSource;
    tableName = paramString;
    DatabaseType localDatabaseType = paramConnectionSource.getDatabaseType();
    field = paramField;
    parentClass = paramClass;
    paramDatabaseFieldConfig.postProcess();
    Object localObject = paramField.getType();
    if (paramDatabaseFieldConfig.getDataPersister() == null)
    {
      paramClass = paramDatabaseFieldConfig.getPersisterClass();
      if ((paramClass == null) || (paramClass == VoidType.class)) {
        paramConnectionSource = DataPersisterManager.lookupForField(paramField);
      }
    }
    String str;
    label300:
    do
    {
      for (;;)
      {
        paramClass = paramDatabaseFieldConfig.getForeignColumnName();
        str = paramField.getName();
        if ((!paramDatabaseFieldConfig.isForeign()) && (!paramDatabaseFieldConfig.isForeignAutoRefresh()) && (paramClass == null)) {
          break label574;
        }
        if ((paramConnectionSource == null) || (!paramConnectionSource.isPrimitive())) {
          break label449;
        }
        throw new IllegalArgumentException("Field " + this + " is a primitive class " + localObject + " but marked as foreign");
        try
        {
          paramConnectionSource = paramClass.getDeclaredMethod("getSingleton", new Class[0]);
          try
          {
            paramConnectionSource = (DataPersister)paramConnectionSource;
          }
          catch (Exception paramConnectionSource)
          {
            throw SqlExceptionUtil.create("Could not cast result of static getSingleton method to DataPersister from class " + paramClass, paramConnectionSource);
          }
        }
        catch (Exception paramConnectionSource)
        {
          try
          {
            paramConnectionSource = paramConnectionSource.invoke(null, new Object[0]);
            if (paramConnectionSource != null) {
              break label300;
            }
            throw new SQLException("Static getSingleton method should not return null on class " + paramClass);
          }
          catch (InvocationTargetException paramConnectionSource)
          {
            throw SqlExceptionUtil.create("Could not run getSingleton method on class " + paramClass, paramConnectionSource.getTargetException());
          }
          catch (Exception paramConnectionSource)
          {
            throw SqlExceptionUtil.create("Could not run getSingleton method on class " + paramClass, paramConnectionSource);
          }
          paramConnectionSource = paramConnectionSource;
          throw SqlExceptionUtil.create("Could not find getSingleton static method on class " + paramClass, paramConnectionSource);
        }
      }
      paramClass = paramDatabaseFieldConfig.getDataPersister();
      paramConnectionSource = paramClass;
    } while (paramClass.isValidForField(paramField));
    paramConnectionSource = new StringBuilder();
    paramConnectionSource.append("Field class ").append(((Class)localObject).getName());
    paramConnectionSource.append(" for field ").append(this);
    paramConnectionSource.append(" is not valid for type ").append(paramClass);
    paramString = paramClass.getPrimaryClass();
    if (paramString != null) {
      paramConnectionSource.append(", maybe should be " + paramString);
    }
    throw new IllegalArgumentException(paramConnectionSource.toString());
    label449:
    if (paramClass == null) {
      paramClass = str + "_id";
    }
    while (ForeignCollection.class.isAssignableFrom((Class)localObject))
    {
      throw new SQLException("Field '" + paramField.getName() + "' in class " + localObject + "' should use the @" + ForeignCollectionField.class.getSimpleName() + " annotation not foreign=true");
      paramClass = str + "_" + paramClass;
      continue;
      label574:
      if (paramDatabaseFieldConfig.isForeignCollection())
      {
        if ((localObject != Collection.class) && (!ForeignCollection.class.isAssignableFrom((Class)localObject))) {
          throw new SQLException("Field class for '" + paramField.getName() + "' must be of class " + ForeignCollection.class.getSimpleName() + " or Collection.");
        }
        localObject = paramField.getGenericType();
        if (!(localObject instanceof ParameterizedType)) {
          throw new SQLException("Field class for '" + paramField.getName() + "' must be a parameterized Collection.");
        }
        paramClass = str;
        if (((ParameterizedType)localObject).getActualTypeArguments().length == 0) {
          throw new SQLException("Field class for '" + paramField.getName() + "' must be a parameterized Collection with at least 1 type.");
        }
      }
      else
      {
        paramClass = str;
        if (paramConnectionSource == null)
        {
          paramClass = str;
          if (!paramDatabaseFieldConfig.isForeignCollection())
          {
            if (byte[].class.isAssignableFrom((Class)localObject)) {
              throw new SQLException("ORMLite does not know how to store " + localObject + " for field '" + paramField.getName() + "'. byte[] fields must specify dataType=DataType.BYTE_ARRAY or SERIALIZABLE");
            }
            if (Serializable.class.isAssignableFrom((Class)localObject)) {
              throw new SQLException("ORMLite does not know how to store " + localObject + " for field '" + paramField.getName() + "'.  Use another class, custom persister, or to serialize it use " + "dataType=DataType.SERIALIZABLE");
            }
            throw new IllegalArgumentException("ORMLite does not know how to store " + localObject + " for field " + paramField.getName() + ". Use another class or a custom persister.");
          }
        }
      }
    }
    if (paramDatabaseFieldConfig.getColumnName() == null) {}
    for (columnName = paramClass;; columnName = paramDatabaseFieldConfig.getColumnName())
    {
      fieldConfig = paramDatabaseFieldConfig;
      if (!paramDatabaseFieldConfig.isId()) {
        break label1105;
      }
      if ((!paramDatabaseFieldConfig.isGeneratedId()) && (paramDatabaseFieldConfig.getGeneratedIdSequence() == null)) {
        break;
      }
      throw new IllegalArgumentException("Must specify one of id, generatedId, and generatedIdSequence with " + paramField.getName());
    }
    isId = true;
    isGeneratedId = false;
    generatedIdSequence = null;
    while ((isId) && ((paramDatabaseFieldConfig.isForeign()) || (paramDatabaseFieldConfig.isForeignAutoRefresh())))
    {
      throw new IllegalArgumentException("Id field " + paramField.getName() + " cannot also be a foreign object");
      label1105:
      if (paramDatabaseFieldConfig.isGeneratedId())
      {
        if (paramDatabaseFieldConfig.getGeneratedIdSequence() != null) {
          throw new IllegalArgumentException("Must specify one of id, generatedId, and generatedIdSequence with " + paramField.getName());
        }
        isId = true;
        isGeneratedId = true;
        if (localDatabaseType.isIdSequenceNeeded()) {
          generatedIdSequence = localDatabaseType.generateIdSequenceName(paramString, this);
        } else {
          generatedIdSequence = null;
        }
      }
      else if (paramDatabaseFieldConfig.getGeneratedIdSequence() != null)
      {
        isId = true;
        isGeneratedId = true;
        paramClass = paramDatabaseFieldConfig.getGeneratedIdSequence();
        paramString = paramClass;
        if (localDatabaseType.isEntityNamesMustBeUpCase()) {
          paramString = paramClass.toUpperCase();
        }
        generatedIdSequence = paramString;
      }
      else
      {
        isId = false;
        isGeneratedId = false;
        generatedIdSequence = null;
      }
    }
    if (paramDatabaseFieldConfig.isUseGetSet())
    {
      fieldGetMethod = DatabaseFieldConfig.findGetMethod(paramField, true);
      fieldSetMethod = DatabaseFieldConfig.findSetMethod(paramField, true);
    }
    while ((paramDatabaseFieldConfig.isAllowGeneratedIdInsert()) && (!paramDatabaseFieldConfig.isGeneratedId()))
    {
      throw new IllegalArgumentException("Field " + paramField.getName() + " must be a generated-id if allowGeneratedIdInsert = true");
      if (!paramField.isAccessible()) {}
      try
      {
        field.setAccessible(true);
        fieldGetMethod = null;
        fieldSetMethod = null;
      }
      catch (SecurityException paramConnectionSource)
      {
        throw new IllegalArgumentException("Could not open access to field " + paramField.getName() + ".  You may have to set useGetSet=true to fix.");
      }
    }
    if ((paramDatabaseFieldConfig.isForeignAutoRefresh()) && (!paramDatabaseFieldConfig.isForeign())) {
      throw new IllegalArgumentException("Field " + paramField.getName() + " must have foreign = true if foreignAutoRefresh = true");
    }
    if ((paramDatabaseFieldConfig.isForeignAutoCreate()) && (!paramDatabaseFieldConfig.isForeign())) {
      throw new IllegalArgumentException("Field " + paramField.getName() + " must have foreign = true if foreignAutoCreate = true");
    }
    if ((paramDatabaseFieldConfig.getForeignColumnName() != null) && (!paramDatabaseFieldConfig.isForeign())) {
      throw new IllegalArgumentException("Field " + paramField.getName() + " must have foreign = true if foreignColumnName is set");
    }
    if ((paramDatabaseFieldConfig.isVersion()) && ((paramConnectionSource == null) || (!paramConnectionSource.isValidForVersion()))) {
      throw new IllegalArgumentException("Field " + paramField.getName() + " is not a valid type to be a version field");
    }
    if ((paramDatabaseFieldConfig.getMaxForeignAutoRefreshLevel() > 0) && (!paramDatabaseFieldConfig.isForeignAutoRefresh())) {
      throw new IllegalArgumentException("Field " + paramField.getName() + " has maxForeignAutoRefreshLevel set but not foreignAutoRefresh is false");
    }
    assignDataType(localDatabaseType, paramConnectionSource);
  }
  
  private void assignDataType(DatabaseType paramDatabaseType, DataPersister paramDataPersister)
    throws SQLException
  {
    dataPersister = paramDataPersister;
    if (paramDataPersister == null)
    {
      if ((!fieldConfig.isForeign()) && (!fieldConfig.isForeignCollection())) {
        throw new SQLException("Data persister for field " + this + " is null but the field is not a foreign or foreignCollection");
      }
    }
    else
    {
      fieldConverter = paramDatabaseType.getFieldConverter(paramDataPersister);
      if ((isGeneratedId) && (!paramDataPersister.isValidGeneratedType()))
      {
        paramDatabaseType = new StringBuilder();
        paramDatabaseType.append("Generated-id field '").append(field.getName());
        paramDatabaseType.append("' in ").append(field.getDeclaringClass().getSimpleName());
        paramDatabaseType.append(" can't be type ").append(dataPersister.getSqlType());
        paramDatabaseType.append(".  Must be one of: ");
        paramDataPersister = DataType.values();
        int j = paramDataPersister.length;
        int i = 0;
        while (i < j)
        {
          Object localObject = paramDataPersister[i];
          DataPersister localDataPersister = ((DataType)localObject).getDataPersister();
          if ((localDataPersister != null) && (localDataPersister.isValidGeneratedType())) {
            paramDatabaseType.append(localObject).append(' ');
          }
          i += 1;
        }
        throw new IllegalArgumentException(paramDatabaseType.toString());
      }
      if ((fieldConfig.isThrowIfNull()) && (!paramDataPersister.isPrimitive())) {
        throw new SQLException("Field " + field.getName() + " must be a primitive if set with throwIfNull");
      }
      if ((isId) && (!paramDataPersister.isAppropriateId())) {
        throw new SQLException("Field '" + field.getName() + "' is of data type " + paramDataPersister + " which cannot be the ID field");
      }
      dataTypeConfigObj = paramDataPersister.makeConfigObject(this);
      paramDatabaseType = fieldConfig.getDefaultValue();
      if (paramDatabaseType != null) {
        break label391;
      }
      defaultValue = null;
    }
    return;
    label391:
    if (isGeneratedId) {
      throw new SQLException("Field '" + field.getName() + "' cannot be a generatedId and have a default value '" + paramDatabaseType + "'");
    }
    defaultValue = fieldConverter.parseDefaultString(this, paramDatabaseType);
  }
  
  public static FieldType createFieldType(ConnectionSource paramConnectionSource, String paramString, Field paramField, Class<?> paramClass)
    throws SQLException
  {
    DatabaseFieldConfig localDatabaseFieldConfig = DatabaseFieldConfig.fromField(paramConnectionSource.getDatabaseType(), paramString, paramField);
    if (localDatabaseFieldConfig == null) {
      return null;
    }
    return new FieldType(paramConnectionSource, paramString, paramField, localDatabaseFieldConfig, paramClass);
  }
  
  private FieldType findForeignFieldType(Class<?> paramClass1, Class<?> paramClass2, BaseDaoImpl<?, ?> paramBaseDaoImpl)
    throws SQLException
  {
    String str = fieldConfig.getForeignCollectionForeignFieldName();
    paramBaseDaoImpl = paramBaseDaoImpl.getTableInfo().getFieldTypes();
    int j = paramBaseDaoImpl.length;
    int i = 0;
    FieldType localFieldType;
    while (i < j)
    {
      localFieldType = paramBaseDaoImpl[i];
      if ((localFieldType.getType() == paramClass2) && ((str == null) || (localFieldType.getField().getName().equals(str))))
      {
        if ((fieldConfig.isForeign()) || (fieldConfig.isForeignAutoRefresh())) {
          break label255;
        }
        throw new SQLException("Foreign collection object " + paramClass1 + " for field '" + field.getName() + "' contains a field of class " + paramClass2 + " but it's not foreign");
      }
      i += 1;
    }
    paramBaseDaoImpl = new StringBuilder();
    paramBaseDaoImpl.append("Foreign collection class ").append(paramClass1.getName());
    paramBaseDaoImpl.append(" for field '").append(field.getName()).append("' column-name does not contain a foreign field");
    if (str != null) {
      paramBaseDaoImpl.append(" named '").append(str).append('\'');
    }
    paramBaseDaoImpl.append(" of class ").append(paramClass2.getName());
    throw new SQLException(paramBaseDaoImpl.toString());
    label255:
    return localFieldType;
  }
  
  private boolean isFieldValueDefault(Object paramObject)
  {
    if (paramObject == null) {
      return true;
    }
    return paramObject.equals(getJavaDefaultValueDefault());
  }
  
  /* Error */
  public void assignField(Object paramObject1, Object paramObject2, boolean paramBoolean, ObjectCache paramObjectCache)
    throws SQLException
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 5
    //   3: aload_0
    //   4: getfield 524	com/j256/ormlite/field/FieldType:foreignIdField	Lcom/j256/ormlite/field/FieldType;
    //   7: ifnull +54 -> 61
    //   10: aload_2
    //   11: astore 5
    //   13: aload_2
    //   14: ifnull +47 -> 61
    //   17: aload_0
    //   18: aload_1
    //   19: invokevirtual 528	com/j256/ormlite/field/FieldType:extractJavaFieldValue	(Ljava/lang/Object;)Ljava/lang/Object;
    //   22: astore 5
    //   24: aload 5
    //   26: ifnull +13 -> 39
    //   29: aload 5
    //   31: aload_2
    //   32: invokevirtual 518	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   35: ifeq +4 -> 39
    //   38: return
    //   39: aload_0
    //   40: getfield 530	com/j256/ormlite/field/FieldType:foreignDao	Lcom/j256/ormlite/dao/BaseDaoImpl;
    //   43: invokevirtual 534	com/j256/ormlite/dao/BaseDaoImpl:getObjectCache	()Lcom/j256/ormlite/dao/ObjectCache;
    //   46: astore 5
    //   48: aload 5
    //   50: ifnonnull +66 -> 116
    //   53: aconst_null
    //   54: astore 5
    //   56: aload 5
    //   58: ifnull +75 -> 133
    //   61: aload_0
    //   62: getfield 343	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   65: ifnonnull +347 -> 412
    //   68: aload_0
    //   69: getfield 104	com/j256/ormlite/field/FieldType:field	Ljava/lang/reflect/Field;
    //   72: aload_1
    //   73: aload 5
    //   75: invokevirtual 538	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   78: return
    //   79: astore_1
    //   80: new 155	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   87: ldc_w 540
    //   90: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: aload 5
    //   95: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   98: ldc_w 542
    //   101: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: aload_0
    //   105: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   108: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: aload_1
    //   112: invokestatic 200	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   115: athrow
    //   116: aload 5
    //   118: aload_0
    //   119: invokevirtual 489	com/j256/ormlite/field/FieldType:getType	()Ljava/lang/Class;
    //   122: aload_2
    //   123: invokeinterface 548 3 0
    //   128: astore 5
    //   130: goto -74 -> 56
    //   133: aload_2
    //   134: astore 5
    //   136: iload_3
    //   137: ifne -76 -> 61
    //   140: getstatic 81	com/j256/ormlite/field/FieldType:threadLevelCounters	Ljava/lang/ThreadLocal;
    //   143: invokevirtual 552	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   146: checkcast 8	com/j256/ormlite/field/FieldType$LevelCounters
    //   149: astore 5
    //   151: aload 5
    //   153: getfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   156: ifne +15 -> 171
    //   159: aload 5
    //   161: aload_0
    //   162: getfield 293	com/j256/ormlite/field/FieldType:fieldConfig	Lcom/j256/ormlite/field/DatabaseFieldConfig;
    //   165: invokevirtual 380	com/j256/ormlite/field/DatabaseFieldConfig:getMaxForeignAutoRefreshLevel	()I
    //   168: putfield 558	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevelMax	I
    //   171: aload 5
    //   173: getfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   176: aload 5
    //   178: getfield 558	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevelMax	I
    //   181: if_icmplt +34 -> 215
    //   184: aload_0
    //   185: getfield 560	com/j256/ormlite/field/FieldType:foreignTableInfo	Lcom/j256/ormlite/table/TableInfo;
    //   188: invokevirtual 563	com/j256/ormlite/table/TableInfo:createObject	()Ljava/lang/Object;
    //   191: astore 5
    //   193: aload_0
    //   194: getfield 524	com/j256/ormlite/field/FieldType:foreignIdField	Lcom/j256/ormlite/field/FieldType;
    //   197: aload 5
    //   199: aload_2
    //   200: iconst_0
    //   201: aload 4
    //   203: invokevirtual 565	com/j256/ormlite/field/FieldType:assignField	(Ljava/lang/Object;Ljava/lang/Object;ZLcom/j256/ormlite/dao/ObjectCache;)V
    //   206: aload 5
    //   208: astore_2
    //   209: aload_2
    //   210: astore 5
    //   212: goto -151 -> 61
    //   215: aload_0
    //   216: getfield 567	com/j256/ormlite/field/FieldType:mappedQueryForId	Lcom/j256/ormlite/stmt/mapped/MappedQueryForId;
    //   219: ifnonnull +30 -> 249
    //   222: aload_0
    //   223: aload_0
    //   224: getfield 94	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   227: invokeinterface 102 1 0
    //   232: aload_0
    //   233: getfield 530	com/j256/ormlite/field/FieldType:foreignDao	Lcom/j256/ormlite/dao/BaseDaoImpl;
    //   236: invokevirtual 482	com/j256/ormlite/dao/BaseDaoImpl:getTableInfo	()Lcom/j256/ormlite/table/TableInfo;
    //   239: aload_0
    //   240: getfield 524	com/j256/ormlite/field/FieldType:foreignIdField	Lcom/j256/ormlite/field/FieldType;
    //   243: invokestatic 573	com/j256/ormlite/stmt/mapped/MappedQueryForId:build	(Lcom/j256/ormlite/db/DatabaseType;Lcom/j256/ormlite/table/TableInfo;Lcom/j256/ormlite/field/FieldType;)Lcom/j256/ormlite/stmt/mapped/MappedQueryForId;
    //   246: putfield 567	com/j256/ormlite/field/FieldType:mappedQueryForId	Lcom/j256/ormlite/stmt/mapped/MappedQueryForId;
    //   249: aload 5
    //   251: aload 5
    //   253: getfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   256: iconst_1
    //   257: iadd
    //   258: putfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   261: aload_0
    //   262: getfield 94	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   265: invokeinterface 577 1 0
    //   270: astore 6
    //   272: aload_0
    //   273: getfield 567	com/j256/ormlite/field/FieldType:mappedQueryForId	Lcom/j256/ormlite/stmt/mapped/MappedQueryForId;
    //   276: aload 6
    //   278: aload_2
    //   279: aload 4
    //   281: invokevirtual 581	com/j256/ormlite/stmt/mapped/MappedQueryForId:execute	(Lcom/j256/ormlite/support/DatabaseConnection;Ljava/lang/Object;Lcom/j256/ormlite/dao/ObjectCache;)Ljava/lang/Object;
    //   284: astore 4
    //   286: aload_0
    //   287: getfield 94	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   290: aload 6
    //   292: invokeinterface 585 2 0
    //   297: aload 5
    //   299: aload 5
    //   301: getfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   304: iconst_1
    //   305: isub
    //   306: putfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   309: aload 4
    //   311: astore_2
    //   312: aload 5
    //   314: getfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   317: ifgt -108 -> 209
    //   320: getstatic 81	com/j256/ormlite/field/FieldType:threadLevelCounters	Ljava/lang/ThreadLocal;
    //   323: invokevirtual 588	java/lang/ThreadLocal:remove	()V
    //   326: aload 4
    //   328: astore_2
    //   329: goto -120 -> 209
    //   332: astore_1
    //   333: aload_0
    //   334: getfield 94	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   337: aload 6
    //   339: invokeinterface 585 2 0
    //   344: aload_1
    //   345: athrow
    //   346: astore_1
    //   347: aload 5
    //   349: aload 5
    //   351: getfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   354: iconst_1
    //   355: isub
    //   356: putfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   359: aload 5
    //   361: getfield 555	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshLevel	I
    //   364: ifgt +9 -> 373
    //   367: getstatic 81	com/j256/ormlite/field/FieldType:threadLevelCounters	Ljava/lang/ThreadLocal;
    //   370: invokevirtual 588	java/lang/ThreadLocal:remove	()V
    //   373: aload_1
    //   374: athrow
    //   375: astore_1
    //   376: new 155	java/lang/StringBuilder
    //   379: dup
    //   380: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   383: ldc_w 540
    //   386: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: aload 5
    //   391: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   394: ldc_w 542
    //   397: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: aload_0
    //   401: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   404: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   407: aload_1
    //   408: invokestatic 200	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   411: athrow
    //   412: aload_0
    //   413: getfield 343	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   416: aload_1
    //   417: iconst_1
    //   418: anewarray 4	java/lang/Object
    //   421: dup
    //   422: iconst_0
    //   423: aload 5
    //   425: aastore
    //   426: invokevirtual 189	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   429: pop
    //   430: return
    //   431: astore_1
    //   432: new 155	java/lang/StringBuilder
    //   435: dup
    //   436: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   439: ldc_w 590
    //   442: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: aload_0
    //   446: getfield 343	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   449: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   452: ldc_w 592
    //   455: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: aload 5
    //   460: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   463: ldc_w 594
    //   466: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: aload_0
    //   470: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   473: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   476: aload_1
    //   477: invokestatic 200	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   480: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	481	0	this	FieldType
    //   0	481	1	paramObject1	Object
    //   0	481	2	paramObject2	Object
    //   0	481	3	paramBoolean	boolean
    //   0	481	4	paramObjectCache	ObjectCache
    //   1	458	5	localObject	Object
    //   270	68	6	localDatabaseConnection	com.j256.ormlite.support.DatabaseConnection
    // Exception table:
    //   from	to	target	type
    //   68	78	79	java/lang/IllegalArgumentException
    //   272	286	332	finally
    //   261	272	346	finally
    //   286	297	346	finally
    //   333	346	346	finally
    //   68	78	375	java/lang/IllegalAccessException
    //   412	430	431	java/lang/Exception
  }
  
  public Object assignIdValue(Object paramObject, Number paramNumber, ObjectCache paramObjectCache)
    throws SQLException
  {
    paramNumber = dataPersister.convertIdNumber(paramNumber);
    if (paramNumber == null) {
      throw new SQLException("Invalid class " + dataPersister + " for sequence-id " + this);
    }
    assignField(paramObject, paramNumber, false, paramObjectCache);
    return paramNumber;
  }
  
  public <FT, FID> BaseForeignCollection<FT, FID> buildForeignCollection(Object paramObject, FID paramFID)
    throws SQLException
  {
    if (foreignFieldType == null) {
      return null;
    }
    BaseDaoImpl localBaseDaoImpl = foreignDao;
    if (!fieldConfig.isForeignCollectionEager()) {
      return new LazyForeignCollection(localBaseDaoImpl, paramObject, paramFID, foreignFieldType, fieldConfig.getForeignCollectionOrderColumnName(), fieldConfig.isForeignCollectionOrderAscending());
    }
    LevelCounters localLevelCounters = (LevelCounters)threadLevelCounters.get();
    if (foreignCollectionLevel == 0) {
      foreignCollectionLevelMax = fieldConfig.getForeignCollectionMaxEagerLevel();
    }
    if (foreignCollectionLevel >= foreignCollectionLevelMax) {
      return new LazyForeignCollection(localBaseDaoImpl, paramObject, paramFID, foreignFieldType, fieldConfig.getForeignCollectionOrderColumnName(), fieldConfig.isForeignCollectionOrderAscending());
    }
    foreignCollectionLevel += 1;
    try
    {
      paramObject = new EagerForeignCollection(localBaseDaoImpl, paramObject, paramFID, foreignFieldType, fieldConfig.getForeignCollectionOrderColumnName(), fieldConfig.isForeignCollectionOrderAscending());
      return (BaseForeignCollection<FT, FID>)paramObject;
    }
    finally
    {
      foreignCollectionLevel -= 1;
    }
  }
  
  public void configDaoInformation(ConnectionSource paramConnectionSource, Class<?> paramClass)
    throws SQLException
  {
    Object localObject3 = field.getType();
    DatabaseType localDatabaseType = paramConnectionSource.getDatabaseType();
    String str = fieldConfig.getForeignColumnName();
    Object localObject2;
    Object localObject1;
    if ((fieldConfig.isForeignAutoRefresh()) || (str != null))
    {
      paramClass = fieldConfig.getForeignTableConfig();
      if (paramClass == null) {
        paramConnectionSource = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, (Class)localObject3);
      }
      for (localObject2 = paramConnectionSource.getTableInfo(); str == null; localObject2 = paramConnectionSource.getTableInfo())
      {
        paramClass = ((TableInfo)localObject2).getIdField();
        localObject1 = paramClass;
        if (paramClass != null) {
          break label204;
        }
        throw new IllegalArgumentException("Foreign field " + localObject3 + " does not have id field");
        paramClass.extractFieldTypes(paramConnectionSource);
        paramConnectionSource = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, paramClass);
      }
      paramClass = ((TableInfo)localObject2).getFieldTypeByColumnName(str);
      localObject1 = paramClass;
      if (paramClass == null) {
        throw new IllegalArgumentException("Foreign field " + localObject3 + " does not have field named '" + str + "'");
      }
      label204:
      localObject3 = MappedQueryForId.build(localDatabaseType, (TableInfo)localObject2, (FieldType)localObject1);
      paramClass = null;
    }
    for (;;)
    {
      mappedQueryForId = ((MappedQueryForId)localObject3);
      foreignTableInfo = ((TableInfo)localObject2);
      foreignFieldType = paramClass;
      foreignDao = paramConnectionSource;
      foreignIdField = ((FieldType)localObject1);
      if (foreignIdField != null) {
        assignDataType(localDatabaseType, foreignIdField.getDataPersister());
      }
      return;
      if (fieldConfig.isForeign())
      {
        if ((dataPersister != null) && (dataPersister.isPrimitive())) {
          throw new IllegalArgumentException("Field " + this + " is a primitive class " + localObject3 + " but marked as foreign");
        }
        paramClass = fieldConfig.getForeignTableConfig();
        if (paramClass != null) {
          paramClass.extractFieldTypes(paramConnectionSource);
        }
        for (paramConnectionSource = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, paramClass);; paramConnectionSource = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, (Class)localObject3))
        {
          localObject2 = paramConnectionSource.getTableInfo();
          localObject1 = ((TableInfo)localObject2).getIdField();
          if (localObject1 != null) {
            break;
          }
          throw new IllegalArgumentException("Foreign field " + localObject3 + " does not have id field");
        }
        if ((isForeignAutoCreate()) && (!((FieldType)localObject1).isGeneratedId())) {
          throw new IllegalArgumentException("Field " + field.getName() + ", if foreignAutoCreate = true then class " + ((Class)localObject3).getSimpleName() + " must have id field with generatedId = true");
        }
        paramClass = null;
        localObject3 = null;
      }
      else
      {
        if (fieldConfig.isForeignCollection())
        {
          if ((localObject3 != Collection.class) && (!ForeignCollection.class.isAssignableFrom((Class)localObject3))) {
            throw new SQLException("Field class for '" + field.getName() + "' must be of class " + ForeignCollection.class.getSimpleName() + " or Collection.");
          }
          localObject1 = field.getGenericType();
          if (!(localObject1 instanceof ParameterizedType)) {
            throw new SQLException("Field class for '" + field.getName() + "' must be a parameterized Collection.");
          }
          localObject1 = ((ParameterizedType)localObject1).getActualTypeArguments();
          if (localObject1.length == 0) {
            throw new SQLException("Field class for '" + field.getName() + "' must be a parameterized Collection with at least 1 type.");
          }
          if (!(localObject1[0] instanceof Class)) {
            throw new SQLException("Field class for '" + field.getName() + "' must be a parameterized Collection whose generic argument is an entity class not: " + localObject1[0]);
          }
          localObject2 = (Class)localObject1[0];
          localObject1 = fieldConfig.getForeignTableConfig();
          if (localObject1 == null) {}
          for (paramConnectionSource = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, (Class)localObject2);; paramConnectionSource = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, (DatabaseTableConfig)localObject1))
          {
            localObject1 = paramConnectionSource;
            paramClass = findForeignFieldType((Class)localObject2, paramClass, paramConnectionSource);
            str = null;
            localObject2 = null;
            localObject3 = null;
            paramConnectionSource = (ConnectionSource)localObject1;
            localObject1 = str;
            break;
          }
        }
        localObject2 = null;
        localObject1 = null;
        paramClass = null;
        paramConnectionSource = null;
        localObject3 = null;
      }
    }
  }
  
  public Object convertJavaFieldToSqlArgValue(Object paramObject)
    throws SQLException
  {
    if (paramObject == null) {
      return null;
    }
    return fieldConverter.javaToSqlArg(this, paramObject);
  }
  
  public Object convertStringToJavaField(String paramString, int paramInt)
    throws SQLException
  {
    if (paramString == null) {
      return null;
    }
    return fieldConverter.resultStringToJava(this, paramString, paramInt);
  }
  
  public <T> int createWithForeignDao(T paramT)
    throws SQLException
  {
    return foreignDao.create(paramT);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {}
    do
    {
      do
      {
        return false;
        paramObject = (FieldType)paramObject;
      } while (!field.equals(field));
      if (parentClass != null) {
        break;
      }
    } while (parentClass != null);
    for (;;)
    {
      return true;
      if (!parentClass.equals(parentClass)) {
        break;
      }
    }
  }
  
  public Object extractJavaFieldToSqlArgValue(Object paramObject)
    throws SQLException
  {
    return convertJavaFieldToSqlArgValue(extractJavaFieldValue(paramObject));
  }
  
  public Object extractJavaFieldValue(Object paramObject)
    throws SQLException
  {
    Object localObject = extractRawJavaFieldValue(paramObject);
    paramObject = localObject;
    if (foreignIdField != null)
    {
      paramObject = localObject;
      if (localObject != null) {
        paramObject = foreignIdField.extractRawJavaFieldValue(localObject);
      }
    }
    return paramObject;
  }
  
  public <FV> FV extractRawJavaFieldValue(Object paramObject)
    throws SQLException
  {
    if (fieldGetMethod == null) {}
    for (;;)
    {
      try
      {
        paramObject = field.get(paramObject);
        return (FV)paramObject;
      }
      catch (Exception paramObject)
      {
        throw SqlExceptionUtil.create("Could not get field value for " + this, (Throwable)paramObject);
      }
      try
      {
        paramObject = fieldGetMethod.invoke(paramObject, new Object[0]);
      }
      catch (Exception paramObject)
      {
        throw SqlExceptionUtil.create("Could not call " + fieldGetMethod + " for " + this, (Throwable)paramObject);
      }
    }
  }
  
  public Object generateId()
  {
    return dataPersister.generateId();
  }
  
  public String getColumnDefinition()
  {
    return fieldConfig.getColumnDefinition();
  }
  
  public String getColumnName()
  {
    return columnName;
  }
  
  public DataPersister getDataPersister()
  {
    return dataPersister;
  }
  
  public Object getDataTypeConfigObj()
  {
    return dataTypeConfigObj;
  }
  
  public Object getDefaultValue()
  {
    return defaultValue;
  }
  
  public Field getField()
  {
    return field;
  }
  
  public String getFieldName()
  {
    return field.getName();
  }
  
  public <FV> FV getFieldValueIfNotDefault(Object paramObject)
    throws SQLException
  {
    Object localObject = extractJavaFieldValue(paramObject);
    paramObject = localObject;
    if (isFieldValueDefault(localObject)) {
      paramObject = null;
    }
    return (FV)paramObject;
  }
  
  public FieldType getForeignIdField()
  {
    return foreignIdField;
  }
  
  public String getFormat()
  {
    return fieldConfig.getFormat();
  }
  
  public String getGeneratedIdSequence()
  {
    return generatedIdSequence;
  }
  
  public String getIndexName()
  {
    return fieldConfig.getIndexName(tableName);
  }
  
  public Object getJavaDefaultValueDefault()
  {
    if (field.getType() == Boolean.TYPE) {
      return Boolean.valueOf(DEFAULT_VALUE_BOOLEAN);
    }
    if ((field.getType() == Byte.TYPE) || (field.getType() == Byte.class)) {
      return Byte.valueOf(DEFAULT_VALUE_BYTE);
    }
    if ((field.getType() == Character.TYPE) || (field.getType() == Character.class)) {
      return Character.valueOf(DEFAULT_VALUE_CHAR);
    }
    if ((field.getType() == Short.TYPE) || (field.getType() == Short.class)) {
      return Short.valueOf(DEFAULT_VALUE_SHORT);
    }
    if ((field.getType() == Integer.TYPE) || (field.getType() == Integer.class)) {
      return Integer.valueOf(DEFAULT_VALUE_INT);
    }
    if ((field.getType() == Long.TYPE) || (field.getType() == Long.class)) {
      return Long.valueOf(DEFAULT_VALUE_LONG);
    }
    if ((field.getType() == Float.TYPE) || (field.getType() == Float.class)) {
      return Float.valueOf(DEFAULT_VALUE_FLOAT);
    }
    if ((field.getType() == Double.TYPE) || (field.getType() == Double.class)) {
      return Double.valueOf(DEFAULT_VALUE_DOUBLE);
    }
    return null;
  }
  
  public SqlType getSqlType()
  {
    return fieldConverter.getSqlType();
  }
  
  public String getTableName()
  {
    return tableName;
  }
  
  public Class<?> getType()
  {
    return field.getType();
  }
  
  public String getUniqueIndexName()
  {
    return fieldConfig.getUniqueIndexName(tableName);
  }
  
  public Enum<?> getUnknownEnumVal()
  {
    return fieldConfig.getUnknownEnumValue();
  }
  
  public int getWidth()
  {
    return fieldConfig.getWidth();
  }
  
  public int hashCode()
  {
    return field.hashCode();
  }
  
  public boolean isAllowGeneratedIdInsert()
  {
    return fieldConfig.isAllowGeneratedIdInsert();
  }
  
  public boolean isArgumentHolderRequired()
  {
    return dataPersister.isArgumentHolderRequired();
  }
  
  public boolean isCanBeNull()
  {
    return fieldConfig.isCanBeNull();
  }
  
  public boolean isComparable()
    throws SQLException
  {
    if (fieldConfig.isForeignCollection()) {
      return false;
    }
    if (dataPersister == null) {
      throw new SQLException("Internal error.  Data-persister is not configured for field.  Please post _full_ exception with associated data objects to mailing list: " + this);
    }
    return dataPersister.isComparable();
  }
  
  public boolean isEscapedDefaultValue()
  {
    return dataPersister.isEscapedDefaultValue();
  }
  
  public boolean isEscapedValue()
  {
    return dataPersister.isEscapedValue();
  }
  
  public boolean isForeign()
  {
    return fieldConfig.isForeign();
  }
  
  public boolean isForeignAutoCreate()
  {
    return fieldConfig.isForeignAutoCreate();
  }
  
  public boolean isForeignCollection()
  {
    return fieldConfig.isForeignCollection();
  }
  
  public boolean isGeneratedId()
  {
    return isGeneratedId;
  }
  
  public boolean isGeneratedIdSequence()
  {
    return generatedIdSequence != null;
  }
  
  public boolean isId()
  {
    return isId;
  }
  
  public boolean isObjectsFieldValueDefault(Object paramObject)
    throws SQLException
  {
    return isFieldValueDefault(extractJavaFieldValue(paramObject));
  }
  
  public boolean isReadOnly()
  {
    return fieldConfig.isReadOnly();
  }
  
  public boolean isSelfGeneratedId()
  {
    return dataPersister.isSelfGeneratedId();
  }
  
  public boolean isUnique()
  {
    return fieldConfig.isUnique();
  }
  
  public boolean isUniqueCombo()
  {
    return fieldConfig.isUniqueCombo();
  }
  
  public boolean isVersion()
  {
    return fieldConfig.isVersion();
  }
  
  public Object moveToNextValue(Object paramObject)
  {
    if (dataPersister == null) {
      return null;
    }
    return dataPersister.moveToNextValue(paramObject);
  }
  
  public <T> T resultToJava(DatabaseResults paramDatabaseResults, Map<String, Integer> paramMap)
    throws SQLException
  {
    Object localObject2 = (Integer)paramMap.get(columnName);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = Integer.valueOf(paramDatabaseResults.findColumn(columnName));
      paramMap.put(columnName, localObject1);
    }
    localObject2 = fieldConverter.resultToJava(this, paramDatabaseResults, ((Integer)localObject1).intValue());
    if (fieldConfig.isForeign())
    {
      paramMap = (Map<String, Integer>)localObject2;
      if (paramDatabaseResults.wasNull(((Integer)localObject1).intValue())) {
        paramMap = null;
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return paramMap;
            if (!dataPersister.isPrimitive()) {
              break;
            }
            paramMap = (Map<String, Integer>)localObject2;
          } while (!fieldConfig.isThrowIfNull());
          paramMap = (Map<String, Integer>)localObject2;
        } while (!paramDatabaseResults.wasNull(((Integer)localObject1).intValue()));
        throw new SQLException("Results value for primitive field '" + field.getName() + "' was an invalid null value");
        paramMap = (Map<String, Integer>)localObject2;
      } while (fieldConverter.isStreamType());
      paramMap = (Map<String, Integer>)localObject2;
    } while (!paramDatabaseResults.wasNull(((Integer)localObject1).intValue()));
    return null;
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + ":name=" + field.getName() + ",class=" + field.getDeclaringClass().getSimpleName();
  }
  
  private static class LevelCounters
  {
    int autoRefreshLevel;
    int autoRefreshLevelMax;
    int foreignCollectionLevel;
    int foreignCollectionLevelMax;
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.field.FieldType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */