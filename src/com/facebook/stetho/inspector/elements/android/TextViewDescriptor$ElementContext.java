package com.facebook.stetho.inspector.elements.android;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Descriptor.Host;

final class TextViewDescriptor$ElementContext
  implements TextWatcher
{
  private TextView mElement;
  
  private TextViewDescriptor$ElementContext(TextViewDescriptor paramTextViewDescriptor) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() == 0)
    {
      TextViewDescriptor.access$100(this$0).onAttributeRemoved(mElement, "text");
      return;
    }
    TextViewDescriptor.access$200(this$0).onAttributeModified(mElement, "text", paramEditable.toString());
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void hook(TextView paramTextView)
  {
    mElement = ((TextView)Util.throwIfNull(paramTextView));
    mElement.addTextChangedListener(this);
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void unhook()
  {
    if (mElement != null)
    {
      mElement.removeTextChangedListener(this);
      mElement = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.TextViewDescriptor.ElementContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */