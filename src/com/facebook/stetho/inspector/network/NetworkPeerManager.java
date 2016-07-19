package com.facebook.stetho.inspector.network;

import android.content.Context;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import javax.annotation.Nullable;

public class NetworkPeerManager
  extends ChromePeerManager
{
  private static NetworkPeerManager sInstance;
  private AsyncPrettyPrinterRegistry mAsyncPrettyPrinterRegistry;
  private AsyncPrettyPrinterInitializer mPrettyPrinterInitializer;
  private final ResponseBodyFileManager mResponseBodyFileManager;
  private final PeersRegisteredListener mTempFileCleanup = new PeersRegisteredListener()
  {
    protected void onFirstPeerRegistered()
    {
      
      if ((mAsyncPrettyPrinterRegistry == null) && (mPrettyPrinterInitializer != null))
      {
        NetworkPeerManager.access$002(NetworkPeerManager.this, new AsyncPrettyPrinterRegistry());
        mPrettyPrinterInitializer.populatePrettyPrinters(mAsyncPrettyPrinterRegistry);
      }
      mResponseBodyFileManager.cleanupFiles();
    }
    
    protected void onLastPeerUnregistered()
    {
      mResponseBodyFileManager.cleanupFiles();
      AsyncPrettyPrinterExecutorHolder.shutdown();
    }
  };
  
  public NetworkPeerManager(ResponseBodyFileManager paramResponseBodyFileManager)
  {
    mResponseBodyFileManager = paramResponseBodyFileManager;
    setListener(mTempFileCleanup);
  }
  
  @Nullable
  public static NetworkPeerManager getInstanceOrNull()
  {
    try
    {
      NetworkPeerManager localNetworkPeerManager = sInstance;
      return localNetworkPeerManager;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static NetworkPeerManager getOrCreateInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new NetworkPeerManager(new ResponseBodyFileManager(paramContext.getApplicationContext()));
      }
      paramContext = sInstance;
      return paramContext;
    }
    finally {}
  }
  
  @Nullable
  public AsyncPrettyPrinterRegistry getAsyncPrettyPrinterRegistry()
  {
    return mAsyncPrettyPrinterRegistry;
  }
  
  public ResponseBodyFileManager getResponseBodyFileManager()
  {
    return mResponseBodyFileManager;
  }
  
  public void setPrettyPrinterInitializer(AsyncPrettyPrinterInitializer paramAsyncPrettyPrinterInitializer)
  {
    Util.throwIfNotNull(mPrettyPrinterInitializer);
    mPrettyPrinterInitializer = ((AsyncPrettyPrinterInitializer)Util.throwIfNull(paramAsyncPrettyPrinterInitializer));
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.NetworkPeerManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */