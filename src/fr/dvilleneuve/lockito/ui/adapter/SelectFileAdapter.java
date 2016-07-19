package fr.dvilleneuve.lockito.ui.adapter;

import android.content.Context;
import android.widget.TextView;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import java.io.File;
import org.androidannotations.annotations.EBean;

@EBean
public class SelectFileAdapter
  extends QuickAdapter<File>
{
  private final IconDrawable fileDrawable;
  private final IconDrawable folderDrawable;
  private File selectedFile;
  
  public SelectFileAdapter(Context paramContext)
  {
    super(paramContext, 2130903082);
    folderDrawable = new IconDrawable(paramContext, FontAwesomeIcons.fa_folder_open);
    folderDrawable.sizeRes(2131361898);
    folderDrawable.colorRes(2131558491);
    fileDrawable = new IconDrawable(paramContext, FontAwesomeIcons.fa_file);
    fileDrawable.sizeRes(2131361898);
    fileDrawable.colorRes(2131558490);
  }
  
  protected void convert(BaseAdapterHelper paramBaseAdapterHelper, File paramFile)
  {
    paramBaseAdapterHelper = (TextView)paramBaseAdapterHelper.getView(2131624084);
    paramBaseAdapterHelper.setText(paramFile.getName());
    if (paramFile.equals(selectedFile)) {
      paramBaseAdapterHelper.setBackgroundResource(2131558489);
    }
    while (paramFile.isDirectory())
    {
      paramBaseAdapterHelper.setCompoundDrawablesWithIntrinsicBounds(folderDrawable, null, null, null);
      return;
      paramBaseAdapterHelper.setBackgroundColor(0);
    }
    paramBaseAdapterHelper.setCompoundDrawablesWithIntrinsicBounds(fileDrawable, null, null, null);
  }
  
  public void setSelectedFile(File paramFile)
  {
    selectedFile = paramFile;
    notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.SelectFileAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */