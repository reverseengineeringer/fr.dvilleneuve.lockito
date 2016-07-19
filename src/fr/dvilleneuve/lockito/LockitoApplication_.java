package fr.dvilleneuve.lockito;

import fr.dvilleneuve.lockito.core.manager.NotificationManager_;
import fr.dvilleneuve.lockito.core.manager.SimulationManager_;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.BackgroundExecutor.Task;

public final class LockitoApplication_
  extends LockitoApplication
{
  private static LockitoApplication INSTANCE_;
  
  public static LockitoApplication getInstance()
  {
    return INSTANCE_;
  }
  
  private void init_()
  {
    notificationManager = NotificationManager_.getInstance_(this);
    simulationManager = SimulationManager_.getInstance_(this);
  }
  
  public static void setForTesting(LockitoApplication paramLockitoApplication)
  {
    INSTANCE_ = paramLockitoApplication;
  }
  
  public void loadUserIdentifier()
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "")
    {
      public void execute()
      {
        try
        {
          LockitoApplication_.this.loadUserIdentifier();
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
      }
    });
  }
  
  public void onCreate()
  {
    INSTANCE_ = this;
    init_();
    super.onCreate();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.LockitoApplication_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */