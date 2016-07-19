package fr.dvilleneuve.lockito.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import fr.dvilleneuve.lockito.core.converter.ConverterFormat;
import fr.dvilleneuve.lockito.core.converter.ItineraryConverter;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.manager.SimulationManager;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.utils.IOUtils;
import fr.dvilleneuve.lockito.ui.fragment.ImportFragment;
import fr.dvilleneuve.lockito.ui.fragment.ImportGpxFragment_;
import fr.dvilleneuve.lockito.ui.fragment.ImportGpxFragment_.FragmentBuilder_;
import fr.dvilleneuve.lockito.ui.fragment.ImportKmlFragment_;
import fr.dvilleneuve.lockito.ui.fragment.ImportKmlFragment_.FragmentBuilder_;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;

@EActivity(2130903067)
@OptionsMenu({2131755011})
public class ImportActivity
  extends AbstractActivity
{
  public static final String IMPORT_FRAGMENT_TAG = "IMPORT_FRAGMENT_TAG";
  private ImportFragment importFragment;
  @Bean
  ItineraryManager itineraryManager;
  @OptionsMenuItem
  MenuItem menuImport;
  @Extra
  File selectedFile;
  @Bean
  SimulationManager simulationManager;
  private Uri uri;
  
  private InputStream getInputStream(Uri paramUri)
    throws IOException
  {
    if (paramUri.getScheme().startsWith("http"))
    {
      paramUri = new URL(paramUri.toString());
      Logger.info("Try to download %s", new Object[] { paramUri.toString() });
      paramUri = (HttpURLConnection)paramUri.openConnection();
      paramUri.setRequestMethod("GET");
      paramUri.setDoOutput(true);
      paramUri.connect();
      return paramUri.getInputStream();
    }
    return getContentResolver().openInputStream(paramUri);
  }
  
  private Uri resolveUri(Intent paramIntent)
  {
    Object localObject = paramIntent.getData();
    if (localObject != null) {
      return (Uri)localObject;
    }
    localObject = paramIntent.getParcelableExtra("android.intent.extra.STREAM");
    if (localObject != null) {
      return (Uri)localObject;
    }
    paramIntent = paramIntent.getStringExtra("android.intent.extra.TEXT");
    try
    {
      paramIntent = Uri.parse(paramIntent);
      return paramIntent;
    }
    catch (Exception paramIntent)
    {
      paramIntent.printStackTrace();
    }
    return null;
  }
  
  @AfterViews
  void init()
  {
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setDisplayHomeAsUpEnabled(true);
      localActionBar.setHomeButtonEnabled(true);
    }
    setTitle(2131165263);
    if (selectedFile == null) {}
    for (uri = resolveUri(getIntent());; uri = Uri.fromFile(selectedFile))
    {
      Logger.info("File to import : %s", new Object[] { uri });
      resolveFileFormat(uri);
      return;
    }
  }
  
  @Background
  void loadItinerary(Uri paramUri)
  {
    updateLoadingState(true);
    Object localObject3 = null;
    ItineraryInfo localItineraryInfo = null;
    Object localObject2 = localItineraryInfo;
    Object localObject1 = localObject3;
    try
    {
      if (importFragment == null)
      {
        localObject2 = localItineraryInfo;
        localObject1 = localObject3;
        throw new IllegalArgumentException("Something went wrong. The import fragment shouldn't be null");
      }
    }
    catch (Exception paramUri)
    {
      localObject1 = localObject2;
      Logger.error("Import failed", paramUri, new Object[0]);
      localObject1 = localObject2;
      showToast(1, 2131165361, new Object[] { paramUri.getLocalizedMessage() });
      IOUtils.safelyClose((InputStream)localObject2);
      for (;;)
      {
        updateLoadingState(false);
        return;
        localObject2 = localItineraryInfo;
        localObject1 = localObject3;
        paramUri = getInputStream(paramUri);
        localObject2 = paramUri;
        localObject1 = paramUri;
        localItineraryInfo = importFragment.getConfiguredConverter().importItinerary(paramUri);
        localObject2 = paramUri;
        localObject1 = paramUri;
        showToast(1, 2131165363, new Object[0]);
        localObject2 = paramUri;
        localObject1 = paramUri;
        simulationManager.setImportedItineraryInfo(localItineraryInfo);
        localObject2 = paramUri;
        localObject1 = paramUri;
        ItineraryActivity_.intent(this).importItinerary(Boolean.valueOf(true)).start();
        localObject2 = paramUri;
        localObject1 = paramUri;
        overridePendingTransition(2130968587, 2130968588);
        localObject2 = paramUri;
        localObject1 = paramUri;
        finish();
        IOUtils.safelyClose(paramUri);
      }
    }
    finally
    {
      IOUtils.safelyClose((InputStream)localObject1);
    }
  }
  
  @OptionsItem({16908332})
  void menuHome()
  {
    finish();
  }
  
  @OptionsItem
  void menuImport()
  {
    if (menuImport != null) {
      menuImport.setEnabled(false);
    }
    loadItinerary(uri);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = super.onCreateOptionsMenu(paramMenu);
    menuImport.setIcon(new IconDrawable(this, FontAwesomeIcons.fa_check).colorRes(2131558474).actionBarSize());
    paramMenu = menuImport;
    if (importFragment != null) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      paramMenu.setEnabled(bool1);
      return bool2;
    }
  }
  
  @Background
  void resolveFileFormat(Uri paramUri)
  {
    updateLoadingState(true);
    localObject = null;
    localUri = null;
    try
    {
      paramUri = getInputStream(paramUri);
      localUri = paramUri;
      localObject = paramUri;
      ConverterFormat localConverterFormat = ItineraryConverter.detectFormat(paramUri);
      localUri = paramUri;
      localObject = paramUri;
      Logger.info("Format detected : %s", new Object[] { localConverterFormat.name() });
      localUri = paramUri;
      localObject = paramUri;
      updateUiFormatDetected(localConverterFormat);
      IOUtils.safelyClose(paramUri);
    }
    catch (Exception paramUri)
    {
      for (;;)
      {
        localObject = localUri;
        Logger.error("Format detection", paramUri, new Object[0]);
        localObject = localUri;
        showToast(1, 2131165362, new Object[] { paramUri.getLocalizedMessage() });
        IOUtils.safelyClose(localUri);
      }
    }
    finally
    {
      IOUtils.safelyClose((InputStream)localObject);
    }
    updateLoadingState(false);
  }
  
  @UiThread
  void updateUiFormatDetected(ConverterFormat paramConverterFormat)
  {
    if (menuImport != null) {
      menuImport.setEnabled(true);
    }
    FragmentManager localFragmentManager = getSupportFragmentManager();
    Fragment localFragment = localFragmentManager.findFragmentByTag("IMPORT_FRAGMENT_TAG");
    if (localFragment == null)
    {
      if (paramConverterFormat == ConverterFormat.GPX) {
        importFragment = ImportGpxFragment_.builder().build();
      }
      for (;;)
      {
        if (importFragment != null) {
          localFragmentManager.beginTransaction().add(2131624034, importFragment, "IMPORT_FRAGMENT_TAG").commit();
        }
        return;
        if (paramConverterFormat == ConverterFormat.KML) {
          importFragment = ImportKmlFragment_.builder().build();
        }
      }
    }
    importFragment = ((ImportFragment)localFragment);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ImportActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */