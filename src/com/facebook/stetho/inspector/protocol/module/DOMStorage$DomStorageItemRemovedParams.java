package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class DOMStorage$DomStorageItemRemovedParams
{
  @JsonProperty(required=true)
  public String key;
  @JsonProperty(required=true)
  public DOMStorage.StorageId storageId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemRemovedParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */