package fr.dvilleneuve.lockito.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.UiThread.Propagation;

@EFragment
public abstract class AbstractFragment
  extends Fragment
{
  @UiThread(propagation=UiThread.Propagation.REUSE)
  void updateLoadingState(boolean paramBoolean)
  {
    ActionBarActivity localActionBarActivity = (ActionBarActivity)getActivity();
    if (localActionBarActivity != null) {
      localActionBarActivity.setSupportProgressBarIndeterminateVisibility(paramBoolean);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.AbstractFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */