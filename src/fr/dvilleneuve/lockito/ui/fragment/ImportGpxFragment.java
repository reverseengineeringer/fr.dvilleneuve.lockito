package fr.dvilleneuve.lockito.ui.fragment;

import android.widget.EditText;
import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import fr.dvilleneuve.lockito.core.converter.GpxConverter;
import fr.dvilleneuve.lockito.core.converter.ItineraryConverter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(2130903078)
public class ImportGpxFragment
  extends ImportFragment
{
  @ViewById
  EditText xpathDescriptionText;
  @ViewById
  EditText xpathLegsText;
  @ViewById
  EditText xpathNameText;
  @ViewById
  EditText xpathPointsText;
  @ViewById
  EditText xpathWaypointsText;
  
  public ImportGpxFragment()
  {
    super(ConverterFormat.GPX);
  }
  
  public ItineraryConverter getConfiguredConverter()
  {
    GpxConverter localGpxConverter = new GpxConverter();
    localGpxConverter.setXpathName(xpathNameText.getText().toString());
    localGpxConverter.setXpathDescription(xpathDescriptionText.getText().toString());
    localGpxConverter.setXpathWaypoints(xpathWaypointsText.getText().toString());
    localGpxConverter.setXpathLegs(xpathLegsText.getText().toString());
    localGpxConverter.setXpathLegPoints(xpathPointsText.getText().toString());
    return localGpxConverter;
  }
  
  @AfterViews
  void init()
  {
    xpathNameText.setText("/gpx/metadata/name/text()");
    xpathDescriptionText.setText("/gpx/metadata/desc/text()");
    xpathWaypointsText.setText("/gpx/wpt");
    xpathLegsText.setText("/gpx/trk/trkseg");
    xpathPointsText.setText("/gpx/trk/trkseg[%d]/trkpt");
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.ImportGpxFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */