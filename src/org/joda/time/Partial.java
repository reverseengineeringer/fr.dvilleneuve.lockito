package org.joda.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.joda.time.base.AbstractPartial;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class Partial
  extends AbstractPartial
  implements ReadablePartial, Serializable
{
  private static final long serialVersionUID = 12324121189002L;
  private final Chronology iChronology;
  private transient DateTimeFormatter[] iFormatter;
  private final DateTimeFieldType[] iTypes;
  private final int[] iValues;
  
  public Partial()
  {
    this((Chronology)null);
  }
  
  public Partial(Chronology paramChronology)
  {
    iChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    iTypes = new DateTimeFieldType[0];
    iValues = new int[0];
  }
  
  Partial(Chronology paramChronology, DateTimeFieldType[] paramArrayOfDateTimeFieldType, int[] paramArrayOfInt)
  {
    iChronology = paramChronology;
    iTypes = paramArrayOfDateTimeFieldType;
    iValues = paramArrayOfInt;
  }
  
  public Partial(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    this(paramDateTimeFieldType, paramInt, null);
  }
  
  public Partial(DateTimeFieldType paramDateTimeFieldType, int paramInt, Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    iChronology = paramChronology;
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("The field type must not be null");
    }
    iTypes = new DateTimeFieldType[] { paramDateTimeFieldType };
    iValues = new int[] { paramInt };
    paramChronology.validate(this, iValues);
  }
  
  Partial(Partial paramPartial, int[] paramArrayOfInt)
  {
    iChronology = iChronology;
    iTypes = iTypes;
    iValues = paramArrayOfInt;
  }
  
  public Partial(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("The partial must not be null");
    }
    iChronology = DateTimeUtils.getChronology(paramReadablePartial.getChronology()).withUTC();
    iTypes = new DateTimeFieldType[paramReadablePartial.size()];
    iValues = new int[paramReadablePartial.size()];
    int i = 0;
    while (i < paramReadablePartial.size())
    {
      iTypes[i] = paramReadablePartial.getFieldType(i);
      iValues[i] = paramReadablePartial.getValue(i);
      i += 1;
    }
  }
  
  public Partial(DateTimeFieldType[] paramArrayOfDateTimeFieldType, int[] paramArrayOfInt)
  {
    this(paramArrayOfDateTimeFieldType, paramArrayOfInt, null);
  }
  
  public Partial(DateTimeFieldType[] paramArrayOfDateTimeFieldType, int[] paramArrayOfInt, Chronology paramChronology)
  {
    Chronology localChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    iChronology = localChronology;
    if (paramArrayOfDateTimeFieldType == null) {
      throw new IllegalArgumentException("Types array must not be null");
    }
    if (paramArrayOfInt == null) {
      throw new IllegalArgumentException("Values array must not be null");
    }
    if (paramArrayOfInt.length != paramArrayOfDateTimeFieldType.length) {
      throw new IllegalArgumentException("Values array must be the same length as the types array");
    }
    if (paramArrayOfDateTimeFieldType.length == 0)
    {
      iTypes = paramArrayOfDateTimeFieldType;
      iValues = paramArrayOfInt;
      return;
    }
    int i = 0;
    while (i < paramArrayOfDateTimeFieldType.length)
    {
      if (paramArrayOfDateTimeFieldType[i] == null) {
        throw new IllegalArgumentException("Types array must not contain null: index " + i);
      }
      i += 1;
    }
    paramChronology = null;
    i = 0;
    while (i < paramArrayOfDateTimeFieldType.length)
    {
      DateTimeFieldType localDateTimeFieldType = paramArrayOfDateTimeFieldType[i];
      DurationField localDurationField1 = localDateTimeFieldType.getDurationType().getField(iChronology);
      if (i > 0)
      {
        if (!localDurationField1.isSupported())
        {
          if (paramChronology.isSupported()) {
            throw new IllegalArgumentException("Types array must be in order largest-smallest: " + paramArrayOfDateTimeFieldType[(i - 1)].getName() + " < " + localDateTimeFieldType.getName());
          }
          throw new IllegalArgumentException("Types array must not contain duplicate unsupported: " + paramArrayOfDateTimeFieldType[(i - 1)].getName() + " and " + localDateTimeFieldType.getName());
        }
        int j = paramChronology.compareTo(localDurationField1);
        if (j < 0) {
          throw new IllegalArgumentException("Types array must be in order largest-smallest: " + paramArrayOfDateTimeFieldType[(i - 1)].getName() + " < " + localDateTimeFieldType.getName());
        }
        if ((j == 0) && (paramChronology.equals(localDurationField1))) {
          if (paramArrayOfDateTimeFieldType[(i - 1)].getRangeDurationType() == null)
          {
            if (localDateTimeFieldType.getRangeDurationType() == null) {
              throw new IllegalArgumentException("Types array must not contain duplicate: " + paramArrayOfDateTimeFieldType[(i - 1)].getName() + " and " + localDateTimeFieldType.getName());
            }
          }
          else
          {
            if (localDateTimeFieldType.getRangeDurationType() == null) {
              throw new IllegalArgumentException("Types array must be in order largest-smallest: " + paramArrayOfDateTimeFieldType[(i - 1)].getName() + " < " + localDateTimeFieldType.getName());
            }
            paramChronology = paramArrayOfDateTimeFieldType[(i - 1)].getRangeDurationType().getField(iChronology);
            DurationField localDurationField2 = localDateTimeFieldType.getRangeDurationType().getField(iChronology);
            if (paramChronology.compareTo(localDurationField2) < 0) {
              throw new IllegalArgumentException("Types array must be in order largest-smallest: " + paramArrayOfDateTimeFieldType[(i - 1)].getName() + " < " + localDateTimeFieldType.getName());
            }
            if (paramChronology.compareTo(localDurationField2) == 0) {
              throw new IllegalArgumentException("Types array must not contain duplicate: " + paramArrayOfDateTimeFieldType[(i - 1)].getName() + " and " + localDateTimeFieldType.getName());
            }
          }
        }
      }
      paramChronology = localDurationField1;
      i += 1;
    }
    iTypes = ((DateTimeFieldType[])paramArrayOfDateTimeFieldType.clone());
    localChronology.validate(this, paramArrayOfInt);
    iValues = ((int[])paramArrayOfInt.clone());
  }
  
  public Chronology getChronology()
  {
    return iChronology;
  }
  
  protected DateTimeField getField(int paramInt, Chronology paramChronology)
  {
    return iTypes[paramInt].getField(paramChronology);
  }
  
  public DateTimeFieldType getFieldType(int paramInt)
  {
    return iTypes[paramInt];
  }
  
  public DateTimeFieldType[] getFieldTypes()
  {
    return (DateTimeFieldType[])iTypes.clone();
  }
  
  public DateTimeFormatter getFormatter()
  {
    Object localObject2 = iFormatter;
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      if (size() == 0) {
        return null;
      }
      localObject1 = new DateTimeFormatter[2];
    }
    try
    {
      localObject2 = new ArrayList(Arrays.asList(iTypes));
      localObject1[0] = ISODateTimeFormat.forFields((Collection)localObject2, true, false);
      if (((List)localObject2).size() == 0) {
        localObject1[1] = localObject1[0];
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    iFormatter = ((DateTimeFormatter[])localObject1);
    return localObject1[0];
  }
  
  public int getValue(int paramInt)
  {
    return iValues[paramInt];
  }
  
  public int[] getValues()
  {
    return (int[])iValues.clone();
  }
  
  public boolean isMatch(ReadableInstant paramReadableInstant)
  {
    long l = DateTimeUtils.getInstantMillis(paramReadableInstant);
    paramReadableInstant = DateTimeUtils.getInstantChronology(paramReadableInstant);
    int i = 0;
    while (i < iTypes.length)
    {
      if (iTypes[i].getField(paramReadableInstant).get(l) != iValues[i]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public boolean isMatch(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("The partial must not be null");
    }
    int i = 0;
    while (i < iTypes.length)
    {
      if (paramReadablePartial.get(iTypes[i]) != iValues[i]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public Partial minus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, -1);
  }
  
  public Partial plus(ReadablePeriod paramReadablePeriod)
  {
    return withPeriodAdded(paramReadablePeriod, 1);
  }
  
  public Property property(DateTimeFieldType paramDateTimeFieldType)
  {
    return new Property(this, indexOfSupported(paramDateTimeFieldType));
  }
  
  public int size()
  {
    return iTypes.length;
  }
  
  public String toString()
  {
    DateTimeFormatter[] arrayOfDateTimeFormatter = iFormatter;
    Object localObject = arrayOfDateTimeFormatter;
    if (arrayOfDateTimeFormatter == null)
    {
      getFormatter();
      arrayOfDateTimeFormatter = iFormatter;
      localObject = arrayOfDateTimeFormatter;
      if (arrayOfDateTimeFormatter == null) {
        return toStringList();
      }
    }
    localObject = localObject[1];
    if (localObject == null) {
      return toStringList();
    }
    return ((DateTimeFormatter)localObject).print(this);
  }
  
  public String toString(String paramString)
  {
    if (paramString == null) {
      return toString();
    }
    return DateTimeFormat.forPattern(paramString).print(this);
  }
  
  public String toString(String paramString, Locale paramLocale)
  {
    if (paramString == null) {
      return toString();
    }
    return DateTimeFormat.forPattern(paramString).withLocale(paramLocale).print(this);
  }
  
  public String toStringList()
  {
    int j = size();
    StringBuilder localStringBuilder = new StringBuilder(j * 20);
    localStringBuilder.append('[');
    int i = 0;
    while (i < j)
    {
      if (i > 0) {
        localStringBuilder.append(',').append(' ');
      }
      localStringBuilder.append(iTypes[i].getName());
      localStringBuilder.append('=');
      localStringBuilder.append(iValues[i]);
      i += 1;
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public Partial with(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("The field type must not be null");
    }
    int i = indexOf(paramDateTimeFieldType);
    if (i == -1)
    {
      DateTimeFieldType[] arrayOfDateTimeFieldType = new DateTimeFieldType[iTypes.length + 1];
      int[] arrayOfInt = new int[arrayOfDateTimeFieldType.length];
      int j = 0;
      i = 0;
      DurationField localDurationField1 = paramDateTimeFieldType.getDurationType().getField(iChronology);
      DateTimeFieldType localDateTimeFieldType;
      if (localDurationField1.isSupported())
      {
        j = i;
        if (i < iTypes.length)
        {
          localDateTimeFieldType = iTypes[i];
          DurationField localDurationField2 = localDateTimeFieldType.getDurationType().getField(iChronology);
          if (!localDurationField2.isSupported()) {
            break label264;
          }
          j = localDurationField1.compareTo(localDurationField2);
          if (j <= 0) {
            break label241;
          }
          j = i;
        }
      }
      for (;;)
      {
        System.arraycopy(iTypes, 0, arrayOfDateTimeFieldType, 0, j);
        System.arraycopy(iValues, 0, arrayOfInt, 0, j);
        arrayOfDateTimeFieldType[j] = paramDateTimeFieldType;
        arrayOfInt[j] = paramInt;
        System.arraycopy(iTypes, j, arrayOfDateTimeFieldType, j + 1, arrayOfDateTimeFieldType.length - j - 1);
        System.arraycopy(iValues, j, arrayOfInt, j + 1, arrayOfInt.length - j - 1);
        paramDateTimeFieldType = new Partial(arrayOfDateTimeFieldType, arrayOfInt, iChronology);
        iChronology.validate(paramDateTimeFieldType, arrayOfInt);
        return paramDateTimeFieldType;
        label241:
        if (j == 0)
        {
          j = i;
          if (paramDateTimeFieldType.getRangeDurationType() == null) {
            continue;
          }
          if (localDateTimeFieldType.getRangeDurationType() != null) {
            break label271;
          }
        }
        label264:
        label271:
        while (paramDateTimeFieldType.getRangeDurationType().getField(iChronology).compareTo(localDateTimeFieldType.getRangeDurationType().getField(iChronology)) <= 0)
        {
          i += 1;
          break;
        }
        j = i;
      }
    }
    if (paramInt == getValue(i)) {
      return this;
    }
    paramDateTimeFieldType = getValues();
    return new Partial(this, getField(i).set(this, i, paramDateTimeFieldType, paramInt));
  }
  
  public Partial withChronologyRetainFields(Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology).withUTC();
    if (paramChronology == getChronology()) {
      return this;
    }
    Partial localPartial = new Partial(paramChronology, iTypes, iValues);
    paramChronology.validate(localPartial, iValues);
    return localPartial;
  }
  
  public Partial withField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDateTimeFieldType);
    if (paramInt == getValue(i)) {
      return this;
    }
    paramDateTimeFieldType = getValues();
    return new Partial(this, getField(i).set(this, i, paramDateTimeFieldType, paramInt));
  }
  
  public Partial withFieldAddWrapped(DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDurationFieldType);
    if (paramInt == 0) {
      return this;
    }
    paramDurationFieldType = getValues();
    return new Partial(this, getField(i).addWrapPartial(this, i, paramDurationFieldType, paramInt));
  }
  
  public Partial withFieldAdded(DurationFieldType paramDurationFieldType, int paramInt)
  {
    int i = indexOfSupported(paramDurationFieldType);
    if (paramInt == 0) {
      return this;
    }
    paramDurationFieldType = getValues();
    return new Partial(this, getField(i).add(this, i, paramDurationFieldType, paramInt));
  }
  
  public Partial withPeriodAdded(ReadablePeriod paramReadablePeriod, int paramInt)
  {
    if ((paramReadablePeriod == null) || (paramInt == 0)) {
      return this;
    }
    Object localObject1 = getValues();
    int i = 0;
    while (i < paramReadablePeriod.size())
    {
      int j = indexOf(paramReadablePeriod.getFieldType(i));
      Object localObject2 = localObject1;
      if (j >= 0) {
        localObject2 = getField(j).add(this, j, (int[])localObject1, FieldUtils.safeMultiply(paramReadablePeriod.getValue(i), paramInt));
      }
      i += 1;
      localObject1 = localObject2;
    }
    return new Partial(this, (int[])localObject1);
  }
  
  public Partial without(DateTimeFieldType paramDateTimeFieldType)
  {
    int i = indexOf(paramDateTimeFieldType);
    if (i != -1)
    {
      Object localObject = new DateTimeFieldType[size() - 1];
      paramDateTimeFieldType = new int[size() - 1];
      System.arraycopy(iTypes, 0, localObject, 0, i);
      System.arraycopy(iTypes, i + 1, localObject, i, localObject.length - i);
      System.arraycopy(iValues, 0, paramDateTimeFieldType, 0, i);
      System.arraycopy(iValues, i + 1, paramDateTimeFieldType, i, paramDateTimeFieldType.length - i);
      localObject = new Partial(iChronology, (DateTimeFieldType[])localObject, paramDateTimeFieldType);
      iChronology.validate((ReadablePartial)localObject, paramDateTimeFieldType);
      return (Partial)localObject;
    }
    return this;
  }
  
  public static class Property
    extends AbstractPartialFieldProperty
    implements Serializable
  {
    private static final long serialVersionUID = 53278362873888L;
    private final int iFieldIndex;
    private final Partial iPartial;
    
    Property(Partial paramPartial, int paramInt)
    {
      iPartial = paramPartial;
      iFieldIndex = paramInt;
    }
    
    public Partial addToCopy(int paramInt)
    {
      int[] arrayOfInt = iPartial.getValues();
      arrayOfInt = getField().add(iPartial, iFieldIndex, arrayOfInt, paramInt);
      return new Partial(iPartial, arrayOfInt);
    }
    
    public Partial addWrapFieldToCopy(int paramInt)
    {
      int[] arrayOfInt = iPartial.getValues();
      arrayOfInt = getField().addWrapField(iPartial, iFieldIndex, arrayOfInt, paramInt);
      return new Partial(iPartial, arrayOfInt);
    }
    
    public int get()
    {
      return iPartial.getValue(iFieldIndex);
    }
    
    public DateTimeField getField()
    {
      return iPartial.getField(iFieldIndex);
    }
    
    public Partial getPartial()
    {
      return iPartial;
    }
    
    protected ReadablePartial getReadablePartial()
    {
      return iPartial;
    }
    
    public Partial setCopy(int paramInt)
    {
      int[] arrayOfInt = iPartial.getValues();
      arrayOfInt = getField().set(iPartial, iFieldIndex, arrayOfInt, paramInt);
      return new Partial(iPartial, arrayOfInt);
    }
    
    public Partial setCopy(String paramString)
    {
      return setCopy(paramString, null);
    }
    
    public Partial setCopy(String paramString, Locale paramLocale)
    {
      int[] arrayOfInt = iPartial.getValues();
      paramString = getField().set(iPartial, iFieldIndex, arrayOfInt, paramString, paramLocale);
      return new Partial(iPartial, paramString);
    }
    
    public Partial withMaximumValue()
    {
      return setCopy(getMaximumValue());
    }
    
    public Partial withMinimumValue()
    {
      return setCopy(getMinimumValue());
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Partial
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */