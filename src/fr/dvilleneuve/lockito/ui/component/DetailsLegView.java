package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(2130903074)
public class DetailsLegView
  extends DetailsView
{
  @ViewById
  AddressEditText fromEditText;
  @ViewById
  AddressEditText toEditText;
  
  public DetailsLegView(Context paramContext)
  {
    super(paramContext);
  }
  
  public DetailsLegView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public DetailsLegView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void showData(ItineraryInfo paramItineraryInfo, Integer paramInteger1, Integer paramInteger2)
  {
    paramItineraryInfo = paramItineraryInfo.getItinerary().getLeg(paramInteger1.intValue());
    if (paramItineraryInfo != null)
    {
      fromEditText.setPoint(paramItineraryInfo.getStartWaypoint());
      toEditText.setPoint(paramItineraryInfo.getEndWaypoint());
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.DetailsLegView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */