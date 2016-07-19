package com.facebook.stetho.inspector.domstorage;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId;
import java.util.Map;

class DOMStoragePeerManager$DevToolsSharedPreferencesListener
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private final Map<String, Object> mCopy;
  private final SharedPreferences mPrefs;
  private final DOMStorage.StorageId mStorageId;
  
  public DOMStoragePeerManager$DevToolsSharedPreferencesListener(DOMStoragePeerManager paramDOMStoragePeerManager, SharedPreferences paramSharedPreferences, String paramString)
  {
    mPrefs = paramSharedPreferences;
    mStorageId = new DOMStorage.StorageId();
    mStorageId.securityOrigin = paramString;
    mStorageId.isLocalStorage = true;
    mCopy = DOMStoragePeerManager.access$100(paramSharedPreferences.getAll());
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.getAll();
    boolean bool1 = mCopy.containsKey(paramString);
    boolean bool2 = paramSharedPreferences.containsKey(paramString);
    if (bool2) {}
    for (paramSharedPreferences = paramSharedPreferences.get(paramString); (bool1) && (bool2); paramSharedPreferences = null)
    {
      this$0.signalItemUpdated(mStorageId, paramString, SharedPreferencesHelper.valueToString(mCopy.get(paramString)), SharedPreferencesHelper.valueToString(paramSharedPreferences));
      mCopy.put(paramString, paramSharedPreferences);
      return;
    }
    if (bool1)
    {
      this$0.signalItemRemoved(mStorageId, paramString);
      mCopy.remove(paramString);
      return;
    }
    if (bool2)
    {
      this$0.signalItemAdded(mStorageId, paramString, SharedPreferencesHelper.valueToString(paramSharedPreferences));
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

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.domstorage.DOMStoragePeerManager.DevToolsSharedPreferencesListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */