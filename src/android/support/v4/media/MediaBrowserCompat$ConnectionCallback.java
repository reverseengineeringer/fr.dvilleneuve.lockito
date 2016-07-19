package android.support.v4.media;

import android.os.Build.VERSION;

public class MediaBrowserCompat$ConnectionCallback
{
  private ConnectionCallbackInternal mConnectionCallbackInternal;
  final Object mConnectionCallbackObj;
  
  public MediaBrowserCompat$ConnectionCallback()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21(null));
      return;
    }
    mConnectionCallbackObj = null;
  }
  
  public void onConnected() {}
  
  public void onConnectionFailed() {}
  
  public void onConnectionSuspended() {}
  
  void setInternalConnectionCallback(ConnectionCallbackInternal paramConnectionCallbackInternal)
  {
    mConnectionCallbackInternal = paramConnectionCallbackInternal;
  }
  
  static abstract interface ConnectionCallbackInternal
  {
    public abstract void onConnected();
    
    public abstract void onConnectionFailed();
    
    public abstract void onConnectionSuspended();
  }
  
  private class StubApi21
    implements MediaBrowserCompatApi21.ConnectionCallback
  {
    private StubApi21() {}
    
    public void onConnected()
    {
      if (mConnectionCallbackInternal != null) {
        mConnectionCallbackInternal.onConnected();
      }
      MediaBrowserCompat.ConnectionCallback.this.onConnected();
    }
    
    public void onConnectionFailed()
    {
      if (mConnectionCallbackInternal != null) {
        mConnectionCallbackInternal.onConnectionFailed();
      }
      MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
    }
    
    public void onConnectionSuspended()
    {
      if (mConnectionCallbackInternal != null) {
        mConnectionCallbackInternal.onConnectionSuspended();
      }
      MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserCompat.ConnectionCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */