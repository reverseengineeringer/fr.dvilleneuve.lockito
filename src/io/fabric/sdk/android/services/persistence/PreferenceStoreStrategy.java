package io.fabric.sdk.android.services.persistence;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceStoreStrategy<T>
  implements PersistenceStrategy<T>
{
  private final String key;
  private final SerializationStrategy<T> serializer;
  private final PreferenceStore store;
  
  public PreferenceStoreStrategy(PreferenceStore paramPreferenceStore, SerializationStrategy<T> paramSerializationStrategy, String paramString)
  {
    store = paramPreferenceStore;
    serializer = paramSerializationStrategy;
    key = paramString;
  }
  
  public void clear()
  {
    store.edit().remove(key).commit();
  }
  
  public T restore()
  {
    SharedPreferences localSharedPreferences = store.get();
    return (T)serializer.deserialize(localSharedPreferences.getString(key, null));
  }
  
  @SuppressLint({"CommitPrefEdits"})
  public void save(T paramT)
  {
    store.save(store.edit().putString(key, serializer.serialize(paramT)));
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.persistence.PreferenceStoreStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */