package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader
  extends ObjectCodec
  implements Versioned, Serializable
{
  private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
  private static final long serialVersionUID = -4251443320039569153L;
  protected final DeserializationConfig _config;
  protected final DefaultDeserializationContext _context;
  protected final DataFormatReaders _dataFormatReaders;
  protected final InjectableValues _injectableValues;
  protected final JsonFactory _parserFactory;
  protected final JsonDeserializer<Object> _rootDeserializer;
  protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
  protected final RootNameLookup _rootNames;
  protected final FormatSchema _schema;
  protected final boolean _unwrapRoot;
  protected final Object _valueToUpdate;
  protected final JavaType _valueType;
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig)
  {
    this(paramObjectMapper, paramDeserializationConfig, null, null, null, null);
  }
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues)
  {
    _config = paramDeserializationConfig;
    _context = _deserializationContext;
    _rootDeserializers = _rootDeserializers;
    _parserFactory = _jsonFactory;
    _rootNames = _rootNames;
    _valueType = paramJavaType;
    _valueToUpdate = paramObject;
    if ((paramObject != null) && (paramJavaType.isArrayType())) {
      throw new IllegalArgumentException("Can not update an array value");
    }
    _schema = paramFormatSchema;
    _injectableValues = paramInjectableValues;
    _unwrapRoot = paramDeserializationConfig.useRootWrapping();
    _rootDeserializer = _prefetchRootDeserializer(paramDeserializationConfig, paramJavaType);
    _dataFormatReaders = null;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, JsonFactory paramJsonFactory)
  {
    _config = _config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, paramJsonFactory.requiresPropertyOrdering());
    _context = _context;
    _rootDeserializers = _rootDeserializers;
    _parserFactory = paramJsonFactory;
    _rootNames = _rootNames;
    _valueType = _valueType;
    _rootDeserializer = _rootDeserializer;
    _valueToUpdate = _valueToUpdate;
    _schema = _schema;
    _injectableValues = _injectableValues;
    _unwrapRoot = _unwrapRoot;
    _dataFormatReaders = _dataFormatReaders;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig)
  {
    _config = paramDeserializationConfig;
    _context = _context;
    _rootDeserializers = _rootDeserializers;
    _parserFactory = _parserFactory;
    _rootNames = _rootNames;
    _valueType = _valueType;
    _rootDeserializer = _rootDeserializer;
    _valueToUpdate = _valueToUpdate;
    _schema = _schema;
    _injectableValues = _injectableValues;
    _unwrapRoot = paramDeserializationConfig.useRootWrapping();
    _dataFormatReaders = _dataFormatReaders;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues, DataFormatReaders paramDataFormatReaders)
  {
    _config = paramDeserializationConfig;
    _context = _context;
    _rootDeserializers = _rootDeserializers;
    _parserFactory = _parserFactory;
    _rootNames = _rootNames;
    _valueType = paramJavaType;
    _rootDeserializer = paramJsonDeserializer;
    _valueToUpdate = paramObject;
    if ((paramObject != null) && (paramJavaType.isArrayType())) {
      throw new IllegalArgumentException("Can not update an array value");
    }
    _schema = paramFormatSchema;
    _injectableValues = paramInjectableValues;
    _unwrapRoot = paramDeserializationConfig.useRootWrapping();
    _dataFormatReaders = paramDataFormatReaders;
  }
  
  protected static JsonToken _initForReading(JsonParser paramJsonParser)
    throws IOException, JsonParseException, JsonMappingException
  {
    JsonToken localJsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken localJsonToken1 = localJsonToken2;
    if (localJsonToken2 == null)
    {
      localJsonToken2 = paramJsonParser.nextToken();
      localJsonToken1 = localJsonToken2;
      if (localJsonToken2 == null) {
        throw JsonMappingException.from(paramJsonParser, "No content to map due to end-of-input");
      }
    }
    return localJsonToken1;
  }
  
  protected Object _bind(JsonParser paramJsonParser, Object paramObject)
    throws IOException, JsonParseException, JsonMappingException
  {
    Object localObject2 = _initForReading(paramJsonParser);
    Object localObject1;
    if (localObject2 == JsonToken.VALUE_NULL)
    {
      localObject1 = paramObject;
      if (paramObject == null) {
        localObject1 = _findRootDeserializer(createDeserializationContext(paramJsonParser, _config), _valueType).getNullValue();
      }
    }
    for (;;)
    {
      paramJsonParser.clearCurrentToken();
      return localObject1;
      localObject1 = paramObject;
      if (localObject2 != JsonToken.END_ARRAY)
      {
        localObject1 = paramObject;
        if (localObject2 != JsonToken.END_OBJECT)
        {
          localObject1 = createDeserializationContext(paramJsonParser, _config);
          localObject2 = _findRootDeserializer((DeserializationContext)localObject1, _valueType);
          if (_unwrapRoot)
          {
            localObject1 = _unwrapAndDeserialize(paramJsonParser, (DeserializationContext)localObject1, _valueType, (JsonDeserializer)localObject2);
          }
          else if (paramObject == null)
          {
            localObject1 = ((JsonDeserializer)localObject2).deserialize(paramJsonParser, (DeserializationContext)localObject1);
          }
          else
          {
            ((JsonDeserializer)localObject2).deserialize(paramJsonParser, (DeserializationContext)localObject1, paramObject);
            localObject1 = paramObject;
          }
        }
      }
    }
  }
  
  /* Error */
  protected Object _bindAndClose(JsonParser paramJsonParser, Object paramObject)
    throws IOException, JsonParseException, JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 98	com/fasterxml/jackson/databind/ObjectReader:_schema	Lcom/fasterxml/jackson/core/FormatSchema;
    //   4: ifnull +11 -> 15
    //   7: aload_1
    //   8: aload_0
    //   9: getfield 98	com/fasterxml/jackson/databind/ObjectReader:_schema	Lcom/fasterxml/jackson/core/FormatSchema;
    //   12: invokevirtual 208	com/fasterxml/jackson/core/JsonParser:setSchema	(Lcom/fasterxml/jackson/core/FormatSchema;)V
    //   15: aload_1
    //   16: invokestatic 163	com/fasterxml/jackson/databind/ObjectReader:_initForReading	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/core/JsonToken;
    //   19: astore 4
    //   21: aload 4
    //   23: getstatic 169	com/fasterxml/jackson/core/JsonToken:VALUE_NULL	Lcom/fasterxml/jackson/core/JsonToken;
    //   26: if_acmpne +36 -> 62
    //   29: aload_2
    //   30: astore_3
    //   31: aload_2
    //   32: ifnonnull +24 -> 56
    //   35: aload_0
    //   36: aload_0
    //   37: aload_1
    //   38: aload_0
    //   39: getfield 61	com/fasterxml/jackson/databind/ObjectReader:_config	Lcom/fasterxml/jackson/databind/DeserializationConfig;
    //   42: invokevirtual 173	com/fasterxml/jackson/databind/ObjectReader:createDeserializationContext	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationConfig;)Lcom/fasterxml/jackson/databind/deser/DefaultDeserializationContext;
    //   45: aload_0
    //   46: getfield 81	com/fasterxml/jackson/databind/ObjectReader:_valueType	Lcom/fasterxml/jackson/databind/JavaType;
    //   49: invokevirtual 177	com/fasterxml/jackson/databind/ObjectReader:_findRootDeserializer	(Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   52: invokevirtual 183	com/fasterxml/jackson/databind/JsonDeserializer:getNullValue	()Ljava/lang/Object;
    //   55: astore_3
    //   56: aload_1
    //   57: invokevirtual 211	com/fasterxml/jackson/core/JsonParser:close	()V
    //   60: aload_3
    //   61: areturn
    //   62: aload_2
    //   63: astore_3
    //   64: aload 4
    //   66: getstatic 189	com/fasterxml/jackson/core/JsonToken:END_ARRAY	Lcom/fasterxml/jackson/core/JsonToken;
    //   69: if_acmpeq -13 -> 56
    //   72: aload_2
    //   73: astore_3
    //   74: aload 4
    //   76: getstatic 192	com/fasterxml/jackson/core/JsonToken:END_OBJECT	Lcom/fasterxml/jackson/core/JsonToken;
    //   79: if_acmpeq -23 -> 56
    //   82: aload_0
    //   83: aload_1
    //   84: aload_0
    //   85: getfield 61	com/fasterxml/jackson/databind/ObjectReader:_config	Lcom/fasterxml/jackson/databind/DeserializationConfig;
    //   88: invokevirtual 173	com/fasterxml/jackson/databind/ObjectReader:createDeserializationContext	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationConfig;)Lcom/fasterxml/jackson/databind/deser/DefaultDeserializationContext;
    //   91: astore_3
    //   92: aload_0
    //   93: aload_3
    //   94: aload_0
    //   95: getfield 81	com/fasterxml/jackson/databind/ObjectReader:_valueType	Lcom/fasterxml/jackson/databind/JavaType;
    //   98: invokevirtual 177	com/fasterxml/jackson/databind/ObjectReader:_findRootDeserializer	(Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   101: astore 4
    //   103: aload_0
    //   104: getfield 107	com/fasterxml/jackson/databind/ObjectReader:_unwrapRoot	Z
    //   107: ifeq +19 -> 126
    //   110: aload_0
    //   111: aload_1
    //   112: aload_3
    //   113: aload_0
    //   114: getfield 81	com/fasterxml/jackson/databind/ObjectReader:_valueType	Lcom/fasterxml/jackson/databind/JavaType;
    //   117: aload 4
    //   119: invokevirtual 196	com/fasterxml/jackson/databind/ObjectReader:_unwrapAndDeserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JsonDeserializer;)Ljava/lang/Object;
    //   122: astore_3
    //   123: goto -67 -> 56
    //   126: aload_2
    //   127: ifnonnull +14 -> 141
    //   130: aload 4
    //   132: aload_1
    //   133: aload_3
    //   134: invokevirtual 200	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   137: astore_3
    //   138: goto -82 -> 56
    //   141: aload 4
    //   143: aload_1
    //   144: aload_3
    //   145: aload_2
    //   146: invokevirtual 203	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;)Ljava/lang/Object;
    //   149: pop
    //   150: aload_2
    //   151: astore_3
    //   152: goto -96 -> 56
    //   155: astore_2
    //   156: aload_1
    //   157: invokevirtual 211	com/fasterxml/jackson/core/JsonParser:close	()V
    //   160: aload_2
    //   161: athrow
    //   162: astore_1
    //   163: aload_3
    //   164: areturn
    //   165: astore_1
    //   166: goto -6 -> 160
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	this	ObjectReader
    //   0	169	1	paramJsonParser	JsonParser
    //   0	169	2	paramObject	Object
    //   30	134	3	localObject1	Object
    //   19	123	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   15	29	155	finally
    //   35	56	155	finally
    //   64	72	155	finally
    //   74	123	155	finally
    //   130	138	155	finally
    //   141	150	155	finally
    //   56	60	162	java/io/IOException
    //   156	160	165	java/io/IOException
  }
  
  /* Error */
  protected JsonNode _bindAndCloseAsTree(JsonParser paramJsonParser)
    throws IOException, JsonParseException, JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 98	com/fasterxml/jackson/databind/ObjectReader:_schema	Lcom/fasterxml/jackson/core/FormatSchema;
    //   4: ifnull +11 -> 15
    //   7: aload_1
    //   8: aload_0
    //   9: getfield 98	com/fasterxml/jackson/databind/ObjectReader:_schema	Lcom/fasterxml/jackson/core/FormatSchema;
    //   12: invokevirtual 208	com/fasterxml/jackson/core/JsonParser:setSchema	(Lcom/fasterxml/jackson/core/FormatSchema;)V
    //   15: aload_0
    //   16: aload_1
    //   17: invokevirtual 216	com/fasterxml/jackson/databind/ObjectReader:_bindAsTree	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   20: astore_2
    //   21: aload_1
    //   22: invokevirtual 211	com/fasterxml/jackson/core/JsonParser:close	()V
    //   25: aload_2
    //   26: areturn
    //   27: astore_2
    //   28: aload_1
    //   29: invokevirtual 211	com/fasterxml/jackson/core/JsonParser:close	()V
    //   32: aload_2
    //   33: athrow
    //   34: astore_1
    //   35: aload_2
    //   36: areturn
    //   37: astore_1
    //   38: goto -6 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	ObjectReader
    //   0	41	1	paramJsonParser	JsonParser
    //   20	6	2	localJsonNode1	JsonNode
    //   27	9	2	localJsonNode2	JsonNode
    // Exception table:
    //   from	to	target	type
    //   15	21	27	finally
    //   21	25	34	java/io/IOException
    //   28	32	37	java/io/IOException
  }
  
  protected <T> MappingIterator<T> _bindAndReadValues(JsonParser paramJsonParser, Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (_schema != null) {
      paramJsonParser.setSchema(_schema);
    }
    paramJsonParser.nextToken();
    paramObject = createDeserializationContext(paramJsonParser, _config);
    return new MappingIterator(_valueType, paramJsonParser, (DeserializationContext)paramObject, _findRootDeserializer((DeserializationContext)paramObject, _valueType), true, _valueToUpdate);
  }
  
  protected JsonNode _bindAsTree(JsonParser paramJsonParser)
    throws IOException, JsonParseException, JsonMappingException
  {
    Object localObject = _initForReading(paramJsonParser);
    if ((localObject == JsonToken.VALUE_NULL) || (localObject == JsonToken.END_ARRAY) || (localObject == JsonToken.END_OBJECT)) {
      localObject = NullNode.instance;
    }
    for (;;)
    {
      paramJsonParser.clearCurrentToken();
      return (JsonNode)localObject;
      localObject = createDeserializationContext(paramJsonParser, _config);
      JsonDeserializer localJsonDeserializer = _findRootDeserializer((DeserializationContext)localObject, JSON_NODE_TYPE);
      if (_unwrapRoot) {
        localObject = (JsonNode)_unwrapAndDeserialize(paramJsonParser, (DeserializationContext)localObject, JSON_NODE_TYPE, localJsonDeserializer);
      } else {
        localObject = (JsonNode)localJsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)localObject);
      }
    }
  }
  
  protected Object _detectBindAndClose(DataFormatReaders.Match paramMatch, boolean paramBoolean)
    throws IOException
  {
    if (!paramMatch.hasMatch()) {
      _reportUnkownFormat(_dataFormatReaders, paramMatch);
    }
    JsonParser localJsonParser = paramMatch.createParserWithMatch();
    if (paramBoolean) {
      localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    }
    return paramMatch.getReader()._bindAndClose(localJsonParser, _valueToUpdate);
  }
  
  protected Object _detectBindAndClose(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramArrayOfByte = _dataFormatReaders.findFormat(paramArrayOfByte, paramInt1, paramInt2);
    if (!paramArrayOfByte.hasMatch()) {
      _reportUnkownFormat(_dataFormatReaders, paramArrayOfByte);
    }
    JsonParser localJsonParser = paramArrayOfByte.createParserWithMatch();
    return paramArrayOfByte.getReader()._bindAndClose(localJsonParser, _valueToUpdate);
  }
  
  protected JsonNode _detectBindAndCloseAsTree(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = _dataFormatReaders.findFormat(paramInputStream);
    if (!paramInputStream.hasMatch()) {
      _reportUnkownFormat(_dataFormatReaders, paramInputStream);
    }
    JsonParser localJsonParser = paramInputStream.createParserWithMatch();
    localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    return paramInputStream.getReader()._bindAndCloseAsTree(localJsonParser);
  }
  
  protected <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match paramMatch, boolean paramBoolean)
    throws IOException, JsonProcessingException
  {
    if (!paramMatch.hasMatch()) {
      _reportUnkownFormat(_dataFormatReaders, paramMatch);
    }
    JsonParser localJsonParser = paramMatch.createParserWithMatch();
    if (paramBoolean) {
      localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    }
    return paramMatch.getReader()._bindAndReadValues(localJsonParser, _valueToUpdate);
  }
  
  protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    Object localObject;
    if (_rootDeserializer != null) {
      localObject = _rootDeserializer;
    }
    JsonDeserializer localJsonDeserializer;
    do
    {
      return (JsonDeserializer<Object>)localObject;
      if (paramJavaType == null) {
        throw new JsonMappingException("No value type configured for ObjectReader");
      }
      localJsonDeserializer = (JsonDeserializer)_rootDeserializers.get(paramJavaType);
      localObject = localJsonDeserializer;
    } while (localJsonDeserializer != null);
    paramDeserializationContext = paramDeserializationContext.findRootValueDeserializer(paramJavaType);
    if (paramDeserializationContext == null) {
      throw new JsonMappingException("Can not find a deserializer for type " + paramJavaType);
    }
    _rootDeserializers.put(paramJavaType, paramDeserializationContext);
    return paramDeserializationContext;
  }
  
  protected InputStream _inputStream(File paramFile)
    throws IOException
  {
    return new FileInputStream(paramFile);
  }
  
  protected InputStream _inputStream(URL paramURL)
    throws IOException
  {
    return paramURL.openStream();
  }
  
  protected JsonDeserializer<Object> _prefetchRootDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    JsonDeserializer localJsonDeserializer = null;
    paramDeserializationConfig = localJsonDeserializer;
    if (paramJavaType != null)
    {
      if (_config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
        break label25;
      }
      paramDeserializationConfig = localJsonDeserializer;
    }
    for (;;)
    {
      return paramDeserializationConfig;
      label25:
      localJsonDeserializer = (JsonDeserializer)_rootDeserializers.get(paramJavaType);
      paramDeserializationConfig = localJsonDeserializer;
      if (localJsonDeserializer == null)
      {
        paramDeserializationConfig = localJsonDeserializer;
        try
        {
          localJsonDeserializer = createDeserializationContext(null, _config).findRootValueDeserializer(paramJavaType);
          paramDeserializationConfig = localJsonDeserializer;
          if (localJsonDeserializer != null)
          {
            paramDeserializationConfig = localJsonDeserializer;
            _rootDeserializers.put(paramJavaType, localJsonDeserializer);
            return localJsonDeserializer;
          }
        }
        catch (JsonProcessingException paramJavaType) {}
      }
    }
    return paramDeserializationConfig;
  }
  
  protected void _reportUndetectableSource(Object paramObject)
    throws JsonProcessingException
  {
    throw new JsonParseException("Can not use source of type " + paramObject.getClass().getName() + " with format auto-detection: must be byte- not char-based", JsonLocation.NA);
  }
  
  protected void _reportUnkownFormat(DataFormatReaders paramDataFormatReaders, DataFormatReaders.Match paramMatch)
    throws JsonProcessingException
  {
    throw new JsonParseException("Can not detect format from input, does not look like any of detectable formats " + paramDataFormatReaders.toString(), JsonLocation.NA);
  }
  
  protected Object _unwrapAndDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer)
    throws IOException, JsonParseException, JsonMappingException
  {
    String str2 = _config.getRootName();
    String str1 = str2;
    if (str2 == null) {
      str1 = _rootNames.findRootName(paramJavaType, _config).getValue();
    }
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      throw JsonMappingException.from(paramJsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
    }
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME) {
      throw JsonMappingException.from(paramJsonParser, "Current token not FIELD_NAME (to contain expected root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
    }
    str2 = paramJsonParser.getCurrentName();
    if (!str1.equals(str2)) {
      throw JsonMappingException.from(paramJsonParser, "Root name '" + str2 + "' does not match expected ('" + str1 + "') for type " + paramJavaType);
    }
    paramJsonParser.nextToken();
    if (_valueToUpdate == null) {}
    for (paramDeserializationContext = paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext); paramJsonParser.nextToken() != JsonToken.END_OBJECT; paramDeserializationContext = _valueToUpdate)
    {
      throw JsonMappingException.from(paramJsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
      paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext, _valueToUpdate);
    }
    return paramDeserializationContext;
  }
  
  protected void _verifySchemaType(FormatSchema paramFormatSchema)
  {
    if ((paramFormatSchema != null) && (!_parserFactory.canUseSchema(paramFormatSchema))) {
      throw new IllegalArgumentException("Can not use FormatSchema of type " + paramFormatSchema.getClass().getName() + " for format " + _parserFactory.getFormatName());
    }
  }
  
  protected ObjectReader _with(DeserializationConfig paramDeserializationConfig)
  {
    if (paramDeserializationConfig == _config) {
      return this;
    }
    if (_dataFormatReaders != null) {
      return new ObjectReader(this, paramDeserializationConfig).withFormatDetection(_dataFormatReaders.with(paramDeserializationConfig));
    }
    return new ObjectReader(this, paramDeserializationConfig);
  }
  
  public JsonNode createArrayNode()
  {
    return _config.getNodeFactory().arrayNode();
  }
  
  protected DefaultDeserializationContext createDeserializationContext(JsonParser paramJsonParser, DeserializationConfig paramDeserializationConfig)
  {
    return _context.createInstance(paramDeserializationConfig, paramJsonParser, _injectableValues);
  }
  
  public JsonNode createObjectNode()
  {
    return _config.getNodeFactory().objectNode();
  }
  
  public ContextAttributes getAttributes()
  {
    return _config.getAttributes();
  }
  
  public DeserializationConfig getConfig()
  {
    return _config;
  }
  
  public JsonFactory getFactory()
  {
    return _parserFactory;
  }
  
  @Deprecated
  public JsonFactory getJsonFactory()
  {
    return _parserFactory;
  }
  
  public TypeFactory getTypeFactory()
  {
    return _config.getTypeFactory();
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return _parserFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(DeserializationFeature paramDeserializationFeature)
  {
    return _config.isEnabled(paramDeserializationFeature);
  }
  
  public boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return _config.isEnabled(paramMapperFeature);
  }
  
  public <T extends TreeNode> T readTree(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    return _bindAsTree(paramJsonParser);
  }
  
  public JsonNode readTree(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return _detectBindAndCloseAsTree(paramInputStream);
    }
    return _bindAndCloseAsTree(_parserFactory.createParser(paramInputStream));
  }
  
  public JsonNode readTree(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    return _bindAndCloseAsTree(_parserFactory.createParser(paramReader));
  }
  
  public JsonNode readTree(String paramString)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    return _bindAndCloseAsTree(_parserFactory.createParser(paramString));
  }
  
  public <T> T readValue(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    return (T)_bind(paramJsonParser, _valueToUpdate);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException
  {
    return (T)withType((JavaType)paramResolvedType).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException
  {
    return (T)withType(paramTypeReference).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    return (T)withType(paramJavaType).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException
  {
    return (T)withType(paramClass).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonNode paramJsonNode)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      _reportUndetectableSource(paramJsonNode);
    }
    return (T)_bindAndClose(treeAsTokens(paramJsonNode), _valueToUpdate);
  }
  
  public <T> T readValue(File paramFile)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return (T)_detectBindAndClose(_dataFormatReaders.findFormat(_inputStream(paramFile)), true);
    }
    return (T)_bindAndClose(_parserFactory.createParser(paramFile), _valueToUpdate);
  }
  
  public <T> T readValue(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return (T)_detectBindAndClose(_dataFormatReaders.findFormat(paramInputStream), false);
    }
    return (T)_bindAndClose(_parserFactory.createParser(paramInputStream), _valueToUpdate);
  }
  
  public <T> T readValue(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    return (T)_bindAndClose(_parserFactory.createParser(paramReader), _valueToUpdate);
  }
  
  public <T> T readValue(String paramString)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    return (T)_bindAndClose(_parserFactory.createParser(paramString), _valueToUpdate);
  }
  
  public <T> T readValue(URL paramURL)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return (T)_detectBindAndClose(_dataFormatReaders.findFormat(_inputStream(paramURL)), true);
    }
    return (T)_bindAndClose(_parserFactory.createParser(paramURL), _valueToUpdate);
  }
  
  public <T> T readValue(byte[] paramArrayOfByte)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return (T)_detectBindAndClose(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    return (T)_bindAndClose(_parserFactory.createParser(paramArrayOfByte), _valueToUpdate);
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return (T)_detectBindAndClose(paramArrayOfByte, paramInt1, paramInt2);
    }
    return (T)_bindAndClose(_parserFactory.createParser(paramArrayOfByte, paramInt1, paramInt2), _valueToUpdate);
  }
  
  public <T> MappingIterator<T> readValues(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramJsonParser, _config);
    return new MappingIterator(_valueType, paramJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext, _valueType), false, _valueToUpdate);
  }
  
  public <T> MappingIterator<T> readValues(File paramFile)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return _detectBindAndReadValues(_dataFormatReaders.findFormat(_inputStream(paramFile)), false);
    }
    return _bindAndReadValues(_parserFactory.createParser(paramFile), _valueToUpdate);
  }
  
  public <T> MappingIterator<T> readValues(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return _detectBindAndReadValues(_dataFormatReaders.findFormat(paramInputStream), false);
    }
    return _bindAndReadValues(_parserFactory.createParser(paramInputStream), _valueToUpdate);
  }
  
  public <T> MappingIterator<T> readValues(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    paramReader = _parserFactory.createParser(paramReader);
    if (_schema != null) {
      paramReader.setSchema(_schema);
    }
    paramReader.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramReader, _config);
    return new MappingIterator(_valueType, paramReader, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext, _valueType), true, _valueToUpdate);
  }
  
  public <T> MappingIterator<T> readValues(String paramString)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    paramString = _parserFactory.createParser(paramString);
    if (_schema != null) {
      paramString.setSchema(_schema);
    }
    paramString.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramString, _config);
    return new MappingIterator(_valueType, paramString, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext, _valueType), true, _valueToUpdate);
  }
  
  public <T> MappingIterator<T> readValues(URL paramURL)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return _detectBindAndReadValues(_dataFormatReaders.findFormat(_inputStream(paramURL)), true);
    }
    return _bindAndReadValues(_parserFactory.createParser(paramURL), _valueToUpdate);
  }
  
  public final <T> MappingIterator<T> readValues(byte[] paramArrayOfByte)
    throws IOException, JsonProcessingException
  {
    return readValues(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public <T> MappingIterator<T> readValues(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonProcessingException
  {
    if (_dataFormatReaders != null) {
      return _detectBindAndReadValues(_dataFormatReaders.findFormat(paramArrayOfByte, paramInt1, paramInt2), false);
    }
    return _bindAndReadValues(_parserFactory.createParser(paramArrayOfByte), _valueToUpdate);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException
  {
    return readValues(paramJsonParser, (JavaType)paramResolvedType);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException
  {
    return withType(paramTypeReference).readValues(paramJsonParser);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    return withType(paramJavaType).readValues(paramJsonParser);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException
  {
    return withType(paramClass).readValues(paramJsonParser);
  }
  
  public JsonParser treeAsTokens(TreeNode paramTreeNode)
  {
    return new TreeTraversingParser((JsonNode)paramTreeNode, this);
  }
  
  public <T> T treeToValue(TreeNode paramTreeNode, Class<T> paramClass)
    throws JsonProcessingException
  {
    try
    {
      paramTreeNode = readValue(treeAsTokens(paramTreeNode), paramClass);
      return paramTreeNode;
    }
    catch (JsonProcessingException paramTreeNode)
    {
      throw paramTreeNode;
    }
    catch (IOException paramTreeNode)
    {
      throw new IllegalArgumentException(paramTreeNode.getMessage(), paramTreeNode);
    }
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public ObjectReader with(Base64Variant paramBase64Variant)
  {
    return _with(_config.with(paramBase64Variant));
  }
  
  public ObjectReader with(FormatSchema paramFormatSchema)
  {
    if (_schema == paramFormatSchema) {
      return this;
    }
    _verifySchemaType(paramFormatSchema);
    return new ObjectReader(this, _config, _valueType, _rootDeserializer, _valueToUpdate, paramFormatSchema, _injectableValues, _dataFormatReaders);
  }
  
  public ObjectReader with(JsonFactory paramJsonFactory)
  {
    if (paramJsonFactory == _parserFactory) {
      return this;
    }
    ObjectReader localObjectReader = new ObjectReader(this, paramJsonFactory);
    if (paramJsonFactory.getCodec() == null) {
      paramJsonFactory.setCodec(localObjectReader);
    }
    return localObjectReader;
  }
  
  public ObjectReader with(DeserializationConfig paramDeserializationConfig)
  {
    return _with(paramDeserializationConfig);
  }
  
  public ObjectReader with(DeserializationFeature paramDeserializationFeature)
  {
    return _with(_config.with(paramDeserializationFeature));
  }
  
  public ObjectReader with(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    return _with(_config.with(paramDeserializationFeature, paramVarArgs));
  }
  
  public ObjectReader with(InjectableValues paramInjectableValues)
  {
    if (_injectableValues == paramInjectableValues) {
      return this;
    }
    return new ObjectReader(this, _config, _valueType, _rootDeserializer, _valueToUpdate, _schema, paramInjectableValues, _dataFormatReaders);
  }
  
  public ObjectReader with(ContextAttributes paramContextAttributes)
  {
    paramContextAttributes = _config.with(paramContextAttributes);
    if (paramContextAttributes == _config) {
      return this;
    }
    return new ObjectReader(this, paramContextAttributes);
  }
  
  public ObjectReader with(JsonNodeFactory paramJsonNodeFactory)
  {
    return _with(_config.with(paramJsonNodeFactory));
  }
  
  public ObjectReader with(Locale paramLocale)
  {
    return _with(_config.with(paramLocale));
  }
  
  public ObjectReader with(TimeZone paramTimeZone)
  {
    return _with(_config.with(paramTimeZone));
  }
  
  public ObjectReader withAttribute(Object paramObject1, Object paramObject2)
  {
    paramObject1 = (DeserializationConfig)_config.withAttribute(paramObject1, paramObject2);
    if (paramObject1 == _config) {
      return this;
    }
    return new ObjectReader(this, (DeserializationConfig)paramObject1);
  }
  
  public ObjectReader withAttributes(Map<Object, Object> paramMap)
  {
    paramMap = (DeserializationConfig)_config.withAttributes(paramMap);
    if (paramMap == _config) {
      return this;
    }
    return new ObjectReader(this, paramMap);
  }
  
  public ObjectReader withFeatures(DeserializationFeature... paramVarArgs)
  {
    return _with(_config.withFeatures(paramVarArgs));
  }
  
  public ObjectReader withFormatDetection(DataFormatReaders paramDataFormatReaders)
  {
    return new ObjectReader(this, _config, _valueType, _rootDeserializer, _valueToUpdate, _schema, _injectableValues, paramDataFormatReaders);
  }
  
  public ObjectReader withFormatDetection(ObjectReader... paramVarArgs)
  {
    return withFormatDetection(new DataFormatReaders(paramVarArgs));
  }
  
  public ObjectReader withHandler(DeserializationProblemHandler paramDeserializationProblemHandler)
  {
    return _with(_config.withHandler(paramDeserializationProblemHandler));
  }
  
  public ObjectReader withRootName(String paramString)
  {
    return _with(_config.withRootName(paramString));
  }
  
  public ObjectReader withType(TypeReference<?> paramTypeReference)
  {
    return withType(_config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectReader withType(JavaType paramJavaType)
  {
    if ((paramJavaType != null) && (paramJavaType.equals(_valueType))) {
      return this;
    }
    JsonDeserializer localJsonDeserializer = _prefetchRootDeserializer(_config, paramJavaType);
    DataFormatReaders localDataFormatReaders2 = _dataFormatReaders;
    DataFormatReaders localDataFormatReaders1 = localDataFormatReaders2;
    if (localDataFormatReaders2 != null) {
      localDataFormatReaders1 = localDataFormatReaders2.withType(paramJavaType);
    }
    return new ObjectReader(this, _config, paramJavaType, localJsonDeserializer, _valueToUpdate, _schema, _injectableValues, localDataFormatReaders1);
  }
  
  public ObjectReader withType(Class<?> paramClass)
  {
    return withType(_config.constructType(paramClass));
  }
  
  public ObjectReader withType(Type paramType)
  {
    return withType(_config.getTypeFactory().constructType(paramType));
  }
  
  public ObjectReader withValueToUpdate(Object paramObject)
  {
    if (paramObject == _valueToUpdate) {
      return this;
    }
    if (paramObject == null) {
      throw new IllegalArgumentException("cat not update null value");
    }
    if (_valueType == null) {}
    for (JavaType localJavaType = _config.constructType(paramObject.getClass());; localJavaType = _valueType) {
      return new ObjectReader(this, _config, localJavaType, _rootDeserializer, paramObject, _schema, _injectableValues, _dataFormatReaders);
    }
  }
  
  public ObjectReader withView(Class<?> paramClass)
  {
    return _with(_config.withView(paramClass));
  }
  
  public ObjectReader without(DeserializationFeature paramDeserializationFeature)
  {
    return _with(_config.without(paramDeserializationFeature));
  }
  
  public ObjectReader without(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    return _with(_config.without(paramDeserializationFeature, paramVarArgs));
  }
  
  public ObjectReader withoutAttribute(Object paramObject)
  {
    paramObject = (DeserializationConfig)_config.withoutAttribute(paramObject);
    if (paramObject == _config) {
      return this;
    }
    return new ObjectReader(this, (DeserializationConfig)paramObject);
  }
  
  public ObjectReader withoutFeatures(DeserializationFeature... paramVarArgs)
  {
    return _with(_config.withoutFeatures(paramVarArgs));
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, TreeNode paramTreeNode)
  {
    throw new UnsupportedOperationException();
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonProcessingException
  {
    throw new UnsupportedOperationException("Not implemented for ObjectReader");
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ObjectReader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */