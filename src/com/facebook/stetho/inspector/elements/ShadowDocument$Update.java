package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import java.util.ArrayDeque;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class ShadowDocument$Update
  implements DocumentView
{
  private final Map<Object, ElementInfo> mElementToInfoChangesMap;
  private final Set<Object> mRootElementChangesSet;
  
  public ShadowDocument$Update(Map<Object, ElementInfo> paramMap, Set<Object> paramSet)
  {
    mElementToInfoChangesMap = paramSet;
    Set localSet;
    mRootElementChangesSet = localSet;
  }
  
  private void removeSubTree(Map<Object, ElementInfo> paramMap, Object paramObject)
  {
    ElementInfo localElementInfo = (ElementInfo)paramMap.get(paramObject);
    paramMap.remove(paramObject);
    int i = 0;
    int j = children.size();
    while (i < j)
    {
      removeSubTree(paramMap, children.get(i));
      i += 1;
    }
  }
  
  public void abandon()
  {
    if (!ShadowDocument.access$200(this$0)) {
      throw new IllegalStateException();
    }
    ShadowDocument.access$202(this$0, false);
  }
  
  public void commit()
  {
    if (!ShadowDocument.access$200(this$0)) {
      throw new IllegalStateException();
    }
    ShadowDocument.access$000(this$0).putAll(mElementToInfoChangesMap);
    Iterator localIterator = mRootElementChangesSet.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      removeSubTree(ShadowDocument.access$000(this$0), localObject);
    }
    ShadowDocument.access$202(this$0, false);
  }
  
  public void getChangedElements(Accumulator<Object> paramAccumulator)
  {
    Iterator localIterator = mElementToInfoChangesMap.keySet().iterator();
    while (localIterator.hasNext()) {
      paramAccumulator.store(localIterator.next());
    }
  }
  
  public ElementInfo getElementInfo(Object paramObject)
  {
    ElementInfo localElementInfo = (ElementInfo)mElementToInfoChangesMap.get(paramObject);
    if (localElementInfo != null) {
      return localElementInfo;
    }
    return (ElementInfo)ShadowDocument.access$000(this$0).get(paramObject);
  }
  
  public void getGarbageElements(Accumulator<Object> paramAccumulator)
  {
    ArrayDeque localArrayDeque = new ArrayDeque();
    Object localObject1 = mRootElementChangesSet.iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = ((Iterator)localObject1).next();
      ElementInfo localElementInfo = getElementInfo(localObject2);
      if ((localObject2 != ShadowDocument.access$100(this$0)) && (parentElement == null))
      {
        localArrayDeque.add(localObject2);
        localArrayDeque.add(localObject2);
      }
    }
    label221:
    while (!localArrayDeque.isEmpty())
    {
      localObject2 = localArrayDeque.remove();
      localObject1 = localArrayDeque.remove();
      if (localObject2 == localObject1) {
        localObject1 = null;
      }
      for (;;)
      {
        if (getElementInfoparentElement != localObject1) {
          break label221;
        }
        paramAccumulator.store(localObject2);
        localObject1 = this$0.getElementInfo(localObject2);
        if (localObject1 == null) {
          break;
        }
        int i = 0;
        int j = children.size();
        while (i < j)
        {
          localArrayDeque.add(children.get(i));
          localArrayDeque.add(localObject2);
          i += 1;
        }
        break;
      }
    }
  }
  
  public Object getRootElement()
  {
    return this$0.getRootElement();
  }
  
  public boolean isElementChanged(Object paramObject)
  {
    return mElementToInfoChangesMap.containsKey(paramObject);
  }
  
  public boolean isEmpty()
  {
    return mElementToInfoChangesMap.isEmpty();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.ShadowDocument.Update
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */