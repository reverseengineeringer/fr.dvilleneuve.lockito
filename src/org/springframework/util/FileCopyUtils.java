package org.springframework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;

public abstract class FileCopyUtils
{
  public static final int BUFFER_SIZE = 4096;
  
  public static int copy(File paramFile1, File paramFile2)
    throws IOException
  {
    Assert.notNull(paramFile1, "No input File specified");
    Assert.notNull(paramFile2, "No output File specified");
    return copy(new BufferedInputStream(new FileInputStream(paramFile1)), new BufferedOutputStream(new FileOutputStream(paramFile2)));
  }
  
  /* Error */
  public static int copy(InputStream paramInputStream, java.io.OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 50
    //   3: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 52
    //   9: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: iconst_0
    //   13: istore_2
    //   14: sipush 4096
    //   17: newarray <illegal type>
    //   19: astore 4
    //   21: aload_0
    //   22: aload 4
    //   24: invokevirtual 58	java/io/InputStream:read	([B)I
    //   27: istore_3
    //   28: iload_3
    //   29: iconst_m1
    //   30: if_icmpeq +18 -> 48
    //   33: aload_1
    //   34: aload 4
    //   36: iconst_0
    //   37: iload_3
    //   38: invokevirtual 64	java/io/OutputStream:write	([BII)V
    //   41: iload_2
    //   42: iload_3
    //   43: iadd
    //   44: istore_2
    //   45: goto -24 -> 21
    //   48: aload_1
    //   49: invokevirtual 67	java/io/OutputStream:flush	()V
    //   52: aload_0
    //   53: invokevirtual 70	java/io/InputStream:close	()V
    //   56: aload_1
    //   57: invokevirtual 71	java/io/OutputStream:close	()V
    //   60: iload_2
    //   61: ireturn
    //   62: astore 4
    //   64: aload_0
    //   65: invokevirtual 70	java/io/InputStream:close	()V
    //   68: aload_1
    //   69: invokevirtual 71	java/io/OutputStream:close	()V
    //   72: aload 4
    //   74: athrow
    //   75: astore_0
    //   76: goto -20 -> 56
    //   79: astore_0
    //   80: iload_2
    //   81: ireturn
    //   82: astore_0
    //   83: goto -15 -> 68
    //   86: astore_0
    //   87: goto -15 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	paramInputStream	InputStream
    //   0	90	1	paramOutputStream	java.io.OutputStream
    //   13	68	2	i	int
    //   27	17	3	j	int
    //   19	16	4	arrayOfByte	byte[]
    //   62	11	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	21	62	finally
    //   21	28	62	finally
    //   33	41	62	finally
    //   48	52	62	finally
    //   52	56	75	java/io/IOException
    //   56	60	79	java/io/IOException
    //   64	68	82	java/io/IOException
    //   68	72	86	java/io/IOException
  }
  
  /* Error */
  public static int copy(Reader paramReader, java.io.Writer paramWriter)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 74
    //   3: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 76
    //   9: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: iconst_0
    //   13: istore_2
    //   14: sipush 4096
    //   17: newarray <illegal type>
    //   19: astore 4
    //   21: aload_0
    //   22: aload 4
    //   24: invokevirtual 81	java/io/Reader:read	([C)I
    //   27: istore_3
    //   28: iload_3
    //   29: iconst_m1
    //   30: if_icmpeq +18 -> 48
    //   33: aload_1
    //   34: aload 4
    //   36: iconst_0
    //   37: iload_3
    //   38: invokevirtual 86	java/io/Writer:write	([CII)V
    //   41: iload_2
    //   42: iload_3
    //   43: iadd
    //   44: istore_2
    //   45: goto -24 -> 21
    //   48: aload_1
    //   49: invokevirtual 87	java/io/Writer:flush	()V
    //   52: aload_0
    //   53: invokevirtual 88	java/io/Reader:close	()V
    //   56: aload_1
    //   57: invokevirtual 89	java/io/Writer:close	()V
    //   60: iload_2
    //   61: ireturn
    //   62: astore 4
    //   64: aload_0
    //   65: invokevirtual 88	java/io/Reader:close	()V
    //   68: aload_1
    //   69: invokevirtual 89	java/io/Writer:close	()V
    //   72: aload 4
    //   74: athrow
    //   75: astore_0
    //   76: goto -20 -> 56
    //   79: astore_0
    //   80: iload_2
    //   81: ireturn
    //   82: astore_0
    //   83: goto -15 -> 68
    //   86: astore_0
    //   87: goto -15 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	paramReader	Reader
    //   0	90	1	paramWriter	java.io.Writer
    //   13	68	2	i	int
    //   27	17	3	j	int
    //   19	16	4	arrayOfChar	char[]
    //   62	11	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	21	62	finally
    //   21	28	62	finally
    //   33	41	62	finally
    //   48	52	62	finally
    //   52	56	75	java/io/IOException
    //   56	60	79	java/io/IOException
    //   64	68	82	java/io/IOException
    //   68	72	86	java/io/IOException
  }
  
  /* Error */
  public static void copy(String paramString, java.io.Writer paramWriter)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 92
    //   3: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 76
    //   9: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: aload_1
    //   13: aload_0
    //   14: invokevirtual 95	java/io/Writer:write	(Ljava/lang/String;)V
    //   17: aload_1
    //   18: invokevirtual 89	java/io/Writer:close	()V
    //   21: return
    //   22: astore_0
    //   23: aload_1
    //   24: invokevirtual 89	java/io/Writer:close	()V
    //   27: aload_0
    //   28: athrow
    //   29: astore_0
    //   30: return
    //   31: astore_1
    //   32: goto -5 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	paramString	String
    //   0	35	1	paramWriter	java.io.Writer
    // Exception table:
    //   from	to	target	type
    //   12	17	22	finally
    //   17	21	29	java/io/IOException
    //   23	27	31	java/io/IOException
  }
  
  public static void copy(byte[] paramArrayOfByte, File paramFile)
    throws IOException
  {
    Assert.notNull(paramArrayOfByte, "No input byte array specified");
    Assert.notNull(paramFile, "No output File specified");
    copy(new ByteArrayInputStream(paramArrayOfByte), new BufferedOutputStream(new FileOutputStream(paramFile)));
  }
  
  /* Error */
  public static void copy(byte[] paramArrayOfByte, java.io.OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 98
    //   3: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 52
    //   9: invokestatic 24	org/springframework/util/Assert:notNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: aload_1
    //   13: aload_0
    //   14: invokevirtual 106	java/io/OutputStream:write	([B)V
    //   17: aload_1
    //   18: invokevirtual 71	java/io/OutputStream:close	()V
    //   21: return
    //   22: astore_0
    //   23: aload_1
    //   24: invokevirtual 71	java/io/OutputStream:close	()V
    //   27: aload_0
    //   28: athrow
    //   29: astore_0
    //   30: return
    //   31: astore_1
    //   32: goto -5 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	paramArrayOfByte	byte[]
    //   0	35	1	paramOutputStream	java.io.OutputStream
    // Exception table:
    //   from	to	target	type
    //   12	17	22	finally
    //   17	21	29	java/io/IOException
    //   23	27	31	java/io/IOException
  }
  
  public static byte[] copyToByteArray(File paramFile)
    throws IOException
  {
    Assert.notNull(paramFile, "No input File specified");
    return copyToByteArray(new BufferedInputStream(new FileInputStream(paramFile)));
  }
  
  public static byte[] copyToByteArray(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(4096);
    copy(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static String copyToString(Reader paramReader)
    throws IOException
  {
    StringWriter localStringWriter = new StringWriter();
    copy(paramReader, localStringWriter);
    return localStringWriter.toString();
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.FileCopyUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */