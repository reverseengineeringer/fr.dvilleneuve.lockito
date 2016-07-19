package fr.dvilleneuve.lockito.ui;

import org.androidannotations.api.BackgroundExecutor.Task;

class ItinerariesActivity_$7
  extends BackgroundExecutor.Task
{
  ItinerariesActivity_$7(ItinerariesActivity_ paramItinerariesActivity_, String paramString1, int paramInt, String paramString2, Long paramLong)
  {
    super(paramString1, paramInt, paramString2);
  }
  
  public void execute()
  {
    try
    {
      ItinerariesActivity_.access$401(this$0, val$itineraryId);
      return;
    }
    catch (Throwable localThrowable)
    {
      Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItinerariesActivity_.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */