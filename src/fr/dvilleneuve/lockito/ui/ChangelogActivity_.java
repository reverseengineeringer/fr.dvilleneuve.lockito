package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChangelogActivity_
  extends ChangelogActivity
  implements HasViews, OnViewChangedListener
{
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  private void init_(Bundle paramBundle)
  {
    OnViewChangedNotifier.registerOnViewChangedListener(this);
    analyticsHelper = GAnalyticsHelper_.getInstance_(this);
    preferenceManager = PreferenceManager_.getInstance_(this);
  }
  
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
    setContentView(2130903066);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((SdkVersionHelper.getSdkInt() < 5) && (paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0)) {
      onBackPressed();
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (super.onOptionsItemSelected(paramMenuItem)) {
      return true;
    }
    if (paramMenuItem.getItemId() == 16908332)
    {
      menuHome();
      return true;
    }
    return false;
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    webview = ((WebView)paramHasViews.findViewById(2131624032));
    init();
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
  
  public void showToast(final int paramInt1, final int paramInt2, final Object[] paramArrayOfObject)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ChangelogActivity_.this.showToast(paramInt1, paramInt2, paramArrayOfObject);
      }
    });
  }
  
  public void showToast(final int paramInt, final String paramString)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ChangelogActivity_.this.showToast(paramInt, paramString);
      }
    });
  }
  
  public void updateLoadingState(final boolean paramBoolean)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ChangelogActivity_.this.updateLoadingState(paramBoolean);
      }
    });
  }
  
  public static class IntentBuilder_
    extends ActivityIntentBuilder<IntentBuilder_>
  {
    private android.support.v4.app.Fragment fragmentSupport_;
    private android.app.Fragment fragment_;
    
    public IntentBuilder_(android.app.Fragment paramFragment)
    {
      super(ChangelogActivity_.class);
      fragment_ = paramFragment;
    }
    
    public IntentBuilder_(Context paramContext)
    {
      super(ChangelogActivity_.class);
    }
    
    public IntentBuilder_(android.support.v4.app.Fragment paramFragment)
    {
      super(ChangelogActivity_.class);
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
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ChangelogActivity_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */