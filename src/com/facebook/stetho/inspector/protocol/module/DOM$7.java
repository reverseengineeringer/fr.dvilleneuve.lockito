package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.inspector.elements.Document;

class DOM$7
  implements Runnable
{
  DOM$7(DOM paramDOM, DOM.PerformSearchRequest paramPerformSearchRequest, ArrayListAccumulator paramArrayListAccumulator) {}
  
  public void run()
  {
    DOM.access$300(this$0).findMatchingElements(val$request.query, val$resultNodeIds);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */