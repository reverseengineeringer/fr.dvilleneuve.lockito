package fr.dvilleneuve.lockito.ui;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.io.File;

class SelectFileActivity_$1
  implements AdapterView.OnItemClickListener
{
  SelectFileActivity_$1(SelectFileActivity_ paramSelectFileActivity_) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.filesListItemClicked((File)paramAdapterView.getAdapter().getItem(paramInt));
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SelectFileActivity_.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */