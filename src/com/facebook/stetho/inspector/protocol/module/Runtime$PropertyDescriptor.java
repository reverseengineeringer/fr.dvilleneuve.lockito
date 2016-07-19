package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class Runtime$PropertyDescriptor
{
  @JsonProperty(required=true)
  public final boolean configurable = false;
  @JsonProperty(required=true)
  public final boolean enumerable = true;
  @JsonProperty(required=true)
  public final boolean isOwn = true;
  @JsonProperty(required=true)
  public String name;
  @JsonProperty(required=true)
  public Runtime.RemoteObject value;
  @JsonProperty(required=true)
  public final boolean writable = false;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.PropertyDescriptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */