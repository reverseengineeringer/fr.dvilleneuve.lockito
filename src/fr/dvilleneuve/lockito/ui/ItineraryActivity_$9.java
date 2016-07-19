package fr.dvilleneuve.lockito.ui;

import java.util.List;
import org.androidannotations.api.BackgroundExecutor.Task;

class ItineraryActivity_$9
  extends BackgroundExecutor.Task
{
  ItineraryActivity_$9(ItineraryActivity_ paramItineraryActivity_, String paramString1, int paramInt, String paramString2, List paramList)
  {
    super(paramString1, paramInt, paramString2);
  }
  
  public void execute()
  {
    try
    {
      ItineraryActivity_.access$1201(this$0, val$waypoints);
      return;
    }
    catch (Throwable localThrowable)
    {
      Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItineraryActivity_.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */