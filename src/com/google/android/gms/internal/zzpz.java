package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzpz
  extends GoogleApiClient
{
  private final UnsupportedOperationException tl;
  
  public zzpz(String paramString)
  {
    tl = new UnsupportedOperationException(paramString);
  }
  
  public ConnectionResult blockingConnect()
  {
    throw tl;
  }
  
  public ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit)
  {
    throw tl;
  }
  
  public PendingResult<Status> clearDefaultAccountAndReconnect()
  {
    throw tl;
  }
  
  public void connect()
  {
    throw tl;
  }
  
  public void disconnect()
  {
    throw tl;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    throw tl;
  }
  
  @NonNull
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi)
  {
    throw tl;
  }
  
  public boolean hasConnectedApi(@NonNull Api<?> paramApi)
  {
    throw tl;
  }
  
  public boolean isConnected()
  {
    throw tl;
  }
  
  public boolean isConnecting()
  {
    throw tl;
  }
  
  public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    throw tl;
  }
  
  public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    throw tl;
  }
  
  public void reconnect()
  {
    throw tl;
  }
  
  public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    throw tl;
  }
  
  public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    throw tl;
  }
  
  public void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity)
  {
    throw tl;
  }
  
  public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    throw tl;
  }
  
  public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    throw tl;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzpz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */