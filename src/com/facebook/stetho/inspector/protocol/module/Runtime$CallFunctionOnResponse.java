package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class Runtime$CallFunctionOnResponse
  implements JsonRpcResult
{
  @JsonProperty
  public Runtime.RemoteObject result;
  @JsonProperty(required=false)
  public Boolean wasThrown;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.CallFunctionOnResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */