package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

class NotificationManagerCompat$ImplBase
  implements NotificationManagerCompat.Impl
{
  public boolean areNotificationsEnabled(Context paramContext, NotificationManager paramNotificationManager)
  {
    return true;
  }
  
  public void cancelNotification(NotificationManager paramNotificationManager, String paramString, int paramInt)
  {
    paramNotificationManager.cancel(paramInt);
  }
  
  public int getImportance(NotificationManager paramNotificationManager)
  {
    return 64536;
  }
  
  public int getSideChannelBindFlags()
  {
    return 1;
  }
  
  public void postNotification(NotificationManager paramNotificationManager, String paramString, int paramInt, Notification paramNotification)
  {
    paramNotificationManager.notify(paramInt, paramNotification);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationManagerCompat.ImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */