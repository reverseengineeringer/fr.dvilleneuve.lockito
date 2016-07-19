package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class BeanPropertyMap$IteratorImpl
  implements Iterator<SettableBeanProperty>
{
  private final BeanPropertyMap.Bucket[] _buckets;
  private BeanPropertyMap.Bucket _currentBucket;
  private int _nextBucketIndex;
  
  public BeanPropertyMap$IteratorImpl(BeanPropertyMap.Bucket[] paramArrayOfBucket)
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

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap.IteratorImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */