package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$ResolveNodeResponse
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public Runtime.RemoteObject object;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.ResolveNodeResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */