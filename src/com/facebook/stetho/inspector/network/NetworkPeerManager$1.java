package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.helper.PeersRegisteredListener;

class NetworkPeerManager$1
  extends PeersRegisteredListener
{
  NetworkPeerManager$1(NetworkPeerManager paramNetworkPeerManager) {}
  
  protected void onFirstPeerRegistered()
  {
    
    if ((NetworkPeerManager.access$000(this$0) == null) && (NetworkPeerManager.access$100(this$0) != null))
    {
      NetworkPeerManager.access$002(this$0, new AsyncPrettyPrinterRegistry());
      NetworkPeerManager.access$100(this$0).populatePrettyPrinters(NetworkPeerManager.access$000(this$0));
    }
    NetworkPeerManager.access$200(this$0).cleanupFiles();
  }
  
  protected void onLastPeerUnregistered()
  {
    NetworkPeerManager.access$200(this$0).cleanupFiles();
    AsyncPrettyPrinterExecutorHolder.shutdown();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.NetworkPeerManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */