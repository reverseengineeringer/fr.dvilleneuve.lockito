package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import org.androidannotations.api.builder.ActivityIntentBuilder;

public class SplashscreenActivity_$IntentBuilder_
  extends ActivityIntentBuilder<IntentBuilder_>
{
  private android.support.v4.app.Fragment fragmentSupport_;
  private android.app.Fragment fragment_;
  
  public SplashscreenActivity_$IntentBuilder_(android.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), SplashscreenActivity_.class);
    fragment_ = paramFragment;
  }
  
  public SplashscreenActivity_$IntentBuilder_(Context paramContext)
  {
    super(paramContext, SplashscreenActivity_.class);
  }
  
  public SplashscreenActivity_$IntentBuilder_(android.support.v4.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), SplashscreenActivity_.class);
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
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SplashscreenActivity_.IntentBuilder_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */