package fr.dvilleneuve.lockito.core.service;

import android.location.Location;
import android.location.LocationManager;

class SimulationService$1
  implements Runnable
{
  SimulationService$1(SimulationService paramSimulationService, Location paramLocation) {}
  
  public void run()
  {
    this$0.locationManager.setTestProviderLocation(val$location.getProvider(), val$location);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */