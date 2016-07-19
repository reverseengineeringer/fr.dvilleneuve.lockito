package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.Document;

class DOM$5
  implements Runnable
{
  DOM$5(DOM paramDOM, DOM.SetAttributesAsTextRequest paramSetAttributesAsTextRequest) {}
  
  public void run()
  {
    Object localObject = DOM.access$300(this$0).getElementForNodeId(val$request.nodeId);
    if (localObject != null) {
      DOM.access$300(this$0).setAttributesAsText(localObject, val$request.text);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */