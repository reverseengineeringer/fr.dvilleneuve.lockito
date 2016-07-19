package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import java.util.List;

class CSS$1
  implements Runnable
{
  CSS$1(CSS paramCSS, CSS.GetComputedStyleForNodeRequest paramGetComputedStyleForNodeRequest, CSS.GetComputedStyleForNodeResult paramGetComputedStyleForNodeResult) {}
  
  public void run()
  {
    Object localObject = CSS.access$200(this$0).getElementForNodeId(val$request.nodeId);
    if (localObject == null)
    {
      LogUtil.e("Tried to get the style of an element that does not exist, using nodeid=" + val$request.nodeId);
      return;
    }
    CSS.access$200(this$0).getElementStyles(localObject, new StyleAccumulator()
    {
      public void store(String paramAnonymousString1, String paramAnonymousString2, boolean paramAnonymousBoolean)
      {
        if (!paramAnonymousBoolean)
        {
          CSS.CSSComputedStyleProperty localCSSComputedStyleProperty = new CSS.CSSComputedStyleProperty(null);
          name = paramAnonymousString1;
          value = paramAnonymousString2;
          val$result.computedStyle.add(localCSSComputedStyleProperty);
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */