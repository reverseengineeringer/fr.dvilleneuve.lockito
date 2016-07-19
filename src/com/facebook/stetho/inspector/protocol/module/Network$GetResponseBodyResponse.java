package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class Network$GetResponseBodyResponse
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public boolean base64Encoded;
  @JsonProperty(required=true)
  public String body;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.GetResponseBodyResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */