package com.facebook.stetho.inspector.runtime;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RhinoDetectingRuntimeReplFactory
  implements RuntimeReplFactory
{
  private final Context mContext;
  private RuntimeException mRhinoJsUnexpectedError;
  private RuntimeReplFactory mRhinoReplFactory;
  private boolean mSearchedForRhinoJs;
  
  public RhinoDetectingRuntimeReplFactory(Context paramContext)
  {
    mContext = paramContext;
  }
  
  @Nullable
  private static RuntimeReplFactory findRhinoReplFactory(Context paramContext)
    throws RuntimeException
  {
    try
    {
      paramContext = (RuntimeReplFactory)Class.forName("com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder").getDeclaredMethod("defaultFactory", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      LogUtil.i("Error finding stetho-js-rhino, cannot enable console evaluation!");
      return null;
    }
    catch (NoSuchMethodException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
  }
  
  public RuntimeRepl newInstance()
  {
    if (!mSearchedForRhinoJs) {
      mSearchedForRhinoJs = true;
    }
    try
    {
      mRhinoReplFactory = findRhinoReplFactory(mContext);
      if (mRhinoReplFactory != null) {
        return mRhinoReplFactory.newInstance();
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        mRhinoJsUnexpectedError = localRuntimeException;
      }
    }
    new RuntimeRepl()
    {
      public Object evaluate(String paramAnonymousString)
        throws Exception
      {
        if (mRhinoJsUnexpectedError != null) {
          return "stetho-js-rhino threw: " + mRhinoJsUnexpectedError.toString();
        }
        return "Not supported without stetho-js-rhino dependency";
      }
    };
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */