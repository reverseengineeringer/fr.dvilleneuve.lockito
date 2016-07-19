package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzab;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks
{
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask)
    throws ExecutionException, InterruptedException
  {
    zzab.zzata();
    zzab.zzb(paramTask, "Task must not be null");
    if (paramTask.isComplete()) {
      return (TResult)zzb(paramTask);
    }
    zza localzza = new zza(null);
    zza(paramTask, localzza);
    localzza.await();
    return (TResult)zzb(paramTask);
  }
  
  public static <TResult> TResult await(@NonNull Task<TResult> paramTask, long paramLong, @NonNull TimeUnit paramTimeUnit)
    throws ExecutionException, InterruptedException, TimeoutException
  {
    zzab.zzata();
    zzab.zzb(paramTask, "Task must not be null");
    zzab.zzb(paramTimeUnit, "TimeUnit must not be null");
    if (paramTask.isComplete()) {
      return (TResult)zzb(paramTask);
    }
    zza localzza = new zza(null);
    zza(paramTask, localzza);
    if (!localzza.await(paramLong, paramTimeUnit)) {
      throw new TimeoutException("Timed out waiting for Task");
    }
    return (TResult)zzb(paramTask);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Callable<TResult> paramCallable)
  {
    return call(TaskExecutors.MAIN_THREAD, paramCallable);
  }
  
  public static <TResult> Task<TResult> call(@NonNull Executor paramExecutor, @NonNull final Callable<TResult> paramCallable)
  {
    zzab.zzb(paramExecutor, "Executor must not be null");
    zzab.zzb(paramCallable, "Callback must not be null");
    zzh localzzh = new zzh();
    paramExecutor.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          setResult(paramCallable.call());
          return;
        }
        catch (Exception localException)
        {
          setException(localException);
        }
      }
    });
    return localzzh;
  }
  
  public static <TResult> Task<TResult> forException(@NonNull Exception paramException)
  {
    zzh localzzh = new zzh();
    localzzh.setException(paramException);
    return localzzh;
  }
  
  public static <TResult> Task<TResult> forResult(TResult paramTResult)
  {
    zzh localzzh = new zzh();
    localzzh.setResult(paramTResult);
    return localzzh;
  }
  
  public static Task<Void> whenAll(Collection<? extends Task<?>> paramCollection)
  {
    if (paramCollection.isEmpty()) {
      return forResult(null);
    }
    Object localObject = paramCollection.iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((Task)((Iterator)localObject).next() == null) {
        throw new NullPointerException("null tasks are not accepted");
      }
    }
    localObject = new zzh();
    zzc localzzc = new zzc(paramCollection.size(), (zzh)localObject);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      zza((Task)paramCollection.next(), localzzc);
    }
    return (Task<Void>)localObject;
  }
  
  public static Task<Void> whenAll(Task<?>... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return forResult(null);
    }
    return whenAll(Arrays.asList(paramVarArgs));
  }
  
  private static void zza(Task<?> paramTask, zzb paramzzb)
  {
    paramTask.addOnSuccessListener(TaskExecutors.aDO, paramzzb);
    paramTask.addOnFailureListener(TaskExecutors.aDO, paramzzb);
  }
  
  private static <TResult> TResult zzb(Task<TResult> paramTask)
    throws ExecutionException
  {
    if (paramTask.isSuccessful()) {
      return (TResult)paramTask.getResult();
    }
    throw new ExecutionException(paramTask.getException());
  }
  
  private static final class zza
    implements Tasks.zzb
  {
    private final CountDownLatch zzalc = new CountDownLatch(1);
    
    public void await()
      throws InterruptedException
    {
      zzalc.await();
    }
    
    public boolean await(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      return zzalc.await(paramLong, paramTimeUnit);
    }
    
    public void onFailure(@NonNull Exception paramException)
    {
      zzalc.countDown();
    }
    
    public void onSuccess(Object paramObject)
    {
      zzalc.countDown();
    }
  }
  
  static abstract interface zzb
    extends OnFailureListener, OnSuccessListener<Object>
  {}
  
  private static final class zzc
    implements Tasks.zzb
  {
    private final zzh<Void> aDN;
    private Exception aDS;
    private final int aDU;
    private int aDV;
    private int aDW;
    private final Object zzail = new Object();
    
    public zzc(int paramInt, zzh<Void> paramzzh)
    {
      aDU = paramInt;
      aDN = paramzzh;
    }
    
    private void zzchl()
    {
      if (aDV + aDW == aDU)
      {
        if (aDS == null) {
          aDN.setResult(null);
        }
      }
      else {
        return;
      }
      zzh localzzh = aDN;
      int i = aDW;
      int j = aDU;
      localzzh.setException(new ExecutionException(54 + i + " out of " + j + " underlying tasks failed", aDS));
    }
    
    public void onFailure(@NonNull Exception paramException)
    {
      synchronized (zzail)
      {
        aDW += 1;
        aDS = paramException;
        zzchl();
        return;
      }
    }
    
    public void onSuccess(Object arg1)
    {
      synchronized (zzail)
      {
        aDV += 1;
        zzchl();
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.Tasks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */