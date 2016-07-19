package com.facebook.stetho.inspector.elements;

import android.os.SystemClock;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class Document
  extends ThreadBoundProxy
{
  private AttributeListAccumulator mCachedAttributeAccumulator;
  private ChildEventingList mCachedChildEventingList;
  private ArrayListAccumulator<Object> mCachedChildrenAccumulator;
  private final Queue<Object> mCachedUpdateQueue;
  private DocumentProvider mDocumentProvider;
  private final DocumentProviderFactory mFactory;
  private final ObjectIdMapper mObjectIdMapper;
  @GuardedBy("this")
  private int mReferenceCounter;
  private ShadowDocument mShadowDocument;
  private UpdateListenerCollection mUpdateListeners;
  
  public Document(DocumentProviderFactory paramDocumentProviderFactory)
  {
    super(paramDocumentProviderFactory);
    mFactory = paramDocumentProviderFactory;
    mObjectIdMapper = new DocumentObjectIdMapper(null);
    mReferenceCounter = 0;
    mUpdateListeners = new UpdateListenerCollection();
    mCachedUpdateQueue = new ArrayDeque();
  }
  
  private AttributeListAccumulator acquireCachedAttributeAccumulator()
  {
    AttributeListAccumulator localAttributeListAccumulator2 = mCachedAttributeAccumulator;
    AttributeListAccumulator localAttributeListAccumulator1 = localAttributeListAccumulator2;
    if (localAttributeListAccumulator2 == null) {
      localAttributeListAccumulator1 = new AttributeListAccumulator();
    }
    mCachedChildrenAccumulator = null;
    return localAttributeListAccumulator1;
  }
  
  private ChildEventingList acquireChildEventingList(Object paramObject, DocumentView paramDocumentView)
  {
    ChildEventingList localChildEventingList2 = mCachedChildEventingList;
    ChildEventingList localChildEventingList1 = localChildEventingList2;
    if (localChildEventingList2 == null) {
      localChildEventingList1 = new ChildEventingList(null);
    }
    mCachedChildEventingList = null;
    localChildEventingList1.acquire(paramObject, paramDocumentView);
    return localChildEventingList1;
  }
  
  private ArrayListAccumulator<Object> acquireChildrenAccumulator()
  {
    ArrayListAccumulator localArrayListAccumulator2 = mCachedChildrenAccumulator;
    ArrayListAccumulator localArrayListAccumulator1 = localArrayListAccumulator2;
    if (localArrayListAccumulator2 == null) {
      localArrayListAccumulator1 = new ArrayListAccumulator();
    }
    mCachedChildrenAccumulator = null;
    return localArrayListAccumulator1;
  }
  
  private void applyDocumentUpdate(final ShadowDocument.Update paramUpdate)
  {
    paramUpdate.getGarbageElements(new Accumulator()
    {
      public void store(Object paramAnonymousObject)
      {
        if (!mObjectIdMapper.containsObject(paramAnonymousObject)) {
          throw new IllegalStateException();
        }
        if (paramUpdategetElementInfoparentElement == null)
        {
          ElementInfo localElementInfo = mShadowDocument.getElementInfo(paramAnonymousObject);
          int i = mObjectIdMapper.getIdForObject(parentElement).intValue();
          int j = mObjectIdMapper.getIdForObject(paramAnonymousObject).intValue();
          mUpdateListeners.onChildNodeRemoved(i, j);
        }
        mObjectIdMapper.removeObject(paramAnonymousObject);
      }
    });
    paramUpdate.getChangedElements(new Accumulator()
    {
      private Accumulator<Object> insertedElements = new Accumulator()
      {
        public void store(Object paramAnonymous2Object)
        {
          if (val$docUpdate.isElementChanged(paramAnonymous2Object)) {
            listenerInsertedElements.add(paramAnonymous2Object);
          }
        }
      };
      private final HashSet<Object> listenerInsertedElements = new HashSet();
      
      public void store(Object paramAnonymousObject)
      {
        if (!mObjectIdMapper.containsObject(paramAnonymousObject)) {}
        while (listenerInsertedElements.contains(paramAnonymousObject)) {
          return;
        }
        Object localObject2 = paramUpdate.getElementInfo(paramAnonymousObject);
        Object localObject1 = mShadowDocument.getElementInfo(paramAnonymousObject);
        if (localObject1 != null) {}
        for (localObject1 = children;; localObject1 = Collections.emptyList())
        {
          localObject2 = children;
          paramAnonymousObject = Document.this.acquireChildEventingList(paramAnonymousObject, paramUpdate);
          int i = 0;
          int j = ((List)localObject1).size();
          while (i < j)
          {
            Object localObject3 = ((List)localObject1).get(i);
            if (mObjectIdMapper.containsObject(localObject3)) {
              ((Document.ChildEventingList)paramAnonymousObject).add(localObject3);
            }
            i += 1;
          }
        }
        Document.updateListenerChildren((Document.ChildEventingList)paramAnonymousObject, (List)localObject2, insertedElements);
        Document.this.releaseChildEventingList((Document.ChildEventingList)paramAnonymousObject);
      }
    });
    paramUpdate.commit();
  }
  
  private void cleanUp()
  {
    mDocumentProvider.postAndWait(new Runnable()
    {
      public void run()
      {
        mDocumentProvider.setListener(null);
        Document.access$102(Document.this, null);
        mObjectIdMapper.clear();
        mDocumentProvider.dispose();
        Document.access$202(Document.this, null);
      }
    });
    mUpdateListeners.clear();
  }
  
  private ShadowDocument.Update createShadowDocumentUpdate()
  {
    verifyThreadAccess();
    if (mDocumentProvider.getRootElement() != mShadowDocument.getRootElement()) {
      throw new IllegalStateException();
    }
    ArrayListAccumulator localArrayListAccumulator = acquireChildrenAccumulator();
    ShadowDocument.UpdateBuilder localUpdateBuilder = mShadowDocument.beginUpdate();
    mCachedUpdateQueue.add(mDocumentProvider.getRootElement());
    while (!mCachedUpdateQueue.isEmpty())
    {
      Object localObject1 = mCachedUpdateQueue.remove();
      NodeDescriptor localNodeDescriptor = mDocumentProvider.getNodeDescriptor(localObject1);
      mObjectIdMapper.putObject(localObject1);
      localNodeDescriptor.getChildren(localObject1, localArrayListAccumulator);
      int i = 0;
      int j = localArrayListAccumulator.size();
      if (i < j)
      {
        Object localObject2 = localArrayListAccumulator.get(i);
        if (localObject2 != null) {
          mCachedUpdateQueue.add(localObject2);
        }
        for (;;)
        {
          i += 1;
          break;
          LogUtil.e("%s.getChildren() emitted a null child at position %s for element %s", new Object[] { localNodeDescriptor.getClass().getName(), Integer.toString(i), localObject1 });
          localArrayListAccumulator.remove(i);
          i -= 1;
          j -= 1;
        }
      }
      localUpdateBuilder.setElementChildren(localObject1, localArrayListAccumulator);
      localArrayListAccumulator.clear();
    }
    releaseChildrenAccumulator(localArrayListAccumulator);
    return localUpdateBuilder.build();
  }
  
  private boolean doesElementMatch(Object paramObject, Pattern paramPattern)
  {
    AttributeListAccumulator localAttributeListAccumulator = acquireCachedAttributeAccumulator();
    NodeDescriptor localNodeDescriptor = mDocumentProvider.getNodeDescriptor(paramObject);
    localNodeDescriptor.getAttributes(paramObject, localAttributeListAccumulator);
    int i = 0;
    int j = localAttributeListAccumulator.size();
    while (i < j)
    {
      if (paramPattern.matcher((CharSequence)localAttributeListAccumulator.get(i)).find())
      {
        releaseCachedAttributeAccumulator(localAttributeListAccumulator);
        return true;
      }
      i += 1;
    }
    releaseCachedAttributeAccumulator(localAttributeListAccumulator);
    return paramPattern.matcher(localNodeDescriptor.getNodeName(paramObject)).find();
  }
  
  private void findMatches(Object paramObject, Pattern paramPattern, Accumulator<Integer> paramAccumulator)
  {
    paramObject = mShadowDocument.getElementInfo(paramObject);
    int i = 0;
    int j = children.size();
    while (i < j)
    {
      Object localObject = children.get(i);
      if (doesElementMatch(localObject, paramPattern)) {
        paramAccumulator.store(mObjectIdMapper.getIdForObject(localObject));
      }
      findMatches(localObject, paramPattern, paramAccumulator);
      i += 1;
    }
  }
  
  private void init()
  {
    mDocumentProvider = mFactory.create();
    mDocumentProvider.postAndWait(new Runnable()
    {
      public void run()
      {
        Document.access$102(Document.this, new ShadowDocument(mDocumentProvider.getRootElement()));
        Document.this.createShadowDocumentUpdate().commit();
        mDocumentProvider.setListener(new Document.ProviderListener(Document.this, null));
      }
    });
  }
  
  private void releaseCachedAttributeAccumulator(AttributeListAccumulator paramAttributeListAccumulator)
  {
    paramAttributeListAccumulator.clear();
    if (mCachedAttributeAccumulator == null) {
      mCachedAttributeAccumulator = paramAttributeListAccumulator;
    }
  }
  
  private void releaseChildEventingList(ChildEventingList paramChildEventingList)
  {
    paramChildEventingList.release();
    if (mCachedChildEventingList == null) {
      mCachedChildEventingList = paramChildEventingList;
    }
  }
  
  private void releaseChildrenAccumulator(ArrayListAccumulator<Object> paramArrayListAccumulator)
  {
    paramArrayListAccumulator.clear();
    if (mCachedChildrenAccumulator == null) {
      mCachedChildrenAccumulator = paramArrayListAccumulator;
    }
  }
  
  private static void updateListenerChildren(ChildEventingList paramChildEventingList, List<Object> paramList, Accumulator<Object> paramAccumulator)
  {
    int i = 0;
    for (;;)
    {
      if (i <= paramChildEventingList.size())
      {
        if (i != paramChildEventingList.size()) {
          break label49;
        }
        if (i != paramList.size()) {}
      }
      else
      {
        return;
      }
      paramChildEventingList.addWithEvent(i, paramList.get(i), paramAccumulator);
      i += 1;
      continue;
      label49:
      if (i == paramList.size())
      {
        paramChildEventingList.removeWithEvent(i);
      }
      else
      {
        Object localObject1 = paramChildEventingList.get(i);
        Object localObject2 = paramList.get(i);
        if (localObject1 == localObject2)
        {
          i += 1;
        }
        else
        {
          int j = paramChildEventingList.indexOf(localObject2);
          if (j == -1)
          {
            paramChildEventingList.addWithEvent(i, localObject2, paramAccumulator);
            i += 1;
          }
          else
          {
            paramChildEventingList.removeWithEvent(j);
            paramChildEventingList.addWithEvent(i, localObject2, paramAccumulator);
            i += 1;
          }
        }
      }
    }
  }
  
  private void updateTree()
  {
    long l = SystemClock.elapsedRealtime();
    Object localObject = createShadowDocumentUpdate();
    boolean bool = ((ShadowDocument.Update)localObject).isEmpty();
    String str;
    if (bool)
    {
      ((ShadowDocument.Update)localObject).abandon();
      str = Long.toString(SystemClock.elapsedRealtime() - l);
      if (!bool) {
        break label74;
      }
    }
    label74:
    for (localObject = " (no changes)";; localObject = "")
    {
      LogUtil.d("Document.updateTree() completed in %s ms%s", new Object[] { str, localObject });
      return;
      applyDocumentUpdate((ShadowDocument.Update)localObject);
      break;
    }
  }
  
  public void addRef()
  {
    try
    {
      int i = mReferenceCounter;
      mReferenceCounter = (i + 1);
      if (i == 0) {
        init();
      }
      return;
    }
    finally {}
  }
  
  public void addUpdateListener(UpdateListener paramUpdateListener)
  {
    mUpdateListeners.add(paramUpdateListener);
  }
  
  public void findMatchingElements(String paramString, Accumulator<Integer> paramAccumulator)
  {
    verifyThreadAccess();
    paramString = Pattern.compile(Pattern.quote(paramString), 2);
    findMatches(mDocumentProvider.getRootElement(), paramString, paramAccumulator);
  }
  
  public DocumentView getDocumentView()
  {
    verifyThreadAccess();
    return mShadowDocument;
  }
  
  @Nullable
  public Object getElementForNodeId(int paramInt)
  {
    return mObjectIdMapper.getObjectForId(paramInt);
  }
  
  public void getElementStyles(Object paramObject, StyleAccumulator paramStyleAccumulator)
  {
    getNodeDescriptor(paramObject).getStyles(paramObject, paramStyleAccumulator);
  }
  
  @Nullable
  public NodeDescriptor getNodeDescriptor(Object paramObject)
  {
    verifyThreadAccess();
    return mDocumentProvider.getNodeDescriptor(paramObject);
  }
  
  @Nullable
  public Integer getNodeIdForElement(Object paramObject)
  {
    return mObjectIdMapper.getIdForObject(paramObject);
  }
  
  public Object getRootElement()
  {
    verifyThreadAccess();
    Object localObject = mDocumentProvider.getRootElement();
    if (localObject == null) {
      throw new IllegalStateException();
    }
    if (localObject != mShadowDocument.getRootElement()) {
      throw new IllegalStateException();
    }
    return localObject;
  }
  
  public void hideHighlight()
  {
    verifyThreadAccess();
    mDocumentProvider.hideHighlight();
  }
  
  public void highlightElement(Object paramObject, int paramInt)
  {
    verifyThreadAccess();
    mDocumentProvider.highlightElement(paramObject, paramInt);
  }
  
  public void release()
  {
    try
    {
      if (mReferenceCounter > 0)
      {
        int i = mReferenceCounter - 1;
        mReferenceCounter = i;
        if (i == 0) {
          cleanUp();
        }
      }
      return;
    }
    finally {}
  }
  
  public void removeUpdateListener(UpdateListener paramUpdateListener)
  {
    mUpdateListeners.remove(paramUpdateListener);
  }
  
  public void setAttributesAsText(Object paramObject, String paramString)
  {
    verifyThreadAccess();
    mDocumentProvider.setAttributesAsText(paramObject, paramString);
  }
  
  public void setInspectModeEnabled(boolean paramBoolean)
  {
    verifyThreadAccess();
    mDocumentProvider.setInspectModeEnabled(paramBoolean);
  }
  
  public static final class AttributeListAccumulator
    extends ArrayList<String>
    implements AttributeAccumulator
  {
    public void store(String paramString1, String paramString2)
    {
      add(paramString1);
      add(paramString2);
    }
  }
  
  private final class ChildEventingList
    extends ArrayList<Object>
  {
    private DocumentView mDocumentView;
    private Object mParentElement = null;
    private int mParentNodeId = -1;
    
    private ChildEventingList() {}
    
    public void acquire(Object paramObject, DocumentView paramDocumentView)
    {
      mParentElement = paramObject;
      if (mParentElement == null) {}
      for (int i = -1;; i = mObjectIdMapper.getIdForObject(mParentElement).intValue())
      {
        mParentNodeId = i;
        mDocumentView = paramDocumentView;
        return;
      }
    }
    
    public void addWithEvent(int paramInt, Object paramObject, Accumulator<Object> paramAccumulator)
    {
      Object localObject;
      if (paramInt == 0)
      {
        localObject = null;
        if (localObject != null) {
          break label56;
        }
      }
      label56:
      for (int i = -1;; i = mObjectIdMapper.getIdForObject(localObject).intValue())
      {
        add(paramInt, paramObject);
        mUpdateListeners.onChildNodeInserted(mDocumentView, paramObject, mParentNodeId, i, paramAccumulator);
        return;
        localObject = get(paramInt - 1);
        break;
      }
    }
    
    public void release()
    {
      clear();
      mParentElement = null;
      mParentNodeId = -1;
      mDocumentView = null;
    }
    
    public void removeWithEvent(int paramInt)
    {
      Object localObject = remove(paramInt);
      paramInt = mObjectIdMapper.getIdForObject(localObject).intValue();
      mUpdateListeners.onChildNodeRemoved(mParentNodeId, paramInt);
    }
  }
  
  private final class DocumentObjectIdMapper
    extends ObjectIdMapper
  {
    private DocumentObjectIdMapper() {}
    
    protected void onMapped(Object paramObject, int paramInt)
    {
      verifyThreadAccess();
      mDocumentProvider.getNodeDescriptor(paramObject).hook(paramObject);
    }
    
    protected void onUnmapped(Object paramObject, int paramInt)
    {
      verifyThreadAccess();
      mDocumentProvider.getNodeDescriptor(paramObject).unhook(paramObject);
    }
  }
  
  private final class ProviderListener
    implements DocumentProviderListener
  {
    private ProviderListener() {}
    
    public void onAttributeModified(Object paramObject, String paramString1, String paramString2)
    {
      verifyThreadAccess();
      mUpdateListeners.onAttributeModified(paramObject, paramString1, paramString2);
    }
    
    public void onAttributeRemoved(Object paramObject, String paramString)
    {
      verifyThreadAccess();
      mUpdateListeners.onAttributeRemoved(paramObject, paramString);
    }
    
    public void onInspectRequested(Object paramObject)
    {
      verifyThreadAccess();
      mUpdateListeners.onInspectRequested(paramObject);
    }
    
    public void onPossiblyChanged()
    {
      Document.this.updateTree();
    }
  }
  
  public static abstract interface UpdateListener
  {
    public abstract void onAttributeModified(Object paramObject, String paramString1, String paramString2);
    
    public abstract void onAttributeRemoved(Object paramObject, String paramString);
    
    public abstract void onChildNodeInserted(DocumentView paramDocumentView, Object paramObject, int paramInt1, int paramInt2, Accumulator<Object> paramAccumulator);
    
    public abstract void onChildNodeRemoved(int paramInt1, int paramInt2);
    
    public abstract void onInspectRequested(Object paramObject);
  }
  
  private class UpdateListenerCollection
    implements Document.UpdateListener
  {
    private final List<Document.UpdateListener> mListeners = new ArrayList();
    private volatile Document.UpdateListener[] mListenersSnapshot;
    
    public UpdateListenerCollection() {}
    
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
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */