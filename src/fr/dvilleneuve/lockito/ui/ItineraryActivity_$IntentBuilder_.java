package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import org.androidannotations.api.builder.ActivityIntentBuilder;

public class ItineraryActivity_$IntentBuilder_
  extends ActivityIntentBuilder<IntentBuilder_>
{
  private android.support.v4.app.Fragment fragmentSupport_;
  private android.app.Fragment fragment_;
  
  public ItineraryActivity_$IntentBuilder_(android.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), ItineraryActivity_.class);
    fragment_ = paramFragment;
  }
  
  public ItineraryActivity_$IntentBuilder_(Context paramContext)
  {
    super(paramContext, ItineraryActivity_.class);
  }
  
  public ItineraryActivity_$IntentBuilder_(android.support.v4.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), ItineraryActivity_.class);
    fragmentSupport_ = paramFragment;
  }
  
  public IntentBuilder_ importItinerary(Boolean paramBoolean)
  {
    return (IntentBuilder_)super.extra("importItinerary", paramBoolean);
  }
  
  public IntentBuilder_ itineraryId(Long paramLong)
  {
    return (IntentBuilder_)super.extra("itineraryId", paramLong);
  }
  
  public IntentBuilder_ itineraryName(String paramString)
  {
    return (IntentBuilder_)super.extra("itineraryName", paramString);
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

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ItineraryActivity_.IntentBuilder_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */