package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzae
  extends zzal
{
  private static final String ID = zzaf.zzhk.toString();
  private static final String awj = zzag.zzlk.toString();
  private static final String awk = zzag.zzqo.toString();
  private static final String awl = zzag.zzph.toString();
  private static final String awm = zzag.zzqy.toString();
  
  public zzae()
  {
    super(ID, new String[] { awj });
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    Object localObject = (zzai.zza)paramMap.get(awj);
    if ((localObject == null) || (localObject == zzdl.zzcdq())) {
      return zzdl.zzcdq();
    }
    String str2 = zzdl.zzg((zzai.zza)localObject);
    localObject = (zzai.zza)paramMap.get(awl);
    String str1;
    if (localObject == null)
    {
      str1 = "text";
      localObject = (zzai.zza)paramMap.get(awm);
      if (localObject != null) {
        break label148;
      }
      localObject = "base16";
      label75:
      paramMap = (zzai.zza)paramMap.get(awk);
      if ((paramMap == null) || (!zzdl.zzk(paramMap).booleanValue())) {
        break label348;
      }
    }
    label148:
    label269:
    label287:
    label308:
    label348:
    for (int i = 3;; i = 2)
    {
      for (;;)
      {
        try
        {
          if ("text".equals(str1))
          {
            paramMap = str2.getBytes();
            if (!"base16".equals(localObject)) {
              break label269;
            }
            paramMap = zzk.zzp(paramMap);
            return zzdl.zzar(paramMap);
            str1 = zzdl.zzg((zzai.zza)localObject);
            break;
            localObject = zzdl.zzg((zzai.zza)localObject);
            break label75;
          }
          if ("base16".equals(str1))
          {
            paramMap = zzk.zznn(str2);
            continue;
          }
          if ("base64".equals(str1))
          {
            paramMap = Base64.decode(str2, i);
            continue;
          }
          if ("base64url".equals(str1))
          {
            paramMap = Base64.decode(str2, i | 0x8);
            continue;
          }
          paramMap = String.valueOf(str1);
          if (paramMap.length() != 0)
          {
            paramMap = "Encode: unknown input format: ".concat(paramMap);
            zzbn.e(paramMap);
            return zzdl.zzcdq();
          }
          paramMap = new String("Encode: unknown input format: ");
          continue;
          if (!"base64".equals(localObject)) {
            break label287;
          }
        }
        catch (IllegalArgumentException paramMap)
        {
          zzbn.e("Encode: invalid input:");
          return zzdl.zzcdq();
        }
        paramMap = Base64.encodeToString(paramMap, i);
        continue;
        if (!"base64url".equals(localObject)) {
          break label308;
        }
        paramMap = Base64.encodeToString(paramMap, i | 0x8);
      }
      paramMap = String.valueOf(localObject);
      if (paramMap.length() != 0) {}
      for (paramMap = "Encode: unknown output format: ".concat(paramMap);; paramMap = new String("Encode: unknown output format: "))
      {
        zzbn.e(paramMap);
        return zzdl.zzcdq();
      }
    }
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzae
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */