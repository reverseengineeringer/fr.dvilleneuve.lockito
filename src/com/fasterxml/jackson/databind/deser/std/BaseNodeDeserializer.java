package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

abstract class BaseNodeDeserializer<T extends JsonNode>
  extends StdDeserializer<T>
{
  public BaseNodeDeserializer(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  protected void _handleDuplicateField(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory, String paramString, ObjectNode paramObjectNode, JsonNode paramJsonNode1, JsonNode paramJsonNode2)
    throws JsonProcessingException
  {
    if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
      _reportProblem(paramJsonParser, "Duplicate field '" + paramString + "' for ObjectNode: not allowed when FAIL_ON_READING_DUP_TREE_KEY enabled");
    }
    _handleDuplicateField(paramString, paramObjectNode, paramJsonNode1, paramJsonNode2);
  }
  
  @Deprecated
  protected void _handleDuplicateField(String paramString, ObjectNode paramObjectNode, JsonNode paramJsonNode1, JsonNode paramJsonNode2)
    throws JsonProcessingException
  {}
  
  protected void _reportProblem(JsonParser paramJsonParser, String paramString)
    throws JsonMappingException
  {
    throw new JsonMappingException(paramString, paramJsonParser.getTokenLocation());
  }
  
  protected final JsonNode deserializeAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException, JsonProcessingException
  {
    switch (paramJsonParser.getCurrentToken())
    {
    case ???: 
    default: 
      throw paramDeserializationContext.mappingException(handledType());
    case ???: 
    case ???: 
      return deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
    case ???: 
      return deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
    case ???: 
      return deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
    case ???: 
      paramJsonParser = paramJsonParser.getEmbeddedObject();
      if (paramJsonParser == null) {
        return paramJsonNodeFactory.nullNode();
      }
      if (paramJsonParser.getClass() == byte[].class) {
        return paramJsonNodeFactory.binaryNode((byte[])paramJsonParser);
      }
      return paramJsonNodeFactory.pojoNode(paramJsonParser);
    case ???: 
      return paramJsonNodeFactory.textNode(paramJsonParser.getText());
    case ???: 
      JsonParser.NumberType localNumberType = paramJsonParser.getNumberType();
      if ((localNumberType == JsonParser.NumberType.BIG_INTEGER) || (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))) {
        return paramJsonNodeFactory.numberNode(paramJsonParser.getBigIntegerValue());
      }
      if (localNumberType == JsonParser.NumberType.INT) {
        return paramJsonNodeFactory.numberNode(paramJsonParser.getIntValue());
      }
      return paramJsonNodeFactory.numberNode(paramJsonParser.getLongValue());
    case ???: 
      if ((paramJsonParser.getNumberType() == JsonParser.NumberType.BIG_DECIMAL) || (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))) {
        return paramJsonNodeFactory.numberNode(paramJsonParser.getDecimalValue());
      }
      return paramJsonNodeFactory.numberNode(paramJsonParser.getDoubleValue());
    case ???: 
      return paramJsonNodeFactory.booleanNode(true);
    case ???: 
      return paramJsonNodeFactory.booleanNode(false);
    }
    return paramJsonNodeFactory.nullNode();
  }
  
  protected final ArrayNode deserializeArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException, JsonProcessingException
  {
    ArrayNode localArrayNode = paramJsonNodeFactory.arrayNode();
    for (;;)
    {
      JsonToken localJsonToken = paramJsonParser.nextToken();
      if (localJsonToken == null) {
        throw paramDeserializationContext.mappingException("Unexpected end-of-input when binding data into ArrayNode");
      }
      switch (localJsonToken)
      {
      default: 
        localArrayNode.add(deserializeAny(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
        break;
      case ???: 
        localArrayNode.add(deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
        break;
      case ???: 
        localArrayNode.add(deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
        break;
      case ???: 
        localArrayNode.add(paramJsonNodeFactory.textNode(paramJsonParser.getText()));
      }
    }
    return localArrayNode;
  }
  
  protected final ObjectNode deserializeObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException, JsonProcessingException
  {
    ObjectNode localObjectNode = paramJsonNodeFactory.objectNode();
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    if (localObject1 == JsonToken.FIELD_NAME)
    {
      localObject2 = paramJsonParser.getCurrentName();
      switch (paramJsonParser.nextToken())
      {
      default: 
        localObject1 = deserializeAny(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      }
      for (;;)
      {
        JsonNode localJsonNode = localObjectNode.replace((String)localObject2, (JsonNode)localObject1);
        if (localJsonNode != null) {
          _handleDuplicateField(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory, (String)localObject2, localObjectNode, localJsonNode, (JsonNode)localObject1);
        }
        localObject1 = paramJsonParser.nextToken();
        break;
        localObject1 = deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
        continue;
        localObject1 = deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
        continue;
        localObject1 = paramJsonNodeFactory.textNode(paramJsonParser.getText());
      }
    }
    return localObjectNode;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */