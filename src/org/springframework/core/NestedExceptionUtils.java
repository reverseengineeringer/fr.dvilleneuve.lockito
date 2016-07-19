package org.springframework.core;

public abstract class NestedExceptionUtils
{
  public static String buildMessage(String paramString, Throwable paramThrowable)
  {
    Object localObject = paramString;
    if (paramThrowable != null)
    {
      localObject = new StringBuilder();
      if (paramString != null) {
        ((StringBuilder)localObject).append(paramString).append("; ");
      }
      ((StringBuilder)localObject).append("nested exception is ").append(paramThrowable);
      localObject = ((StringBuilder)localObject).toString();
    }
    return (String)localObject;
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.NestedExceptionUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */