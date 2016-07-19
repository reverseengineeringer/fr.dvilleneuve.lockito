package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$ResolveNodeRequest
{
  @JsonProperty(required=true)
  public int nodeId;
  @JsonProperty
  public String objectGroup;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.ResolveNodeRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */