package fr.dvilleneuve.lockito.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.joanzapata.android.BaseAdapterHelper;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.utils.IOUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

@EBean
public class ItinerariesAdapter
  extends BaseAdapter
{
  @RootContext
  Context context;
  private List<ItineraryInfo> itineraries = Collections.emptyList();
  @DimensionPixelSizeRes
  int itinerariesItemPadding;
  @DimensionPixelSizeRes(2131361885)
  int itinerariesItemPaddingBottom;
  @DimensionPixelSizeRes(2131361886)
  int itinerariesItemPaddingRight;
  @Bean
  PreferenceManager preferenceManager;
  private SparseBooleanArray selectedItems = new SparseBooleanArray();
  @Bean
  StringFormatHelper stringFormatHelper;
  
  public void clearSelectedItems()
  {
    selectedItems.clear();
    notifyDataSetChanged();
  }
  
  public int getCount()
  {
    return itineraries.size();
  }
  
  public ItineraryInfo getItem(int paramInt)
  {
    return (ItineraryInfo)itineraries.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public List<ItineraryInfo> getSelectedItems()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    int j = getCount();
    while (i < j)
    {
      if (isSelected(i)) {
        localArrayList.add(getItem(i));
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    boolean bool2 = preferenceManager.shouldShowThumbnails();
    ItineraryInfo localItineraryInfo = getItem(paramInt);
    Itinerary localItinerary = localItineraryInfo.getItinerary();
    Date localDate2 = localItineraryInfo.getUpdatedDate();
    Date localDate1 = localDate2;
    if (localDate2 == null) {
      localDate1 = localItineraryInfo.getCreationDate();
    }
    paramView = BaseAdapterHelper.get(context, paramView, paramViewGroup, 2130903081);
    paramView.setText(2131624075, localItineraryInfo.getName());
    paramView.setText(2131624076, context.getString(2131165383, new Object[] { Long.valueOf(localItineraryInfo.getId()) }));
    paramView.setText(2131624082, stringFormatHelper.formatUpdatedDate(localDate1, true));
    paramView.setText(2131624078, stringFormatHelper.formatAccuracy(localItineraryInfo.getAccuracyBase(), localItineraryInfo.getAccuracyDelta(), true));
    paramView.setText(2131624047, stringFormatHelper.formatSpeed(localItineraryInfo.getSpeed(), true));
    paramView.setText(2131624079, stringFormatHelper.formatDistance(localItineraryInfo.getDistance(), true));
    paramView.setText(2131624081, stringFormatHelper.formatDuration(localItineraryInfo.getDuration(), true));
    paramView.setText(2131624083, stringFormatHelper.formatCounter(localItineraryInfo.getPlayCounter(), true));
    boolean bool1;
    if (!localItinerary.isSinglePoint())
    {
      bool1 = true;
      paramView.setVisible(2131624047, bool1);
      if (localItinerary.isSinglePoint()) {
        break label377;
      }
      bool1 = true;
      label254:
      paramView.setVisible(2131624079, bool1);
      if (localItinerary.isSinglePoint()) {
        break label383;
      }
      bool1 = true;
      label274:
      paramView.setVisible(2131624081, bool1);
      paramView.setVisible(2131624074, bool2);
      if (bool2)
      {
        paramViewGroup = IOUtils.getSnapshotFile(context, localItineraryInfo);
        if (!paramViewGroup.exists()) {
          break label389;
        }
        paramView.setImageBitmap(2131624074, BitmapFactory.decodeFile(paramViewGroup.getAbsolutePath()));
      }
      label328:
      paramView = paramView.getView();
      if (!isSelected(paramInt)) {
        break label401;
      }
    }
    label377:
    label383:
    label389:
    label401:
    for (paramInt = 2130837639;; paramInt = 2130837640)
    {
      paramView.setBackgroundResource(paramInt);
      paramView.setPadding(itinerariesItemPadding, itinerariesItemPadding, itinerariesItemPaddingRight, itinerariesItemPaddingBottom);
      return paramView;
      bool1 = false;
      break;
      bool1 = false;
      break label254;
      bool1 = false;
      break label274;
      paramView.setImageResource(2131624074, 2130837635);
      break label328;
    }
  }
  
  public boolean isSelected(int paramInt)
  {
    return selectedItems.get(paramInt);
  }
  
  public void setItineraries(List<ItineraryInfo> paramList)
  {
    itineraries = paramList;
    notifyDataSetChanged();
  }
  
  public void setSelectedItem(int paramInt, boolean paramBoolean)
  {
    selectedItems.put(paramInt, paramBoolean);
    notifyDataSetChanged();
  }
  
  public void toggleSelectedItem(int paramInt)
  {
    if (!selectedItems.get(paramInt)) {}
    for (boolean bool = true;; bool = false)
    {
      setSelectedItem(paramInt, bool);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.ItinerariesAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */