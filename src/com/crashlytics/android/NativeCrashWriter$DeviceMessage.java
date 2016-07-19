package com.crashlytics.android;

import java.io.IOException;

final class NativeCrashWriter$DeviceMessage
  extends NativeCrashWriter.ProtobufMessage
{
  private static final int PROTOBUF_TAG = 5;
  
  public NativeCrashWriter$DeviceMessage()
  {
    super(5, new NativeCrashWriter.ProtobufMessage[0]);
  }
  
  public int getPropertiesSize()
  {
    return 0 + CodedOutputStream.computeFloatSize(1, 0.0F) + CodedOutputStream.computeInt32Size(2, 0) + CodedOutputStream.computeBoolSize(3, false) + CodedOutputStream.computeUInt32Size(4, 0) + CodedOutputStream.computeUInt64Size(5, 0L) + CodedOutputStream.computeUInt64Size(6, 0L);
  }
  
  public void writeProperties(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    paramCodedOutputStream.writeFloat(1, 0.0F);
    paramCodedOutputStream.writeInt32(2, 0);
    paramCodedOutputStream.writeBool(3, false);
    paramCodedOutputStream.writeUInt32(4, 0);
    paramCodedOutputStream.writeUInt64(5, 0L);
    paramCodedOutputStream.writeUInt64(6, 0L);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.NativeCrashWriter.DeviceMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */