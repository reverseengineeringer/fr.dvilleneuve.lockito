package io.fabric.sdk.android.services.concurrency;

public abstract interface DelegateProvider
{
  public abstract <T extends Dependency<Task>,  extends PriorityProvider,  extends Task> T getDelegate();
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.concurrency.DelegateProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */