package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;

class Runtime$1
  implements RuntimeReplFactory
{
  public RuntimeRepl newInstance()
  {
    new RuntimeRepl()
    {
      public Object evaluate(String paramAnonymousString)
        throws Throwable
      {
        return "Not supported with legacy Runtime module";
      }
    };
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */