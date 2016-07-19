package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper_;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AddressEditText_
  extends AddressEditText
  implements HasViews
{
  private boolean alreadyInflated_ = false;
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  public AddressEditText_(Context paramContext)
  {
    super(paramContext);
    init_();
  }
  
  public AddressEditText_(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init_();
  }
  
  public AddressEditText_(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init_();
  }
  
  public static AddressEditText build(Context paramContext)
  {
    paramContext = new AddressEditText_(paramContext);
    paramContext.onFinishInflate();
    return paramContext;
  }
  
  public static AddressEditText build(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = new AddressEditText_(paramContext, paramAttributeSet);
    paramContext.onFinishInflate();
    return paramContext;
  }
  
  public static AddressEditText build(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = new AddressEditText_(paramContext, paramAttributeSet, paramInt);
    paramContext.onFinishInflate();
    return paramContext;
  }
  
  private void init_()
  {
    OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
    stringFormatHelper = StringFormatHelper_.getInstance_(getContext());
    OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
  }
  
  public void onFinishInflate()
  {
    if (!alreadyInflated_)
    {
      alreadyInflated_ = true;
      onViewChangedNotifier_.notifyViewChanged(this);
    }
    super.onFinishInflate();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.AddressEditText_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */