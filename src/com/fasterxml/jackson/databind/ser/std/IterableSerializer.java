package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IterableSerializer
  extends AsArraySerializerBase<Iterable<?>>
{
  public IterableSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty)
  {
    super(Iterable.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, null);
  }
  
  public IterableSerializer(IterableSerializer paramIterableSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramIterableSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IterableSerializer(_elementType, _staticTyping, paramTypeSerializer, _property);
  }
  
  public boolean hasSingleElement(Iterable<?> paramIterable)
  {
    if (paramIterable != null)
    {
      paramIterable = paramIterable.iterator();
      if (paramIterable.hasNext())
      {
        paramIterable.next();
        if (!paramIterable.hasNext()) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isEmpty(Iterable<?> paramIterable)
  {
    return (paramIterable == null) || (!paramIterable.iterator().hasNext());
  }
  
  public void serializeContents(Iterable<?> paramIterable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    Object localObject1 = null;
    Iterator localIterator = paramIterable.iterator();
    TypeSerializer localTypeSerializer;
    if (localIterator.hasNext())
    {
      localTypeSerializer = _valueTypeSerializer;
      paramIterable = null;
    }
    for (;;)
    {
      Object localObject5 = localIterator.next();
      if (localObject5 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      while (!localIterator.hasNext())
      {
        return;
        JsonSerializer localJsonSerializer = _elementSerializer;
        Object localObject2 = localObject1;
        Object localObject3 = paramIterable;
        Object localObject4 = localJsonSerializer;
        if (localJsonSerializer == null)
        {
          localObject2 = localObject5.getClass();
          if (localObject2 != localObject1) {
            break label129;
          }
          localObject4 = paramIterable;
          localObject3 = paramIterable;
          localObject2 = localObject1;
        }
        for (;;)
        {
          if (localTypeSerializer != null) {
            break label148;
          }
          ((JsonSerializer)localObject4).serialize(localObject5, paramJsonGenerator, paramSerializerProvider);
          localObject1 = localObject2;
          paramIterable = (Iterable<?>)localObject3;
          break;
          label129:
          localObject3 = paramSerializerProvider.findValueSerializer((Class)localObject2, _property);
          localObject4 = localObject3;
        }
        label148:
        ((JsonSerializer)localObject4).serializeWithType(localObject5, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        localObject1 = localObject2;
        paramIterable = (Iterable<?>)localObject3;
      }
    }
  }
  
  public IterableSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    return new IterableSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.IterableSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */