package fr.dvilleneuve.lockito.core.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils
{
  private static final int DEFAULT_BUFFER_SIZE = 4096;
  public static final String DIR_EXPORT = "lockito/export";
  public static final String DIR_THUMBNAILS = "thumbnails";
  
  public static int copy(@NonNull InputStream paramInputStream, @NonNull OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['က'];
    int i = 0;
    for (;;)
    {
      int j = paramInputStream.read(arrayOfByte);
      if (-1 == j) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, j);
      i += j;
    }
    return i;
  }
  
  @NonNull
  public static File getExportFile(@NonNull String paramString)
  {
    return new File(Environment.getExternalStorageDirectory(), "lockito/export" + File.separator + paramString);
  }
  
  @NonNull
  public static File getSnapshotFile(@NonNull Context paramContext, @NonNull ItineraryInfo paramItineraryInfo)
  {
    return getSnapshotFile(paramContext, String.valueOf(paramItineraryInfo.getId()));
  }
  
  @NonNull
  public static File getSnapshotFile(@NonNull Context paramContext, @NonNull String paramString)
  {
    return new File(paramContext.getFilesDir(), "thumbnails" + File.separator + paramString);
  }
  
  public static void safelyClose(@Nullable InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream) {}
  }
  
  public static void safelyClose(@Nullable OutputStream paramOutputStream)
  {
    if (paramOutputStream != null) {}
    try
    {
      paramOutputStream.close();
      return;
    }
    catch (IOException paramOutputStream) {}
  }
  
  public static byte[] toBytes(@NonNull InputStream paramInputStream)
    throws IOException
  {
    Object localObject3 = null;
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      safelyClose(paramInputStream);
    }
    finally
    {
      try
      {
        copy(paramInputStream, localByteArrayOutputStream);
        paramInputStream = localByteArrayOutputStream.toByteArray();
        safelyClose(localByteArrayOutputStream);
        return paramInputStream;
      }
      finally
      {
        paramInputStream = (InputStream)localObject1;
        Object localObject2 = localObject4;
      }
      localObject1 = finally;
      paramInputStream = (InputStream)localObject3;
    }
    throw ((Throwable)localObject1);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.IOUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */