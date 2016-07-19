package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class CSS$GetMatchedStylesForNodeRequest
  implements JsonRpcResult
{
  @JsonProperty
  public Boolean excludeInherited;
  @JsonProperty
  public Boolean excludePseudo;
  @JsonProperty(required=true)
  public int nodeId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.GetMatchedStylesForNodeRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */