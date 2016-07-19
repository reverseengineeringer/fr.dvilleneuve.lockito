package com.facebook.stetho.inspector.domstorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemAddedParams;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemRemovedParams;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemUpdatedParams;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DOMStoragePeerManager
  extends ChromePeerManager
{
  private final Context mContext;
  private final PeerRegistrationListener mPeerListener = new PeersRegisteredListener()
  {
    private final List<DOMStoragePeerManager.DevToolsSharedPreferencesListener> mPrefsListeners = new ArrayList();
    
    protected void onFirstPeerRegistered()
    {
      try
      {
        Iterator localIterator = SharedPreferencesHelper.getSharedPreferenceTags(mContext).iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = (String)localIterator.next();
          SharedPreferences localSharedPreferences = mContext.getSharedPreferences((String)localObject2, 0);
          localObject2 = new DOMStoragePeerManager.DevToolsSharedPreferencesListener(DOMStoragePeerManager.this, localSharedPreferences, (String)localObject2);
          localSharedPreferences.registerOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener)localObject2);
          mPrefsListeners.add(localObject2);
        }
      }
      finally {}
    }
    
    protected void onLastPeerUnregistered()
    {
      try
      {
        Iterator localIterator = mPrefsListeners.iterator();
        while (localIterator.hasNext()) {
          ((DOMStoragePeerManager.DevToolsSharedPreferencesListener)localIterator.next()).unregister();
        }
        mPrefsListeners.clear();
      }
      finally {}
    }
  };
  
  public DOMStoragePeerManager(Context paramContext)
  {
    mContext = paramContext;
    setListener(mPeerListener);
  }
  
  private static Map<String, Object> prefsCopy(Map<String, ?> paramMap)
  {
    HashMap localHashMap = new HashMap(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Object localObject = (Map.Entry)paramMap.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = ((Map.Entry)localObject).getValue();
      if ((localObject instanceof Set)) {
        localHashMap.put(str, shallowCopy((Set)localObject));
      } else {
        localHashMap.put(str, localObject);
      }
    }
    return localHashMap;
  }
  
  private static <T> Set<T> shallowCopy(Set<T> paramSet)
  {
    HashSet localHashSet = new HashSet();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext()) {
      localHashSet.add(paramSet.next());
    }
    return localHashSet;
  }
  
  public void signalItemAdded(DOMStorage.StorageId paramStorageId, String paramString1, String paramString2)
  {
    DOMStorage.DomStorageItemAddedParams localDomStorageItemAddedParams = new DOMStorage.DomStorageItemAddedParams();
    storageId = paramStorageId;
    key = paramString1;
    newValue = paramString2;
    sendNotificationToPeers("DOMStorage.domStorageItemAdded", localDomStorageItemAddedParams);
  }
  
  public void signalItemRemoved(DOMStorage.StorageId paramStorageId, String paramString)
  {
    DOMStorage.DomStorageItemRemovedParams localDomStorageItemRemovedParams = new DOMStorage.DomStorageItemRemovedParams();
    storageId = paramStorageId;
    key = paramString;
    sendNotificationToPeers("DOMStorage.domStorageItemRemoved", localDomStorageItemRemovedParams);
  }
  
  public void signalItemUpdated(DOMStorage.StorageId paramStorageId, String paramString1, String paramString2, String paramString3)
  {
    DOMStorage.DomStorageItemUpdatedParams localDomStorageItemUpdatedParams = new DOMStorage.DomStorageItemUpdatedParams();
    storageId = paramStorageId;
    key = paramString1;
    oldValue = paramString2;
    newValue = paramString3;
    sendNotificationToPeers("DOMStorage.domStorageItemUpdated", localDomStorageItemUpdatedParams);
  }
  
  private class DevToolsSharedPreferencesListener
    implements SharedPreferences.OnSharedPreferenceChangeListener
  {
    private final Map<String, Object> mCopy;
    private final SharedPreferences mPrefs;
    private final DOMStorage.StorageId mStorageId;
    
    public DevToolsSharedPreferencesListener(SharedPreferences paramSharedPreferences, String paramString)
    {
      mPrefs = paramSharedPreferences;
      mStorageId = new DOMStorage.StorageId();
      mStorageId.securityOrigin = paramString;
      mStorageId.isLocalStorage = true;
      mCopy = DOMStoragePeerManager.prefsCopy(paramSharedPreferences.getAll());
    }
    
    public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
    {
      paramSharedPreferences = paramSharedPreferences.getAll();
      boolean bool1 = mCopy.containsKey(paramString);
      boolean bool2 = paramSharedPreferences.containsKey(paramString);
      if (bool2) {}
      for (paramSharedPreferences = paramSharedPreferences.get(paramString); (bool1) && (bool2); paramSharedPreferences = null)
      {
        signalItemUpdated(mStorageId, paramString, SharedPreferencesHelper.valueToString(mCopy.get(paramString)), SharedPreferencesHelper.valueToString(paramSharedPreferences));
        mCopy.put(paramString, paramSharedPreferences);
        return;
      }
      if (bool1)
      {
        signalItemRemoved(mStorageId, paramString);
        mCopy.remove(paramString);
        return;
      }
      if (bool2)
      {
        signalItemAdded(mStorageId, paramString, SharedPreferencesHelper.valueToString(paramSharedPreferences));
        mCopy.put(paramString, paramSharedPreferences);
        return;
      }
      LogUtil.i("Detected rapid put/remove of %s", new Object[] { paramString });
    }
    
    public void unregister()
    {
      mPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.domstorage.DOMStoragePeerManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */