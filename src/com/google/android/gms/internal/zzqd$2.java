package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.concurrent.atomic.AtomicReference;

class zzqd$2
  implements GoogleApiClient.ConnectionCallbacks
{
  zzqd$2(zzqd paramzzqd, AtomicReference paramAtomicReference, zzqz paramzzqz) {}
  
  public void onConnected(Bundle paramBundle)
  {
    zzqd.zza(ug, (GoogleApiClient)uh.get(), ui, true);
  }
  
  public void onConnectionSuspended(int paramInt) {}
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqd.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */