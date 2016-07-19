package org.androidannotations.api;

public abstract interface BackgroundExecutor$WrongThreadListener
{
  public abstract void onBgExpected(String... paramVarArgs);
  
  public abstract void onUiExpected();
  
  public abstract void onWrongBgSerial(String paramString, String... paramVarArgs);
}

/* Location:
 * Qualified Name:     org.androidannotations.api.BackgroundExecutor.WrongThreadListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */