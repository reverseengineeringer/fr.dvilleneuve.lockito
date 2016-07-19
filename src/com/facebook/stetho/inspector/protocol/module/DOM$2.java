package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.Document;

class DOM$2
  implements Runnable
{
  DOM$2(DOM paramDOM, DOM.HighlightNodeRequest paramHighlightNodeRequest, DOM.RGBAColor paramRGBAColor) {}
  
  public void run()
  {
    Object localObject = DOM.access$300(this$0).getElementForNodeId(val$request.nodeId.intValue());
    if (localObject != null) {
      DOM.access$300(this$0).highlightElement(localObject, val$contentColor.getColor());
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */