package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class Page$ExecutionContextDescription
{
  @JsonProperty(required=true)
  public String frameId;
  @JsonProperty(required=true)
  public int id;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Page.ExecutionContextDescription
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */