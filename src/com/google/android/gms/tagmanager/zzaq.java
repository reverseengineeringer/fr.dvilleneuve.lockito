package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class zzaq
  extends zzal
{
  private static final String ID = zzaf.zzhm.toString();
  private static final String awj = zzag.zzlk.toString();
  private static final String awl = zzag.zzph.toString();
  private static final String awp = zzag.zzla.toString();
  
  public zzaq()
  {
    super(ID, new String[] { awj });
  }
  
  private byte[] zzf(String paramString, byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    paramString = MessageDigest.getInstance(paramString);
    paramString.update(paramArrayOfByte);
    return paramString.digest();
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    Object localObject = (zzai.zza)paramMap.get(awj);
    if ((localObject == null) || (localObject == zzdl.zzcdq())) {
      return zzdl.zzcdq();
    }
    String str = zzdl.zzg((zzai.zza)localObject);
    localObject = (zzai.zza)paramMap.get(awp);
    if (localObject == null)
    {
      localObject = "MD5";
      paramMap = (zzai.zza)paramMap.get(awl);
      if (paramMap != null) {
        break label110;
      }
      paramMap = "text";
      label73:
      if (!"text".equals(paramMap)) {
        break label118;
      }
      paramMap = str.getBytes();
    }
    for (;;)
    {
      try
      {
        paramMap = zzdl.zzar(zzk.zzp(zzf((String)localObject, paramMap)));
        return paramMap;
      }
      catch (NoSuchAlgorithmException paramMap)
      {
        label110:
        label118:
        paramMap = String.valueOf(localObject);
        if (paramMap.length() == 0) {
          break label203;
        }
      }
      localObject = zzdl.zzg((zzai.zza)localObject);
      break;
      paramMap = zzdl.zzg(paramMap);
      break label73;
      if ("base16".equals(paramMap))
      {
        paramMap = zzk.zznn(str);
      }
      else
      {
        paramMap = String.valueOf(paramMap);
        if (paramMap.length() != 0) {}
        for (paramMap = "Hash: unknown input format: ".concat(paramMap);; paramMap = new String("Hash: unknown input format: "))
        {
          zzbn.e(paramMap);
          return zzdl.zzcdq();
        }
      }
    }
    label203:
    for (paramMap = "Hash: unknown algorithm: ".concat(paramMap);; paramMap = new String("Hash: unknown algorithm: "))
    {
      zzbn.e(paramMap);
      return zzdl.zzcdq();
    }
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzaq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */