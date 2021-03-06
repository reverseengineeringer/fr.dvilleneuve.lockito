package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class DoubleType
  extends DoubleObjectType
{
  private static final DoubleType singleTon = new DoubleType();
  
  private DoubleType()
  {
    super(SqlType.DOUBLE, new Class[] { Double.TYPE });
  }
  
  protected DoubleType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }
  
  public static DoubleType getSingleton()
  {
    return singleTon;
  }
  
  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.field.types.DoubleType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */