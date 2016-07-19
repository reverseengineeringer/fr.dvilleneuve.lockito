package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper_;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class DetailsItineraryView_
  extends DetailsItineraryView
  implements HasViews, OnViewChangedListener
{
  private boolean alreadyInflated_ = false;
  private Handler handler_ = new Handler(Looper.getMainLooper());
  private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
  
  public DetailsItineraryView_(Context paramContext)
  {
    super(paramContext);
    init_();
  }
  
  public DetailsItineraryView_(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init_();
  }
  
  public DetailsItineraryView_(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init_();
  }
  
  public static DetailsItineraryView build(Context paramContext)
  {
    paramContext = new DetailsItineraryView_(paramContext);
    paramContext.onFinishInflate();
    return paramContext;
  }
  
  public static DetailsItineraryView build(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = new DetailsItineraryView_(paramContext, paramAttributeSet);
    paramContext.onFinishInflate();
    return paramContext;
  }
  
  public static DetailsItineraryView build(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = new DetailsItineraryView_(paramContext, paramAttributeSet, paramInt);
    paramContext.onFinishInflate();
    return paramContext;
  }
  
  private void init_()
  {
    OnViewChangedNotifier localOnViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
    OnViewChangedNotifier.registerOnViewChangedListener(this);
    preferenceManager = PreferenceManager_.getInstance_(getContext());
    stringFormatHelper = StringFormatHelper_.getInstance_(getContext());
    afterInject();
    OnViewChangedNotifier.replaceNotifier(localOnViewChangedNotifier);
  }
  
  public void onFinishInflate()
  {
    if (!alreadyInflated_)
    {
      alreadyInflated_ = true;
      inflate(getContext(), 2130903073, this);
      onViewChangedNotifier_.notifyViewChanged(this);
    }
    super.onFinishInflate();
  }
  
  public void onViewChanged(HasViews paramHasViews)
  {
    accuracyBaseText = ((FixedEditTextDecimal)paramHasViews.findViewById(2131624049));
    accuracyDeltaText = ((FixedEditTextDecimal)paramHasViews.findViewById(2131624050));
    altitudeText = ((FixedEditTextDecimal)paramHasViews.findViewById(2131624048));
    speedText = ((FixedEditTextDecimal)paramHasViews.findViewById(2131624047));
    summaryText = ((TextView)paramHasViews.findViewById(2131624051));
    TextView localTextView = (TextView)paramHasViews.findViewById(2131624048);
    if (localTextView != null) {
      localTextView.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          altitudeTextTextChanged();
        }
      });
    }
    localTextView = (TextView)paramHasViews.findViewById(2131624049);
    if (localTextView != null) {
      localTextView.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          accuracyBaseTextTextChanged();
        }
      });
    }
    localTextView = (TextView)paramHasViews.findViewById(2131624050);
    if (localTextView != null) {
      localTextView.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          accuracyDeltaTextTextChanged();
        }
      });
    }
    paramHasViews = (TextView)paramHasViews.findViewById(2131624047);
    if (paramHasViews != null) {
      paramHasViews.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          speedTextTextChanged();
        }
      });
    }
    afterViews();
  }
  
  public void setData(final ItineraryInfo paramItineraryInfo, final Integer paramInteger1, final Integer paramInteger2)
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      super.setData(paramItineraryInfo, paramInteger1, paramInteger2);
      return;
    }
    handler_.post(new Runnable()
    {
      public void run()
      {
        DetailsItineraryView_.this.setData(paramItineraryInfo, paramInteger1, paramInteger2);
      }
    });
  }
  
  public void updateSummary()
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      super.updateSummary();
      return;
    }
    handler_.post(new Runnable()
    {
      public void run()
      {
        DetailsItineraryView_.this.updateSummary();
      }
    });
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.DetailsItineraryView_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */