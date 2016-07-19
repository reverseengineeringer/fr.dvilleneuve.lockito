package org.androidannotations.api.builder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class IntentBuilder<I extends IntentBuilder<I>>
  extends Builder
{
  protected final Context context;
  protected final Intent intent;
  
  public IntentBuilder(Context paramContext, Intent paramIntent)
  {
    context = paramContext;
    intent = paramIntent;
  }
  
  public IntentBuilder(Context paramContext, Class<?> paramClass)
  {
    this(paramContext, new Intent(paramContext, paramClass));
  }
  
  public I action(String paramString)
  {
    intent.setAction(paramString);
    return this;
  }
  
  public I extra(String paramString, byte paramByte)
  {
    intent.putExtra(paramString, paramByte);
    return this;
  }
  
  public I extra(String paramString, char paramChar)
  {
    intent.putExtra(paramString, paramChar);
    return this;
  }
  
  public I extra(String paramString, double paramDouble)
  {
    intent.putExtra(paramString, paramDouble);
    return this;
  }
  
  public I extra(String paramString, float paramFloat)
  {
    intent.putExtra(paramString, paramFloat);
    return this;
  }
  
  public I extra(String paramString, int paramInt)
  {
    intent.putExtra(paramString, paramInt);
    return this;
  }
  
  public I extra(String paramString, long paramLong)
  {
    intent.putExtra(paramString, paramLong);
    return this;
  }
  
  public I extra(String paramString, Bundle paramBundle)
  {
    intent.putExtra(paramString, paramBundle);
    return this;
  }
  
  public I extra(String paramString, Parcelable paramParcelable)
  {
    intent.putExtra(paramString, paramParcelable);
    return this;
  }
  
  public I extra(String paramString, Serializable paramSerializable)
  {
    intent.putExtra(paramString, paramSerializable);
    return this;
  }
  
  public I extra(String paramString, CharSequence paramCharSequence)
  {
    intent.putExtra(paramString, paramCharSequence);
    return this;
  }
  
  public I extra(String paramString1, String paramString2)
  {
    intent.putExtra(paramString1, paramString2);
    return this;
  }
  
  public I extra(String paramString, short paramShort)
  {
    intent.putExtra(paramString, paramShort);
    return this;
  }
  
  public I extra(String paramString, boolean paramBoolean)
  {
    intent.putExtra(paramString, paramBoolean);
    return this;
  }
  
  public I extra(String paramString, byte[] paramArrayOfByte)
  {
    intent.putExtra(paramString, paramArrayOfByte);
    return this;
  }
  
  public I extra(String paramString, char[] paramArrayOfChar)
  {
    intent.putExtra(paramString, paramArrayOfChar);
    return this;
  }
  
  public I extra(String paramString, double[] paramArrayOfDouble)
  {
    intent.putExtra(paramString, paramArrayOfDouble);
    return this;
  }
  
  public I extra(String paramString, float[] paramArrayOfFloat)
  {
    intent.putExtra(paramString, paramArrayOfFloat);
    return this;
  }
  
  public I extra(String paramString, int[] paramArrayOfInt)
  {
    intent.putExtra(paramString, paramArrayOfInt);
    return this;
  }
  
  public I extra(String paramString, long[] paramArrayOfLong)
  {
    intent.putExtra(paramString, paramArrayOfLong);
    return this;
  }
  
  public I extra(String paramString, Parcelable[] paramArrayOfParcelable)
  {
    intent.putExtra(paramString, paramArrayOfParcelable);
    return this;
  }
  
  public I extra(String paramString, String[] paramArrayOfString)
  {
    intent.putExtra(paramString, paramArrayOfString);
    return this;
  }
  
  public I extra(String paramString, short[] paramArrayOfShort)
  {
    intent.putExtra(paramString, paramArrayOfShort);
    return this;
  }
  
  public I extra(String paramString, boolean[] paramArrayOfBoolean)
  {
    intent.putExtra(paramString, paramArrayOfBoolean);
    return this;
  }
  
  public I extras(Intent paramIntent)
  {
    intent.putExtras(paramIntent);
    return this;
  }
  
  public I flags(int paramInt)
  {
    intent.setFlags(paramInt);
    return this;
  }
  
  public Intent get()
  {
    return intent;
  }
  
  public Context getContext()
  {
    return context;
  }
  
  public I integerArrayListExtra(String paramString, ArrayList<Integer> paramArrayList)
  {
    intent.putIntegerArrayListExtra(paramString, paramArrayList);
    return this;
  }
  
  public I parcelableArrayListExtra(String paramString, ArrayList<? extends Parcelable> paramArrayList)
  {
    intent.putParcelableArrayListExtra(paramString, paramArrayList);
    return this;
  }
  
  public I stringArrayListExtra(String paramString, ArrayList<String> paramArrayList)
  {
    intent.putStringArrayListExtra(paramString, paramArrayList);
    return this;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.builder.IntentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */