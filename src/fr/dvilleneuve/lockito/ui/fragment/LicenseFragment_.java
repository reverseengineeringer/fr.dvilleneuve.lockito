package fr.dvilleneuve.lockito.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LicenseFragment_
  extends LicenseFragment
  implements HasViews, OnViewChangedListener
{
  public static final String CONTENT_STRING_ARG = "contentString";
  public static final String URL_STRING_ARG = "urlString";
  private View contentView_;
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  public static FragmentBuilder_ builder()
  {
    return new FragmentBuilder_();
  }
  
  private void init_(Bundle paramBundle)
  {
    OnViewChangedNotifier.registerOnViewChangedListener(this);
    injectFragmentArguments_();
    setHasOptionsMenu(true);
  }
  
  private void injectFragmentArguments_()
  {
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      if (localBundle.containsKey("contentString")) {
        contentString = localBundle.getString("contentString");
      }
      if (localBundle.containsKey("urlString")) {
        urlString = localBundle.getString("urlString");
      }
    }
  }
  
  public View findViewById(int paramInt)
  {
    if (contentView_ == null) {
      return null;
    }
    return contentView_.findViewById(paramInt);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
    init_(paramBundle);
    super.onCreate(paramBundle);
    OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    paramMenuInflater.inflate(2131755014, paramMenu);
    super.onCreateOptionsMenu(paramMenu, paramMenuInflater);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    contentView_ = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (contentView_ == null) {
      contentView_ = paramLayoutInflater.inflate(2130903080, paramViewGroup, false);
    }
    return contentView_;
  }
  
  public void onDestroyView()
  {
    contentView_ = null;
    super.onDestroyView();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (super.onOptionsItemSelected(paramMenuItem)) {
      return true;
    }
    if (paramMenuItem.getItemId() == 2131624115)
    {
      menuGoto();
      return true;
    }
    return false;
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    content = ((TextView)paramHasViews.findViewById(2131624073));
    init();
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public void updateLoadingState(final boolean paramBoolean)
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      super.updateLoadingState(paramBoolean);
      return;
    }
    handler_.post(new Runnable()
    {
      public void run()
      {
        LicenseFragment_.this.updateLoadingState(paramBoolean);
      }
    });
  }
  
  public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, LicenseFragment>
  {
    public LicenseFragment build()
    {
      LicenseFragment_ localLicenseFragment_ = new LicenseFragment_();
      localLicenseFragment_.setArguments(args);
      return localLicenseFragment_;
    }
    
    public FragmentBuilder_ contentString(String paramString)
    {
      args.putString("contentString", paramString);
      return this;
    }
    
    public FragmentBuilder_ urlString(String paramString)
    {
      args.putString("urlString", paramString);
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.LicenseFragment_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */