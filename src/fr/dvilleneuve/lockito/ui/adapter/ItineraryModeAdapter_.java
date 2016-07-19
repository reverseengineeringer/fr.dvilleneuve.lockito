package fr.dvilleneuve.lockito.ui.adapter;

import android.content.Context;

public final class ItineraryModeAdapter_
  extends ItineraryModeAdapter
{
  private Context context_;
  
  private ItineraryModeAdapter_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static ItineraryModeAdapter_ getInstance_(Context paramContext)
  {
    return new ItineraryModeAdapter_(paramContext);
  }
  
  private void init_()
  {
    context = context_;
  }
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.ItineraryModeAdapter_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */