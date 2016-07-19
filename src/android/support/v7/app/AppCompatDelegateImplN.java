package android.support.v7.app;

import android.content.Context;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import android.view.Window.Callback;
import java.util.List;

class AppCompatDelegateImplN
  extends AppCompatDelegateImplV23
{
  AppCompatDelegateImplN(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackN(paramCallback);
  }
  
  class AppCompatWindowCallbackN
    extends AppCompatDelegateImplV23.AppCompatWindowCallbackV23
  {
    AppCompatWindowCallbackN(Window.Callback paramCallback)
    {
      super(paramCallback);
    }
    
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> paramList, Menu paramMenu, int paramInt)
    {
      AppCompatDelegateImplV7.PanelFeatureState localPanelFeatureState = getPanelState(0, true);
      if ((localPanelFeatureState != null) && (menu != null))
      {
        super.onProvideKeyboardShortcuts(paramList, menu, paramInt);
        return;
      }
      super.onProvideKeyboardShortcuts(paramList, paramMenu, paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplN
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */