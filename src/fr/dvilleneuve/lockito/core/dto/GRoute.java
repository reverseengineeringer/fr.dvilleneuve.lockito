package fr.dvilleneuve.lockito.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GRoute
{
  public GBounds bounds;
  public List<GLeg> legs;
  public List<Integer> waypoint_order;
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.dto.GRoute
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */