package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ListUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ShadowDocument$UpdateBuilder
{
  private HashSet<Object> mCachedNotNewChildrenSet;
  private final Map<Object, ElementInfo> mElementToInfoChangesMap = new LinkedHashMap();
  private final HashSet<Object> mRootElementChanges = new HashSet();
  
  public ShadowDocument$UpdateBuilder(ShadowDocument paramShadowDocument) {}
  
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
        localElementInfo = (ElementInfo)ShadowDocument.access$000(this$0).get(paramObject1);
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
    return new ShadowDocument.Update(this$0, mElementToInfoChangesMap, mRootElementChanges);
  }
  
  public void setElementChildren(Object paramObject, List<Object> paramList)
  {
    Object localObject2 = (ElementInfo)mElementToInfoChangesMap.get(paramObject);
    if ((localObject2 != null) && (ListUtil.identityEquals(paramList, children))) {}
    ElementInfo localElementInfo;
    do
    {
      return;
      localElementInfo = (ElementInfo)ShadowDocument.access$000(this$0).get(paramObject);
    } while ((localObject2 == null) && (localElementInfo != null) && (ListUtil.identityEquals(paramList, children)));
    Object localObject1;
    if ((localObject2 != null) && (localElementInfo != null) && (parentElement == parentElement) && (ListUtil.identityEquals(paramList, children)))
    {
      paramList = (ElementInfo)ShadowDocument.access$000(this$0).get(paramObject);
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
        localElementInfo = (ElementInfo)ShadowDocument.access$000(this$0).get(localObject2);
        if ((localElementInfo != null) && (parentElement == paramObject)) {
          setElementParent(localObject2, null);
        }
      }
    }
    releaseNotNewChildrenHashSet((HashSet)localObject1);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.ShadowDocument.UpdateBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */