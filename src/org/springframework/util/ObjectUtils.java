package org.springframework.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class ObjectUtils
{
  private static final String ARRAY_ELEMENT_SEPARATOR = ", ";
  private static final String ARRAY_END = "}";
  private static final String ARRAY_START = "{";
  private static final String EMPTY_ARRAY = "{}";
  private static final String EMPTY_STRING = "";
  private static final int INITIAL_HASH = 7;
  private static final int MULTIPLIER = 31;
  private static final String NULL_STRING = "null";
  
  public static <A, O extends A> A[] addObjectToArray(A[] paramArrayOfA, O paramO)
  {
    Object localObject = Object.class;
    if (paramArrayOfA != null)
    {
      localObject = paramArrayOfA.getClass().getComponentType();
      if (paramArrayOfA == null) {
        break label70;
      }
    }
    label70:
    for (int i = paramArrayOfA.length + 1;; i = 1)
    {
      localObject = (Object[])Array.newInstance((Class)localObject, i);
      if (paramArrayOfA != null) {
        System.arraycopy(paramArrayOfA, 0, localObject, 0, paramArrayOfA.length);
      }
      localObject[(localObject.length - 1)] = paramO;
      return (A[])localObject;
      if (paramO == null) {
        break;
      }
      localObject = paramO.getClass();
      break;
    }
  }
  
  public static <E extends Enum<?>> E caseInsensitiveValueOf(E[] paramArrayOfE, String paramString)
  {
    int j = paramArrayOfE.length;
    int i = 0;
    while (i < j)
    {
      E ? = paramArrayOfE[i];
      if (?.toString().equalsIgnoreCase(paramString)) {
        return ?;
      }
      i += 1;
    }
    throw new IllegalArgumentException(String.format("constant [%s] does not exist in enum type %s", new Object[] { paramString, paramArrayOfE.getClass().getComponentType().getName() }));
  }
  
  public static boolean containsConstant(Enum<?>[] paramArrayOfEnum, String paramString)
  {
    return containsConstant(paramArrayOfEnum, paramString, false);
  }
  
  public static boolean containsConstant(Enum<?>[] paramArrayOfEnum, String paramString, boolean paramBoolean)
  {
    int j = paramArrayOfEnum.length;
    int i = 0;
    while (i < j)
    {
      Enum<?> localEnum = paramArrayOfEnum[i];
      if (paramBoolean)
      {
        if (!localEnum.toString().equals(paramString)) {}
      }
      else {
        while (localEnum.toString().equalsIgnoreCase(paramString)) {
          return true;
        }
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean containsElement(Object[] paramArrayOfObject, Object paramObject)
  {
    if (paramArrayOfObject == null) {}
    for (;;)
    {
      return false;
      int j = paramArrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        if (nullSafeEquals(paramArrayOfObject[i], paramObject)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static String getDisplayString(Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    return nullSafeToString(paramObject);
  }
  
  public static String getIdentityHexString(Object paramObject)
  {
    return Integer.toHexString(System.identityHashCode(paramObject));
  }
  
  public static int hashCode(double paramDouble)
  {
    return hashCode(Double.doubleToLongBits(paramDouble));
  }
  
  public static int hashCode(float paramFloat)
  {
    return Float.floatToIntBits(paramFloat);
  }
  
  public static int hashCode(long paramLong)
  {
    return (int)(paramLong >>> 32 ^ paramLong);
  }
  
  public static int hashCode(boolean paramBoolean)
  {
    if (paramBoolean) {
      return 1231;
    }
    return 1237;
  }
  
  public static String identityToString(Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    return paramObject.getClass().getName() + "@" + getIdentityHexString(paramObject);
  }
  
  public static boolean isArray(Object paramObject)
  {
    return (paramObject != null) && (paramObject.getClass().isArray());
  }
  
  public static boolean isCheckedException(Throwable paramThrowable)
  {
    return (!(paramThrowable instanceof RuntimeException)) && (!(paramThrowable instanceof Error));
  }
  
  public static boolean isCompatibleWithThrowsClause(Throwable paramThrowable, Class<?>[] paramArrayOfClass)
  {
    if (!isCheckedException(paramThrowable)) {
      return true;
    }
    if (paramArrayOfClass != null)
    {
      int i = 0;
      for (;;)
      {
        if (i >= paramArrayOfClass.length) {
          break label41;
        }
        if (paramArrayOfClass[i].isAssignableFrom(paramThrowable.getClass())) {
          break;
        }
        i += 1;
      }
    }
    label41:
    return false;
  }
  
  public static boolean isEmpty(Object[] paramArrayOfObject)
  {
    return (paramArrayOfObject == null) || (paramArrayOfObject.length == 0);
  }
  
  public static String nullSafeClassName(Object paramObject)
  {
    if (paramObject != null) {
      return paramObject.getClass().getName();
    }
    return "null";
  }
  
  public static boolean nullSafeEquals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {}
    do
    {
      return true;
      if ((paramObject1 == null) || (paramObject2 == null)) {
        return false;
      }
    } while (paramObject1.equals(paramObject2));
    if ((paramObject1.getClass().isArray()) && (paramObject2.getClass().isArray()))
    {
      if (((paramObject1 instanceof Object[])) && ((paramObject2 instanceof Object[]))) {
        return Arrays.equals((Object[])paramObject1, (Object[])paramObject2);
      }
      if (((paramObject1 instanceof boolean[])) && ((paramObject2 instanceof boolean[]))) {
        return Arrays.equals((boolean[])paramObject1, (boolean[])paramObject2);
      }
      if (((paramObject1 instanceof byte[])) && ((paramObject2 instanceof byte[]))) {
        return Arrays.equals((byte[])paramObject1, (byte[])paramObject2);
      }
      if (((paramObject1 instanceof char[])) && ((paramObject2 instanceof char[]))) {
        return Arrays.equals((char[])paramObject1, (char[])paramObject2);
      }
      if (((paramObject1 instanceof double[])) && ((paramObject2 instanceof double[]))) {
        return Arrays.equals((double[])paramObject1, (double[])paramObject2);
      }
      if (((paramObject1 instanceof float[])) && ((paramObject2 instanceof float[]))) {
        return Arrays.equals((float[])paramObject1, (float[])paramObject2);
      }
      if (((paramObject1 instanceof int[])) && ((paramObject2 instanceof int[]))) {
        return Arrays.equals((int[])paramObject1, (int[])paramObject2);
      }
      if (((paramObject1 instanceof long[])) && ((paramObject2 instanceof long[]))) {
        return Arrays.equals((long[])paramObject1, (long[])paramObject2);
      }
      if (((paramObject1 instanceof short[])) && ((paramObject2 instanceof short[]))) {
        return Arrays.equals((short[])paramObject1, (short[])paramObject2);
      }
    }
    return false;
  }
  
  public static int nullSafeHashCode(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    if (paramObject.getClass().isArray())
    {
      if ((paramObject instanceof Object[])) {
        return nullSafeHashCode((Object[])paramObject);
      }
      if ((paramObject instanceof boolean[])) {
        return nullSafeHashCode((boolean[])paramObject);
      }
      if ((paramObject instanceof byte[])) {
        return nullSafeHashCode((byte[])paramObject);
      }
      if ((paramObject instanceof char[])) {
        return nullSafeHashCode((char[])paramObject);
      }
      if ((paramObject instanceof double[])) {
        return nullSafeHashCode((double[])paramObject);
      }
      if ((paramObject instanceof float[])) {
        return nullSafeHashCode((float[])paramObject);
      }
      if ((paramObject instanceof int[])) {
        return nullSafeHashCode((int[])paramObject);
      }
      if ((paramObject instanceof long[])) {
        return nullSafeHashCode((long[])paramObject);
      }
      if ((paramObject instanceof short[])) {
        return nullSafeHashCode((short[])paramObject);
      }
    }
    return paramObject.hashCode();
  }
  
  public static int nullSafeHashCode(byte[] paramArrayOfByte)
  {
    int k;
    if (paramArrayOfByte == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfByte.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + paramArrayOfByte[j];
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(char[] paramArrayOfChar)
  {
    int k;
    if (paramArrayOfChar == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfChar.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + paramArrayOfChar[j];
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(double[] paramArrayOfDouble)
  {
    int k;
    if (paramArrayOfDouble == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfDouble.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + hashCode(paramArrayOfDouble[j]);
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(float[] paramArrayOfFloat)
  {
    int k;
    if (paramArrayOfFloat == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfFloat.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + hashCode(paramArrayOfFloat[j]);
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(int[] paramArrayOfInt)
  {
    int k;
    if (paramArrayOfInt == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfInt.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + paramArrayOfInt[j];
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(long[] paramArrayOfLong)
  {
    int k;
    if (paramArrayOfLong == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfLong.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + hashCode(paramArrayOfLong[j]);
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(Object[] paramArrayOfObject)
  {
    int k;
    if (paramArrayOfObject == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfObject.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + nullSafeHashCode(paramArrayOfObject[j]);
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(short[] paramArrayOfShort)
  {
    int k;
    if (paramArrayOfShort == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfShort.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + paramArrayOfShort[j];
      j += 1;
    }
  }
  
  public static int nullSafeHashCode(boolean[] paramArrayOfBoolean)
  {
    int k;
    if (paramArrayOfBoolean == null)
    {
      k = 0;
      return k;
    }
    int i = 7;
    int m = paramArrayOfBoolean.length;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      i = i * 31 + hashCode(paramArrayOfBoolean[j]);
      j += 1;
    }
  }
  
  public static String nullSafeToString(Object paramObject)
  {
    if (paramObject == null) {
      return "null";
    }
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if ((paramObject instanceof Object[])) {
      return nullSafeToString((Object[])paramObject);
    }
    if ((paramObject instanceof boolean[])) {
      return nullSafeToString((boolean[])paramObject);
    }
    if ((paramObject instanceof byte[])) {
      return nullSafeToString((byte[])paramObject);
    }
    if ((paramObject instanceof char[])) {
      return nullSafeToString((char[])paramObject);
    }
    if ((paramObject instanceof double[])) {
      return nullSafeToString((double[])paramObject);
    }
    if ((paramObject instanceof float[])) {
      return nullSafeToString((float[])paramObject);
    }
    if ((paramObject instanceof int[])) {
      return nullSafeToString((int[])paramObject);
    }
    if ((paramObject instanceof long[])) {
      return nullSafeToString((long[])paramObject);
    }
    if ((paramObject instanceof short[])) {
      return nullSafeToString((short[])paramObject);
    }
    paramObject = paramObject.toString();
    if (paramObject != null) {}
    for (;;)
    {
      return (String)paramObject;
      paramObject = "";
    }
  }
  
  public static String nullSafeToString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "null";
    }
    int j = paramArrayOfByte.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(paramArrayOfByte[i]);
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(char[] paramArrayOfChar)
  {
    if (paramArrayOfChar == null) {
      return "null";
    }
    int j = paramArrayOfChar.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append("'").append(paramArrayOfChar[i]).append("'");
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble == null) {
      return "null";
    }
    int j = paramArrayOfDouble.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(paramArrayOfDouble[i]);
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(float[] paramArrayOfFloat)
  {
    if (paramArrayOfFloat == null) {
      return "null";
    }
    int j = paramArrayOfFloat.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(paramArrayOfFloat[i]);
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null) {
      return "null";
    }
    int j = paramArrayOfInt.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(paramArrayOfInt[i]);
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(long[] paramArrayOfLong)
  {
    if (paramArrayOfLong == null) {
      return "null";
    }
    int j = paramArrayOfLong.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(paramArrayOfLong[i]);
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null) {
      return "null";
    }
    int j = paramArrayOfObject.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(String.valueOf(paramArrayOfObject[i]));
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(short[] paramArrayOfShort)
  {
    if (paramArrayOfShort == null) {
      return "null";
    }
    int j = paramArrayOfShort.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(paramArrayOfShort[i]);
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static String nullSafeToString(boolean[] paramArrayOfBoolean)
  {
    if (paramArrayOfBoolean == null) {
      return "null";
    }
    int j = paramArrayOfBoolean.length;
    if (j == 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < j)
    {
      if (i == 0) {
        localStringBuilder.append("{");
      }
      for (;;)
      {
        localStringBuilder.append(paramArrayOfBoolean[i]);
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static Object[] toObjectArray(Object paramObject)
  {
    if ((paramObject instanceof Object[])) {
      return (Object[])paramObject;
    }
    if (paramObject == null) {
      return new Object[0];
    }
    if (!paramObject.getClass().isArray()) {
      throw new IllegalArgumentException("Source is not an array: " + paramObject);
    }
    int j = Array.getLength(paramObject);
    if (j == 0) {
      return new Object[0];
    }
    Object[] arrayOfObject = (Object[])Array.newInstance(Array.get(paramObject, 0).getClass(), j);
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = Array.get(paramObject, i);
      i += 1;
    }
    return arrayOfObject;
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ObjectUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */