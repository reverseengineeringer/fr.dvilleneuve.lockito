package fr.dvilleneuve.lockito.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GLeg
{
  public GMeasurement distance;
  public GMeasurement duration;
  public String end_address;
  public GLocation end_location;
  public String start_address;
  public GLocation start_location;
  public List<GStep> steps;
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.dto.GLeg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */