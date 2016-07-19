package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Runtime$RemoteObject
{
  @JsonProperty
  public String className;
  @JsonProperty
  public String description;
  @JsonProperty
  public String objectId;
  @JsonProperty
  public Runtime.ObjectSubType subtype;
  @JsonProperty(required=true)
  public Runtime.ObjectType type;
  @JsonProperty
  public Object value;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.RemoteObject
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */