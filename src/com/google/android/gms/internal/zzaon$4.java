package com.google.android.gms.internal;

import java.io.IOException;

final class zzaon$4
  extends zzank<Character>
{
  public void zza(zzaor paramzzaor, Character paramCharacter)
    throws IOException
  {
    if (paramCharacter == null) {}
    for (paramCharacter = null;; paramCharacter = String.valueOf(paramCharacter))
    {
      paramzzaor.zztb(paramCharacter);
      return;
    }
  }
  
  public Character zzp(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    paramzzaop = paramzzaop.nextString();
    if (paramzzaop.length() != 1)
    {
      paramzzaop = String.valueOf(paramzzaop);
      if (paramzzaop.length() != 0) {}
      for (paramzzaop = "Expecting character, got: ".concat(paramzzaop);; paramzzaop = new String("Expecting character, got: ")) {
        throw new zzanh(paramzzaop);
      }
    }
    return Character.valueOf(paramzzaop.charAt(0));
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */