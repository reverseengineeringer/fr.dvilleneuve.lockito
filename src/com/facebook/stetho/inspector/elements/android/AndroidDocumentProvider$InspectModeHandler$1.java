package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.common.Predicate;

class AndroidDocumentProvider$InspectModeHandler$1
  implements Predicate<View>
{
  AndroidDocumentProvider$InspectModeHandler$1(AndroidDocumentProvider.InspectModeHandler paramInspectModeHandler) {}
  
  public boolean apply(View paramView)
  {
    return !(paramView instanceof DocumentHiddenView);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider.InspectModeHandler.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */