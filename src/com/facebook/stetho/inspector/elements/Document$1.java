package com.facebook.stetho.inspector.elements;

class Document$1
  implements Runnable
{
  Document$1(Document paramDocument) {}
  
  public void run()
  {
    Document.access$102(this$0, new ShadowDocument(Document.access$200(this$0).getRootElement()));
    Document.access$300(this$0).commit();
    Document.access$200(this$0).setListener(new Document.ProviderListener(this$0, null));
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */