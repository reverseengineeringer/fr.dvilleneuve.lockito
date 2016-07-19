package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import java.util.ArrayList;
import java.util.List;

class Document$UpdateListenerCollection
  implements Document.UpdateListener
{
  private final List<Document.UpdateListener> mListeners = new ArrayList();
  private volatile Document.UpdateListener[] mListenersSnapshot;
  
  public Document$UpdateListenerCollection(Document paramDocument) {}
  
  private Document.UpdateListener[] getListenersSnapshot()
  {
    for (;;)
    {
      Document.UpdateListener[] arrayOfUpdateListener = mListenersSnapshot;
      if (arrayOfUpdateListener != null) {
        return arrayOfUpdateListener;
      }
      try
      {
        if (mListenersSnapshot == null)
        {
          mListenersSnapshot = ((Document.UpdateListener[])mListeners.toArray(new Document.UpdateListener[mListeners.size()]));
          arrayOfUpdateListener = mListenersSnapshot;
          return arrayOfUpdateListener;
        }
      }
      finally {}
    }
  }
  
  public void add(Document.UpdateListener paramUpdateListener)
  {
    try
    {
      mListeners.add(paramUpdateListener);
      mListenersSnapshot = null;
      return;
    }
    finally
    {
      paramUpdateListener = finally;
      throw paramUpdateListener;
    }
  }
  
  public void clear()
  {
    try
    {
      mListeners.clear();
      mListenersSnapshot = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void onAttributeModified(Object paramObject, String paramString1, String paramString2)
  {
    Document.UpdateListener[] arrayOfUpdateListener = getListenersSnapshot();
    int j = arrayOfUpdateListener.length;
    int i = 0;
    while (i < j)
    {
      arrayOfUpdateListener[i].onAttributeModified(paramObject, paramString1, paramString2);
      i += 1;
    }
  }
  
  public void onAttributeRemoved(Object paramObject, String paramString)
  {
    Document.UpdateListener[] arrayOfUpdateListener = getListenersSnapshot();
    int j = arrayOfUpdateListener.length;
    int i = 0;
    while (i < j)
    {
      arrayOfUpdateListener[i].onAttributeRemoved(paramObject, paramString);
      i += 1;
    }
  }
  
  public void onChildNodeInserted(DocumentView paramDocumentView, Object paramObject, int paramInt1, int paramInt2, Accumulator<Object> paramAccumulator)
  {
    Document.UpdateListener[] arrayOfUpdateListener = getListenersSnapshot();
    int j = arrayOfUpdateListener.length;
    int i = 0;
    while (i < j)
    {
      arrayOfUpdateListener[i].onChildNodeInserted(paramDocumentView, paramObject, paramInt1, paramInt2, paramAccumulator);
      i += 1;
    }
  }
  
  public void onChildNodeRemoved(int paramInt1, int paramInt2)
  {
    Document.UpdateListener[] arrayOfUpdateListener = getListenersSnapshot();
    int j = arrayOfUpdateListener.length;
    int i = 0;
    while (i < j)
    {
      arrayOfUpdateListener[i].onChildNodeRemoved(paramInt1, paramInt2);
      i += 1;
    }
  }
  
  public void onInspectRequested(Object paramObject)
  {
    Document.UpdateListener[] arrayOfUpdateListener = getListenersSnapshot();
    int j = arrayOfUpdateListener.length;
    int i = 0;
    while (i < j)
    {
      arrayOfUpdateListener[i].onInspectRequested(paramObject);
      i += 1;
    }
  }
  
  public void remove(Document.UpdateListener paramUpdateListener)
  {
    try
    {
      mListeners.remove(paramUpdateListener);
      mListenersSnapshot = null;
      return;
    }
    finally
    {
      paramUpdateListener = finally;
      throw paramUpdateListener;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.UpdateListenerCollection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */