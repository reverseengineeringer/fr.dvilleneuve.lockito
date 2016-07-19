package android.support.v7.app;

import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window.Callback;
import java.util.List;

class AppCompatDelegateImplN$AppCompatWindowCallbackN
  extends AppCompatDelegateImplV23.AppCompatWindowCallbackV23
{
  AppCompatDelegateImplN$AppCompatWindowCallbackN(AppCompatDelegateImplN paramAppCompatDelegateImplN, Window.Callback paramCallback)
  {
    super(paramAppCompatDelegateImplN, paramCallback);
  }
  
  public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> paramList, Menu paramMenu, int paramInt)
  {
    AppCompatDelegateImplV7.PanelFeatureState localPanelFeatureState = this$0.getPanelState(0, true);
    if ((localPanelFeatureState != null) && (menu != null))
    {
      super.onProvideKeyboardShortcuts(paramList, menu, paramInt);
      return;
    }
    super.onProvideKeyboardShortcuts(paramList, paramMenu, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplN.AppCompatWindowCallbackN
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */