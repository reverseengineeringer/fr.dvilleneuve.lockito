package fr.dvilleneuve.lockito.core.model.ormlite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;
import fr.dvilleneuve.lockito.core.model.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PointListConverter
  extends PointConverter
{
  private static final PointListConverter singleton = new PointListConverter();
  
  public PointListConverter()
  {
    super(SqlType.STRING, new Class[0]);
  }
  
  public static PointListConverter getSingleton()
  {
    return singleton;
  }
  
  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    try
    {
      String str = toString((List)paramObject);
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
      List localList = parsePositions(paramString);
      return localList;
    }
    catch (Exception localException)
    {
      throw SqlExceptionUtil.create("Problems with field " + paramFieldType + " parsing positions value: " + paramString, localException);
    }
  }
  
  public List<Point> parsePositions(String paramString)
    throws IOException
  {
    (List)objectMapper.readValue(paramString, new TypeReference() {});
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
  
  public String toString(List<Point> paramList)
    throws JsonProcessingException
  {
    return objectMapper.writeValueAsString(paramList);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.ormlite.PointListConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */