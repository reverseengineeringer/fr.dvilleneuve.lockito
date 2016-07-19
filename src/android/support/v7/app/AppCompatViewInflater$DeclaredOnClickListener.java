package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AppCompatViewInflater$DeclaredOnClickListener
  implements View.OnClickListener
{
  private final View mHostView;
  private final String mMethodName;
  private Context mResolvedContext;
  private Method mResolvedMethod;
  
  public AppCompatViewInflater$DeclaredOnClickListener(@NonNull View paramView, @NonNull String paramString)
  {
    mHostView = paramView;
    mMethodName = paramString;
  }
  
  @NonNull
  private void resolveMethod(@Nullable Context paramContext, @NonNull String paramString)
  {
    while (paramContext != null) {
      try
      {
        if (!paramContext.isRestricted())
        {
          paramString = paramContext.getClass().getMethod(mMethodName, new Class[] { View.class });
          if (paramString != null)
          {
            mResolvedMethod = paramString;
            mResolvedContext = paramContext;
            return;
          }
        }
      }
      catch (NoSuchMethodException paramString)
      {
        if ((paramContext instanceof ContextWrapper)) {
          paramContext = ((ContextWrapper)paramContext).getBaseContext();
        } else {
          paramContext = null;
        }
      }
    }
    int i = mHostView.getId();
    if (i == -1) {}
    for (paramContext = "";; paramContext = " with id '" + mHostView.getContext().getResources().getResourceEntryName(i) + "'") {
      throw new IllegalStateException("Could not find method " + mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + mHostView.getClass() + paramContext);
    }
  }
  
  public void onClick(@NonNull View paramView)
  {
    if (mResolvedMethod == null) {
      resolveMethod(mHostView.getContext(), mMethodName);
    }
    try
    {
      mResolvedMethod.invoke(mResolvedContext, new Object[] { paramView });
      return;
    }
    catch (IllegalAccessException paramView)
    {
      throw new IllegalStateException("Could not execute non-public method for android:onClick", paramView);
    }
    catch (InvocationTargetException paramView)
    {
      throw new IllegalStateException("Could not execute method for android:onClick", paramView);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatViewInflater.DeclaredOnClickListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */