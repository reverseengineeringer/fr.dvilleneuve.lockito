package fr.dvilleneuve.lockito.core.events;

import fr.dvilleneuve.lockito.core.SimulationState;

public class SimulationStateChangedEvent
{
  public final SimulationState state;
  
  public SimulationStateChangedEvent(SimulationState paramSimulationState)
  {
    state = paramSimulationState;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.events.SimulationStateChangedEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */