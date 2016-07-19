package com.google.android.gms.common.api;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract interface GoogleApiClient$ConnectionCallbacks
{
  public static final int CAUSE_NETWORK_LOST = 2;
  public static final int CAUSE_SERVICE_DISCONNECTED = 1;
  
  public abstract void onConnected(@Nullable Bundle paramBundle);
  
  public abstract void onConnectionSuspended(int paramInt);
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */