package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IteratorSerializer
  extends AsArraySerializerBase<Iterator<?>>
{
  public IteratorSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty)
  {
    super(Iterator.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, null);
  }
  
  public IteratorSerializer(IteratorSerializer paramIteratorSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramIteratorSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IteratorSerializer(_elementType, _staticTyping, paramTypeSerializer, _property);
  }
  
  public boolean hasSingleElement(Iterator<?> paramIterator)
  {
    return false;
  }
  
  public boolean isEmpty(Iterator<?> paramIterator)
  {
    return (paramIterator == null) || (!paramIterator.hasNext());
  }
  
  public void serializeContents(Iterator<?> paramIterator, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    Object localObject2 = null;
    TypeSerializer localTypeSerializer;
    Object localObject1;
    if (paramIterator.hasNext())
    {
      localTypeSerializer = _valueTypeSerializer;
      localObject1 = null;
    }
    for (;;)
    {
      Object localObject6 = paramIterator.next();
      if (localObject6 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      while (!paramIterator.hasNext())
      {
        return;
        JsonSerializer localJsonSerializer = _elementSerializer;
        Object localObject3 = localObject2;
        Object localObject4 = localObject1;
        Object localObject5 = localJsonSerializer;
        if (localJsonSerializer == null)
        {
          localObject3 = localObject6.getClass();
          if (localObject3 != localObject2) {
            break label123;
          }
          localObject5 = localObject1;
          localObject4 = localObject1;
          localObject3 = localObject2;
        }
        for (;;)
        {
          if (localTypeSerializer != null) {
            break label142;
          }
          ((JsonSerializer)localObject5).serialize(localObject6, paramJsonGenerator, paramSerializerProvider);
          localObject2 = localObject3;
          localObject1 = localObject4;
          break;
          label123:
          localObject4 = paramSerializerProvider.findValueSerializer((Class)localObject3, _property);
          localObject5 = localObject4;
        }
        label142:
        ((JsonSerializer)localObject5).serializeWithType(localObject6, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        localObject2 = localObject3;
        localObject1 = localObject4;
      }
    }
  }
  
  public IteratorSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    return new IteratorSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.impl.IteratorSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */