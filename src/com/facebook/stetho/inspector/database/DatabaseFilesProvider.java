package com.facebook.stetho.inspector.database;

import java.io.File;
import java.util.List;

public abstract interface DatabaseFilesProvider
{
  public abstract List<File> getDatabaseFiles();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.database.DatabaseFilesProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */