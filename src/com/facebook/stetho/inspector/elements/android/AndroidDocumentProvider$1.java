package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.inspector.elements.DocumentProviderListener;

class AndroidDocumentProvider$1
  implements Runnable
{
  AndroidDocumentProvider$1(AndroidDocumentProvider paramAndroidDocumentProvider) {}
  
  public void run()
  {
    AndroidDocumentProvider.access$002(this$0, false);
    if (AndroidDocumentProvider.access$100(this$0) != null)
    {
      AndroidDocumentProvider.access$100(this$0).onPossiblyChanged();
      AndroidDocumentProvider.access$002(this$0, true);
      this$0.postDelayed(this, 1000L);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */