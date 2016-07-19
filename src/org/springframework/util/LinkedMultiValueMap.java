package org.springframework.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LinkedMultiValueMap<K, V>
  implements MultiValueMap<K, V>, Serializable
{
  private static final long serialVersionUID = 3801124242820219131L;
  private final Map<K, List<V>> targetMap;
  
  public LinkedMultiValueMap()
  {
    targetMap = new LinkedHashMap();
  }
  
  public LinkedMultiValueMap(int paramInt)
  {
    targetMap = new LinkedHashMap(paramInt);
  }
  
  public LinkedMultiValueMap(Map<K, List<V>> paramMap)
  {
    targetMap = new LinkedHashMap(paramMap);
  }
  
  public void add(K paramK, V paramV)
  {
    List localList = (List)targetMap.get(paramK);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new LinkedList();
      targetMap.put(paramK, localObject);
    }
    ((List)localObject).add(paramV);
  }
  
  public void clear()
  {
    targetMap.clear();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return targetMap.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject)
  {
    return targetMap.containsValue(paramObject);
  }
  
  public Set<Map.Entry<K, List<V>>> entrySet()
  {
    return targetMap.entrySet();
  }
  
  public boolean equals(Object paramObject)
  {
    return targetMap.equals(paramObject);
  }
  
  public List<V> get(Object paramObject)
  {
    return (List)targetMap.get(paramObject);
  }
  
  public V getFirst(K paramK)
  {
    paramK = (List)targetMap.get(paramK);
    if (paramK != null) {
      return (V)paramK.get(0);
    }
    return null;
  }
  
  public int hashCode()
  {
    return targetMap.hashCode();
  }
  
  public boolean isEmpty()
  {
    return targetMap.isEmpty();
  }
  
  public Set<K> keySet()
  {
    return targetMap.keySet();
  }
  
  public List<V> put(K paramK, List<V> paramList)
  {
    return (List)targetMap.put(paramK, paramList);
  }
  
  public void putAll(Map<? extends K, ? extends List<V>> paramMap)
  {
    targetMap.putAll(paramMap);
  }
  
  public List<V> remove(Object paramObject)
  {
    return (List)targetMap.remove(paramObject);
  }
  
  public void set(K paramK, V paramV)
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(paramV);
    targetMap.put(paramK, localLinkedList);
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
    return targetMap.size();
  }
  
  public Map<K, V> toSingleValueMap()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(targetMap.size());
    Iterator localIterator = targetMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedHashMap.put(localEntry.getKey(), ((List)localEntry.getValue()).get(0));
    }
    return localLinkedHashMap;
  }
  
  public String toString()
  {
    return targetMap.toString();
  }
  
  public Collection<List<V>> values()
  {
    return targetMap.values();
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.LinkedMultiValueMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */