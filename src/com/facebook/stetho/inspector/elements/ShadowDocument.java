package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.Util;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class ShadowDocument
  implements DocumentView
{
  private final IdentityHashMap<Object, ElementInfo> mElementToInfoMap = new IdentityHashMap();
  private boolean mIsUpdating;
  private final Object mRootElement;
  
  public ShadowDocument(Object paramObject)
  {
    mRootElement = Util.throwIfNull(paramObject);
  }
  
  public UpdateBuilder beginUpdate()
  {
    if (mIsUpdating) {
      throw new IllegalStateException();
    }
    mIsUpdating = true;
    return new UpdateBuilder();
  }
  
  public ElementInfo getElementInfo(Object paramObject)
  {
    return (ElementInfo)mElementToInfoMap.get(paramObject);
  }
  
  public Object getRootElement()
  {
    return mRootElement;
  }
  
  public final class Update
    implements DocumentView
  {
    private final Map<Object, ElementInfo> mElementToInfoChangesMap;
    private final Set<Object> mRootElementChangesSet;
    
    public Update(Set<Object> paramSet)
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
      if (!mIsUpdating) {
        throw new IllegalStateException();
      }
      ShadowDocument.access$202(ShadowDocument.this, false);
    }
    
    public void commit()
    {
      if (!mIsUpdating) {
        throw new IllegalStateException();
      }
      mElementToInfoMap.putAll(mElementToInfoChangesMap);
      Iterator localIterator = mRootElementChangesSet.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        removeSubTree(mElementToInfoMap, localObject);
      }
      ShadowDocument.access$202(ShadowDocument.this, false);
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
      return (ElementInfo)mElementToInfoMap.get(paramObject);
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
        if ((localObject2 != mRootElement) && (parentElement == null))
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
          localObject1 = ShadowDocument.this.getElementInfo(localObject2);
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
      return ShadowDocument.this.getRootElement();
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
  
  public final class UpdateBuilder
  {
    private HashSet<Object> mCachedNotNewChildrenSet;
    private final Map<Object, ElementInfo> mElementToInfoChangesMap = new LinkedHashMap();
    private final HashSet<Object> mRootElementChanges = new HashSet();
    
    public UpdateBuilder() {}
    
    private HashSet<Object> acquireNotNewChildrenHashSet()
    {
      HashSet localHashSet2 = mCachedNotNewChildrenSet;
      HashSet localHashSet1 = localHashSet2;
      if (localHashSet2 == null) {
        localHashSet1 = new HashSet();
      }
      mCachedNotNewChildrenSet = null;
      return localHashSet1;
    }
    
    private void releaseNotNewChildrenHashSet(HashSet<Object> paramHashSet)
    {
      paramHashSet.clear();
      if (mCachedNotNewChildrenSet == null) {
        mCachedNotNewChildrenSet = paramHashSet;
      }
    }
    
    private void setElementParent(Object paramObject1, Object paramObject2)
    {
      Object localObject = (ElementInfo)mElementToInfoChangesMap.get(paramObject1);
      if ((localObject != null) && (paramObject2 == parentElement)) {}
      ElementInfo localElementInfo;
      do
      {
        do
        {
          return;
          localElementInfo = (ElementInfo)mElementToInfoMap.get(paramObject1);
        } while ((localObject == null) && (localElementInfo != null) && (paramObject2 == parentElement));
        if ((localObject == null) || (localElementInfo == null) || (paramObject2 != parentElement) || (!ListUtil.identityEquals(children, children))) {
          break;
        }
        mElementToInfoChangesMap.remove(paramObject1);
      } while (paramObject2 != null);
      mRootElementChanges.remove(paramObject1);
      return;
      if (localObject != null) {
        localObject = children;
      }
      for (;;)
      {
        localObject = new ElementInfo(paramObject1, paramObject2, (List)localObject);
        mElementToInfoChangesMap.put(paramObject1, localObject);
        if (paramObject2 != null) {
          break;
        }
        mRootElementChanges.add(paramObject1);
        return;
        if (localElementInfo != null) {
          localObject = children;
        } else {
          localObject = Collections.emptyList();
        }
      }
    }
    
    public ShadowDocument.Update build()
    {
      return new ShadowDocument.Update(ShadowDocument.this, mElementToInfoChangesMap, mRootElementChanges);
    }
    
    public void setElementChildren(Object paramObject, List<Object> paramList)
    {
      Object localObject2 = (ElementInfo)mElementToInfoChangesMap.get(paramObject);
      if ((localObject2 != null) && (ListUtil.identityEquals(paramList, children))) {}
      ElementInfo localElementInfo;
      do
      {
        return;
        localElementInfo = (ElementInfo)mElementToInfoMap.get(paramObject);
      } while ((localObject2 == null) && (localElementInfo != null) && (ListUtil.identityEquals(paramList, children)));
      Object localObject1;
      if ((localObject2 != null) && (localElementInfo != null) && (parentElement == parentElement) && (ListUtil.identityEquals(paramList, children)))
      {
        paramList = (ElementInfo)mElementToInfoMap.get(paramObject);
        mElementToInfoChangesMap.remove(paramObject);
        localObject1 = acquireNotNewChildrenHashSet();
        if ((localElementInfo != null) && (children != children))
        {
          i = 0;
          j = children.size();
        }
      }
      else
      {
        while (i < j)
        {
          ((HashSet)localObject1).add(children.get(i));
          i += 1;
          continue;
          if (localObject2 != null) {
            localObject1 = parentElement;
          }
          for (;;)
          {
            paramList = new ElementInfo(paramObject, localObject1, paramList);
            mElementToInfoChangesMap.put(paramObject, paramList);
            break;
            if (localElementInfo != null) {
              localObject1 = parentElement;
            } else {
              localObject1 = null;
            }
          }
        }
      }
      if ((localObject2 != null) && (children != children))
      {
        i = 0;
        j = children.size();
        while (i < j)
        {
          ((HashSet)localObject1).add(children.get(i));
          i += 1;
        }
      }
      int i = 0;
      int j = children.size();
      while (i < j)
      {
        localObject2 = children.get(i);
        setElementParent(localObject2, paramObject);
        ((HashSet)localObject1).remove(localObject2);
        i += 1;
      }
      paramList = ((HashSet)localObject1).iterator();
      while (paramList.hasNext())
      {
        localObject2 = paramList.next();
        localElementInfo = (ElementInfo)mElementToInfoChangesMap.get(localObject2);
        if ((localElementInfo == null) || (parentElement == paramObject))
        {
          localElementInfo = (ElementInfo)mElementToInfoMap.get(localObject2);
          if ((localElementInfo != null) && (parentElement == paramObject)) {
            setElementParent(localObject2, null);
          }
        }
      }
      releaseNotNewChildrenHashSet((HashSet)localObject1);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.ShadowDocument
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */