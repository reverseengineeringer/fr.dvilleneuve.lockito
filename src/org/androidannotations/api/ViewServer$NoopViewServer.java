package org.androidannotations.api;

import android.app.Activity;
import android.view.View;
import java.io.IOException;

class ViewServer$NoopViewServer
  extends ViewServer
{
  private ViewServer$NoopViewServer()
  {
    super(null);
  }
  
  public void addWindow(Activity paramActivity) {}
  
  public void addWindow(View paramView, String paramString) {}
  
  public boolean isRunning()
  {
    return false;
  }
  
  public void removeWindow(Activity paramActivity) {}
  
  public void removeWindow(View paramView) {}
  
  public void run() {}
  
  public void setFocusedWindow(Activity paramActivity) {}
  
  public void setFocusedWindow(View paramView) {}
  
  public boolean start()
    throws IOException
  {
    return false;
  }
  
  public boolean stop()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.ViewServer.NoopViewServer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */