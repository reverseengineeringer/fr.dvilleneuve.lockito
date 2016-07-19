package fr.dvilleneuve.lockito.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GItinerary
{
  @JsonIgnore
  public List<LatLng> locations;
  public List<GRoute> routes;
  public String status;
  @JsonIgnore
  public long totalDistance;
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.dto.GItinerary
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */