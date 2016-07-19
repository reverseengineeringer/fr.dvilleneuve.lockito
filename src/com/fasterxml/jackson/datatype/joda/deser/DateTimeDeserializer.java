package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;

public class DateTimeDeserializer
  extends JodaDeserializerBase<ReadableInstant>
{
  private static final long serialVersionUID = 1L;
  
  public DateTimeDeserializer(Class<? extends ReadableInstant> paramClass)
  {
    super(paramClass);
  }
  
  public static <T extends ReadableInstant> JsonDeserializer<T> forType(Class<T> paramClass)
  {
    return new DateTimeDeserializer(paramClass);
  }
  
  public ReadableDateTime deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject = paramDeserializationContext.getTimeZone();
    if (localObject == null) {}
    for (localObject = DateTimeZone.UTC; localJsonToken == JsonToken.VALUE_NUMBER_INT; localObject = DateTimeZone.forTimeZone((TimeZone)localObject)) {
      return new DateTime(paramJsonParser.getLongValue(), (DateTimeZone)localObject);
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return null;
      }
      return new DateTime(paramJsonParser, (DateTimeZone)localObject);
    }
    throw paramDeserializationContext.mappingException(getValueClass());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */