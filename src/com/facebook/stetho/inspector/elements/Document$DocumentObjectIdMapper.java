package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.inspector.helper.ObjectIdMapper;

final class Document$DocumentObjectIdMapper
  extends ObjectIdMapper
{
  private Document$DocumentObjectIdMapper(Document paramDocument) {}
  
  protected void onMapped(Object paramObject, int paramInt)
  {
    this$0.verifyThreadAccess();
    Document.access$200(this$0).getNodeDescriptor(paramObject).hook(paramObject);
  }
  
  protected void onUnmapped(Object paramObject, int paramInt)
  {
    this$0.verifyThreadAccess();
    Document.access$200(this$0).getNodeDescriptor(paramObject).unhook(paramObject);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.DocumentObjectIdMapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */