package org.androidannotations.api.builder;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class FragmentBuilder<I extends FragmentBuilder<I, F>, F>
  extends Builder
{
  protected Bundle args = new Bundle();
  
  public I arg(Bundle paramBundle)
  {
    args.putAll(paramBundle);
    return this;
  }
  
  public I arg(String paramString, byte paramByte)
  {
    args.putByte(paramString, paramByte);
    return this;
  }
  
  public I arg(String paramString, char paramChar)
  {
    args.putChar(paramString, paramChar);
    return this;
  }
  
  public I arg(String paramString, double paramDouble)
  {
    args.putDouble(paramString, paramDouble);
    return this;
  }
  
  public I arg(String paramString, float paramFloat)
  {
    args.putFloat(paramString, paramFloat);
    return this;
  }
  
  public I arg(String paramString, int paramInt)
  {
    args.putInt(paramString, paramInt);
    return this;
  }
  
  public I arg(String paramString, long paramLong)
  {
    args.putLong(paramString, paramLong);
    return this;
  }
  
  public I arg(String paramString, Bundle paramBundle)
  {
    args.putBundle(paramString, paramBundle);
    return this;
  }
  
  public I arg(String paramString, Parcelable paramParcelable)
  {
    args.putParcelable(paramString, paramParcelable);
    return this;
  }
  
  public I arg(String paramString, SparseArray<? extends Parcelable> paramSparseArray)
  {
    args.putSparseParcelableArray(paramString, paramSparseArray);
    return this;
  }
  
  public I arg(String paramString, Serializable paramSerializable)
  {
    args.putSerializable(paramString, paramSerializable);
    return this;
  }
  
  public I arg(String paramString, CharSequence paramCharSequence)
  {
    args.putCharSequence(paramString, paramCharSequence);
    return this;
  }
  
  public I arg(String paramString1, String paramString2)
  {
    args.putString(paramString1, paramString2);
    return this;
  }
  
  public I arg(String paramString, short paramShort)
  {
    args.putShort(paramString, paramShort);
    return this;
  }
  
  public I arg(String paramString, boolean paramBoolean)
  {
    args.putBoolean(paramString, paramBoolean);
    return this;
  }
  
  public I arg(String paramString, byte[] paramArrayOfByte)
  {
    args.putByteArray(paramString, paramArrayOfByte);
    return this;
  }
  
  public I arg(String paramString, char[] paramArrayOfChar)
  {
    args.putCharArray(paramString, paramArrayOfChar);
    return this;
  }
  
  public I arg(String paramString, double[] paramArrayOfDouble)
  {
    args.putDoubleArray(paramString, paramArrayOfDouble);
    return this;
  }
  
  public I arg(String paramString, float[] paramArrayOfFloat)
  {
    args.putFloatArray(paramString, paramArrayOfFloat);
    return this;
  }
  
  public I arg(String paramString, int[] paramArrayOfInt)
  {
    args.putIntArray(paramString, paramArrayOfInt);
    return this;
  }
  
  public I arg(String paramString, long[] paramArrayOfLong)
  {
    args.putLongArray(paramString, paramArrayOfLong);
    return this;
  }
  
  public I arg(String paramString, Parcelable[] paramArrayOfParcelable)
  {
    args.putParcelableArray(paramString, paramArrayOfParcelable);
    return this;
  }
  
  public I arg(String paramString, String[] paramArrayOfString)
  {
    args.putStringArray(paramString, paramArrayOfString);
    return this;
  }
  
  public I arg(String paramString, short[] paramArrayOfShort)
  {
    args.putShortArray(paramString, paramArrayOfShort);
    return this;
  }
  
  public I arg(String paramString, boolean[] paramArrayOfBoolean)
  {
    args.putBooleanArray(paramString, paramArrayOfBoolean);
    return this;
  }
  
  public Bundle args()
  {
    return args;
  }
  
  public abstract F build();
  
  public I integerArrayListArg(String paramString, ArrayList<Integer> paramArrayList)
  {
    args.putIntegerArrayList(paramString, paramArrayList);
    return this;
  }
  
  public I parcelableArrayListArg(String paramString, ArrayList<? extends Parcelable> paramArrayList)
  {
    args.putParcelableArrayList(paramString, paramArrayList);
    return this;
  }
  
  public I stringArrayListArg(String paramString, ArrayList<String> paramArrayList)
  {
    args.putStringArrayList(paramString, paramArrayList);
    return this;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.builder.FragmentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */