package fr.dvilleneuve.lockito.core.service;

import android.location.LocationManager;

class SimulationService$3
  implements Runnable
{
  SimulationService$3(SimulationService paramSimulationService) {}
  
  public void run()
  {
    this$0.locationManager.clearTestProviderEnabled("gps");
    this$0.locationManager.clearTestProviderStatus("gps");
    this$0.locationManager.clearTestProviderLocation("gps");
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationService.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */