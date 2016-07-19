package fr.dvilleneuve.lockito.core.service;

public abstract interface SimulationTask$OnLocationMockListener
{
  public abstract void onLocationFinished(MockedLocation paramMockedLocation);
  
  public abstract void onLocationMocked(MockedLocation paramMockedLocation);
  
  public abstract void onLocationStarted(MockedLocation paramMockedLocation);
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationTask.OnLocationMockListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */