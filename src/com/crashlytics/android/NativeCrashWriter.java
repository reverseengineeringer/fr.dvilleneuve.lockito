package com.crashlytics.android;

import com.crashlytics.android.internal.models.BinaryImageData;
import com.crashlytics.android.internal.models.CustomAttributeData;
import com.crashlytics.android.internal.models.SessionEventData;
import com.crashlytics.android.internal.models.SignalData;
import com.crashlytics.android.internal.models.ThreadData;
import com.crashlytics.android.internal.models.ThreadData.FrameData;
import java.io.IOException;

class NativeCrashWriter
{
  private static final SignalData DEFAULT_SIGNAL = new SignalData("", "", 0L);
  private static final BinaryImageMessage[] EMPTY_BINARY_IMAGE_MESSAGES = new BinaryImageMessage[0];
  private static final ProtobufMessage[] EMPTY_CHILDREN = new ProtobufMessage[0];
  private static final CustomAttributeMessage[] EMPTY_CUSTOM_ATTRIBUTE_MESSAGES = new CustomAttributeMessage[0];
  private static final FrameMessage[] EMPTY_FRAME_MESSAGES;
  private static final ThreadMessage[] EMPTY_THREAD_MESSAGES = new ThreadMessage[0];
  static final String NDK_CRASH_TYPE = "ndk-crash";
  
  static
  {
    EMPTY_FRAME_MESSAGES = new FrameMessage[0];
  }
  
  private static RepeatedMessage createBinaryImagesMessage(BinaryImageData[] paramArrayOfBinaryImageData)
  {
    if (paramArrayOfBinaryImageData != null) {}
    for (BinaryImageMessage[] arrayOfBinaryImageMessage = new BinaryImageMessage[paramArrayOfBinaryImageData.length];; arrayOfBinaryImageMessage = EMPTY_BINARY_IMAGE_MESSAGES)
    {
      int i = 0;
      while (i < arrayOfBinaryImageMessage.length)
      {
        arrayOfBinaryImageMessage[i] = new BinaryImageMessage(paramArrayOfBinaryImageData[i]);
        i += 1;
      }
    }
    return new RepeatedMessage(arrayOfBinaryImageMessage);
  }
  
  private static RepeatedMessage createCustomAttributesMessage(CustomAttributeData[] paramArrayOfCustomAttributeData)
  {
    if (paramArrayOfCustomAttributeData != null) {}
    for (CustomAttributeMessage[] arrayOfCustomAttributeMessage = new CustomAttributeMessage[paramArrayOfCustomAttributeData.length];; arrayOfCustomAttributeMessage = EMPTY_CUSTOM_ATTRIBUTE_MESSAGES)
    {
      int i = 0;
      while (i < arrayOfCustomAttributeMessage.length)
      {
        arrayOfCustomAttributeMessage[i] = new CustomAttributeMessage(paramArrayOfCustomAttributeData[i]);
        i += 1;
      }
    }
    return new RepeatedMessage(arrayOfCustomAttributeMessage);
  }
  
  private static RepeatedMessage createFramesMessage(ThreadData.FrameData[] paramArrayOfFrameData)
  {
    if (paramArrayOfFrameData != null) {}
    for (FrameMessage[] arrayOfFrameMessage = new FrameMessage[paramArrayOfFrameData.length];; arrayOfFrameMessage = EMPTY_FRAME_MESSAGES)
    {
      int i = 0;
      while (i < arrayOfFrameMessage.length)
      {
        arrayOfFrameMessage[i] = new FrameMessage(paramArrayOfFrameData[i]);
        i += 1;
      }
    }
    return new RepeatedMessage(arrayOfFrameMessage);
  }
  
  private static RepeatedMessage createThreadsMessage(ThreadData[] paramArrayOfThreadData)
  {
    if (paramArrayOfThreadData != null) {}
    for (ThreadMessage[] arrayOfThreadMessage = new ThreadMessage[paramArrayOfThreadData.length];; arrayOfThreadMessage = EMPTY_THREAD_MESSAGES)
    {
      int i = 0;
      while (i < arrayOfThreadMessage.length)
      {
        ThreadData localThreadData = paramArrayOfThreadData[i];
        arrayOfThreadMessage[i] = new ThreadMessage(localThreadData, createFramesMessage(frames));
        i += 1;
      }
    }
    return new RepeatedMessage(arrayOfThreadMessage);
  }
  
  private static EventMessage readCrashEventData(SessionEventData paramSessionEventData)
    throws IOException
  {
    if (signal != null) {}
    for (Object localObject = signal;; localObject = DEFAULT_SIGNAL)
    {
      localObject = new ApplicationMessage(new ExecutionMessage(new SignalMessage((SignalData)localObject), createThreadsMessage(threads), createBinaryImagesMessage(binaryImages)), createCustomAttributesMessage(customAttributes));
      DeviceMessage localDeviceMessage = new DeviceMessage();
      return new EventMessage(timestamp, "ndk-crash", (ApplicationMessage)localObject, localDeviceMessage);
    }
  }
  
  public static void writeNativeCrash(SessionEventData paramSessionEventData, CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    readCrashEventData(paramSessionEventData).write(paramCodedOutputStream);
  }
  
  private static final class ApplicationMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 3;
    
    public ApplicationMessage(NativeCrashWriter.ExecutionMessage paramExecutionMessage, NativeCrashWriter.RepeatedMessage paramRepeatedMessage)
    {
      super(new NativeCrashWriter.ProtobufMessage[] { paramExecutionMessage, paramRepeatedMessage });
    }
  }
  
  private static final class BinaryImageMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 4;
    private final long baseAddr;
    private final String filePath;
    private final long imageSize;
    private final String uuid;
    
    public BinaryImageMessage(BinaryImageData paramBinaryImageData)
    {
      super(new NativeCrashWriter.ProtobufMessage[0]);
      baseAddr = baseAddress;
      imageSize = size;
      filePath = path;
      uuid = id;
    }
    
    public int getPropertiesSize()
    {
      int i = CodedOutputStream.computeUInt64Size(1, baseAddr);
      int j = CodedOutputStream.computeUInt64Size(2, imageSize);
      return CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(filePath)) + i + j + CodedOutputStream.computeBytesSize(4, ByteString.copyFromUtf8(uuid));
    }
    
    public void writeProperties(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      paramCodedOutputStream.writeUInt64(1, baseAddr);
      paramCodedOutputStream.writeUInt64(2, imageSize);
      paramCodedOutputStream.writeBytes(3, ByteString.copyFromUtf8(filePath));
      paramCodedOutputStream.writeBytes(4, ByteString.copyFromUtf8(uuid));
    }
  }
  
  private static final class CustomAttributeMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 2;
    private final String key;
    private final String value;
    
    public CustomAttributeMessage(CustomAttributeData paramCustomAttributeData)
    {
      super(new NativeCrashWriter.ProtobufMessage[0]);
      key = key;
      value = value;
    }
    
    public int getPropertiesSize()
    {
      int i = CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(key));
      if (value == null) {}
      for (String str = "";; str = value) {
        return i + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(str));
      }
    }
    
    public void writeProperties(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      paramCodedOutputStream.writeBytes(1, ByteString.copyFromUtf8(key));
      if (value == null) {}
      for (String str = "";; str = value)
      {
        paramCodedOutputStream.writeBytes(2, ByteString.copyFromUtf8(str));
        return;
      }
    }
  }
  
  private static final class DeviceMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 5;
    
    public DeviceMessage()
    {
      super(new NativeCrashWriter.ProtobufMessage[0]);
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
  
  private static final class EventMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 10;
    private final String crashType;
    private final long time;
    
    public EventMessage(long paramLong, String paramString, NativeCrashWriter.ApplicationMessage paramApplicationMessage, NativeCrashWriter.DeviceMessage paramDeviceMessage)
    {
      super(new NativeCrashWriter.ProtobufMessage[] { paramApplicationMessage, paramDeviceMessage });
      time = paramLong;
      crashType = paramString;
    }
    
    public int getPropertiesSize()
    {
      return CodedOutputStream.computeUInt64Size(1, time) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(crashType));
    }
    
    public void writeProperties(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      paramCodedOutputStream.writeUInt64(1, time);
      paramCodedOutputStream.writeBytes(2, ByteString.copyFromUtf8(crashType));
    }
  }
  
  private static final class ExecutionMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 1;
    
    public ExecutionMessage(NativeCrashWriter.SignalMessage paramSignalMessage, NativeCrashWriter.RepeatedMessage paramRepeatedMessage1, NativeCrashWriter.RepeatedMessage paramRepeatedMessage2)
    {
      super(new NativeCrashWriter.ProtobufMessage[] { paramRepeatedMessage1, paramSignalMessage, paramRepeatedMessage2 });
    }
  }
  
  private static final class FrameMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 3;
    private final long address;
    private final String file;
    private final int importance;
    private final long offset;
    private final String symbol;
    
    public FrameMessage(ThreadData.FrameData paramFrameData)
    {
      super(new NativeCrashWriter.ProtobufMessage[0]);
      address = address;
      symbol = symbol;
      file = file;
      offset = offset;
      importance = importance;
    }
    
    public int getPropertiesSize()
    {
      return CodedOutputStream.computeUInt64Size(1, address) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(symbol)) + CodedOutputStream.computeBytesSize(3, ByteString.copyFromUtf8(file)) + CodedOutputStream.computeUInt64Size(4, offset) + CodedOutputStream.computeUInt32Size(5, importance);
    }
    
    public void writeProperties(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      paramCodedOutputStream.writeUInt64(1, address);
      paramCodedOutputStream.writeBytes(2, ByteString.copyFromUtf8(symbol));
      paramCodedOutputStream.writeBytes(3, ByteString.copyFromUtf8(file));
      paramCodedOutputStream.writeUInt64(4, offset);
      paramCodedOutputStream.writeUInt32(5, importance);
    }
  }
  
  private static abstract class ProtobufMessage
  {
    private final ProtobufMessage[] children;
    private final int tag;
    
    public ProtobufMessage(int paramInt, ProtobufMessage... paramVarArgs)
    {
      tag = paramInt;
      if (paramVarArgs != null) {}
      for (;;)
      {
        children = paramVarArgs;
        return;
        paramVarArgs = NativeCrashWriter.EMPTY_CHILDREN;
      }
    }
    
    public int getPropertiesSize()
    {
      return 0;
    }
    
    public int getSize()
    {
      int i = getSizeNoTag();
      return i + CodedOutputStream.computeRawVarint32Size(i) + CodedOutputStream.computeTagSize(tag);
    }
    
    public int getSizeNoTag()
    {
      int j = getPropertiesSize();
      ProtobufMessage[] arrayOfProtobufMessage = children;
      int k = arrayOfProtobufMessage.length;
      int i = 0;
      while (i < k)
      {
        j += arrayOfProtobufMessage[i].getSize();
        i += 1;
      }
      return j;
    }
    
    public void write(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      paramCodedOutputStream.writeTag(tag, 2);
      paramCodedOutputStream.writeRawVarint32(getSizeNoTag());
      writeProperties(paramCodedOutputStream);
      ProtobufMessage[] arrayOfProtobufMessage = children;
      int j = arrayOfProtobufMessage.length;
      int i = 0;
      while (i < j)
      {
        arrayOfProtobufMessage[i].write(paramCodedOutputStream);
        i += 1;
      }
    }
    
    public void writeProperties(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {}
  }
  
  private static final class RepeatedMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private final NativeCrashWriter.ProtobufMessage[] messages;
    
    public RepeatedMessage(NativeCrashWriter.ProtobufMessage... paramVarArgs)
    {
      super(new NativeCrashWriter.ProtobufMessage[0]);
      messages = paramVarArgs;
    }
    
    public int getSize()
    {
      int j = 0;
      NativeCrashWriter.ProtobufMessage[] arrayOfProtobufMessage = messages;
      int k = arrayOfProtobufMessage.length;
      int i = 0;
      while (i < k)
      {
        j += arrayOfProtobufMessage[i].getSize();
        i += 1;
      }
      return j;
    }
    
    public void write(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      NativeCrashWriter.ProtobufMessage[] arrayOfProtobufMessage = messages;
      int j = arrayOfProtobufMessage.length;
      int i = 0;
      while (i < j)
      {
        arrayOfProtobufMessage[i].write(paramCodedOutputStream);
        i += 1;
      }
    }
  }
  
  private static final class SignalMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 3;
    private final long sigAddr;
    private final String sigCode;
    private final String sigName;
    
    public SignalMessage(SignalData paramSignalData)
    {
      super(new NativeCrashWriter.ProtobufMessage[0]);
      sigName = name;
      sigCode = code;
      sigAddr = faultAddress;
    }
    
    public int getPropertiesSize()
    {
      return CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(sigName)) + CodedOutputStream.computeBytesSize(2, ByteString.copyFromUtf8(sigCode)) + CodedOutputStream.computeUInt64Size(3, sigAddr);
    }
    
    public void writeProperties(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      paramCodedOutputStream.writeBytes(1, ByteString.copyFromUtf8(sigName));
      paramCodedOutputStream.writeBytes(2, ByteString.copyFromUtf8(sigCode));
      paramCodedOutputStream.writeUInt64(3, sigAddr);
    }
  }
  
  private static final class ThreadMessage
    extends NativeCrashWriter.ProtobufMessage
  {
    private static final int PROTOBUF_TAG = 1;
    private final int importance;
    private final String name;
    
    public ThreadMessage(ThreadData paramThreadData, NativeCrashWriter.RepeatedMessage paramRepeatedMessage)
    {
      super(new NativeCrashWriter.ProtobufMessage[] { paramRepeatedMessage });
      name = name;
      importance = importance;
    }
    
    private boolean hasName()
    {
      return (name != null) && (name.length() > 0);
    }
    
    public int getPropertiesSize()
    {
      if (hasName()) {}
      for (int i = CodedOutputStream.computeBytesSize(1, ByteString.copyFromUtf8(name));; i = 0) {
        return CodedOutputStream.computeUInt32Size(2, importance) + i;
      }
    }
    
    public void writeProperties(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      if (hasName()) {
        paramCodedOutputStream.writeBytes(1, ByteString.copyFromUtf8(name));
      }
      paramCodedOutputStream.writeUInt32(2, importance);
    }
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.NativeCrashWriter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */