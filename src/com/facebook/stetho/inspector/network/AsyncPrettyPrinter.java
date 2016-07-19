package com.facebook.stetho.inspector.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public abstract interface AsyncPrettyPrinter
{
  public abstract PrettyPrinterDisplayType getPrettifiedType();
  
  public abstract void printTo(PrintWriter paramPrintWriter, InputStream paramInputStream)
    throws IOException;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.AsyncPrettyPrinter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */