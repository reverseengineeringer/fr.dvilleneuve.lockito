package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.StyleAccumulator;
import java.util.List;

class CSS$2$1
  implements StyleAccumulator
{
  CSS$2$1(CSS.2 param2) {}
  
  public void store(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      CSS.CSSProperty localCSSProperty = new CSS.CSSProperty(null);
      name = paramString1;
      value = paramString2;
      this$1.val$match.rule.style.cssProperties.add(localCSSProperty);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.2.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */