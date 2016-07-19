package com.google.android.gms.tagmanager;

import android.content.Context;

public class zzz
  implements zzas
{
  private static final Object auM = new Object();
  private static zzz avZ;
  private zzck avp;
  private zzat awa;
  
  private zzz(Context paramContext)
  {
    this(zzau.zzdz(paramContext), new zzcz());
  }
  
  zzz(zzat paramzzat, zzck paramzzck)
  {
    awa = paramzzat;
    avp = paramzzck;
  }
  
  public static zzas zzds(Context paramContext)
  {
    synchronized (auM)
    {
      if (avZ == null) {
        avZ = new zzz(paramContext);
      }
      paramContext = avZ;
      return paramContext;
    }
  }
  
  public boolean zzob(String paramString)
  {
    if (!avp.zzade())
    {
      zzbn.zzcy("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
      return false;
    }
    awa.zzof(paramString);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */