package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzab.zza;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreetViewPanoramaView
  extends FrameLayout
{
  private StreetViewPanorama ahD;
  private final zzb ahP;
  
  public StreetViewPanoramaView(Context paramContext)
  {
    super(paramContext);
    ahP = new zzb(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    ahP = new zzb(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ahP = new zzb(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
  {
    super(paramContext);
    ahP = new zzb(this, paramContext, paramStreetViewPanoramaOptions);
  }
  
  @Deprecated
  public final StreetViewPanorama getStreetViewPanorama()
  {
    if (ahD != null) {
      return ahD;
    }
    ahP.zzbpt();
    if (ahP.zzbcr() == null) {
      return null;
    }
    try
    {
      ahD = new StreetViewPanorama(((zza)ahP.zzbcr()).zzbqa().getStreetViewPanorama());
      return ahD;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
  {
    zzab.zzhj("getStreetViewPanoramaAsync() must be called on the main thread");
    ahP.getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    ahP.onCreate(paramBundle);
    if (ahP.zzbcr() == null) {
      zza.zzb(this);
    }
  }
  
  public final void onDestroy()
  {
    ahP.onDestroy();
  }
  
  public final void onLowMemory()
  {
    ahP.onLowMemory();
  }
  
  public final void onPause()
  {
    ahP.onPause();
  }
  
  public final void onResume()
  {
    ahP.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    ahP.onSaveInstanceState(paramBundle);
  }
  
  static class zza
    implements StreetViewLifecycleDelegate
  {
    private final IStreetViewPanoramaViewDelegate ahQ;
    private View ahR;
    private final ViewGroup ahp;
    
    public zza(ViewGroup paramViewGroup, IStreetViewPanoramaViewDelegate paramIStreetViewPanoramaViewDelegate)
    {
      ahQ = ((IStreetViewPanoramaViewDelegate)zzab.zzaa(paramIStreetViewPanoramaViewDelegate));
      ahp = ((ViewGroup)zzab.zzaa(paramViewGroup));
    }
    
    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
    {
      try
      {
        ahQ.getStreetViewPanoramaAsync(new zzab.zza()
        {
          public void zza(IStreetViewPanoramaDelegate paramAnonymousIStreetViewPanoramaDelegate)
            throws RemoteException
          {
            paramOnStreetViewPanoramaReadyCallback.onStreetViewPanoramaReady(new StreetViewPanorama(paramAnonymousIStreetViewPanoramaDelegate));
          }
        });
        return;
      }
      catch (RemoteException paramOnStreetViewPanoramaReadyCallback)
      {
        throw new RuntimeRemoteException(paramOnStreetViewPanoramaReadyCallback);
      }
    }
    
    public void onCreate(Bundle paramBundle)
    {
      try
      {
        ahQ.onCreate(paramBundle);
        ahR = ((View)zze.zzad(ahQ.getView()));
        ahp.removeAllViews();
        ahp.addView(ahR);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public void onDestroy()
    {
      try
      {
        ahQ.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onDestroyView()
    {
      throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public void onLowMemory()
    {
      try
      {
        ahQ.onLowMemory();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onPause()
    {
      try
      {
        ahQ.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onResume()
    {
      try
      {
        ahQ.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        ahQ.onSaveInstanceState(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public void onStart() {}
    
    public void onStop() {}
    
    public IStreetViewPanoramaViewDelegate zzbqa()
    {
      return ahQ;
    }
  }
  
  static class zzb
    extends zza<StreetViewPanoramaView.zza>
  {
    private final List<OnStreetViewPanoramaReadyCallback> ahH = new ArrayList();
    private final StreetViewPanoramaOptions ahT;
    protected zzf<StreetViewPanoramaView.zza> ahm;
    private final ViewGroup aht;
    private final Context mContext;
    
    zzb(ViewGroup paramViewGroup, Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
    {
      aht = paramViewGroup;
      mContext = paramContext;
      ahT = paramStreetViewPanoramaOptions;
    }
    
    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
    {
      if (zzbcr() != null)
      {
        ((StreetViewPanoramaView.zza)zzbcr()).getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
        return;
      }
      ahH.add(paramOnStreetViewPanoramaReadyCallback);
    }
    
    protected void zza(zzf<StreetViewPanoramaView.zza> paramzzf)
    {
      ahm = paramzzf;
      zzbpt();
    }
    
    public void zzbpt()
    {
      if ((ahm != null) && (zzbcr() == null)) {}
      try
      {
        Object localObject = zzae.zzdk(mContext).zza(zze.zzae(mContext), ahT);
        ahm.zza(new StreetViewPanoramaView.zza(aht, (IStreetViewPanoramaViewDelegate)localObject));
        localObject = ahH.iterator();
        while (((Iterator)localObject).hasNext())
        {
          OnStreetViewPanoramaReadyCallback localOnStreetViewPanoramaReadyCallback = (OnStreetViewPanoramaReadyCallback)((Iterator)localObject).next();
          ((StreetViewPanoramaView.zza)zzbcr()).getStreetViewPanoramaAsync(localOnStreetViewPanoramaReadyCallback);
        }
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
        ahH.clear();
        return;
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException) {}
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.StreetViewPanoramaView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */