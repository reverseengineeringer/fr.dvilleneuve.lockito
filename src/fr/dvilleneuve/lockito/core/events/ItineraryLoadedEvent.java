package fr.dvilleneuve.lockito.core.events;

import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;

public class ItineraryLoadedEvent
{
  public final ItineraryInfo itineraryInfo;
  
  public ItineraryLoadedEvent(ItineraryInfo paramItineraryInfo)
  {
    itineraryInfo = paramItineraryInfo;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.events.ItineraryLoadedEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */