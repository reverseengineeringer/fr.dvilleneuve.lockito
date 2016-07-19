package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;

public class SimpleBooleanResult
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public boolean result;
  
  public SimpleBooleanResult() {}
  
  public SimpleBooleanResult(boolean paramBoolean)
  {
    result = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.SimpleBooleanResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */