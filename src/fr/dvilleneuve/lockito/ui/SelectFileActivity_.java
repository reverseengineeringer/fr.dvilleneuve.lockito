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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper_;
import fr.dvilleneuve.lockito.ui.adapter.SelectFileAdapter_;
import java.io.File;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SelectFileActivity_
  extends SelectFileActivity
  implements HasViews, OnViewChangedListener
{
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  private void init_(Bundle paramBundle)
  {
    OnViewChangedNotifier.registerOnViewChangedListener(this);
    analyticsHelper = GAnalyticsHelper_.getInstance_(this);
    selectFileAdapter = SelectFileAdapter_.getInstance_(this);
    restoreSavedInstanceState_(paramBundle);
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
  
  private void restoreSavedInstanceState_(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return;
    }
    selectedFile = ((File)paramBundle.getSerializable("selectedFile"));
    currentFolder = ((File)paramBundle.getSerializable("currentFolder"));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
    init_(paramBundle);
    super.onCreate(paramBundle);
    OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
    setContentView(2130903071);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131755015, paramMenu);
    menuValidate = paramMenu.findItem(2131624116);
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
    if (i == 16908332)
    {
      menuHome();
      return true;
    }
    if (i == 2131624116)
    {
      menuValidate();
      return true;
    }
    return false;
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putSerializable("selectedFile", selectedFile);
    paramBundle.putSerializable("currentFolder", currentFolder);
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    filesList = ((ListView)paramHasViews.findViewById(2131624044));
    emptyView = paramHasViews.findViewById(2131624037);
    currentFolderText = ((TextView)paramHasViews.findViewById(2131624043));
    if (filesList != null) {
      filesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          filesListItemClicked((File)paramAnonymousAdapterView.getAdapter().getItem(paramAnonymousInt));
        }
      });
    }
    afterViews();
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
        SelectFileActivity_.this.showToast(paramInt1, paramInt2, paramArrayOfObject);
      }
    });
  }
  
  public void showToast(final int paramInt, final String paramString)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        SelectFileActivity_.this.showToast(paramInt, paramString);
      }
    });
  }
  
  public void updateLoadingState(final boolean paramBoolean)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        SelectFileActivity_.this.updateLoadingState(paramBoolean);
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
      super(SelectFileActivity_.class);
      fragment_ = paramFragment;
    }
    
    public IntentBuilder_(Context paramContext)
    {
      super(SelectFileActivity_.class);
    }
    
    public IntentBuilder_(android.support.v4.app.Fragment paramFragment)
    {
      super(SelectFileActivity_.class);
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
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SelectFileActivity_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */