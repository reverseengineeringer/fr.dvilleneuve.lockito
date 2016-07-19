package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import java.util.List;

class CSS$2
  implements Runnable
{
  CSS$2(CSS paramCSS, CSS.GetMatchedStylesForNodeRequest paramGetMatchedStylesForNodeRequest, CSS.RuleMatch paramRuleMatch) {}
  
  public void run()
  {
    Object localObject = CSS.access$200(this$0).getElementForNodeId(val$request.nodeId);
    if (localObject == null)
    {
      LogUtil.w("Failed to get style of an element that does not exist, nodeid=" + val$request.nodeId);
      return;
    }
    CSS.access$200(this$0).getElementStyles(localObject, new StyleAccumulator()
    {
      public void store(String paramAnonymousString1, String paramAnonymousString2, boolean paramAnonymousBoolean)
      {
        if (!paramAnonymousBoolean)
        {
          CSS.CSSProperty localCSSProperty = new CSS.CSSProperty(null);
          name = paramAnonymousString1;
          value = paramAnonymousString2;
          val$match.rule.style.cssProperties.add(localCSSProperty);
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */