package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class Runtime$EvaluateResponse
  implements JsonRpcResult
{
  @JsonProperty
  public Runtime.ExceptionDetails exceptionDetails;
  @JsonProperty(required=true)
  public Runtime.RemoteObject result;
  @JsonProperty(required=true)
  public boolean wasThrown;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.EvaluateResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */