package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public class ObjectWriter
  implements Versioned, Serializable
{
  protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
  private static final long serialVersionUID = -7040667122552707164L;
  protected final boolean _cfgBigDecimalAsPlain;
  protected final CharacterEscapes _characterEscapes;
  protected final SerializationConfig _config;
  protected final JsonFactory _generatorFactory;
  protected final PrettyPrinter _prettyPrinter;
  protected final JsonSerializer<Object> _rootSerializer;
  protected final JavaType _rootType;
  protected final FormatSchema _schema;
  protected final SerializerFactory _serializerFactory;
  protected final DefaultSerializerProvider _serializerProvider;
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig)
  {
    _config = paramSerializationConfig;
    _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
    _serializerProvider = _serializerProvider;
    _serializerFactory = _serializerFactory;
    _generatorFactory = _jsonFactory;
    _rootType = null;
    _rootSerializer = null;
    _prettyPrinter = null;
    _schema = null;
    _characterEscapes = null;
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, FormatSchema paramFormatSchema)
  {
    _config = paramSerializationConfig;
    _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
    _serializerProvider = _serializerProvider;
    _serializerFactory = _serializerFactory;
    _generatorFactory = _jsonFactory;
    _rootType = null;
    _rootSerializer = null;
    _prettyPrinter = null;
    _schema = paramFormatSchema;
    _characterEscapes = null;
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, JavaType paramJavaType, PrettyPrinter paramPrettyPrinter)
  {
    _config = paramSerializationConfig;
    _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
    _serializerProvider = _serializerProvider;
    _serializerFactory = _serializerFactory;
    _generatorFactory = _jsonFactory;
    paramObjectMapper = paramJavaType;
    if (paramJavaType != null) {
      paramObjectMapper = paramJavaType.withStaticTyping();
    }
    _rootType = paramObjectMapper;
    _prettyPrinter = paramPrettyPrinter;
    _schema = null;
    _characterEscapes = null;
    _rootSerializer = _prefetchRootSerializer(paramSerializationConfig, paramObjectMapper);
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, JsonFactory paramJsonFactory)
  {
    _config = _config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, paramJsonFactory.requiresPropertyOrdering());
    _cfgBigDecimalAsPlain = _cfgBigDecimalAsPlain;
    _serializerProvider = _serializerProvider;
    _serializerFactory = _serializerFactory;
    _generatorFactory = _generatorFactory;
    _schema = _schema;
    _characterEscapes = _characterEscapes;
    _rootType = _rootType;
    _rootSerializer = _rootSerializer;
    _prettyPrinter = _prettyPrinter;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig)
  {
    _config = paramSerializationConfig;
    _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
    _serializerProvider = _serializerProvider;
    _serializerFactory = _serializerFactory;
    _generatorFactory = _generatorFactory;
    _schema = _schema;
    _characterEscapes = _characterEscapes;
    _rootType = _rootType;
    _rootSerializer = _rootSerializer;
    _prettyPrinter = _prettyPrinter;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer, PrettyPrinter paramPrettyPrinter, FormatSchema paramFormatSchema, CharacterEscapes paramCharacterEscapes)
  {
    _config = paramSerializationConfig;
    _cfgBigDecimalAsPlain = _config.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
    _serializerProvider = _serializerProvider;
    _serializerFactory = _serializerFactory;
    _generatorFactory = _generatorFactory;
    _rootType = paramJavaType;
    _rootSerializer = paramJsonSerializer;
    _prettyPrinter = paramPrettyPrinter;
    _schema = paramFormatSchema;
    _characterEscapes = paramCharacterEscapes;
  }
  
  private void _configureJsonGenerator(JsonGenerator paramJsonGenerator)
  {
    PrettyPrinter localPrettyPrinter2;
    if (_prettyPrinter != null)
    {
      localPrettyPrinter2 = _prettyPrinter;
      if (localPrettyPrinter2 == NULL_PRETTY_PRINTER) {
        paramJsonGenerator.setPrettyPrinter(null);
      }
    }
    for (;;)
    {
      if (_characterEscapes != null) {
        paramJsonGenerator.setCharacterEscapes(_characterEscapes);
      }
      if (_schema != null) {
        paramJsonGenerator.setSchema(_schema);
      }
      if (_cfgBigDecimalAsPlain) {
        paramJsonGenerator.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
      }
      return;
      PrettyPrinter localPrettyPrinter1 = localPrettyPrinter2;
      if ((localPrettyPrinter2 instanceof Instantiatable)) {
        localPrettyPrinter1 = (PrettyPrinter)((Instantiatable)localPrettyPrinter2).createInstance();
      }
      paramJsonGenerator.setPrettyPrinter(localPrettyPrinter1);
      continue;
      if (_config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
        paramJsonGenerator.useDefaultPrettyPrinter();
      }
    }
  }
  
  /* Error */
  private final void _writeCloseable(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    // Byte code:
    //   0: aload_2
    //   1: checkcast 168	java/io/Closeable
    //   4: astore 4
    //   6: aload_0
    //   7: getfield 77	com/fasterxml/jackson/databind/ObjectWriter:_rootType	Lcom/fasterxml/jackson/databind/JavaType;
    //   10: ifnonnull +49 -> 59
    //   13: aload_0
    //   14: aload_3
    //   15: invokevirtual 171	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   18: aload_1
    //   19: aload_2
    //   20: invokevirtual 177	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;)V
    //   23: aload_1
    //   24: invokevirtual 180	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   27: aload 4
    //   29: invokeinterface 181 1 0
    //   34: iconst_0
    //   35: ifeq +11 -> 46
    //   38: new 183	java/lang/NullPointerException
    //   41: dup
    //   42: invokespecial 184	java/lang/NullPointerException:<init>	()V
    //   45: athrow
    //   46: iconst_0
    //   47: ifeq +11 -> 58
    //   50: new 183	java/lang/NullPointerException
    //   53: dup
    //   54: invokespecial 184	java/lang/NullPointerException:<init>	()V
    //   57: athrow
    //   58: return
    //   59: aload_0
    //   60: aload_3
    //   61: invokevirtual 171	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   64: aload_1
    //   65: aload_2
    //   66: aload_0
    //   67: getfield 77	com/fasterxml/jackson/databind/ObjectWriter:_rootType	Lcom/fasterxml/jackson/databind/JavaType;
    //   70: aload_0
    //   71: getfield 79	com/fasterxml/jackson/databind/ObjectWriter:_rootSerializer	Lcom/fasterxml/jackson/databind/JsonSerializer;
    //   74: invokevirtual 187	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JsonSerializer;)V
    //   77: goto -54 -> 23
    //   80: astore_3
    //   81: aload 4
    //   83: astore_2
    //   84: aload_3
    //   85: astore 4
    //   87: aload_1
    //   88: astore_3
    //   89: aload 4
    //   91: astore_1
    //   92: aload_3
    //   93: ifnull +7 -> 100
    //   96: aload_3
    //   97: invokevirtual 180	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   100: aload_2
    //   101: ifnull +9 -> 110
    //   104: aload_2
    //   105: invokeinterface 181 1 0
    //   110: aload_1
    //   111: athrow
    //   112: astore_1
    //   113: goto -67 -> 46
    //   116: astore_1
    //   117: return
    //   118: astore_3
    //   119: goto -19 -> 100
    //   122: astore_2
    //   123: goto -13 -> 110
    //   126: astore_1
    //   127: aconst_null
    //   128: astore_3
    //   129: aload 4
    //   131: astore_2
    //   132: goto -40 -> 92
    //   135: astore_1
    //   136: aconst_null
    //   137: astore_2
    //   138: aconst_null
    //   139: astore_3
    //   140: goto -48 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	143	0	this	ObjectWriter
    //   0	143	1	paramJsonGenerator	JsonGenerator
    //   0	143	2	paramObject	Object
    //   0	143	3	paramSerializationConfig	SerializationConfig
    //   4	126	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   6	23	80	finally
    //   59	77	80	finally
    //   38	46	112	java/io/IOException
    //   50	58	116	java/io/IOException
    //   96	100	118	java/io/IOException
    //   104	110	122	java/io/IOException
    //   23	27	126	finally
    //   27	34	135	finally
  }
  
  /* Error */
  private final void _writeCloseableValue(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    // Byte code:
    //   0: aload_2
    //   1: checkcast 168	java/io/Closeable
    //   4: astore 4
    //   6: aload_0
    //   7: getfield 77	com/fasterxml/jackson/databind/ObjectWriter:_rootType	Lcom/fasterxml/jackson/databind/JavaType;
    //   10: ifnonnull +52 -> 62
    //   13: aload_0
    //   14: aload_3
    //   15: invokevirtual 171	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   18: aload_1
    //   19: aload_2
    //   20: invokevirtual 177	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;)V
    //   23: aload_0
    //   24: getfield 48	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   27: getstatic 192	com/fasterxml/jackson/databind/SerializationFeature:FLUSH_AFTER_WRITE_VALUE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   30: invokevirtual 60	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   33: ifeq +7 -> 40
    //   36: aload_1
    //   37: invokevirtual 195	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   40: aconst_null
    //   41: astore_2
    //   42: aload 4
    //   44: invokeinterface 181 1 0
    //   49: iconst_0
    //   50: ifeq +11 -> 61
    //   53: new 183	java/lang/NullPointerException
    //   56: dup
    //   57: invokespecial 184	java/lang/NullPointerException:<init>	()V
    //   60: athrow
    //   61: return
    //   62: aload_0
    //   63: aload_3
    //   64: invokevirtual 171	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   67: aload_1
    //   68: aload_2
    //   69: aload_0
    //   70: getfield 77	com/fasterxml/jackson/databind/ObjectWriter:_rootType	Lcom/fasterxml/jackson/databind/JavaType;
    //   73: aload_0
    //   74: getfield 79	com/fasterxml/jackson/databind/ObjectWriter:_rootSerializer	Lcom/fasterxml/jackson/databind/JsonSerializer;
    //   77: invokevirtual 187	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JsonSerializer;)V
    //   80: goto -57 -> 23
    //   83: astore_1
    //   84: aload 4
    //   86: astore_2
    //   87: aload_2
    //   88: ifnull +9 -> 97
    //   91: aload_2
    //   92: invokeinterface 181 1 0
    //   97: aload_1
    //   98: athrow
    //   99: astore_1
    //   100: return
    //   101: astore_2
    //   102: goto -5 -> 97
    //   105: astore_1
    //   106: goto -19 -> 87
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	this	ObjectWriter
    //   0	109	1	paramJsonGenerator	JsonGenerator
    //   0	109	2	paramObject	Object
    //   0	109	3	paramSerializationConfig	SerializationConfig
    //   4	81	4	localCloseable	Closeable
    // Exception table:
    //   from	to	target	type
    //   6	23	83	finally
    //   23	40	83	finally
    //   62	80	83	finally
    //   53	61	99	java/io/IOException
    //   91	97	101	java/io/IOException
    //   42	49	105	finally
  }
  
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configureJsonGenerator(paramJsonGenerator);
    if ((_config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE)) && ((paramObject instanceof Closeable)))
    {
      _writeCloseable(paramJsonGenerator, paramObject, _config);
      return;
    }
    int j = 0;
    int i = j;
    for (;;)
    {
      try
      {
        if (_rootType == null)
        {
          i = j;
          _serializerProvider(_config).serializeValue(paramJsonGenerator, paramObject);
          i = 1;
          paramJsonGenerator.close();
          return;
        }
      }
      finally
      {
        if (i != 0) {}
      }
      try
      {
        paramJsonGenerator.close();
        throw ((Throwable)paramObject);
        i = j;
        _serializerProvider(_config).serializeValue(paramJsonGenerator, paramObject, _rootType, _rootSerializer);
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;) {}
      }
    }
  }
  
  protected JsonSerializer<Object> _prefetchRootSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType)
  {
    if ((paramJavaType == null) || (!_config.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH))) {
      return null;
    }
    try
    {
      paramSerializationConfig = _serializerProvider(paramSerializationConfig).findTypedValueSerializer(paramJavaType, true, null);
      return paramSerializationConfig;
    }
    catch (JsonProcessingException paramSerializationConfig) {}
    return null;
  }
  
  protected DefaultSerializerProvider _serializerProvider(SerializationConfig paramSerializationConfig)
  {
    return _serializerProvider.createInstance(paramSerializationConfig, _serializerFactory);
  }
  
  protected void _verifySchemaType(FormatSchema paramFormatSchema)
  {
    if ((paramFormatSchema != null) && (!_generatorFactory.canUseSchema(paramFormatSchema))) {
      throw new IllegalArgumentException("Can not use FormatSchema of type " + paramFormatSchema.getClass().getName() + " for format " + _generatorFactory.getFormatName());
    }
  }
  
  public void acceptJsonFormatVisitor(JavaType paramJavaType, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("type must be provided");
    }
    _serializerProvider(_config).acceptJsonFormatVisitor(paramJavaType, paramJsonFormatVisitorWrapper);
  }
  
  public boolean canSerialize(Class<?> paramClass)
  {
    return _serializerProvider(_config).hasSerializerFor(paramClass, null);
  }
  
  public boolean canSerialize(Class<?> paramClass, AtomicReference<Throwable> paramAtomicReference)
  {
    return _serializerProvider(_config).hasSerializerFor(paramClass, paramAtomicReference);
  }
  
  public ContextAttributes getAttributes()
  {
    return _config.getAttributes();
  }
  
  public SerializationConfig getConfig()
  {
    return _config;
  }
  
  public JsonFactory getFactory()
  {
    return _generatorFactory;
  }
  
  @Deprecated
  public JsonFactory getJsonFactory()
  {
    return _generatorFactory;
  }
  
  public TypeFactory getTypeFactory()
  {
    return _config.getTypeFactory();
  }
  
  public boolean hasPrefetchedSerializer()
  {
    return _rootSerializer != null;
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return _generatorFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return _config.isEnabled(paramMapperFeature);
  }
  
  public boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    return _config.isEnabled(paramSerializationFeature);
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public ObjectWriter with(Base64Variant paramBase64Variant)
  {
    paramBase64Variant = _config.with(paramBase64Variant);
    if (paramBase64Variant == _config) {
      return this;
    }
    return new ObjectWriter(this, paramBase64Variant);
  }
  
  public ObjectWriter with(JsonFactory paramJsonFactory)
  {
    if (paramJsonFactory == _generatorFactory) {
      return this;
    }
    return new ObjectWriter(this, paramJsonFactory);
  }
  
  public ObjectWriter with(PrettyPrinter paramPrettyPrinter)
  {
    if (paramPrettyPrinter == _prettyPrinter) {
      return this;
    }
    if (paramPrettyPrinter == null) {
      paramPrettyPrinter = NULL_PRETTY_PRINTER;
    }
    for (;;)
    {
      return new ObjectWriter(this, _config, _rootType, _rootSerializer, paramPrettyPrinter, _schema, _characterEscapes);
    }
  }
  
  public ObjectWriter with(CharacterEscapes paramCharacterEscapes)
  {
    if (_characterEscapes == paramCharacterEscapes) {
      return this;
    }
    return new ObjectWriter(this, _config, _rootType, _rootSerializer, _prettyPrinter, _schema, paramCharacterEscapes);
  }
  
  public ObjectWriter with(SerializationFeature paramSerializationFeature)
  {
    paramSerializationFeature = _config.with(paramSerializationFeature);
    if (paramSerializationFeature == _config) {
      return this;
    }
    return new ObjectWriter(this, paramSerializationFeature);
  }
  
  public ObjectWriter with(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    paramSerializationFeature = _config.with(paramSerializationFeature, paramVarArgs);
    if (paramSerializationFeature == _config) {
      return this;
    }
    return new ObjectWriter(this, paramSerializationFeature);
  }
  
  public ObjectWriter with(ContextAttributes paramContextAttributes)
  {
    paramContextAttributes = _config.with(paramContextAttributes);
    if (paramContextAttributes == _config) {
      return this;
    }
    return new ObjectWriter(this, paramContextAttributes);
  }
  
  public ObjectWriter with(FilterProvider paramFilterProvider)
  {
    if (paramFilterProvider == _config.getFilterProvider()) {
      return this;
    }
    return new ObjectWriter(this, _config.withFilters(paramFilterProvider));
  }
  
  public ObjectWriter with(DateFormat paramDateFormat)
  {
    paramDateFormat = _config.with(paramDateFormat);
    if (paramDateFormat == _config) {
      return this;
    }
    return new ObjectWriter(this, paramDateFormat);
  }
  
  public ObjectWriter with(Locale paramLocale)
  {
    paramLocale = _config.with(paramLocale);
    if (paramLocale == _config) {
      return this;
    }
    return new ObjectWriter(this, paramLocale);
  }
  
  public ObjectWriter with(TimeZone paramTimeZone)
  {
    paramTimeZone = _config.with(paramTimeZone);
    if (paramTimeZone == _config) {
      return this;
    }
    return new ObjectWriter(this, paramTimeZone);
  }
  
  public ObjectWriter withAttribute(Object paramObject1, Object paramObject2)
  {
    paramObject1 = (SerializationConfig)_config.withAttribute(paramObject1, paramObject2);
    if (paramObject1 == _config) {
      return this;
    }
    return new ObjectWriter(this, (SerializationConfig)paramObject1);
  }
  
  public ObjectWriter withAttributes(Map<Object, Object> paramMap)
  {
    paramMap = (SerializationConfig)_config.withAttributes(paramMap);
    if (paramMap == _config) {
      return this;
    }
    return new ObjectWriter(this, paramMap);
  }
  
  public ObjectWriter withDefaultPrettyPrinter()
  {
    return with(new DefaultPrettyPrinter());
  }
  
  public ObjectWriter withFeatures(SerializationFeature... paramVarArgs)
  {
    paramVarArgs = _config.withFeatures(paramVarArgs);
    if (paramVarArgs == _config) {
      return this;
    }
    return new ObjectWriter(this, paramVarArgs);
  }
  
  public ObjectWriter withRootName(String paramString)
  {
    paramString = _config.withRootName(paramString);
    if (paramString == _config) {
      return this;
    }
    return new ObjectWriter(this, paramString);
  }
  
  public ObjectWriter withSchema(FormatSchema paramFormatSchema)
  {
    if (_schema == paramFormatSchema) {
      return this;
    }
    _verifySchemaType(paramFormatSchema);
    return new ObjectWriter(this, _config, _rootType, _rootSerializer, _prettyPrinter, paramFormatSchema, _characterEscapes);
  }
  
  public ObjectWriter withType(TypeReference<?> paramTypeReference)
  {
    return withType(_config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectWriter withType(JavaType paramJavaType)
  {
    paramJavaType = paramJavaType.withStaticTyping();
    JsonSerializer localJsonSerializer = _prefetchRootSerializer(_config, paramJavaType);
    return new ObjectWriter(this, _config, paramJavaType, localJsonSerializer, _prettyPrinter, _schema, _characterEscapes);
  }
  
  public ObjectWriter withType(Class<?> paramClass)
  {
    return withType(_config.constructType(paramClass));
  }
  
  public ObjectWriter withView(Class<?> paramClass)
  {
    paramClass = _config.withView(paramClass);
    if (paramClass == _config) {
      return this;
    }
    return new ObjectWriter(this, paramClass);
  }
  
  public ObjectWriter without(SerializationFeature paramSerializationFeature)
  {
    paramSerializationFeature = _config.without(paramSerializationFeature);
    if (paramSerializationFeature == _config) {
      return this;
    }
    return new ObjectWriter(this, paramSerializationFeature);
  }
  
  public ObjectWriter without(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    paramSerializationFeature = _config.without(paramSerializationFeature, paramVarArgs);
    if (paramSerializationFeature == _config) {
      return this;
    }
    return new ObjectWriter(this, paramSerializationFeature);
  }
  
  public ObjectWriter withoutAttribute(Object paramObject)
  {
    paramObject = (SerializationConfig)_config.withoutAttribute(paramObject);
    if (paramObject == _config) {
      return this;
    }
    return new ObjectWriter(this, (SerializationConfig)paramObject);
  }
  
  public ObjectWriter withoutFeatures(SerializationFeature... paramVarArgs)
  {
    paramVarArgs = _config.withoutFeatures(paramVarArgs);
    if (paramVarArgs == _config) {
      return this;
    }
    return new ObjectWriter(this, paramVarArgs);
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configureJsonGenerator(paramJsonGenerator);
    if ((_config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE)) && ((paramObject instanceof Closeable))) {
      _writeCloseableValue(paramJsonGenerator, paramObject, _config);
    }
    for (;;)
    {
      return;
      if (_rootType == null) {
        _serializerProvider(_config).serializeValue(paramJsonGenerator, paramObject);
      }
      while (_config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE))
      {
        paramJsonGenerator.flush();
        return;
        _serializerProvider(_config).serializeValue(paramJsonGenerator, paramObject, _rootType, _rootSerializer);
      }
    }
  }
  
  public void writeValue(File paramFile, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(_generatorFactory.createGenerator(paramFile, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(OutputStream paramOutputStream, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(_generatorFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(Writer paramWriter, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(_generatorFactory.createGenerator(paramWriter), paramObject);
  }
  
  public byte[] writeValueAsBytes(Object paramObject)
    throws JsonProcessingException
  {
    ByteArrayBuilder localByteArrayBuilder = new ByteArrayBuilder(_generatorFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(_generatorFactory.createGenerator(localByteArrayBuilder, JsonEncoding.UTF8), paramObject);
      paramObject = localByteArrayBuilder.toByteArray();
      localByteArrayBuilder.release();
      return (byte[])paramObject;
    }
    catch (JsonProcessingException paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (IOException paramObject)
    {
      throw JsonMappingException.fromUnexpectedIOE((IOException)paramObject);
    }
  }
  
  public String writeValueAsString(Object paramObject)
    throws JsonProcessingException
  {
    SegmentedStringWriter localSegmentedStringWriter = new SegmentedStringWriter(_generatorFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(_generatorFactory.createGenerator(localSegmentedStringWriter), paramObject);
      return localSegmentedStringWriter.getAndClear();
    }
    catch (JsonProcessingException paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (IOException paramObject)
    {
      throw JsonMappingException.fromUnexpectedIOE((IOException)paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ObjectWriter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */