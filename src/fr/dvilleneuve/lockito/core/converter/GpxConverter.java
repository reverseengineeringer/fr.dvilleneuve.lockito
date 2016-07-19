package fr.dvilleneuve.lockito.core.converter;

import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.collection.GeometricList;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GpxConverter
  extends ItineraryConverter
{
  public static final String XPATH_DESCRIPTION = "/gpx/metadata/desc/text()";
  public static final String XPATH_LEGS = "/gpx/trk/trkseg";
  public static final String XPATH_LEG_POINTS = "/gpx/trk/trkseg[%d]/trkpt";
  public static final String XPATH_NAME = "/gpx/metadata/name/text()";
  public static final String XPATH_WAYPOINTS = "/gpx/wpt";
  private String xpathDescription = "/gpx/metadata/desc/text()";
  private String xpathLegPoints = "/gpx/trk/trkseg[%d]/trkpt";
  private String xpathLegs = "/gpx/trk/trkseg";
  private String xpathName = "/gpx/metadata/name/text()";
  private String xpathWaypoints = "/gpx/wpt";
  
  public String exportItinerary(ItineraryInfo paramItineraryInfo)
    throws Exception
  {
    Object localObject1 = paramItineraryInfo.getItinerary();
    Object localObject3 = ((Itinerary)localObject1).getWaypoints();
    Object localObject2 = ((Itinerary)localObject1).getLegs();
    localObject1 = createDocument();
    Element localElement = createElement((Document)localObject1, (Node)localObject1, "gpx", new String[] { "creator", "Lockito", "version", "1.1" });
    Object localObject4 = createElement((Document)localObject1, localElement, "metadata", new String[0]);
    createCDataElement((Document)localObject1, (Node)localObject4, "name", paramItineraryInfo.getName(), new String[0]);
    createCDataElement((Document)localObject1, (Node)localObject4, "desc", formatDescription(paramItineraryInfo), new String[0]);
    int i;
    if (!((List)localObject3).isEmpty())
    {
      i = 0;
      localObject3 = ((List)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (Point)((Iterator)localObject3).next();
        localObject4 = createElement((Document)localObject1, localElement, "wpt", new String[] { "lat", Double.toString(((Point)localObject4).getLatitude()), "lon", Double.toString(((Point)localObject4).getLongitude()) });
        createContentElement((Document)localObject1, (Node)localObject4, "ele", "0", new String[0]);
        createCDataElement((Document)localObject1, (Node)localObject4, "name", "Waypoint " + i, new String[0]);
        createCDataElement((Document)localObject1, (Node)localObject4, "cmt", "Waypoint " + i, new String[0]);
        createCDataElement((Document)localObject1, (Node)localObject4, "desc", "Waypoint " + i, new String[0]);
        i += 1;
      }
    }
    if (!((Collection)localObject2).isEmpty())
    {
      localElement = createElement((Document)localObject1, localElement, "trk", new String[0]);
      createCDataElement((Document)localObject1, localElement, "name", paramItineraryInfo.getName(), new String[0]);
      createCDataElement((Document)localObject1, localElement, "desc", paramItineraryInfo.getName(), new String[0]);
      createContentElement((Document)localObject1, localElement, "number", Integer.toString(1), new String[0]);
      paramItineraryInfo = ((Collection)localObject2).iterator();
      while (paramItineraryInfo.hasNext())
      {
        localObject3 = (Leg)paramItineraryInfo.next();
        localObject2 = createElement((Document)localObject1, localElement, "trkseg", new String[0]);
        i = 0;
        localObject3 = ((Leg)localObject3).getPoints().iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (Point)((Iterator)localObject3).next();
          localObject4 = createElement((Document)localObject1, (Node)localObject2, "trkpt", new String[] { "lat", Double.toString(((Point)localObject4).getLatitude()), "lon", Double.toString(((Point)localObject4).getLongitude()) });
          createContentElement((Document)localObject1, (Node)localObject4, "ele", "0", new String[0]);
          createCDataElement((Document)localObject1, (Node)localObject4, "name", "Point " + i, new String[0]);
          i += 1;
        }
      }
    }
    return toString((Document)localObject1);
  }
  
  public ItineraryInfo importItinerary(InputStream paramInputStream)
    throws Exception
  {
    paramInputStream = parseDocument(paramInputStream).getDocumentElement();
    paramInputStream.normalize();
    if (!"gpx".equalsIgnoreCase(paramInputStream.getNodeName())) {
      throw new IllegalArgumentException("This file doesn't seems to be a GPX file");
    }
    ItineraryInfo localItineraryInfo = new ItineraryInfo();
    localItineraryInfo.setName(getNodeContent(paramInputStream, xpathName));
    parseDescription(localItineraryInfo, getNodeContent(paramInputStream, xpathDescription));
    Object localObject1 = new Itinerary();
    localItineraryInfo.setItinerary((Itinerary)localObject1);
    Object localObject2 = ((Itinerary)localObject1).getWaypoints();
    NodeList localNodeList = getNodes(paramInputStream, xpathWaypoints);
    int i = 0;
    int j = localNodeList.getLength();
    Node localNode;
    while (i < j)
    {
      localNode = localNodeList.item(i);
      ((List)localObject2).add(new Point(getAttributeDoubleByName(localNode, "lat").doubleValue(), getAttributeDoubleByName(localNode, "lon").doubleValue()));
      i += 1;
    }
    Logger.debug("Imported %d waypoints", new Object[] { Integer.valueOf(((List)localObject2).size()) });
    localObject1 = ((Itinerary)localObject1).getLegs();
    localObject2 = getNodes(paramInputStream, xpathLegs);
    i = 0;
    int k = ((NodeList)localObject2).getLength();
    while (i < k)
    {
      localObject2 = new Leg();
      localNodeList = getNodes(paramInputStream, String.format(xpathLegPoints, new Object[] { Integer.valueOf(i + 1) }));
      j = 0;
      int m = localNodeList.getLength();
      while (j < m)
      {
        localNode = localNodeList.item(j);
        ((Leg)localObject2).add(new Point(getAttributeDoubleByName(localNode, "lat").doubleValue(), getAttributeDoubleByName(localNode, "lon").doubleValue()));
        j += 1;
      }
      ((Collection)localObject1).add(localObject2);
      Logger.debug("Imported %d points in new leg", new Object[] { Integer.valueOf(((Leg)localObject2).getPoints().size()) });
      i += 1;
    }
    return localItineraryInfo;
  }
  
  public void setXpathDescription(String paramString)
  {
    xpathDescription = paramString;
  }
  
  public void setXpathLegPoints(String paramString)
  {
    xpathLegPoints = paramString;
  }
  
  public void setXpathLegs(String paramString)
  {
    xpathLegs = paramString;
  }
  
  public void setXpathName(String paramString)
  {
    xpathName = paramString;
  }
  
  public void setXpathWaypoints(String paramString)
  {
    xpathWaypoints = paramString;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.converter.GpxConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */