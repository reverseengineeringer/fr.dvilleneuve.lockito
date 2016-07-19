package com.squareup.picasso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

class Dispatcher
{
  static final int AIRPLANE_MODE_CHANGE = 10;
  private static final int AIRPLANE_MODE_OFF = 0;
  private static final int AIRPLANE_MODE_ON = 1;
  private static final int BATCH_DELAY = 200;
  private static final String DISPATCHER_THREAD_NAME = "Dispatcher";
  static final int HUNTER_BATCH_COMPLETE = 8;
  static final int HUNTER_COMPLETE = 4;
  static final int HUNTER_DECODE_FAILED = 6;
  static final int HUNTER_DELAY_NEXT_BATCH = 7;
  static final int HUNTER_RETRY = 5;
  static final int NETWORK_STATE_CHANGE = 9;
  static final int REQUEST_CANCEL = 2;
  static final int REQUEST_GCED = 3;
  static final int REQUEST_SUBMIT = 1;
  private static final int RETRY_DELAY = 500;
  boolean airplaneMode;
  final List<BitmapHunter> batch;
  final Cache cache;
  final Context context;
  final DispatcherThread dispatcherThread = new DispatcherThread();
  final Downloader downloader;
  final Handler handler;
  final Map<String, BitmapHunter> hunterMap;
  final Handler mainThreadHandler;
  NetworkInfo networkInfo;
  final NetworkBroadcastReceiver receiver;
  final ExecutorService service;
  final Stats stats;
  
  Dispatcher(Context paramContext, ExecutorService paramExecutorService, Handler paramHandler, Downloader paramDownloader, Cache paramCache, Stats paramStats)
  {
    dispatcherThread.start();
    context = paramContext;
    service = paramExecutorService;
    hunterMap = new LinkedHashMap();
    handler = new DispatcherHandler(dispatcherThread.getLooper());
    downloader = paramDownloader;
    mainThreadHandler = paramHandler;
    cache = paramCache;
    stats = paramStats;
    batch = new ArrayList(4);
    airplaneMode = Utils.isAirplaneModeOn(context);
    receiver = new NetworkBroadcastReceiver(context);
    receiver.register();
  }
  
  private void batch(BitmapHunter paramBitmapHunter)
  {
    if (paramBitmapHunter.isCancelled()) {}
    do
    {
      return;
      batch.add(paramBitmapHunter);
    } while (handler.hasMessages(7));
    handler.sendEmptyMessageDelayed(7, 200L);
  }
  
  void dispatchAirplaneModeChange(boolean paramBoolean)
  {
    Handler localHandler1 = handler;
    Handler localHandler2 = handler;
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      localHandler1.sendMessage(localHandler2.obtainMessage(10, i, 0));
      return;
    }
  }
  
  void dispatchCancel(Action paramAction)
  {
    handler.sendMessage(handler.obtainMessage(2, paramAction));
  }
  
  void dispatchComplete(BitmapHunter paramBitmapHunter)
  {
    handler.sendMessage(handler.obtainMessage(4, paramBitmapHunter));
  }
  
  void dispatchFailed(BitmapHunter paramBitmapHunter)
  {
    handler.sendMessage(handler.obtainMessage(6, paramBitmapHunter));
  }
  
  void dispatchNetworkStateChange(NetworkInfo paramNetworkInfo)
  {
    handler.sendMessage(handler.obtainMessage(9, paramNetworkInfo));
  }
  
  void dispatchRetry(BitmapHunter paramBitmapHunter)
  {
    handler.sendMessageDelayed(handler.obtainMessage(5, paramBitmapHunter), 500L);
  }
  
  void dispatchSubmit(Action paramAction)
  {
    handler.sendMessage(handler.obtainMessage(1, paramAction));
  }
  
  void performAirplaneModeChange(boolean paramBoolean)
  {
    airplaneMode = paramBoolean;
  }
  
  void performBatchComplete()
  {
    ArrayList localArrayList = new ArrayList(batch);
    batch.clear();
    mainThreadHandler.sendMessage(mainThreadHandler.obtainMessage(8, localArrayList));
  }
  
  void performCancel(Action paramAction)
  {
    String str = paramAction.getKey();
    BitmapHunter localBitmapHunter = (BitmapHunter)hunterMap.get(str);
    if (localBitmapHunter != null)
    {
      localBitmapHunter.detach(paramAction);
      if (localBitmapHunter.cancel()) {
        hunterMap.remove(str);
      }
    }
  }
  
  void performComplete(BitmapHunter paramBitmapHunter)
  {
    if (!paramBitmapHunter.shouldSkipMemoryCache()) {
      cache.set(paramBitmapHunter.getKey(), paramBitmapHunter.getResult());
    }
    hunterMap.remove(paramBitmapHunter.getKey());
    batch(paramBitmapHunter);
  }
  
  void performError(BitmapHunter paramBitmapHunter)
  {
    hunterMap.remove(paramBitmapHunter.getKey());
    batch(paramBitmapHunter);
  }
  
  void performNetworkStateChange(NetworkInfo paramNetworkInfo)
  {
    networkInfo = paramNetworkInfo;
    if ((service instanceof PicassoExecutorService)) {
      ((PicassoExecutorService)service).adjustThreadCount(paramNetworkInfo);
    }
  }
  
  void performRetry(BitmapHunter paramBitmapHunter)
  {
    if (paramBitmapHunter.isCancelled()) {
      return;
    }
    if (service.isShutdown())
    {
      performError(paramBitmapHunter);
      return;
    }
    if (paramBitmapHunter.shouldRetry(airplaneMode, networkInfo))
    {
      future = service.submit(paramBitmapHunter);
      return;
    }
    performError(paramBitmapHunter);
  }
  
  void performSubmit(Action paramAction)
  {
    BitmapHunter localBitmapHunter = (BitmapHunter)hunterMap.get(paramAction.getKey());
    if (localBitmapHunter != null) {
      localBitmapHunter.attach(paramAction);
    }
    while (service.isShutdown()) {
      return;
    }
    localBitmapHunter = BitmapHunter.forRequest(context, paramAction.getPicasso(), this, cache, stats, paramAction, downloader);
    future = service.submit(localBitmapHunter);
    hunterMap.put(paramAction.getKey(), localBitmapHunter);
  }
  
  void shutdown()
  {
    service.shutdown();
    dispatcherThread.quit();
    receiver.unregister();
  }
  
  private class DispatcherHandler
    extends Handler
  {
    public DispatcherHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      boolean bool = true;
      switch (what)
      {
      case 3: 
      case 8: 
      default: 
        throw new AssertionError("Unknown handler message received: " + what);
      case 1: 
        paramMessage = (Action)obj;
        performSubmit(paramMessage);
        return;
      case 2: 
        paramMessage = (Action)obj;
        performCancel(paramMessage);
        return;
      case 4: 
        paramMessage = (BitmapHunter)obj;
        performComplete(paramMessage);
        return;
      case 5: 
        paramMessage = (BitmapHunter)obj;
        performRetry(paramMessage);
        return;
      case 6: 
        paramMessage = (BitmapHunter)obj;
        performError(paramMessage);
        return;
      case 7: 
        performBatchComplete();
        return;
      case 9: 
        paramMessage = (NetworkInfo)obj;
        performNetworkStateChange(paramMessage);
        return;
      }
      Dispatcher localDispatcher = Dispatcher.this;
      if (arg1 == 1) {}
      for (;;)
      {
        localDispatcher.performAirplaneModeChange(bool);
        return;
        bool = false;
      }
    }
  }
  
  static class DispatcherThread
    extends HandlerThread
  {
    DispatcherThread()
    {
      super(10);
    }
  }
  
  private class NetworkBroadcastReceiver
    extends BroadcastReceiver
  {
    private static final String EXTRA_AIRPLANE_STATE = "state";
    private final ConnectivityManager connectivityManager;
    
    NetworkBroadcastReceiver(Context paramContext)
    {
      connectivityManager = ((ConnectivityManager)paramContext.getSystemService("connectivity"));
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      paramIntent = paramIntent.getExtras();
      if ("android.intent.action.AIRPLANE_MODE".equals(paramContext)) {
        dispatchAirplaneModeChange(paramIntent.getBoolean("state", false));
      }
      while (!"android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext)) {
        return;
      }
      dispatchNetworkStateChange(connectivityManager.getActiveNetworkInfo());
    }
    
    void register()
    {
      if (((service instanceof PicassoExecutorService)) && (Utils.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE"))) {}
      for (int i = 1;; i = 0)
      {
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        if (i != 0) {
          localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
        context.registerReceiver(this, localIntentFilter);
        return;
      }
    }
    
    void unregister()
    {
      context.unregisterReceiver(this);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Dispatcher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */