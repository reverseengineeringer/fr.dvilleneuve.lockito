package fr.dvilleneuve.lockito.core.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class DialogUtils
{
  public static AlertDialog confirmDeleteItineraries(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, int paramInt)
  {
    return confirmDialog(paramContext, paramOnClickListener, paramContext.getString(2131165287), paramContext.getString(2131165286, new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public static AlertDialog confirmDeleteItinerary(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, String paramString)
  {
    return confirmDialog(paramContext, paramOnClickListener, paramContext.getString(2131165289), paramContext.getString(2131165288, new Object[] { paramString }));
  }
  
  public static AlertDialog confirmDialog(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, int paramInt1, int paramInt2)
  {
    return confirmDialog(paramContext, paramOnClickListener, paramContext.getString(paramInt1), paramContext.getString(paramInt2));
  }
  
  public static AlertDialog confirmDialog(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, String paramString1, String paramString2)
  {
    new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).setPositiveButton(2131165370, paramOnClickListener).setNegativeButton(2131165320, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).create();
  }
  
  public static AlertDialog confirmDuplicateItineraries(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, int paramInt)
  {
    return confirmDialog(paramContext, paramOnClickListener, paramContext.getString(2131165291), paramContext.getString(2131165290, new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public static AlertDialog confirmDuplicateItinerary(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, String paramString)
  {
    return confirmDialog(paramContext, paramOnClickListener, paramContext.getString(2131165293), paramContext.getString(2131165292, new Object[] { paramString }));
  }
  
  public static AlertDialog enableMock(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    new AlertDialog.Builder(paramContext).setCancelable(false).setTitle(2131165297).setMessage(2131165294).setPositiveButton(2131165296, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        try
        {
          val$context.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
          return;
        }
        catch (ActivityNotFoundException paramAnonymousDialogInterface)
        {
          val$context.startActivity(new Intent("com.android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
        }
      }
    }).setNegativeButton(2131165295, paramOnClickListener).create();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.DialogUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */