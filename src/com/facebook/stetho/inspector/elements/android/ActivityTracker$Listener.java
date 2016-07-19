package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;

public abstract interface ActivityTracker$Listener
{
  public abstract void onActivityAdded(Activity paramActivity);
  
  public abstract void onActivityRemoved(Activity paramActivity);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ActivityTracker.Listener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */