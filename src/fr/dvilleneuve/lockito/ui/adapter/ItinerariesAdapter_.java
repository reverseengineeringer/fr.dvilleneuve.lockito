package fr.dvilleneuve.lockito.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper_;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;

public final class ItinerariesAdapter_
  extends ItinerariesAdapter
{
  private Context context_;
  
  private ItinerariesAdapter_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static ItinerariesAdapter_ getInstance_(Context paramContext)
  {
    return new ItinerariesAdapter_(paramContext);
  }
  
  private void init_()
  {
    Resources localResources = context_.getResources();
    itinerariesItemPadding = localResources.getDimensionPixelSize(2131361884);
    itinerariesItemPaddingRight = localResources.getDimensionPixelSize(2131361886);
    itinerariesItemPaddingBottom = localResources.getDimensionPixelSize(2131361885);
    context = context_;
    stringFormatHelper = StringFormatHelper_.getInstance_(context_);
    preferenceManager = PreferenceManager_.getInstance_(context_);
  }
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.ItinerariesAdapter_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */