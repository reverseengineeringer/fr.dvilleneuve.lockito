package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$GetDocumentResponse
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public DOM.Node root;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.GetDocumentResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */