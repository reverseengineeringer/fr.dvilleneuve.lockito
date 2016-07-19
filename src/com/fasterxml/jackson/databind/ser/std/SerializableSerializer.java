package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer
  extends StdSerializer<JsonSerializable>
{
  private static final AtomicReference<ObjectMapper> _mapperReference = new AtomicReference();
  public static final SerializableSerializer instance = new SerializableSerializer();
  
  protected SerializableSerializer()
  {
    super(JsonSerializable.class);
  }
  
  private static final ObjectMapper _getObjectMapper()
  {
    try
    {
      ObjectMapper localObjectMapper2 = (ObjectMapper)_mapperReference.get();
      ObjectMapper localObjectMapper1 = localObjectMapper2;
      if (localObjectMapper2 == null)
      {
        localObjectMapper1 = new ObjectMapper();
        _mapperReference.set(localObjectMapper1);
      }
      return localObjectMapper1;
    }
    finally {}
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    String str = null;
    ObjectNode localObjectNode = createObjectNode();
    paramSerializerProvider = "any";
    JsonSerializableSchema localJsonSerializableSchema;
    if (paramType != null)
    {
      paramType = TypeFactory.rawClass(paramType);
      if (paramType.isAnnotationPresent(JsonSerializableSchema.class))
      {
        localJsonSerializableSchema = (JsonSerializableSchema)paramType.getAnnotation(JsonSerializableSchema.class);
        paramType = localJsonSerializableSchema.schemaType();
        if (!"##irrelevant".equals(localJsonSerializableSchema.schemaObjectPropertiesDefinition()))
        {
          paramSerializerProvider = localJsonSerializableSchema.schemaObjectPropertiesDefinition();
          if (!"##irrelevant".equals(localJsonSerializableSchema.schemaItemDefinition())) {
            str = localJsonSerializableSchema.schemaItemDefinition();
          }
        }
      }
    }
    for (;;)
    {
      localObjectNode.put("type", paramType);
      if (paramSerializerProvider != null) {}
      try
      {
        localObjectNode.put("properties", _getObjectMapper().readTree(paramSerializerProvider));
        if (str != null) {}
        continue;
        paramSerializerProvider = null;
      }
      catch (IOException paramSerializerProvider)
      {
        try
        {
          localObjectNode.put("items", _getObjectMapper().readTree(str));
          return localObjectNode;
        }
        catch (IOException paramSerializerProvider)
        {
          throw new JsonMappingException("Failed to parse @JsonSerializableSchema.schemaItemDefinition value");
        }
        paramSerializerProvider = paramSerializerProvider;
        throw new JsonMappingException("Failed to parse @JsonSerializableSchema.schemaObjectPropertiesDefinition value");
      }
      break;
      localJsonSerializableSchema = null;
      paramType = paramSerializerProvider;
      paramSerializerProvider = localJsonSerializableSchema;
    }
  }
  
  public void serialize(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonSerializable.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramJsonSerializable.serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.SerializableSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */