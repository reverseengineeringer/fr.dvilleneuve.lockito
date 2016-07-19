package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import java.util.HashSet;

class Document$4$1
  implements Accumulator<Object>
{
  Document$4$1(Document.4 param4) {}
  
  public void store(Object paramObject)
  {
    if (this$1.val$docUpdate.isElementChanged(paramObject)) {
      Document.4.access$800(this$1).add(paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */