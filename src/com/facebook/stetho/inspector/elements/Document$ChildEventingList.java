package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import java.util.ArrayList;

final class Document$ChildEventingList
  extends ArrayList<Object>
{
  private DocumentView mDocumentView;
  private Object mParentElement = null;
  private int mParentNodeId = -1;
  
  private Document$ChildEventingList(Document paramDocument) {}
  
  public void acquire(Object paramObject, DocumentView paramDocumentView)
  {
    mParentElement = paramObject;
    if (mParentElement == null) {}
    for (int i = -1;; i = Document.access$500(this$0).getIdForObject(mParentElement).intValue())
    {
      mParentNodeId = i;
      mDocumentView = paramDocumentView;
      return;
    }
  }
  
  public void addWithEvent(int paramInt, Object paramObject, Accumulator<Object> paramAccumulator)
  {
    Object localObject;
    if (paramInt == 0)
    {
      localObject = null;
      if (localObject != null) {
        break label56;
      }
    }
    label56:
    for (int i = -1;; i = Document.access$500(this$0).getIdForObject(localObject).intValue())
    {
      add(paramInt, paramObject);
      Document.access$700(this$0).onChildNodeInserted(mDocumentView, paramObject, mParentNodeId, i, paramAccumulator);
      return;
      localObject = get(paramInt - 1);
      break;
    }
  }
  
  public void release()
  {
    clear();
    mParentElement = null;
    mParentNodeId = -1;
    mDocumentView = null;
  }
  
  public void removeWithEvent(int paramInt)
  {
    Object localObject = remove(paramInt);
    paramInt = Document.access$500(this$0).getIdForObject(localObject).intValue();
    Document.access$700(this$0).onChildNodeRemoved(mParentNodeId, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.ChildEventingList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */