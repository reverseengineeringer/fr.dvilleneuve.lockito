package com.google.android.gms.maps.model;

public final class StreetViewPanoramaCamera$Builder
{
  public float bearing;
  public float tilt;
  public float zoom;
  
  public StreetViewPanoramaCamera$Builder() {}
  
  public StreetViewPanoramaCamera$Builder(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    zoom = zoom;
    bearing = bearing;
    tilt = tilt;
  }
  
  public Builder bearing(float paramFloat)
  {
    bearing = paramFloat;
    return this;
  }
  
  public StreetViewPanoramaCamera build()
  {
    return new StreetViewPanoramaCamera(zoom, tilt, bearing);
  }
  
  public Builder orientation(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
  {
    tilt = tilt;
    bearing = bearing;
    return this;
  }
  
  public Builder tilt(float paramFloat)
  {
    tilt = paramFloat;
    return this;
  }
  
  public Builder zoom(float paramFloat)
  {
    zoom = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.model.StreetViewPanoramaCamera.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */