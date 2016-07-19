package fr.dvilleneuve.lockito.ui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import fr.dvilleneuve.lockito.core.converter.ItineraryConverter;

public abstract class ImportFragment
  extends Fragment
{
  private final ConverterFormat format;
  
  public ImportFragment(ConverterFormat paramConverterFormat)
  {
    format = paramConverterFormat;
    setRetainInstance(true);
  }
  
  public abstract ItineraryConverter getConfiguredConverter();
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    paramActivity.setTitle(getString(2131165264, new Object[] { format }));
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.ImportFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */