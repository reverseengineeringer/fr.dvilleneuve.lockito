package android.support.v4.app;

import android.app.NotificationManager;

class NotificationManagerCompatApi24
{
  public static boolean areNotificationsEnabled(NotificationManager paramNotificationManager)
  {
    return paramNotificationManager.areNotificationsEnabled();
  }
  
  public static int getImportance(NotificationManager paramNotificationManager)
  {
    return paramNotificationManager.getImportance();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationManagerCompatApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */