package org.joda.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.joda.convert.factory.BooleanArrayStringConverterFactory;
import org.joda.convert.factory.BooleanObjectArrayStringConverterFactory;
import org.joda.convert.factory.ByteObjectArrayStringConverterFactory;
import org.joda.convert.factory.CharObjectArrayStringConverterFactory;
import org.joda.convert.factory.NumericArrayStringConverterFactory;
import org.joda.convert.factory.NumericObjectArrayStringConverterFactory;

public final class StringConvert
{
  private static final StringConverter<?> CACHED_NULL = new StringConverter()
  {
    public Object convertFromString(Class<? extends Object> paramAnonymousClass, String paramAnonymousString)
    {
      return null;
    }
    
    public String convertToString(Object paramAnonymousObject)
    {
      return null;
    }
  };
  public static final StringConvert INSTANCE = new StringConvert();
  private final CopyOnWriteArrayList<StringConverterFactory> factories = new CopyOnWriteArrayList();
  private final ConcurrentMap<Class<?>, StringConverter<?>> registered = new ConcurrentHashMap();
  
  public StringConvert()
  {
    this(true, new StringConverterFactory[0]);
  }
  
  public StringConvert(boolean paramBoolean, StringConverterFactory... paramVarArgs)
  {
    if (paramVarArgs == null) {
      throw new IllegalArgumentException("StringConverterFactory array must not be null");
    }
    int i = 0;
    while (i < paramVarArgs.length)
    {
      if (paramVarArgs[i] == null) {
        throw new IllegalArgumentException("StringConverterFactory array must not contain a null element");
      }
      i += 1;
    }
    if (paramBoolean)
    {
      JDKStringConverter[] arrayOfJDKStringConverter = JDKStringConverter.values();
      int j = arrayOfJDKStringConverter.length;
      i = 0;
      while (i < j)
      {
        JDKStringConverter localJDKStringConverter = arrayOfJDKStringConverter[i];
        registered.put(localJDKStringConverter.getType(), localJDKStringConverter);
        i += 1;
      }
      registered.put(Boolean.TYPE, JDKStringConverter.BOOLEAN);
      registered.put(Byte.TYPE, JDKStringConverter.BYTE);
      registered.put(Short.TYPE, JDKStringConverter.SHORT);
      registered.put(Integer.TYPE, JDKStringConverter.INTEGER);
      registered.put(Long.TYPE, JDKStringConverter.LONG);
      registered.put(Float.TYPE, JDKStringConverter.FLOAT);
      registered.put(Double.TYPE, JDKStringConverter.DOUBLE);
      registered.put(Character.TYPE, JDKStringConverter.CHARACTER);
      tryRegister("java.time.Instant", "parse");
      tryRegister("java.time.Duration", "parse");
      tryRegister("java.time.LocalDate", "parse");
      tryRegister("java.time.LocalTime", "parse");
      tryRegister("java.time.LocalDateTime", "parse");
      tryRegister("java.time.OffsetTime", "parse");
      tryRegister("java.time.OffsetDateTime", "parse");
      tryRegister("java.time.ZonedDateTime", "parse");
      tryRegister("java.time.Year", "parse");
      tryRegister("java.time.YearMonth", "parse");
      tryRegister("java.time.MonthDay", "parse");
      tryRegister("java.time.Period", "parse");
      tryRegister("java.time.ZoneOffset", "of");
      tryRegister("java.time.ZoneId", "of");
      tryRegister("org.threeten.bp.Instant", "parse");
      tryRegister("org.threeten.bp.Duration", "parse");
      tryRegister("org.threeten.bp.LocalDate", "parse");
      tryRegister("org.threeten.bp.LocalTime", "parse");
      tryRegister("org.threeten.bp.LocalDateTime", "parse");
      tryRegister("org.threeten.bp.OffsetTime", "parse");
      tryRegister("org.threeten.bp.OffsetDateTime", "parse");
      tryRegister("org.threeten.bp.ZonedDateTime", "parse");
      tryRegister("org.threeten.bp.Year", "parse");
      tryRegister("org.threeten.bp.YearMonth", "parse");
      tryRegister("org.threeten.bp.MonthDay", "parse");
      tryRegister("org.threeten.bp.Period", "parse");
      tryRegister("org.threeten.bp.ZoneOffset", "of");
      tryRegister("org.threeten.bp.ZoneId", "of");
      tryRegister("javax.time.Instant", "parse");
      tryRegister("javax.time.Duration", "parse");
      tryRegister("javax.time.calendar.LocalDate", "parse");
      tryRegister("javax.time.calendar.LocalTime", "parse");
      tryRegister("javax.time.calendar.LocalDateTime", "parse");
      tryRegister("javax.time.calendar.OffsetDate", "parse");
      tryRegister("javax.time.calendar.OffsetTime", "parse");
      tryRegister("javax.time.calendar.OffsetDateTime", "parse");
      tryRegister("javax.time.calendar.ZonedDateTime", "parse");
      tryRegister("javax.time.calendar.Year", "parse");
      tryRegister("javax.time.calendar.YearMonth", "parse");
      tryRegister("javax.time.calendar.MonthDay", "parse");
      tryRegister("javax.time.calendar.Period", "parse");
      tryRegister("javax.time.calendar.ZoneOffset", "of");
      tryRegister("javax.time.calendar.ZoneId", "of");
      tryRegister("javax.time.calendar.TimeZone", "of");
    }
    if (paramVarArgs.length > 0) {
      factories.addAll(Arrays.asList(paramVarArgs));
    }
    factories.add(AnnotationStringConverterFactory.INSTANCE);
  }
  
  public static StringConvert create()
  {
    return new StringConvert(true, new StringConverterFactory[] { NumericArrayStringConverterFactory.INSTANCE, NumericObjectArrayStringConverterFactory.INSTANCE, CharObjectArrayStringConverterFactory.INSTANCE, ByteObjectArrayStringConverterFactory.INSTANCE, BooleanArrayStringConverterFactory.INSTANCE, BooleanObjectArrayStringConverterFactory.INSTANCE });
  }
  
  private <T> StringConverter<T> findAnyConverter(Class<T> paramClass)
  {
    StringConverter localStringConverter = null;
    for (Object localObject = paramClass.getSuperclass(); (localObject != null) && (localStringConverter == null); localObject = ((Class)localObject).getSuperclass())
    {
      localStringConverter = (StringConverter)registered.get(localObject);
      if ((localStringConverter != null) && (localStringConverter != CACHED_NULL)) {
        return localStringConverter;
      }
    }
    localObject = paramClass.getInterfaces();
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localStringConverter = localObject[i];
      localStringConverter = (StringConverter)registered.get(localStringConverter);
      if ((localStringConverter != null) && (localStringConverter != CACHED_NULL)) {
        return localStringConverter;
      }
      i += 1;
    }
    localObject = factories.iterator();
    while (((Iterator)localObject).hasNext())
    {
      localStringConverter = ((StringConverterFactory)((Iterator)localObject).next()).findConverter(paramClass);
      if (localStringConverter != null) {
        return localStringConverter;
      }
    }
    return null;
  }
  
  private <T> StringConverter<T> findConverterQuiet(Class<T> paramClass)
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("Class must not be null");
    }
    StringConverter localStringConverter2 = (StringConverter)registered.get(paramClass);
    if (localStringConverter2 == CACHED_NULL) {
      return null;
    }
    StringConverter localStringConverter1 = localStringConverter2;
    if (localStringConverter2 == null)
    {
      try
      {
        localStringConverter1 = findAnyConverter(paramClass);
        if (localStringConverter1 == null)
        {
          registered.putIfAbsent(paramClass, CACHED_NULL);
          return null;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        registered.putIfAbsent(paramClass, CACHED_NULL);
        throw localRuntimeException;
      }
      registered.putIfAbsent(paramClass, localRuntimeException);
    }
    return localRuntimeException;
  }
  
  private <T> Constructor<T> findFromStringConstructorByType(Class<T> paramClass)
  {
    try
    {
      Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[] { String.class });
      return localConstructor;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        paramClass = paramClass.getDeclaredConstructor(new Class[] { CharSequence.class });
        return paramClass;
      }
      catch (NoSuchMethodException paramClass)
      {
        throw new IllegalArgumentException("Constructor not found", paramClass);
      }
    }
  }
  
  private Method findFromStringMethod(Class<?> paramClass, String paramString)
  {
    try
    {
      Method localMethod = paramClass.getMethod(paramString, new Class[] { String.class });
      paramClass = localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        paramClass = paramClass.getMethod(paramString, new Class[] { CharSequence.class });
      }
      catch (NoSuchMethodException paramClass)
      {
        throw new IllegalArgumentException("Method not found", paramClass);
      }
    }
    if (!Modifier.isStatic(paramClass.getModifiers())) {
      throw new IllegalArgumentException("Method must be static: " + paramString);
    }
    return paramClass;
  }
  
  private Method findToStringMethod(Class<?> paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, new Class[0]);
      if (Modifier.isStatic(paramClass.getModifiers())) {
        throw new IllegalArgumentException("Method must not be static: " + paramString);
      }
    }
    catch (NoSuchMethodException paramClass)
    {
      throw new IllegalArgumentException(paramClass);
    }
    return paramClass;
  }
  
  private void tryRegister(String paramString1, String paramString2)
  {
    try
    {
      registerMethods(getClass().getClassLoader().loadClass(paramString1), "toString", paramString2);
      return;
    }
    catch (Exception paramString1) {}
  }
  
  public <T> T convertFromString(Class<T> paramClass, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return (T)findConverter(paramClass).convertFromString(paramClass, paramString);
  }
  
  public String convertToString(Class<?> paramClass, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return findConverterNoGenerics(paramClass).convertToString(paramObject);
  }
  
  public String convertToString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return findConverterNoGenerics(paramObject.getClass()).convertToString(paramObject);
  }
  
  public <T> StringConverter<T> findConverter(Class<T> paramClass)
  {
    StringConverter localStringConverter = findConverterQuiet(paramClass);
    if (localStringConverter == null) {
      throw new IllegalStateException("No registered converter found: " + paramClass);
    }
    return localStringConverter;
  }
  
  public StringConverter<Object> findConverterNoGenerics(Class<?> paramClass)
  {
    StringConverter localStringConverter = findConverterQuiet(paramClass);
    if (localStringConverter == null) {
      throw new IllegalStateException("No registered converter found: " + paramClass);
    }
    return localStringConverter;
  }
  
  public boolean isConvertible(Class<?> paramClass)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramClass != null) {}
    try
    {
      paramClass = findConverterQuiet(paramClass);
      bool1 = bool2;
      if (paramClass != null) {
        bool1 = true;
      }
      return bool1;
    }
    catch (RuntimeException paramClass) {}
    return false;
  }
  
  public <T> void register(Class<T> paramClass, StringConverter<T> paramStringConverter)
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("Class must not be null");
    }
    if (paramStringConverter == null) {
      throw new IllegalArgumentException("StringConverter must not be null");
    }
    if (this == INSTANCE) {
      throw new IllegalStateException("Global singleton cannot be extended");
    }
    registered.put(paramClass, paramStringConverter);
  }
  
  public <T> void register(Class<T> paramClass, final ToStringConverter<T> paramToStringConverter, final FromStringConverter<T> paramFromStringConverter)
  {
    if ((paramFromStringConverter == null) || (paramToStringConverter == null)) {
      throw new IllegalArgumentException("Converters must not be null");
    }
    register(paramClass, new StringConverter()
    {
      public T convertFromString(Class<? extends T> paramAnonymousClass, String paramAnonymousString)
      {
        return (T)paramFromStringConverter.convertFromString(paramAnonymousClass, paramAnonymousString);
      }
      
      public String convertToString(T paramAnonymousT)
      {
        return paramToStringConverter.convertToString(paramAnonymousT);
      }
    });
  }
  
  public void registerFactory(StringConverterFactory paramStringConverterFactory)
  {
    if (paramStringConverterFactory == null) {
      throw new IllegalArgumentException("Factory must not be null");
    }
    if (this == INSTANCE) {
      throw new IllegalStateException("Global singleton cannot be extended");
    }
    factories.add(0, paramStringConverterFactory);
  }
  
  public <T> void registerMethodConstructor(Class<T> paramClass, String paramString)
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("Class must not be null");
    }
    if (paramString == null) {
      throw new IllegalArgumentException("Method name must not be null");
    }
    if (this == INSTANCE) {
      throw new IllegalStateException("Global singleton cannot be extended");
    }
    paramString = new MethodConstructorStringConverter(paramClass, findToStringMethod(paramClass, paramString), findFromStringConstructorByType(paramClass));
    registered.putIfAbsent(paramClass, paramString);
  }
  
  public <T> void registerMethods(Class<T> paramClass, String paramString1, String paramString2)
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("Class must not be null");
    }
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException("Method names must not be null");
    }
    if (this == INSTANCE) {
      throw new IllegalStateException("Global singleton cannot be extended");
    }
    paramString1 = new MethodsStringConverter(paramClass, findToStringMethod(paramClass, paramString1), findFromStringMethod(paramClass, paramString2));
    registered.putIfAbsent(paramClass, paramString1);
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.StringConvert
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */