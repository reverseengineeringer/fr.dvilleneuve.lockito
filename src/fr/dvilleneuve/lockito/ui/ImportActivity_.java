package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager_;
import fr.dvilleneuve.lockito.core.manager.SimulationManager_;
import java.io.File;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.BackgroundExecutor.Task;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ImportActivity_
  extends ImportActivity
  implements HasViews, OnViewChangedListener
{
  public static final String SELECTED_FILE_EXTRA = "selectedFile";
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  private void init_(Bundle paramBundle)
  {
    analyticsHelper = GAnalyticsHelper_.getInstance_(this);
    itineraryManager = ItineraryManager_.getInstance_(this);
    simulationManager = SimulationManager_.getInstance_(this);
    injectExtras_();
    OnViewChangedNotifier.registerOnViewChangedListener(this);
  }
  
  private void injectExtras_()
  {
    Bundle localBundle = getIntent().getExtras();
    if ((localBundle != null) && (localBundle.containsKey("selectedFile"))) {
      selectedFile = ((File)localBundle.getSerializable("selectedFile"));
    }
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
  
  public void loadItinerary(final Uri paramUri)
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "")
    {
      public void execute()
      {
        try
        {
          ImportActivity_.this.loadItinerary(paramUri);
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
      }
    });
  }
  
  public void onCreate(Bundle paramBundle)
  {
    OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
    init_(paramBundle);
    super.onCreate(paramBundle);
    OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
    setContentView(2130903067);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131755011, paramMenu);
    menuImport = paramMenu.findItem(2131624100);
    return super.onCreateOptionsMenu(paramMenu);
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
    int i = paramMenuItem.getItemId();
    if (i == 2131624100)
    {
      menuImport();
      return true;
    }
    if (i == 16908332)
    {
      menuHome();
      return true;
    }
    return false;
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    init();
  }
  
  public void resolveFileFormat(final Uri paramUri)
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "")
    {
      public void execute()
      {
        try
        {
          ImportActivity_.this.resolveFileFormat(paramUri);
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
      }
    });
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
  
  public void setIntent(Intent paramIntent)
  {
    super.setIntent(paramIntent);
    injectExtras_();
  }
  
  public void showToast(final int paramInt1, final int paramInt2, final Object[] paramArrayOfObject)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ImportActivity_.this.showToast(paramInt1, paramInt2, paramArrayOfObject);
      }
    });
  }
  
  public void showToast(final int paramInt, final String paramString)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ImportActivity_.this.showToast(paramInt, paramString);
      }
    });
  }
  
  public void updateLoadingState(final boolean paramBoolean)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ImportActivity_.this.updateLoadingState(paramBoolean);
      }
    });
  }
  
  public void updateUiFormatDetected(final ConverterFormat paramConverterFormat)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ImportActivity_.this.updateUiFormatDetected(paramConverterFormat);
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
      super(ImportActivity_.class);
      fragment_ = paramFragment;
    }
    
    public IntentBuilder_(Context paramContext)
    {
      super(ImportActivity_.class);
    }
    
    public IntentBuilder_(android.support.v4.app.Fragment paramFragment)
    {
      super(ImportActivity_.class);
      fragmentSupport_ = paramFragment;
    }
    
    public IntentBuilder_ selectedFile(File paramFile)
    {
      return (IntentBuilder_)super.extra("selectedFile", paramFile);
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
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ImportActivity_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */