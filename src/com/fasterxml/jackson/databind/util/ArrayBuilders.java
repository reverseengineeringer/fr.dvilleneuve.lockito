package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public final class ArrayBuilders
{
  private BooleanBuilder _booleanBuilder = null;
  private ByteBuilder _byteBuilder = null;
  private DoubleBuilder _doubleBuilder = null;
  private FloatBuilder _floatBuilder = null;
  private IntBuilder _intBuilder = null;
  private LongBuilder _longBuilder = null;
  private ShortBuilder _shortBuilder = null;
  
  public static <T> List<T> addToList(List<T> paramList, T paramT)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = new ArrayList();
    }
    ((List)localObject).add(paramT);
    return (List<T>)localObject;
  }
  
  public static <T> Iterable<T> arrayAsIterable(T[] paramArrayOfT)
  {
    return new ArrayIterator(paramArrayOfT);
  }
  
  public static <T> Iterator<T> arrayAsIterator(T[] paramArrayOfT)
  {
    return new ArrayIterator(paramArrayOfT);
  }
  
  public static <T> ArrayList<T> arrayToList(T[] paramArrayOfT)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramArrayOfT != null)
    {
      int j = paramArrayOfT.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(paramArrayOfT[i]);
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public static <T> HashSet<T> arrayToSet(T[] paramArrayOfT)
  {
    HashSet localHashSet = new HashSet();
    if (paramArrayOfT != null)
    {
      int j = paramArrayOfT.length;
      int i = 0;
      while (i < j)
      {
        localHashSet.add(paramArrayOfT[i]);
        i += 1;
      }
    }
    return localHashSet;
  }
  
  public static Object getArrayComparator(final Object paramObject)
  {
    final int i = Array.getLength(paramObject);
    new Object()
    {
      public boolean equals(Object paramAnonymousObject)
      {
        boolean bool2 = false;
        boolean bool1;
        if (paramAnonymousObject == this) {
          bool1 = true;
        }
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (paramAnonymousObject == null);
            bool1 = bool2;
          } while (paramAnonymousObject.getClass() != val$defaultValueType);
          bool1 = bool2;
        } while (Array.getLength(paramAnonymousObject) != i);
        int i = 0;
        if (i < i)
        {
          Object localObject1 = Array.get(paramObject, i);
          Object localObject2 = Array.get(paramAnonymousObject, i);
          if (localObject1 == localObject2) {}
          while ((localObject1 == null) || (localObject1.equals(localObject2)))
          {
            i += 1;
            break;
          }
          return false;
        }
        return true;
      }
    };
  }
  
  public static <T> T[] insertInList(T[] paramArrayOfT, T paramT)
  {
    int i = paramArrayOfT.length;
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), i + 1);
    if (i > 0) {
      System.arraycopy(paramArrayOfT, 0, arrayOfObject, 1, i);
    }
    arrayOfObject[0] = paramT;
    return arrayOfObject;
  }
  
  public static <T> T[] insertInListNoDup(T[] paramArrayOfT, T paramT)
  {
    int j = paramArrayOfT.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfT[i] == paramT)
      {
        if (i == 0) {
          paramT = paramArrayOfT;
        }
        do
        {
          return paramT;
          arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), j);
          System.arraycopy(paramArrayOfT, 0, arrayOfObject, 1, i);
          arrayOfObject[0] = paramT;
          i += 1;
          j -= i;
          paramT = arrayOfObject;
        } while (j <= 0);
        System.arraycopy(paramArrayOfT, i, arrayOfObject, i, j);
        return arrayOfObject;
      }
      i += 1;
    }
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), j + 1);
    if (j > 0) {
      System.arraycopy(paramArrayOfT, 0, arrayOfObject, 1, j);
    }
    arrayOfObject[0] = paramT;
    return arrayOfObject;
  }
  
  public static <T> HashSet<T> setAndArray(Set<T> paramSet, T[] paramArrayOfT)
  {
    HashSet localHashSet = new HashSet();
    if (paramSet != null) {
      localHashSet.addAll(paramSet);
    }
    if (paramArrayOfT != null)
    {
      int j = paramArrayOfT.length;
      int i = 0;
      while (i < j)
      {
        localHashSet.add(paramArrayOfT[i]);
        i += 1;
      }
    }
    return localHashSet;
  }
  
  public BooleanBuilder getBooleanBuilder()
  {
    if (_booleanBuilder == null) {
      _booleanBuilder = new BooleanBuilder();
    }
    return _booleanBuilder;
  }
  
  public ByteBuilder getByteBuilder()
  {
    if (_byteBuilder == null) {
      _byteBuilder = new ByteBuilder();
    }
    return _byteBuilder;
  }
  
  public DoubleBuilder getDoubleBuilder()
  {
    if (_doubleBuilder == null) {
      _doubleBuilder = new DoubleBuilder();
    }
    return _doubleBuilder;
  }
  
  public FloatBuilder getFloatBuilder()
  {
    if (_floatBuilder == null) {
      _floatBuilder = new FloatBuilder();
    }
    return _floatBuilder;
  }
  
  public IntBuilder getIntBuilder()
  {
    if (_intBuilder == null) {
      _intBuilder = new IntBuilder();
    }
    return _intBuilder;
  }
  
  public LongBuilder getLongBuilder()
  {
    if (_longBuilder == null) {
      _longBuilder = new LongBuilder();
    }
    return _longBuilder;
  }
  
  public ShortBuilder getShortBuilder()
  {
    if (_shortBuilder == null) {
      _shortBuilder = new ShortBuilder();
    }
    return _shortBuilder;
  }
  
  private static final class ArrayIterator<T>
    implements Iterator<T>, Iterable<T>
  {
    private final T[] _array;
    private int _index;
    
    public ArrayIterator(T[] paramArrayOfT)
    {
      _array = paramArrayOfT;
      _index = 0;
    }
    
    public boolean hasNext()
    {
      return _index < _array.length;
    }
    
    public Iterator<T> iterator()
    {
      return this;
    }
    
    public T next()
    {
      if (_index >= _array.length) {
        throw new NoSuchElementException();
      }
      Object[] arrayOfObject = _array;
      int i = _index;
      _index = (i + 1);
      return (T)arrayOfObject[i];
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
  
  public static final class BooleanBuilder
    extends PrimitiveArrayBuilder<boolean[]>
  {
    public final boolean[] _constructArray(int paramInt)
    {
      return new boolean[paramInt];
    }
  }
  
  public static final class ByteBuilder
    extends PrimitiveArrayBuilder<byte[]>
  {
    public final byte[] _constructArray(int paramInt)
    {
      return new byte[paramInt];
    }
  }
  
  public static final class DoubleBuilder
    extends PrimitiveArrayBuilder<double[]>
  {
    public final double[] _constructArray(int paramInt)
    {
      return new double[paramInt];
    }
  }
  
  public static final class FloatBuilder
    extends PrimitiveArrayBuilder<float[]>
  {
    public final float[] _constructArray(int paramInt)
    {
      return new float[paramInt];
    }
  }
  
  public static final class IntBuilder
    extends PrimitiveArrayBuilder<int[]>
  {
    public final int[] _constructArray(int paramInt)
    {
      return new int[paramInt];
    }
  }
  
  public static final class LongBuilder
    extends PrimitiveArrayBuilder<long[]>
  {
    public final long[] _constructArray(int paramInt)
    {
      return new long[paramInt];
    }
  }
  
  public static final class ShortBuilder
    extends PrimitiveArrayBuilder<short[]>
  {
    public final short[] _constructArray(int paramInt)
    {
      return new short[paramInt];
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ArrayBuilders
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */