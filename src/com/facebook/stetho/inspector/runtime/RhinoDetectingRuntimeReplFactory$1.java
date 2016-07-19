package com.facebook.stetho.inspector.runtime;

import com.facebook.stetho.inspector.console.RuntimeRepl;

class RhinoDetectingRuntimeReplFactory$1
  implements RuntimeRepl
{
  RhinoDetectingRuntimeReplFactory$1(RhinoDetectingRuntimeReplFactory paramRhinoDetectingRuntimeReplFactory) {}
  
  public Object evaluate(String paramString)
    throws Exception
  {
    if (RhinoDetectingRuntimeReplFactory.access$000(this$0) != null) {
      return "stetho-js-rhino threw: " + RhinoDetectingRuntimeReplFactory.access$000(this$0).toString();
    }
    return "Not supported without stetho-js-rhino dependency";
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */