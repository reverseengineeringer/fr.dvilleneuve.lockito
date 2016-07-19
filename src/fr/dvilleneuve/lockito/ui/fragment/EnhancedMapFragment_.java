package fr.dvilleneuve.lockito.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper_;
import fr.dvilleneuve.lockito.core.manager.BusManager_;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;
import fr.dvilleneuve.lockito.core.manager.VibratorManager_;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.GeometryUtils_;
import java.util.Collection;
import java.util.List;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class EnhancedMapFragment_
  extends EnhancedMapFragment
  implements HasViews
{
  private View contentView_;
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  public static FragmentBuilder_ builder()
  {
    return new FragmentBuilder_();
  }
  
  private void init_(Bundle paramBundle)
  {
    stringFormatHelper = StringFormatHelper_.getInstance_(getActivity());
    busManager = BusManager_.getInstance_(getActivity());
    vibratorManager = VibratorManager_.getInstance_(getActivity());
    geometryUtils = GeometryUtils_.getInstance_(getActivity());
    preferenceManager = PreferenceManager_.getInstance_(getActivity());
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
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    onViewChangedNotifier_.notifyViewChanged(this);
  }
  
  public void setItineraryPoints(@Nullable final Collection<Leg> paramCollection)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        EnhancedMapFragment_.this.setItineraryPoints(paramCollection);
      }
    });
  }
  
  public void setWaypoints(@Nullable final List<Point> paramList)
  {
    handler_.post(new Runnable()
    {
      public void run()
      {
        EnhancedMapFragment_.this.setWaypoints(paramList);
      }
    });
  }
  
  public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, EnhancedMapFragment>
  {
    public EnhancedMapFragment build()
    {
      EnhancedMapFragment_ localEnhancedMapFragment_ = new EnhancedMapFragment_();
      localEnhancedMapFragment_.setArguments(args);
      return localEnhancedMapFragment_;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */