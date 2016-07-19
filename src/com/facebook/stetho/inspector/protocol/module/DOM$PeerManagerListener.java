package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import java.util.Map;

final class DOM$PeerManagerListener
  extends PeersRegisteredListener
{
  private DOM$PeerManagerListener(DOM paramDOM) {}
  
  protected void onFirstPeerRegistered()
  {
    try
    {
      DOM.access$300(this$0).addRef();
      DOM.access$300(this$0).addUpdateListener(DOM.access$1900(this$0));
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected void onLastPeerUnregistered()
  {
    try
    {
      DOM.access$2000(this$0).clear();
      DOM.access$300(this$0).removeUpdateListener(DOM.access$1900(this$0));
      DOM.access$300(this$0).release();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.PeerManagerListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */