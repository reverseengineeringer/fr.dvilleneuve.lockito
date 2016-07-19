package com.facebook.stetho.inspector.database;

import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;

class DatabasePeerManager$1
  implements PeerRegistrationListener
{
  DatabasePeerManager$1(DatabasePeerManager paramDatabasePeerManager) {}
  
  public void onPeerRegistered(JsonRpcPeer paramJsonRpcPeer)
  {
    DatabasePeerManager.access$000(this$0, paramJsonRpcPeer);
  }
  
  public void onPeerUnregistered(JsonRpcPeer paramJsonRpcPeer) {}
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.database.DatabasePeerManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */