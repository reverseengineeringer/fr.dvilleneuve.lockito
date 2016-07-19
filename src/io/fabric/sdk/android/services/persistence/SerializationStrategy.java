package io.fabric.sdk.android.services.persistence;

public abstract interface SerializationStrategy<T>
{
  public abstract T deserialize(String paramString);
  
  public abstract String serialize(T paramT);
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.persistence.SerializationStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */