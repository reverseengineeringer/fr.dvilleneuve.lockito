package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Action.Builder;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationCompatApi24$Builder
  implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
{
  private Notification.Builder b;
  
  public NotificationCompatApi24$Builder(Context paramContext, Notification paramNotification1, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, RemoteViews paramRemoteViews1, int paramInt1, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, Bitmap paramBitmap, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt4, CharSequence paramCharSequence4, boolean paramBoolean4, String paramString1, ArrayList<String> paramArrayList, Bundle paramBundle, int paramInt5, int paramInt6, Notification paramNotification2, String paramString2, boolean paramBoolean5, String paramString3, CharSequence[] paramArrayOfCharSequence, RemoteViews paramRemoteViews2, RemoteViews paramRemoteViews3, RemoteViews paramRemoteViews4)
  {
    paramContext = new Notification.Builder(paramContext).setWhen(when).setShowWhen(paramBoolean2).setSmallIcon(icon, iconLevel).setContent(contentView).setTicker(tickerText, paramRemoteViews1).setSound(sound, audioStreamType).setVibrate(vibrate).setLights(ledARGB, ledOnMS, ledOffMS);
    if ((flags & 0x2) != 0)
    {
      paramBoolean2 = true;
      paramContext = paramContext.setOngoing(paramBoolean2);
      if ((flags & 0x8) == 0) {
        break label375;
      }
      paramBoolean2 = true;
      label117:
      paramContext = paramContext.setOnlyAlertOnce(paramBoolean2);
      if ((flags & 0x10) == 0) {
        break label381;
      }
      paramBoolean2 = true;
      label137:
      paramContext = paramContext.setAutoCancel(paramBoolean2).setDefaults(defaults).setContentTitle(paramCharSequence1).setContentText(paramCharSequence2).setSubText(paramCharSequence4).setContentInfo(paramCharSequence3).setContentIntent(paramPendingIntent1).setDeleteIntent(deleteIntent);
      if ((flags & 0x80) == 0) {
        break label387;
      }
    }
    label375:
    label381:
    label387:
    for (paramBoolean2 = true;; paramBoolean2 = false)
    {
      b = paramContext.setFullScreenIntent(paramPendingIntent2, paramBoolean2).setLargeIcon(paramBitmap).setNumber(paramInt1).setUsesChronometer(paramBoolean3).setPriority(paramInt4).setProgress(paramInt2, paramInt3, paramBoolean1).setLocalOnly(paramBoolean4).setExtras(paramBundle).setGroup(paramString2).setGroupSummary(paramBoolean5).setSortKey(paramString3).setCategory(paramString1).setColor(paramInt5).setVisibility(paramInt6).setPublicVersion(paramNotification2).setRemoteInputHistory(paramArrayOfCharSequence);
      if (paramRemoteViews2 != null) {
        b.setCustomContentView(paramRemoteViews2);
      }
      if (paramRemoteViews3 != null) {
        b.setCustomBigContentView(paramRemoteViews3);
      }
      if (paramRemoteViews4 != null) {
        b.setCustomHeadsUpContentView(paramRemoteViews4);
      }
      paramContext = paramArrayList.iterator();
      while (paramContext.hasNext())
      {
        paramNotification1 = (String)paramContext.next();
        b.addPerson(paramNotification1);
      }
      paramBoolean2 = false;
      break;
      paramBoolean2 = false;
      break label117;
      paramBoolean2 = false;
      break label137;
    }
  }
  
  public void addAction(NotificationCompatBase.Action paramAction)
  {
    Notification.Action.Builder localBuilder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    if (paramAction.getRemoteInputs() != null)
    {
      RemoteInput[] arrayOfRemoteInput = RemoteInputCompatApi20.fromCompat(paramAction.getRemoteInputs());
      int j = arrayOfRemoteInput.length;
      int i = 0;
      while (i < j)
      {
        localBuilder.addRemoteInput(arrayOfRemoteInput[i]);
        i += 1;
      }
    }
    if (paramAction.getExtras() != null) {
      localBuilder.addExtras(paramAction.getExtras());
    }
    localBuilder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies());
    b.addAction(localBuilder.build());
  }
  
  public Notification build()
  {
    return b.build();
  }
  
  public Notification.Builder getBuilder()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompatApi24.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */