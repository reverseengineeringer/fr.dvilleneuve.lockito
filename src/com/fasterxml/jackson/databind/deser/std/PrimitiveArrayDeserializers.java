package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.FloatBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.IntBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ShortBuilder;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T>
  extends StdDeserializer<T>
{
  protected PrimitiveArrayDeserializers(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  public static JsonDeserializer<?> forType(Class<?> paramClass)
  {
    if (paramClass == Integer.TYPE) {
      return IntDeser.instance;
    }
    if (paramClass == Long.TYPE) {
      return LongDeser.instance;
    }
    if (paramClass == Byte.TYPE) {
      return new ByteDeser();
    }
    if (paramClass == Short.TYPE) {
      return new ShortDeser();
    }
    if (paramClass == Float.TYPE) {
      return new FloatDeser();
    }
    if (paramClass == Double.TYPE) {
      return new DoubleDeser();
    }
    if (paramClass == Boolean.TYPE) {
      return new BooleanDeser();
    }
    if (paramClass == Character.TYPE) {
      return new CharDeser();
    }
    throw new IllegalStateException();
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  @JacksonStdImpl
  static final class BooleanDeser
    extends PrimitiveArrayDeserializers<boolean[]>
  {
    private static final long serialVersionUID = 1L;
    
    public BooleanDeser()
    {
      super();
    }
    
    private final boolean[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(_valueClass);
      }
      return new boolean[] { _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext) };
    }
    
    public boolean[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if (!paramJsonParser.isExpectedStartArrayToken()) {
        return handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.BooleanBuilder localBooleanBuilder = paramDeserializationContext.getArrayBuilders().getBooleanBuilder();
      boolean[] arrayOfBoolean = (boolean[])localBooleanBuilder.resetAndStart();
      int i = 0;
      boolean bool;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
      {
        bool = _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext);
        if (i < arrayOfBoolean.length) {
          break label104;
        }
        arrayOfBoolean = (boolean[])localBooleanBuilder.appendCompletedChunk(arrayOfBoolean, i);
        i = 0;
      }
      label104:
      for (;;)
      {
        int j = i + 1;
        arrayOfBoolean[i] = bool;
        i = j;
        break;
        return (boolean[])localBooleanBuilder.completeAndClearBuffer(arrayOfBoolean, i);
      }
    }
  }
  
  @JacksonStdImpl
  static final class ByteDeser
    extends PrimitiveArrayDeserializers<byte[]>
  {
    private static final long serialVersionUID = 1L;
    
    public ByteDeser()
    {
      super();
    }
    
    private final byte[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(_valueClass);
      }
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {}
      for (int i = paramJsonParser.getByteValue();; i = 0)
      {
        return new byte[] { i };
        if (localJsonToken != JsonToken.VALUE_NULL) {
          throw paramDeserializationContext.mappingException(_valueClass.getComponentType());
        }
      }
    }
    
    public byte[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      Object localObject = paramJsonParser.getCurrentToken();
      if (localObject == JsonToken.VALUE_STRING) {
        return paramJsonParser.getBinaryValue(paramDeserializationContext.getBase64Variant());
      }
      if (localObject == JsonToken.VALUE_EMBEDDED_OBJECT)
      {
        localObject = paramJsonParser.getEmbeddedObject();
        if (localObject == null) {
          return null;
        }
        if ((localObject instanceof byte[])) {
          return (byte[])localObject;
        }
      }
      if (!paramJsonParser.isExpectedStartArrayToken()) {
        return handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.ByteBuilder localByteBuilder = paramDeserializationContext.getArrayBuilders().getByteBuilder();
      localObject = (byte[])localByteBuilder.resetAndStart();
      int j = 0;
      JsonToken localJsonToken = paramJsonParser.nextToken();
      int i;
      if (localJsonToken != JsonToken.END_ARRAY) {
        if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT))
        {
          i = paramJsonParser.getByteValue();
          label132:
          if (j < localObject.length) {
            break label214;
          }
          localObject = (byte[])localByteBuilder.appendCompletedChunk(localObject, j);
          j = 0;
        }
      }
      label214:
      for (;;)
      {
        int k = j + 1;
        localObject[j] = i;
        j = k;
        break;
        if (localJsonToken != JsonToken.VALUE_NULL) {
          throw paramDeserializationContext.mappingException(_valueClass.getComponentType());
        }
        i = 0;
        break label132;
        return (byte[])localByteBuilder.completeAndClearBuffer(localObject, j);
      }
    }
  }
  
  @JacksonStdImpl
  static final class CharDeser
    extends PrimitiveArrayDeserializers<char[]>
  {
    private static final long serialVersionUID = 1L;
    
    public CharDeser()
    {
      super();
    }
    
    public char[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      Object localObject1 = paramJsonParser.getCurrentToken();
      if (localObject1 == JsonToken.VALUE_STRING)
      {
        paramDeserializationContext = paramJsonParser.getTextCharacters();
        int i = paramJsonParser.getTextOffset();
        int j = paramJsonParser.getTextLength();
        paramJsonParser = new char[j];
        System.arraycopy(paramDeserializationContext, i, paramJsonParser, 0, j);
        return paramJsonParser;
      }
      if (paramJsonParser.isExpectedStartArrayToken())
      {
        localObject1 = new StringBuilder(64);
        for (;;)
        {
          Object localObject2 = paramJsonParser.nextToken();
          if (localObject2 == JsonToken.END_ARRAY) {
            break;
          }
          if (localObject2 != JsonToken.VALUE_STRING) {
            throw paramDeserializationContext.mappingException(Character.TYPE);
          }
          localObject2 = paramJsonParser.getText();
          if (((String)localObject2).length() != 1) {
            throw JsonMappingException.from(paramJsonParser, "Can not convert a JSON String of length " + ((String)localObject2).length() + " into a char element of char array");
          }
          ((StringBuilder)localObject1).append(((String)localObject2).charAt(0));
        }
        return ((StringBuilder)localObject1).toString().toCharArray();
      }
      if (localObject1 == JsonToken.VALUE_EMBEDDED_OBJECT)
      {
        paramJsonParser = paramJsonParser.getEmbeddedObject();
        if (paramJsonParser == null) {
          return null;
        }
        if ((paramJsonParser instanceof char[])) {
          return (char[])paramJsonParser;
        }
        if ((paramJsonParser instanceof String)) {
          return ((String)paramJsonParser).toCharArray();
        }
        if ((paramJsonParser instanceof byte[])) {
          return Base64Variants.getDefaultVariant().encode((byte[])paramJsonParser, false).toCharArray();
        }
      }
      throw paramDeserializationContext.mappingException(_valueClass);
    }
  }
  
  @JacksonStdImpl
  static final class DoubleDeser
    extends PrimitiveArrayDeserializers<double[]>
  {
    private static final long serialVersionUID = 1L;
    
    public DoubleDeser()
    {
      super();
    }
    
    private final double[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(_valueClass);
      }
      return new double[] { _parseDoublePrimitive(paramJsonParser, paramDeserializationContext) };
    }
    
    public double[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if (!paramJsonParser.isExpectedStartArrayToken()) {
        return handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.DoubleBuilder localDoubleBuilder = paramDeserializationContext.getArrayBuilders().getDoubleBuilder();
      double[] arrayOfDouble = (double[])localDoubleBuilder.resetAndStart();
      int i = 0;
      double d;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
      {
        d = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
        if (i < arrayOfDouble.length) {
          break label110;
        }
        arrayOfDouble = (double[])localDoubleBuilder.appendCompletedChunk(arrayOfDouble, i);
        i = 0;
      }
      label110:
      for (;;)
      {
        int j = i + 1;
        arrayOfDouble[i] = d;
        i = j;
        break;
        return (double[])localDoubleBuilder.completeAndClearBuffer(arrayOfDouble, i);
      }
    }
  }
  
  @JacksonStdImpl
  static final class FloatDeser
    extends PrimitiveArrayDeserializers<float[]>
  {
    private static final long serialVersionUID = 1L;
    
    public FloatDeser()
    {
      super();
    }
    
    private final float[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(_valueClass);
      }
      return new float[] { _parseFloatPrimitive(paramJsonParser, paramDeserializationContext) };
    }
    
    public float[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if (!paramJsonParser.isExpectedStartArrayToken()) {
        return handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.FloatBuilder localFloatBuilder = paramDeserializationContext.getArrayBuilders().getFloatBuilder();
      float[] arrayOfFloat = (float[])localFloatBuilder.resetAndStart();
      int i = 0;
      float f;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
      {
        f = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
        if (i < arrayOfFloat.length) {
          break label110;
        }
        arrayOfFloat = (float[])localFloatBuilder.appendCompletedChunk(arrayOfFloat, i);
        i = 0;
      }
      label110:
      for (;;)
      {
        int j = i + 1;
        arrayOfFloat[i] = f;
        i = j;
        break;
        return (float[])localFloatBuilder.completeAndClearBuffer(arrayOfFloat, i);
      }
    }
  }
  
  @JacksonStdImpl
  static final class IntDeser
    extends PrimitiveArrayDeserializers<int[]>
  {
    public static final IntDeser instance = new IntDeser();
    private static final long serialVersionUID = 1L;
    
    public IntDeser()
    {
      super();
    }
    
    private final int[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(_valueClass);
      }
      return new int[] { _parseIntPrimitive(paramJsonParser, paramDeserializationContext) };
    }
    
    public int[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if (!paramJsonParser.isExpectedStartArrayToken()) {
        return handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.IntBuilder localIntBuilder = paramDeserializationContext.getArrayBuilders().getIntBuilder();
      int[] arrayOfInt = (int[])localIntBuilder.resetAndStart();
      int i = 0;
      int k;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
      {
        k = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
        if (i < arrayOfInt.length) {
          break label104;
        }
        arrayOfInt = (int[])localIntBuilder.appendCompletedChunk(arrayOfInt, i);
        i = 0;
      }
      label104:
      for (;;)
      {
        int j = i + 1;
        arrayOfInt[i] = k;
        i = j;
        break;
        return (int[])localIntBuilder.completeAndClearBuffer(arrayOfInt, i);
      }
    }
  }
  
  @JacksonStdImpl
  static final class LongDeser
    extends PrimitiveArrayDeserializers<long[]>
  {
    public static final LongDeser instance = new LongDeser();
    private static final long serialVersionUID = 1L;
    
    public LongDeser()
    {
      super();
    }
    
    private final long[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(_valueClass);
      }
      return new long[] { _parseLongPrimitive(paramJsonParser, paramDeserializationContext) };
    }
    
    public long[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if (!paramJsonParser.isExpectedStartArrayToken()) {
        return handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.LongBuilder localLongBuilder = paramDeserializationContext.getArrayBuilders().getLongBuilder();
      long[] arrayOfLong = (long[])localLongBuilder.resetAndStart();
      int i = 0;
      long l;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
      {
        l = _parseLongPrimitive(paramJsonParser, paramDeserializationContext);
        if (i < arrayOfLong.length) {
          break label104;
        }
        arrayOfLong = (long[])localLongBuilder.appendCompletedChunk(arrayOfLong, i);
        i = 0;
      }
      label104:
      for (;;)
      {
        int j = i + 1;
        arrayOfLong[i] = l;
        i = j;
        break;
        return (long[])localLongBuilder.completeAndClearBuffer(arrayOfLong, i);
      }
    }
  }
  
  @JacksonStdImpl
  static final class ShortDeser
    extends PrimitiveArrayDeserializers<short[]>
  {
    private static final long serialVersionUID = 1L;
    
    public ShortDeser()
    {
      super();
    }
    
    private final short[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(_valueClass);
      }
      return new short[] { _parseShortPrimitive(paramJsonParser, paramDeserializationContext) };
    }
    
    public short[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if (!paramJsonParser.isExpectedStartArrayToken()) {
        return handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.ShortBuilder localShortBuilder = paramDeserializationContext.getArrayBuilders().getShortBuilder();
      short[] arrayOfShort = (short[])localShortBuilder.resetAndStart();
      int j = 0;
      int i;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
      {
        i = _parseShortPrimitive(paramJsonParser, paramDeserializationContext);
        if (j < arrayOfShort.length) {
          break label110;
        }
        arrayOfShort = (short[])localShortBuilder.appendCompletedChunk(arrayOfShort, j);
        j = 0;
      }
      label110:
      for (;;)
      {
        int k = j + 1;
        arrayOfShort[j] = i;
        j = k;
        break;
        return (short[])localShortBuilder.completeAndClearBuffer(arrayOfShort, j);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */