package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class CollectionSerializer
  extends AsArraySerializerBase<Collection<?>>
{
  public CollectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer)
  {
    super(Collection.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer);
  }
  
  public CollectionSerializer(CollectionSerializer paramCollectionSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramCollectionSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new CollectionSerializer(_elementType, _staticTyping, paramTypeSerializer, _property, _elementSerializer);
  }
  
  public boolean hasSingleElement(Collection<?> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    if (!paramCollection.hasNext()) {}
    do
    {
      return false;
      paramCollection.next();
    } while (paramCollection.hasNext());
    return true;
  }
  
  public boolean isEmpty(Collection<?> paramCollection)
  {
    return (paramCollection == null) || (paramCollection.isEmpty());
  }
  
  public void serializeContents(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (_elementSerializer != null) {
      serializeContentsUsing(paramCollection, paramJsonGenerator, paramSerializerProvider, _elementSerializer);
    }
    Iterator localIterator;
    do
    {
      return;
      localIterator = paramCollection.iterator();
    } while (!localIterator.hasNext());
    Object localObject1 = _dynamicSerializers;
    TypeSerializer localTypeSerializer = _valueTypeSerializer;
    int i = 0;
    int j = i;
    for (;;)
    {
      Object localObject4;
      Class localClass;
      Object localObject2;
      Object localObject3;
      try
      {
        localObject4 = localIterator.next();
        if (localObject4 == null)
        {
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          j = i + 1;
          i = j;
          if (localIterator.hasNext()) {
            break;
          }
          return;
        }
        j = i;
        localClass = localObject4.getClass();
        j = i;
        JsonSerializer localJsonSerializer = ((PropertySerializerMap)localObject1).serializerFor(localClass);
        localObject2 = localObject1;
        localObject3 = localJsonSerializer;
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
          if (localTypeSerializer != null) {
            break label242;
          }
          j = i;
          ((JsonSerializer)localObject3).serialize(localObject4, paramJsonGenerator, paramSerializerProvider);
          localObject1 = localObject2;
          continue;
        }
        j = i;
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramCollection, j);
        return;
      }
      localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, localClass, paramSerializerProvider);
      continue;
      label242:
      j = i;
      ((JsonSerializer)localObject3).serializeWithType(localObject4, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
      localObject1 = localObject2;
    }
  }
  
  public void serializeContentsUsing(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    Iterator localIterator = paramCollection.iterator();
    TypeSerializer localTypeSerializer;
    int i;
    if (localIterator.hasNext())
    {
      localTypeSerializer = _valueTypeSerializer;
      i = 0;
    }
    for (;;)
    {
      Object localObject = localIterator.next();
      if (localObject == null) {}
      try
      {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        for (;;)
        {
          i += 1;
          if (localIterator.hasNext()) {
            break;
          }
          return;
          if (localTypeSerializer != null) {
            break label95;
          }
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramCollection, i);
          continue;
          label95:
          paramJsonSerializer.serializeWithType(localException, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  public CollectionSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    return new CollectionSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.CollectionSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */