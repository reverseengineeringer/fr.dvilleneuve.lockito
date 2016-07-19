package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup(2130903073)
public class DetailsItineraryView
  extends DetailsView
{
  public DetailsItineraryView(Context paramContext)
  {
    super(paramContext);
  }
  
  public DetailsItineraryView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public DetailsItineraryView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void setHints(ItineraryInfo paramItineraryInfo, Leg paramLeg) {}
  
  public void showData(ItineraryInfo paramItineraryInfo, Integer paramInteger1, Integer paramInteger2) {}
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.DetailsItineraryView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */