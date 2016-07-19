package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import org.json.JSONObject;

public class Debugger
  implements ChromeDevtoolsDomain
{
  @ChromeDevtoolsMethod
  public JsonRpcResult canSetScriptSource(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    return new SimpleBooleanResult(false);
  }
  
  @ChromeDevtoolsMethod
  public void disable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject) {}
  
  @ChromeDevtoolsMethod
  public void enable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject) {}
  
  @ChromeDevtoolsMethod
  public void setPauseOnExceptions(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject) {}
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Debugger
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */