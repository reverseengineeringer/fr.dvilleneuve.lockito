package com.joanzapata.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class BaseAdapterHelper
{
  private final Context context;
  private View convertView;
  private int position;
  private final SparseArray<View> views;
  
  private BaseAdapterHelper(Context paramContext, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
  {
    context = paramContext;
    position = paramInt2;
    views = new SparseArray();
    convertView = LayoutInflater.from(paramContext).inflate(paramInt1, paramViewGroup, false);
    convertView.setTag(this);
  }
  
  public static BaseAdapterHelper get(Context paramContext, View paramView, ViewGroup paramViewGroup, int paramInt)
  {
    return get(paramContext, paramView, paramViewGroup, paramInt, -1);
  }
  
  static BaseAdapterHelper get(Context paramContext, View paramView, ViewGroup paramViewGroup, int paramInt1, int paramInt2)
  {
    if (paramView == null) {
      return new BaseAdapterHelper(paramContext, paramViewGroup, paramInt1, paramInt2);
    }
    return (BaseAdapterHelper)paramView.getTag();
  }
  
  private <T extends View> T retrieveView(int paramInt)
  {
    View localView2 = (View)views.get(paramInt);
    View localView1 = localView2;
    if (localView2 == null)
    {
      localView1 = convertView.findViewById(paramInt);
      views.put(paramInt, localView1);
    }
    return localView1;
  }
  
  public int getPosition()
  {
    if (position == -1) {
      throw new IllegalStateException("Use BaseAdapterHelper constructor with position if you need to retrieve the position.");
    }
    return position;
  }
  
  public View getView()
  {
    return convertView;
  }
  
  public <T extends View> T getView(int paramInt)
  {
    return retrieveView(paramInt);
  }
  
  public BaseAdapterHelper linkify(int paramInt)
  {
    Linkify.addLinks((TextView)retrieveView(paramInt), 15);
    return this;
  }
  
  public BaseAdapterHelper setAlpha(int paramInt, float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      retrieveView(paramInt).setAlpha(paramFloat);
      return this;
    }
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(paramFloat, paramFloat);
    localAlphaAnimation.setDuration(0L);
    localAlphaAnimation.setFillAfter(true);
    retrieveView(paramInt).startAnimation(localAlphaAnimation);
    return this;
  }
  
  public BaseAdapterHelper setBackgroundColor(int paramInt1, int paramInt2)
  {
    retrieveView(paramInt1).setBackgroundColor(paramInt2);
    return this;
  }
  
  public BaseAdapterHelper setBackgroundRes(int paramInt1, int paramInt2)
  {
    retrieveView(paramInt1).setBackgroundResource(paramInt2);
    return this;
  }
  
  public BaseAdapterHelper setImageBitmap(int paramInt, Bitmap paramBitmap)
  {
    ((ImageView)retrieveView(paramInt)).setImageBitmap(paramBitmap);
    return this;
  }
  
  public BaseAdapterHelper setImageBuilder(int paramInt, RequestCreator paramRequestCreator)
  {
    paramRequestCreator.into((ImageView)retrieveView(paramInt));
    return this;
  }
  
  public BaseAdapterHelper setImageDrawable(int paramInt, Drawable paramDrawable)
  {
    ((ImageView)retrieveView(paramInt)).setImageDrawable(paramDrawable);
    return this;
  }
  
  public BaseAdapterHelper setImageResource(int paramInt1, int paramInt2)
  {
    ((ImageView)retrieveView(paramInt1)).setImageResource(paramInt2);
    return this;
  }
  
  public BaseAdapterHelper setImageUrl(int paramInt, String paramString)
  {
    ImageView localImageView = (ImageView)retrieveView(paramInt);
    Picasso.with(context).load(paramString).into(localImageView);
    return this;
  }
  
  public BaseAdapterHelper setMax(int paramInt1, int paramInt2)
  {
    ((ProgressBar)retrieveView(paramInt1)).setMax(paramInt2);
    return this;
  }
  
  public BaseAdapterHelper setProgress(int paramInt1, int paramInt2)
  {
    ((ProgressBar)retrieveView(paramInt1)).setProgress(paramInt2);
    return this;
  }
  
  public BaseAdapterHelper setProgress(int paramInt1, int paramInt2, int paramInt3)
  {
    ProgressBar localProgressBar = (ProgressBar)retrieveView(paramInt1);
    localProgressBar.setMax(paramInt3);
    localProgressBar.setProgress(paramInt2);
    return this;
  }
  
  public BaseAdapterHelper setRating(int paramInt, float paramFloat)
  {
    ((RatingBar)retrieveView(paramInt)).setRating(paramFloat);
    return this;
  }
  
  public BaseAdapterHelper setRating(int paramInt1, float paramFloat, int paramInt2)
  {
    RatingBar localRatingBar = (RatingBar)retrieveView(paramInt1);
    localRatingBar.setMax(paramInt2);
    localRatingBar.setRating(paramFloat);
    return this;
  }
  
  public BaseAdapterHelper setText(int paramInt, String paramString)
  {
    ((TextView)retrieveView(paramInt)).setText(paramString);
    return this;
  }
  
  public BaseAdapterHelper setTextColor(int paramInt1, int paramInt2)
  {
    ((TextView)retrieveView(paramInt1)).setTextColor(paramInt2);
    return this;
  }
  
  public BaseAdapterHelper setTextColorRes(int paramInt1, int paramInt2)
  {
    ((TextView)retrieveView(paramInt1)).setTextColor(context.getResources().getColor(paramInt2));
    return this;
  }
  
  public BaseAdapterHelper setTypeface(int paramInt, Typeface paramTypeface)
  {
    ((TextView)retrieveView(paramInt)).setTypeface(paramTypeface);
    return this;
  }
  
  public BaseAdapterHelper setTypeface(Typeface paramTypeface, int... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      ((TextView)retrieveView(paramVarArgs[i])).setTypeface(paramTypeface);
      i += 1;
    }
    return this;
  }
  
  public BaseAdapterHelper setVisible(int paramInt, boolean paramBoolean)
  {
    View localView = retrieveView(paramInt);
    if (paramBoolean) {}
    for (paramInt = 0;; paramInt = 8)
    {
      localView.setVisibility(paramInt);
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.android.BaseAdapterHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */