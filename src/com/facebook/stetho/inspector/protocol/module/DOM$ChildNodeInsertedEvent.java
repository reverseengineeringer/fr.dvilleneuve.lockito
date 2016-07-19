package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$ChildNodeInsertedEvent
{
  @JsonProperty(required=true)
  public DOM.Node node;
  @JsonProperty(required=true)
  public int parentNodeId;
  @JsonProperty(required=true)
  public int previousNodeId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.ChildNodeInsertedEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */