package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzqu
  extends zzps
{
  private TaskCompletionSource<Void> sq = new TaskCompletionSource();
  
  private zzqu(zzqp paramzzqp)
  {
    super(paramzzqp);
    va.zza("GmsAvailabilityHelper", this);
  }
  
  public static zzqu zzu(Activity paramActivity)
  {
    paramActivity = zzs(paramActivity);
    zzqu localzzqu = (zzqu)paramActivity.zza("GmsAvailabilityHelper", zzqu.class);
    if (localzzqu != null)
    {
      if (sq.getTask().isComplete()) {
        sq = new TaskCompletionSource();
      }
      return localzzqu;
    }
    return new zzqu(paramActivity);
  }
  
  public Task<Void> getTask()
  {
    return sq.getTask();
  }
  
  public void onStop()
  {
    super.onStop();
    sq.setException(new CancellationException());
  }
  
  protected void zza(ConnectionResult paramConnectionResult, int paramInt)
  {
    sq.setException(new Exception());
  }
  
  protected void zzaol()
  {
    int i = rX.isGooglePlayServicesAvailable(va.zzaqp());
    if (i == 0)
    {
      sq.setResult(null);
      return;
    }
    zzk(new ConnectionResult(i, null));
  }
  
  public void zzk(ConnectionResult paramConnectionResult)
  {
    zzb(paramConnectionResult, 0);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */