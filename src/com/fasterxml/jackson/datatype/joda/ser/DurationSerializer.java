package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.Duration;

public final class DurationSerializer
  extends JodaSerializerBase<Duration>
{
  public DurationSerializer()
  {
    super(Duration.class);
  }
  
  public void serialize(Duration paramDuration, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeNumber(paramDuration.getMillis());
      return;
    }
    paramJsonGenerator.writeString(paramDuration.toString());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.DurationSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */