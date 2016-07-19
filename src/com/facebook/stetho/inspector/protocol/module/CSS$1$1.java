package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.StyleAccumulator;
import java.util.List;

class CSS$1$1
  implements StyleAccumulator
{
  CSS$1$1(CSS.1 param1) {}
  
  public void store(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      CSS.CSSComputedStyleProperty localCSSComputedStyleProperty = new CSS.CSSComputedStyleProperty(null);
      name = paramString1;
      value = paramString2;
      this$1.val$result.computedStyle.add(localCSSComputedStyleProperty);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */