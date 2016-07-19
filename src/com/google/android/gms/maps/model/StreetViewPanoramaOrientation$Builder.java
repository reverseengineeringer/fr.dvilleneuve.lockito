package com.google.android.gms.maps.model;

public final class StreetViewPanoramaOrientation$Builder
{
  public float bearing;
  public float tilt;
  
  public StreetViewPanoramaOrientation$Builder() {}
  
  public StreetViewPanoramaOrientation$Builder(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
  {
    bearing = bearing;
    tilt = tilt;
  }
  
  public Builder bearing(float paramFloat)
  {
    bearing = paramFloat;
    return this;
  }
  
  public StreetViewPanoramaOrientation build()
  {
    return new StreetViewPanoramaOrientation(tilt, bearing);
  }
  
  public Builder tilt(float paramFloat)
  {
    tilt = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.model.StreetViewPanoramaOrientation.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */