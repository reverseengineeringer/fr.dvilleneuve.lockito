package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager_;
import fr.dvilleneuve.lockito.core.preferences.MockPreferences_;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.BackgroundExecutor.Task;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SplashscreenActivity_
  extends SplashscreenActivity
  implements HasViews, OnViewChangedListener
{
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  private void init_(Bundle paramBundle)
  {
    preferences = new MockPreferences_(this);
    OnViewChangedNotifier.registerOnViewChangedListener(this);
    requestWindowFeature(1);
    itineraryManager = ItineraryManager_.getInstance_(this);
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
    setContentView(2130903072);
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    loadingProgress = ((ProgressBar)paramHasViews.findViewById(2131624045));
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
  
  public void updateLoadingState(final boolean paramBoolean)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        SplashscreenActivity_.this.updateLoadingState(paramBoolean);
      }
    });
  }
  
  public void upgradeData()
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "")
    {
      public void execute()
      {
        try
        {
          SplashscreenActivity_.this.upgradeData();
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
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
      super(SplashscreenActivity_.class);
      fragment_ = paramFragment;
    }
    
    public IntentBuilder_(Context paramContext)
    {
      super(SplashscreenActivity_.class);
    }
    
    public IntentBuilder_(android.support.v4.app.Fragment paramFragment)
    {
      super(SplashscreenActivity_.class);
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
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SplashscreenActivity_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */