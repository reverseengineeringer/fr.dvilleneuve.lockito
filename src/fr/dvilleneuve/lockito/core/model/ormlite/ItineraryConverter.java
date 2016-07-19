package fr.dvilleneuve.lockito.core.model.ormlite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.ObjectMapperFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItineraryConverter
  extends BaseDataType
{
  private static final ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
  private static final ItineraryConverter singleton = new ItineraryConverter();
  
  public ItineraryConverter()
  {
    super(SqlType.STRING, new Class[0]);
  }
  
  public static ItineraryConverter getSingleton()
  {
    return singleton;
  }
  
  public Object javaToSqlArg(FieldType paramFieldType, Object paramObject)
    throws SQLException
  {
    try
    {
      paramFieldType = objectMapper.writeValueAsString(paramObject);
      return paramFieldType;
    }
    catch (JsonProcessingException paramFieldType)
    {
      throw new SQLException("Couldn't serialize itinerary");
    }
  }
  
  public Object parseDefaultString(FieldType paramFieldType, String paramString)
    throws SQLException
  {
    throw new SQLException("Default values for serializable types are not supported");
  }
  
  public Object resultToSqlArg(FieldType paramFieldType, DatabaseResults paramDatabaseResults, int paramInt)
    throws SQLException
  {
    return paramDatabaseResults.getString(paramInt);
  }
  
  public Object sqlArgToJava(FieldType paramFieldType, Object paramObject, int paramInt)
    throws SQLException
  {
    try
    {
      paramFieldType = objectMapper.readValue(paramObject.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, Leg.class));
      return paramFieldType;
    }
    catch (IOException paramFieldType)
    {
      throw new SQLException("Couldn't parse stored itinerary");
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.ormlite.ItineraryConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */