package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class DOMStorage$DomStorageItemUpdatedParams
{
  @JsonProperty(required=true)
  public String key;
  @JsonProperty(required=true)
  public String newValue;
  @JsonProperty(required=true)
  public String oldValue;
  @JsonProperty(required=true)
  public DOMStorage.StorageId storageId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOMStorage.DomStorageItemUpdatedParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */