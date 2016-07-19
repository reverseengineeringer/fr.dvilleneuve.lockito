package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.zzk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

class zzqd$4
  implements ResultCallback<Status>
{
  zzqd$4(zzqd paramzzqd, zzqz paramzzqz, boolean paramBoolean, GoogleApiClient paramGoogleApiClient) {}
  
  public void zzp(@NonNull Status paramStatus)
  {
    zzk.zzbc(zzqd.zzc(ug)).zzagl();
    if ((paramStatus.isSuccess()) && (ug.isConnected())) {
      ug.reconnect();
    }
    ui.zzc(paramStatus);
    if (uj) {
      iD.disconnect();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqd.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */