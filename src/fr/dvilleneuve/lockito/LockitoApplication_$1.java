package fr.dvilleneuve.lockito;

import org.androidannotations.api.BackgroundExecutor.Task;

class LockitoApplication_$1
  extends BackgroundExecutor.Task
{
  LockitoApplication_$1(LockitoApplication_ paramLockitoApplication_, String paramString1, int paramInt, String paramString2)
  {
    super(paramString1, paramInt, paramString2);
  }
  
  public void execute()
  {
    try
    {
      LockitoApplication_.access$001(this$0);
      return;
    }
    catch (Throwable localThrowable)
    {
      Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.LockitoApplication_.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */