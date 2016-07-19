package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import com.facebook.stetho.common.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class ActivityTracker
{
  private static final ActivityTracker sInstance = new ActivityTracker();
  @GuardedBy("Looper.getMainLooper()")
  private final ArrayList<Activity> mActivities = new ArrayList();
  private final List<Activity> mActivitiesUnmodifiable = Collections.unmodifiableList(mActivities);
  @Nullable
  private AutomaticTracker mAutomaticTracker;
  private final List<Listener> mListeners = new CopyOnWriteArrayList();
  
  public static ActivityTracker get()
  {
    return sInstance;
  }
  
  public void add(Activity paramActivity)
  {
    Util.throwIfNull(paramActivity);
    if (Looper.myLooper() == Looper.getMainLooper()) {}
    for (boolean bool = true;; bool = false)
    {
      Util.throwIfNot(bool);
      mActivities.add(paramActivity);
      Iterator localIterator = mListeners.iterator();
      while (localIterator.hasNext()) {
        ((Listener)localIterator.next()).onActivityAdded(paramActivity);
      }
    }
  }
  
  public boolean beginTrackingIfPossible(Application paramApplication)
  {
    if (mAutomaticTracker == null)
    {
      paramApplication = AutomaticTracker.newInstanceIfPossible(paramApplication, this);
      if (paramApplication != null)
      {
        paramApplication.register();
        mAutomaticTracker = paramApplication;
        return true;
      }
    }
    return false;
  }
  
  public boolean endTracking()
  {
    if (mAutomaticTracker != null)
    {
      mAutomaticTracker.unregister();
      mAutomaticTracker = null;
      return true;
    }
    return false;
  }
  
  public List<Activity> getActivitiesView()
  {
    return mActivitiesUnmodifiable;
  }
  
  public void registerListener(Listener paramListener)
  {
    mListeners.add(paramListener);
  }
  
  public void remove(Activity paramActivity)
  {
    Util.throwIfNull(paramActivity);
    if (Looper.myLooper() == Looper.getMainLooper()) {}
    for (boolean bool = true;; bool = false)
    {
      Util.throwIfNot(bool);
      if (!mActivities.remove(paramActivity)) {
        break;
      }
      Iterator localIterator = mListeners.iterator();
      while (localIterator.hasNext()) {
        ((Listener)localIterator.next()).onActivityRemoved(paramActivity);
      }
    }
  }
  
  public Activity tryGetTopActivity()
  {
    if (mActivitiesUnmodifiable.isEmpty()) {
      return null;
    }
    return (Activity)mActivitiesUnmodifiable.get(mActivitiesUnmodifiable.size() - 1);
  }
  
  public void unregisterListener(Listener paramListener)
  {
    mListeners.remove(paramListener);
  }
  
  private static abstract class AutomaticTracker
  {
    @Nullable
    public static AutomaticTracker newInstanceIfPossible(Application paramApplication, ActivityTracker paramActivityTracker)
    {
      if (Build.VERSION.SDK_INT >= 14) {
        return new AutomaticTrackerICSAndBeyond(paramApplication, paramActivityTracker);
      }
      return null;
    }
    
    public abstract void register();
    
    public abstract void unregister();
    
    @TargetApi(14)
    private static class AutomaticTrackerICSAndBeyond
      extends ActivityTracker.AutomaticTracker
    {
      private final Application mApplication;
      private final Application.ActivityLifecycleCallbacks mLifecycleCallbacks = new Application.ActivityLifecycleCallbacks()
      {
        public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
        {
          mTracker.add(paramAnonymousActivity);
        }
        
        public void onActivityDestroyed(Activity paramAnonymousActivity)
        {
          mTracker.remove(paramAnonymousActivity);
        }
        
        public void onActivityPaused(Activity paramAnonymousActivity) {}
        
        public void onActivityResumed(Activity paramAnonymousActivity) {}
        
        public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
        
        public void onActivityStarted(Activity paramAnonymousActivity) {}
        
        public void onActivityStopped(Activity paramAnonymousActivity) {}
      };
      private final ActivityTracker mTracker;
      
      public AutomaticTrackerICSAndBeyond(Application paramApplication, ActivityTracker paramActivityTracker)
      {
        super();
        mApplication = paramApplication;
        mTracker = paramActivityTracker;
      }
      
      public void register()
      {
        mApplication.registerActivityLifecycleCallbacks(mLifecycleCallbacks);
      }
      
      public void unregister()
      {
        mApplication.unregisterActivityLifecycleCallbacks(mLifecycleCallbacks);
      }
    }
  }
  
  public static abstract interface Listener
  {
    public abstract void onActivityAdded(Activity paramActivity);
    
    public abstract void onActivityRemoved(Activity paramActivity);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ActivityTracker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */