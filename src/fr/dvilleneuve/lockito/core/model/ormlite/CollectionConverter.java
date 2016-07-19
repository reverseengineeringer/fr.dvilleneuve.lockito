package fr.dvilleneuve.lockito.core.model.ormlite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dvilleneuve.lockito.core.logger.Logger;
import java.io.IOException;
import java.util.Collection;

public class CollectionConverter
  extends PointConverter
{
  private static final CollectionConverter singleton = new CollectionConverter();
  
  public static <T extends Collection<?>> T deserialize(String paramString)
  {
    try
    {
      paramString = (Collection)objectMapper.readValue(paramString, new TypeReference() {});
      return paramString;
    }
    catch (IOException paramString)
    {
      Logger.error("Can't deserialize value", new Object[0]);
    }
    return null;
  }
  
  public static <T extends Collection<?>> String serialize(T paramT)
  {
    try
    {
      paramT = objectMapper.writeValueAsString(paramT);
      return paramT;
    }
    catch (JsonProcessingException paramT)
    {
      Logger.error("Can't serialize value", new Object[0]);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.ormlite.CollectionConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */