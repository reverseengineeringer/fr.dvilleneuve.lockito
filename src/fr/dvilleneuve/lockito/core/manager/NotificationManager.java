package fr.dvilleneuve.lockito.core.manager;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import fr.dvilleneuve.lockito.core.SimulationState;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.service.SimulationActionService_;
import fr.dvilleneuve.lockito.core.service.SimulationActionService_.IntentBuilder_;
import fr.dvilleneuve.lockito.ui.ItineraryActivity_;
import fr.dvilleneuve.lockito.ui.ItineraryActivity_.IntentBuilder_;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;

@EBean
public class NotificationManager
{
  private static final int NOTIFICATION_ID = 1;
  @RootContext
  Context context;
  @SystemService
  android.app.NotificationManager notificationManager;
  @Bean
  SimulationManager simulationManager;
  @Bean
  StringFormatHelper stringFormatHelper;
  
  private PendingIntent createActionPendingIntent(String paramString)
  {
    Intent localIntent = SimulationActionService_.intent(context).get();
    localIntent.setAction(paramString);
    return PendingIntent.getService(context, 2, localIntent, 134217728);
  }
  
  public Notification buildNotification(ItineraryInfo paramItineraryInfo, SimulationState paramSimulationState, Location paramLocation, int paramInt)
  {
    if (paramItineraryInfo == null) {
      return null;
    }
    Object localObject = ((ItineraryActivity_.IntentBuilder_)ItineraryActivity_.intent(context).flags(603979776)).get();
    localObject = PendingIntent.getActivity(context, 0, (Intent)localObject, 268435456);
    paramItineraryInfo = new NotificationCompat.Builder(context).setWhen(System.currentTimeMillis()).setOngoing(true).setSmallIcon(2130837637).setTicker(context.getString(2131165321, new Object[] { paramItineraryInfo.getName() })).setContentTitle(context.getString(2131165322, new Object[] { paramItineraryInfo.getName() })).setContentIntent((PendingIntent)localObject).setPriority(2);
    if (paramLocation != null)
    {
      paramLocation = stringFormatHelper.formatLocation(paramLocation);
      paramItineraryInfo.setContentText(paramLocation);
      paramItineraryInfo.setStyle(new NotificationCompat.BigTextStyle().bigText(paramLocation));
    }
    if (paramInt >= 0) {
      paramItineraryInfo.setProgress(100, paramInt, false);
    }
    if (simulationManager.isLocationMockEnabled()) {
      switch (paramSimulationState)
      {
      }
    }
    for (;;)
    {
      return paramItineraryInfo.build();
      paramItineraryInfo.addAction(2130837648, context.getString(2131165251), createActionPendingIntent("pause"));
      paramItineraryInfo.addAction(2130837654, context.getString(2131165258), createActionPendingIntent("stop"));
      continue;
      paramItineraryInfo.addAction(2130837651, context.getString(2131165252), createActionPendingIntent("play"));
      paramItineraryInfo.addAction(2130837654, context.getString(2131165258), createActionPendingIntent("stop"));
      continue;
      paramItineraryInfo.addAction(2130837651, context.getString(2131165252), createActionPendingIntent("play"));
    }
  }
  
  public void removeNotification()
  {
    notificationManager.cancel(1);
  }
  
  public void showNotification(ItineraryInfo paramItineraryInfo, SimulationState paramSimulationState, Location paramLocation, int paramInt)
  {
    paramItineraryInfo = buildNotification(paramItineraryInfo, paramSimulationState, paramLocation, paramInt);
    if (paramItineraryInfo != null) {
      notificationManager.notify(1, paramItineraryInfo);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.NotificationManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */