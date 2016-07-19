package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BeanPropertyMap
  implements Iterable<SettableBeanProperty>, Serializable
{
  private static final long serialVersionUID = 1L;
  private final Bucket[] _buckets;
  private final int _hashMask;
  private int _nextBucketIndex = 0;
  private final int _size;
  
  public BeanPropertyMap(Collection<SettableBeanProperty> paramCollection)
  {
    _size = paramCollection.size();
    int i = findSize(_size);
    _hashMask = (i - 1);
    Bucket[] arrayOfBucket = new Bucket[i];
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)paramCollection.next();
      String str = localSettableBeanProperty.getName();
      i = str.hashCode() & _hashMask;
      Bucket localBucket = arrayOfBucket[i];
      int j = _nextBucketIndex;
      _nextBucketIndex = (j + 1);
      arrayOfBucket[i] = new Bucket(localBucket, str, localSettableBeanProperty, j);
    }
    _buckets = arrayOfBucket;
  }
  
  private BeanPropertyMap(Bucket[] paramArrayOfBucket, int paramInt1, int paramInt2)
  {
    _buckets = paramArrayOfBucket;
    _size = paramInt1;
    _hashMask = (paramArrayOfBucket.length - 1);
    _nextBucketIndex = paramInt2;
  }
  
  private SettableBeanProperty _findWithEquals(String paramString, int paramInt)
  {
    for (Bucket localBucket = _buckets[paramInt]; localBucket != null; localBucket = next) {
      if (paramString.equals(key)) {
        return value;
      }
    }
    return null;
  }
  
  private static final int findSize(int paramInt)
  {
    if (paramInt <= 32) {
      paramInt += paramInt;
    }
    int i;
    for (;;)
    {
      i = 2;
      while (i < paramInt) {
        i += i;
      }
      paramInt = (paramInt >> 2) + paramInt;
    }
    return i;
  }
  
  public BeanPropertyMap assignIndexes()
  {
    Bucket[] arrayOfBucket = _buckets;
    int k = arrayOfBucket.length;
    int i = 0;
    int j = 0;
    while (i < k)
    {
      Bucket localBucket = arrayOfBucket[i];
      while (localBucket != null)
      {
        value.assignIndex(j);
        localBucket = next;
        j += 1;
      }
      i += 1;
    }
    return this;
  }
  
  public SettableBeanProperty find(int paramInt)
  {
    int j = _buckets.length;
    int i = 0;
    while (i < j)
    {
      for (Bucket localBucket = _buckets[i]; localBucket != null; localBucket = next) {
        if (index == paramInt) {
          return value;
        }
      }
      i += 1;
    }
    return null;
  }
  
  public SettableBeanProperty find(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Can not pass null property name");
    }
    int i = paramString.hashCode();
    i = _hashMask & i;
    Bucket localBucket2 = _buckets[i];
    if (localBucket2 == null) {
      return null;
    }
    Bucket localBucket1 = localBucket2;
    if (key == paramString) {
      return value;
    }
    do
    {
      localBucket2 = next;
      if (localBucket2 == null) {
        break;
      }
      localBucket1 = localBucket2;
    } while (key != paramString);
    return value;
    return _findWithEquals(paramString, i);
  }
  
  public SettableBeanProperty[] getPropertiesInInsertionOrder()
  {
    SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[_nextBucketIndex];
    Bucket[] arrayOfBucket = _buckets;
    int j = arrayOfBucket.length;
    int i = 0;
    while (i < j)
    {
      for (Bucket localBucket = arrayOfBucket[i]; localBucket != null; localBucket = next) {
        arrayOfSettableBeanProperty[index] = value;
      }
      i += 1;
    }
    return arrayOfSettableBeanProperty;
  }
  
  public Iterator<SettableBeanProperty> iterator()
  {
    return new IteratorImpl(_buckets);
  }
  
  public void remove(SettableBeanProperty paramSettableBeanProperty)
  {
    String str = paramSettableBeanProperty.getName();
    int j = str.hashCode() & _buckets.length - 1;
    Bucket localBucket1 = _buckets[j];
    int i = 0;
    Bucket localBucket2 = null;
    if (localBucket1 != null)
    {
      if ((i == 0) && (key.equals(str))) {
        i = 1;
      }
      for (;;)
      {
        localBucket1 = next;
        break;
        localBucket2 = new Bucket(localBucket2, key, value, index);
      }
    }
    if (i == 0) {
      throw new NoSuchElementException("No entry '" + paramSettableBeanProperty + "' found, can't remove");
    }
    _buckets[j] = localBucket2;
  }
  
  public BeanPropertyMap renameAll(NameTransformer paramNameTransformer)
  {
    if ((paramNameTransformer == null) || (paramNameTransformer == NameTransformer.NOP)) {
      return this;
    }
    Iterator localIterator = iterator();
    ArrayList localArrayList = new ArrayList();
    while (localIterator.hasNext())
    {
      Object localObject = (SettableBeanProperty)localIterator.next();
      SettableBeanProperty localSettableBeanProperty = ((SettableBeanProperty)localObject).withSimpleName(paramNameTransformer.transform(((SettableBeanProperty)localObject).getName()));
      JsonDeserializer localJsonDeserializer1 = localSettableBeanProperty.getValueDeserializer();
      localObject = localSettableBeanProperty;
      if (localJsonDeserializer1 != null)
      {
        JsonDeserializer localJsonDeserializer2 = localJsonDeserializer1.unwrappingDeserializer(paramNameTransformer);
        localObject = localSettableBeanProperty;
        if (localJsonDeserializer2 != localJsonDeserializer1) {
          localObject = localSettableBeanProperty.withValueDeserializer(localJsonDeserializer2);
        }
      }
      localArrayList.add(localObject);
    }
    return new BeanPropertyMap(localArrayList);
  }
  
  public void replace(SettableBeanProperty paramSettableBeanProperty)
  {
    String str = paramSettableBeanProperty.getName();
    int j = str.hashCode() & _buckets.length - 1;
    Bucket localBucket1 = _buckets[j];
    int i = -1;
    Bucket localBucket2 = null;
    if (localBucket1 != null)
    {
      if ((i < 0) && (key.equals(str))) {
        i = index;
      }
      for (;;)
      {
        localBucket1 = next;
        break;
        localBucket2 = new Bucket(localBucket2, key, value, index);
      }
    }
    if (i < 0) {
      throw new NoSuchElementException("No entry '" + paramSettableBeanProperty + "' found, can't replace");
    }
    _buckets[j] = new Bucket(localBucket2, str, paramSettableBeanProperty, i);
  }
  
  public int size()
  {
    return _size;
  }
  
  public String toString()
  {
    int j = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Properties=[");
    SettableBeanProperty[] arrayOfSettableBeanProperty = getPropertiesInInsertionOrder();
    int k = arrayOfSettableBeanProperty.length;
    int i = 0;
    if (i < k)
    {
      SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
      if (localSettableBeanProperty == null) {}
      for (;;)
      {
        i += 1;
        break;
        if (j > 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append(localSettableBeanProperty.getName());
        localStringBuilder.append('(');
        localStringBuilder.append(localSettableBeanProperty.getType());
        localStringBuilder.append(')');
        j += 1;
      }
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public BeanPropertyMap withProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    int i = _buckets.length;
    Object localObject = new Bucket[i];
    System.arraycopy(_buckets, 0, localObject, 0, i);
    String str = paramSettableBeanProperty.getName();
    if (find(paramSettableBeanProperty.getName()) == null)
    {
      i = str.hashCode() & _hashMask;
      Bucket localBucket = localObject[i];
      int j = _nextBucketIndex;
      _nextBucketIndex = (j + 1);
      localObject[i] = new Bucket(localBucket, str, paramSettableBeanProperty, j);
      return new BeanPropertyMap((Bucket[])localObject, _size + 1, _nextBucketIndex);
    }
    localObject = new BeanPropertyMap((Bucket[])localObject, i, _nextBucketIndex);
    ((BeanPropertyMap)localObject).replace(paramSettableBeanProperty);
    return (BeanPropertyMap)localObject;
  }
  
  private static final class Bucket
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    public final int index;
    public final String key;
    public final Bucket next;
    public final SettableBeanProperty value;
    
    public Bucket(Bucket paramBucket, String paramString, SettableBeanProperty paramSettableBeanProperty, int paramInt)
    {
      next = paramBucket;
      key = paramString;
      value = paramSettableBeanProperty;
      index = paramInt;
    }
  }
  
  private static final class IteratorImpl
    implements Iterator<SettableBeanProperty>
  {
    private final BeanPropertyMap.Bucket[] _buckets;
    private BeanPropertyMap.Bucket _currentBucket;
    private int _nextBucketIndex;
    
    public IteratorImpl(BeanPropertyMap.Bucket[] paramArrayOfBucket)
    {
      _buckets = paramArrayOfBucket;
      int i = 0;
      int k = _buckets.length;
      int j;
      if (i < k)
      {
        paramArrayOfBucket = _buckets;
        j = i + 1;
        paramArrayOfBucket = paramArrayOfBucket[i];
        if (paramArrayOfBucket != null) {
          _currentBucket = paramArrayOfBucket;
        }
      }
      for (;;)
      {
        _nextBucketIndex = j;
        return;
        i = j;
        break;
        j = i;
      }
    }
    
    public boolean hasNext()
    {
      return _currentBucket != null;
    }
    
    public SettableBeanProperty next()
    {
      BeanPropertyMap.Bucket localBucket = _currentBucket;
      if (localBucket == null) {
        throw new NoSuchElementException();
      }
      int i;
      for (Object localObject = next; (localObject == null) && (_nextBucketIndex < _buckets.length); localObject = localObject[i])
      {
        localObject = _buckets;
        i = _nextBucketIndex;
        _nextBucketIndex = (i + 1);
      }
      _currentBucket = ((BeanPropertyMap.Bucket)localObject);
      return value;
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */