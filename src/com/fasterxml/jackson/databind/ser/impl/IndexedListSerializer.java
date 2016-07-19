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
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer
  extends AsArraySerializerBase<List<?>>
{
  public IndexedListSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer)
  {
    super(List.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer);
  }
  
  public IndexedListSerializer(IndexedListSerializer paramIndexedListSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramIndexedListSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IndexedListSerializer(_elementType, _staticTyping, paramTypeSerializer, _property, _elementSerializer);
  }
  
  public boolean hasSingleElement(List<?> paramList)
  {
    return paramList.size() == 1;
  }
  
  public boolean isEmpty(List<?> paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  public void serializeContents(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (_elementSerializer != null) {
      serializeContentsUsing(paramList, paramJsonGenerator, paramSerializerProvider, _elementSerializer);
    }
    int k;
    do
    {
      return;
      if (_valueTypeSerializer != null)
      {
        serializeTypedContents(paramList, paramJsonGenerator, paramSerializerProvider);
        return;
      }
      k = paramList.size();
    } while (k == 0);
    int j = 0;
    int i = 0;
    for (;;)
    {
      Class localClass;
      try
      {
        localObject1 = _dynamicSerializers;
        if (i >= k) {
          break;
        }
        j = i;
        Object localObject4 = paramList.get(i);
        if (localObject4 == null)
        {
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        else
        {
          j = i;
          localClass = localObject4.getClass();
          j = i;
          JsonSerializer localJsonSerializer = ((PropertySerializerMap)localObject1).serializerFor(localClass);
          Object localObject2 = localObject1;
          Object localObject3 = localJsonSerializer;
          if (localJsonSerializer == null)
          {
            j = i;
            if (_elementType.hasGenericTypes())
            {
              j = i;
              localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, paramSerializerProvider.constructSpecializedType(_elementType, localClass), paramSerializerProvider);
              j = i;
              localObject2 = _dynamicSerializers;
              localObject3 = localObject1;
            }
          }
          else
          {
            j = i;
            ((JsonSerializer)localObject3).serialize(localObject4, paramJsonGenerator, paramSerializerProvider);
            localObject1 = localObject2;
          }
        }
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramList, j);
        return;
      }
      j = i;
      Object localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, localClass, paramSerializerProvider);
      continue;
      i += 1;
    }
  }
  
  public void serializeContentsUsing(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    int j = paramList.size();
    if (j == 0) {}
    for (;;)
    {
      return;
      TypeSerializer localTypeSerializer = _valueTypeSerializer;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramList.get(i);
        if (localObject == null) {}
        try
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramList, i);
        }
        if (localTypeSerializer == null) {
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        } else {
          paramJsonSerializer.serializeWithType(localException, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
        i += 1;
      }
    }
  }
  
  public void serializeTypedContents(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    int m = paramList.size();
    if (m == 0) {
      return;
    }
    int k = 0;
    int i = 0;
    int j = k;
    for (;;)
    {
      Class localClass;
      try
      {
        TypeSerializer localTypeSerializer = _valueTypeSerializer;
        j = k;
        localObject1 = _dynamicSerializers;
        if (i >= m) {
          break;
        }
        j = i;
        Object localObject4 = paramList.get(i);
        if (localObject4 == null)
        {
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        else
        {
          j = i;
          localClass = localObject4.getClass();
          j = i;
          JsonSerializer localJsonSerializer = ((PropertySerializerMap)localObject1).serializerFor(localClass);
          Object localObject2 = localObject1;
          Object localObject3 = localJsonSerializer;
          if (localJsonSerializer == null)
          {
            j = i;
            if (_elementType.hasGenericTypes())
            {
              j = i;
              localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, paramSerializerProvider.constructSpecializedType(_elementType, localClass), paramSerializerProvider);
              j = i;
              localObject2 = _dynamicSerializers;
              localObject3 = localObject1;
            }
          }
          else
          {
            j = i;
            ((JsonSerializer)localObject3).serializeWithType(localObject4, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
            localObject1 = localObject2;
          }
        }
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramList, j);
        return;
      }
      j = i;
      Object localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, localClass, paramSerializerProvider);
      continue;
      i += 1;
    }
  }
  
  public IndexedListSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    return new IndexedListSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */