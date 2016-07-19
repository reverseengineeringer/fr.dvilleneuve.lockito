package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import java.util.Map;

final class Runtime$2
  implements DisconnectReceiver
{
  Runtime$2(JsonRpcPeer paramJsonRpcPeer) {}
  
  public void onDisconnect()
  {
    Runtime.access$100().remove(val$peer);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */