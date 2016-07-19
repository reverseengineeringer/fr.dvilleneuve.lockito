package fr.dvilleneuve.lockito.core.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class SimulationActionReceiver
  extends WakefulBroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    startWakefulService(paramContext, paramIntent.setComponent(new ComponentName(paramContext.getPackageName(), SimulationActionService_.class.getName())));
    setResultCode(-1);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationActionReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */