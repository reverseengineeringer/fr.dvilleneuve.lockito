package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.dvilleneuve.lockito.core.UnitSystem;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import fr.dvilleneuve.lockito.core.model.SimulationConfig;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import fr.dvilleneuve.lockito.core.utils.SimulationUtils;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.UiThread.Propagation;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class DetailsView
  extends RelativeLayout
{
  @ViewById
  FixedEditTextDecimal accuracyBaseText;
  @ViewById
  FixedEditTextDecimal accuracyDeltaText;
  @ViewById
  FixedEditTextDecimal altitudeText;
  protected ItineraryInfo itineraryInfo;
  @Bean
  PreferenceManager preferenceManager;
  protected boolean propagateTextChange = true;
  protected SimulationConfig selectedPart;
  @ViewById
  FixedEditTextDecimal speedText;
  @Bean
  StringFormatHelper stringFormatHelper;
  @ViewById
  TextView summaryText;
  protected UnitSystem unitSystem;
  
  public DetailsView(Context paramContext)
  {
    super(paramContext);
  }
  
  public DetailsView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public DetailsView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void setHint(TextView paramTextView, float paramFloat)
  {
    paramTextView.setHint(String.format("%.1f", new Object[] { Float.valueOf(paramFloat) }));
  }
  
  private void setText(TextView paramTextView, float paramFloat)
  {
    if (paramFloat > 0.0F) {}
    for (String str = String.format("%.1f", new Object[] { Float.valueOf(paramFloat) });; str = null)
    {
      paramTextView.setText(str);
      return;
    }
  }
  
  @TextChange
  void accuracyBaseTextTextChanged()
  {
    if ((selectedPart != null) && (propagateTextChange))
    {
      float f = accuracyBaseText.getValue(0.0F);
      selectedPart.setAccuracyBase(unitSystem.toMeters(f));
      itineraryInfo.makHasNewChanges();
      updateSummary();
    }
  }
  
  @TextChange
  void accuracyDeltaTextTextChanged()
  {
    if ((selectedPart != null) && (propagateTextChange))
    {
      float f = accuracyDeltaText.getValue(0.0F);
      selectedPart.setAccuracyDelta(unitSystem.toMeters(f));
      itineraryInfo.makHasNewChanges();
      updateSummary();
    }
  }
  
  @AfterInject
  void afterInject()
  {
    unitSystem = preferenceManager.getUnitSystem();
  }
  
  @AfterViews
  void afterViews()
  {
    speedText.setSuffixText(unitSystem.getSpeedUnit());
    altitudeText.setSuffixText(unitSystem.getDistanceUnitBase());
    accuracyBaseText.setSuffixText(unitSystem.getDistanceUnitBase());
    accuracyDeltaText.setSuffixText(unitSystem.getDistanceUnitBase());
  }
  
  @TextChange
  void altitudeTextTextChanged()
  {
    if ((selectedPart != null) && (propagateTextChange))
    {
      float f = altitudeText.getValue(0.0F);
      selectedPart.setAltitude(unitSystem.toMeters(f));
      itineraryInfo.makHasNewChanges();
      updateSummary();
    }
  }
  
  @UiThread(propagation=UiThread.Propagation.REUSE)
  public void setData(ItineraryInfo paramItineraryInfo, Integer paramInteger1, Integer paramInteger2)
  {
    itineraryInfo = paramItineraryInfo;
    Object localObject = null;
    Leg localLeg;
    if (paramInteger1 != null)
    {
      localLeg = paramItineraryInfo.getItinerary().getLeg(paramInteger1.intValue());
      if (paramInteger2 != null)
      {
        localObject = localLeg;
        selectedPart = localLeg.get(paramInteger2.intValue());
      }
    }
    for (;;)
    {
      propagateTextChange = false;
      setHints(paramItineraryInfo, (Leg)localObject);
      setTexts();
      updateSummary();
      showData(paramItineraryInfo, paramInteger1, paramInteger2);
      propagateTextChange = true;
      return;
      selectedPart = localLeg;
      continue;
      selectedPart = paramItineraryInfo;
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    speedText.setEnabled(paramBoolean);
    altitudeText.setEnabled(paramBoolean);
    accuracyBaseText.setEnabled(paramBoolean);
    accuracyDeltaText.setEnabled(paramBoolean);
  }
  
  protected void setHints(ItineraryInfo paramItineraryInfo, Leg paramLeg)
  {
    setHint(speedText, unitSystem.fromMetersSeconds(SimulationUtils.getSpeed(paramItineraryInfo, paramLeg, null)));
    setHint(altitudeText, unitSystem.fromMeters(SimulationUtils.getAltitude(paramItineraryInfo, paramLeg, null)));
    setHint(accuracyBaseText, unitSystem.fromMeters(SimulationUtils.getAccuracyBase(paramItineraryInfo, paramLeg, null)));
    setHint(accuracyDeltaText, unitSystem.fromMeters(SimulationUtils.getAccuracyDelta(paramItineraryInfo, paramLeg, null)));
  }
  
  protected void setTexts()
  {
    if (selectedPart != null)
    {
      setText(speedText, unitSystem.fromMetersSeconds(selectedPart.getSpeed()));
      setText(altitudeText, unitSystem.fromMeters(selectedPart.getAltitude()));
      setText(accuracyBaseText, unitSystem.fromMeters(selectedPart.getAccuracyBase()));
      setText(accuracyDeltaText, unitSystem.fromMeters(selectedPart.getAccuracyDelta()));
    }
  }
  
  public abstract void showData(ItineraryInfo paramItineraryInfo, Integer paramInteger1, Integer paramInteger2);
  
  @TextChange
  void speedTextTextChanged()
  {
    if ((selectedPart != null) && (propagateTextChange))
    {
      float f = speedText.getValue(0.0F);
      selectedPart.setSpeed(unitSystem.toMetersSeconds(f));
      itineraryInfo.makHasNewChanges();
      updateSummary();
    }
  }
  
  @UiThread(propagation=UiThread.Propagation.REUSE)
  public void updateSummary()
  {
    long l1 = itineraryInfo.getDistance();
    long l2 = SimulationUtils.getDuration(itineraryInfo);
    String str1 = stringFormatHelper.formatDistance(l1);
    String str2 = stringFormatHelper.formatDuration(l2);
    summaryText.setText(getResources().getString(2131165273, new Object[] { str1, str2 }));
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.DetailsView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */