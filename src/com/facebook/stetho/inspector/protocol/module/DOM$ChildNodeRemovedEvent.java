package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$ChildNodeRemovedEvent
{
  @JsonProperty(required=true)
  public int nodeId;
  @JsonProperty(required=true)
  public int parentNodeId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.ChildNodeRemovedEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */