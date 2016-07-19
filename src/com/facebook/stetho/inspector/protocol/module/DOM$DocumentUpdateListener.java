package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.Document.UpdateListener;
import com.facebook.stetho.inspector.elements.DocumentView;
import com.facebook.stetho.inspector.helper.ChromePeerManager;

final class DOM$DocumentUpdateListener
  implements Document.UpdateListener
{
  private DOM$DocumentUpdateListener(DOM paramDOM) {}
  
  public void onAttributeModified(Object paramObject, String paramString1, String paramString2)
  {
    DOM.AttributeModifiedEvent localAttributeModifiedEvent = new DOM.AttributeModifiedEvent(null);
    nodeId = DOM.access$300(this$0).getNodeIdForElement(paramObject).intValue();
    name = paramString1;
    value = paramString2;
    DOM.access$1200(this$0).sendNotificationToPeers("DOM.onAttributeModified", localAttributeModifiedEvent);
  }
  
  public void onAttributeRemoved(Object paramObject, String paramString)
  {
    DOM.AttributeRemovedEvent localAttributeRemovedEvent = new DOM.AttributeRemovedEvent(null);
    nodeId = DOM.access$300(this$0).getNodeIdForElement(paramObject).intValue();
    name = paramString;
    DOM.access$1200(this$0).sendNotificationToPeers("DOM.attributeRemoved", localAttributeRemovedEvent);
  }
  
  public void onChildNodeInserted(DocumentView paramDocumentView, Object paramObject, int paramInt1, int paramInt2, Accumulator<Object> paramAccumulator)
  {
    DOM.ChildNodeInsertedEvent localChildNodeInsertedEvent = DOM.access$1700(this$0);
    parentNodeId = paramInt1;
    previousNodeId = paramInt2;
    node = DOM.access$400(this$0, paramObject, paramDocumentView);
    paramAccumulator.store(paramObject);
    DOM.access$1200(this$0).sendNotificationToPeers("DOM.childNodeInserted", localChildNodeInsertedEvent);
    DOM.access$1800(this$0, localChildNodeInsertedEvent);
  }
  
  public void onChildNodeRemoved(int paramInt1, int paramInt2)
  {
    DOM.ChildNodeRemovedEvent localChildNodeRemovedEvent = DOM.access$1500(this$0);
    parentNodeId = paramInt1;
    nodeId = paramInt2;
    DOM.access$1200(this$0).sendNotificationToPeers("DOM.childNodeRemoved", localChildNodeRemovedEvent);
    DOM.access$1600(this$0, localChildNodeRemovedEvent);
  }
  
  public void onInspectRequested(Object paramObject)
  {
    Integer localInteger = DOM.access$300(this$0).getNodeIdForElement(paramObject);
    if (localInteger == null)
    {
      LogUtil.d("DocumentProvider.Listener.onInspectRequested() called for a non-mapped node: element=%s", new Object[] { paramObject });
      return;
    }
    paramObject = new DOM.InspectNodeRequestedEvent(null);
    nodeId = localInteger.intValue();
    DOM.access$1200(this$0).sendNotificationToPeers("DOM.inspectNodeRequested", paramObject);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.DocumentUpdateListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */