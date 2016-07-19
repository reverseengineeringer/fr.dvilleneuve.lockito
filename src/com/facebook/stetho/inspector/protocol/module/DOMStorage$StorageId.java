package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class DOMStorage$StorageId
{
  @JsonProperty(required=true)
  public boolean isLocalStorage;
  @JsonProperty(required=true)
  public String securityOrigin;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */