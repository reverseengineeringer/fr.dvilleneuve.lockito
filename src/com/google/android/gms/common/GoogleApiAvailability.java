package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.R.string;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzqj;
import com.google.android.gms.internal.zzqj.zza;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class GoogleApiAvailability
  extends zzc
{
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  private static final GoogleApiAvailability qU = new GoogleApiAvailability();
  
  public static GoogleApiAvailability getInstance()
  {
    return qU;
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2)
  {
    return GooglePlayServicesUtil.getErrorDialog(paramInt1, paramActivity, paramInt2);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return GooglePlayServicesUtil.getErrorDialog(paramInt1, paramActivity, paramInt2, paramOnCancelListener);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2)
  {
    return super.getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.hasResolution()) {
      return paramConnectionResult.getResolution();
    }
    int j = paramConnectionResult.getErrorCode();
    int i = j;
    if (com.google.android.gms.common.util.zzi.zzck(paramContext))
    {
      i = j;
      if (j == 2) {
        i = 42;
      }
    }
    return getErrorResolutionPendingIntent(paramContext, i, 0);
  }
  
  public final String getErrorString(int paramInt)
  {
    return super.getErrorString(paramInt);
  }
  
  @Nullable
  public String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    return super.getOpenSourceSoftwareLicenseInfo(paramContext);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext)
  {
    return super.isGooglePlayServicesAvailable(paramContext);
  }
  
  public final boolean isUserResolvableError(int paramInt)
  {
    return super.isUserResolvableError(paramInt);
  }
  
  @MainThread
  public Task<Void> makeGooglePlayServicesAvailable(Activity paramActivity)
  {
    zzab.zzhj("makeGooglePlayServicesAvailable must be called from the main thread");
    int i = isGooglePlayServicesAvailable(paramActivity);
    if (i == 0) {
      return Tasks.forResult(null);
    }
    paramActivity = zzqu.zzu(paramActivity);
    paramActivity.zzk(new ConnectionResult(i, null));
    return paramActivity.getTask();
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2)
  {
    return GooglePlayServicesUtil.showErrorDialogFragment(paramInt1, paramActivity, paramInt2);
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return GooglePlayServicesUtil.showErrorDialogFragment(paramInt1, paramActivity, paramInt2, paramOnCancelListener);
  }
  
  public void showErrorNotification(Context paramContext, int paramInt)
  {
    if (paramInt == 6) {
      Log.e("GoogleApiAvailability", "showErrorNotification(context, errorCode) is called for RESOLUTION_REQUIRED when showErrorNotification(context, result) should be called");
    }
    if (isUserResolvableError(paramInt)) {
      GooglePlayServicesUtil.showErrorNotification(paramInt, paramContext);
    }
  }
  
  public void showErrorNotification(Context paramContext, ConnectionResult paramConnectionResult)
  {
    PendingIntent localPendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    if (localPendingIntent != null) {
      GooglePlayServicesUtil.zza(paramConnectionResult.getErrorCode(), paramContext, localPendingIntent);
    }
  }
  
  public Dialog zza(Activity paramActivity, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Object localObject2 = new ProgressBar(paramActivity, null, 16842874);
    ((ProgressBar)localObject2).setIndeterminate(true);
    ((ProgressBar)localObject2).setVisibility(0);
    Object localObject1 = new AlertDialog.Builder(paramActivity);
    ((AlertDialog.Builder)localObject1).setView((View)localObject2);
    localObject2 = GooglePlayServicesUtil.zzbv(paramActivity);
    ((AlertDialog.Builder)localObject1).setMessage(paramActivity.getResources().getString(R.string.common_google_play_services_updating_text, new Object[] { localObject2 }));
    ((AlertDialog.Builder)localObject1).setTitle(R.string.common_google_play_services_updating_title);
    ((AlertDialog.Builder)localObject1).setPositiveButton("", null);
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    GooglePlayServicesUtil.zza(paramActivity, paramOnCancelListener, "GooglePlayServicesUpdatingDialog", (Dialog)localObject1);
    return (Dialog)localObject1;
  }
  
  @Nullable
  public PendingIntent zza(Context paramContext, int paramInt1, int paramInt2, @Nullable String paramString)
  {
    return super.zza(paramContext, paramInt1, paramInt2, paramString);
  }
  
  @Nullable
  public Intent zza(Context paramContext, int paramInt, @Nullable String paramString)
  {
    return super.zza(paramContext, paramInt, paramString);
  }
  
  @Nullable
  public zzqj zza(Context paramContext, zzqj.zza paramzza)
  {
    Object localObject = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addDataScheme("package");
    zzqj localzzqj = new zzqj(paramzza);
    paramContext.registerReceiver(localzzqj, (IntentFilter)localObject);
    localzzqj.setContext(paramContext);
    localObject = localzzqj;
    if (!zzm(paramContext, "com.google.android.gms"))
    {
      paramzza.zzaor();
      localzzqj.unregister();
      localObject = null;
    }
    return (zzqj)localObject;
  }
  
  public void zza(Context paramContext, ConnectionResult paramConnectionResult, int paramInt)
  {
    PendingIntent localPendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    if (localPendingIntent != null) {
      GooglePlayServicesUtil.zza(paramConnectionResult.getErrorCode(), paramContext, GoogleApiActivity.zza(paramContext, localPendingIntent, paramInt));
    }
  }
  
  public boolean zza(Activity paramActivity, @NonNull zzqp paramzzqp, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    paramzzqp = GooglePlayServicesUtil.zza(paramInt1, paramActivity, com.google.android.gms.common.internal.zzi.zza(paramzzqp, zza(paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
    if (paramzzqp == null) {
      return false;
    }
    GooglePlayServicesUtil.zza(paramActivity, paramOnCancelListener, "GooglePlayServicesErrorDialog", paramzzqp);
    return true;
  }
  
  public int zzbn(Context paramContext)
  {
    return super.zzbn(paramContext);
  }
  
  public boolean zzc(Context paramContext, int paramInt)
  {
    return super.zzc(paramContext, paramInt);
  }
  
  @Deprecated
  @Nullable
  public Intent zzfa(int paramInt)
  {
    return super.zzfa(paramInt);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.GoogleApiAvailability
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */