package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.LinkedNode;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class DeserializationConfig
  extends MapperConfigBase<DeserializationFeature, DeserializationConfig>
  implements Serializable
{
  private static final long serialVersionUID = -4227480407273773599L;
  protected final int _deserFeatures;
  protected final JsonNodeFactory _nodeFactory;
  protected final LinkedNode<DeserializationProblemHandler> _problemHandlers;
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, int paramInt1, int paramInt2)
  {
    super(paramDeserializationConfig, paramInt1);
    _deserFeatures = paramInt2;
    _nodeFactory = _nodeFactory;
    _problemHandlers = _problemHandlers;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, BaseSettings paramBaseSettings)
  {
    super(paramDeserializationConfig, paramBaseSettings);
    _deserFeatures = _deserFeatures;
    _nodeFactory = _nodeFactory;
    _problemHandlers = _problemHandlers;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, ContextAttributes paramContextAttributes)
  {
    super(paramDeserializationConfig, paramContextAttributes);
    _deserFeatures = _deserFeatures;
    _problemHandlers = _problemHandlers;
    _nodeFactory = _nodeFactory;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, SubtypeResolver paramSubtypeResolver)
  {
    super(paramDeserializationConfig, paramSubtypeResolver);
    _deserFeatures = _deserFeatures;
    _nodeFactory = _nodeFactory;
    _problemHandlers = _problemHandlers;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, JsonNodeFactory paramJsonNodeFactory)
  {
    super(paramDeserializationConfig);
    _deserFeatures = _deserFeatures;
    _problemHandlers = _problemHandlers;
    _nodeFactory = paramJsonNodeFactory;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, LinkedNode<DeserializationProblemHandler> paramLinkedNode)
  {
    super(paramDeserializationConfig);
    _deserFeatures = _deserFeatures;
    _problemHandlers = paramLinkedNode;
    _nodeFactory = _nodeFactory;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
  {
    super(paramDeserializationConfig, paramClass);
    _deserFeatures = _deserFeatures;
    _problemHandlers = _problemHandlers;
    _nodeFactory = _nodeFactory;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, String paramString)
  {
    super(paramDeserializationConfig, paramString);
    _deserFeatures = _deserFeatures;
    _problemHandlers = _problemHandlers;
    _nodeFactory = _nodeFactory;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, Map<ClassKey, Class<?>> paramMap)
  {
    super(paramDeserializationConfig, paramMap);
    _deserFeatures = _deserFeatures;
    _problemHandlers = _problemHandlers;
    _nodeFactory = _nodeFactory;
  }
  
  public DeserializationConfig(BaseSettings paramBaseSettings, SubtypeResolver paramSubtypeResolver, Map<ClassKey, Class<?>> paramMap)
  {
    super(paramBaseSettings, paramSubtypeResolver, paramMap);
    _deserFeatures = collectFeatureDefaults(DeserializationFeature.class);
    _nodeFactory = JsonNodeFactory.instance;
    _problemHandlers = null;
  }
  
  private final DeserializationConfig _withBase(BaseSettings paramBaseSettings)
  {
    if (_base == paramBaseSettings) {
      return this;
    }
    return new DeserializationConfig(this, paramBaseSettings);
  }
  
  public AnnotationIntrospector getAnnotationIntrospector()
  {
    if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {
      return super.getAnnotationIntrospector();
    }
    return NopAnnotationIntrospector.instance;
  }
  
  protected BaseSettings getBaseSettings()
  {
    return _base;
  }
  
  public VisibilityChecker<?> getDefaultVisibilityChecker()
  {
    Object localObject2 = super.getDefaultVisibilityChecker();
    Object localObject1 = localObject2;
    if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
      localObject1 = ((VisibilityChecker)localObject2).withSetterVisibility(JsonAutoDetect.Visibility.NONE);
    }
    localObject2 = localObject1;
    if (!isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
      localObject2 = ((VisibilityChecker)localObject1).withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
    }
    localObject1 = localObject2;
    if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
      localObject1 = ((VisibilityChecker)localObject2).withFieldVisibility(JsonAutoDetect.Visibility.NONE);
    }
    return (VisibilityChecker<?>)localObject1;
  }
  
  public final int getDeserializationFeatures()
  {
    return _deserFeatures;
  }
  
  public final JsonNodeFactory getNodeFactory()
  {
    return _nodeFactory;
  }
  
  public LinkedNode<DeserializationProblemHandler> getProblemHandlers()
  {
    return _problemHandlers;
  }
  
  public final boolean hasDeserializationFeatures(int paramInt)
  {
    return (_deserFeatures & paramInt) == paramInt;
  }
  
  public <T extends BeanDescription> T introspect(JavaType paramJavaType)
  {
    return getClassIntrospector().forDeserialization(this, paramJavaType, this);
  }
  
  public BeanDescription introspectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forClassAnnotations(this, paramJavaType, this);
  }
  
  public BeanDescription introspectDirectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forDirectClassAnnotations(this, paramJavaType, this);
  }
  
  public <T extends BeanDescription> T introspectForBuilder(JavaType paramJavaType)
  {
    return getClassIntrospector().forDeserializationWithBuilder(this, paramJavaType, this);
  }
  
  public <T extends BeanDescription> T introspectForCreation(JavaType paramJavaType)
  {
    return getClassIntrospector().forCreation(this, paramJavaType, this);
  }
  
  public final boolean isEnabled(DeserializationFeature paramDeserializationFeature)
  {
    return (_deserFeatures & paramDeserializationFeature.getMask()) != 0;
  }
  
  public boolean useRootWrapping()
  {
    if (_rootName != null) {
      return _rootName.length() > 0;
    }
    return isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
  }
  
  public DeserializationConfig with(Base64Variant paramBase64Variant)
  {
    return _withBase(_base.with(paramBase64Variant));
  }
  
  public DeserializationConfig with(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(_base.withAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig with(DeserializationFeature paramDeserializationFeature)
  {
    int i = _deserFeatures;
    i = paramDeserializationFeature.getMask() | i;
    if (i == _deserFeatures) {
      return this;
    }
    return new DeserializationConfig(this, _mapperFeatures, i);
  }
  
  public DeserializationConfig with(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    int i = _deserFeatures;
    int j = paramDeserializationFeature.getMask() | i;
    int k = paramVarArgs.length;
    i = 0;
    while (i < k)
    {
      j |= paramVarArgs[i].getMask();
      i += 1;
    }
    if (j == _deserFeatures) {
      return this;
    }
    return new DeserializationConfig(this, _mapperFeatures, j);
  }
  
  public DeserializationConfig with(MapperFeature paramMapperFeature, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = _mapperFeatures | paramMapperFeature.getMask(); i == _mapperFeatures; i = _mapperFeatures & (paramMapperFeature.getMask() ^ 0xFFFFFFFF)) {
      return this;
    }
    return new DeserializationConfig(this, i, _deserFeatures);
  }
  
  public DeserializationConfig with(PropertyNamingStrategy paramPropertyNamingStrategy)
  {
    return _withBase(_base.withPropertyNamingStrategy(paramPropertyNamingStrategy));
  }
  
  public DeserializationConfig with(ContextAttributes paramContextAttributes)
  {
    if (paramContextAttributes == _attributes) {
      return this;
    }
    return new DeserializationConfig(this, paramContextAttributes);
  }
  
  public DeserializationConfig with(HandlerInstantiator paramHandlerInstantiator)
  {
    return _withBase(_base.withHandlerInstantiator(paramHandlerInstantiator));
  }
  
  public DeserializationConfig with(ClassIntrospector paramClassIntrospector)
  {
    return _withBase(_base.withClassIntrospector(paramClassIntrospector));
  }
  
  public DeserializationConfig with(VisibilityChecker<?> paramVisibilityChecker)
  {
    return _withBase(_base.withVisibilityChecker(paramVisibilityChecker));
  }
  
  public DeserializationConfig with(SubtypeResolver paramSubtypeResolver)
  {
    if (_subtypeResolver == paramSubtypeResolver) {
      return this;
    }
    return new DeserializationConfig(this, paramSubtypeResolver);
  }
  
  public DeserializationConfig with(TypeResolverBuilder<?> paramTypeResolverBuilder)
  {
    return _withBase(_base.withTypeResolverBuilder(paramTypeResolverBuilder));
  }
  
  public DeserializationConfig with(JsonNodeFactory paramJsonNodeFactory)
  {
    if (_nodeFactory == paramJsonNodeFactory) {
      return this;
    }
    return new DeserializationConfig(this, paramJsonNodeFactory);
  }
  
  public DeserializationConfig with(TypeFactory paramTypeFactory)
  {
    return _withBase(_base.withTypeFactory(paramTypeFactory));
  }
  
  public DeserializationConfig with(DateFormat paramDateFormat)
  {
    return _withBase(_base.withDateFormat(paramDateFormat));
  }
  
  public DeserializationConfig with(Locale paramLocale)
  {
    return _withBase(_base.with(paramLocale));
  }
  
  public DeserializationConfig with(TimeZone paramTimeZone)
  {
    return _withBase(_base.with(paramTimeZone));
  }
  
  public DeserializationConfig with(MapperFeature... paramVarArgs)
  {
    int j = _mapperFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j |= paramVarArgs[i].getMask();
      i += 1;
    }
    if (j == _mapperFeatures) {
      return this;
    }
    return new DeserializationConfig(this, j, _deserFeatures);
  }
  
  public DeserializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(_base.withAppendedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig withFeatures(DeserializationFeature... paramVarArgs)
  {
    int j = _deserFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j |= paramVarArgs[i].getMask();
      i += 1;
    }
    if (j == _deserFeatures) {
      return this;
    }
    return new DeserializationConfig(this, _mapperFeatures, j);
  }
  
  public DeserializationConfig withHandler(DeserializationProblemHandler paramDeserializationProblemHandler)
  {
    if (LinkedNode.contains(_problemHandlers, paramDeserializationProblemHandler)) {
      return this;
    }
    return new DeserializationConfig(this, new LinkedNode(paramDeserializationProblemHandler, _problemHandlers));
  }
  
  public DeserializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(_base.withInsertedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig withNoProblemHandlers()
  {
    if (_problemHandlers == null) {
      return this;
    }
    return new DeserializationConfig(this, (LinkedNode)null);
  }
  
  public DeserializationConfig withRootName(String paramString)
  {
    if (paramString == null)
    {
      if (_rootName != null) {}
    }
    else {
      while (paramString.equals(_rootName)) {
        return this;
      }
    }
    return new DeserializationConfig(this, paramString);
  }
  
  public DeserializationConfig withView(Class<?> paramClass)
  {
    if (_view == paramClass) {
      return this;
    }
    return new DeserializationConfig(this, paramClass);
  }
  
  public DeserializationConfig withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility)
  {
    return _withBase(_base.withVisibility(paramPropertyAccessor, paramVisibility));
  }
  
  public DeserializationConfig without(DeserializationFeature paramDeserializationFeature)
  {
    int i = _deserFeatures;
    i = (paramDeserializationFeature.getMask() ^ 0xFFFFFFFF) & i;
    if (i == _deserFeatures) {
      return this;
    }
    return new DeserializationConfig(this, _mapperFeatures, i);
  }
  
  public DeserializationConfig without(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    int i = _deserFeatures;
    int j = (paramDeserializationFeature.getMask() ^ 0xFFFFFFFF) & i;
    int k = paramVarArgs.length;
    i = 0;
    while (i < k)
    {
      j &= (paramVarArgs[i].getMask() ^ 0xFFFFFFFF);
      i += 1;
    }
    if (j == _deserFeatures) {
      return this;
    }
    return new DeserializationConfig(this, _mapperFeatures, j);
  }
  
  public DeserializationConfig without(MapperFeature... paramVarArgs)
  {
    int j = _mapperFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j &= (paramVarArgs[i].getMask() ^ 0xFFFFFFFF);
      i += 1;
    }
    if (j == _mapperFeatures) {
      return this;
    }
    return new DeserializationConfig(this, j, _deserFeatures);
  }
  
  public DeserializationConfig withoutFeatures(DeserializationFeature... paramVarArgs)
  {
    int j = _deserFeatures;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j &= (paramVarArgs[i].getMask() ^ 0xFFFFFFFF);
      i += 1;
    }
    if (j == _deserFeatures) {
      return this;
    }
    return new DeserializationConfig(this, _mapperFeatures, j);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.DeserializationConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */