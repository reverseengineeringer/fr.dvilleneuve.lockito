package fr.dvilleneuve.lockito.core.service;

import android.location.LocationManager;

class SimulationService$2
  implements Runnable
{
  SimulationService$2(SimulationService paramSimulationService) {}
  
  public void run()
  {
    if (!SimulationService.access$000(this$0))
    {
      SimulationService.access$002(this$0, true);
      this$0.locationManager.addTestProvider("gps", false, true, false, false, true, true, true, 1, 1);
    }
    this$0.locationManager.setTestProviderEnabled("gps", true);
    this$0.locationManager.setTestProviderStatus("gps", 2, null, System.currentTimeMillis());
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.SimulationService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */