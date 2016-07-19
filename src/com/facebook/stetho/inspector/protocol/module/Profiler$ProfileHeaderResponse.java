package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class Profiler$ProfileHeaderResponse
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public List<Profiler.ProfileHeader> headers;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Profiler.ProfileHeaderResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */