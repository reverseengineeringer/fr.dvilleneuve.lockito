package org.joda.time.convert;

class ConverterSet
{
  private final Converter[] iConverters;
  private Entry[] iSelectEntries;
  
  ConverterSet(Converter[] paramArrayOfConverter)
  {
    iConverters = paramArrayOfConverter;
    iSelectEntries = new Entry[16];
  }
  
  private static Converter selectSlow(ConverterSet paramConverterSet, Class<?> paramClass)
  {
    Object localObject2 = iConverters;
    int i = localObject2.length;
    int j = i;
    Object localObject1 = paramConverterSet;
    paramConverterSet = (ConverterSet)localObject2;
    int k;
    Object localObject3;
    for (;;)
    {
      k = j - 1;
      if (k < 0) {
        break;
      }
      localObject2 = paramConverterSet[k];
      localObject3 = ((Converter)localObject2).getSupportedType();
      if (localObject3 == paramClass) {
        return (Converter)localObject2;
      }
      if (localObject3 != null)
      {
        j = k;
        if (paramClass != null)
        {
          j = k;
          if (((Class)localObject3).isAssignableFrom(paramClass)) {}
        }
      }
      else
      {
        localObject1 = ((ConverterSet)localObject1).remove(k, null);
        paramConverterSet = iConverters;
        i = paramConverterSet.length;
        j = k;
      }
    }
    if ((paramClass == null) || (i == 0)) {
      return null;
    }
    if (i == 1) {
      return paramConverterSet[0];
    }
    int m = i;
    m -= 1;
    if (m >= 0)
    {
      Class localClass = paramConverterSet[m].getSupportedType();
      j = i;
      localObject3 = localObject1;
      k = i;
      i = j;
      j = m;
      localObject2 = paramConverterSet;
      for (;;)
      {
        int n = i - 1;
        paramConverterSet = (ConverterSet)localObject2;
        m = j;
        i = k;
        localObject1 = localObject3;
        if (n < 0) {
          break;
        }
        i = n;
        if (n != j)
        {
          i = n;
          if (localObject2[n].getSupportedType().isAssignableFrom(localClass))
          {
            localObject3 = ((ConverterSet)localObject3).remove(n, null);
            localObject2 = iConverters;
            k = localObject2.length;
            j = k - 1;
            i = n;
          }
        }
      }
    }
    if (i == 1) {
      return paramConverterSet[0];
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Unable to find best converter for type \"");
    ((StringBuilder)localObject1).append(paramClass.getName());
    ((StringBuilder)localObject1).append("\" from remaining set: ");
    j = 0;
    if (j < i)
    {
      paramClass = paramConverterSet[j];
      localObject2 = paramClass.getSupportedType();
      ((StringBuilder)localObject1).append(paramClass.getClass().getName());
      ((StringBuilder)localObject1).append('[');
      if (localObject2 == null) {}
      for (paramClass = null;; paramClass = ((Class)localObject2).getName())
      {
        ((StringBuilder)localObject1).append(paramClass);
        ((StringBuilder)localObject1).append("], ");
        j += 1;
        break;
      }
    }
    throw new IllegalStateException(((StringBuilder)localObject1).toString());
  }
  
  ConverterSet add(Converter paramConverter, Converter[] paramArrayOfConverter)
  {
    Converter[] arrayOfConverter1 = iConverters;
    int k = arrayOfConverter1.length;
    int i = 0;
    while (i < k)
    {
      localObject = arrayOfConverter1[i];
      if (paramConverter.equals(localObject))
      {
        if (paramArrayOfConverter != null) {
          paramArrayOfConverter[0] = null;
        }
        return this;
      }
      if (paramConverter.getSupportedType() == ((Converter)localObject).getSupportedType())
      {
        Converter[] arrayOfConverter2 = new Converter[k];
        int j = 0;
        if (j < k)
        {
          if (j != i) {
            arrayOfConverter2[j] = arrayOfConverter1[j];
          }
          for (;;)
          {
            j += 1;
            break;
            arrayOfConverter2[j] = paramConverter;
          }
        }
        if (paramArrayOfConverter != null) {
          paramArrayOfConverter[0] = localObject;
        }
        return new ConverterSet(arrayOfConverter2);
      }
      i += 1;
    }
    Object localObject = new Converter[k + 1];
    System.arraycopy(arrayOfConverter1, 0, localObject, 0, k);
    localObject[k] = paramConverter;
    if (paramArrayOfConverter != null) {
      paramArrayOfConverter[0] = null;
    }
    return new ConverterSet((Converter[])localObject);
  }
  
  void copyInto(Converter[] paramArrayOfConverter)
  {
    System.arraycopy(iConverters, 0, paramArrayOfConverter, 0, iConverters.length);
  }
  
  ConverterSet remove(int paramInt, Converter[] paramArrayOfConverter)
  {
    Converter[] arrayOfConverter = iConverters;
    int m = arrayOfConverter.length;
    if (paramInt >= m) {
      throw new IndexOutOfBoundsException();
    }
    if (paramArrayOfConverter != null) {
      paramArrayOfConverter[0] = arrayOfConverter[paramInt];
    }
    paramArrayOfConverter = new Converter[m - 1];
    int j = 0;
    int i = 0;
    if (j < m)
    {
      if (j == paramInt) {
        break label96;
      }
      int k = i + 1;
      paramArrayOfConverter[i] = arrayOfConverter[j];
      i = k;
    }
    label96:
    for (;;)
    {
      j += 1;
      break;
      return new ConverterSet(paramArrayOfConverter);
    }
  }
  
  ConverterSet remove(Converter paramConverter, Converter[] paramArrayOfConverter)
  {
    Converter[] arrayOfConverter = iConverters;
    int j = arrayOfConverter.length;
    int i = 0;
    if (i < j) {
      if (paramConverter.equals(arrayOfConverter[i])) {
        paramConverter = remove(i, paramArrayOfConverter);
      }
    }
    do
    {
      return paramConverter;
      i += 1;
      break;
      paramConverter = this;
    } while (paramArrayOfConverter == null);
    paramArrayOfConverter[0] = null;
    return this;
  }
  
  Converter select(Class<?> paramClass)
    throws IllegalStateException
  {
    Entry[] arrayOfEntry = iSelectEntries;
    int m = arrayOfEntry.length;
    if (paramClass == null) {
      i = 0;
    }
    for (;;)
    {
      localObject = arrayOfEntry[i];
      if (localObject == null) {
        break;
      }
      if (iType == paramClass)
      {
        return iConverter;
        i = paramClass.hashCode() & m - 1;
      }
      else
      {
        j = i + 1;
        i = j;
        if (j >= m) {
          i = 0;
        }
      }
    }
    Object localObject = selectSlow(this, paramClass);
    Entry localEntry = new Entry(paramClass, (Converter)localObject);
    paramClass = (Entry[])arrayOfEntry.clone();
    paramClass[i] = localEntry;
    int i = 0;
    while (i < m)
    {
      if (paramClass[i] == null)
      {
        iSelectEntries = paramClass;
        return (Converter)localObject;
      }
      i += 1;
    }
    int n = m << 1;
    arrayOfEntry = new Entry[n];
    int j = 0;
    while (j < m)
    {
      localEntry = paramClass[j];
      Class localClass = iType;
      if (localClass == null) {
        i = 0;
      }
      while (arrayOfEntry[i] != null)
      {
        int k = i + 1;
        i = k;
        if (k >= n)
        {
          i = 0;
          continue;
          i = localClass.hashCode() & n - 1;
        }
      }
      arrayOfEntry[i] = localEntry;
      j += 1;
    }
    iSelectEntries = arrayOfEntry;
    return (Converter)localObject;
  }
  
  int size()
  {
    return iConverters.length;
  }
  
  static class Entry
  {
    final Converter iConverter;
    final Class<?> iType;
    
    Entry(Class<?> paramClass, Converter paramConverter)
    {
      iType = paramClass;
      iConverter = paramConverter;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.ConverterSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */