package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzqo;
import com.google.android.gms.internal.zzqp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

final class zzh<TResult>
  extends Task<TResult>
{
  private final zzg<TResult> aDP = new zzg();
  private boolean aDQ;
  private TResult aDR;
  private Exception aDS;
  private final Object zzail = new Object();
  
  private void zzchi()
  {
    zzab.zza(aDQ, "Task is not yet complete");
  }
  
  private void zzchj()
  {
    if (!aDQ) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zza(bool, "Task is already complete");
      return;
    }
  }
  
  private void zzchk()
  {
    synchronized (zzail)
    {
      if (!aDQ) {
        return;
      }
      aDP.zza(this);
      return;
    }
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull Activity paramActivity, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    paramOnCompleteListener = new zzc(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
    aDP.zza(paramOnCompleteListener);
    zza.zzv(paramActivity).zzb(paramOnCompleteListener);
    zzchk();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    return addOnCompleteListener(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
  }
  
  @NonNull
  public Task<TResult> addOnCompleteListener(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    aDP.zza(new zzc(paramExecutor, paramOnCompleteListener));
    zzchk();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull Activity paramActivity, @NonNull OnFailureListener paramOnFailureListener)
  {
    paramOnFailureListener = new zzd(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
    aDP.zza(paramOnFailureListener);
    zza.zzv(paramActivity).zzb(paramOnFailureListener);
    zzchk();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull OnFailureListener paramOnFailureListener)
  {
    return addOnFailureListener(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
  }
  
  @NonNull
  public Task<TResult> addOnFailureListener(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    aDP.zza(new zzd(paramExecutor, paramOnFailureListener));
    zzchk();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull Activity paramActivity, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    paramOnSuccessListener = new zze(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
    aDP.zza(paramOnSuccessListener);
    zza.zzv(paramActivity).zzb(paramOnSuccessListener);
    zzchk();
    return this;
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    return addOnSuccessListener(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
  }
  
  @NonNull
  public Task<TResult> addOnSuccessListener(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    aDP.zza(new zze(paramExecutor, paramOnSuccessListener));
    zzchk();
    return this;
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return continueWith(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    zzh localzzh = new zzh();
    aDP.zza(new zza(paramExecutor, paramContinuation, localzzh));
    zzchk();
    return localzzh;
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return continueWithTask(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    zzh localzzh = new zzh();
    aDP.zza(new zzb(paramExecutor, paramContinuation, localzzh));
    zzchk();
    return localzzh;
  }
  
  @Nullable
  public Exception getException()
  {
    synchronized (zzail)
    {
      Exception localException = aDS;
      return localException;
    }
  }
  
  public TResult getResult()
  {
    synchronized (zzail)
    {
      zzchi();
      if (aDS != null) {
        throw new RuntimeExecutionException(aDS);
      }
    }
    Object localObject3 = aDR;
    return (TResult)localObject3;
  }
  
  public <X extends Throwable> TResult getResult(@NonNull Class<X> paramClass)
    throws Throwable
  {
    synchronized (zzail)
    {
      zzchi();
      if (paramClass.isInstance(aDS)) {
        throw ((Throwable)paramClass.cast(aDS));
      }
    }
    if (aDS != null) {
      throw new RuntimeExecutionException(aDS);
    }
    paramClass = aDR;
    return paramClass;
  }
  
  public boolean isComplete()
  {
    synchronized (zzail)
    {
      boolean bool = aDQ;
      return bool;
    }
  }
  
  public boolean isSuccessful()
  {
    for (;;)
    {
      synchronized (zzail)
      {
        if ((aDQ) && (aDS == null))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void setException(@NonNull Exception paramException)
  {
    zzab.zzb(paramException, "Exception must not be null");
    synchronized (zzail)
    {
      zzchj();
      aDQ = true;
      aDS = paramException;
      aDP.zza(this);
      return;
    }
  }
  
  public void setResult(TResult paramTResult)
  {
    synchronized (zzail)
    {
      zzchj();
      aDQ = true;
      aDR = paramTResult;
      aDP.zza(this);
      return;
    }
  }
  
  private static class zza
    extends zzqo
  {
    private final List<WeakReference<zzf<?>>> mListeners = new ArrayList();
    
    private zza(zzqp paramzzqp)
    {
      super();
      va.zza("TaskOnStopCallback", this);
    }
    
    public static zza zzv(Activity paramActivity)
    {
      zzqp localzzqp = zzs(paramActivity);
      zza localzza = (zza)localzzqp.zza("TaskOnStopCallback", zza.class);
      paramActivity = localzza;
      if (localzza == null) {
        paramActivity = new zza(localzzqp);
      }
      return paramActivity;
    }
    
    @MainThread
    public void onStop()
    {
      synchronized (mListeners)
      {
        Iterator localIterator = mListeners.iterator();
        while (localIterator.hasNext())
        {
          zzf localzzf = (zzf)((WeakReference)localIterator.next()).get();
          if (localzzf != null) {
            localzzf.cancel();
          }
        }
      }
      mListeners.clear();
    }
    
    public <T> void zzb(zzf<T> paramzzf)
    {
      synchronized (mListeners)
      {
        mListeners.add(new WeakReference(paramzzf));
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */