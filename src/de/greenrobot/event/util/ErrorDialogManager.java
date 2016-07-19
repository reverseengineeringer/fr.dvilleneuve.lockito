package de.greenrobot.event.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import de.greenrobot.event.EventBus;

public class ErrorDialogManager
{
  public static final String KEY_EVENT_TYPE_ON_CLOSE = "de.greenrobot.eventbus.errordialog.event_type_on_close";
  public static final String KEY_FINISH_AFTER_DIALOG = "de.greenrobot.eventbus.errordialog.finish_after_dialog";
  public static final String KEY_ICON_ID = "de.greenrobot.eventbus.errordialog.icon_id";
  public static final String KEY_MESSAGE = "de.greenrobot.eventbus.errordialog.message";
  public static final String KEY_TITLE = "de.greenrobot.eventbus.errordialog.title";
  protected static final String TAG_ERROR_DIALOG = "de.greenrobot.eventbus.error_dialog";
  protected static final String TAG_ERROR_DIALOG_MANAGER = "de.greenrobot.eventbus.error_dialog_manager";
  public static ErrorDialogFragmentFactory<?> factory;
  
  public static void attachTo(Activity paramActivity)
  {
    attachTo(paramActivity, false, null);
  }
  
  public static void attachTo(Activity paramActivity, Object paramObject, boolean paramBoolean, Bundle paramBundle)
  {
    if (factory == null) {
      throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
    }
    if (isSupportActivity(paramActivity))
    {
      SupportManagerFragment.attachTo(paramActivity, paramObject, paramBoolean, paramBundle);
      return;
    }
    HoneycombManagerFragment.attachTo(paramActivity, paramObject, paramBoolean, paramBundle);
  }
  
  public static void attachTo(Activity paramActivity, boolean paramBoolean)
  {
    attachTo(paramActivity, paramBoolean, null);
  }
  
  public static void attachTo(Activity paramActivity, boolean paramBoolean, Bundle paramBundle)
  {
    attachTo(paramActivity, paramActivity.getClass(), paramBoolean, paramBundle);
  }
  
  protected static void checkLogException(ThrowableFailureEvent paramThrowableFailureEvent)
  {
    if (factoryconfig.logExceptions)
    {
      String str2 = factoryconfig.tagForLoggingExceptions;
      String str1 = str2;
      if (str2 == null) {
        str1 = EventBus.TAG;
      }
      Log.i(str1, "Error dialog manager received exception", throwable);
    }
  }
  
  private static boolean isInExecutionScope(Object paramObject, ThrowableFailureEvent paramThrowableFailureEvent)
  {
    if ((paramObject != null) && ((paramThrowableFailureEvent instanceof HasExecutionScope)))
    {
      paramThrowableFailureEvent = paramThrowableFailureEvent.getExecutionScope();
      if ((paramThrowableFailureEvent != null) && (!paramThrowableFailureEvent.equals(paramObject))) {
        return false;
      }
    }
    return true;
  }
  
  private static boolean isSupportActivity(Activity paramActivity)
  {
    boolean bool = false;
    for (Class localClass = paramActivity.getClass().getSuperclass();; localClass = localClass.getSuperclass())
    {
      if (localClass == null) {
        throw new RuntimeException("Illegal activity type: " + paramActivity.getClass());
      }
      String str = localClass.getName();
      if (str.equals("android.support.v4.app.FragmentActivity")) {
        bool = true;
      }
      do
      {
        return bool;
        if ((str.startsWith("com.actionbarsherlock.app")) && ((str.endsWith(".SherlockActivity")) || (str.endsWith(".SherlockListActivity")) || (str.endsWith(".SherlockPreferenceActivity")))) {
          throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + str);
        }
        if (!str.equals("android.app.Activity")) {
          break;
        }
      } while (Build.VERSION.SDK_INT >= 11);
      throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
    }
  }
  
  @TargetApi(11)
  public static class HoneycombManagerFragment
    extends android.app.Fragment
  {
    protected Bundle argumentsForErrorDialog;
    private EventBus eventBus;
    private Object executionScope;
    protected boolean finishAfterDialog;
    
    public static void attachTo(Activity paramActivity, Object paramObject, boolean paramBoolean, Bundle paramBundle)
    {
      android.app.FragmentManager localFragmentManager = paramActivity.getFragmentManager();
      HoneycombManagerFragment localHoneycombManagerFragment = (HoneycombManagerFragment)localFragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog_manager");
      paramActivity = localHoneycombManagerFragment;
      if (localHoneycombManagerFragment == null)
      {
        paramActivity = new HoneycombManagerFragment();
        localFragmentManager.beginTransaction().add(paramActivity, "de.greenrobot.eventbus.error_dialog_manager").commit();
        localFragmentManager.executePendingTransactions();
      }
      finishAfterDialog = paramBoolean;
      argumentsForErrorDialog = paramBundle;
      executionScope = paramObject;
    }
    
    public void onEventMainThread(ThrowableFailureEvent paramThrowableFailureEvent)
    {
      if (!ErrorDialogManager.isInExecutionScope(executionScope, paramThrowableFailureEvent)) {}
      android.app.FragmentManager localFragmentManager;
      do
      {
        return;
        ErrorDialogManager.checkLogException(paramThrowableFailureEvent);
        localFragmentManager = getFragmentManager();
        localFragmentManager.executePendingTransactions();
        android.app.DialogFragment localDialogFragment = (android.app.DialogFragment)localFragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
        if (localDialogFragment != null) {
          localDialogFragment.dismiss();
        }
        paramThrowableFailureEvent = (android.app.DialogFragment)ErrorDialogManager.factory.prepareErrorFragment(paramThrowableFailureEvent, finishAfterDialog, argumentsForErrorDialog);
      } while (paramThrowableFailureEvent == null);
      paramThrowableFailureEvent.show(localFragmentManager, "de.greenrobot.eventbus.error_dialog");
    }
    
    public void onPause()
    {
      eventBus.unregister(this);
      super.onPause();
    }
    
    public void onResume()
    {
      super.onResume();
      eventBus = factoryconfig.getEventBus();
      eventBus.register(this);
    }
  }
  
  public static class SupportManagerFragment
    extends android.support.v4.app.Fragment
  {
    protected Bundle argumentsForErrorDialog;
    private EventBus eventBus;
    private Object executionScope;
    protected boolean finishAfterDialog;
    private boolean skipRegisterOnNextResume;
    
    public static void attachTo(Activity paramActivity, Object paramObject, boolean paramBoolean, Bundle paramBundle)
    {
      android.support.v4.app.FragmentManager localFragmentManager = ((FragmentActivity)paramActivity).getSupportFragmentManager();
      SupportManagerFragment localSupportManagerFragment = (SupportManagerFragment)localFragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog_manager");
      paramActivity = localSupportManagerFragment;
      if (localSupportManagerFragment == null)
      {
        paramActivity = new SupportManagerFragment();
        localFragmentManager.beginTransaction().add(paramActivity, "de.greenrobot.eventbus.error_dialog_manager").commit();
        localFragmentManager.executePendingTransactions();
      }
      finishAfterDialog = paramBoolean;
      argumentsForErrorDialog = paramBundle;
      executionScope = paramObject;
    }
    
    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      eventBus = factoryconfig.getEventBus();
      eventBus.register(this);
      skipRegisterOnNextResume = true;
    }
    
    public void onEventMainThread(ThrowableFailureEvent paramThrowableFailureEvent)
    {
      if (!ErrorDialogManager.isInExecutionScope(executionScope, paramThrowableFailureEvent)) {}
      android.support.v4.app.FragmentManager localFragmentManager;
      do
      {
        return;
        ErrorDialogManager.checkLogException(paramThrowableFailureEvent);
        localFragmentManager = getFragmentManager();
        localFragmentManager.executePendingTransactions();
        android.support.v4.app.DialogFragment localDialogFragment = (android.support.v4.app.DialogFragment)localFragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
        if (localDialogFragment != null) {
          localDialogFragment.dismiss();
        }
        paramThrowableFailureEvent = (android.support.v4.app.DialogFragment)ErrorDialogManager.factory.prepareErrorFragment(paramThrowableFailureEvent, finishAfterDialog, argumentsForErrorDialog);
      } while (paramThrowableFailureEvent == null);
      paramThrowableFailureEvent.show(localFragmentManager, "de.greenrobot.eventbus.error_dialog");
    }
    
    public void onPause()
    {
      eventBus.unregister(this);
      super.onPause();
    }
    
    public void onResume()
    {
      super.onResume();
      if (skipRegisterOnNextResume)
      {
        skipRegisterOnNextResume = false;
        return;
      }
      eventBus = factoryconfig.getEventBus();
      eventBus.register(this);
    }
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.ErrorDialogManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */