package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class Runtime$EvaluateRequest
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public String expression;
  @JsonProperty(required=true)
  public String objectGroup;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.EvaluateRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */