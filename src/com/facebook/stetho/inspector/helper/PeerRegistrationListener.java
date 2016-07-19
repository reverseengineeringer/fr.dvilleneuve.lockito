package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;

public abstract interface PeerRegistrationListener
{
  public abstract void onPeerRegistered(JsonRpcPeer paramJsonRpcPeer);
  
  public abstract void onPeerUnregistered(JsonRpcPeer paramJsonRpcPeer);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.helper.PeerRegistrationListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */