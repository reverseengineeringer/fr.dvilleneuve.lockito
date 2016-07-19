package fr.dvilleneuve.lockito.core.events;

import fr.dvilleneuve.lockito.core.model.entity.Leg;
import java.util.Collection;

public class ItineraryChangedEvent
{
  public final Collection<Leg> legs;
  
  public ItineraryChangedEvent(Collection<Leg> paramCollection)
  {
    legs = paramCollection;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.events.ItineraryChangedEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */