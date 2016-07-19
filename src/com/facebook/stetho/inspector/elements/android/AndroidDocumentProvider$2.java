package com.facebook.stetho.inspector.elements.android;

import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.Descriptor;

class AndroidDocumentProvider$2
  implements Accumulator<Object>
{
  AndroidDocumentProvider$2(AndroidDocumentProvider paramAndroidDocumentProvider, Accumulator paramAccumulator) {}
  
  public void store(Object paramObject)
  {
    if ((paramObject instanceof Window)) {
      val$accumulator.store((Window)paramObject);
    }
    Descriptor localDescriptor;
    do
    {
      return;
      localDescriptor = this$0.getDescriptor(paramObject);
    } while (localDescriptor == null);
    localDescriptor.getChildren(paramObject, this);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */