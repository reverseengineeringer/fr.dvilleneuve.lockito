package org.springframework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collection<*>;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public abstract class CollectionUtils
{
  public static List<?> arrayToList(Object paramObject)
  {
    return Arrays.asList(ObjectUtils.toObjectArray(paramObject));
  }
  
  public static boolean contains(Enumeration<?> paramEnumeration, Object paramObject)
  {
    if (paramEnumeration != null) {
      while (paramEnumeration.hasMoreElements()) {
        if (ObjectUtils.nullSafeEquals(paramEnumeration.nextElement(), paramObject)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean contains(Iterator<?> paramIterator, Object paramObject)
  {
    if (paramIterator != null) {
      while (paramIterator.hasNext()) {
        if (ObjectUtils.nullSafeEquals(paramIterator.next(), paramObject)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean containsAny(Collection<?> paramCollection1, Collection<?> paramCollection2)
  {
    if ((isEmpty(paramCollection1)) || (isEmpty(paramCollection2))) {}
    do
    {
      while (!paramCollection2.hasNext())
      {
        return false;
        paramCollection2 = paramCollection2.iterator();
      }
    } while (!paramCollection1.contains(paramCollection2.next()));
    return true;
  }
  
  public static boolean containsInstance(Collection<?> paramCollection, Object paramObject)
  {
    if (paramCollection != null)
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        if (paramCollection.next() == paramObject) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static Class<?> findCommonElementType(Collection<?> paramCollection)
  {
    if (isEmpty(paramCollection))
    {
      localObject = null;
      return (Class<?>)localObject;
    }
    Object localObject = null;
    Iterator localIterator = paramCollection.iterator();
    paramCollection = (Collection<?>)localObject;
    label56:
    do
    {
      for (;;)
      {
        localObject = paramCollection;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject = localIterator.next();
        if (localObject != null)
        {
          if (paramCollection != null) {
            break label56;
          }
          paramCollection = localObject.getClass();
        }
      }
    } while (paramCollection == localObject.getClass());
    return null;
  }
  
  public static Object findFirstMatch(Collection<?> paramCollection1, Collection<?> paramCollection2)
  {
    if ((isEmpty(paramCollection1)) || (isEmpty(paramCollection2))) {
      return null;
    }
    paramCollection2 = paramCollection2.iterator();
    while (paramCollection2.hasNext())
    {
      Object localObject = paramCollection2.next();
      if (paramCollection1.contains(localObject)) {
        return localObject;
      }
    }
    return null;
  }
  
  public static <T> T findValueOfType(Collection<?> paramCollection, Class<T> paramClass)
  {
    if (isEmpty(paramCollection))
    {
      localObject = null;
      return (T)localObject;
    }
    Object localObject = null;
    Iterator localIterator = paramCollection.iterator();
    for (paramCollection = (Collection<?>)localObject;; paramCollection = (Collection<?>)localObject)
    {
      do
      {
        localObject = paramCollection;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject = localIterator.next();
      } while ((paramClass != null) && (!paramClass.isInstance(localObject)));
      if (paramCollection != null) {
        return null;
      }
    }
  }
  
  public static Object findValueOfType(Collection<?> paramCollection, Class<?>[] paramArrayOfClass)
  {
    Object localObject1;
    if ((isEmpty(paramCollection)) || (ObjectUtils.isEmpty(paramArrayOfClass)))
    {
      localObject1 = null;
      return localObject1;
    }
    int j = paramArrayOfClass.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label55;
      }
      Object localObject2 = findValueOfType(paramCollection, paramArrayOfClass[i]);
      localObject1 = localObject2;
      if (localObject2 != null) {
        break;
      }
      i += 1;
    }
    label55:
    return null;
  }
  
  public static boolean hasUniqueObject(Collection<?> paramCollection)
  {
    if (isEmpty(paramCollection)) {
      return false;
    }
    int i = 0;
    Object localObject = null;
    Iterator localIterator = paramCollection.iterator();
    paramCollection = (Collection<?>)localObject;
    while (localIterator.hasNext())
    {
      localObject = localIterator.next();
      if (i == 0)
      {
        i = 1;
        paramCollection = (Collection<?>)localObject;
      }
      else if (paramCollection != localObject)
      {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isEmpty(Collection<?> paramCollection)
  {
    return (paramCollection == null) || (paramCollection.isEmpty());
  }
  
  public static boolean isEmpty(Map<?, ?> paramMap)
  {
    return (paramMap == null) || (paramMap.isEmpty());
  }
  
  public static void mergeArrayIntoCollection(Object paramObject, Collection<Object> paramCollection)
  {
    if (paramCollection == null) {
      throw new IllegalArgumentException("Collection must not be null");
    }
    paramObject = ObjectUtils.toObjectArray(paramObject);
    int j = paramObject.length;
    int i = 0;
    while (i < j)
    {
      paramCollection.add(paramObject[i]);
      i += 1;
    }
  }
  
  public static void mergePropertiesIntoMap(Properties paramProperties, Map<String, Object> paramMap)
  {
    if (paramMap == null) {
      throw new IllegalArgumentException("Map must not be null");
    }
    if (paramProperties != null)
    {
      Enumeration localEnumeration = paramProperties.propertyNames();
      while (localEnumeration.hasMoreElements())
      {
        String str2 = (String)localEnumeration.nextElement();
        String str1 = paramProperties.getProperty(str2);
        Object localObject = str1;
        if (str1 == null) {
          localObject = paramProperties.get(str2);
        }
        paramMap.put(str2, localObject);
      }
    }
  }
  
  public static <A, E extends A> A[] toArray(Enumeration<E> paramEnumeration, A[] paramArrayOfA)
  {
    ArrayList localArrayList = new ArrayList();
    while (paramEnumeration.hasMoreElements()) {
      localArrayList.add(paramEnumeration.nextElement());
    }
    return localArrayList.toArray(paramArrayOfA);
  }
  
  public static <E> Iterator<E> toIterator(Enumeration<E> paramEnumeration)
  {
    return new EnumerationIterator(paramEnumeration);
  }
  
  public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map<K, List<V>> paramMap)
  {
    return new MultiValueMapAdapter(paramMap);
  }
  
  public static <K, V> MultiValueMap<K, V> unmodifiableMultiValueMap(MultiValueMap<? extends K, ? extends V> paramMultiValueMap)
  {
    Assert.notNull(paramMultiValueMap, "'map' must not be null");
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(paramMultiValueMap.size());
    paramMultiValueMap = paramMultiValueMap.entrySet().iterator();
    while (paramMultiValueMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMultiValueMap.next();
      List localList = Collections.unmodifiableList((List)localEntry.getValue());
      localLinkedHashMap.put(localEntry.getKey(), localList);
    }
    return toMultiValueMap(Collections.unmodifiableMap(localLinkedHashMap));
  }
  
  private static class EnumerationIterator<E>
    implements Iterator<E>
  {
    private Enumeration<E> enumeration;
    
    public EnumerationIterator(Enumeration<E> paramEnumeration)
    {
      enumeration = paramEnumeration;
    }
    
    public boolean hasNext()
    {
      return enumeration.hasMoreElements();
    }
    
    public E next()
    {
      return (E)enumeration.nextElement();
    }
    
    public void remove()
      throws UnsupportedOperationException
    {
      throw new UnsupportedOperationException("Not supported");
    }
  }
  
  private static class MultiValueMapAdapter<K, V>
    implements MultiValueMap<K, V>, Serializable
  {
    private static final long serialVersionUID = 1L;
    private final Map<K, List<V>> map;
    
    public MultiValueMapAdapter(Map<K, List<V>> paramMap)
    {
      Assert.notNull(paramMap, "'map' must not be null");
      map = paramMap;
    }
    
    public void add(K paramK, V paramV)
    {
      List localList = (List)map.get(paramK);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new LinkedList();
        map.put(paramK, localObject);
      }
      ((List)localObject).add(paramV);
    }
    
    public void clear()
    {
      map.clear();
    }
    
    public boolean containsKey(Object paramObject)
    {
      return map.containsKey(paramObject);
    }
    
    public boolean containsValue(Object paramObject)
    {
      return map.containsValue(paramObject);
    }
    
    public Set<Map.Entry<K, List<V>>> entrySet()
    {
      return map.entrySet();
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      return map.equals(paramObject);
    }
    
    public List<V> get(Object paramObject)
    {
      return (List)map.get(paramObject);
    }
    
    public V getFirst(K paramK)
    {
      paramK = (List)map.get(paramK);
      if (paramK != null) {
        return (V)paramK.get(0);
      }
      return null;
    }
    
    public int hashCode()
    {
      return map.hashCode();
    }
    
    public boolean isEmpty()
    {
      return map.isEmpty();
    }
    
    public Set<K> keySet()
    {
      return map.keySet();
    }
    
    public List<V> put(K paramK, List<V> paramList)
    {
      return (List)map.put(paramK, paramList);
    }
    
    public void putAll(Map<? extends K, ? extends List<V>> paramMap)
    {
      map.putAll(paramMap);
    }
    
    public List<V> remove(Object paramObject)
    {
      return (List)map.remove(paramObject);
    }
    
    public void set(K paramK, V paramV)
    {
      LinkedList localLinkedList = new LinkedList();
      localLinkedList.add(paramV);
      map.put(paramK, localLinkedList);
    }
    
    public void setAll(Map<K, V> paramMap)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        set(localEntry.getKey(), localEntry.getValue());
      }
    }
    
    public int size()
    {
      return map.size();
    }
    
    public Map<K, V> toSingleValueMap()
    {
      LinkedHashMap localLinkedHashMap = new LinkedHashMap(map.size());
      Iterator localIterator = map.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localLinkedHashMap.put(localEntry.getKey(), ((List)localEntry.getValue()).get(0));
      }
      return localLinkedHashMap;
    }
    
    public String toString()
    {
      return map.toString();
    }
    
    public Collection<List<V>> values()
    {
      return map.values();
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.CollectionUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */