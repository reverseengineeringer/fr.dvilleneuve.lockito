package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$PerformSearchResponse
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public int resultCount;
  @JsonProperty(required=true)
  public String searchId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.PerformSearchResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */