package fr.dvilleneuve.lockito.core.converter;

import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public abstract class ItineraryConverter
{
  private static final DocumentBuilderFactory DOCUMENT_FACTORY = ;
  private static final ItineraryConverter GPX_CONVERTER = new GpxConverter();
  private static final ItineraryConverter KML_CONVERTER = new KmlConverter();
  private static final XPath XPATH = XPATH_FACTORY.newXPath();
  private static final XPathFactory XPATH_FACTORY = XPathFactory.newInstance();
  
  public static ConverterFormat detectFormat(InputStream paramInputStream)
    throws XmlPullParserException, IOException, UnknownFormatException
  {
    Object localObject = XmlPullParserFactory.newInstance();
    ((XmlPullParserFactory)localObject).setNamespaceAware(true);
    localObject = ((XmlPullParserFactory)localObject).newPullParser();
    ((XmlPullParser)localObject).setInput(paramInputStream, "UTF-8");
    for (int i = ((XmlPullParser)localObject).getEventType(); i != 1; i = ((XmlPullParser)localObject).next()) {
      if (i == 2)
      {
        paramInputStream = ((XmlPullParser)localObject).getName();
        if (paramInputStream.equalsIgnoreCase("gpx")) {
          return ConverterFormat.GPX;
        }
        if (paramInputStream.equalsIgnoreCase("kml")) {
          return ConverterFormat.KML;
        }
      }
    }
    throw new UnknownFormatException();
  }
  
  public static String exportItinerary(ItineraryInfo paramItineraryInfo, ConverterFormat paramConverterFormat)
    throws Exception
  {
    return getConverter(paramConverterFormat).exportItinerary(paramItineraryInfo);
  }
  
  private static ItineraryConverter getConverter(ConverterFormat paramConverterFormat)
  {
    switch (paramConverterFormat)
    {
    default: 
      return GPX_CONVERTER;
    case ???: 
      return GPX_CONVERTER;
    }
    return KML_CONVERTER;
  }
  
  public static ItineraryInfo importItinerary(InputStream paramInputStream, ConverterFormat paramConverterFormat)
    throws Exception
  {
    return getConverter(paramConverterFormat).importItinerary(paramInputStream);
  }
  
  protected Element createCDataElement(Document paramDocument, Node paramNode, String paramString1, String paramString2, String... paramVarArgs)
  {
    paramNode = createElement(paramDocument, paramNode, paramString1, paramVarArgs);
    paramNode.appendChild(paramDocument.createCDATASection(paramString2));
    return paramNode;
  }
  
  protected Element createContentElement(Document paramDocument, Node paramNode, String paramString1, String paramString2, String... paramVarArgs)
  {
    paramNode = createElement(paramDocument, paramNode, paramString1, paramVarArgs);
    paramNode.appendChild(paramDocument.createTextNode(paramString2));
    return paramNode;
  }
  
  protected Document createDocument()
    throws ParserConfigurationException
  {
    return DOCUMENT_FACTORY.newDocumentBuilder().newDocument();
  }
  
  protected Element createElement(Document paramDocument, Node paramNode, String paramString, String... paramVarArgs)
  {
    paramDocument = paramDocument.createElement(paramString);
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      paramDocument.setAttribute(paramVarArgs[i], paramVarArgs[(i + 1)]);
      i += 2;
    }
    paramNode.appendChild(paramDocument);
    return paramDocument;
  }
  
  public abstract String exportItinerary(ItineraryInfo paramItineraryInfo)
    throws Exception;
  
  protected String formatDescription(ItineraryInfo paramItineraryInfo)
  {
    return "speed=" + paramItineraryInfo.getSpeed() + "," + "accuracyBase=" + paramItineraryInfo.getAccuracyBase() + "," + "accuracyDelta=" + paramItineraryInfo.getAccuracyDelta();
  }
  
  protected String getAttributeByName(Node paramNode, String paramString)
  {
    if (paramNode.getNodeType() == 1) {
      return ((Element)paramNode).getAttribute(paramString);
    }
    return null;
  }
  
  protected Double getAttributeDoubleByName(Node paramNode, String paramString)
  {
    try
    {
      double d = Double.parseDouble(getAttributeByName(paramNode, paramString));
      return Double.valueOf(d);
    }
    catch (Exception paramNode)
    {
      paramNode.printStackTrace();
    }
    return null;
  }
  
  protected Node getNode(Node paramNode, String paramString)
  {
    try
    {
      paramNode = (Node)XPATH.evaluate(paramString, paramNode, XPathConstants.NODE);
      return paramNode;
    }
    catch (XPathExpressionException paramNode)
    {
      paramNode.printStackTrace();
    }
    return null;
  }
  
  protected String getNodeContent(Node paramNode, String paramString)
  {
    try
    {
      paramNode = (String)XPATH.evaluate(paramString, paramNode, XPathConstants.STRING);
      return paramNode;
    }
    catch (XPathExpressionException paramNode)
    {
      paramNode.printStackTrace();
    }
    return null;
  }
  
  protected NodeList getNodes(Node paramNode, String paramString)
  {
    try
    {
      paramNode = (NodeList)XPATH.evaluate(paramString, paramNode, XPathConstants.NODESET);
      return paramNode;
    }
    catch (XPathExpressionException paramNode)
    {
      paramNode.printStackTrace();
    }
    return null;
  }
  
  public abstract ItineraryInfo importItinerary(InputStream paramInputStream)
    throws Exception;
  
  protected void parseDescription(ItineraryInfo paramItineraryInfo, String paramString)
  {
    paramString = paramString.split(",");
    int j = paramString.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramString[i];
      String[] arrayOfString = ((String)localObject).split("=", 2);
      if (arrayOfString.length != 2) {}
      for (;;)
      {
        i += 1;
        break;
        try
        {
          if (!arrayOfString[0].equalsIgnoreCase("speed")) {
            break label96;
          }
          paramItineraryInfo.setSpeed(Float.parseFloat(arrayOfString[1]));
        }
        catch (Exception localException)
        {
          Logger.warn("Couldn't parse %s", new Object[] { localObject });
        }
        continue;
        label96:
        if (localException[0].equalsIgnoreCase("accuracyBase")) {
          paramItineraryInfo.setAccuracyBase(Float.parseFloat(localException[1]));
        } else if (localException[0].equalsIgnoreCase("accuracyDelta")) {
          paramItineraryInfo.setAccuracyDelta(Float.parseFloat(localException[1]));
        }
      }
    }
  }
  
  protected Document parseDocument(InputStream paramInputStream)
    throws ParserConfigurationException, IOException, SAXException
  {
    return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream);
  }
  
  protected String toString(Document paramDocument)
    throws TransformerException
  {
    Transformer localTransformer = TransformerFactory.newInstance().newTransformer();
    localTransformer.setOutputProperty("indent", "yes");
    localTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    paramDocument = new DOMSource(paramDocument);
    StringWriter localStringWriter = new StringWriter();
    localTransformer.transform(paramDocument, new StreamResult(localStringWriter));
    return localStringWriter.toString();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.converter.ItineraryConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */