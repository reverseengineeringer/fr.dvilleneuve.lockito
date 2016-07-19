package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public final class zzad$zza
{
  private boolean zzakp = false;
  private int zzaub = -1;
  private int zzaue = -1;
  private String zzauf;
  private String zzauh;
  private final Bundle zzauj = new Bundle();
  private String zzaul;
  private boolean zzaun;
  private final Bundle zzavq = new Bundle();
  private final HashSet<String> zzavv = new HashSet();
  private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzavw = new HashMap();
  private final HashSet<String> zzavx = new HashSet();
  private final HashSet<String> zzavy = new HashSet();
  private Date zzfp;
  private Location zzft;
  
  public void setManualImpressionsEnabled(boolean paramBoolean)
  {
    zzakp = paramBoolean;
  }
  
  @Deprecated
  public void zza(NetworkExtras paramNetworkExtras)
  {
    if ((paramNetworkExtras instanceof AdMobExtras))
    {
      zza(AdMobAdapter.class, ((AdMobExtras)paramNetworkExtras).getExtras());
      return;
    }
    zzavw.put(paramNetworkExtras.getClass(), paramNetworkExtras);
  }
  
  public void zza(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
  {
    zzavq.putBundle(paramClass.getName(), paramBundle);
  }
  
  public void zza(Date paramDate)
  {
    zzfp = paramDate;
  }
  
  public void zzaf(String paramString)
  {
    zzavv.add(paramString);
  }
  
  public void zzag(String paramString)
  {
    zzavx.add(paramString);
  }
  
  public void zzah(String paramString)
  {
    zzavx.remove(paramString);
  }
  
  public void zzai(String paramString)
  {
    zzauh = paramString;
  }
  
  public void zzaj(String paramString)
  {
    zzauf = paramString;
  }
  
  public void zzak(String paramString)
  {
    zzaul = paramString;
  }
  
  public void zzal(String paramString)
  {
    zzavy.add(paramString);
  }
  
  public void zzb(Location paramLocation)
  {
    zzft = paramLocation;
  }
  
  public void zzb(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
  {
    if (zzavq.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
      zzavq.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
    }
    zzavq.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(paramClass.getName(), paramBundle);
  }
  
  public void zzf(String paramString1, String paramString2)
  {
    zzauj.putString(paramString1, paramString2);
  }
  
  public void zzn(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      zzaue = i;
      return;
    }
  }
  
  public void zzo(boolean paramBoolean)
  {
    zzaun = paramBoolean;
  }
  
  public void zzt(int paramInt)
  {
    zzaub = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzad.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */