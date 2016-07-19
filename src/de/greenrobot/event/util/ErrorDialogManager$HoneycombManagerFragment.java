package de.greenrobot.event.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import de.greenrobot.event.EventBus;

@TargetApi(11)
public class ErrorDialogManager$HoneycombManagerFragment
  extends Fragment
{
  protected Bundle argumentsForErrorDialog;
  private EventBus eventBus;
  private Object executionScope;
  protected boolean finishAfterDialog;
  
  public static void attachTo(Activity paramActivity, Object paramObject, boolean paramBoolean, Bundle paramBundle)
  {
    FragmentManager localFragmentManager = paramActivity.getFragmentManager();
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
    eventBus = factoryconfig.getEventBus();
    eventBus.register(this);
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.util.ErrorDialogManager.HoneycombManagerFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */