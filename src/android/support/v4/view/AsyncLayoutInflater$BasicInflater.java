package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

class AsyncLayoutInflater$BasicInflater
  extends LayoutInflater
{
  private static final String[] sClassPrefixList = { "android.widget.", "android.webkit.", "android.app." };
  
  public AsyncLayoutInflater$BasicInflater(Context paramContext)
  {
    super(paramContext);
  }
  
  public LayoutInflater cloneInContext(Context paramContext)
  {
    return new BasicInflater(paramContext);
  }
  
  protected View onCreateView(String paramString, AttributeSet paramAttributeSet)
    throws ClassNotFoundException
  {
    String[] arrayOfString = sClassPrefixList;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfString[i];
      try
      {
        localObject = createView(paramString, (String)localObject, paramAttributeSet);
        if (localObject != null) {
          return (View)localObject;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        i += 1;
      }
    }
    return super.onCreateView(paramString, paramAttributeSet);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.AsyncLayoutInflater.BasicInflater
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */