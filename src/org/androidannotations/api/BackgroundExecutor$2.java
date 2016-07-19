package org.androidannotations.api;

final class BackgroundExecutor$2
  extends BackgroundExecutor.Task
{
  BackgroundExecutor$2(String paramString1, int paramInt, String paramString2, Runnable paramRunnable)
  {
    super(paramString1, paramInt, paramString2);
  }
  
  public void execute()
  {
    val$runnable.run();
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.BackgroundExecutor.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */