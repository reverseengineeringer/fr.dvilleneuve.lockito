package fr.dvilleneuve.lockito.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ItineraryNameDialog_
  extends ItineraryNameDialog
  implements HasViews
{
  private View contentView_;
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  public static FragmentBuilder_ builder()
  {
    return new FragmentBuilder_();
  }
  
  private void init_(Bundle paramBundle)
  {
    restoreSavedInstanceState_(paramBundle);
  }
  
  private void restoreSavedInstanceState_(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return;
    }
    itineraryInfoId = ((Long)paramBundle.getSerializable("itineraryInfoId"));
    itineraryInfoName = paramBundle.getString("itineraryInfoName");
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
    return contentView_;
  }
  
  public void onDestroyView()
  {
    contentView_ = null;
    super.onDestroyView();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putSerializable("itineraryInfoId", itineraryInfoId);
    paramBundle.putString("itineraryInfoName", itineraryInfoName);
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, ItineraryNameDialog>
  {
    public ItineraryNameDialog build()
    {
      ItineraryNameDialog_ localItineraryNameDialog_ = new ItineraryNameDialog_();
      localItineraryNameDialog_.setArguments(args);
      return localItineraryNameDialog_;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.dialog.ItineraryNameDialog_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */