package org.androidannotations.api;

import java.util.Arrays;

final class BackgroundExecutor$1
  implements BackgroundExecutor.WrongThreadListener
{
  public void onBgExpected(String... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      throw new IllegalStateException("Method invocation is expected from a background thread, but it was called from the UI thread");
    }
    throw new IllegalStateException("Method invocation is expected from one of serials " + Arrays.toString(paramVarArgs) + ", but it was called from the UI thread");
  }
  
  public void onUiExpected()
  {
    throw new IllegalStateException("Method invocation is expected from the UI thread");
  }
  
  public void onWrongBgSerial(String paramString, String... paramVarArgs)
  {
    String str = paramString;
    if (paramString == null) {
      str = "anonymous";
    }
    throw new IllegalStateException("Method invocation is expected from one of serials " + Arrays.toString(paramVarArgs) + ", but it was called from " + str + " serial");
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.BackgroundExecutor.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */