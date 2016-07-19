package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzab;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

public class zzrc<R extends Result>
  extends TransformedResult<R>
  implements ResultCallback<R>
{
  private final Object sJ = new Object();
  private final WeakReference<GoogleApiClient> sL;
  private ResultTransform<? super R, ? extends Result> vk = null;
  private zzrc<? extends Result> vl = null;
  private volatile ResultCallbacks<? super R> vm = null;
  private PendingResult<R> vn = null;
  private Status vo = null;
  private final zza vp;
  private boolean vq = false;
  
  public zzrc(WeakReference<GoogleApiClient> paramWeakReference)
  {
    zzab.zzb(paramWeakReference, "GoogleApiClient reference must not be null");
    sL = paramWeakReference;
    paramWeakReference = (GoogleApiClient)sL.get();
    if (paramWeakReference != null) {}
    for (paramWeakReference = paramWeakReference.getLooper();; paramWeakReference = Looper.getMainLooper())
    {
      vp = new zza(paramWeakReference);
      return;
    }
  }
  
  private void zzac(Status paramStatus)
  {
    synchronized (sJ)
    {
      vo = paramStatus;
      zzad(vo);
      return;
    }
  }
  
  private void zzad(Status paramStatus)
  {
    synchronized (sJ)
    {
      if (vk != null)
      {
        paramStatus = vk.onFailure(paramStatus);
        zzab.zzb(paramStatus, "onFailure must not return null");
        vl.zzac(paramStatus);
      }
      while (!zzaqu()) {
        return;
      }
      vm.onFailure(paramStatus);
    }
  }
  
  private void zzaqs()
  {
    if ((vk == null) && (vm == null)) {}
    do
    {
      return;
      GoogleApiClient localGoogleApiClient = (GoogleApiClient)sL.get();
      if ((!vq) && (vk != null) && (localGoogleApiClient != null))
      {
        localGoogleApiClient.zza(this);
        vq = true;
      }
      if (vo != null)
      {
        zzad(vo);
        return;
      }
    } while (vn == null);
    vn.setResultCallback(this);
  }
  
  private boolean zzaqu()
  {
    GoogleApiClient localGoogleApiClient = (GoogleApiClient)sL.get();
    return (vm != null) && (localGoogleApiClient != null);
  }
  
  private void zze(Result paramResult)
  {
    if ((paramResult instanceof Releasable)) {}
    try
    {
      ((Releasable)paramResult).release();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      paramResult = String.valueOf(paramResult);
      Log.w("TransformedResultImpl", String.valueOf(paramResult).length() + 18 + "Unable to release " + paramResult, localRuntimeException);
    }
  }
  
  public void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks)
  {
    boolean bool2 = true;
    for (;;)
    {
      synchronized (sJ)
      {
        if (vm == null)
        {
          bool1 = true;
          zzab.zza(bool1, "Cannot call andFinally() twice.");
          if (vk != null) {
            break label65;
          }
          bool1 = bool2;
          zzab.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          vm = paramResultCallbacks;
          zzaqs();
          return;
        }
      }
      boolean bool1 = false;
      continue;
      label65:
      bool1 = false;
    }
  }
  
  public void onResult(final R paramR)
  {
    for (;;)
    {
      synchronized (sJ)
      {
        if (paramR.getStatus().isSuccess())
        {
          if (vk != null)
          {
            zzqw.zzapz().submit(new Runnable()
            {
              @WorkerThread
              public void run()
              {
                try
                {
                  zzpt.sI.set(Boolean.valueOf(true));
                  Object localObject1 = zzrc.zzc(zzrc.this).onSuccess(paramR);
                  zzrc.zzd(zzrc.this).sendMessage(zzrc.zzd(zzrc.this).obtainMessage(0, localObject1));
                  zzpt.sI.set(Boolean.valueOf(false));
                  zzrc.zza(zzrc.this, paramR);
                  localObject1 = (GoogleApiClient)zzrc.zze(zzrc.this).get();
                  if (localObject1 != null) {
                    ((GoogleApiClient)localObject1).zzb(zzrc.this);
                  }
                  return;
                }
                catch (RuntimeException localRuntimeException)
                {
                  zzrc.zzd(zzrc.this).sendMessage(zzrc.zzd(zzrc.this).obtainMessage(1, localRuntimeException));
                  GoogleApiClient localGoogleApiClient1;
                  return;
                }
                finally
                {
                  zzpt.sI.set(Boolean.valueOf(false));
                  zzrc.zza(zzrc.this, paramR);
                  GoogleApiClient localGoogleApiClient2 = (GoogleApiClient)zzrc.zze(zzrc.this).get();
                  if (localGoogleApiClient2 != null) {
                    localGoogleApiClient2.zzb(zzrc.this);
                  }
                }
              }
            });
            return;
          }
          if (!zzaqu()) {
            continue;
          }
          vm.onSuccess(paramR);
        }
      }
      zzac(paramR.getStatus());
      zze(paramR);
    }
  }
  
  @NonNull
  public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform)
  {
    boolean bool2 = true;
    for (;;)
    {
      synchronized (sJ)
      {
        if (vk == null)
        {
          bool1 = true;
          zzab.zza(bool1, "Cannot call then() twice.");
          if (vm != null) {
            break label83;
          }
          bool1 = bool2;
          zzab.zza(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          vk = paramResultTransform;
          paramResultTransform = new zzrc(sL);
          vl = paramResultTransform;
          zzaqs();
          return paramResultTransform;
        }
      }
      boolean bool1 = false;
      continue;
      label83:
      bool1 = false;
    }
  }
  
  public void zza(PendingResult<?> paramPendingResult)
  {
    synchronized (sJ)
    {
      vn = paramPendingResult;
      zzaqs();
      return;
    }
  }
  
  void zzaqt()
  {
    vm = null;
  }
  
  private final class zza
    extends Handler
  {
    public zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        int i = what;
        Log.e("TransformedResultImpl", 70 + "TransformationResultHandler received unknown message type: " + i);
        return;
      case 0: 
        PendingResult localPendingResult1 = (PendingResult)obj;
        paramMessage = zzrc.zzf(zzrc.this);
        if (localPendingResult1 == null) {}
        for (;;)
        {
          try
          {
            zzrc.zza(zzrc.zzg(zzrc.this), new Status(13, "Transform returned null"));
            return;
          }
          finally {}
          if ((localPendingResult2 instanceof zzqx)) {
            zzrc.zza(zzrc.zzg(zzrc.this), ((zzqx)localPendingResult2).getStatus());
          } else {
            zzrc.zzg(zzrc.this).zza(localPendingResult2);
          }
        }
      }
      RuntimeException localRuntimeException = (RuntimeException)obj;
      paramMessage = String.valueOf(localRuntimeException.getMessage());
      if (paramMessage.length() != 0) {}
      for (paramMessage = "Runtime exception on the transformation worker thread: ".concat(paramMessage);; paramMessage = new String("Runtime exception on the transformation worker thread: "))
      {
        Log.e("TransformedResultImpl", paramMessage);
        throw localRuntimeException;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */