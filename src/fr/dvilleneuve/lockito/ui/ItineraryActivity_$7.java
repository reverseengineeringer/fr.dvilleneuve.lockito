package fr.dvilleneuve.lockito.ui;

import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import org.androidannotations.api.BackgroundExecutor.Task;

class ItineraryActivity_$7
  extends BackgroundExecutor.Task
{
  ItineraryActivity_$7(ItineraryActivity_ paramItineraryActivity_, String paramString1, int paramInt1, String paramString2, ConverterFormat paramConverterFormat, int paramInt2)
  {
    super(paramString1, paramInt1, paramString2);
  }
  
  public void execute()
  {
    try
    {
      ItineraryActivity_.access$1001(this$0, val$format, val$exportTitleResId);
      return;
    }
    catch (Throwable localThrowable)
    {
      Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItineraryActivity_.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */