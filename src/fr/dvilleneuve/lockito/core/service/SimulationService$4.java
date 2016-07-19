package fr.dvilleneuve.lockito.core.service;

import android.location.LocationManager;

class SimulationService$4
  implements Runnable
{
  SimulationService$4(SimulationService paramSimulationService) {}
  
  public void run()
  {
    this$0.locationManager.removeTestProvider("gps");
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationService.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */