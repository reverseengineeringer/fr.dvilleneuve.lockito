package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.inspector.elements.Document;

class DOM$1
  implements UncheckedCallable<DOM.Node>
{
  DOM$1(DOM paramDOM) {}
  
  public DOM.Node call()
  {
    Object localObject = DOM.access$300(this$0).getRootElement();
    return DOM.access$400(this$0, localObject, DOM.access$300(this$0).getDocumentView());
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */