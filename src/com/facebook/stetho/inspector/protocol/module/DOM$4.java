package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.inspector.elements.Document;

class DOM$4
  implements UncheckedCallable<Object>
{
  DOM$4(DOM paramDOM, DOM.ResolveNodeRequest paramResolveNodeRequest) {}
  
  public Object call()
  {
    return DOM.access$300(this$0).getElementForNodeId(val$request.nodeId);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */