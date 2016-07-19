package de.greenrobot.event.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import de.greenrobot.event.EventBus;

public class ErrorDialogManager$SupportManagerFragment
  extends Fragment
{
  protected Bundle argumentsForErrorDialog;
  private EventBus eventBus;
  private Object executionScope;
  protected boolean finishAfterDialog;
  private boolean skipRegisterOnNextResume;
  
  public static void attachTo(Activity paramActivity, Object paramObject, boolean paramBoolean, Bundle paramBundle)
  {
    FragmentManager localFragmentManager = ((FragmentActivity)paramActivity).getSupportFragmentManager();
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
    if (!ErrorDialogManager.access$000(executionScope, paramThrowableFailureEvent)) {}
    FragmentManager localFragmentManager;
    do
    {
      return;
      ErrorDialogManager.checkLogException(paramThrowableFailureEvent);
      localFragmentManager = getFragmentManager();
      localFragmentManager.executePendingTransactions();
      DialogFragment localDialogFragment = (DialogFragment)localFragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
      if (localDialogFragment != null) {
        localDialogFragment.dismiss();
      }
      paramThrowableFailureEvent = (DialogFragment)ErrorDialogManager.factory.prepareErrorFragment(paramThrowableFailureEvent, finishAfterDialog, argumentsForErrorDialog);
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

/* Location:
 * Qualified Name:     de.greenrobot.event.util.ErrorDialogManager.SupportManagerFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */