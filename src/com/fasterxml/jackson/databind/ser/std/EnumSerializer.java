package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@JacksonStdImpl
public class EnumSerializer
  extends StdScalarSerializer<Enum<?>>
  implements ContextualSerializer
{
  protected final Boolean _serializeAsIndex;
  protected final EnumValues _values;
  
  @Deprecated
  public EnumSerializer(EnumValues paramEnumValues)
  {
    this(paramEnumValues, null);
  }
  
  public EnumSerializer(EnumValues paramEnumValues, Boolean paramBoolean)
  {
    super(Enum.class, false);
    _values = paramEnumValues;
    _serializeAsIndex = paramBoolean;
  }
  
  protected static Boolean _isShapeWrittenUsingIndex(Class<?> paramClass, JsonFormat.Value paramValue, boolean paramBoolean)
  {
    if (paramValue == null)
    {
      paramValue = null;
      if (paramValue != null) {
        break label20;
      }
    }
    label20:
    while ((paramValue == JsonFormat.Shape.ANY) || (paramValue == JsonFormat.Shape.SCALAR))
    {
      return null;
      paramValue = paramValue.getShape();
      break;
    }
    if (paramValue == JsonFormat.Shape.STRING) {
      return Boolean.FALSE;
    }
    if (paramValue.isNumeric()) {
      return Boolean.TRUE;
    }
    paramValue = new StringBuilder().append("Unsupported serialization shape (").append(paramValue).append(") for Enum ").append(paramClass.getName()).append(", not supported as ");
    if (paramBoolean) {}
    for (paramClass = "class";; paramClass = "property") {
      throw new IllegalArgumentException(paramClass + " annotation");
    }
  }
  
  @Deprecated
  public static EnumSerializer construct(Class<Enum<?>> paramClass, SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription)
  {
    return construct(paramClass, paramSerializationConfig, paramBeanDescription, paramBeanDescription.findExpectedFormat(null));
  }
  
  public static EnumSerializer construct(Class<Enum<?>> paramClass, SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, JsonFormat.Value paramValue)
  {
    paramBeanDescription = paramSerializationConfig.getAnnotationIntrospector();
    if (paramSerializationConfig.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {}
    for (paramSerializationConfig = EnumValues.constructFromToString(paramClass, paramBeanDescription);; paramSerializationConfig = EnumValues.constructFromName(paramClass, paramBeanDescription)) {
      return new EnumSerializer(paramSerializationConfig, _isShapeWrittenUsingIndex(paramClass, paramValue, true));
    }
  }
  
  protected final boolean _serializeAsIndex(SerializerProvider paramSerializerProvider)
  {
    if (_serializeAsIndex != null) {
      return _serializeAsIndex.booleanValue();
    }
    return paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper.getProvider().isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX))
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.INT);
      }
    }
    do
    {
      return;
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
    } while ((paramJavaType == null) || (paramJsonFormatVisitorWrapper == null) || (!paramJavaType.isEnumType()));
    paramJavaType = new LinkedHashSet();
    Iterator localIterator = _values.values().iterator();
    while (localIterator.hasNext()) {
      paramJavaType.add(((SerializedString)localIterator.next()).getValue());
    }
    paramJsonFormatVisitorWrapper.enumTypes(paramJavaType);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    EnumSerializer localEnumSerializer = this;
    if (paramBeanProperty != null)
    {
      paramSerializerProvider = paramSerializerProvider.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
      localEnumSerializer = this;
      if (paramSerializerProvider != null)
      {
        paramSerializerProvider = _isShapeWrittenUsingIndex(paramBeanProperty.getType().getRawClass(), paramSerializerProvider, false);
        localEnumSerializer = this;
        if (paramSerializerProvider != _serializeAsIndex) {
          localEnumSerializer = new EnumSerializer(_values, paramSerializerProvider);
        }
      }
    }
    return localEnumSerializer;
  }
  
  public EnumValues getEnumValues()
  {
    return _values;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (_serializeAsIndex(paramSerializerProvider)) {
      return createSchemaNode("integer", true);
    }
    ObjectNode localObjectNode = createSchemaNode("string", true);
    if ((paramType != null) && (paramSerializerProvider.constructType(paramType).isEnumType()))
    {
      paramSerializerProvider = localObjectNode.putArray("enum");
      paramType = _values.values().iterator();
      while (paramType.hasNext()) {
        paramSerializerProvider.add(((SerializedString)paramType.next()).getValue());
      }
    }
    return localObjectNode;
  }
  
  public final void serialize(Enum<?> paramEnum, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (_serializeAsIndex(paramSerializerProvider))
    {
      paramJsonGenerator.writeNumber(paramEnum.ordinal());
      return;
    }
    paramJsonGenerator.writeString(_values.serializedValueFor(paramEnum));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.EnumSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */