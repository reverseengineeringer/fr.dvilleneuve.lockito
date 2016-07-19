package fr.dvilleneuve.lockito.core;

import fr.dvilleneuve.lockito.core.dto.GItinerary;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Rest(converters={MappingJackson2HttpMessageConverter.class}, rootUrl="http://maps.googleapis.com")
public abstract interface RestClient
{
  public abstract RestTemplate getRestTemplate();
  
  @Get("/maps/api/directions/json?origin={origin}&destination={destination}&waypoints={waypoints}&sensor=false&units=metric&mode={mode}")
  public abstract GItinerary retrieveItinerary(String paramString1, String paramString2, String paramString3, String paramString4);
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.RestClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */