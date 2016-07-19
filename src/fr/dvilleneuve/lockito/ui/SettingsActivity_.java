package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SettingsActivity_
  extends SettingsActivity
  implements HasViews
{
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  private void init_(Bundle paramBundle) {}
  
  public static IntentBuilder_ intent(android.app.Fragment paramFragment)
  {
    return new IntentBuilder_(paramFragment);
  }
  
  public static IntentBuilder_ intent(Context paramContext)
  {
    return new IntentBuilder_(paramContext);
  }
  
  public static IntentBuilder_ intent(android.support.v4.app.Fragment paramFragment)
  {
    return new IntentBuilder_(paramFragment);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
    init_(paramBundle);
    super.onCreate(paramBundle);
    OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
  }
  
  public void setContentView(int paramInt)
  {
    super.setContentView(paramInt);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public void setContentView(View paramView)
  {
    super.setContentView(paramView);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public static class IntentBuilder_
    extends ActivityIntentBuilder<IntentBuilder_>
  {
    private android.support.v4.app.Fragment fragmentSupport_;
    private android.app.Fragment fragment_;
    
    public IntentBuilder_(android.app.Fragment paramFragment)
    {
      super(SettingsActivity_.class);
      fragment_ = paramFragment;
    }
    
    public IntentBuilder_(Context paramContext)
    {
      super(SettingsActivity_.class);
    }
    
    public IntentBuilder_(android.support.v4.app.Fragment paramFragment)
    {
      super(SettingsActivity_.class);
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
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SettingsActivity_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */