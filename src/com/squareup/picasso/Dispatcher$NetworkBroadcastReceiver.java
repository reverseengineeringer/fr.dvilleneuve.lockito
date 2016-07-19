package com.squareup.picasso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

class Dispatcher$NetworkBroadcastReceiver
  extends BroadcastReceiver
{
  private static final String EXTRA_AIRPLANE_STATE = "state";
  private final ConnectivityManager connectivityManager;
  
  Dispatcher$NetworkBroadcastReceiver(Dispatcher paramDispatcher, Context paramContext)
  {
    connectivityManager = ((ConnectivityManager)paramContext.getSystemService("connectivity"));
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getAction();
    paramIntent = paramIntent.getExtras();
    if ("android.intent.action.AIRPLANE_MODE".equals(paramContext)) {
      this$0.dispatchAirplaneModeChange(paramIntent.getBoolean("state", false));
    }
    while (!"android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext)) {
      return;
    }
    this$0.dispatchNetworkStateChange(connectivityManager.getActiveNetworkInfo());
  }
  
  void register()
  {
    if (((this$0.service instanceof PicassoExecutorService)) && (Utils.hasPermission(this$0.context, "android.permission.ACCESS_NETWORK_STATE"))) {}
    for (int i = 1;; i = 0)
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
      if (i != 0) {
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      }
      this$0.context.registerReceiver(this, localIntentFilter);
      return;
    }
  }
  
  void unregister()
  {
    this$0.context.unregisterReceiver(this);
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Dispatcher.NetworkBroadcastReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */