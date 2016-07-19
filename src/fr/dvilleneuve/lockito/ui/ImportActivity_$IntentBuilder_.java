package fr.dvilleneuve.lockito.ui;

import android.content.Context;
import java.io.File;
import org.androidannotations.api.builder.ActivityIntentBuilder;

public class ImportActivity_$IntentBuilder_
  extends ActivityIntentBuilder<IntentBuilder_>
{
  private android.support.v4.app.Fragment fragmentSupport_;
  private android.app.Fragment fragment_;
  
  public ImportActivity_$IntentBuilder_(android.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), ImportActivity_.class);
    fragment_ = paramFragment;
  }
  
  public ImportActivity_$IntentBuilder_(Context paramContext)
  {
    super(paramContext, ImportActivity_.class);
  }
  
  public ImportActivity_$IntentBuilder_(android.support.v4.app.Fragment paramFragment)
  {
    super(paramFragment.getActivity(), ImportActivity_.class);
    fragmentSupport_ = paramFragment;
  }
  
  public IntentBuilder_ selectedFile(File paramFile)
  {
    return (IntentBuilder_)super.extra("selectedFile", paramFile);
  }
  
  public void startForResult(int paramInt)
  {
    if (fragmentSupport_ != null)
    {
      fragmentSupport_.startActivityForResult(intent, paramInt);
      return;
    }
    if (fragment_ != null)
    {
      fragment_.startActivityForResult(intent, paramInt);
      return;
    }
    super.startForResult(paramInt);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ImportActivity_.IntentBuilder_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */