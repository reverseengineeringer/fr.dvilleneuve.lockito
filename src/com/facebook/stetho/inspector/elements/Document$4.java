package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Document$4
  implements Accumulator<Object>
{
  private Accumulator<Object> insertedElements = new Accumulator()
  {
    public void store(Object paramAnonymousObject)
    {
      if (val$docUpdate.isElementChanged(paramAnonymousObject)) {
        listenerInsertedElements.add(paramAnonymousObject);
      }
    }
  };
  private final HashSet<Object> listenerInsertedElements = new HashSet();
  
  Document$4(Document paramDocument, ShadowDocument.Update paramUpdate) {}
  
  public void store(Object paramObject)
  {
    if (!Document.access$500(this$0).containsObject(paramObject)) {}
    while (listenerInsertedElements.contains(paramObject)) {
      return;
    }
    Object localObject2 = val$docUpdate.getElementInfo(paramObject);
    Object localObject1 = Document.access$100(this$0).getElementInfo(paramObject);
    if (localObject1 != null) {}
    for (localObject1 = children;; localObject1 = Collections.emptyList())
    {
      localObject2 = children;
      paramObject = Document.access$900(this$0, paramObject, val$docUpdate);
      int i = 0;
      int j = ((List)localObject1).size();
      while (i < j)
      {
        Object localObject3 = ((List)localObject1).get(i);
        if (Document.access$500(this$0).containsObject(localObject3)) {
          ((Document.ChildEventingList)paramObject).add(localObject3);
        }
        i += 1;
      }
    }
    Document.access$1000((Document.ChildEventingList)paramObject, (List)localObject2, insertedElements);
    Document.access$1100(this$0, (Document.ChildEventingList)paramObject);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */