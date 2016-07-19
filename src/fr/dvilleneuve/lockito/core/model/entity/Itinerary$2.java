package fr.dvilleneuve.lockito.core.model.entity;

import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.utils.collection.ImmutableFlattenList;
import java.util.Collection;
import java.util.List;

class Itinerary$2
  extends ImmutableFlattenList<Leg, Point>
{
  Itinerary$2(Itinerary paramItinerary, Collection paramCollection)
  {
    super(paramCollection);
  }
  
  public List<Point> getSubList(Leg paramLeg)
  {
    return paramLeg.getPoints();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.Itinerary.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */