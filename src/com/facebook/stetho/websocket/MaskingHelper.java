package com.facebook.stetho.websocket;

class MaskingHelper
{
  public static void unmask(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2)
  {
    int i = 0;
    while (paramInt2 > 0)
    {
      paramArrayOfByte2[paramInt1] = ((byte)(paramArrayOfByte2[paramInt1] ^ paramArrayOfByte1[(i % paramArrayOfByte1.length)]));
      i += 1;
      paramInt2 -= 1;
      paramInt1 += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.MaskingHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */