package com.facebook.stetho.inspector.elements;

import java.util.ArrayList;

public final class Document$AttributeListAccumulator
  extends ArrayList<String>
  implements AttributeAccumulator
{
  public void store(String paramString1, String paramString2)
  {
    add(paramString1);
    add(paramString2);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.AttributeListAccumulator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */