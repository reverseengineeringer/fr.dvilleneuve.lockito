package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class Runtime$GetPropertiesRequest
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public String objectId;
  @JsonProperty(required=true)
  public boolean ownProperties;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.GetPropertiesRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */