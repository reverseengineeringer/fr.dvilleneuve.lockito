package com.joanzapata.iconify.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.util.TypedValue;
import android.widget.TextView;
import com.joanzapata.iconify.Icon;
import java.util.List;

public final class ParsingUtil
{
  private static final String ANDROID_PACKAGE_NAME = "android";
  
  public static float dpToPx(Context paramContext, float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  public static int getColorFromResource(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getResources();
    int i = paramContext.getIdentifier(paramString2, "color", paramString1);
    if (i <= 0) {
      return Integer.MAX_VALUE;
    }
    return paramContext.getColor(i);
  }
  
  public static float getPxFromDimen(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getResources();
    int i = paramContext.getIdentifier(paramString2, "dimen", paramString1);
    if (i <= 0) {
      return -1.0F;
    }
    return paramContext.getDimension(i);
  }
  
  private static boolean hasAnimatedSpans(SpannableStringBuilder paramSpannableStringBuilder)
  {
    boolean bool2 = false;
    paramSpannableStringBuilder = (CustomTypefaceSpan[])paramSpannableStringBuilder.getSpans(0, paramSpannableStringBuilder.length(), CustomTypefaceSpan.class);
    int j = paramSpannableStringBuilder.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramSpannableStringBuilder[i].isAnimated()) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static CharSequence parse(Context paramContext, List<IconFontDescriptorWrapper> paramList, CharSequence paramCharSequence, TextView paramTextView)
  {
    paramContext = paramContext.getApplicationContext();
    if (paramCharSequence == null) {
      return paramCharSequence;
    }
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
    recursivePrepareSpannableIndexes(paramContext, paramCharSequence.toString(), localSpannableStringBuilder, paramList, 0);
    if (hasAnimatedSpans(localSpannableStringBuilder))
    {
      if (paramTextView == null) {
        throw new IllegalArgumentException("You can't use \"spin\" without providing the target TextView.");
      }
      if (!(paramTextView instanceof HasOnViewAttachListener)) {
        throw new IllegalArgumentException(paramTextView.getClass().getSimpleName() + " does not implement " + "HasOnViewAttachListener. Please use IconTextView, IconButton or IconToggleButton.");
      }
      ((HasOnViewAttachListener)paramTextView).setOnViewAttachListener(new HasOnViewAttachListener.OnViewAttachListener()
      {
        boolean isAttached = false;
        
        public void onAttach()
        {
          isAttached = true;
          ViewCompat.postOnAnimation(val$target, new Runnable()
          {
            public void run()
            {
              if (isAttached)
              {
                val$target.invalidate();
                ViewCompat.postOnAnimation(val$target, this);
              }
            }
          });
        }
        
        public void onDetach()
        {
          isAttached = false;
        }
      });
    }
    for (;;)
    {
      return localSpannableStringBuilder;
      if ((paramTextView instanceof HasOnViewAttachListener)) {
        ((HasOnViewAttachListener)paramTextView).setOnViewAttachListener(null);
      }
    }
  }
  
  private static void recursivePrepareSpannableIndexes(Context paramContext, String paramString, SpannableStringBuilder paramSpannableStringBuilder, List<IconFontDescriptorWrapper> paramList, int paramInt)
  {
    Object localObject = paramSpannableStringBuilder.toString();
    int k = ((String)localObject).indexOf("{", paramInt);
    if (k == -1) {}
    int m;
    do
    {
      return;
      m = ((String)localObject).indexOf("}", k) + 1;
    } while (m == -1);
    String[] arrayOfString = ((String)localObject).substring(k + 1, m - 1).split(" ");
    String str = arrayOfString[0];
    localObject = null;
    Icon localIcon = null;
    paramInt = 0;
    for (;;)
    {
      if (paramInt < paramList.size())
      {
        localObject = (IconFontDescriptorWrapper)paramList.get(paramInt);
        localIcon = ((IconFontDescriptorWrapper)localObject).getIcon(str);
        if (localIcon == null) {}
      }
      else
      {
        if (localIcon != null) {
          break;
        }
        recursivePrepareSpannableIndexes(paramContext, paramString, paramSpannableStringBuilder, paramList, m);
        return;
      }
      paramInt += 1;
    }
    float f1 = -1.0F;
    paramInt = Integer.MAX_VALUE;
    float f2 = -1.0F;
    boolean bool2 = false;
    boolean bool1 = false;
    int i = 1;
    if (i < arrayOfString.length)
    {
      str = arrayOfString[i];
      if (str.equalsIgnoreCase("spin")) {
        bool2 = true;
      }
      label540:
      int j;
      label620:
      do
      {
        do
        {
          for (;;)
          {
            i += 1;
            break;
            if (str.equalsIgnoreCase("baseline"))
            {
              bool1 = true;
            }
            else if (str.matches("([0-9]*(\\.[0-9]*)?)dp"))
            {
              f1 = dpToPx(paramContext, Float.valueOf(str.substring(0, str.length() - 2)).floatValue());
            }
            else if (str.matches("([0-9]*(\\.[0-9]*)?)sp"))
            {
              f1 = spToPx(paramContext, Float.valueOf(str.substring(0, str.length() - 2)).floatValue());
            }
            else if (str.matches("([0-9]*)px"))
            {
              f1 = Integer.valueOf(str.substring(0, str.length() - 2)).intValue();
            }
            else
            {
              float f3;
              if (str.matches("@dimen/(.*)"))
              {
                f3 = getPxFromDimen(paramContext, paramContext.getPackageName(), str.substring(7));
                f1 = f3;
                if (f3 < 0.0F) {
                  throw new IllegalArgumentException("Unknown resource " + str + " in \"" + paramString + "\"");
                }
              }
              else if (str.matches("@android:dimen/(.*)"))
              {
                f3 = getPxFromDimen(paramContext, "android", str.substring(15));
                f1 = f3;
                if (f3 < 0.0F) {
                  throw new IllegalArgumentException("Unknown resource " + str + " in \"" + paramString + "\"");
                }
              }
              else if (str.matches("([0-9]*(\\.[0-9]*)?)%"))
              {
                f2 = Float.valueOf(str.substring(0, str.length() - 1)).floatValue() / 100.0F;
              }
              else
              {
                if (!str.matches("#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})")) {
                  break label540;
                }
                paramInt = Color.parseColor(str);
              }
            }
          }
          if (!str.matches("@color/(.*)")) {
            break label620;
          }
          j = getColorFromResource(paramContext, paramContext.getPackageName(), str.substring(7));
          paramInt = j;
        } while (j != Integer.MAX_VALUE);
        throw new IllegalArgumentException("Unknown resource " + str + " in \"" + paramString + "\"");
        if (!str.matches("@android:color/(.*)")) {
          break label698;
        }
        j = getColorFromResource(paramContext, "android", str.substring(15));
        paramInt = j;
      } while (j != Integer.MAX_VALUE);
      throw new IllegalArgumentException("Unknown resource " + str + " in \"" + paramString + "\"");
      label698:
      throw new IllegalArgumentException("Unknown expression " + str + " in \"" + paramString + "\"");
    }
    paramSpannableStringBuilder = paramSpannableStringBuilder.replace(k, m, "" + localIcon.character());
    paramSpannableStringBuilder.setSpan(new CustomTypefaceSpan(localIcon, ((IconFontDescriptorWrapper)localObject).getTypeface(paramContext), f1, f2, paramInt, bool2, bool1), k, k + 1, 17);
    recursivePrepareSpannableIndexes(paramContext, paramString, paramSpannableStringBuilder, paramList, k);
  }
  
  public static float spToPx(Context paramContext, float paramFloat)
  {
    return TypedValue.applyDimension(2, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.internal.ParsingUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */