package fr.dvilleneuve.lockito.core.helper;

import android.widget.Toast;

class DatabaseHelper$1
  implements Runnable
{
  DatabaseHelper$1(DatabaseHelper paramDatabaseHelper) {}
  
  public void run()
  {
    Toast.makeText(DatabaseHelper.access$000(this$0), "Something went wrong in database upgrade process. A report has been sent.", 1).show();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.DatabaseHelper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */