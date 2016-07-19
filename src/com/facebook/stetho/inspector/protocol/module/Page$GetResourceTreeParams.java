package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class Page$GetResourceTreeParams
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public Page.FrameResourceTree frameTree;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Page.GetResourceTreeParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */