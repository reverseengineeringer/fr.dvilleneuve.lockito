package fr.dvilleneuve.lockito.core.model.ormlite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.utils.collection.GeometricList;
import java.io.IOException;
import java.sql.SQLException;

public class PointGeometricListConverter
  extends PointConverter
{
  private static final PointGeometricListConverter singleton = new PointGeometricListConverter();
  
  public PointGeometricListConverter()
  {
    super(SqlType.STRING, new Class[0]);
  }
  
  public static PointGeometricListConverter getSingleton()
  {
    return singleton;
  }
  
  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    try
    {
      String str = toString((GeometricList)paramObject);
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
      GeometricList localGeometricList = parsePositions(paramString);
      return localGeometricList;
    }
    catch (Exception localException)
    {
      throw SqlExceptionUtil.create("Problems with field " + paramFieldType + " parsing positions value: " + paramString, localException);
    }
  }
  
  protected GeometricList<Point> parsePositions(String paramString)
    throws IOException
  {
    (GeometricList)objectMapper.readValue(paramString, new TypeReference() {});
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
  
  protected String toString(GeometricList<Point> paramGeometricList)
    throws JsonProcessingException
  {
    return objectMapper.writeValueAsString(paramGeometricList);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.ormlite.PointGeometricListConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */