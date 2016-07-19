package android.support.v4.print;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;

class PrintHelperApi23
  extends PrintHelperApi20
{
  PrintHelperApi23(Context paramContext)
  {
    super(paramContext);
    mIsMinMarginsHandlingCorrect = false;
  }
  
  protected PrintAttributes.Builder copyAttributes(PrintAttributes paramPrintAttributes)
  {
    PrintAttributes.Builder localBuilder = super.copyAttributes(paramPrintAttributes);
    if (paramPrintAttributes.getDuplexMode() != 0) {
      localBuilder.setDuplexMode(paramPrintAttributes.getDuplexMode());
    }
    return localBuilder;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.print.PrintHelperApi23
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */