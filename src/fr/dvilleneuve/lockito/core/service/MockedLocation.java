package fr.dvilleneuve.lockito.core.service;

import android.location.Location;

public class MockedLocation
{
  private final long distance;
  private final Location location;
  private final double secondsAfterLast;
  
  public MockedLocation(Location paramLocation, long paramLong, double paramDouble)
  {
    location = paramLocation;
    distance = paramLong;
    secondsAfterLast = paramDouble;
  }
  
  public long getDistance()
  {
    return distance;
  }
  
  public Location getLocation()
  {
    return location;
  }
  
  public double getSecondsAfterLast()
  {
    return secondsAfterLast;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.service.MockedLocation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */