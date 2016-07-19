package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import org.androidannotations.api.builder.ActivityIntentBuilder;

public class SelectFileActivity_$IntentBuilder_
  extends ActivityIntentBuilder<IntentBuilder_>
{
  private android.support.v4.app.Fragment fragmentSupport_;
  private android.app.Fragment fragment_;
  
  public SelectFileActivity_$IntentBuilder_(android.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), SelectFileActivity_.class);
    fragment_ = paramFragment;
  }
  
  public SelectFileActivity_$IntentBuilder_(Context paramContext)
  {
    super(paramContext, SelectFileActivity_.class);
  }
  
  public SelectFileActivity_$IntentBuilder_(android.support.v4.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), SelectFileActivity_.class);
    fragmentSupport_ = paramFragment;
  }
  
  public void startForResult(int paramInt)
  {
    if (fragmentSupport_ != null)
    {
      fragmentSupport_.startActivityForResult(intent, paramInt);
      return;
    }
    if (fragment_ != null)
    {
      fragment_.startActivityForResult(intent, paramInt);
      return;
    }
    super.startForResult(paramInt);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SelectFileActivity_.IntentBuilder_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */