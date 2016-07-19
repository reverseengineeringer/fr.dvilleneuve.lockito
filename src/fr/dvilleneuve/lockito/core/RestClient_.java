package fr.dvilleneuve.lockito.core;

import android.content.Context;
import fr.dvilleneuve.lockito.core.dto.GItinerary;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public final class RestClient_
  implements RestClient
{
  private RestTemplate restTemplate = new RestTemplate();
  private String rootUrl = "http://maps.googleapis.com";
  
  public RestClient_(Context paramContext)
  {
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
  }
  
  public RestTemplate getRestTemplate()
  {
    return restTemplate;
  }
  
  public GItinerary retrieveItinerary(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("mode", paramString4);
    localHashMap.put("origin", paramString1);
    localHashMap.put("destination", paramString2);
    localHashMap.put("waypoints", paramString3);
    return (GItinerary)restTemplate.exchange(rootUrl.concat("/maps/api/directions/json?origin={origin}&destination={destination}&waypoints={waypoints}&sensor=false&units=metric&mode={mode}"), HttpMethod.GET, null, GItinerary.class, localHashMap).getBody();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.RestClient_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */