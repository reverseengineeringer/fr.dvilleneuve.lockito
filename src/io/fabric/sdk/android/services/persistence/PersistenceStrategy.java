package io.fabric.sdk.android.services.persistence;

public abstract interface PersistenceStrategy<T>
{
  public abstract void clear();
  
  public abstract T restore();
  
  public abstract void save(T paramT);
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.persistence.PersistenceStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */