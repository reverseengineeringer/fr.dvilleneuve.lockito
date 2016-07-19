package io.fabric.sdk.android;

public class SilentLogger
  implements Logger
{
  private int logLevel = 7;
  
  public void d(String paramString1, String paramString2) {}
  
  public void d(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public void e(String paramString1, String paramString2) {}
  
  public void e(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public int getLogLevel()
  {
    return logLevel;
  }
  
  public void i(String paramString1, String paramString2) {}
  
  public void i(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public boolean isLoggable(String paramString, int paramInt)
  {
    return false;
  }
  
  public void log(int paramInt, String paramString1, String paramString2) {}
  
  public void log(int paramInt, String paramString1, String paramString2, boolean paramBoolean) {}
  
  public void setLogLevel(int paramInt) {}
  
  public void v(String paramString1, String paramString2) {}
  
  public void v(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public void w(String paramString1, String paramString2) {}
  
  public void w(String paramString1, String paramString2, Throwable paramThrowable) {}
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.SilentLogger
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */