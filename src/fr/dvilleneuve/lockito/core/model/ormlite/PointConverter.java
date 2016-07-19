package fr.dvilleneuve.lockito.core.model.ormlite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.utils.ObjectMapperFactory;
import java.io.IOException;
import java.sql.SQLException;

public class PointConverter
  extends BaseDataType
{
  protected static final ObjectMapper objectMapper = ;
  private static final PointConverter singleton = new PointConverter();
  
  public PointConverter()
  {
    this(SqlType.STRING, new Class[0]);
  }
  
  public PointConverter(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }
  
  public static PointConverter getSingleton()
  {
    return singleton;
  }
  
  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    try
    {
      String str = toString((Point)paramObject);
      return str;
    }
    catch (JsonProcessingException localJsonProcessingException)
    {
      throw SqlExceptionUtil.create("Problems with field " + paramFieldType + " serialization: " + paramObject.toString(), localJsonProcessingException);
    }
  }
  
  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    try
    {
      Point localPoint = parsePosition(paramString);
      return localPoint;
    }
    catch (Exception localException)
    {
      throw SqlExceptionUtil.create("Problems with field " + paramFieldType + " parsing position value: " + paramString, localException);
    }
  }
  
  public Point parsePosition(String paramString)
    throws IOException
  {
    return (Point)objectMapper.readValue(paramString, Point.class);
  }
  
  public Object resultToSqlArg(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return paramDatabaseResults.getString(paramInt);
  }
  
  public Object sqlArgToJava(FieldType paramFieldType, Object paramObject, int paramInt)
    throws SQLException
  {
    return parseDefaultString(paramFieldType, (String)paramObject);
  }
  
  public String toString(Point paramPoint)
    throws JsonProcessingException
  {
    return objectMapper.writeValueAsString(paramPoint);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.ormlite.PointConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */