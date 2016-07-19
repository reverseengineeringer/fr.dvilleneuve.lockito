package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class PeriodSerializer
  extends JodaSerializerBase<ReadablePeriod>
{
  protected final PeriodFormatter defaultFormat = ISOPeriodFormat.standard();
  
  public PeriodSerializer()
  {
    super(ReadablePeriod.class);
  }
  
  public void serialize(ReadablePeriod paramReadablePeriod, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeString(defaultFormat.print(paramReadablePeriod));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.PeriodSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */