package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import java.util.List;

class ApplicationDescriptor$ElementContext
{
  private Application mElement;
  private final ActivityTracker.Listener mListener = new ActivityTracker.Listener()
  {
    public void onActivityAdded(Activity paramAnonymousActivity) {}
    
    public void onActivityRemoved(Activity paramAnonymousActivity) {}
  };
  
  public ApplicationDescriptor$ElementContext(ApplicationDescriptor paramApplicationDescriptor) {}
  
  public List<Activity> getActivitiesList()
  {
    return ApplicationDescriptor.access$000(this$0).getActivitiesView();
  }
  
  public void hook(Application paramApplication)
  {
    mElement = paramApplication;
    ApplicationDescriptor.access$000(this$0).registerListener(mListener);
  }
  
  public void unhook()
  {
    ApplicationDescriptor.access$000(this$0).unregisterListener(mListener);
    mElement = null;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ApplicationDescriptor.ElementContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */