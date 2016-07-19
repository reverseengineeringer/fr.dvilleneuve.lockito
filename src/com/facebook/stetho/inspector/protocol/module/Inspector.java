package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import org.json.JSONObject;

public class Inspector
  implements ChromeDevtoolsDomain
{
  @ChromeDevtoolsMethod
  public void disable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject) {}
  
  @ChromeDevtoolsMethod
  public void enable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject) {}
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Inspector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */