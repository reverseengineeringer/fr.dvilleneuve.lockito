package org.joda.time.base;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadablePartial;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PartialConverter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class BasePartial
  extends AbstractPartial
  implements ReadablePartial, Serializable
{
  private static final long serialVersionUID = 2353678632973660L;
  private final Chronology iChronology;
  private final int[] iValues;
  
  protected BasePartial()
  {
    this(DateTimeUtils.currentTimeMillis(), null);
  }
  
  protected BasePartial(long paramLong)
  {
    this(paramLong, null);
  }
  
  protected BasePartial(long paramLong, Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology);
    iChronology = paramChronology.withUTC();
    iValues = paramChronology.get(this, paramLong);
  }
  
  protected BasePartial(Object paramObject, Chronology paramChronology)
  {
    PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
    paramChronology = DateTimeUtils.getChronology(localPartialConverter.getChronology(paramObject, paramChronology));
    iChronology = paramChronology.withUTC();
    iValues = localPartialConverter.getPartialValues(this, paramObject, paramChronology);
  }
  
  protected BasePartial(Object paramObject, Chronology paramChronology, DateTimeFormatter paramDateTimeFormatter)
  {
    PartialConverter localPartialConverter = ConverterManager.getInstance().getPartialConverter(paramObject);
    paramChronology = DateTimeUtils.getChronology(localPartialConverter.getChronology(paramObject, paramChronology));
    iChronology = paramChronology.withUTC();
    iValues = localPartialConverter.getPartialValues(this, paramObject, paramChronology, paramDateTimeFormatter);
  }
  
  protected BasePartial(Chronology paramChronology)
  {
    this(DateTimeUtils.currentTimeMillis(), paramChronology);
  }
  
  protected BasePartial(BasePartial paramBasePartial, Chronology paramChronology)
  {
    iChronology = paramChronology.withUTC();
    iValues = iValues;
  }
  
  protected BasePartial(BasePartial paramBasePartial, int[] paramArrayOfInt)
  {
    iChronology = iChronology;
    iValues = paramArrayOfInt;
  }
  
  protected BasePartial(int[] paramArrayOfInt, Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology);
    iChronology = paramChronology.withUTC();
    paramChronology.validate(this, paramArrayOfInt);
    iValues = paramArrayOfInt;
  }
  
  public Chronology getChronology()
  {
    return iChronology;
  }
  
  public int getValue(int paramInt)
  {
    return iValues[paramInt];
  }
  
  public int[] getValues()
  {
    return (int[])iValues.clone();
  }
  
  protected void setValue(int paramInt1, int paramInt2)
  {
    System.arraycopy(getField(paramInt1).set(this, paramInt1, iValues, paramInt2), 0, iValues, 0, iValues.length);
  }
  
  protected void setValues(int[] paramArrayOfInt)
  {
    getChronology().validate(this, paramArrayOfInt);
    System.arraycopy(paramArrayOfInt, 0, iValues, 0, iValues.length);
  }
  
  public String toString(String paramString)
  {
    if (paramString == null) {
      return toString();
    }
    return DateTimeFormat.forPattern(paramString).print(this);
  }
  
  public String toString(String paramString, Locale paramLocale)
    throws IllegalArgumentException
  {
    if (paramString == null) {
      return toString();
    }
    return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.BasePartial
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */