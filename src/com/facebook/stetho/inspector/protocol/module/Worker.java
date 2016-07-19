package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import org.json.JSONObject;

public class Worker
  implements ChromeDevtoolsDomain
{
  @ChromeDevtoolsMethod
  public JsonRpcResult canInspectWorkers(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    return new SimpleBooleanResult(true);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Worker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */