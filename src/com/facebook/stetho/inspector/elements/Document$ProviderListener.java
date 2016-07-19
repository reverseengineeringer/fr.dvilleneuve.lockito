package com.facebook.stetho.inspector.elements;

final class Document$ProviderListener
  implements DocumentProviderListener
{
  private Document$ProviderListener(Document paramDocument) {}
  
  public void onAttributeModified(Object paramObject, String paramString1, String paramString2)
  {
    this$0.verifyThreadAccess();
    Document.access$700(this$0).onAttributeModified(paramObject, paramString1, paramString2);
  }
  
  public void onAttributeRemoved(Object paramObject, String paramString)
  {
    this$0.verifyThreadAccess();
    Document.access$700(this$0).onAttributeRemoved(paramObject, paramString);
  }
  
  public void onInspectRequested(Object paramObject)
  {
    this$0.verifyThreadAccess();
    Document.access$700(this$0).onInspectRequested(paramObject);
  }
  
  public void onPossiblyChanged()
  {
    Document.access$1200(this$0);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.ProviderListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */