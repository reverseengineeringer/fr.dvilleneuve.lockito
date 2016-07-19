package fr.dvilleneuve.lockito.ui.fragment;

import android.widget.EditText;
import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import fr.dvilleneuve.lockito.core.converter.ItineraryConverter;
import fr.dvilleneuve.lockito.core.converter.KmlConverter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(2130903079)
public class ImportKmlFragment
  extends ImportFragment
{
  @ViewById
  EditText xpathDescriptionText;
  @ViewById
  EditText xpathNameText;
  @ViewById
  EditText xpathPointsText;
  @ViewById
  EditText xpathWaypointsText;
  
  public ImportKmlFragment()
  {
    super(ConverterFormat.KML);
  }
  
  public ItineraryConverter getConfiguredConverter()
  {
    KmlConverter localKmlConverter = new KmlConverter();
    localKmlConverter.setXpathName(xpathNameText.getText().toString());
    localKmlConverter.setXpathDescription(xpathDescriptionText.getText().toString());
    localKmlConverter.setXpathWaypoints(xpathWaypointsText.getText().toString());
    localKmlConverter.setXpathPoints(xpathPointsText.getText().toString());
    return localKmlConverter;
  }
  
  @AfterViews
  void init()
  {
    xpathNameText.setText("/kml/Document/name/text()");
    xpathDescriptionText.setText("/kml/Document/description/text()");
    xpathWaypointsText.setText("/kml/Document/Folder[name/text()='Waypoints']/Placemark/Point/coordinates");
    xpathPointsText.setText("/kml/Document/Folder[name/text()='Tracks']/Placemark/LineString/coordinates");
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.ImportKmlFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */