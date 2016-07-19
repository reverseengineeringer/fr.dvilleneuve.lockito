package fr.dvilleneuve.lockito.ui;

import android.annotation.TargetApi;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import fr.dvilleneuve.lockito.ui.adapter.SelectFileAdapter;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

@EActivity(2130903071)
@OptionsMenu({2131755015})
public class SelectFileActivity
  extends AbstractActivity
{
  @InstanceState
  File currentFolder = new File("/");
  @ViewById
  TextView currentFolderText;
  @ViewById
  View emptyView;
  @ViewById
  ListView filesList;
  @OptionsMenuItem
  MenuItem menuValidate;
  @Bean
  SelectFileAdapter selectFileAdapter;
  @InstanceState
  File selectedFile;
  
  private void changeFolder(File paramFile)
  {
    if (paramFile == null) {
      return;
    }
    currentFolder = paramFile;
    currentFolderText.setText(getString(2131165388, new Object[] { paramFile.getAbsolutePath() }));
    paramFile = paramFile.listFiles();
    if (paramFile == null)
    {
      selectFileAdapter.clear();
      return;
    }
    Arrays.sort(paramFile, new Comparator()
    {
      public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
      {
        String str1 = paramAnonymousFile1.getName();
        String str2 = paramAnonymousFile2.getName();
        if ((paramAnonymousFile1.isDirectory()) && (paramAnonymousFile2.isDirectory())) {
          return str1.compareTo(str2);
        }
        if ((paramAnonymousFile1.isDirectory()) && (paramAnonymousFile2.isFile())) {
          return -1;
        }
        if ((paramAnonymousFile1.isFile()) && (paramAnonymousFile2.isDirectory())) {
          return 1;
        }
        return str1.compareTo(str2);
      }
    });
    selectFileAdapter.replaceAll(Arrays.asList(paramFile));
  }
  
  private void selectFile(File paramFile)
  {
    selectedFile = paramFile;
    MenuItem localMenuItem;
    if (menuValidate != null)
    {
      localMenuItem = menuValidate;
      if (paramFile == null) {
        break label40;
      }
    }
    label40:
    for (boolean bool = true;; bool = false)
    {
      localMenuItem.setEnabled(bool);
      selectFileAdapter.setSelectedFile(paramFile);
      return;
    }
  }
  
  @AfterViews
  void afterViews()
  {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    changeFolder(currentFolder);
    selectFile(selectedFile);
    filesList.setAdapter(selectFileAdapter);
    filesList.setEmptyView(emptyView);
  }
  
  @ItemClick
  void filesListItemClicked(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      changeFolder(paramFile);
      return;
    }
    if (selectedFile != paramFile)
    {
      selectFile(paramFile);
      return;
    }
    selectFile(null);
  }
  
  @TargetApi(11)
  @OptionsItem({16908332})
  void menuHome()
  {
    finish();
  }
  
  @OptionsItem
  void menuValidate()
  {
    ImportActivity_.intent(this).selectedFile(selectedFile).start();
    finish();
  }
  
  public void onBackPressed()
  {
    if (currentFolder.getParentFile() != null)
    {
      changeFolder(currentFolder.getParentFile());
      selectFile(null);
      return;
    }
    super.onBackPressed();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = super.onCreateOptionsMenu(paramMenu);
    menuValidate.setIcon(new IconDrawable(this, FontAwesomeIcons.fa_check).colorRes(2131558474).actionBarSize());
    paramMenu = menuValidate;
    if (selectedFile != null) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      paramMenu.setEnabled(bool1);
      return bool2;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SelectFileActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */