package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;

final class CSS$PeerManagerListener
  extends PeersRegisteredListener
{
  private CSS$PeerManagerListener(CSS paramCSS) {}
  
  protected void onFirstPeerRegistered()
  {
    try
    {
      CSS.access$200(this$0).addRef();
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
      CSS.access$200(this$0).release();
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
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.PeerManagerListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */