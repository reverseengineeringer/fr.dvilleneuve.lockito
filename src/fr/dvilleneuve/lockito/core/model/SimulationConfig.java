package fr.dvilleneuve.lockito.core.model;

public abstract interface SimulationConfig
{
  public abstract float getAccuracyBase();
  
  public abstract float getAccuracyDelta();
  
  public abstract float getAltitude();
  
  public abstract float getSpeed();
  
  public abstract void setAccuracyBase(float paramFloat);
  
  public abstract void setAccuracyDelta(float paramFloat);
  
  public abstract void setAltitude(float paramFloat);
  
  public abstract void setSpeed(float paramFloat);
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.SimulationConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */