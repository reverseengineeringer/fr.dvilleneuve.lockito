package com.facebook.stetho.inspector.jsonrpc;

import android.database.Observable;
import java.util.ArrayList;

class JsonRpcPeer$DisconnectObservable
  extends Observable<DisconnectReceiver>
{
  public void onDisconnect()
  {
    int i = 0;
    int j = mObservers.size();
    while (i < j)
    {
      ((DisconnectReceiver)mObservers.get(i)).onDisconnect();
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer.DisconnectObservable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */