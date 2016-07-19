package fr.dvilleneuve.lockito.core.converter;

import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KmlConverter
  extends ItineraryConverter
{
  public static final String XPATH_DESCRIPTION = "/kml/Document/description/text()";
  public static final String XPATH_NAME = "/kml/Document/name/text()";
  public static final String XPATH_POINTS = "/kml/Document/Folder[name/text()='Tracks']/Placemark/LineString/coordinates";
  public static final String XPATH_WAYPOINTS = "/kml/Document/Folder[name/text()='Waypoints']/Placemark/Point/coordinates";
  private String xpathDescription = "/kml/Document/description/text()";
  private String xpathName = "/kml/Document/name/text()";
  private String xpathPoints = "/kml/Document/Folder[name/text()='Tracks']/Placemark/LineString/coordinates";
  private String xpathWaypoints = "/kml/Document/Folder[name/text()='Waypoints']/Placemark/Point/coordinates";
  
  private Point parsePoint(String paramString)
  {
    paramString = paramString.split(",");
    if (paramString.length >= 2) {
      return new Point(Double.parseDouble(paramString[1]), Double.parseDouble(paramString[0]));
    }
    return null;
  }
  
  private List<Point> parsePoints(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split(" ");
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      Point localPoint = parsePoint(paramString[i]);
      if (localPoint != null) {
        localArrayList.add(localPoint);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private String toString(Point paramPoint)
  {
    return paramPoint.getLongitude() + "," + paramPoint.getLatitude() + ",0";
  }
  
  private String toString(List<Point> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localStringBuilder.append(toString((Point)paramList.next())).append(" ");
    }
    return localStringBuilder.substring(0, localStringBuilder.length() - 1);
  }
  
  public String exportItinerary(ItineraryInfo paramItineraryInfo)
    throws Exception
  {
    Object localObject1 = paramItineraryInfo.getItinerary();
    Object localObject4 = ((Itinerary)localObject1).getWaypoints();
    Object localObject2 = ((Itinerary)localObject1).getLegs();
    localObject1 = createDocument();
    Object localObject3 = createElement((Document)localObject1, createElement((Document)localObject1, (Node)localObject1, "kml", new String[0]), "Document", new String[0]);
    createContentElement((Document)localObject1, (Node)localObject3, "name", paramItineraryInfo.getName(), new String[0]);
    createContentElement((Document)localObject1, (Node)localObject3, "description", formatDescription(paramItineraryInfo), new String[0]);
    int i;
    if (!((List)localObject4).isEmpty())
    {
      paramItineraryInfo = createElement((Document)localObject1, (Node)localObject3, "Folder", new String[0]);
      createContentElement((Document)localObject1, paramItineraryInfo, "name", "Waypoints", new String[0]);
      createContentElement((Document)localObject1, paramItineraryInfo, "open", "1", new String[0]);
      i = 0;
      localObject4 = ((List)localObject4).iterator();
      while (((Iterator)localObject4).hasNext())
      {
        Point localPoint = (Point)((Iterator)localObject4).next();
        Element localElement = createElement((Document)localObject1, paramItineraryInfo, "Placemark", new String[0]);
        createContentElement((Document)localObject1, localElement, "name", "Waypoint " + i, new String[0]);
        createContentElement((Document)localObject1, createElement((Document)localObject1, localElement, "Point", new String[0]), "coordinates", toString(localPoint), new String[0]);
        i += 1;
      }
    }
    if (!((Collection)localObject2).isEmpty())
    {
      paramItineraryInfo = createElement((Document)localObject1, (Node)localObject3, "Folder", new String[0]);
      createContentElement((Document)localObject1, paramItineraryInfo, "name", "Tracks", new String[0]);
      createContentElement((Document)localObject1, paramItineraryInfo, "open", "1", new String[0]);
      i = 0;
      localObject2 = ((Collection)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Leg)((Iterator)localObject2).next();
        localObject4 = createElement((Document)localObject1, paramItineraryInfo, "Placemark", new String[0]);
        createContentElement((Document)localObject1, (Node)localObject4, "name", "Leg " + i, new String[0]);
        localObject4 = createElement((Document)localObject1, (Node)localObject4, "LineString", new String[0]);
        createContentElement((Document)localObject1, (Node)localObject4, "tessellate", "1", new String[0]);
        createContentElement((Document)localObject1, (Node)localObject4, "coordinates", toString(((Leg)localObject3).getPoints()), new String[0]);
        i += 1;
      }
    }
    return toString((Document)localObject1);
  }
  
  public ItineraryInfo importItinerary(InputStream paramInputStream)
    throws Exception
  {
    Object localObject2 = parseDocument(paramInputStream).getDocumentElement();
    ((Element)localObject2).normalize();
    if (!"kml".equalsIgnoreCase(((Element)localObject2).getNodeName())) {
      throw new IllegalArgumentException("This file doesn't seems to be a KML file");
    }
    paramInputStream = new ItineraryInfo();
    paramInputStream.setName(getNodeContent((Node)localObject2, xpathName));
    parseDescription(paramInputStream, getNodeContent((Node)localObject2, xpathDescription));
    Object localObject1 = new Itinerary();
    paramInputStream.setItinerary((Itinerary)localObject1);
    List localList = ((Itinerary)localObject1).getWaypoints();
    Object localObject3 = getNodes((Node)localObject2, xpathWaypoints);
    int i = 0;
    int j = ((NodeList)localObject3).getLength();
    while (i < j)
    {
      localList.add(parsePoint(((NodeList)localObject3).item(i).getTextContent()));
      i += 1;
    }
    localObject1 = ((Itinerary)localObject1).getLegs();
    localObject2 = getNodes((Node)localObject2, xpathPoints);
    i = 0;
    j = ((NodeList)localObject2).getLength();
    while (i < j)
    {
      localObject3 = ((NodeList)localObject2).item(i);
      Leg localLeg = new Leg();
      localLeg.addAll(parsePoints(((Node)localObject3).getTextContent()));
      ((Collection)localObject1).add(localLeg);
      i += 1;
    }
    Logger.debug("Imported %d waypoints", new Object[] { Integer.valueOf(localList.size()) });
    return paramInputStream;
  }
  
  public void setXpathDescription(String paramString)
  {
    xpathDescription = paramString;
  }
  
  public void setXpathName(String paramString)
  {
    xpathName = paramString;
  }
  
  public void setXpathPoints(String paramString)
  {
    xpathPoints = paramString;
  }
  
  public void setXpathWaypoints(String paramString)
  {
    xpathWaypoints = paramString;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.converter.KmlConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */