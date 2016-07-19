package com.facebook.stetho.inspector.jsonrpc;

import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcResponse;

public abstract interface PendingRequestCallback
{
  public abstract void onResponse(JsonRpcPeer paramJsonRpcPeer, JsonRpcResponse paramJsonRpcResponse);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.jsonrpc.PendingRequestCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */