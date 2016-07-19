package android.support.v4.app;

import android.app.NotificationManager;
import android.content.Context;

class NotificationManagerCompat$ImplApi24
  extends NotificationManagerCompat.ImplKitKat
{
  public boolean areNotificationsEnabled(Context paramContext, NotificationManager paramNotificationManager)
  {
    return NotificationManagerCompatApi24.areNotificationsEnabled(paramNotificationManager);
  }
  
  public int getImportance(NotificationManager paramNotificationManager)
  {
    return NotificationManagerCompatApi24.getImportance(paramNotificationManager);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationManagerCompat.ImplApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */