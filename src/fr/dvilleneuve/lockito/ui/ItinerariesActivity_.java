package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import com.google.android.gms.ads.AdView;
import fr.dvilleneuve.lockito.core.helper.AdHelper_;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_;
import fr.dvilleneuve.lockito.core.manager.BusManager_;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager_;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;
import fr.dvilleneuve.lockito.core.manager.SimulationManager_;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.ui.adapter.ItinerariesAdapter_;
import java.util.List;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.BackgroundExecutor.Task;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ItinerariesActivity_
  extends ItinerariesActivity
  implements HasViews, OnViewChangedListener
{
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  private void init_(Bundle paramBundle)
  {
    OnViewChangedNotifier.registerOnViewChangedListener(this);
    analyticsHelper = GAnalyticsHelper_.getInstance_(this);
    itinerariesAdapter = ItinerariesAdapter_.getInstance_(this);
    itineraryManager = ItineraryManager_.getInstance_(this);
    busManager = BusManager_.getInstance_(this);
    simulationManager = SimulationManager_.getInstance_(this);
    adHelper = AdHelper_.getInstance_(this);
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
  
  public void loadItineraries()
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "")
    {
      public void execute()
      {
        try
        {
          ItinerariesActivity_.this.loadItineraries();
          return;
        }
        catch (Throwable localThrowable)
        {
          Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), localThrowable);
        }
      }
    });
  }
  
  public void loadItinerary(final Long paramLong)
  {
    BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "")
    {
      public void execute()
      {
        try
        {
          ItinerariesActivity_.this.loadItinerary(paramLong);
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
    setContentView(2130903068);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131755008, paramMenu);
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
    if (i == 2131624099)
    {
      menuSettings();
      return true;
    }
    if (i == 2131624098)
    {
      menuAdd();
      return true;
    }
    if (i == 2131624101)
    {
      menuAbout();
      return true;
    }
    if (i == 2131624100)
    {
      menuImport();
      return true;
    }
    return false;
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    listView = ((ListView)paramHasViews.findViewById(2131624035));
    emptyView = paramHasViews.findViewById(2131624037);
    adView = ((AdView)paramHasViews.findViewById(2131624036));
    if (listView != null)
    {
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          listViewItemClicked(paramAnonymousInt);
        }
      });
      listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          listViewItemLongClicked(paramAnonymousInt);
          return true;
        }
      });
    }
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
        ItinerariesActivity_.this.showToast(paramInt1, paramInt2, paramArrayOfObject);
      }
    });
  }
  
  public void showToast(final int paramInt, final String paramString)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItinerariesActivity_.this.showToast(paramInt, paramString);
      }
    });
  }
  
  public void updateItineraries(final List<ItineraryInfo> paramList)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItinerariesActivity_.this.updateItineraries(paramList);
      }
    });
  }
  
  public void updateLoadingState(final boolean paramBoolean)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        ItinerariesActivity_.this.updateLoadingState(paramBoolean);
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
      super(ItinerariesActivity_.class);
      fragment_ = paramFragment;
    }
    
    public IntentBuilder_(Context paramContext)
    {
      super(ItinerariesActivity_.class);
    }
    
    public IntentBuilder_(android.support.v4.app.Fragment paramFragment)
    {
      super(ItinerariesActivity_.class);
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
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItinerariesActivity_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */