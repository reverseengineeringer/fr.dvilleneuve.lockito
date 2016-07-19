package fr.dvilleneuve.lockito.ui;

import android.net.Uri;
import org.androidannotations.api.BackgroundExecutor.Task;

class ImportActivity_$5
  extends BackgroundExecutor.Task
{
  ImportActivity_$5(ImportActivity_ paramImportActivity_, String paramString1, int paramInt, String paramString2, Uri paramUri)
  {
    super(paramString1, paramInt, paramString2);
  }
  
  public void execute()
  {
    try
    {
      ImportActivity_.access$401(this$0, val$uri);
      return;
    }
    catch (Throwable localThrowable)
    {
      Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ImportActivity_.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */