package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;

@JacksonStdImpl
public class SqlDateSerializer
  extends StdScalarSerializer<Date>
{
  public SqlDateSerializer()
  {
    super(Date.class);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (paramJsonFormatVisitorWrapper = null;; paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType))
    {
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.format(JsonValueFormat.DATE_TIME);
      }
      return;
    }
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("string", true);
  }
  
  public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeString(paramDate.toString());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.SqlDateSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */