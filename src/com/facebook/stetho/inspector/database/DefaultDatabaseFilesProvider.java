package com.facebook.stetho.inspector.database;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class DefaultDatabaseFilesProvider
  implements DatabaseFilesProvider
{
  private final Context mContext;
  
  public DefaultDatabaseFilesProvider(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public List<File> getDatabaseFiles()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = mContext.databaseList();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(new File(arrayOfString[i]));
      i += 1;
    }
    return localArrayList;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.database.DefaultDatabaseFilesProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */