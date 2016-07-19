package fr.dvilleneuve.lockito.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory
{
  private static ObjectMapper objectMapper;
  
  public static ObjectMapper getInstance()
  {
    if (objectMapper == null)
    {
      objectMapper = new ObjectMapper();
      objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    return objectMapper;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.ObjectMapperFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */