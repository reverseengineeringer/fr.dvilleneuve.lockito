package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class PreviewActivity
  extends Activity
{
  private void zzm(String paramString1, String paramString2, String paramString3)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localAlertDialog.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    try
    {
      super.onCreate(paramBundle);
      zzbn.zzcx("Preview activity");
      paramBundle = getIntent().getData();
      if (!TagManager.getInstance(this).zzt(paramBundle))
      {
        paramBundle = String.valueOf(paramBundle);
        paramBundle = String.valueOf(paramBundle).length() + 73 + "Cannot preview the app with the uri: " + paramBundle + ". Launching current version instead.";
        zzbn.zzcy(paramBundle);
        zzm("Preview failure", paramBundle, "Continue");
      }
      Intent localIntent = getPackageManager().getLaunchIntentForPackage(getPackageName());
      if (localIntent != null)
      {
        paramBundle = String.valueOf(getPackageName());
        if (paramBundle.length() != 0) {}
        for (paramBundle = "Invoke the launch activity for package name: ".concat(paramBundle);; paramBundle = new String("Invoke the launch activity for package name: "))
        {
          zzbn.zzcx(paramBundle);
          startActivity(localIntent);
          return;
        }
        paramBundle = "Calling preview threw an exception: ".concat(paramBundle);
      }
    }
    catch (Exception paramBundle)
    {
      paramBundle = String.valueOf(paramBundle.getMessage());
      if (paramBundle.length() == 0) {}
    }
    for (;;)
    {
      zzbn.e(paramBundle);
      return;
      paramBundle = String.valueOf(getPackageName());
      if (paramBundle.length() != 0) {}
      for (paramBundle = "No launch activity found for package name: ".concat(paramBundle);; paramBundle = new String("No launch activity found for package name: "))
      {
        zzbn.zzcx(paramBundle);
        return;
      }
      paramBundle = new String("Calling preview threw an exception: ");
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.PreviewActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */