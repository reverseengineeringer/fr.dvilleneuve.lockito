package fr.dvilleneuve.lockito.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ImportKmlFragment_
  extends ImportKmlFragment
  implements HasViews, OnViewChangedListener
{
  private View contentView_;
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  public static FragmentBuilder_ builder()
  {
    return new FragmentBuilder_();
  }
  
  private void init_(Bundle paramBundle)
  {
    OnViewChangedNotifier.registerOnViewChangedListener(this);
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
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    contentView_ = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (contentView_ == null) {
      contentView_ = paramLayoutInflater.inflate(2130903079, paramViewGroup, false);
    }
    return contentView_;
  }
  
  public void onDestroyView()
  {
    contentView_ = null;
    super.onDestroyView();
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    xpathDescriptionText = ((EditText)paramHasViews.findViewById(2131624066));
    xpathWaypointsText = ((EditText)paramHasViews.findViewById(2131624068));
    xpathPointsText = ((EditText)paramHasViews.findViewById(2131624072));
    xpathNameText = ((EditText)paramHasViews.findViewById(2131624064));
    init();
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, ImportKmlFragment>
  {
    public ImportKmlFragment build()
    {
      ImportKmlFragment_ localImportKmlFragment_ = new ImportKmlFragment_();
      localImportKmlFragment_.setArguments(args);
      return localImportKmlFragment_;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.ImportKmlFragment_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */