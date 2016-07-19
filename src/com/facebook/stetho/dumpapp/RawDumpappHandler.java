package com.facebook.stetho.dumpapp;

import android.content.Context;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class RawDumpappHandler
  extends DumpappHandler
{
  private static final String RESPONSE_HEADER_EXIT_CODE = "X-FAB-ExitCode";
  
  public RawDumpappHandler(Context paramContext, Dumper paramDumper)
  {
    super(paramContext, paramDumper);
  }
  
  private static HttpEntity createResponseEntity(byte[] paramArrayOfByte)
  {
    ByteArrayEntity localByteArrayEntity = new ByteArrayEntity(paramArrayOfByte);
    if (isProbablyBinaryData(paramArrayOfByte))
    {
      localByteArrayEntity.setContentType("application/octet-stream");
      return localByteArrayEntity;
    }
    localByteArrayEntity.setContentType("text/plain");
    return localByteArrayEntity;
  }
  
  private static boolean isProbablyBinaryData(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i];
      if ((j >= 127) || ((j < 32) && (j != 13) && (j != 10) && (j != 9))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  /* Error */
  protected HttpEntity getResponseEntity(org.apache.http.HttpRequest paramHttpRequest, java.io.InputStream paramInputStream, org.apache.http.HttpResponse paramHttpResponse)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 38	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 41	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 7
    //   9: new 43	java/io/PrintStream
    //   12: dup
    //   13: aload 7
    //   15: invokespecial 46	java/io/PrintStream:<init>	(Ljava/io/OutputStream;)V
    //   18: astore 4
    //   20: new 38	java/io/ByteArrayOutputStream
    //   23: dup
    //   24: invokespecial 41	java/io/ByteArrayOutputStream:<init>	()V
    //   27: astore 5
    //   29: new 43	java/io/PrintStream
    //   32: dup
    //   33: aload 5
    //   35: invokespecial 46	java/io/PrintStream:<init>	(Ljava/io/OutputStream;)V
    //   38: astore 6
    //   40: aload_3
    //   41: ldc 8
    //   43: aload_0
    //   44: invokevirtual 50	com/facebook/stetho/dumpapp/RawDumpappHandler:getDumper	()Lcom/facebook/stetho/dumpapp/Dumper;
    //   47: aload_2
    //   48: aload 4
    //   50: aload 6
    //   52: aload_1
    //   53: invokestatic 54	com/facebook/stetho/dumpapp/RawDumpappHandler:getArgs	(Lorg/apache/http/HttpRequest;)[Ljava/lang/String;
    //   56: invokevirtual 60	com/facebook/stetho/dumpapp/Dumper:dump	(Ljava/io/InputStream;Ljava/io/PrintStream;Ljava/io/PrintStream;[Ljava/lang/String;)I
    //   59: invokestatic 66	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   62: invokeinterface 72 3 0
    //   67: aload 6
    //   69: invokevirtual 75	java/io/PrintStream:close	()V
    //   72: aload 5
    //   74: invokevirtual 79	java/io/ByteArrayOutputStream:size	()I
    //   77: ifle +14 -> 91
    //   80: getstatic 85	java/lang/System:err	Ljava/io/PrintStream;
    //   83: aload 5
    //   85: invokevirtual 89	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   88: invokevirtual 92	java/io/PrintStream:write	([B)V
    //   91: aload 4
    //   93: invokevirtual 75	java/io/PrintStream:close	()V
    //   96: aload_2
    //   97: invokevirtual 95	java/io/InputStream:close	()V
    //   100: aload 7
    //   102: invokevirtual 89	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   105: invokestatic 97	com/facebook/stetho/dumpapp/RawDumpappHandler:createResponseEntity	([B)Lorg/apache/http/HttpEntity;
    //   108: areturn
    //   109: astore_1
    //   110: aload 6
    //   112: invokevirtual 75	java/io/PrintStream:close	()V
    //   115: aload 5
    //   117: invokevirtual 79	java/io/ByteArrayOutputStream:size	()I
    //   120: ifle +14 -> 134
    //   123: getstatic 85	java/lang/System:err	Ljava/io/PrintStream;
    //   126: aload 5
    //   128: invokevirtual 89	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   131: invokevirtual 92	java/io/PrintStream:write	([B)V
    //   134: aload_1
    //   135: athrow
    //   136: astore_1
    //   137: aload 4
    //   139: invokevirtual 75	java/io/PrintStream:close	()V
    //   142: aload_1
    //   143: athrow
    //   144: astore_1
    //   145: aload_2
    //   146: invokevirtual 95	java/io/InputStream:close	()V
    //   149: aload_1
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	this	RawDumpappHandler
    //   0	151	1	paramHttpRequest	org.apache.http.HttpRequest
    //   0	151	2	paramInputStream	java.io.InputStream
    //   0	151	3	paramHttpResponse	org.apache.http.HttpResponse
    //   18	120	4	localPrintStream1	java.io.PrintStream
    //   27	100	5	localByteArrayOutputStream1	java.io.ByteArrayOutputStream
    //   38	73	6	localPrintStream2	java.io.PrintStream
    //   7	94	7	localByteArrayOutputStream2	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   40	67	109	finally
    //   20	40	136	finally
    //   67	91	136	finally
    //   110	134	136	finally
    //   134	136	136	finally
    //   9	20	144	finally
    //   91	96	144	finally
    //   137	144	144	finally
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.RawDumpappHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */