package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;

class ChromePeerManager$UnregisterOnDisconnect
  implements DisconnectReceiver
{
  private final JsonRpcPeer mPeer;
  
  public ChromePeerManager$UnregisterOnDisconnect(ChromePeerManager paramChromePeerManager, JsonRpcPeer paramJsonRpcPeer)
  {
    mPeer = paramJsonRpcPeer;
  }
  
  public void onDisconnect()
  {
    this$0.removePeer(mPeer);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.helper.ChromePeerManager.UnregisterOnDisconnect
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */