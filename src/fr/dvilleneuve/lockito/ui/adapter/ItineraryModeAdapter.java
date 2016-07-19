package fr.dvilleneuve.lockito.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.joanzapata.iconify.IconDrawable;
import fr.dvilleneuve.lockito.core.model.ItineraryMode;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ItineraryModeAdapter
  extends BaseAdapter
{
  @RootContext
  Context context;
  private ItineraryMode[] itineraryModes = ItineraryMode.values();
  
  public int getCount()
  {
    return itineraryModes.length;
  }
  
  public ItineraryMode getItem(int paramInt)
  {
    return itineraryModes[paramInt];
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = LayoutInflater.from(context).inflate(2130903075, paramViewGroup, false);
    }
    for (paramViewGroup = new Holder(paramView);; paramViewGroup = (Holder)paramView.getTag())
    {
      ItineraryMode localItineraryMode = getItem(paramInt);
      imageView.setImageDrawable(new IconDrawable(context, localItineraryMode.getIcon()).sizeRes(2131361891).colorRes(2131558461));
      return paramView;
    }
  }
  
  public int indexOf(@Nullable ItineraryMode paramItineraryMode)
  {
    if (paramItineraryMode != null)
    {
      int i = 0;
      int j = itineraryModes.length;
      while (i < j)
      {
        if (itineraryModes[i] == paramItineraryMode) {
          return i;
        }
        i += 1;
      }
    }
    return 0;
  }
  
  private class Holder
  {
    public ImageView imageView;
    
    public Holder(View paramView)
    {
      imageView = ((ImageView)paramView.findViewById(2131624059));
      paramView.setTag(this);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.ItineraryModeAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */