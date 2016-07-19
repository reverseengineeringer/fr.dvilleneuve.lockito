package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.util.Log;
import android.view.ActionMode.Callback;
import android.view.Window;
import android.view.Window.Callback;

class AppCompatDelegateImplV14
  extends AppCompatDelegateImplV11
{
  private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
  private static TwilightManager sTwilightManager;
  private boolean mApplyDayNightCalled;
  private boolean mHandleNativeActionModes = true;
  private int mLocalNightMode = -100;
  
  AppCompatDelegateImplV14(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  private TwilightManager getTwilightManager()
  {
    if (sTwilightManager == null) {
      sTwilightManager = new TwilightManager(mContext.getApplicationContext());
    }
    return sTwilightManager;
  }
  
  private boolean updateConfigurationForNightMode(int paramInt)
  {
    Resources localResources = mContext.getResources();
    Configuration localConfiguration = localResources.getConfiguration();
    int i = uiMode;
    if (paramInt == 2) {}
    for (paramInt = 32; (i & 0x30) != paramInt; paramInt = 16)
    {
      localConfiguration = new Configuration(localConfiguration);
      uiMode = (uiMode & 0xFFFFFFCF | paramInt);
      localResources.updateConfiguration(localConfiguration, null);
      return true;
    }
    return false;
  }
  
  public boolean applyDayNight()
  {
    mApplyDayNightCalled = true;
    if (mLocalNightMode == -100) {}
    for (int i = getDefaultNightMode();; i = mLocalNightMode)
    {
      i = mapNightMode(i);
      if (i == -1) {
        break;
      }
      return updateConfigurationForNightMode(i);
    }
    return false;
  }
  
  public boolean isHandleNativeActionModesEnabled()
  {
    return mHandleNativeActionModes;
  }
  
  int mapNightMode(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return paramInt;
    case 0: 
      if (getTwilightManager().isNight()) {
        return 2;
      }
      return 1;
    }
    return -1;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((paramBundle != null) && (mLocalNightMode == -100)) {
      mLocalNightMode = paramBundle.getInt("appcompat:local_night_mode", -100);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (mLocalNightMode != -100) {
      paramBundle.putInt("appcompat:local_night_mode", mLocalNightMode);
    }
  }
  
  public void setHandleNativeActionModesEnabled(boolean paramBoolean)
  {
    mHandleNativeActionModes = paramBoolean;
  }
  
  public void setLocalNightMode(int paramInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      Log.d("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
      do
      {
        return;
      } while (mLocalNightMode == paramInt);
      mLocalNightMode = paramInt;
    } while (!mApplyDayNightCalled);
    applyDayNight();
  }
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackV14(paramCallback);
  }
  
  class AppCompatWindowCallbackV14
    extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase
  {
    AppCompatWindowCallbackV14(Window.Callback paramCallback)
    {
      super(paramCallback);
    }
    
    public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback)
    {
      if (isHandleNativeActionModesEnabled()) {
        return startAsSupportActionMode(paramCallback);
      }
      return super.onWindowStartingActionMode(paramCallback);
    }
    
    final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback paramCallback)
    {
      paramCallback = new SupportActionModeWrapper.CallbackWrapper(mContext, paramCallback);
      android.support.v7.view.ActionMode localActionMode = startSupportActionMode(paramCallback);
      if (localActionMode != null) {
        return paramCallback.getActionModeWrapper(localActionMode);
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */