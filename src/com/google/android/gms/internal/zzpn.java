package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseException;

public abstract class zzpn
{
  public final int it;
  public final int sn;
  
  public zzpn(int paramInt1, int paramInt2)
  {
    sn = paramInt1;
    it = paramInt2;
  }
  
  public boolean cancel()
  {
    return true;
  }
  
  public void zza(SparseArray<zzrd> paramSparseArray) {}
  
  public abstract void zzb(Api.zzb paramzzb)
    throws DeadObjectException;
  
  public abstract void zzx(@NonNull Status paramStatus);
  
  public static final class zza
    extends zzpn
  {
    public final zzpr.zza<? extends Result, Api.zzb> so;
    
    public zza(int paramInt1, int paramInt2, zzpr.zza<? extends Result, Api.zzb> paramzza)
    {
      super(paramInt2);
      so = paramzza;
    }
    
    public boolean cancel()
    {
      return so.zzaos();
    }
    
    public void zza(SparseArray<zzrd> paramSparseArray)
    {
      paramSparseArray = (zzrd)paramSparseArray.get(sn);
      if (paramSparseArray != null) {
        paramSparseArray.zzg(so);
      }
    }
    
    public void zzb(Api.zzb paramzzb)
      throws DeadObjectException
    {
      so.zzb(paramzzb);
    }
    
    public void zzx(@NonNull Status paramStatus)
    {
      so.zzz(paramStatus);
    }
  }
  
  public static final class zzb<TResult>
    extends zzpn
  {
    private static final Status sr = new Status(8, "Connection to Google Play services was lost while executing the API call.");
    private final zzrb<Api.zzb, TResult> sp;
    private final TaskCompletionSource<TResult> sq;
    
    public zzb(int paramInt1, int paramInt2, zzrb<Api.zzb, TResult> paramzzrb, TaskCompletionSource<TResult> paramTaskCompletionSource)
    {
      super(paramInt2);
      sq = paramTaskCompletionSource;
      sp = paramzzrb;
    }
    
    public void zzb(Api.zzb paramzzb)
      throws DeadObjectException
    {
      try
      {
        sp.zza(paramzzb, sq);
        return;
      }
      catch (DeadObjectException paramzzb)
      {
        zzx(sr);
        throw paramzzb;
      }
      catch (RemoteException paramzzb)
      {
        zzx(sr);
      }
    }
    
    public void zzx(@NonNull Status paramStatus)
    {
      if (paramStatus.getStatusCode() == 8)
      {
        sq.setException(new FirebaseException(paramStatus.getStatusMessage()));
        return;
      }
      sq.setException(new FirebaseApiNotAvailableException(paramStatus.getStatusMessage()));
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzpn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */