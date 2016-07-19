package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.Interval;

public class IntervalSerializer
  extends JodaSerializerBase<Interval>
{
  public IntervalSerializer()
  {
    super(Interval.class);
  }
  
  public void serialize(Interval paramInterval, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeString(paramInterval.getStartMillis() + "-" + paramInterval.getEndMillis());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.IntervalSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */