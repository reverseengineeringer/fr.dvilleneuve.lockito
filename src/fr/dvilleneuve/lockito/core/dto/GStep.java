package fr.dvilleneuve.lockito.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GStep
{
  public GMeasurement distance;
  public GMeasurement duration;
  public GLocation end_location;
  public GPolyline polyline;
  public GLocation start_location;
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.dto.GStep
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */