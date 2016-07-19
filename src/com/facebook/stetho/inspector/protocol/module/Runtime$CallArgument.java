package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class Runtime$CallArgument
{
  @JsonProperty(required=false)
  public String objectId;
  @JsonProperty(required=false)
  public Runtime.ObjectType type;
  @JsonProperty(required=false)
  public Object value;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.CallArgument
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */