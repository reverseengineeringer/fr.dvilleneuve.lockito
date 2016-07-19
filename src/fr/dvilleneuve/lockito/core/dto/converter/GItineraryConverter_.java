package fr.dvilleneuve.lockito.core.dto.converter;

import android.content.Context;

public final class GItineraryConverter_
  extends GItineraryConverter
{
  private Context context_;
  
  private GItineraryConverter_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static GItineraryConverter_ getInstance_(Context paramContext)
  {
    return new GItineraryConverter_(paramContext);
  }
  
  private void init_() {}
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.dto.converter.GItineraryConverter_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */